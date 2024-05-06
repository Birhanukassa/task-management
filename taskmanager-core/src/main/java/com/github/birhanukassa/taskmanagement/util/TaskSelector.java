package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.*;
import java.util.List;

public class TaskSelector {

    public TypedNameValue<String, Object> selectTask(List<Task> tasks) {

        while (true) {
            InputHandler handler = new InputHandler();
            TypedNameValue<String, Object> input = handler.getUserInput(
                    "Enter the key of the task you want to manage, or 'E' to exit: ");
            Object value = input.getValue();

            if ("E".equalsIgnoreCase((String) value)) {
                System.out.println("Exiting task Manager.");
                return new TypedNameValue<>("Exit", "input", "E");
            }

            try {
                int selectedTaskIndex = Integer.parseInt((String) value);

                if (selectedTaskIndex < 0 || selectedTaskIndex >= tasks.size()) {
                    System.out.println("Invalid key. Please enter a valid number.");
                    continue;
                }

                Task selectedTask = tasks.get(selectedTaskIndex);
                return new TypedNameValue<>("Task", "selectedTask", selectedTask);
            } catch (NumberFormatException e) {
                // If input is not a number, return it as a String
                System.out.println("Invalid input. Please enter a valid number or 'E' to exit.");
            }
        }
    }
}
