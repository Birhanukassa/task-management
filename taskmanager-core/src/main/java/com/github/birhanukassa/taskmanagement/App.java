package com.github.birhanukassa.taskmanagement;
import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        while (true) {

            if (tasks.size() > 0) {
                TaskManagerInterface UI = new DisplayImpl();
                UI.displaySortedTasks(tasks);
            } else {
                //  you dont have task. you can create one by intering T bellow
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit: ");
            String userInput = scanner.nextLine().toUpperCase();

            Task task;

            switch (userInput) {
                case "T":
                    System.out.println("You chose creating a Task.");
                    System.out.print("Enter the name of the task: ");
                    String taskName = scanner.nextLine();

                    System.out.print("Enter the description of the task: ");
                    String taskDescription = scanner.nextLine();

                    task = new Task(taskName, taskDescription);
                    System.out.print("You create a new task: \n" + task.toString());
                    tasks.add(task);
                    break;

                case "P":
                    System.out.println("You chose Prioritizing a task.");
                    System.out.print("Enter the key of the task you want to manage, or 'E' to exit: ");
                    int selectedKey = scanner.nextInt();
                    
                    if (selectedKey =< tasks.size()) {
                        Task selectedTask = tasks.get(selectedKey);
                    }
                  

                    if (selectedTask instanceof Task) {
                        System.out.print("You choose : \n" + task.toString());
                   
                        PrioritizeTaskCommand prioritize = new PrioritizeTaskCommand();
                        tasks = prioritize.execute(tasks);
                    } else if (selectedKey)
                    } else {
                        System.out.println("Invalid key. Please enter a number.");
                        continue;
                    }

                   
                case "M":
                     // time, date.
                    System.out.println("You chose Managing a task.");

                    TaskSchedulerCommand schedual = new TaskSchedulerCommand();
                    newTask = schedual.execute(tasks);

                    if (newTask.getTime() != null && task.getInterval() != null) {
                        String taskName = scanner.nextLine();

                        System.out.print("Enter the description of the task: ");
                        String taskDescription = scanner.nextLine();
    
                        newTask = new Task(taskName, taskDescription);
                        tasks.add(newTask);
                        Task newTask = ScheduleTaskCommand();
                        break;
    
                    case "E":
                    }

               
                    System.out.println("Exiting the program.");

                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

           
            for (Task task : tasks) {
                System.out.println(task.toString());
            }

            System.out.print("Enter T for Task, P for Prioritizing, M for ManagingTasks, or E to exit: ");
            scanner.close();
        }
    }
}
