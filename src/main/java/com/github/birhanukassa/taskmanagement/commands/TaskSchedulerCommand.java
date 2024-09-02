package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.TimePeriod;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;
public class TaskSchedulerCommand {
    
    private TaskSchedulerCommand() {
        // private constructor to prevent instantiation
    }
    
    private static final Logger logger = Logger.getLogger(TaskSchedulerCommand.class.getName());
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


