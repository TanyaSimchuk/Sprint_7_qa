package edu.praktikum.models;

public class CourierLogin {
    private String login;
    private String password;

    public CourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierLogin fromCourierCreate(CourierCreate courierCreate) {
        return new CourierLogin(courierCreate.getLogin(), courierCreate.getPassword());
    }
}