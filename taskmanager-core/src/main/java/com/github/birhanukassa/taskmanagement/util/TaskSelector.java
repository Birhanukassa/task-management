package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;

public class TaskSelector {

    public NameValue selectTask(List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);
        Display display = new Display();

        while (true) {
            display.sortAndDisplayTasks(tasks);

            System.out.print("Enter the key of the task you want to manage, or 'E' to exit: ");
            String inputKey = scanner.nextLine();

            if ("E".equalsIgnoreCase(inputKey)) {
                System.out.println("Exiting task Manager.");
                scanner.close();
                return new NameValue("E", char.class);
            }

            try {
                int selectedTaskIndex = Integer.parseInt(inputKey, 10);

                if (selectedTaskIndex < 0 || selectedTaskIndex >= tasks.size()) {
                    System.out.println("Invalid key. Please enter a valid number.");
                    continue;
                }

                Task selectedTask = tasks.get(selectedTaskIndex);
                System.out.println("Selected Task: " + selectedTask.getTaskName());

                scanner.close();
                return new NameValue(selectedTaskIndex, selectedTask.getClass());
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid key. Please enter a valid number.");
            }
        }
    }
}