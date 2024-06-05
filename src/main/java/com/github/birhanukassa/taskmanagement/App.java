package com.github.birhanukassa.taskmanagement;
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.logging.Logger;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
public class App {
    private static final List<Task> sharedTaskList = TaskListSingleton.getInstance();
    private static final InputHandler inputHandler = new InputHandler();
    private static final TaskManagerInterface<Task> display = new DisplayImpl();
    TaskSchedulerCommand taskSchedulerCommand = new TaskSchedulerCommand();

    static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        registerTypeConverters();
        runTaskManagementProgram();
    }

    private static void registerTypeConverters() {
        InputHandler.registerTypeConverter(String.class, String::toString);
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
        InputHandler.registerTypeConverter(Double.class, Double::parseDouble);
    }
    private static void runTaskManagementProgram() {
        TaskManagerInterface<Task> display = new DisplayImpl();
        boolean shouldExit = false;

        while (!shouldExit) {
            display.sortThenDisplayTasks(sharedTaskList);

            String prompt = "Enter (T) to create new Task, (P) for Prioritizing, (M) for Managing Task Schedule, or E to exit the program: ";
            NamedTypedValue<String> userInput = null;
            try {
                userInput = inputHandler.getUserInput(prompt, String.class);
            } catch (Exception e) {
                // Handle the exception
                e.printStackTrace(); // For example, print the stack trace
            }

            if (userInput != null) {
                String inputValue = userInput.getValue().toUpperCase();
                switch (inputValue) {
                case "T":
                    createNewTask(sharedTaskList);
                    break;
                case "P":
                    prioritizeTask(sharedTaskList, inputHandler);
                    break;
                case "M":
                    manageTaskDuration1(sharedTaskList, inputHandler, scanner);
                    break;
                case "E":
                    shouldExit = true;
                    break;
                default:
                    logger.warning("Invalid choice. Please try again.");
                    break;
                }
            }
        }
        logger.info("Exiting the program.");
    }
    /* 
    private static NamedTypedValue<String> getUserInput(Scanner scanner, InputHandler inputHandler) {
        String prompt = "Enter T to create new Task, P for Prioritizing, M for Managing Tasks, or E to exit the program: ";
        NamedTypedValue<String> userInput = null;

        while (userInput == null) {
            try {
                String inputValue = userInput.getValue().toUpperCase();
                if (!inputValue.matches("^[TPME]$")) {
                    LOGGER.warning("Invalid input. Please try again.");
                    userInput = null;
                }
            } catch (Exception e) {
                LOGGER.warning("An error occurred while getting user input: " + e.getMessage());
            }
        }

        return userInput;
    }

    */
    private static void createNewTask(List<Task> sharedTaskList) {
        TaskFactory taskFactory = new TaskFactory();
        try {
            Task newTask = taskFactory.createTask();
            sharedTaskList.add(newTask);
        } catch (Exception e) {
            logger.warning("An error occurred while creating a new task: " + e.getMessage());
        }
    }
    private static void prioritizeTask(List<Task> sharedTaskList, InputHandler inputHandler) {
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        if (maybeSelectedTask.getName().equals("ExitSelection")) {
            logger.info("Exiting Prioritizing a task.");
        } else if (maybeSelectedTask.getValue() != null) {
            Task selectedTask = maybeSelectedTask.getValue();
            PriorityQueueCommand prioritizeTaskCommand = new PriorityQueueCommand();
            prioritizeTaskCommand.execute(selectedTask);
        } else {
            logger.info("No task selected for prioritization.");
        }
    }
    private static void manageTaskDuration1(List<Task> sharedTaskList, InputHandler inputHandler, Scanner scanner) {
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        if (maybeSelectedTask.getValue() instanceof Task) {
            Task selectedTask = maybeSelectedTask.getValue();
            manageTaskDuration2(selectedTask, scanner);
        } else if (maybeSelectedTask.getName().equals("ExitSelection")) {
            logger.info("Exiting Prioritizing a task.");
        } else {
            logger.info("No task selected for managing.");
        }
    }
    private static void manageTaskDuration2(Task selectedTask, Scanner scanner) {
        boolean shouldContinueEditing = true;

        while (shouldContinueEditing) {

            LocalDate startingDate = TaskPromptUtility.promptForDate(scanner, "Enter the starting date for the task (format: YYYY-MM-DD):");

            LocalTime startingTime = TaskPromptUtility.promptForTime(scanner, "Enter the starting time for the task (format: HH:mm):");
            LocalTime endingTime = TaskPromptUtility.promptForTime(scanner, "Enter the end time for the task (format: HH:mm) or 'x' if there is no end time:");
            LocalDate interval = TaskPromptUtility.promptForInterval(scanner, "Do you want to set an interval for the task? (YES/NO):");

            TaskSchedulerCommand.setTimePeriod(selectedTask, startingDate, startingTime, endingTime, interval);

            logger.info(() -> String.format("Task details: %s", selectedTask));


            logger.info("Enter 'q' to quit or any other key to continue editing:");
            String input = scanner.nextLine();
            shouldContinueEditing = !input.equalsIgnoreCase("q");
        }
    }
}

