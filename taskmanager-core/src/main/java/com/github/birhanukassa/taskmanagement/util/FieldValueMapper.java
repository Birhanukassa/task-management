package com.github.birhanukassa.taskmanagement.util;
import java.util.List;

import com.github.birhanukassa.taskmanagement.models.TypedNameValue;

import java.util.ArrayList;
import java.lang.reflect.Field;

public class FieldValueMapper {

    // Private constructor to prevent instantiation
    private FieldValueMapper() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated");
    }

    public static <T> List<TypedNameValue> getInitializedVars(T instance) {
        List<TypedNameValue> vars = new ArrayList<>();

        for (Field field : instance.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true); // Make the field accessible if it's private
                String name = field.getName();
                Object value = field.get(instance);

                TypedNameValue nv = new TypedNameValue(name, value);
                vars.add(nv);
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Handle the possible IllegalAccessException
            }
        }

        return vars;
    }
}

