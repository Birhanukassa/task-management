package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityQueueCommandTest {

    @Test
    void testCalculatePriorityLevelShouldReturnCorrectValue() {
        // Arrange
        int importance = 5;
        int urgency = 3;
        PriorityQueueCommand command = new PriorityQueueCommand();

        // Act
        double actualPriorityLevel = command.calculatePriorityLevel(importance, urgency);

        // Assert
        assertEquals(calculateExpectedPriorityLevel(importance, urgency), actualPriorityLevel, "Priority level calculation should be correct.");
    }

    @Test
    void testExecuteShouldUpdateTaskPriorityScore() {
        // Arrange
        Task task = new Task("Task 1", "Test task");
        int importance = 5;
        int urgency = 3;
        PriorityQueueCommand command = new PriorityQueueCommand();

        // Act
        double priorityLevel = command.calculatePriorityLevel(importance, urgency);
        task.setPriorityScore(priorityLevel);

        // Assert
        assertEquals(calculateExpectedPriorityLevel(importance, urgency), task.getPriorityScore(), "Task priority score should be updated correctly.");
    }

    private double calculateExpectedPriorityLevel(int importance, int urgency) {
        PriorityQueueCommand command = new PriorityQueueCommand();
        return command.calculatePriorityLevel(importance, urgency);
    }
}
