package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.DateTimeValidator;
import com.github.birhanukassa.taskmanagement.util.TimePeriod;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TaskSchedulerCommand {
    public static void setTimePeriod(
        Task selectedTask, LocalDate startingDate, LocalTime startingTime, LocalTime endingTime, LocalDate intervalInput) {
        boolean startDateValid = DateTimeValidator.isValidDate(startingDate);
        boolean startTimeValid = DateTimeValidator.isValidTime(startingTime);
        boolean endTimeValid = DateTimeValidator.isValidTime(endingTime);
        boolean intervalValid = DateTimeValidator.isValidDate(intervalInput);

        LocalDate startDate = startDateValid ? startingDate : null;
        LocalTime timeStart = startTimeValid ? startingTime : null;
        LocalTime timeEnd = endTimeValid ? endingTime : null;
        LocalDate interval = intervalValid ? intervalInput : null;

        if (!startDateValid) {
            // Handle invalid start date case
            return;
        }

        TaskSchedulerCommand.setTimePeriod(selectedTask, startDate, timeStart, timeEnd, interval);
    }

    public TimePeriod.Interval parseInterval(String intervalString) {
        String[] parts = intervalString.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        int value = Integer.parseInt(parts[0]);
        ChronoUnit unit = ChronoUnit.valueOf(parts[1].toUpperCase());
        return new TimePeriod.Interval(value, unit);
    }

    public TimePeriod.Interval scheduleTask(Task task, String intervalString) {
        // Schedule the task using the provided TimePeriod
        // Implement your scheduling logic here
        // You can use a scheduling library like Quartz or a custom implementation

        String[] parts = intervalString.split("\\D+");
        int value = Integer.parseInt(parts[0]);
        ChronoUnit unit = ChronoUnit.valueOf(parts[1].toUpperCase());
        return new TimePeriod.Interval(value, unit);
    }
}