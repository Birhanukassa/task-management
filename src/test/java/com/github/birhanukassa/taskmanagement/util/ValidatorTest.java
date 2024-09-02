package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    @Test
    void testTestWithValidString() {
        Assertions.assertTrue(Validator.test("valid string", String.class));
    }

    @Test
    void testTestWithNullString() {
        Assertions.assertFalse(Validator.test(null, String.class));
    }

    @Test
    void testTestWithValidInteger() {
        Assertions.assertTrue(Validator.test(42, Integer.class));
    }

    @Test
    void testTestWithNullInteger() {
        Assertions.assertFalse(Validator.test(null, Integer.class));
    }

    @Test
    void testTestWithValidDouble() {
        Assertions.assertTrue(Validator.test(3.14, Double.class));
    }

    @Test
    void testTestWithNullDouble() {
        Assertions.assertFalse(Validator.test(null, Double.class));
    }

    @Test
    void testTestWithValidFutureDateAndTime() {
        LocalTime futureTime1 = LocalTime.now().plusHours(4);
        LocalTime invalidTime1 = LocalTime.now().minusHours(1);
        LocalTime validTime2 = LocalTime.now().plusHours(1);
        LocalTime invalidTime2 = LocalTime.now().minusHours(100);

        try (MockedStatic<Validator> mockedValidator = Mockito.mockStatic(Validator.class)) {
            mockedValidator.when(() -> Validator.isValidTime(invalidTime1)).thenReturn(false);
            mockedValidator.when(() -> Validator.isValidTime(invalidTime2)).thenReturn(false);
            mockedValidator.when(() -> Validator.isValidTime(futureTime1)).thenReturn(true);
            mockedValidator.when(() -> Validator.isValidTime(validTime2)).thenReturn(true);
        }

        LocalDate futureDate = LocalDate.now().plusDays(3);
        Assertions.assertTrue(Validator.test(futureDate, LocalDate.class));
    }

    @Test
    void testTestWithInvalidDateAndTime() {
        LocalTime pastTime = LocalTime.now().minusHours(1);
        LocalDate pastDate = LocalDate.now().minusDays(1);
        Assertions.assertFalse(Validator.test(pastTime, LocalTime.class));
        Assertions.assertFalse(Validator.test(pastDate, LocalDate.class));
    }

    @Test
    void testIsValidDatePattern() {
        List<String> validDates = Arrays.asList("01/01/2026", "12/28/2027", "06/09/4026", "02/29/2024", "01/01/0001");
        List<String> invalidDates = Arrays.asList("", "2029-10-01", "2023-01", "01/2026", "invalid-input", "31/12/2023", "2023/12/31", "12/31/9999");

        for (String date : validDates) {
            Assertions.assertTrue(Validator.isValidDatePattern(date), "Expected valid date pattern: " + date);
        }

        for (String date : invalidDates) {
            Assertions.assertFalse(Validator.isValidDatePattern(date), "Expected invalid date pattern: " + date);
        }
    }

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


    @Test
    void testIsValidDate() {
        Assertions.assertTrue(Validator.isValidDate(LocalDate.now()));
        Assertions.assertFalse(Validator.isValidDate(LocalDate.now().minusDays(1)));
    }

    @Test
    void testIsValidTime() {

        LocalTime validTime = LocalTime.now().plusHours(1);
        LocalTime invalidTime = LocalTime.now().minusHours(1);

        try (MockedStatic<Validator> mockedValidator = Mockito.mockStatic(Validator.class)) {
            mockedValidator.when(() -> Validator.isValidTime(validTime)).thenReturn(true);
            mockedValidator.when(() -> Validator.isValidTime(invalidTime)).thenReturn(false);

            assertTrue(InputHandler.isValidInput(validTime, LocalTime.class));
            assertFalse(InputHandler.isValidInput(invalidTime, LocalTime.class));
        }
        

    }

    @Test
    void testIsInt() {
        Assertions.assertTrue(Validator.isInt(42));
        Assertions.assertTrue(Validator.isInt("42"));
        Assertions.assertFalse(Validator.isInt(3.14));
        Assertions.assertFalse(Validator.isInt("3.14"));
        Assertions.assertFalse(Validator.isInt("invalid"));
    }

    private void mockValidatorMethods(MockedStatic<Validator> mockedValidator) {
        mockedValidator.when(() -> Validator.isValidDatePattern("05/05/2030")).thenReturn(true);
        mockedValidator.when(() -> Validator.isValidDatePattern("15/02/2023")).thenReturn(false);
        mockedValidator.when(() -> Validator.isValidTimePattern("12:30:00")).thenReturn(true);
        mockedValidator.when(() -> Validator.isValidTimePattern("invalid-time")).thenReturn(false);
    }

    @Test
    void testIsValidInputPatternForTime() {
        try (MockedStatic<Validator> mockedValidator = Mockito.mockStatic(Validator.class)) {
            mockValidatorMethods(mockedValidator);

            assertTrue(InputHandler.isValidDateTimePattern(LocalTime.class, "12:30:00"));
            assertFalse(InputHandler.isValidDateTimePattern(LocalTime.class, "invalid-time"));
        }
    }

    @Test
    void testIsValidDateAndIsValidTime() {
        LocalDate validDate = LocalDate.now().plusDays(1);
        LocalDate invalidDate = LocalDate.now().minusYears(1);
        LocalTime validTime = LocalTime.now().plusSeconds(1);
        LocalTime invalidTime = LocalTime.now().minusNanos(1);

        assertTrue(InputHandler.isValidInput(validDate, LocalDate.class));
        assertFalse(InputHandler.isValidInput(invalidDate, LocalDate.class));
        assertTrue(InputHandler.isValidInput(validTime, LocalTime.class));
        assertFalse(InputHandler.isValidInput(invalidTime, LocalTime.class));
    }
}


