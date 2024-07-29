package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class TimePeriodTest {

    @Test
    void testConstructor() {
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
    void testSetAndGetStartDate() {
        LocalDate startDate = LocalDate.now();
        TimePeriod timePeriod = new TimePeriod.Builder().build();
        timePeriod.setStartDate(startDate);
        Assertions.assertEquals(startDate, timePeriod.getStartDate());
    }

    @Test
    void testSetAndGetEndDate() {
        LocalDate endDate = LocalDate.now().plusDays(7);
        TimePeriod timePeriod = new TimePeriod.Builder().build();
        timePeriod.setEndDate(endDate);
        Assertions.assertEquals(endDate, timePeriod.getEndDate());
    }

}