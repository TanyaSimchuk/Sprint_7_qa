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
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CreateCourierTest {
    private CourierClient courierClient;
    private CourierCreate courierCreate;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;

        courierCreate = randomCourierCreate();
        courierClient = new CourierClient();
    }
    @Test
    @DisplayName("Create courier")
    @Description("Проверка успешного создания курьера")
    public void createNewCourier() {

        Response createResponse = courierClient.create(courierCreate);
        createResponse.path("id");
        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());
        assertEquals("Неверное сообщение при успешном создании курьера",
                true, createResponse.path("ok"));
    }

    @Test
    @DisplayName("Create same courier")
    @Description("Проверка создания курьера с одинаковыми данными")
    public void createSameCourier() {
        Response firstCourierResponse = courierClient.create(courierCreate);
        assertEquals("Неверный статус код", SC_CREATED, firstCourierResponse.statusCode());
        Response sameCourierResponse = courierClient.create(courierCreate);
        assertEquals("Неверный статус код", SC_CONFLICT, sameCourierResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке при создании курьера с такими же данными",
                "Этот логин уже используется. Попробуйте другой.", sameCourierResponse.path("message"));
    }

    @After
    public void tearDown() {
        Response loginResponse = courierClient.login(credsFrom(courierCreate));
        int courierId = loginResponse.path("id");
        CourierClient.deleteCourierById(courierId);
    }
}