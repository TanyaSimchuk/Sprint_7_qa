package edu.praktikum.models;

public class CourierWithoutFullData {
    private String password;

    public CourierWithoutFullData(String password) {
        this.password = password;
    }

    public static CourierWithoutFullData fromCourierCreate(CourierCreate courierCreate) {
        return new CourierWithoutFullData(courierCreate.getPassword());
    }
}