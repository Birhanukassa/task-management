package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldValueMapper {

    // Private constructor to prevent instantiation
    private FieldValueMapper() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated");
    }

    public static <T> List<NamedTypedValue<?>> getInitializedVars(T instance) {
        List<NamedTypedValue<?>> vars = new ArrayList<>();
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
                continue; // Skip static and transient fields
            }

            field.setAccessible(true);

            try {
                Object value = field.get(instance);
                if (value == null) {
                    continue; // Skip null values
                }

                Class<?> fieldType = field.getType();
                String nameOfType = fieldType.getSimpleName();
                String name = field.getName();

                vars.add(new NamedTypedValue<>(nameOfType, name, value));
            } catch (IllegalAccessException e) {
                // Handle the exception or log it appropriately
                e.printStackTrace();
            }
        }

        return vars;
    }
}



/*public static <T> List<NamedTypedValue<?>> getInitializedVars(T instance) {
    return Arrays.stream(instance.getClass().getDeclaredFields())
            .filter(field -> !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
            .peek(field -> field.setAccessible(true))
            .map(field -> createNamedTypedValue(instance, field))
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
}

private static <T> Optional<NamedTypedValue<?>> createNamedTypedValue(T instance, Field field) {
    try {
        Object value = field.get(instance);
        if (value == null) {
            return Optional.empty();
        }

        Class<?> fieldType = field.getType();
        String nameOfType = fieldType.getSimpleName();
        String name = field.getName();

        return Optional.of(new NamedTypedValue<>(nameOfType, name, value));
    } catch (IllegalAccessException e) {
        // Handle the exception or log it appropriately
        e.printStackTrace();
        return Optional.empty();
    }
}
 */