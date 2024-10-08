// Package declaration
package com.github.birhanukassa.taskmanagement.models;

// Importing necessary classes 
import java.io.Serializable;
import java.util.Objects;

/**
 * An immutable class that represents a named value with an associated type.
 * This class is designed to handle dynamic input or data of varying types consistently.
 *
 * @param <T> the type of the value
 */

public final class NamedTypedValue<T> implements Serializable {

    private String type;
    private String name;
    private transient T value;

    /** 
     * Constructs a new instance of NamedTypedValue.
    
     * @param type  the type of the value, must not be null
     * @param name  the name associated with the value, must not be null
     * @param value the value, may be null
     * @throws NullPointerException if type or name is null
     */
    
    public NamedTypedValue(String type, String name, T value) {
        this.type = Objects.requireNonNull(type, "Type must not be null");
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T type) {
        this.value = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, value);
    }

    /**
     * Returns a string representation of the NamedTypedValue object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "NamedTypedValue{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
