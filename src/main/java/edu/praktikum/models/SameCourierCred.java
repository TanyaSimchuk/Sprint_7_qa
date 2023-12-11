package edu.praktikum.models;

public class SameCourierCred {
    private String login;
    private String password;
    private String firstName;
    public SameCourierCred(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public static SameCourierCred fromCourierCreate(CourierCreate courierCreate) {
        return new SameCourierCred(courierCreate.getLogin(), courierCreate.getPassword(), courierCreate.getFirstName());
    }

}