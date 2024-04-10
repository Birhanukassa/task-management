package com.github.birhanukassa.taskmanagement.display;
import com.github.birhanukassa.taskmanagement.commands.*;

import java.util.Scanner;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class DisplayImpl implements TaskManagerInterface {

    @Override
    public void displaySortedTasks(List<Task> tasks) {
        tasks.sort(Comparator.comparingDouble(Task::getPriorityScore).reversed());
       
        System.out.println("Displaying sorted tasks:");
      
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Key: " + i + ", Task: " + tasks.get(i).getTaskName() +
                               ", Priority Score: " + tasks.get(i).getPriorityScore());
        }
    }

    @Override
    public void displayPriorityMatrix(List<Task> tasks) {
        System.out.println("Displaying priority matrix:");
        for (Task task : tasks) {
            System.out.println("Task: " + task.getTaskName() + ", Priority Score: " + task.getPriorityScore());
        }
    }

    @Override
    public Optional<Task> selectTask(List<Task> tasks) {
        Optional<Task> selectedTask = Optional.empty();
        String inputKey;

        displaySortedTasks(tasks);

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
