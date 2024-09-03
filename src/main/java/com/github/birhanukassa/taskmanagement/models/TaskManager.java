// Package declaration
package com.github.birhanukassa.taskmanagement.models;
// Importing necessary classes 
import com.github.birhanukassa.taskmanagement.util.FieldValueMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

// Class definition
/**
 * This class manages the tasks, which are either loaded from a CSV file or creates instance to hold tasks when created and modified. 
 * It provides methods to load tasks from a CSV file,
 * update tasks, and save tasks back to the CSV file.
 */
public class TaskManager {
    private static final String FILE_PATH = "/home/birhanu/WGU-BS-CS/intro-to-programming/task-management/src/main/java/com/github/birhanukassa/taskmanagement/models/tasks.csv";
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());
    private static TaskManager instance;
    private List<Task> tasks;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * It initializes the tasks list by loading tasks from the CSV file.
     */
    private TaskManager() {
        try {
            tasks = loadTasksFromCsv(FILE_PATH);
        } catch (IOException e) {
            logger.warning("No available tasks to load tasks. Create a new list of tasks");
            e.printStackTrace();
        }
    }

    // Static method to get the singleton instance of TaskManager
    /**
     * Returns the singleton instance of TaskManager.
     * If the instance doesn't exist, it creates a new instance.
     *
     * @return The singleton instance of TaskManager.
     */
    public static TaskManager getInstance() {
        if (instance == null) instance = new TaskManager();
        return instance;
    }

    // Method to load tasks from a CSV file
    /**
     * Loads tasks from a CSV file and returns a list of Task objects.
     *
     * @param filePath The path of the CSV file.
     * @return A list of Task objects loaded from the CSV file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> loadTasksFromCsv(String filePath) throws IOException {
        tasks = new ArrayList<>();

        // Read the CSV file line by line
        for (String line : Files.readAllLines(Paths.get(filePath))) {
            Task task = new Task(line);
            tasks.add(task);
        }

        return tasks;
    }

    // Method to update tasks and save to a CSV file
    /**
     * Updates the tasks list and saves it to the CSV file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void updateTaskAndSaveToFile() throws IOException {
        if (!tasks.isEmpty()) {
            writeTasksToCSV();
        }
    }

    // Helper method to get field values as a CSV string
    /**
     * Returns a CSV string representation of the field values of a Task object.
     *
     * @param task The Task object.
     * @return A CSV string representation of the field values.
     */
    public static String getFieldValuesAsCsv(Task task) {
        List<NamedTypedValue<Object>> fieldValues = FieldValueMapper.getInitializedVars(task);
        List<String> fieldValueStrings = new ArrayList<>();

        // Iterate over the field values and create a string representation
        for (NamedTypedValue<?> fieldValue : fieldValues) {
            String fieldValueString = fieldValue.getName() + ": " + fieldValue.getValue();
            fieldValueStrings.add(fieldValueString);
        }

        return String.join(", ", fieldValueStrings);
    }

    // Helper method to write tasks to a CSV file
    /**
     * Writes the tasks list to the CSV file.
     *
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void writeTasksToCSV() throws IOException {
        Files.write(Paths.get(FILE_PATH), tasks.stream()
            .map(TaskManager::getFieldValuesAsCsv)
            .collect(Collectors.toList()));
    }

    // Getter and setter methods for tasks
    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    // Setter method for tasks
    /**
     * Sets the list of tasks.
     *
     * @param tasks The list of tasks to be set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

