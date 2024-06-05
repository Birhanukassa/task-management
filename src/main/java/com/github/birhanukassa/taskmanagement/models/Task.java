package com.github.birhanukassa.taskmanagement.models;

import com.github.birhanukassa.taskmanagement.util.*;

import java.util.stream.Collectors;

/**
 * Represents a task with its details, status, and priority.
 */
public class Task {
    private String name;
    private String description;

    private int importance;
    private int urgency ;
    private double priorityLevel = 0;

    private TaskStatus status;

    private TaskLocation location;
    private TimePeriod taskTime;
    private TimePeriod timePeriod;

    private boolean isCompleted;

    /**
     * Constructs a Task object with a name and description.
     *
     * @param name        the name of the task
     * @param description the description of the task
     */
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
        this.isCompleted = false;
    }

    /**
     * Constructs a Task object with only a name.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this(name, null);
    }

    /**
     * Represents the status of a task.
     */
    public enum TaskStatus {
        NEW,
        IN_PROGRESS,
        ON_HOLD,
        COMPLETED,
        CANCELLED
    }

    public void setTaskName(String name) {
        this.name = name;
    }

    public String getTaskName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPriorityScore(double priorityScore) {
        this.priorityLevel = priorityScore;
    }

    public double getPriorityScore() {
        return priorityLevel;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }

    public void setUrgency(int urgency ) {
        this.urgency  = urgency ;
    }

    public int getUrgency() {
        return urgency ;
    }

    public void setTaskTime(TimePeriod taskTime) {
        this.taskTime = taskTime;
    }

    public TimePeriod getTaskTime() {
        return taskTime;
    }

    public void setLocation(TaskLocation location) {
        this.location = location;
    }

    public TaskLocation getLocation() {
        return location;
    }

   
    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    @Override
    public String toString() {
        return getFieldValues();
    }
    /**
     * Represents the location details of a task.
     */
    public static class TaskLocation {
        private String location;

        // Constructors, getters, and setters for TaskLocation
    }

    /**
     * Returns a string representation of the initialized fields of the Task object.
     *
     * @return a string with field names and values separated by newlines
     */
    private String getFieldValues() {
        return FieldValueMapper.getInitializedVars(this)
                .stream()
                .map(nv -> nv.getName() + ": " + nv.getValue())
                .collect(Collectors.joining("\n"));
    }
    
}
