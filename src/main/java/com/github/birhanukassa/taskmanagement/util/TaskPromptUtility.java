package com.github.birhanukassa.taskmanagement.util;

import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskPromptUtility {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ISO_TIME;
    private static final Logger logger = Logger.getLogger(TaskPromptUtility.class.getName());

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
            logger.info("Invalid date format. Please try again.");
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

    public static int promptForInterval(Scanner scanner, String prompt) {
    System.out.println(prompt);
    int input;
    try {
        input = scanner.nextInt();
        return input;
    } catch (InputMismatchException e) {
        logger.info("Invalid input format. Please try again.");
        // Clear the invalid input from the scanner
        scanner.nextLine(); 
        return promptForInterval(scanner, prompt);
    }
}

}


