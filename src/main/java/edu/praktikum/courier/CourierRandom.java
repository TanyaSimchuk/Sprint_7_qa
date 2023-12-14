package edu.praktikum.courier;

import edu.praktikum.models.CourierCreate;

import static edu.praktikum.utils.RandomCores.randomString;

public class CourierRandom {
    public static CourierCreate randomCourierCreate() {
        return new CourierCreate()
                .withLogin(randomString(8))
                .withPassword(randomString(16))
                .withFirstName(randomString(12));
    }
}