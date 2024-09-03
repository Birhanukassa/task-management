// Package declaration
package com.github.birhanukassa.taskmanagement.util;
// Importing necessary classes 
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import java.lang.reflect.Field;
import java.util.*;

// Class declaration
/**
 * This class provides a utility method to retrieve the initialized fields and their values of a given object.
 * It recursively traverses the object's fields and collects the initialized fields and their values.
 */
public final class FieldValueMapper {

    private FieldValueMapper() {
        // Private constructor to prevent instantiation
    }

    // Method to retrieve initialized fields and their values
    /**
     * Returns a list of NamedTypedValue objects representing the initialized fields and their values of the given instance.
     *
     * @param instance The object for which initialized fields and their values need to be retrieved.
     * @param <T>      The type of the object.
     * @return A list of NamedTypedValue objects containing the field names, types, and values of the initialized fields.
     */
    public static <T> List<NamedTypedValue<Object>> getInitializedVars(T instance) {
        Set<Integer> visited = new HashSet<>();
        return getInitializedVarsRecursive(instance, visited);
    }

    // Recursive helper method to traverse the object's fields
    /**
     * Recursive helper method to traverse the object's fields and collect the initialized fields and their values.
     *
     * @param instance The object for which initialized fields and their values need to be retrieved.
     * @param visited  A set to keep track of visited objects to avoid infinite recursion.
     * @param <T>      The type of the object.
     * @return A list of NamedTypedValue objects containing the field names, types, and values of the initialized fields.
     */
    private static <T> List<NamedTypedValue<Object>> getInitializedVarsRecursive(T instance, Set<Integer> visited) {
        if (instance == null) return Collections.emptyList();

        // making sure if the instance has already been visited
        int instanceIdentityHashCode = System.identityHashCode(instance);
        if (!visited.add(instanceIdentityHashCode)) return Collections.emptyList();
        
        List<NamedTypedValue<Object>> vars = new ArrayList<>();
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            processField(instance, field, vars, visited);
        }
        return vars;
    }

    // Helper method to process each field
    /**
     * Helper method to process each field of the object and add the initialized fields and their values to the list.
     *
     * @param instance       The object for which initialized fields and their values need to be processed.
     * @param field          The field to be processed.
     * @param initializedVars The list to which the initialized fields and their values will be added.
     * @param visited        A set to keep track of visited objects to avoid infinite recursion.
     * @param <T>            The type of the object.
     */
    private static <T> void processField(T instance, Field field, List<NamedTypedValue<Object>> initializedVars,
            Set<Integer> visited) {
        try {
            field.setAccessible(true);
            Object value = field.get(instance);

            if (isFieldInitialized(value)) {
                if (!isCustomObject(value)) {
                    initializedVars.add(new NamedTypedValue<>(value.getClass().getName(), field.getName(), value));
                } else {
                    initializedVars.addAll(getInitializedVarsRecursive(value, visited));
                }
            }
        } catch (IllegalAccessException e) {
            handleException(e);
        }
    }

    // Helper method to check if a field is initialized
    /**
     * Helper method to check if a field is initialized based on its value.
     *
     * @param value The value of the field to be checked.
     * @return true if the field is initialized, false otherwise.
     */
    public static boolean isFieldInitialized(Object value) {
        return !((value == null) || (value instanceof Boolean && !((Boolean) value))
                || (value instanceof Character && (Character) value == '\u0000')
                || (value instanceof Number && ((Number) value).doubleValue() == 0)
                || (value instanceof String && ((String) value).isEmpty()));
    }

    // Helper method to check if an object is a custom object
    /**
     * Helper method to check if an object is a custom object based on its type.
     *
     * @param obj The object to be checked.
     * @return true if the object is a custom object, false otherwise.
     */
    static boolean isCustomObject(Object obj) {
        return obj instanceof com.github.birhanukassa.taskmanagement.util.TimePeriod;
    }

    // Helper method to handle exceptions
    /**
     * Helper method to handle exceptions that may occur while accessing fields.
     *
     * @param e The exception to be handled.
     */
    private static void handleException(Exception e) {
        Throwable cause = e.getCause();
        if (cause instanceof NullPointerException) {
            System.out.println("Null pointer exception occurred while invoking the method.");
        } else if (cause instanceof IndexOutOfBoundsException) {
            System.out.println("Index out of bounds exception occurred while invoking the method.");
        } else {
            System.out.println("An exception occurred while invoking the method: " + cause.getMessage());
        }
    }
}
