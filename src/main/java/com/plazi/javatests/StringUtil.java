package com.plazi.javatests;

public class StringUtil {

    public static String repeat(int times, String text) {

        if (times < 0) throw new IllegalArgumentException("negative times: " + times);
        if (times == 0) return "";
        if (times == 1) return text;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < times; i++) {
            result.append(text);
        }

        return result.toString();
    }
}
