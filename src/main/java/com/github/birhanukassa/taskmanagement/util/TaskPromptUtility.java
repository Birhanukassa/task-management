package com.github.birhanukassa.taskmanagement.util;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskPromptUtility {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ISO_TIME;

    public enum IntervalOption {
        YES,
        NO
    }

    public static LocalDate promptForDate(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        try {
            return LocalDate.parse(input, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return promptForDate(scanner, prompt);
        }
    }

    public static LocalTime promptForTime(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        try {
            return LocalTime.parse(input, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please try again.");
            return promptForTime(scanner, prompt);
        }
    }

    public static LocalDate promptForInterval(Scanner scanner, String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine().toUpperCase();
        try {
            return LocalDate.parse(input, DATE_FORMAT);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid day formate. Please try again.");
            return promptForInterval(scanner, prompt);
        }
    }
}


