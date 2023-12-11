import edu.praktikum.courier.CourierClient;
import edu.praktikum.models.CourierCreate;
import edu.praktikum.models.CourierWithWrongDataLogin;
import edu.praktikum.models.CourierWithoutFullData;
import edu.praktikum.models.NonexistingCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static edu.praktikum.courier.CourierRandom.randomCourierCreate;
import static edu.praktikum.models.CourierLogin.fromCourierCreate;
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
    }

    @Test
    @DisplayName("Login courier")
    @Description("Проверка авторизации курьеров")
    public void loginCourier() {

        Response createResponse = courierClient.create(courierCreate);
        createResponse.path("id");
        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());

        Response loginResponse = courierClient.login(fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
        assertThat("Пустое тело ответа сервера", loginResponse.path("id"), instanceOf(Integer.class));

        Response loginWithoutFullDataResponse = courierClient.loginWithoutFullData(CourierWithoutFullData.fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_BAD_REQUEST, loginWithoutFullDataResponse.statusCode());

        Response loginWrongDataResponse = courierClient.loginWithWrongData(CourierWithWrongDataLogin.fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_NOT_FOUND, loginWrongDataResponse.statusCode());

        Response loginNonexistingResponse = courierClient.loginNonexistingCourier(NonexistingCourier.fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_NOT_FOUND, loginNonexistingResponse.statusCode());
    }

    @After
    public void tearDown() {
        CourierClient courierClient = new CourierClient();
        courierClient.delete("id");
    }

}