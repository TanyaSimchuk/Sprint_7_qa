package edu.praktikum.models;

public class CourierCreateCreds {
    private final String login;
    private final String password;

    public CourierCreateCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCreateCreds credsFrom(CourierCreate courierCreate) {
        return new CourierCreateCreds(courierCreate.getLogin(), courierCreate.getPassword());
    }
}
