package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;

import java.util.logging.Logger;

public class PriorityQueueCommand implements TaskCommand<Task> {
    private static final Logger LOGGER = Logger.getLogger(PriorityQueueCommand.class.getName());

    static {
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
    }

    public PriorityQueueCommand() {
        // Private constructor to prevent instantiation of this class
    }

    @Override
    public void execute(Task task) {
        double priorityLevel;
        int urgency;
        int importance;

        try {
            importance = InputHandler.getUserInput("Rate how important the task is (1-10): ", Integer.class).getValue();
            urgency = InputHandler.getUserInput("Rate how urgent the task is (1-10): ", Integer.class).getValue();

            priorityLevel = calculatePriorityLevel(importance, urgency);
            task.setPriorityScore(priorityLevel);
            LOGGER.info(() -> String.format("The calculated priority score is: %.2f", priorityLevel));
        } catch (Exception e) {
            LOGGER.severe("Error: " + e.getMessage());
        }
    }

    double calculatePriorityLevel(int importance, int urgency) {
        return (importance * 2) + (double) urgency;
    }
}
