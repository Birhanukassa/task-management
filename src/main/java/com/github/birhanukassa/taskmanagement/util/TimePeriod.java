package com.github.birhanukassa.taskmanagement.util;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;

public class TimePeriod {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer interval;

    public TimePeriod(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.interval = builder.interval;
    }

    public static class Builder {
        private LocalDate startDate = null;
        private LocalDate endDate = null;
        private LocalTime startTime = null;
        private LocalTime endTime = null;
        private Integer interval = 0;

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

    public static LocalDate parseLocalDate(String input) {
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public static LocalTime parseLocalTime(String input) {
        return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public List<NamedTypedValue<Object>> getFieldValues() {
        return FieldValueMapper.getInitializedVars(this);
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}


/*
[
    [ 'startingDate', 'endingDate', 'startingTime',  'endingTime',    interval ],   
    [ 'startingDate', 'endingDate', 'startingTime',  'endingTime',    null ],
    [ 'startingDate', 'endingDate', 'startingTime',  null,           'interval' ],
    [ 'startingDate', 'endingDate', 'startingTime',  null,           null ],
    [ 'startingDate', 'endingDate', null,            null,          'interval' ],
    [ 'ingDate', 'endingDate', null,            null,           null ],
    [ 'startingDate',  null,       'startingTime',  'endingTime', '  interval' ],
    [ 'startingDate',  null,       'startingTime',  'endingTime',    null ],
    [ 'startingDate',  null,       'startingTime',  null,          'interval' ],
    [ 'startingDate',  null,       'startingTime',  null,           null ],

    [ 'startingDate',  null,        null,          null,           'interval' ],
    [ 'startingDate',  null,        null,          null,            null ],
]
*/