package com.github.birhanukassa.taskmanagement.commands;

import java.util.logging.Logger;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.InputHandler;

public class PriorityQueueCommand implements TaskCommand<Task> {
    private static final Logger LOGGER = Logger.getLogger(PriorityQueueCommand.class.getName());
    InputHandler inputHandler = new InputHandler();
    NamedTypedValue<Integer> userInput;

    @Override
    public void execute(Task task) {
        double priorityLevel;
        Integer urgency;
        Integer importance;

        try {
            importance = inputHandler.getValidatedNumberInput("Rate how important the task is (1-10): ", 1, 10);
            urgency = inputHandler.getValidatedNumberInput("Rate how urgent the task is (1-10): ", 1, 10);
            priorityLevel = calculatePriorityLevel(importance, urgency);
            task.setPriorityScore(priorityLevel);
            LOGGER.info(() -> String.format("The calculated priority score is: %.2f", priorityLevel));
        } catch (Exception e) {
            LOGGER.severe("Error: " + e.getMessage());
        }
    }

    private double calculatePriorityLevel(int importance, int urgency) {
        return (importance * 2) + (double) urgency;
    }

    static {
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
    }
}



