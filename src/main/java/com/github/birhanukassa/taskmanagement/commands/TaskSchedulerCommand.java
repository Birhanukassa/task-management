package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.DateTimeValidator;


import java.time.LocalDate;
import java.time.LocalTime;

public class TaskSchedulerCommand {
    public static void setTimePeriod(
        Task selectedTask, LocalDate startingDate, LocalTime startingTime, LocalTime endingTime, int intervalInput) {
            boolean startDateValid = DateTimeValidator.isValidDate(startingDate);
            boolean startTimeValid = DateTimeValidator.isValidTime(startingTime);
            boolean endTimeValid = DateTimeValidator.isValidTime(endingTime);
            boolean intervalValid = intervalInput > 0;

        if (!startDateValid) {
            // Handle invalid start date case
            return;
        }

        LocalTime timeStart = startTimeValid ? startingTime : null;
        LocalTime timeEnd = endTimeValid ? endingTime : null;
        int interval = intervalValid ? intervalInput : null;

        selectedTask.setStartDate(startingDate);
        selectedTask.setStartTime(timeStart);
        selectedTask.setEndTime(timeEnd);
        selectedTask.setInterval(interval);
    }

    public int parseInterval(String intervalString) {
        return Integer.parseInt(intervalString);
    }
}

