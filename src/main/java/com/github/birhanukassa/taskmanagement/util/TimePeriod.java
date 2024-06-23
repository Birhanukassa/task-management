package com.github.birhanukassa.taskmanagement.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a time period with start and end dates/times, and an optional interval.
 */
public class TimePeriod {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int interval;

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int interval) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime, int interval) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate, LocalTime startTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate, int interval) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalTime startTime, LocalTime endTime, int interval) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     */
    public TimePeriod(LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalTime startTime, int interval) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param endDate      the end date of the time period
     * @param startTime    the start time of the time period
     * @param endTime      the end time of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, LocalTime startTime) {
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     * @param invalid     the interval between start and end times
     */
    public TimePeriod(LocalDate startDate, int interval) {
        this.startDate = startDate;
        this.interval = interval;
    }

    /**
     * Constructs a TimePeriod object with start date, end date, start time, end time, and interval.
     *
     * @param startDate    the start date of the time period
     */
    public TimePeriod(LocalDate startDate) {
        this.startDate = startDate;
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