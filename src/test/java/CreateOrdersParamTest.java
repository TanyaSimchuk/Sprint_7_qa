import edu.praktikum.order.OrderCreate;
import edu.praktikum.order.OrderMap;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static edu.praktikum.order.OrderRandom.randomOrderCreate;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CreateOrdersParamTest {
    private List<String> scooterColor;
    private OrderCreate orderCreate;
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("GREY","BLACK")},
                {List.of()},
        };
    }
    public CreateOrdersParamTest(List<String> scooterColor) {
        this.scooterColor = scooterColor;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Orders with setting different scooter colors")
    @Description("Проверка возможности создания заказов с разными цветами скутеров")
    public void createNewOrder() {
        OrderMap orderMap = new OrderMap();
        orderCreate = randomOrderCreate();
        orderCreate.withColor(scooterColor);

        Response createResponse = orderMap.createAnOrder(orderCreate);
        createResponse.path("track");
        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());
        assertThat("Пустое тело ответа сервера", createResponse.path("track"), instanceOf(Integer.class));
    }

    @After
    public void tearDown() {
        OrderCreate orderCreate = new OrderCreate();
        orderCreate.delete("track");
    }

}