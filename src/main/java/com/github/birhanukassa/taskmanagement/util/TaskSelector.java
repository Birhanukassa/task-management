package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.models.Task;

import java.util.List;

public class TaskSelector {
    private TaskSelector() {
        // Private constructor to prevent instantiation
    }

    public static NamedTypedValue<Task> promptUserForTaskSelection(List<Task> tasks, String userInput) {
        System.out.println("Select a task by entering the task key, or enter 'E' to exit");

        Task selectedTask = null;
        try {
            if (userInput.equalsIgnoreCase("E")) return new NamedTypedValue<>("string", "ExitSelection", null);

            if (Validator.isInt(userInput)) {
                int taskIndex = Integer.parseInt(userInput) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) selectedTask = tasks.get(taskIndex);
            } else {
                System.out.println("Invalid task number. Please try again.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid task number or 'E' to exit.");
        } catch (Exception e) {
            System.err.println("Error getting user input: " + e.getMessage());
            return new NamedTypedValue<>("Task", "ErrorSelection", null);
        }

        if (selectedTask == null) {
            return new NamedTypedValue<>("Task", "ErrorSelection", null);
        }

        return new NamedTypedValue<>("Task", "SelectedTask", selectedTask);
    }
}
