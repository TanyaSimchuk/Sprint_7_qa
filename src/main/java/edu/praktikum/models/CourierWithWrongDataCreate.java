package edu.praktikum.models;

import static edu.praktikum.utils.RandomCores.randomString;

public class CourierWithWrongDataCreate {
    private String login;
    private String password;
    private String firstName;

    public CourierWithWrongDataCreate(String login, String password,String firstName) {
        this.password = password;
        this.login = login;
        this.firstName = firstName;
    }

    public static CourierWithWrongDataCreate fromCourierCreate(CourierCreate courierCreate) {
        return new CourierWithWrongDataCreate(courierCreate.getLogin(), randomString(16), randomString(12));
    }
}