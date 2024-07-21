package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import java.lang.reflect.Field;
import java.util.*;

public final class FieldValueMapper {

    private FieldValueMapper() {
        // Private constructor to prevent instantiation
    }

    public static <T> List<NamedTypedValue<Object>> getInitializedVars(T instance) {
        Set<Integer> visited = new HashSet<>();
        return getInitializedVarsRecursive(instance, visited);
    }

    private static <T> List<NamedTypedValue<Object>> getInitializedVarsRecursive(T instance, Set<Integer> visited) {
        if (instance == null) return Collections.emptyList();

        // making sure if the instance has already been visited
        int instanceIdentityHashCode = System.identityHashCode(instance);
        if (!visited.add(instanceIdentityHashCode))  return Collections.emptyList();
        
        List<NamedTypedValue<Object>> vars = new ArrayList<>();
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            processField(instance, field, vars, visited);
        }
        return vars;
    }

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

    public static boolean isFieldInitialized(Object value) {
        return !((value == null) || (value instanceof Boolean && !((Boolean) value))
                || (value instanceof Character && (Character) value == '\u0000')
                || (value instanceof Number && ((Number) value).doubleValue() == 0)
                || (value instanceof String && ((String) value).isEmpty()));
    }

    private static boolean isCustomObject(Object obj) {
        return obj instanceof com.github.birhanukassa.taskmanagement.util.TimePeriod;
    }

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
