package com.github.birhanukassa.taskmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class ItemList<T> {
    private static ItemList<?> instance;
    private final List<T> itemsList;

    private ItemList() {
        itemsList = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> ItemList<T> getInstance() {
        if (instance == null) {
            synchronized (ItemList.class) {
                if (instance == null) {
                    instance = new ItemList<>();
                }
            }
        }
        return (ItemList<T>) instance;
    }
      
      
    public List<T> getTasks() {
        return itemsList;
    } 
}