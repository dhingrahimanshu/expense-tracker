package com.himanshudhingra.util;

public class Util {
    public static boolean isValidMonth (int month) {
        return month >= 1 && month <= 12;
    }

    public static boolean isValidAmount (int amount) {
        return amount > 0;
    }
}
