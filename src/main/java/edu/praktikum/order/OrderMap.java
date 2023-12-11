package edu.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderMap {
    private static final String ORDERS_API = "/api/v1/orders";

    @Step("Getting orders list")
    public Response getOrdersList() {
        return given()
                .header("Content-Type", "application/json")
                .get(ORDERS_API);
    }
    @Step("Creating an order with different colors")
    public Response createAnOrder(OrderCreate orderCreate) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(orderCreate)
                .when()
                .post(ORDERS_API);
    }
}