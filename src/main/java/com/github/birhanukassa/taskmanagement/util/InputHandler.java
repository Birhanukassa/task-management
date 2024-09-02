package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// a utility class for handling user input and converting it to the desired type.
public class InputHandler {
    private static final Map<Class<?>, Function<String, ?>> TYPE_CONVERTERS = new HashMap<>();
    private static final String EXIT_INPUT_PROMPT = "Q";
    private static final NamedTypedValue<String> EXIT_VALUE = new NamedTypedValue<>("string", "EXIT_INPUT_PROMPT", EXIT_INPUT_PROMPT);

    static {
        registerTypeConverter(String.class, String::toString);
        registerTypeConverter(Integer.class, Integer::parseInt);
        registerTypeConverter(Double.class, Double::parseDouble);
        registerTypeConverter(LocalDate.class, TimePeriod::parseLocalDate);
        registerTypeConverter(LocalTime.class, TimePeriod::parseLocalTime);
    }

    // Registers a type converter for the specified class. 
    public static synchronized <T> void registerTypeConverter(Class<T> type, Function<String, T> converter) {
        TYPE_CONVERTERS.put(type, converter);
    }

    // Checks if the input string matches the expected date or time pattern for the specified class. 
    public static <T> boolean isValidDateTimePattern(Class<T> type, String input) {
        return (
            (type == LocalDate.class && Validator.isValidDatePattern(input)) 
            || (type == LocalTime.class && Validator.isValidTimePattern(input)));
    }

    // Validates the converted value based on the specified class. 
    public static <T> boolean isValidInput(T convertedValue, Class<T> type) {
        if (type == LocalDate.class) {
            LocalDate convertedDate = (LocalDate) convertedValue;
            if (!Validator.isValidDate(convertedDate)) {
                System.out.println("Input date is in the past. Please try again.");
                return false;
            }
            return true;
        } else if (type == LocalTime.class) {
            LocalTime convertedTime = (LocalTime) convertedValue;
            if (!Validator.isValidTime(convertedTime)) {
                System.out.println("Input time is in the past. Please try again.");
                return false;
            }
            return true;
        }
        return Validator.test(convertedValue, type); 
    }  

    // Coverts the user input to the specified target class. 
    static <T> T convertInput(String userInput, Class<T> targetClass) {
        if (userInput == null || userInput.isEmpty()) {
            promptForInput("User input can't be null or empty.");
            return null;
        }

        Function<String, ?> converter = TYPE_CONVERTERS.get(targetClass);
        if (converter == null) {
            promptForInput("No converter registered for type " + targetClass.getName() + ". Please register a converter first.");
            return null;
        }

        if ((targetClass.equals(LocalDate.class) || targetClass.equals(LocalTime.class)) 
            && !isValidDateTimePattern(targetClass, userInput)) {
                return null;
            }
        

        return targetClass.cast(TYPE_CONVERTERS.get(targetClass).apply(userInput));
    }

    private static String promptForInput(String prompt) {
        System.out.println(prompt);
        return  ScannerWrapper.nextLine();
    }

    // Prompts the user for input and converts it to the specified  target class. 
    @SuppressWarnings("unchecked")
    public static <T> NamedTypedValue<T> getUserInput(String message, Class<T> targetClass) {
        while (true) {
            String userInput = promptForInput(message.trim());
            
            if (userInput.equalsIgnoreCase(EXIT_INPUT_PROMPT)) return (NamedTypedValue<T>) EXIT_VALUE;

            T convertedValue = convertInput(userInput, targetClass);
            if (isValidInput(convertedValue, targetClass)) {
                return new NamedTypedValue<>(targetClass.getName(), "convertedValue", convertedValue);
            }
        }  
    }
}

