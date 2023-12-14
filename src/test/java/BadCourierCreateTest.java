import edu.praktikum.courier.CourierClient;
import edu.praktikum.models.CourierCreate;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static edu.praktikum.models.CourierCreateCreds.credsFrom;
import static edu.praktikum.utils.RandomCores.randomString;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BadCourierCreateTest {
    private CourierClient courierClient;
    private CourierCreate courierCreate;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;

        courierClient = new CourierClient();
    }

    @Parameterized.Parameter
    public String login;
    @Parameterized.Parameter(1)
    public String password;
    @Parameterized.Parameter(2)
    public String firstName;

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"", randomString(10), randomString(16)},
                {randomString(8), "", randomString(16)},
                {randomString(8), randomString(10), ""},
        };
    }

    @Test
    @DisplayName("Create bad data courier")
    @Description("Проверка создания курьеров с неправильными данными")
    public void createNegativeData() {
        courierCreate = new CourierCreate();
        Response response = courierClient.create(courierCreate);
        assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
        assertEquals("Некорректное сообщение об ошибке создания курьера без необходимых данных",
                "Недостаточно данных для создания учетной записи", response.path("message"));
    }

    @After
    public void tearDown() {
        Response loginResponse = courierClient.login(credsFrom(courierCreate));

        if (loginResponse.statusCode() == SC_OK) {
            int courierId = loginResponse.path("id");
            CourierClient.deleteCourierById(courierId);
        }
    }
}
