// Package declaration
package com.github.birhanukassa.taskmanagement.commands;

// import statements
import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;

import java.util.logging.Logger;


/**
 * This class implements the TaskCommand interface and is responsible for calculating
 * the priority score for a given task based on the importance and urgency factors.
 */
public class PriorityQueueCommand implements TaskCommand<Task> {
    private static final Logger LOGGER = Logger.getLogger(PriorityQueueCommand.class.getName());
    // Static initializer block to register the Integer type converter for the InputHandler or future added converters 
    static {
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
    }

    public PriorityQueueCommand() {
        // Private constructor to prevent instantiation of this class
    }
  
    /**
     * Executes the command to calculate the priority score for the given task.
     * It prompts the user to rate the importance and urgency of the task,
     * calculates the priority score using the calculatePriorityLevel method,
     * and sets the priority score on the task object.
     *
     * @param task The task for which the priority score needs to be calculated.
     * @return void
     * @throws Exception If any error occurs during the execution of the command.
     * @see InputHandler#getUserInput(String, Class)
     */
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

    /**
     * Calculates the priority score based on the importance and urgency factors.
     * The priority score is a weighted sum of the importance and urgency values.
     *
     * @param importance The importance factor (1-10).
     * @param urgency The urgency factor (1-10).
     * @return The calculated priority score.
     */
    double calculatePriorityLevel(int importance, int urgency) {
        return (importance * 2) + (double) urgency;
    }
}
