import edu.praktikum.courier.CourierClient;
import edu.praktikum.models.CourierCreate;
import edu.praktikum.models.CourierWithWrongDataCreate;
import edu.praktikum.models.CourierWithoutFullData;
import edu.praktikum.models.SameCourierCred;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static edu.praktikum.courier.CourierRandom.randomCourierCreate;
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
    @Description("Проверка возможности создания различных курьеров")
    public void createNewCourier() {

        Response createResponse = courierClient.create(courierCreate);
        createResponse.path("id");
        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());
        assertEquals("Неверное сообщение при успешном создании курьера",
                true, createResponse.path("ok"));

        Response sameFullDataCreateResponse = courierClient.sameCreate(SameCourierCred.fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_CONFLICT, sameFullDataCreateResponse.statusCode());

        Response createWithoutFullDataResponse = courierClient.createWithoutFullData(CourierWithoutFullData.fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_BAD_REQUEST, createWithoutFullDataResponse.statusCode());

        Response createWithSameLoginResponse = courierClient.createWithSameLogin(CourierWithWrongDataCreate.fromCourierCreate(courierCreate));
        assertEquals("Неверный статус код", SC_CONFLICT, createWithSameLoginResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке при создании курьера с такими же данными",
                "Этот логин уже используется. Попробуйте другой.", createWithSameLoginResponse.path("message"));
    }

    @After
    public void tearDown() {
        CourierClient courierClient = new CourierClient();
        courierClient.delete("id");
    }

}