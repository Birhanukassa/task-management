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
    public static void main() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            
            TaskList taskList = TaskList.INSTANCE;
            List<Task> sharedTaskList = taskList.getTasks();
            
            SelectTask selector = new SelectTask();
            Optional<Task> currTask = selector.taskSelectorOptional(sharedTaskList);
 
            if (sharedTaskList.size() > 0) {

                DisplayImpl displayer = new DisplayImpl(sharedTaskList);
                displayer.sortThenDisplayTasks(sharedTaskList);
            } else {

                System.out.println("You don't have any tasks yet. Please create a new task first.");
            }
            
            System.out.print(
                "Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit: "
                );

            String userInput = scanner.nextLine().toUpperCase();

            switch (userInput) {

                case "T":
                // TaskFactory taskFactory = new TaskFactory();
                // Task task = taskFactory.getNewTask();
                // sharedTaskList.add(task);

                    break;

                case "P":
                    System.out.println("You chose Prioritizing a task.");
                
                    // If there are tasks to prioritize 
                    if (!sharedTaskList.isEmpty()) {
            
                        System.out.print("Enter the number of the task to prioritize: ");
                        int taskIndex = scanner.nextInt(); 
                    
                        // Input Validation (Make sure taskIndex is valid)
                        
                
                        Task taskToPrioritize = sharedTaskList.get(taskIndex);
                        System.out.print("Enter priority level (1-Highest, etc.): "); 
                        int priorityLevel = scanner.nextInt(); 
                        scanner.nextLine(); 
                
                        taskToPrioritize.setPriorityScore(priorityLevel);  // Assuming 'Task' has a setPriority method
                } else {
                    System.out.println("No tasks to prioritize.Please create a new task first.");
                }
                    break; 
                

                case "M":
                    // time, date.
                    System.out.println("You chose Managing a task.");

                    TaskSchedulerCommand schedual = new TaskSchedulerCommand();
                    currTask = schedual.execute(sharedTaskList);

                    if (currTask.getTime() != null && task.getInterval() != null) {
                        String taskName = scanner.nextLine();

                        System.out.print("Enter the description of the task: ");
                        String taskDescription = scanner.nextLine();
    
                        currTask = new Task(taskName, taskDescription);
                        tasks.add(currTask);
                        Task currTask = ScheduleTaskCommand();
                        break;
                    }
    
                case "E":
            
                    System.out.println("Exiting the program.");

                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
            
        } 
    }
}

