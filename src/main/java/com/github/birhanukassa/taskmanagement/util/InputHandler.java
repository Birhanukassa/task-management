package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InputHandler {
    private static final Map<Class<?>, Function<String, ?>> TYPE_CONVERTERS = new ConcurrentHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String EXIT_INPUT = "Q";

    // b/c at some point user will use the Exit. this object pre-made for optimization
    private static final NamedTypedValue<String> EXIT_VALUE = new NamedTypedValue<>("string", "EXIT_INPUT", EXIT_INPUT);

    static {
        registerTypeConverter(LocalDate.class, TimePeriod::parseLocalDate);
        registerTypeConverter(LocalTime.class, TimePeriod::parseLocalTime);
        registerTypeConverter(String.class, String::toString);
        registerTypeConverter(Integer.class, Integer::parseInt);
        registerTypeConverter(Double.class, Double::parseDouble);
    }

    private InputHandler() {
        // private constructor to prevent instantiation
    }

    public static <T> void registerTypeConverter(Class<T> type, Function<String, T> converter) {
        TYPE_CONVERTERS.put(type, converter);
    }

    public static <T> NamedTypedValue<T> getUserInput(String message, Class<T> targetClass) {
        while (true) {
            System.out.println(message);
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase(EXIT_INPUT)) return (NamedTypedValue<T>) EXIT_VALUE;

            if (userInput.trim().isEmpty() || !(targetClass instanceof Class<?>)) {
                System.out.println(
                        "The input or the target class is not valid. Please check your input and target classes");
                continue;
            }

            Function<String, ?> converter = TYPE_CONVERTERS.get(targetClass);
            if (converter == null) {
                System.out.println("No converter registered for type " + targetClass.getName()
                        + ". Please register a converter first.");
                return new NamedTypedValue<>(targetClass.getName(), "userInput", (T) userInput);
            }

            if (!isValidDateTimePattern(targetClass, userInput)) {
                System.out.println("Invalid Time/Date input pattern. Please try again.");
                continue;
            }
            
            T convertedValue = targetClass.cast(TYPE_CONVERTERS.get(targetClass).apply(userInput));
            if (isValidInput(convertedValue, targetClass)) {
                return new NamedTypedValue<>(targetClass.getName(), "convertedValue", convertedValue);
            } else {
                System.out.println("Invalid input. Please try again.");
            } 
        }
    }

    public static <T> boolean isValidDateTimePattern(Class<T> type, String input) {
        return ((type == LocalDate.class && Validator.isValidDatePattern(input)) || 
        (type == LocalTime.class && Validator.isValidTimePattern(input)));
    }

    public static <T> boolean isValidInput(T convertedValue, Class<T> type) {
        if (type == LocalDate.class) {
            LocalDate convertedDate = (LocalDate) convertedValue;
            if (!Validator.isValidDate(convertedDate)) {
                System.out.println("Input date is in the past. Please try again.");
                return false;
            }
        } else if (type == LocalTime.class) {
            LocalTime convertedTime = (LocalTime) convertedValue;
            if (!Validator.isValidTime(convertedTime)) {
                System.out.println("Input time is in the past. Please try again.");
                return false;
            }
        }
        return  Validator.test(convertedValue, type); 
    }  
}