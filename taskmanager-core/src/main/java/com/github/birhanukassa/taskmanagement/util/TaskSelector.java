package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.*;
import java.util.List;

public class TaskSelector {

    public <V> TypedNameValue<V> selectTask(List<Task> tasks) {

        while (true) {
            InputHandler handler = new InputHandler();
            TypedNameValue<?> input = handler.getUserInput(
                    "Enter the key of the task you want to manage, or 'E' to exit: ");

            if ("E".equalsIgnoreCase((String) input.getValue())) {
                System.out.println("Exiting task Manager.");
                return new TypedNameValue<>("string", "input", (V) "E");


            try {
                int selectedIndex = Integer.parseInt((String) input.getValue());

                if (selectedIndex < 0 || selectedIndex >= tasks.size()) {
                    System.out.println("Invalid key. Please enter a valid number.");
                    continue;
                }

                Task selectedTask = tasks.get(selectedIndex);
                return new TypedNameValue<V>("Task", "selectedTask", (V) selectedTask);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'E' to exit.");
            }
        }
    }
}
