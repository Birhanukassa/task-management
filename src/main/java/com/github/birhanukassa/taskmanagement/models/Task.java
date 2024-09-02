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
    private String name = "";
    private String description = "";
    private int importance = 0;
    private int urgency = 0;
    private double priorityLevel = 0;
    private TimePeriod timePeriod;
    
    /**
     * @param name        the name of the task
     * @param description the description of the task
     */
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.timePeriod = new TimePeriod.Builder().build();
    }

    public Task(String csvString) {
        Map<String, String> taskProperties = parseTaskPropertiesFromCsv(csvString);
        populateTaskFromProperties(taskProperties);
    }

    // parses task properties from csv file 
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

    // populates task fields from task properties
    private void populateTaskFromProperties(Map<String, String> taskProperties) {
        TimePeriod.Builder builder = new TimePeriod.Builder();

        for (Map.Entry<String, String> entry : taskProperties.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            processTaskProperty(key, value, builder);
        }

        if (builder.build() != null) {
            this.timePeriod = builder.build();
        } else {
            this.timePeriod = new TimePeriod(builder);
        }
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
                System.out.println("Unknown task property: " + key);
                break;
        }
    }

    private void processStartDateProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withStartDate(LocalDate.parse(value));
    }
    private void processNameProperty(String value) {
        if (!value.isEmpty()) this.name = value;
    }

    private void processDescriptionProperty(String value) {
        if (!value.isEmpty()) this.description = value;
    }

    private void processImportanceProperty(String value) {
        if (!value.isEmpty()) this.importance = Integer.parseInt(value);
    }

    private void processUrgencyProperty(String value) {
        if (!value.isEmpty()) this.urgency = Integer.parseInt(value);
    }

    private void processPriorityLevelProperty(String value) {
        if (!value.isEmpty()) this.priorityLevel = Double.parseDouble(value);
    }

    private void processEndDateProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withEndDate(LocalDate.parse(value));
    }

    private void processStartTimeProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withStartTime(LocalTime.parse(value));
        }
    }

    private void processEndTimeProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withEndTime(LocalTime.parse(value));
        
    }

    private void processIntervalProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withInterval(Integer.parseInt(value));
    }

    /**
     * Represents the status of a task.
     */
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

    public LocalDate getEndDate() {
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

    public List<NamedTypedValue<Object>> getFieldValues() {
        return FieldValueMapper.getInitializedVars(this);
    }

    @Override
    public String toString() {
        return "Task details\n============\n" +
            getFieldValues()
            .stream()
            .map(nv -> nv.getName() + ": " + nv.getValue())
            .collect(Collectors.joining("\n")) + "\n";
    }
}
