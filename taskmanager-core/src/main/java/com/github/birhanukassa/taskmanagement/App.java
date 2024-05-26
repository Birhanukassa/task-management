package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class App {
    private static final List<Task> taskList = TaskListSingleton.getInstance();
    private static final InputHandler inputHandler = new InputHandler();
    private static final TaskManagerInterface<Task> display = new DisplayImpl();

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        registerTypeConverters();
        ArrayList<Task> sharedTaskList = new ArrayList<>(taskList);
        
        runTaskManagementProgram(sharedTaskList);
    }

    private static void registerTypeConverters() {
        InputHandler.registerTypeConverter(String.class, s -> s); 
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
        InputHandler.registerTypeConverter(Double.class, Double::parseDouble);
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
        if (!"T".equals(userInput.getValue().toUpperCase()) && sharedTaskList.isEmpty()) {

            LOGGER.warning(String.format("No tasks found. Please create a new task.");

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
                LOGGER.warning("Invalid choice. Please try again.");
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

    /**
    * Manages the task duration.
    *
    * @param sharedTaskList the list of tasks to be managed
    */
    private static void manageTask(List<Task> sharedTaskList) {
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        if (!maybeSelectedTask.getName().equals("ExitSelection")) {
            Task selectedTask = maybeSelectedTask.getValue();
            boolean continueManaging = true;

            while (continueManaging) {
                LOGGER.info("\nManage Task Options:");
                LOGGER.info("1. Add Schedule");
                LOGGER.info("2. Add Deadline");
                LOGGER.info("3. Add Interval");
                LOGGER.info("4. Return to Main Menu");

                int choice = getValidatedInput("Enter your choice: ", 1, 4);

                switch (choice) {
                    case 1:
                        addSchedule(selectedTask);
                        break;
                    case 2:
                        addDeadline(selectedTask);
                        break;
                    case 3:
                        addInterval(selectedTask);
                        break;
                    case 4:
                        continueManaging = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
    
    


