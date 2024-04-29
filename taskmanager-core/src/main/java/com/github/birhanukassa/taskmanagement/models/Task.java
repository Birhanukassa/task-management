package com.github.birhanukassa.taskmanagement.models;

import com.github.birhanukassa.taskmanagement.util.FieldValueMapper;


import java.time.ZonedDateTime;
import java.util.List;


public class Task {
    private String name;
    private String description;
    private TaskStatus status;
    private String location; // could move to different class

    private double priorityLevel = 0;

    private ZonedDateTime startTime; // could move to different class
    private ZonedDateTime intervalDate; // could move to different class
    private ZonedDateTime endTime; // could move to different class

    private boolean isCompleted;

    // public Task(String name) {
    //     this(name, null);
    // }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
        this.isCompleted = false;
    }

    public enum TaskStatus {
        NEW,
        IN_PROGRESS,
        COMPLETED,
        ON_HOLD,
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

    @Override
    public String toString() {
        List<NameValue> vars = FieldValueMapper.getInitializedVars(this);

        String output = "";

        for (NameValue nv : vars) {
            output += nv.getName() + ": " + nv.getValue() + "\n";
        }
        return output;
    }
}