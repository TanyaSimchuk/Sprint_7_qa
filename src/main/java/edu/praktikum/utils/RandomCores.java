package edu.praktikum.utils;

import java.util.Random;


public class RandomCores {
    public static String randomString(int length) {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 128;
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (float) (rightLimit - leftLimit + 1));
            buffer.append(Character.toChars(randomLimitedInt));
        }
        return buffer.toString();
    }

    private static final int PHONE_NUMBER_LENGTH = 12;

    public static String randomPhone() {
        String possibleNumbers = "123456789";
        StringBuffer buffer = new StringBuffer();

        for (int i = 2; i < PHONE_NUMBER_LENGTH; i++) {
            buffer.append("+7" + possibleNumbers.charAt(new Random().nextInt(possibleNumbers.length())));
        }
        return buffer.toString();
    }

    private static final int RENT_DAYS = 5;

    public static int randomRentTime() {
        Random random = new Random();
        int randomRent = random.nextInt(RENT_DAYS);
        return randomRent;
    }

    private static final int MOSCOW_METRO_STATIONS = 263;

    public static String randomMetroStation() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();

        int randomMetro = random.nextInt(MOSCOW_METRO_STATIONS);
        buffer.append(Character.toChars(randomMetro));
        return buffer.toString();
    }

    public static String randomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        String randomDate = year + "-" + month + "-" + day;
        return randomDate;
    }
    public static int createRandomIntBetween(int start, int end){
        return start + (int) Math.round(Math.random() * (end - start));
    }

}