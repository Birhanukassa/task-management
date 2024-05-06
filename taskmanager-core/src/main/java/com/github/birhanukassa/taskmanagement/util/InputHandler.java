package com.github.birhanukassa.taskmanagement.util;

import java.util.Scanner;
import com.github.birhanukassa.taskmanagement.models.TypedNameValue;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public TypedNameValue<String, Object> getUserInput(String message) {
        while (true) {
            try {
                System.out.println(message);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("E")) {
                    scanner.close();
                    return new TypedNameValue<>("string", "input", "E");
                }
                scanner.close();
                return new TypedNameValue<>("integer", "input", Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid input or 'E' to exit.");
            }
        }
    }
}
