package com.github.birhanukassa.taskmanagement.commands;

public class NameValue {
    private String name;
    private Object value;

    public NameValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
