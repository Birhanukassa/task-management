package com.github.birhanukassa.taskmanagement;

// Importing necessary classes and packages
import com.github.birhanukassa.taskmanagement.commands.*;
import com.github.birhanukassa.taskmanagement.display.*;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.util.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;
public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final TaskManager taskManager = TaskManager.getInstance();
    private static final List<Task> sharedTaskList = taskManager.getTasks();
    private static final TaskManagerInterface<Task> display = new DisplayImpl();

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
        ScannerWrapper.close();
        taskManager.updateTaskAndSaveToFile();
    }

    static String promptUserForChoice() {
        String prompt = "\n\nEnter (T) to create new Task, (P) for Prioritizing, (M) for Managing Task Schedule, or (E) to exit the program: ";
        NamedTypedValue<String> userInput =    InputHandler.getUserInput(prompt, String.class);
        return userInput.getValue().toUpperCase();
    }

    static boolean handleUserChoice(String choice) {
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

    static void createNewTask() {
        try {
            Task newTask = TaskFactory.createTask();
            sharedTaskList.add(newTask);
        } catch (Exception e) {
            logger.warning("An error occurred while creating a new task: " + e.getMessage());
        }
    }

    static void prioritizeTask() {
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

    static void manageTaskDuration() {
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

    static void updateTaskDuration(Task selectedTask) {
        boolean shouldContinueEditing;
        do {
            LocalDate startDate = promptAndHandleInput("getStartDate", "Please enter the start date for this task in the format MM/DD/YYYY (e.g., 12/21/2024). If you wish to exit, enter 'Q': ", LocalDate.class, selectedTask);
            LocalDate endDate = promptAndHandleInput("getEndDate", "Please enter the end date for this task in the format MM/DD/YYYY (e.g., 12/21/2024). If you wish to exit, enter 'Q' : " , LocalDate.class, selectedTask);
            LocalTime startTime = promptAndHandleInput("getStartTime", "Please enter the start time for this task using the the 24 hour format HH:MM (e.g., 17:00). If you wish to exit, enter 'Q': ", LocalTime.class, selectedTask);
            LocalTime endTime = promptAndHandleInput("getEndTime", "Please enter the end time for this task using the 24-hour format HH:MM (e.g., 06:00), IF you wish to exit, enter 'Q' to quite: ", LocalTime.class, selectedTask);
            Integer interval = promptAndHandleInput("getInterval", "Please enter the interval number of days you would like it to reoccur the reschedule(e,g., 7). If you wish to exit, enter 'Q': ", Integer.class, selectedTask);

            TaskSchedulerCommand.setTimePeriod(selectedTask, startDate, endDate, startTime, endTime, interval);

            System.out.println("Enter ('Q') to quit or any other key to continue editing: ");
            shouldContinueEditing = !ScannerWrapper.nextLine().equalsIgnoreCase("Q");
        } while (shouldContinueEditing);
    }

    private static <T> T promptAndHandleInput(String getterName, String prompt, Class<T> type, Task task) {
        T currentValue = getTaskFieldValue(getterName, task, type);

        if (FieldValueMapper.isFieldInitialized(currentValue)) {
            System.out.println("Current value for " + prompt + " is " + currentValue + ".\n Press (C) to change or any other key to keep it.");
            String choice = ScannerWrapper.nextLine();
            if (!choice.equalsIgnoreCase("C")) return currentValue;
        }

        NamedTypedValue<T> input = InputHandler.getUserInput(prompt, type);
        return handleInput(input, type);
    }

    private static <T> T getTaskFieldValue(String getterName, Task task, Class<T> type) {
        try {
            return type.cast(task.getClass().getMethod(getterName).invoke(task));
        } catch (Exception e) {
            return getDefaultValue(type);
        }
    }

    private static <T> T handleInput(NamedTypedValue<T> input, Class<T> type) {
        if (input.getName() == null) return getDefaultValue(type);

        if (input.getValue().toString().equalsIgnoreCase("Q")) {
            return getDefaultValue(type); 
        } 
        return input.getValue();
    }

    private static <T> T getDefaultValue(Class<T> type) {
        return switch (type.getSimpleName()) {
            case "Integer" -> type.cast(0);
            case "Character" ->  type.cast('\u0000');
            case "String" -> type.cast("");
            default -> null;
        };
    }   
}


