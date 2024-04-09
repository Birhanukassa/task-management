package com.commands;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class GetInitializedVars {

    // Private constructor to prevent instantiation
    private GetInitializedVars() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated");
    }

    public static <T> List<NameValue> getInitializedVars(T instance) {
        List<NameValue> vars = new ArrayList<>();

        for (Field field : instance.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true); // Make the field accessible if it's private
                String name = field.getName();
                Object value = field.get(instance);

                NameValue nv = new NameValue(name, value);
                vars.add(nv);
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Handle the possible IllegalAccessException
            }
        }

        return vars;
    }
}

