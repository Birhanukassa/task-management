package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.commands.*;



import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter CT to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit: ");
            String userInput = scanner.nextLine().toUpperCase();

            switch (userInput) {
                case "T":
                    System.out.println("You chose creating a Task.");
                    System.out.print("Enter the name of the task: ");
                    String taskName = scanner.nextLine();

                    System.out.print("Enter the description of the task: ");
                    String taskDescription = scanner.nextLine();

                    Task newTask = new Task(taskName, taskDescription);
                    tasks.add(newTask);
                    break;

                case "P":
                    System.out.println("You chose Prioritizing.");
                    System.out.println("rate how important the task is (1-10): ");
                    String isImportant = scanner.nextLine();

                    System.out.println("rate how urgent the task is (1-10): ");
                    String isUrgent = scanner.nextLine();

                    try {
                        double importance = Double.parseDouble(isImportant);
                        double urgency = Double.parseDouble(isUrgent);
                        double score = (importance * 2) + urgency;
                        System.out.println("The calculated priority score is: " + score);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter numeric values for importance and urgency.");
                    }

                    // sort by importance
                    // display a list of sorted by priority tasks

                    PrioritizeTaskCommand prioritizeCommand = new PrioritizeTaskCommand();
                    tasks = prioritizeCommand.execute(tasks);

                case "M":
                    System.out.println("You chose Managing Tasks.");
                    // time, date.
                    break;

                case "E":
                    System.out.println("Exiting the program.");

                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            scanner.close();

            for (Task task : tasks) {
                System.out.println(task.toString());
            }

            System.out.print("Enter T for Task, P for Prioritizing, M for ManagingTasks, or E to exit: ");
        }
    }

}
