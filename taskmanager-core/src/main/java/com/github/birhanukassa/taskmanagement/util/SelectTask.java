package com.github.birhanukassa.taskmanagement.util;
import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;


import java.util.Optional;
import java.util.Scanner;
import java.util.List;

public class SelectTask  {

    public <T> Optional<T> taskSelectorOptional(List<T> tasks) {
        Optional<T> selectedTask = Optional.empty();
        String inputKey;

        DisplayImpl display = new DisplayImpl();

        display.displaySortedTasks(tasks); // the input is generic not tasks arrayLIst 


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the key of the task you want to manage, or 'E' to exit: ");
        inputKey = scanner.nextLine();

        do {
            if ("E".equalsIgnoreCase(inputKey)) {
                System.out.println("Exiting task Manager.");
                break;
            }

            int selectedTaskIndex;

            try {
                selectedTaskIndex = Integer.parseInt(inputKey, 10);

            } catch (NumberFormatException e) {
                System.out.println("Invalid key. Please enter a number.");
                continue;
            }

            if (selectedTaskIndex < 0 || selectedTaskIndex >= tasks.size()) {
                System.out.println("Invalid key. Please try again.");
                continue;
            }

            selectedTask = Optional.of(tasks.get(selectedTaskIndex));
            System.out.println("Selected Task: " + selectedTask.get().getTaskName());

        } while (true);

        scanner.close();
        return selectedTask;
    }
}
