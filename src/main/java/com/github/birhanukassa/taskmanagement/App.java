package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final TaskManager taskManager = TaskManager.getInstance();
    private static final List<Task> sharedTaskList = taskManager.getTasks();
    private static final TaskManagerInterface<Task> display = new DisplayImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            runTaskManagementProgram();
        } catch (IOException e) {
            logger.severe("An error occurred: " + e.getMessage());
        }
    }

    private static void runTaskManagementProgram() throws IOException {
        boolean shouldExit = false;
        while (!shouldExit) {
            display.sortThenDisplayTasks(sharedTaskList);
            String choice = promptUserForChoice();
            shouldExit = handleUserChoice(choice);
        }
        logger.info("Exiting the program...");
        scanner.close();
        taskManager.updateTaskAndSaveToFile();
    }

    private static String promptUserForChoice() {
        String prompt = "\n\nEnter (T) to create new Task, (P) for Prioritizing, (M) for Managing Task Schedule, or E to exit the program: ";
        NamedTypedValue<String> userInput = InputHandler.getUserInput(prompt, String.class);
        return userInput.getValue().toUpperCase();
    }

    private static boolean handleUserChoice(String choice) {
        switch (choice) {
            case "T" -> {
                createNewTask();
                return false;
            }
            case "P" -> {
                prioritizeTask();
                return false;
            }
            case "M" -> {
                manageTaskDuration();
                return false;
            }
            case "E" -> {
                return true;
            }
            default -> {
                logger.warning("Invalid choice. Please try again.");
                return false;
            }
        }
    }

    private static void createNewTask() {
        try {
            Task newTask = TaskFactory.createTask();
            sharedTaskList.add(newTask);
        } catch (Exception e) {
            logger.warning("An error occurred while creating a new task: " + e.getMessage());
        }
    }

    private static void prioritizeTask() {
        NamedTypedValue<Task> maybeSelectedTask = TaskSelector.promptUserForTaskSelection(sharedTaskList);

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

    private static void manageTaskDuration() {
        NamedTypedValue<Task> maybeSelectedTask = TaskSelector.promptUserForTaskSelection(sharedTaskList);

        if (maybeSelectedTask.getValue() != null) {
            Task selectedTask = maybeSelectedTask.getValue();
            updateTaskDuration(selectedTask);
        } else if (maybeSelectedTask.getName().equals("ExitSelection")) {
            logger.info("\n\nExiting Prioritizing a task.");
        } else {
            logger.info("No task selected for managing.");
        }
    }

    private static void updateTaskDuration(Task selectedTask) {
        boolean shouldContinueEditing;
        do {
            LocalDate startDate = promptAndHandleInput("getStartDate", "Enter the starting date for the task (format:MM/dd/yyyy) (or 'Q' to exit):", LocalDate.class, selectedTask);
            LocalDate endDate = promptAndHandleInput("getEndDate", "Enter the end date for the task (format:MM/dd/yyyy) (or 'Q' to exit):", LocalDate.class, selectedTask);
            LocalTime startTime = promptAndHandleInput("getStartTime", "Enter the starting time for the task (format:HH:mm:ss) (or 'Q' to exit):", LocalTime.class, selectedTask);
            LocalTime endTime = promptAndHandleInput("getEndTime", "Enter the end time for the task (format:HH:mm:ss) (or 'Q' to exit):", LocalTime.class, selectedTask);
            Integer interval = promptAndHandleInput("getInterval", "Enter the interval in number of days you would like it to reoccur (or 'Q' to exit):", Integer.class, selectedTask);

            TaskSchedulerCommand.setTimePeriod(selectedTask, startDate, endDate, startTime, endTime, interval);

            System.out.println("Enter 'Q' to quit or any other key to continue editing:");
            shouldContinueEditing = !scanner.nextLine().equalsIgnoreCase("Q");
        } while (shouldContinueEditing);
    }

    private static <T> T promptAndHandleInput(String getterName, String prompt, Class<T> type, Task task) {
        T currentValue = getCurrentValue(getterName, task, type);

        if (FieldValueMapper.isFieldInitialized(currentValue)) {
            System.out.println("Current value for " + prompt + " is " + currentValue + "\n. Press 'C' to change or any other key to keep it.");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("C")) return currentValue;
        }

        NamedTypedValue<T> input = InputHandler.getUserInput(prompt, type);
        return handleInput(input, type);
    }

    private static <T> T getCurrentValue(String getterName, Task task, Class<T> type) {
        try {
            return type.cast(task.getClass().getMethod(getterName).invoke(task));
        } catch (Exception e) {
            return getDefaultValue(type);
        }
    }

    private static <T> T handleInput(NamedTypedValue<T> input, Class<T> type) {
        if (input.getValue().toString().equalsIgnoreCase("Q")) return getDefaultValue(type);  
        return input.getValue();
    }

    private static <T> T getDefaultValue(Class<T> type) {
        if (type == Integer.class) {
            return type.cast(0);
        } else if (type == char.class) {
            return type.cast('\u0000');
        } else if (type == String.class) {
            return type.cast("");
        } else {
            return null;
        }
    }
}
