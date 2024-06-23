package com.github.birhanukassa.taskmanagement.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimePeriod {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int interval;

    private TimePeriod(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.interval = builder.interval;
    }

    public static class Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalTime startTime;
        private LocalTime endTime;
        private int interval;

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

        public Builder withInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public TimePeriod build() {
            return new TimePeriod(this);
        }
    }

    // Getters and setters
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", interval=" + interval +
                '}';
    }
}


/*
[
    [ 'startingDate', 'endingDate', 'startingTime',  'endingTime',    interval ],   
    [ 'startingDate', 'endingDate', 'startingTime',  'endingTime',    null ],
    [ 'startingDate', 'endingDate', 'startingTime',  null,           'interval' ],
    [ 'startingDate', 'endingDate', 'startingTime',  null,           null ],
    [ 'startingDate', 'endingDate', null,            null,          'interval' ],
    [ 'startingDate', 'endingDate', null,            null,           null ],
    [ 'startingDate',  null,       'startingTime',  'endingTime', '  interval' ],
    [ 'startingDate',  null,       'startingTime',  'endingTime',    null ],
    [ 'startingDate',  null,       'startingTime',  null,          'interval' ],
    [ 'startingDate',  null,       'startingTime',  null,           null ],

    [ 'startingDate',  null,        null,          null,           'interval' ],
    [ 'startingDate',  null,        null,          null,            null ],
]
*/