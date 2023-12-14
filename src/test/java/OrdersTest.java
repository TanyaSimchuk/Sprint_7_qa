import edu.praktikum.order.OrderMap;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class OrdersTest {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Getting orders list")
    @Description("Получение списка заказов")
    public void checkOrder() {
        OrderMap orderMap = new OrderMap();

        Response getOrdersResponse = orderMap.getOrdersList();
        assertEquals("Неверный статус код", SC_OK, getOrdersResponse.statusCode());
        assertThat("Пустое тело ответа сервера", getOrdersResponse.path("orders"), instanceOf(List.class));
    }

}