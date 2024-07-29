package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.models.TaskManager;
import com.github.birhanukassa.taskmanagement.util.InputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AppTest {

    @Test
    void testPromptUserForChoice() {
        // Mock user input
        NamedTypedValue<String> input = new NamedTypedValue<>("String", "userInput", "T");
        
        // Prepare test data
        TaskManager taskManager = TaskManager.getInstance();
        List<Task> sharedTaskList = new ArrayList<>();
        taskManager.setTasks(sharedTaskList);
        
        // Call the method under test
        String choice = App.promptUserForChoice();
        
        // Verify the result
        Assertions.assertEquals("T", choice);
    }

    @Test
    void testHandleUserChoice() {
        // Test case 1: Create new task
        boolean shouldExit = App.handleUserChoice("T");
        Assertions.assertFalse(shouldExit);

        // Test case 2: Prioritize task
        shouldExit = App.handleUserChoice("P");
        Assertions.assertFalse(shouldExit);

        // Test case 3: Manage task duration
        shouldExit = App.handleUserChoice("M");
        Assertions.assertFalse(shouldExit);

        // Test case 4: Exit program
        shouldExit = App.handleUserChoice("E");
        Assertions.assertTrue(shouldExit);

        // Test case 5: Invalid choice
        shouldExit = App.handleUserChoice("X");
        Assertions.assertFalse(shouldExit);
    }

    @Test
    void testCreateNewTask() throws IOException {
        // Prepare test data
        List<Task> sharedTaskList = new ArrayList<>();
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.setTasks(sharedTaskList);

        // Mock user input
        NamedTypedValue<String> taskName = new NamedTypedValue<>("String", "userInput", "Test Task");
        NamedTypedValue<String> taskDescription = new NamedTypedValue<>("String", "userInput", "This is a test task");
        InputHandler.registerMockInput(taskName);
        InputHandler.registerMockInput(taskDescription);

        // Call the method under test
        App.createNewTask();

        // Verify the result
        Assertions.assertEquals(1, sharedTaskList.size());
        Task createdTask = sharedTaskList.get(0);
        Assertions.assertEquals("Test Task", createdTask.getTaskName());
        Assertions.assertEquals("This is a test task", createdTask.getDescription());
    }
}
