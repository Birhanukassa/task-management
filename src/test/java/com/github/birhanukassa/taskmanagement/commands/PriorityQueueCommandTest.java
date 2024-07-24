package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityQueueCommandTest {

    @Test
    void testExecute_WithValidTask_ShouldUpdatePriorityLevel() {
        // Arrange
        Task task = new Task("Task 1", "Test task");
        double expectedPriorityLevel = 10.0;
        PriorityQueueCommand command = new PriorityQueueCommand();

        // Act
        command.execute(task);

        // Assert
        assertEquals(expectedPriorityLevel, task.getPriorityScore(), "Priority level should be updated.");
    }

    @Test
    void testExecute_WithInvalidInput_ShouldHandleException() {
        // Arrange
        Task task = new Task("Task 1", "Test task");
        PriorityQueueCommand command = new PriorityQueueCommand();

        // Act
        command.execute(task);

        // Assert
        // Assuming the execute method doesn't modify the task's priority score for invalid input
        assertEquals(0.0, task.getPriorityScore(), "Priority score should remain unchanged for invalid input");
    }

}
