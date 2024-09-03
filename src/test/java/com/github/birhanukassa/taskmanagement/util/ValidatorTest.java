package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


/**
 * This class contains unit tests for the Validator class in the task management application.
 * It tests various validation methods for different data types and formats.
 */
class ValidatorTest {
    /**
     * Test the Validator.test method with a valid string input.
     * Expected behavior: The method should return true for a non-null string.
     */
    @Test
    void testTestWithValidString() {
        Assertions.assertTrue(Validator.test("valid string", String.class));
    }

    /**
     * Test the Validator.test method with a null string input.
     * Expected behavior: The method should return false for a null string.
     */
    @Test
    void testTestWithNullString() {
        Assertions.assertFalse(Validator.test(null, String.class));
    }

    /**
     * Test the Validator.test method with a valid integer input.
     * Expected behavior: The method should return true for a non-null integer.
     */
    @Test
    void testTestWithValidInteger() {
        Assertions.assertTrue(Validator.test(42, Integer.class));
    }

    /**
     * Test the Validator.test method with a null integer input.
     * Expected behavior: The method should return false for a null integer.
     */
    @Test
    void testTestWithNullInteger() {
        Assertions.assertFalse(Validator.test(null, Integer.class));
    }

    /**
     * Test the Validator.test method with a valid double input.
     * Expected behavior: The method should return true for a non-null double.
     */
    @Test
    void testTestWithValidDouble() {
        Assertions.assertTrue(Validator.test(3.14, Double.class));
    }

    /**
     * Test the Validator.test method with a null double input.
     * Expected behavior: The method should return false for a null double.
     */
    @Test
    void testTestWithNullDouble() {
        Assertions.assertFalse(Validator.test(null, Double.class));
    }

    /**
     * Test the Validator.test method with valid future date and time inputs.
     * This test uses Mockito to mock static methods of the Validator class.
     * Expected behavior: The method should return true for future dates and times.
     */
    @Test
    void testTestWithValidFutureDateAndTime() {
        LocalTime futureTime1 = LocalTime.now().plusHours(4);
        LocalTime invalidTime1 = LocalTime.now().minusHours(1);
        LocalTime validTime2 = LocalTime.now().plusHours(1);
        LocalTime invalidTime2 = LocalTime.now().minusHours(100);

        try (MockedStatic<Validator> mockedValidator = Mockito.mockStatic(Validator.class)) {
            // Mock the isValidTime method to return expected results
            mockedValidator.when(() -> Validator.isValidTime(invalidTime1)).thenReturn(false);
            mockedValidator.when(() -> Validator.isValidTime(invalidTime2)).thenReturn(false);
            mockedValidator.when(() -> Validator.isValidTime(futureTime1)).thenReturn(true);
            mockedValidator.when(() -> Validator.isValidTime(validTime2)).thenReturn(true);
        }

        LocalDate futureDate = LocalDate.now().plusDays(3);
        Assertions.assertTrue(Validator.test(futureDate, LocalDate.class));
    }

    /**
     * Test the Validator.test method with invalid past date and time inputs.
     * Expected behavior: The method should return false for past dates and times.
     */
    @Test
    void testTestWithInvalidDateAndTime() {
        LocalTime pastTime = LocalTime.now().minusHours(1);
        LocalDate pastDate = LocalDate.now().minusDays(1);
        Assertions.assertFalse(Validator.test(pastTime, LocalTime.class));
        Assertions.assertFalse(Validator.test(pastDate, LocalDate.class));
    }

    /**
     * Test the Validator.isValidDatePattern method with various date strings.
     * Expected behavior: The method should return true for valid date patterns and false for invalid ones.
     */
    @Test
    void testIsValidDatePattern() {
        List<String> validDates = Arrays.asList("01/01/2026", "12/28/2027", "06/09/4026", "02/29/2024", "01/01/0001");
        List<String> invalidDates = Arrays.asList("", "2029-10-01", "2023-01", "01/2026", "invalid-input", "33/12/2023", "2023/12/31", "12/31/9999");

        for (String date : validDates) {
            Assertions.assertTrue(Validator.isValidDatePattern(date), "Expected valid date pattern: " + date);
        }

        for (String date : invalidDates) {
            Assertions.assertFalse(Validator.isValidDatePattern(date), "Expected invalid date pattern: " + date);
        }
    }

    /**
     * Test the Validator.isValidTimePattern method with various time strings.
     * Expected behavior: The method should return true for valid time patterns and false for invalid ones.
     */
    @Test
    void testIsValidTimePattern() {
        List<String> validTimes = Arrays.asList("12:34", "12:34", "00:00", "23:39", "00:00", "23:59");
        List<String> invalidTimes = Arrays.asList("25:34:00", "24:00", "23:61", "", "invalid-input", "-25:34", "12:34:56 PM", "12:34:56.789", "24:01:00", "12:60:00", "12:34:60");

        for (String time : validTimes) {
            Assertions.assertTrue(Validator.isValidTimePattern(time), "Expected valid time pattern: " + time);
        }

        for (String time : invalidTimes) {
            Assertions.assertFalse(Validator.isValidTimePattern(time), "Expected invalid time pattern: " + time);
        }
    }

    /**
     * Test the Validator.isValidDate method.
     * Expected behavior: The method should return true for today's date and false for a past date.
     */
    @Test
    void testIsValidDate() {
        Assertions.assertTrue(Validator.isValidDate(LocalDate.now()));
        Assertions.assertFalse(Validator.isValidDate(LocalDate.now().minusDays(1)));
    }
}
