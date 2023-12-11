package edu.praktikum.order;


import java.util.List;

import static edu.praktikum.utils.RandomCores.*;
public class OrderRandom {
    public static OrderCreate randomOrderCreate() {
        return new OrderCreate()
                .withFirstName(randomString(16))
                .withLastName(randomString(16))
                .withAddress(randomString(30))
                .withMetroStation(randomMetroStation())
                .withPhone(randomPhone())
                .withRentTime(randomRentTime())
                .withDeliveryDate(randomDate(2023, 2024))
                .withComment(randomString(30))
                .withColor(List.of(randomString(10)));
    }
}