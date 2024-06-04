package com.github.birhanukassa.taskmanagement.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeValidator {

    private DateTimeValidator() {
        // private constructor to prevent instantiation
    }

    // private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("mm/dd/yyyy");
    // private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    // public static boolean isValidDate(String dateString) {
    //     try {
    //         LocalDate.parse(dateString, DATE_FORMAT);
    //         return true;
    //     } catch (DateTimeParseException e) {
    //         return false;
    //     }
    // }

    // public static boolean isValidTime(String timeString) {
    //     try {
    //         LocalTime.parse(timeString, TIME_FORMAT);
    //         return true;
    //     } catch (DateTimeParseException e) {
    //         return false;
    //     }
    // }


    public static boolean isValidDate(LocalDate date) {
        // You can add your validation logic here
        // For example, you can check if the date is in the past or future
        return true; // or false, depending on your validation logic
    }

    public static boolean isValidTime(LocalTime time) {
        // You can add your validation logic here
        // For example, you can check if the time is within a specific range
        return true; // or false, depending on your validation logic
    }

}


