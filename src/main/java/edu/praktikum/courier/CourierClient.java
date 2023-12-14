package edu.praktikum.courier;

import edu.praktikum.models.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierClient {
    private static final String COURIER_API = "/api/v1/courier";
    private static final String COURIER_LOGIN_API = "/api/v1/courier/login";
    private static final String COURIER_DELETE_API = "/api/v1/courier/{id}";
    @Step("Creating a new courier with randomly data")
    public Response create(CourierCreate courierCreate) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courierCreate)
                .when()
                .post(COURIER_API);
    }
    @Step("Logging a courier with randomly collected data")
    public Response login(CourierCreateCreds creds) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(COURIER_LOGIN_API);
    }

    @Step("Deleting a courier by courierId")
    public static void deleteCourierById(int courierId) {
        given()
                .pathParam("id", courierId)
                .when()
                .delete(COURIER_DELETE_API)
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}
