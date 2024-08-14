package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InputHandler {
    private static final Map<Class<?>, Function<String, ?>> TYPE_CONVERTERS = new ConcurrentHashMap<>();
    private static final String EXIT_INPUT = "Q";
    private static final NamedTypedValue<String> EXIT_VALUE = new NamedTypedValue<>("string", "EXIT_INPUT", EXIT_INPUT);

    static {
        registerTypeConverter(String.class, String::toString);
        registerTypeConverter(Integer.class, Integer::parseInt);
        registerTypeConverter(Double.class, Double::parseDouble);
        registerTypeConverter(LocalDate.class, TimePeriod::parseLocalDate);
        registerTypeConverter(LocalTime.class, TimePeriod::parseLocalTime);
    }

    public static <T> void registerTypeConverter(Class type, Function<String, T> converter) {
        TYPE_CONVERTERS.put(type, converter);
    }

    public static <T> boolean isValidDateTimePattern(Class<T> type, String input) {
        return (
            (type == LocalDate.class && Validator.isValidDatePattern(input)) 
            || (type == LocalTime.class && Validator.isValidTimePattern(input)));
    }

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

    static <T> T convertInput(String userInput, Class<T> targetClass) {
        if (userInput == null || userInput.isEmpty()) {
            promptForInput("User input is null. Please try again.");
            return null;
        }

        Function<String, ?> converter = TYPE_CONVERTERS.get(targetClass);
        if (converter == null) {
            promptForInput("No converter registered for type " + targetClass.getName() + ". Please register a converter first.");
            return null;
        }

        if (targetClass.equals(LocalDate.class) || targetClass.equals(LocalTime.class)) {
            if (!isValidDateTimePattern(targetClass, userInput)) {
                return null;
            }
        }

        return targetClass.cast(TYPE_CONVERTERS.get(targetClass).apply(userInput));
    }

    private static String promptForInput(String prompt) {
        System.out.println(prompt);
        return  ScannerWrapper.nextLine();
    }

    public static <T> NamedTypedValue<T> getUserInput(String message, Class<T> targetClass) {
        while (true) {
            String userInput = promptForInput(message.trim());
            
            if (userInput.equalsIgnoreCase(EXIT_INPUT)) return (NamedTypedValue<T>) EXIT_VALUE;

            T convertedValue = convertInput(userInput, targetClass);
            if (isValidInput(convertedValue, targetClass)) {
                return new NamedTypedValue<>(targetClass.getName(), "convertedValue", convertedValue);
            }
        }  
    }
}

