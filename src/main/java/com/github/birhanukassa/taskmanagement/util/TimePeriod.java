// Package declaration
package com.github.birhanukassa.taskmanagement.util;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Importing necessary classes 
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
// Class definition 
/**
 * TimePeriod class represents a time period with start and end dates, start and end times, and an interval.
 * It provides methods to set and get the values of these fields.
 */
public class TimePeriod {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer interval;

    /**
     * Constructor for TimePeriod class.
     *
     * @param builder Builder object containing the values for the TimePeriod fields.
     */
    public TimePeriod(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.interval = builder.interval;
    }

    // Static nested class Builder
    /**
     * Builder class for creating TimePeriod objects.
     * Provides methods to set the values of the TimePeriod fields.
     */
    public static class Builder {
        private LocalDate startDate = null;
        private LocalDate endDate = null;
        private LocalTime startTime = null;
        private LocalTime endTime = null;
        private Integer interval = 0;

        // getter and setter methods for all fields 

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withInterval(Integer interval) {
            this.interval = interval;
            return this;
        }

        public TimePeriod build() {
            return new TimePeriod(this);
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * Utility method to parse a string into a LocalDate object.
     * @param input
     * @return
     */
    public static LocalDate parseLocalDate(String input) {
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    /**
     * Utility method to parse a string into a LocalTime object.
     * @param input
     * @return
     */
    public static LocalTime parseLocalTime(String input) {
        return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Returns a list of NamedTypedValue objects representing the fields and their values of the TimePeriod object.
     * @return List<NamedTypedValue<Object>>
     */
    public List<NamedTypedValue<Object>> getFieldValues() {
        return FieldValueMapper.getInitializedVars(this);
    }
}
