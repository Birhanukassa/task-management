package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.util.TimePeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimePeriodTest {

    @Test
    public void testConstructor() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(7);
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(2);
        int interval = 3;

        TimePeriod timePeriod = new TimePeriod.Builder()
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withStartTime(startTime)
                .withEndTime(endTime)
                .withInterval(interval)
                .build();

        Assertions.assertEquals(startDate, timePeriod.getStartDate());
        Assertions.assertEquals(endDate, timePeriod.getEndDate());
        Assertions.assertEquals(startTime, timePeriod.getStartTime());
        Assertions.assertEquals(endTime, timePeriod.getEndTime());
        Assertions.assertEquals(interval, timePeriod.getInterval());
    }

    @Test
    public void testSetAndGetStartDate() {
        LocalDate startDate = LocalDate.now();
        TimePeriod timePeriod = new TimePeriod.Builder().build();
        timePeriod.setStartDate(startDate);
        Assertions.assertEquals(startDate, timePeriod.getStartDate());
    }

    @Test
    public void testSetAndGetEndDate() {
        LocalDate endDate = LocalDate.now().plusDays(7);
        TimePeriod timePeriod = new TimePeriod.Builder().build();
        timePeriod.setEndDate(endDate);
        Assertions.
