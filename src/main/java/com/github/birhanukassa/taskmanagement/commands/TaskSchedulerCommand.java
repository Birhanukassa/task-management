// Package declaration
package com.github.birhanukassa.taskmanagement.commands;

// import statements
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.TimePeriod;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;
/**
 * This class provides a command to set the time period for a task.
 * 
 *  It takes the selected task, start date, end date, start time, end time, and interval as input
 *  and sets the time period for the task using the TimePeriod.Builder class.
 */
public class TaskSchedulerCommand {
    
    private TaskSchedulerCommand() {
        // private constructor to prevent instantiation
    }
    
    /**
     * Logger instance for logging messages related to TaskSchedulerCommand.
     */
    private static final Logger logger = Logger.getLogger(TaskSchedulerCommand.class.getName());

    /**
     * Sets the time period for the selected task.
     *
     * @param selectedTask      The task for which the time period needs to be set.
     * @param startingDate      The start date for the task.
     * @param endingDate        The end date for the task.
     * @param startingTime      The start time for the task.
     * @param endingTime        The end time for the task.
     * @param intervalInput     The interval for the task in minutes.
     * 
     * 
     * TaskSchedulerCommand is responsible for setting the time period for a task.
     * It uses a builder pattern to create a TimePeriod object and sets it on the selected task.
     * The method performs input validation and logs appropriate messages for invalid inputs.
     */
    public static void setTimePeriod(
        Task selectedTask, LocalDate startingDate, LocalDate endingDate, LocalTime startingTime, LocalTime endingTime, int intervalInput) {
        
        if (startingDate == null) {
            logger.warning("You need to choose starting date first");
            return;
        }
    
        TimePeriod.Builder builder = new TimePeriod.Builder();
        builder.withStartDate(startingDate);

        if (startingTime != null) {
            builder.withStartTime(startingTime);
            if (endingTime != null) builder.withEndTime(endingTime);
            
        } else {
            if (endingTime != null) {
                logger.warning("You need to choose starting time first");
                return;
            }
        }

        if (endingDate != null) builder.withEndDate(endingDate);
        if (intervalInput > 0) {
            builder.withInterval(intervalInput);
        } else {
            logger.info("Interval need to be more than 0");
        }

        // Set the time period for the selected task
        selectedTask.setTimePeriod(builder.build());
    }
}


