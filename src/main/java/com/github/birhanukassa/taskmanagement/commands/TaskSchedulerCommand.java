package com.github.birhanukassa.taskmanagement.commands;
import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.TimePeriod;
import com.github.birhanukassa.taskmanagement.util.TimePeriod.Interval;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TaskSchedulerCommand {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    public void setTimePeriod(Task task, String startDateString, String startTimeString, String endTimeString, Interval interval) {
        LocalDate startDate = LocalDate.parse(startDateString, DATE_FORMAT);
        LocalTime startTime = LocalTime.parse(startTimeString, TIME_FORMAT);
        LocalTime endTime = LocalTime.parse(endTimeString, TIME_FORMAT);

        TimePeriod timePeriod = new TimePeriod(startDate, null, startTime, endTime, interval);
        task.setTimePeriod(timePeriod);
    }

    public TimePeriod.Interval parseInterval(String intervalString) {
        String[] parts = intervalString.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        int value = Integer.parseInt(parts[0]);
        ChronoUnit unit = ChronoUnit.valueOf(parts[1].toUpperCase());
        return new TimePeriod.Interval(value, unit);
    }

    public void scheduleTask(Task task) {
        // Schedule the task using the provided TimePeriod

         // Implement your scheduling logic here

        // Schedule the task using the provided TimePeriod
        // You can use a scheduling library like Quartz or a custom implementation
        // You can use a scheduling library like Quartz or a custom implementation

        String[] parts = intervalString.split("\D+");
        int value = Integer.parseInt(parts[0]);
        ChronoUnit unit = ChronoUnit.valueOf(parts[1].toUpperCase());
        return new TimePeriod.Interval(value, unit);
        
    }
}


