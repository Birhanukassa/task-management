package com.github.birhanukassa.taskmanagement.models;

import com.github.birhanukassa.taskmanagement.util.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private TimePeriod timePeriod;
    
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
    }

    public Task(String csvString) {
        Map<String, String> taskProperties = parseTaskPropertiesFromCsv(csvString);
        populateTaskFromProperties(taskProperties);
    }

    private Map<String, String> parseTaskPropertiesFromCsv(String csvString) {
        Map<String, String> taskProperties = new HashMap<>();
        String[] parts = csvString.split(",");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                taskProperties.put(key, value);
            }
        }
        return taskProperties;
    }

    private void populateTaskFromProperties(Map<String, String> taskProperties) {
    TimePeriod.Builder builder = new TimePeriod.Builder();

    for (Map.Entry<String, String> entry : taskProperties.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        processTaskProperty(key, value, builder);
    }

    this.status = (this.status == null) ? TaskStatus.NEW : this.status;
}

    private void processTaskProperty(String key, String value, TimePeriod.Builder builder) {
        switch (key) {
            case "name":
                processNameProperty(value);
                break;
            case "description":
                processDescriptionProperty(value);
                break;
            case "importance":
                processImportanceProperty(value);
                break;
            case "urgency":
                processUrgencyProperty(value);
                break;
            case "priorityLevel":
                processPriorityLevelProperty(value);
                break;
            case "status":
                processStatusProperty(value);
                break;
            case "startDate":
                processStartDateProperty(value, builder);
                break;
            case "endDate":
                processEndDateProperty(value, builder);
                break;
            case "startTime":
                processStartTimeProperty(value, builder);
                break;
            case "endTime":
                processEndTimeProperty(value, builder);
                break;
            case "interval":
                processIntervalProperty(value, builder);
                break;
            default:
                // create a default clause
                break;
        }
    }

    private void processNameProperty(String value) {
        if (!value.isEmpty()) {
            this.name = value;
        }
    }

    private void processDescriptionProperty(String value) {
        if (!value.isEmpty()) {
            this.description = value;
        }
    }

    private void processImportanceProperty(String value) {
        if (!value.isEmpty()) {
            this.importance = Integer.parseInt(value);
        }
    }

    private void processUrgencyProperty(String value) {
        if (!value.isEmpty()) {
            this.urgency = Integer.parseInt(value);
        }
    }

    private void processPriorityLevelProperty(String value) {
        if (!value.isEmpty()) {
            this.priorityLevel = Double.parseDouble(value);
        }
    }

    private void processStatusProperty(String value) {
        if (!value.isEmpty()) {
            this.status = TaskStatus.valueOf(value.toUpperCase());
        }
    }

    private void processStartDateProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withStartDate(LocalDate.parse(value));
        }
    }

    private void processEndDateProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withEndDate(LocalDate.parse(value));
        }
    }

    private void processStartTimeProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withStartTime(LocalTime.parse(value));
        }
    }

    private void processEndTimeProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withEndTime(LocalTime.parse(value));
        }
    }

    private void processIntervalProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withInterval(Integer.parseInt(value));
        }
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

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public TimePeriod getTimePeriod(){
        return timePeriod;
    }

    public void setStartDate(LocalDate startDate) {
        timePeriod.setStartDate(startDate);
    }
    public LocalDate getStartDate() {
        return timePeriod.getStartDate();
    }

    public void setEndDate(LocalDate date) {
        timePeriod.setEndDate(date);
    }

    public LocalDate getEnDate() {
        return timePeriod.getEndDate();
    }

    public void setStartTime(LocalTime timeStart) {
        timePeriod.setStartTime(timeStart);
    }
    public LocalTime getStartTime() {
        return timePeriod.getStartTime();
    }

    public void setEndTime(LocalTime endTime) {
        timePeriod.setEndTime(endTime);
    }

    public LocalTime getEndTime() {
        return timePeriod.getEndTime();
    }
   
    /**
     * Returns a string representation of the initialized fields of the Task object.
     *
     * @return a string with field names and values separated by newlines
     */
    public  <U> List<NamedTypedValue<Object>> getFieldValues() {
        return FieldValueMapper.getInitializedVars(this);
    }


	public void setInterval(int interval) {
        timePeriod.getInterval();
	}

    public int getInterval() {
        return timePeriod.getInterval();
    }

    
    @Override
    public String toString() {
        
        System.out.println("\nTask details:\n=============\n");

        return getFieldValues()
            .stream()
            .map(nv -> nv.getName() + ": " + nv.getValue())
            .collect(Collectors.joining("\n"));
    }
}
