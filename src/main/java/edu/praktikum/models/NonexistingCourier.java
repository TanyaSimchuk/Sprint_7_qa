package edu.praktikum.models;

import static edu.praktikum.utils.RandomCores.randomString;

public class NonexistingCourier {

    private String login;
    private String password;

    public NonexistingCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static NonexistingCourier fromCourierCreate(CourierCreate courierCreate) {
        return new NonexistingCourier(randomString(8), randomString(16));
    }
}