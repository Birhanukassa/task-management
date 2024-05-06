package com.github.birhanukassa.taskmanagement.models;

public class TypedNameValue<T, V> {
    private T type;
    private String name;
    private V value;

    public TypedNameValue(T type, String name, V value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public void setType(T type) {
        this.type = type;
    }

    public T getType() {
        return type;
    }

    public void setName(N name) {
        this.name = name;
    }

    public N getName() {
        return name;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
