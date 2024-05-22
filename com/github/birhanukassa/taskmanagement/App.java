package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static final List<Task> taskList = TaskListSingleton.getInstance();
    private static final InputHandler inputHandler = new InputHandler();
    private static final TaskManagerInterface<Task> display = new DisplayImpl();

    public static void main(String[] args) {
        registerTypeConverters();
        ArrayList<Task> sharedTaskList = new ArrayList<>(taskList);
        
        runTaskManagementProgram(sharedTaskList);
    }

    private static void registerTypeConverters() {
        inputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
        inputHandler.registerTypeConverter(Double.class, Double::parseDouble);
    }

    private static void runTaskManagementProgram(List<Task> sharedTaskList) {
        NamedTypedValue<String> userInput;
        do {
            display.sortThenDisplayTasks(sharedTaskList);
            userInput = getUserInput();
            handleUserInput(userInput, sharedTaskList);
        } while (!userInput.getValue().equalsIgnoreCase("E"));
        System.out.println("Exiting the program.");
    }

    private static NamedTypedValue<String> getUserInput() {
        try {
            return inputHandler.getUserInput(
                "Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit the program: ", String.class);
        } catch (Exception e) {
            System.out.println("An error occurred while getting user input: " + e.getMessage());
            // Handle the exception or return a default value
            return new NamedTypedValue<>("string", "Error", "E"); // Returning "E" to exit, as an example
        }
    }
    
    private static void handleUserInput(NamedTypedValue<String> userInput, List<Task> sharedTaskList) {
        if (sharedTaskList.isEmpty()) {
            System.out.println("No tasks found. Please create a new task.");
            return;
        }

        switch (userInput.getValue().toUpperCase()) {
            case "T":
                createNewTask(sharedTaskList);
                break;
            case "P":
                prioritizeTask(sharedTaskList);
                break;
            case "M":
                manageTask(sharedTaskList);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void createNewTask(List<Task> sharedTaskList) {
        TaskFactory taskFactory = new TaskFactory();
        try {
            Task newTask = taskFactory.createTask();
            sharedTaskList.add(newTask);
        } catch (Exception e) {
            System.out.println("An error occurred while creating a new task: " + e.getMessage());
        }
    }
        
    private static void prioritizeTask(List<Task> sharedTaskList) {
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        if (maybeSelectedTask.getName().equals("ExitSelection")) {
            System.out.println("Exiting Prioritizing a task.");
        } else if (maybeSelectedTask.getValue() != null) {
            Task selectedTask = maybeSelectedTask.getValue();
            PriorityQueueCommand prioritizeTaskCommand = new PriorityQueueCommand();
            prioritizeTaskCommand.execute(selectedTask);
        } else {
            System.out.println("No task selected for prioritization.");
        }
    }

    private static void manageTask(List<Task> sharedTaskList) {
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        if (maybeSelectedTask.getName().equals("ExitSelection")) {
            System.out.println("Exiting Managing duration of a task.");
        } else if (maybeSelectedTask.getValue() != null) {
            Task selectedTask = maybeSelectedTask.getValue();
            TaskSchedulerCommand taskSchedulerCommand = new TaskSchedulerCommand();
            taskSchedulerCommand.execute(selectedTask);
        } else {
            System.out.println("No task selected for management.");
        }
    }
}


/* 
public class App {
    public static void main() throws Exception {

        // need to work on these !
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);  
        InputHandler.registerTypeConverter(Integer.class, s -> Integer.parseInt(s));
        InputHandler.registerTypeConverter(Double.class, s -> Double.parseDouble(s));

        TaskList taskList = TaskList.INSTANCE;
        List<Task> sharedTaskList = taskList.getTasks();
       
        InputHandler inputHandler = new InputHandler();
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        TaskManagerInterface<Task> display = new DisplayImpl();
        display.sortThenDisplayTasks(sharedTaskList);

        // display.displayPriorityMatrix(sharedTaskList);
        NamedTypedValue<String> userInput = inputHandler.getUserInput(
                "Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit the program: ", String.class);
            
        while (!userInput.getValue().equalsIgnoreCase("E")) {

            if (sharedTaskList.size() > 0) {

                switch (userInput.getValue().toUpperCase()) {

                    case "T":
                        TaskFactory taskFactory = new TaskFactory();
                        Task newTask = taskFactory.createTask();
                        sharedTaskList.add(newTask);
                        break;

                    case "P":
                        System.out.println("You chose Prioritizing a task.");
                        display.sortThenDisplayTasks(sharedTaskList);

                        if (maybeSelectedTask.getName().equals("ExitSelection")) {
                            System.out.println("Exiting Prioritizing a task.");
                            continue;
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

                        display.sortThenDisplayTasks(sharedTaskList);

                        if (maybeSelectedTask.getName().equals("ExitSelection")) {
                            System.out.println("Exiting Prioritizing a task.");
                            continue;
                        } else if (maybeSelectedTask.getValue() != null) {
                            Task selectedTask = maybeSelectedTask.getValue();
                            TaskSchedulerCommand taskSchedulerCommand = new TaskSchedulerCommand();

                            taskSchedulerCommand.execute(selectedTask);
                        } else {
                            System.out.println("No task selected for prioritization.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

            } else {
                System.out.println("No tasks found. Please create a new task.");
            }
        }
    
        System.out.println("Exiting the program.");
    } 
}
 */