package com.github.birhanukassa.taskmanagement.models;

public class GenericSingleton {
    private GenericSingleton() {}

    private static class SingletonHolder {
        private static final GenericSingleton INSTANCE = new GenericSingleton();
    }

    public static GenericSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // Your methods here
}


