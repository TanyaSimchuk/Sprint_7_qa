package edu.praktikum.models;
import static edu.praktikum.utils.RandomCores.randomString;

public class CourierWithWrongDataLogin {
    private String login;
    private String password;

    public CourierWithWrongDataLogin(String login, String password) {
        this.password = password;
        this.login = login;
    }

    public static CourierWithWrongDataLogin fromCourierCreate(CourierCreate courierCreate) {
        return new CourierWithWrongDataLogin(courierCreate.getLogin(), randomString(16));
    }
}