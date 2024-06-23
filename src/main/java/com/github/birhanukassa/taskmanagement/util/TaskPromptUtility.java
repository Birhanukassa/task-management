package com.github.birhanukassa.taskmanagement.util;

import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskPromptUtility {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ISO_TIME;
    private static final Logger logger = Logger.getLogger(TaskPromptUtility.class.getName());
    
    public static LocalDate promptForDate(Scanner scanner, String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return null;
            }
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                logger.info("Invalid date format. Please enter a date in the format dd/MM/yyyy or type 'x' to cancel.");
            }
        }
    }

    public static LocalTime promptForTime(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return null;
            }
            try {
                return LocalTime.parse(input, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                logger.info("Invalid time format. Please try again.");
            }
        }
    }

    public static int promptForInterval(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return -1;
            }
            try {
                return Integer.parseInt(input);
            } catch (InputMismatchException e) {
                logger.info("Invalid input format. Please try again.");
            }
        }
    }
}


