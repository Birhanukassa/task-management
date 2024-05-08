package com.github.birhanukassa.taskmanagement.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import com.github.birhanukassa.taskmanagement.models.TypedNameValue;

public class InputHandler {
    private static final Map<String, Function<String, ?>> TYPE_CONVERTERS = new HashMap<>();

    static {
        TYPE_CONVERTERS.put("string", Function.identity());
        TYPE_CONVERTERS.put("integer", Integer::valueOf);
        TYPE_CONVERTERS.put("double", Double::valueOf);
        TYPE_CONVERTERS.put("boolean", Boolean::valueOf);
    }

    public <T> TypedNameValue<T> getUserInput(String message, String type, Class<T> targetClass) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(message);
            String inputValue = scanner.nextLine();

            if (inputValue.equalsIgnoreCase("E")) {
                return new TypedNameValue<>("string", "input", (T) "E");
            }

            Function<String, ?> converter = TYPE_CONVERTERS.get(type.toLowerCase());
            if (converter == null) {
                System.out.println("Invalid type. Please enter a valid type (string, integer, double, boolean) or 'E' to exit.");
                return null;
            }

            T convertedValue = targetClass.cast(converter.apply(inputValue));
            return new TypedNameValue<>(type, "input", convertedValue);
        } catch (Exception e) {
            System.out.println("Invalid input for type " + type + ". Please try again.");
            return null;
        }
    }
}



// public class InputHandler {
//     private Scanner scanner;

//     public InputHandler() {
//         scanner = new Scanner(System.in);
//     }

//     public TypedNameValue<String> getUserInput(String inputType, String message) {
//         System.out.println(message);
//         String inputValue = scanner.nextLine();

//         if (inputValue.equalsIgnoreCase("E")) {
//             return new TypedNameValue<String>("string", "input", "E");
//         }

//         return createTypedNameValue(inputType, inputValue);
//     }

//     private <T> TypedNameValue<T> createTypedNameValue(String type, String value) {
//         switch (type.toLowerCase()) {
//             case "string":
//                 return new TypedNameValue<>("string", "input", (T) value);
//             case "integer":
//                 return new TypedNameValue<>("Integer", "input", (T) Integer.valueOf(value));
//             case "double":
//                 return new TypedNameValue<>("Double", "input", (T) Double.valueOf(value));
//             case "boolean":
//                 return new TypedNameValue<>("Boolean", "input", (T) Boolean.valueOf(value));
//             default:
//                 // Handle invalid type
//                 System.out.println("Invalid type. Please enter a valid type (string, integer, double, boolean) or 'E' to exit.");
//                 return null;
//         }
//     }

}

// problem: input handler should be able to handle multiple input for different classes 
// 
