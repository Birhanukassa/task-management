package com.github.birhanukassa.taskmanagement.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Validator {
    private Validator() {
        // Private constructor to prevent instantiation of this class
    }

    public static boolean test(Object userInput, Class<?> targetClass) {
        if (userInput == null) return false;
        if (String.class.equals(targetClass)) return isValidStringOrChar(userInput);
        if (Integer.class.equals(targetClass)) return isInt(userInput);
        if (Double.class.equals(targetClass)) return userInput instanceof Double && (double) userInput > 0;
        if (Float.class.equals(targetClass)) return userInput instanceof Float && (float) userInput > 0;
        if (Long.class.equals(targetClass)) return userInput instanceof Long && (long) userInput > 0;
        if (LocalDate.class.equals(targetClass)) {
            return isValidDate((LocalDate) userInput);
        } else if (LocalTime.class.equals(targetClass)) {
            return isValidTime((LocalTime) userInput);
        }
        return false;
    }

    // date formatted string input validator 
    public static boolean isValidDatePattern(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

        try {
            isValidDate(LocalDate.parse(userInput, formatter));
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e);
                return false;
        }
    }

    // time formatted string input validator
    public static boolean isValidTimePattern(String timeStr) {
        final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            isValidTime(LocalTime.parse(timeStr, timeFormatter));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isValidDate(LocalDate userInput) {
        return userInput != null && !(userInput).isBefore(LocalDate.now());
    }

    public static boolean isValidTime(LocalTime time) {
        return time != null && !(time).isBefore(LocalTime.now());
    }

    private static boolean isValidStringOrChar(Object userInput) {
        return (userInput instanceof String) && !((String) userInput).isEmpty()
                || (userInput instanceof Character) && (Character) userInput != ' ';
    }

    public static boolean isInt(Object input) {
        if (input == null) return false; 
        if (input instanceof String) return isIntFromString((String) input);
        return input instanceof Integer;
    }

    private static boolean isIntFromString(String userInput) {
        int length = userInput.length();
        if (length == 0) return false;

        int start = 0;
        if (userInput.charAt(0) == '-') {
            if (length == 1) return false;
            start = 1;
        }

        for (int i = start; i < length; i++) {
            char c = userInput.charAt(i);
            if (c < '0' || c > '9') return false;
            // Check for leading zeros
            if (c == '0' && start == i) return false;
        }

        try {
            // Check for integer overflow/underflow
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

