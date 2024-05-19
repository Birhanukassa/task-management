package com.github.birhanukassa.taskmanagement.util;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;

public class InputHandler {
    private static final Map<Class<?>, Function<String, ?>> TYPE_CONVERTERS = new ConcurrentHashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String EXIT_INPUT = "E";

    public static <T> void registerTypeConverter(Class<T> type, Function<String, T> converter) {
        TYPE_CONVERTERS.put(type, converter);
    }

    public <T> NamedTypedValue<T> getUserInput(String message, Class<T> targetClass) throws Exception {
        System.out.println(message);
        String inputValue = SCANNER.nextLine();

        if (inputValue.equalsIgnoreCase(EXIT_INPUT)) {
            if (targetClass.isAssignableFrom(String.class)) {
                return new NamedTypedValue<>("string", "input", (T) EXIT_INPUT);
            } else {
                throw new ClassCastException("Cannot cast E to " + targetClass.getName());
            }
        }

        Function<String, ?> converter = TYPE_CONVERTERS.get(targetClass);
        if (converter == null) {
            throw new Exception("No converter registered for type " + targetClass.getName() + ". Please register a converter first.");
        }

        try {
            T convertedValue = targetClass.cast(converter.apply(inputValue));
            return new NamedTypedValue<>(targetClass.getName(), "input", convertedValue);
        } catch (ClassCastException e) {
            throw new Exception("Invalid input for type " + targetClass.getName() + ". Please try again.");
        }
    }
}



/* 
public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public NamedTypedValue<String> getUserInput(String inputType, String message) {
        System.out.println(message);
        String inputValue = scanner.nextLine();

        if (inputValue.equalsIgnoreCase("E")) {
            return new NamedTypedValue<String>("string", "input", "E");
        }

        return createTypedNameValue(inputType, inputValue);
    }

    private <T> NamedTypedValue<T> createTypedNameValue(String type, String value) {
        switch (type.toLowerCase()) {
            case "string":
                return new NamedTypedValue<>("string", "input", (T) value);
            case "integer":
                return new NamedTypedValue<>("Integer", "input", (T) Integer.valueOf(value));
            case "double":
                return new NamedTypedValue<>("Double", "input", (T) Double.valueOf(value));
            case "boolean":
                return new NamedTypedValue<>("Boolean", "input", (T) Boolean.valueOf(value));
            default:
                // Handle invalid type
                System.out.println("Invalid type. Please enter a valid type (string, integer, double, boolean) or 'E' to exit.");
                return null;
        }
    }
}
*/



