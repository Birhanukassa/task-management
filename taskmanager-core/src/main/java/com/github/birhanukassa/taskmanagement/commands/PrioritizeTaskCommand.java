package com.github.birhanukassa.taskmanagement.commands;
import com.github.birhanukassa.taskmanagement.display.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;



public class PrioritizeTaskCommand implements TaskCommand<Task> {

    @Override
    public List<Task> execute(List<Task> tasks) {
        TaskManagerInterface taskManager = new DisplayImpl();
        taskManager.displaySortedTasks(tasks);

        Optional<Task> selectedTask = taskManager.selectTask(tasks);

        selectedTask.ifPresent(task -> {
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            scanner.close();

            switch (action.toUpperCase()) {
                case "DO":
                    // Logic for doing the task immediately
                    System.out.println("Performing task: " + task.getTaskName());
                    break;
                case "SCHEDULE":
                    // Logic for scheduling the task
                    System.out.println("Scheduling task: " + task.getTaskName());
                    break;
                case "DELEGATE":
                    // Logic for delegating the task
                    System.out.println("Delegating task: " + task.getTaskName());
                    break;
                case "DELETE":
                    tasks.remove(task);
                    System.out.println("Task deleted.");
                    break;
                default:
                    System.out.println("Invalid action. Please try again.");
                    execute(tasks);  // ? calling recursively  ?
                    break;
            }
        });

        if (!selectedTask.isPresent()) {
            System.out.println("Thank you. You are exiting prioritizing tasks.");
        }

        return tasks;
    }
}
