package com.plazi.javatests.intro;

import java.util.regex.Pattern;

public class PasswordUtil {

    public enum SecurityLevel {
        WEAK, MEDIUM, STRONG
    }

    private static final Pattern alpha = Pattern.compile("[a-zA-Z]+");
    private static final Pattern alphaNumeric = Pattern.compile("[a-zA-Z0-9]+");

    public static SecurityLevel assessSecurity(String password) {

        if (password.length() < 8 || alpha.matcher(password).matches()) {
            return SecurityLevel.WEAK;
        }

        if (alphaNumeric.matcher(password).matches()) {
            return SecurityLevel.MEDIUM;
        }

        return SecurityLevel.STRONG;
    }
}
