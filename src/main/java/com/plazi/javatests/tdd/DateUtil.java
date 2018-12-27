package com.plazi.javatests.tdd;

public class DateUtil {

    public static boolean isLeapYear(int year) {

        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }
}
