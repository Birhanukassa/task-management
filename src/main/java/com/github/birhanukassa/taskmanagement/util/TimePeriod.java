package com.github.birhanukassa.taskmanagement.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents a time period with start and end dates/times, and an optional interval.
 */
public class TimePeriod {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Interval interval;

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     * @param interval     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, Interval interval) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date and start time.
     *
     * @param startDate the start date of the time period
     * @param startTime the start time of the time period
     */
    public TimePeriod(LocalDate startDate, LocalTime startTime) {
        this(startDate, null, startTime, null, null);
    }

    /**
     * Constructs a TimePeriod object with start time and end time.
     *
     * @param startTime the start time of the time period
     * @param endTime   the end time of the time period
     */
    public TimePeriod(LocalTime startTime, LocalTime endTime) {
        this(null, null, startTime, endTime, null);
    }

    /**
     * Constructs a TimePeriod object with start time, end time, and interval.
     *
     * @param startTime the start time of the time period
     * @param endTime   the end time of the time period
     * @param interval  the interval between start and end times
     */
    public TimePeriod(LocalTime startTime, LocalTime endTime, Interval interval) {
        this(null, null, startTime, endTime, interval);
    }

    /**
     * Constructs a TimePeriod object with start date and interval.
     *
     * @param startDate the start date of the time period
     * @param interval  the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, Interval interval) {
        this(startDate, null, null, null, interval);
    }

   
    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    /**
     * Represents an interval duration.
     */
    public static class Interval {
        private long value;
        private ChronoUnit unit;

        /**
         * Constructs an Interval object with a value and a time unit.
         *
         * @param value the value of the interval
         * @param unit  the time unit of the interval (e.g., DAYS, WEEKS, MONTHS, YEARS)
         */
        public Interval(long value, ChronoUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        public long getValue() {
            return value;
        }

        public ChronoUnit getUnit() {
            return unit;
        }

        /**
         * Returns the interval duration as a Duration object.
         *
         * @return the interval duration
         */
        public Duration toDuration() {
            return Duration.of(value, unit);
        }
    }
}

