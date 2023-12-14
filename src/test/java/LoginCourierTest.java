import edu.praktikum.courier.CourierClient;
import edu.praktikum.models.CourierCreate;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static edu.praktikum.courier.CourierRandom.randomCourierCreate;
import static edu.praktikum.models.CourierCreateCreds.credsFrom;
import static edu.praktikum.utils.RandomCores.randomString;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest {
    private CourierCreate courierCreate;
    private CourierClient courierClient;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;

        courierCreate = randomCourierCreate();
        courierClient = new CourierClient();
        courierClient.create(courierCreate);
    }

    @Test
    @DisplayName("Login courier")
    @Description("Проверка успешной авторизации курьера")
    public void loginCourier() {
        Response loginResponse = courierClient.login(credsFrom(courierCreate));
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
        assertThat("Пустое тело ответа сервера", loginResponse.path("id"), instanceOf(Integer.class));
    }
    @Test
    @DisplayName("Wrong login courier")
    @Description("Проверка авторизации курьера с неправильным логином")
    public void loginWithWrongData() {
        CourierCreate wrongCourierCreate = new CourierCreate();
        wrongCourierCreate.withLogin(randomString(8)).withPassword(courierCreate.getPassword());
        Response loginWithWrongDataResponse = courierClient.login(credsFrom(wrongCourierCreate));
        assertEquals("Неверный статус код", SC_NOT_FOUND, loginWithWrongDataResponse.statusCode());
    }
    @Test
    @DisplayName("Empty login courier")
    @Description("Проверка авторизации курьера без логина")
    public void loginWithEmptyField() {
        CourierCreate emptyLoginCreate = new CourierCreate();
        emptyLoginCreate.withPassword(courierCreate.getPassword());
        Response emptyLoginResponse = courierClient.login(credsFrom(emptyLoginCreate));
        assertEquals("Неверный статус код", SC_BAD_REQUEST, emptyLoginResponse.statusCode());
    }
    @Test
    @DisplayName("Nonexisting courier login")
    @Description("Проверка авторизации несуществующего курьера")
    public void loginNonexistingCourier() {
        CourierCreate nonexistingCourier = new CourierCreate();
        nonexistingCourier.withLogin(randomString(10)).withPassword(randomString(14));
        Response loginNonexistingResponse = courierClient.login(credsFrom(nonexistingCourier));
        assertEquals("Неверный статус код", SC_NOT_FOUND, loginNonexistingResponse.statusCode());
    }

    @After
    public void tearDown() {
        Response loginResponse = courierClient.login(credsFrom(courierCreate));
        int courierId = loginResponse.path("id");
        CourierClient.deleteCourierById(courierId);
    }

}