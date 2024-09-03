// Package declaration
package com.github.birhanukassa.taskmanagement.util;
// Importing necessary classes 
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// Class to handle user input
/**
 * This class provides methods to handle user input and perform type conversions.
 * It includes methods to register type converters, validate input patterns,
 * convert user input to the specified target class, and check if the input is valid.
 */
public class InputHandler {
    private static final Map<Class<?>, Function<String, ?>> TYPE_CONVERTERS = new HashMap<>();
    private static final String EXIT_INPUT_PROMPT = "Q";
    private static final NamedTypedValue<String> EXIT_VALUE = new NamedTypedValue<>("string", "EXIT_INPUT_PROMPT", EXIT_INPUT_PROMPT);

    // Static initializer block to register default type converters
    static {
        registerTypeConverter(String.class, String::toString);
        registerTypeConverter(Integer.class, Integer::parseInt);
        registerTypeConverter(Double.class, Double::parseDouble);
        registerTypeConverter(LocalDate.class, TimePeriod::parseLocalDate);
        registerTypeConverter(LocalTime.class, TimePeriod::parseLocalTime);
    }

    /**
     *  Registers a type converter for the specified class.
     *  The converter function takes a string input and returns an instance of the specified class.
     *  The registered converter can be used to convert user input to the specified target class.
     *  @see Function
     *  @see #convertInput(String, Class)
     *  @see #promptForInput(String)
     *  @see #isValidDateTimePattern(Class, String)
     *  @see #isValidInput(Object, Class)
     *  @see #registerTypeConverter(Class, Function)
     *  @see #promptForInput(String)
     *  @see #promptForInput(String)
     * @param <T>
     * @param type
     * @param converter
     */
    public static synchronized <T> void registerTypeConverter(Class<T> type, Function<String, T> converter) {
        TYPE_CONVERTERS.put(type, converter);
    }

    /**
     * Checks if the input string matches the expected date or time pattern for the specified class.
     * @see Validator#isValidDatePattern(String)
     * @see Validator#isValidTimePattern(String)
     * @see LocalDate
     * @see LocalTime
     * @see Class
     * @see String
     * @param <T>
     * @param type
     * @param input
     * @return boolean
     * @throws IllegalArgumentException if the specified class is not supported.
     * @throws NullPointerException if the input string is null or empty.
     * @throws IllegalArgumentException if the input string is not a valid date or time pattern.
     */
    public static <T> boolean isValidDateTimePattern(Class<T> type, String input) {
        return (
            (type == LocalDate.class && Validator.isValidDatePattern(input)) 
            || (type == LocalTime.class && Validator.isValidTimePattern(input)));
    }

    /**
     * Checks if the input value is valid based on the specified class type.
     * @see Validator#test(Object, Class)
     * @see Validator#isValidDate(LocalDate)
     * @see Validator#isValidTime(LocalTime)
     * @see LocalDate
     * @see LocalTime
     * @see Class
     * @see Object
     * @param <T>
     * @param convertedValue
     * @param type
     * @return
     */
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

    /**
     * Converts the user input string to the specified target class using the registered type converter.
     * @see #registerTypeConverter(Class, Function)
     * @see #isValidDateTimePattern(Class, String)
     * @see #promptForInput(String)
     * @see Function
     * @see Class
     * @see String
     * @param <T>
     * @param userInput
     * @param targetClass
     * @return
     */ 
    static <T> T convertInput(String userInput, Class<T> targetClass) {
        if (userInput == null || userInput.isEmpty()) {
            promptForInput("User input can't be null or empty.");
            return null;
        }
        
        // try to get the converter 
        Function<String, ?> converter = TYPE_CONVERTERS.get(targetClass);
    
        if (converter == null) {
            promptForInput("No converter registered for type " + targetClass.getName() + ". Please register a converter first.");
            return null;
        }

        if ((targetClass.equals(LocalDate.class) || targetClass.equals(LocalTime.class)) 
            && !isValidDateTimePattern(targetClass, userInput)) {
                return null;
            }
        
        // cast the converted value to the target class
        return targetClass.cast(TYPE_CONVERTERS.get(targetClass).apply(userInput));
    }

    /**
     * Prompts the user for input and returns the input as a string.
     * @see ScannerWrapper
     * @see String
     * @see System
     * @see #promptForInput(String)
     * @see #promptForInput(String)
     * @see #promptForInput(String)
     * @param prompt
     * @return String
     * @throws NullPointerException if the prompt is null or empty.
     * @throws IllegalArgumentException if the prompt is not a string.
     */
    private static String promptForInput(String prompt) {
        System.out.println(prompt);
        return  ScannerWrapper.nextLine();
    }

    /**
     * Gets user input from the console and converts it to the specified target class.
     * @see #getUserInput(String, Class)
     * @see #convertInput(String, Class)
     * @see #isValidInput(Object, Class)
     * @see #promptForInput(String)
     * @see #promptForInput(String)
     * @see #promptForInput(String)
     * @param <T>
     * @param message
     * @param targetClass
     * @return NamedTypedValue<T>
     * @throws IllegalArgumentException if the message is null or empty.
     * @throws IllegalArgumentException if the targetClass is null.
     * @throws IllegalArgumentException if the targetClass is not supported.
     */
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

