package edu.praktikum.courier;

import edu.praktikum.models.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String COURIER_API = "/api/v1/courier";
    private static final String COURIER_LOGIN_API = "/api/v1/courier/login";
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
    public Response login(CourierLogin courierLogin) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courierLogin)
                .when()
                .post(COURIER_LOGIN_API);
    }
    @Step("Creating a new courier with same data")
    public Response sameCreate(SameCourierCred sameCourierCred) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(sameCourierCred)
                .when()
                .post(COURIER_API);
    }
    @Step("Creating a new courier without a complete data set")
    public Response createWithoutFullData(CourierWithoutFullData courierWithoutFullData) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courierWithoutFullData)
                .when()
                .post(COURIER_API);
    }
    @Step("Creating a new courier with same login")
    public Response createWithSameLogin(CourierWithWrongDataCreate courierWithWrongDataCreate) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courierWithWrongDataCreate)
                .when()
                .post(COURIER_API);
    }
    @Step("Logging a courier without full data set")
    public Response loginWithoutFullData(CourierWithoutFullData courierWithoutFullData) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courierWithoutFullData)
                .when()
                .post(COURIER_LOGIN_API);
    }
    @Step("Logging a courier with wrong data set")
    public Response loginWithWrongData(CourierWithWrongDataLogin courierWithWrongData) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courierWithWrongData)
                .when()
                .post(COURIER_LOGIN_API);
    }
    @Step("Logging a nonexisting courier")
    public Response loginNonexistingCourier(NonexistingCourier nonexistingCourier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(nonexistingCourier)
                .when()
                .post(COURIER_LOGIN_API);
    }
    @Step("Deleting created courier")
    public void delete(String id) {
    }
}
