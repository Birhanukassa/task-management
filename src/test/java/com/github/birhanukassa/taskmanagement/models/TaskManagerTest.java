package com.github.birhanukassa.taskmanagement.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class TaskManagerTest {

    private static final String TEST_CSV_FILE = "src/test/test_tasks.csv";

    @BeforeAll
    static void createTestCsvFile() throws IOException {
        Path testCsvFilePath = Paths.get(TEST_CSV_FILE);
        Files.createDirectories(testCsvFilePath.getParent());

        List<String> testData = Arrays.asList(
            "name: Task 1, description: This is task 1, priorityLevel: 3.5, importance: 5, urgency: 7",
            "name: Task 2, description: This is task 2, priorityLevel: 2.0, importance: 3, urgency: 4"
        );

        Files.write(testCsvFilePath, testData);
    }

    @Test
    void testLoadTasksFromValidCsv() throws IOException {
        TaskManager taskManager = TaskManager.getInstance();
        List<Task> tasks = taskManager.loadTasksFromCsv(TEST_CSV_FILE);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
    }

    @Test
    void testLoadTasksFromInvalidCsv() {
        TaskManager taskManager = TaskManager.getInstance();
        Assertions.assertThrows(IOException.class, () -> taskManager.loadTasksFromCsv("invalid_file.csv"));
    }

    @Test
    void testUpdateTaskAndSaveToFile() throws IOException {
        TaskManager taskManager = TaskManager.getInstance();
        List<Task> tasks = taskManager.loadTasksFromCsv(TEST_CSV_FILE);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());

        // Create a temporary file
        Path tempFile = Files.createTempFile("test_tasks", ".csv");

        // Modify a task
        Task modifiedTask = tasks.get(0);
        modifiedTask.setTaskName("Modified Task Name");

        // Write tasks to the temporary file
        taskManager.setTasks(tasks);
        taskManager.updateTaskAndSaveToFile();

        Files.readAllLines(tempFile);

        // Clean up the temporary file
        Files.delete(tempFile);
    }

    @Test
    void testGetFieldValuesAsCsv() {
        Task task = new Task("Task Name", "Task Description");
        task.setPriorityScore(3.5);
        task.setImportance(5);
        task.setUrgency(7);

        String csvString = TaskManager.getFieldValuesAsCsv(task);
        Assertions.assertNotNull(csvString);
        Assertions.assertTrue(csvString.contains("name: Task Name"));
        Assertions.assertTrue(csvString.contains("description: Task Description"));
        Assertions.assertTrue(csvString.contains("priorityLevel: 3.5"));
        Assertions.assertTrue(csvString.contains("importance: 5"));
        Assertions.assertTrue(csvString.contains("urgency: 7"));
    }
}
