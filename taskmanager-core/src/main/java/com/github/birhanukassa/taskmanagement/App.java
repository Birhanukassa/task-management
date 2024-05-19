package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main() throws Exception {

     
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);  // ?
        InputHandler.registerTypeConverter(Integer.class, s -> Integer.parseInt(s));
        InputHandler.registerTypeConverter(Double.class, s -> Double.parseDouble(s));
    

        
        InputHandler inputHandler = new InputHandler();

        while (true) {

            TaskList taskList = TaskList.INSTANCE;
            List<Task> sharedTaskList = taskList.getTasks();
            TaskSelector taskSelectorInstance = new TaskSelector();

            if (sharedTaskList.size() > 0) {
                TaskManagerInterface<Task> display = new DisplayImpl();
                display.sortThenDisplayTasks(sharedTaskList);

                // display.displayPriorityMatrix(sharedTaskList);
                NamedTypedValue<String> userInput = inputHandler.getUserInput(
                        "Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit: ", String.class);

                // how to pass the rest of args to NamedTypedValue<String, Object> ?

                switch (userInput.getValue()) {

                    case "T":
                        TaskFactory taskFactory = new TaskFactory();
                        Task newTask = taskFactory.createTask();
                        sharedTaskList.add(newTask);
                        break;

                    case "P":
                        System.out.println("You chose Prioritizing a task.");
                       NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.selectTask(sharedTaskList);

                        if (maybeSelectedTask.getName().equals("ExitSelection")) {
                            // Handle the exit case here
                            System.out.println("Exiting task selection.");
                        } else if (maybeSelectedTask.getValue() != null) {
                            Task selectedTask = maybeSelectedTask.getValue();

                            PriorityQueueCommand prioritizeTaskCommand = new PriorityQueueCommand();
                            prioritizeTaskCommand.execute(selectedTask);
                        } else {
                            System.out.println("No task selected for prioritization.");
                        }

                        break;

                    case "M":
                        // time, date.
                        System.out.println("You chose Managing duration of a task.");

                        TaskSchedulerCommand schedule = new TaskSchedulerCommand();
                        // Todo : fix TaskSelector class bugs, explore task and duration problem domain then create an algorithm with pedac; implement efficient solution 

                    case "E":

                        System.out.println("Exiting the program.");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("No tasks found. Please create a new task.");
            }
        }
    }
}
