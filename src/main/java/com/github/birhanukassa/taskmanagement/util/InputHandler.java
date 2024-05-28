package com.github.birhanukassa.taskmanagement.util;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Logger;

import com.github.birhanukassa.taskmanagement.commands.PriorityQueueCommand;
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;

public class InputHandler {
    private static final Logger LOGGER = Logger.getLogger(PriorityQueueCommand.class.getName());
        
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

    public int getValidatedNumberInput(String prompt, int num) throws Exception {
        InputHandler inputHandler = new InputHandler();
        NamedTypedValue<Integer> userInput;
        int value;
        do {
            userInput = inputHandler.getUserInput(prompt, Integer.class);
            value = userInput.getValue();
            if (value > 0) {
                LOGGER.warning(() -> String.format("Invalid input. Please enter a value between %d and %d.", value));
            }
        } while (value > 0);
        return value;
    }

}





