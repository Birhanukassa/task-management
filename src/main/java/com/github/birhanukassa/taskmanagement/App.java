package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;

import java.util.logging.Logger;
import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class App {
    static TaskManager taskManager = TaskManager.getInstance();
    static List<Task> sharedTaskList = taskManager.getTasks();
    private static final InputHandler inputHandler = new InputHandler();
    private static final TaskManagerInterface<Task> display = new DisplayImpl();
    TaskSchedulerCommand taskSchedulerCommand = new TaskSchedulerCommand();
    static Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        registerTypeConverters();
        try {
            runTaskManagementProgram(sharedTaskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registerTypeConverters() {
        InputHandler.registerTypeConverter(String.class, String::toString);
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
        InputHandler.registerTypeConverter(Double.class, Double::parseDouble);
    }

    private static void runTaskManagementProgram(List<Task> sharedTaskList) throws IOException {
        boolean shouldExit = true;

        while (shouldExit) {
            display.sortThenDisplayTasks(sharedTaskList);
            String prompt = "\n\nEnter (T) to create new Task, (P) for Prioritizing, (M) for Managing Task Schedule, or E to exit the program: ";
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
                    manageTaskDuration(sharedTaskList, inputHandler, scanner);
                    break;
                case "E":
                    shouldExit = false;
                    break;
                default:
                    logger.warning("Invalid choice. Please try again.");
                    break;
                }
            }
        }
        logger.info("\n\nExiting the program.\n\n");
        scanner.close();
        taskManager.updateTaskAndSaveToFile();
    }

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

    private static void manageTaskDuration(List<Task> sharedTaskList, InputHandler inputHandler, Scanner scanner) {
        TaskSelector taskSelectorInstance = new TaskSelector(inputHandler);
        NamedTypedValue<Task> maybeSelectedTask = taskSelectorInstance.promptUserForTaskSelection(sharedTaskList);

        if (maybeSelectedTask.getValue() != null) {
            Task selectedTask = maybeSelectedTask.getValue();
            updateTaskDuration(selectedTask, scanner);
        } else if (maybeSelectedTask.getName().equals("ExitSelection")) {
            logger.info("Exiting Prioritizing a task.");
        } else {
            logger.info("No task selected for managing.");
        }
    }

    private static void updateTaskDuration(Task selectedTask, Scanner scanner) {
        boolean shouldContinueEditing = true;

        while (shouldContinueEditing) {
            /*
             choose starting date or 
                   if starting date already chosen 
                        choice starting time 
                            if choosing starting time then 
                                aks  if they want to choice ending time 
                        if choosing ending time while staring time is not chosen then 
                            inform user to first choice staring time 

                
                    else if choosing starting date is not chosen then 
                             inform them they need to chose starting date first 
             */
            LocalDate startingDate = TaskPromptUtility.promptForDate(scanner,
                    "Enter the starting date for the task (format:dd-MM-yyyy):");
            LocalTime startingTime = TaskPromptUtility.promptForTime(scanner,
                    "Enter the starting time for the task (format: HH:mm):");
            LocalTime endingTime = TaskPromptUtility.promptForTime(scanner,
                    "Enter the end time for the task (format: HH:mm) or 'x' if there is no end time:");
            int interval = TaskPromptUtility.promptForInterval(scanner,
                    "Do you want to set an interval for the task? inter the :");

            TaskSchedulerCommand.setTimePeriod(selectedTask, startingDate, startingTime, endingTime, interval);

            logger.info(() -> String.format("Task details: %s", selectedTask));
            logger.info("Enter 'q' to quit or any other key to continue editing:");
            String input = scanner.nextLine();
            shouldContinueEditing = !input.equalsIgnoreCase("q");
        }
    }
}
