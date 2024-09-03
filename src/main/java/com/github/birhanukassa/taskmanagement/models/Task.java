// Package declaration
package com.github.birhanukassa.taskmanagement.models;
import com.github.birhanukassa.taskmanagement.util.*;

// imports to bring in necessary classes for dates, times, logging and other relatives 
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

    // constructor to create tasks from a csv string
    /**
     * @param csvString the csv string to parse the task from
     */
    public Task(String csvString) {
        Map<String, String> taskProperties = parseTaskPropertiesFromCsv(csvString);
        populateTaskFromProperties(taskProperties);
    }

    // parses task properties from csv file 
    /**
     * @param csvString the csv string to parse the task properties from
     * @return a map of task properties
     */
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

    // populates task from properties
    /**
     * @param taskProperties the task properties to populate the task from
     */
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

    // processes task properties
    /**
     * @param key   the key of the task property
     * @param value the value of the task property
     * @param builder the builder to populate the time period
     */
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
    
    // processes start date property
    /**
     * @param value the value of the start date property
     * @param builder the builder to populate the time period
     */
    private void processStartDateProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withStartDate(LocalDate.parse(value));
    }
    // processes name property
    /**
     * @param value the value of the name property
     */
    private void processNameProperty(String value) {
        if (!value.isEmpty()) this.name = value;
    }
    // processes description property
    /**
     * @param value the value of the description property
     */

    private void processDescriptionProperty(String value) {
        if (!value.isEmpty()) this.description = value;
    }
    // processes importance property
    /**
     * @param value the value of the importance property
     */

    private void processImportanceProperty(String value) {
        if (!value.isEmpty()) this.importance = Integer.parseInt(value);
    }
    // processes urgency property
    /**
     * @param value the value of the urgency property
     */
    private void processUrgencyProperty(String value) {
        if (!value.isEmpty()) this.urgency = Integer.parseInt(value);
    }
    // processes priority level property
    /**
     * @param value the value of the priority level property
     */
    private void processPriorityLevelProperty(String value) {
        if (!value.isEmpty()) this.priorityLevel = Double.parseDouble(value);
    }
    // processes end date property
    /**
     * @param value the value of the end date property
     * @param builder the builder to populate the time period
     */
    private void processEndDateProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withEndDate(LocalDate.parse(value));
    }
    // processes start time property
    /**
     * @param value the value of the start time property
     * @param builder the builder to populate the time period
     */
    private void processStartTimeProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) {
            builder.withStartTime(LocalTime.parse(value));
        }
    }
    // processes end time property
    /**
     * @param value the value of the end time property
     * @param builder the builder to populate the time period
     */
    private void processEndTimeProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withEndTime(LocalTime.parse(value));
    }
    // processes interval property
    /**
     * @param value the value of the interval property
     * @param builder the builder to populate the time period
     */
    private void processIntervalProperty(String value, TimePeriod.Builder builder) {
        if (!value.isEmpty()) builder.withInterval(Integer.parseInt(value));
    }

    // setters and getters
    /**
     * @param name the name to set
     */
    public void setTaskName(String name) {
        this.name = name;
    }
    // gets task name
    /**
     * @return the task name
     */
    public String getTaskName() {
        return name;
    }
    // sets task description
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    // gets task description
    /**
     * @return the task description
     */
    public String getDescription() {
        return description;
    }
    // sets priority score
    /**
     * @param priorityScore the priority score to set
     */
    public void setPriorityScore(double priorityScore) {
        this.priorityLevel = priorityScore;
    }
    // gets priority score
    /**
     * @return the priority score
     */
    public double getPriorityScore() {
        return priorityLevel;
    }
    // sets importance
    /**
     * @param importance the importance to set
     */
    public void setImportance(int importance) {
        this.importance = importance;
    }
    // gets importance
    /**
     * @return the importance
     */
    public int getImportance() {
        return importance;
    }
    // sets urgency
    /**
     * @param urgency the urgency to set
     */
    public void setUrgency(int urgency ) {
        this.urgency  = urgency ;
    }
    // gets urgency
    /**
     * @return the urgency
     */
    public int getUrgency() {
        return urgency ;
    }
    // sets time period
    /**
     * @param timePeriod the time period to set
     */
    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }
    // gets time period
    /**
     * @return the time period
     */
    public TimePeriod getTimePeriod(){
        return timePeriod;
    }
    // sets start date
    /**
     * @param startDate the start date to set
     */
    public void setStartDate(LocalDate startDate) {
        timePeriod.setStartDate(startDate);
    }
    // gets start date
    /**
     * @return the start date
     */
    public LocalDate getStartDate() {
        return timePeriod.getStartDate();
    }
    // sets end date
    /**
     * @param date the end date to set
     */
    public void setEndDate(LocalDate date) {
        timePeriod.setEndDate(date);
    }
    // gets end date
    /**
     * @return the end date
     */
    public LocalDate getEndDate() {
        return timePeriod.getEndDate();
    }
    // sets start time
    /**
     * @param timeStart the start time to set
     */
    public void setStartTime(LocalTime timeStart) {
        timePeriod.setStartTime(timeStart);
    }
    // gets start time
    /**
     * @return the start time
     */
    public LocalTime getStartTime() {
        return timePeriod.getStartTime();
    }
    // sets end time
    /**
     * @param endTime the end time to set
     */
    public void setEndTime(LocalTime endTime) {
        timePeriod.setEndTime(endTime);
    }
    // gets end time
    /**
     * @return the end time
     */
    public LocalTime getEndTime() {
        return timePeriod.getEndTime();
    }
    // sets interval
    /**
     * @param interval the interval to set
     */
    public void setInterval(int interval) {
        timePeriod.setInterval(interval);
    }
    // gets interval
    /**
     * @return the interval
     */
    public int getInterval() {
        return timePeriod.getInterval();
    }
    // gets field values
    /**
     * @return the field values
     */
    public List<NamedTypedValue<Object>> getFieldValues() {
        return FieldValueMapper.getInitializedVars(this);
    }

    // converts the task to string representation of the task 
    /**
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "Task details\n============\n" +
            getFieldValues()
            .stream()
            .map(nv -> nv.getName() + ": " + nv.getValue())
            .collect(Collectors.joining("\n")) + "\n";
    }
}
