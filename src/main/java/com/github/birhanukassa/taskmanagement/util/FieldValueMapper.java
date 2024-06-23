package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FieldValueMapper {
    // Private constructor to prevent instantiation
    private FieldValueMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static <T> List<NamedTypedValue<Object>> getInitializedVars(T instance) {
    List<NamedTypedValue<Object>> vars = new ArrayList<>();
    Method[] methods = instance.getClass().getMethods();

    for (Method method : methods) {
        String methodName = method.getName();
        if (isGetter(methodName)) {
            String fieldName = getFieldNameFromGetter(methodName);
            try {
                Object value = method.invoke(instance);
                if (value != null) {
                    vars.add(new NamedTypedValue<>(value.getClass().getSimpleName(), fieldName, value));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                // Handle the exception or log it appropriately
                e.printStackTrace();
            }
        }
    }
    return vars;
    }

    private static boolean isGetter(String methodName) {
        return (methodName.startsWith("get") && methodName.length() > 3) ||
            (methodName.startsWith("is") && methodName.length() > 2);
    }

    private static String getFieldNameFromGetter(String getterName) {
        if (getterName.startsWith("get")) {
            return getterName.substring(3, 4).toLowerCase() + getterName.substring(4);
        } else {
            return getterName.substring(2, 3).toLowerCase() + getterName.substring(3);
        }
    }
    
}
