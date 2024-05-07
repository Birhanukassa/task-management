package com.github.birhanukassa.taskmanagement.util;

import java.util.Scanner;
import com.github.birhanukassa.taskmanagement.models.TypedNameValue;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public TypedNameValue<?> getUserInput(String message) {
        System.out.println(message);
        String inputValue = scanner.nextLine();

        System.out.println(
                "Enter the type of input you want to provide (string, integer, double, boolean) or 'E' to exit: ");
        String inputType = scanner.nextLine();

        if (inputValue.equalsIgnoreCase("E") || inputType.equalsIgnoreCase("E")) {
            return new TypedNameValue<String>("string", "input", "E");
        }

        return createTypedNameValue(inputType, inputValue);
    }

    private <V> TypedNameValue<V> createTypedNameValue(String type, String value) {
        switch (type.toLowerCase()) {
            case "string":
                return new TypedNameValue<>("string", "input", (V) value);
            case "integer":
                return new TypedNameValue<>("Integer", "input", (V) Integer.valueOf(value));
            case "double":
                return new TypedNameValue<>("Double", "input", (V) Double.valueOf(value));
            default:
                // Handle invalid type
                System.out.println(
                        "Invalid type. Please enter a valid type (string, integer, double, boolean) or 'E' to exit.");
                return null;
        }
    }
}
