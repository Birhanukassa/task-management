// Package declaration
package com.github.birhanukassa.taskmanagement.util;

// Importing necessary classes 
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

// Class definition
/**
 * The Validator class provides methods to validate user input based on the target class.
 * It includes methods to check if a string or character is valid, if an integer is valid,
 * if a date or time is valid, and if a date or time is in the future.
 */
public class Validator {

    private Validator() {
        // Private constructor to prevent instantiation of this class
    }

    /**
     * This method checks if the user input is valid based on the target class.
     * It uses a series of if-else statements to check the type of the target class
     * and calls the appropriate helper method to validate the input.
     * If the input is valid, it returns true;
     * @param userInput
     * @param targetClass
     * @return boolean
     * @see Validator#isValidStringOrChar(Object)
     * @see Validator#isInt(Object)
     * @see Validator#isValidDate(LocalDate)
     * @see Validator#isValidTime(LocalTime)
     * @see Validator#isValidDatePattern(String)
     * @see Validator#isValidTimePattern(String)
     * @see Validator#isIntFromString(String)
     */

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

    /**
     * date formatted string input validator
     * @see DateTimeFormatter
     * @see DateTimeParseException
     * @param userInput
     * @return boolean
     */
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
    
    /**
     * This method checks if the date is valid and not in the future.
     * It compares the date with the current date using the isBefore method.
     * If the date is valid and not in the future, it returns true;
     * @param userInput
     * @return boolean
     */
    public static boolean isValidDate(LocalDate userInput) {
        return userInput != null && !(userInput).isBefore(LocalDate.now());
    }

    /**
     * This method checks if the time is valid and not in the future.
     * It compares the time with the current time using the isBefore method.
     * If the time is valid and not in the future, it returns true;
     * @param time
     * @return boolean
     */
    public static boolean isValidTime(LocalTime time) {
        return time != null && !(time).isBefore(LocalTime.now());
    }

    /**
     * This method checks if the string or character is valid.
     * It checks if the input is an instance of String and is not empty,
     * or if the input is an instance of Character and is not a space character.
     * If the input is valid, it returns true;
     * @param userInput
     * @return boolean
     */
    private static boolean isValidStringOrChar(Object userInput) {
        return (userInput instanceof String) && !((String) userInput).isEmpty()
                || (userInput instanceof Character) && (Character) userInput != ' ';
    }

    /**
     * This method checks if the integer is valid.
     * It checks if the input is an instance of String and calls the isIntFromString method,
     * or if the input is an instance of Integer.
     * If the input is valid, it returns true;
     * @param input
     * @return boolean
     */
    public static boolean isInt(Object input) {
        if (input == null) return false; 
        if (input instanceof String) return isIntFromString((String) input);
        return input instanceof Integer;
    }

    /**
     * This method checks if the string can be parsed as an integer.
     * It first checks if the string is empty or null, if so, it returns false.
     * Then, it iterates over each character in the string and checks if it is a digit.
     * If any character is not a digit, it returns false.
     * Finally, it tries to parse the string as an integer using Integer.parseInt.
     * If the parsing is successful, it returns true; otherwise, it returns false.
     * @param userInput
     * @return boolean
     */
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

