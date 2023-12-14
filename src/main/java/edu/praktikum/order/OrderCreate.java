package edu.praktikum.order;

import java.util.List;

public class OrderCreate {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public OrderCreate withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public OrderCreate withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public OrderCreate withAddress(String address) {
        this.address = address;
        return this;
    }
    public OrderCreate withMetroStation(String metroStation) {
        this.metroStation = metroStation;
        return this;
    }
    public OrderCreate withPhone (String phone) {
        this.phone = phone;
        return this;
    }
    public OrderCreate withRentTime (int rentTime) {
        this.rentTime = rentTime;
        return this;
    }
    public OrderCreate withDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }
    public OrderCreate withComment(String comment) {
        this.comment = comment;
        return this;
    }
    public OrderCreate withColor(List<String> color) {
        this.color = color;
        return this;
    }
}