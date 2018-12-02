package com.plazi.javatests;

public class StringUtil {

    public static String repeat(int times, String text) {

        String result = "";

        for (int i = 0; i < times; i++) {
            result += text;
        }

        return result;
    }
}
