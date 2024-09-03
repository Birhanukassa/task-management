// This package contains command classes 
package com.github.birhanukassa.taskmanagement.commands;


// Importing necessary classes for the test
import com.github.birhanukassa.taskmanagement.models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Importing necessary classes for the test
class PriorityQueueCommandTest {

    // Test to ensure the calculatePriorityLevel method returns the correct value
    @Test
    void testCalculatePriorityLevelShouldReturnCorrectValue() {
        // Set up test data
        // Arrange
        int importance = 5;
        int urgency = 3;
        PriorityQueueCommand command = new PriorityQueueCommand();

        // Call the method being tested

        // Act
        double actualPriorityLevel = command.calculatePriorityLevel(importance, urgency);
        
        // Verify the result matches the expected value
        // Assert
        assertEquals(calculateExpectedPriorityLevel(importance, urgency), actualPriorityLevel, "Priority level calculation should be correct.");
    }

    // Test to ensure the execute method updates the task's priority score correctly
    @Test
    void testExecuteShouldUpdateTaskPriorityScore() {
        // Set up test data
        // Arrange
        Task task = new Task("Task 1", "Test task");
        int importance = 5;
        int urgency = 3;
        PriorityQueueCommand command = new PriorityQueueCommand();

        // Calculate priority level and update task
        // Act
        double priorityLevel = command.calculatePriorityLevel(importance, urgency);
        task.setPriorityScore(priorityLevel);

        // Helper method to calculate the expected priority level
        // Assert
        assertEquals(calculateExpectedPriorityLevel(importance, urgency), task.getPriorityScore(), "Task priority score should be updated correctly.");
    }

    private double calculateExpectedPriorityLevel(int importance, int urgency) {
        PriorityQueueCommand command = new PriorityQueueCommand();
        return command.calculatePriorityLevel(importance, urgency);
    }
}
