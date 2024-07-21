package com.github.birhanukassa.taskmanagement.models;
import com.github.birhanukassa.taskmanagement.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;


public class TaskManager {
    private static final String FILE_PATH = "/home/birhanu/WGU-BS-CS/intro-to-programming/task-management/src/main/java/com/github/birhanukassa/taskmanagement/models/tasks.csv";
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());
    private static TaskManager instance;
    private List<Task> tasks;
    private TaskManager() {
        try {
            tasks = loadTasksFromCsv(FILE_PATH);
        } catch (IOException e) {
            logger.warning("No available tasks to load tasks. Create a new list of tasks");
            e.printStackTrace();
        }
    }
    
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public List<Task> loadTasksFromCsv(String filePath) throws IOException {
        tasks = new ArrayList<>();

        // Read the CSV file line by line
        for (String line : Files.readAllLines(Paths.get(filePath))) {
            Task task = new Task(line);
            tasks.add(task);
        }

        return tasks;
    }

    public void updateTaskAndSaveToFile() throws IOException {
        if (!tasks.isEmpty()) {
            writeTasksToCSV();
        }
    }

    public static String getFieldValuesAsCsv(Task task) {
        List<NamedTypedValue<Object>> fieldValues = FieldValueMapper.getInitializedVars(task);
        List<String> fieldValueStrings = new ArrayList<>();
        for (NamedTypedValue<?> fieldValue : fieldValues) {
            String fieldValueString = fieldValue.getName() + ": " + fieldValue.getValue();
            fieldValueStrings.add(fieldValueString);
        }

        return String.join(", ", fieldValueStrings);
    }

    private void writeTasksToCSV() throws IOException {
        Files.write(Paths.get(FILE_PATH), tasks.stream()
            .map(TaskManager::getFieldValuesAsCsv)
            .collect(Collectors.toList()));
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

