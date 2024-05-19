package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.*;
import java.util.List;

public class TaskSelector {
  public NamedTypedValue<Task> selectTask(List<Task> tasks) {
    InputHandler inputHandler = new InputHandler();
    NamedTypedValue<String> userInput = inputHandler.getUserInput("Enter task number or 'E' to exit: ", String.class);

    if (userInput.getValue().equalsIgnoreCase("E")) {
        return new NamedTypedValue<>("Task", "ExitSelection", null);
    }

    do {
        try {
            int selectedIndex = Integer.parseInt(userInput.getValue());

            if (selectedIndex < 0 || selectedIndex >= tasks.size()) {
                System.out.println("Invalid key. Please enter a valid number.");
                userInput = inputHandler.getUserInput("Enter task number or 'E' to exit: ", String.class);
                continue;
            }

            Task selectedTask = tasks.get(selectedIndex);
            return new NamedTypedValue<>("Task", "SelectedTask", selectedTask);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number or 'E' to exit.");
            userInput = inputHandler.getUserInput("Enter task number or 'E' to exit: ", String.class);
        }
    } while (!userInput.getValue().equalsIgnoreCase("E"));

    // This line should never be reached, but added for completeness
    return new NamedTypedValue<>("Task", "NoSelection", null);
}

}     
           

        
