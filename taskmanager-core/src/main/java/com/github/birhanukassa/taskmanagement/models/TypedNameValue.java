package com.github.birhanukassa.taskmanagement.models;

import java.util.Objects;

public final class TypedNameValue<V> {
    private final String type;
    private final String name;
    private final V value;

    public TypedNameValue(String type, String name, V value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TypedNameValue<?> that = (TypedNameValue<?>) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, value);
    }

    @Override
    public String toString() {
        return "TypedNameValue{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
