package com.github.birhanukassa.taskmanagement.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:m");

    public static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, DATE_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidTime(String timeString) {
        try {
            LocalTime.parse(timeString, TIME_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}


// todo Add a private constructor to hide the implicit public one.sonarlint(java:S1118) 