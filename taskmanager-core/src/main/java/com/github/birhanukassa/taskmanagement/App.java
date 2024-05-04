package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.Optional;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main() {

        while (true) {
            Scanner scanner = new Scanner(System.in);

            TaskList taskList = TaskList.INSTANCE;
            List<Task> sharedTaskList = taskList.getTasks();

            if (sharedTaskList.size() > 0) {

                SelectTask taskSelectorInstance = new SelectTask();
                Optional<Task> maybeSelectedTask = taskSelectorInstance.taskSelectorOptional(sharedTaskList);

                TaskManagerInterface<Task> display = new DisplayImpl();
                display.sortThenDisplayTasks(sharedTaskList);

                System.out.print(
                        "Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit: ");

                String userInput = scanner.nextLine().toUpperCase();

                switch (userInput) {

                    case "T":
                        TaskFactory taskFactory = new TaskFactory();
                        Task newTask = taskFactory.createTask();
                        sharedTaskList.add(newTask);
                        break;

                    case "P":
                        // TODO
                        // call PrioritizeTaskCommand class to prioritize the task
                              // call taskSelectorOptional method to select the task to prioritize
                               // call taskSelector method to select the task to prioritize
                        //
                        PriorityQueueCommand prioritizeTaskCommand = new PriorityQueueCommand();
                        PriorityQueueCommand();

                    case "M":
                        // time, date.
                        System.out.println("You chose Managing a task.");

                        TaskSchedulerCommand schedual = new TaskSchedulerCommand();
                        // give a change to select the desired task
                        // create an algorithm that does appropriate action to the current case

                        // Optional<Task> currTask = selector.taskSelectorOptional(sharedTaskList);

                        // newTask = schedule.execute(tasks);

                        // if (newTask.getTime() != null && task.getInterval() != null) {
                        // String taskName = scanner.nextLine();

                        // System.out.print("Enter the description of the task: ");
                        // String taskDescription = scanner.nextLine();

                        // newTask = new Task(taskName, taskDescription);
                        // tasks.add(newTask);
                        // Task newTask = ScheduleTaskCommand();
                        // break;
                        // }

                    case "E":

                        System.out.println("Exiting the program.");

                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
