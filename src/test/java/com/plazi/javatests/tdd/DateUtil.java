package com.plazi.javatests.tdd;

public class DateUtil {

    public static boolean isLeapYear(int year) {

        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        if (year % 4 != 0) return false;
        return true;
    }
}
