// Package declaration
package com.github.birhanukassa.taskmanagement.util;

// Importing necessary classes 
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.models.Task;

import java.util.List;

// this class is responsible for prompting the user to select a task from a list of tasks and returning the selected task
/**
 * This class provides a method to prompt the user to select a task from a list of tasks.
 * It handles user input, validates the input, and returns the selected task or an appropriate error message.
 */
public class TaskSelector {
    
    private TaskSelector() {
        // Private constructor to prevent instantiation
    }

    // Method to prompt the user for task selection
    /**
     * Prompts the user to enter a task key or 'E' to exit. Validates the input and returns the selected task or an appropriate error message.
     *
     * @param tasks A list of tasks to choose from.
     * @return A NamedTypedValue object containing the selected task or an error message.
     */
    public static NamedTypedValue<Task> promptUserForTaskSelection(List<Task> tasks) {
        System.out.println("Select a task by entering the task key, or enter 'E' to exit");
        String userInput = ScannerWrapper.nextLine();

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
