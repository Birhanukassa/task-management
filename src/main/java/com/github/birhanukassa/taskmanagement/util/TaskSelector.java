package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.*;
import java.util.List;

public class TaskSelector {
    private InputHandler inputHandler;

    public TaskSelector(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public NamedTypedValue<Task> promptUserForTaskSelection(List<Task> tasks) {
        System.out.println("Select a task by entering the task key, or enter 'E' to exit:");

        NamedTypedValue<String> userTaskSelection;
        Task selectedTask = null;
        do {
            try {
                userTaskSelection = inputHandler.getUserInput(
                    "Enter task key or 'E' to exit: ", String.class);
                if (userTaskSelection.getValue().equalsIgnoreCase("E")) {
                    return new NamedTypedValue<>("Task", "ExitSelection", null);
                }

                int taskIndex = Integer.parseInt(userTaskSelection.getValue()) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    selectedTask = tasks.get(taskIndex);
                } else {
                    System.out.println("Invalid task number. Please try again.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid task number or 'E' to exit.");
            } catch (Exception e) {
                System.err.println("Error getting user input: " + e.getMessage());
                return new NamedTypedValue<>("Task", "ErrorSelection", null);
            }
        } while (selectedTask == null);

        return new NamedTypedValue<>("Task", "SelectedTask", selectedTask);
    }
}

