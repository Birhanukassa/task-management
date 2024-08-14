package com.github.birhanukassa.taskmanagement.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.function.Supplier;

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

    public static boolean isValidDatePattern(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
            .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate.parse(userInput, formatter);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e);
                return false;
        }
    }

    public static boolean isValidTimePattern(String userInput) {
        return userInput.matches("^(0[0-6]|1\\d|2[0-3]):[0-5]\\d$");
    }

    public static boolean isValidDate(LocalDate userInput) {
        return userInput != null && !(userInput).isBefore(LocalDate.now());
    }

    public static boolean isValidTime(LocalTime time) {
        System.out.println(LocalDate.now().toString());
        return time != null && time.isAfter(LocalTime.now());
    }
    
    // static <T extends Comparable<T>> boolean isCurrentOrAfterCurrentDateTime(T input, Supplier<ZonedDateTime> currentDateTimeSupplier) {
    //     ZonedDateTime currentDateTime = currentDateTimeSupplier.get();

    //     if (input instanceof LocalDate inputDate) {
    //         return !inputDate.isBefore(currentDateTime.toLocalDate());
    //     } else if (input instanceof LocalTime inputTime) {
    //         return !inputTime.isBefore(currentDateTime.toLocalTime());
    //     } else {
    //         return false;
    //     }
    // }

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

