package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for TimePeriod functionality.
 * This class tests the construction, getters, and setters of the TimePeriod class.
 */
class TimePeriodTest {
    private LocalDate startDate, endDate;
    private LocalTime startTime, endTime;
    private int interval;

    /**
     * Sets up common test data before each test method.
     */
    @BeforeEach
    void setUp() {
        startDate = LocalDate.now();
        endDate = startDate.plusDays(7);
        startTime = LocalTime.now();
        endTime = startTime.plusHours(2);
        interval = 3;
    }

    /**
     * Tests the TimePeriod constructor using the Builder pattern.
     * Verifies that all fields are correctly set after construction.
     */
    @Test
    void testConstructor() {
        TimePeriod timePeriod = new TimePeriod.Builder()
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withStartTime(startTime)
                .withEndTime(endTime)
                .withInterval(interval)
                .build();

        assertAll(
            () -> assertEquals(startDate, timePeriod.getStartDate()),
            () -> assertEquals(endDate, timePeriod.getEndDate()),
            () -> assertEquals(startTime, timePeriod.getStartTime()),
            () -> assertEquals(endTime, timePeriod.getEndTime()),
            () -> assertEquals((long) interval, (long) timePeriod.getInterval())
        );
    }

    /**
     * Parameterized test for setters and getters of date and time fields.
     * @param date The date to test with
     * @param time The time to test with
     */
    @ParameterizedTest
    @MethodSource("dateTimeProvider")
    void testSettersAndGetters(LocalDate date, LocalTime time) {
        TimePeriod timePeriod = new TimePeriod.Builder().build();
        
        timePeriod.setStartDate(date);
        timePeriod.setStartTime(time);
        
        assertAll(
            () -> assertEquals(date, timePeriod.getStartDate()),
            () -> assertEquals(time, timePeriod.getStartTime())
        );
    }

    /**
     * Provides test data for the parameterized test.
     * @return List of arguments containing date and time pairs
     */
    static List<Arguments> dateTimeProvider() {
        return Arrays.asList(
            Arguments.of(LocalDate.now(), LocalTime.now()),
            Arguments.of(LocalDate.now().plusDays(1), LocalTime.NOON)
        );
    }
}
