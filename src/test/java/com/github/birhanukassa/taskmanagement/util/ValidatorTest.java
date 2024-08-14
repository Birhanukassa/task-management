package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

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

    // @Test
    // void testTestWithValidFutureDateAndTime() {
    //     LocalTime futureTime = LocalTime.now().plusHours(2);
    //     Assertions.assertTrue(Validator.test(futureTime, LocalTime.class));
    //     LocalDate futureDate = LocalDate.now().plusDays(1);
    //     Assertions.assertTrue(Validator.test(futureDate, LocalDate.class));
    // }

    @Test
    void testTestWithInvalidDateAndTime() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        LocalTime pastTime = LocalTime.now().minusHours(1);
        Assertions.assertFalse(Validator.test(pastDate, LocalDate.class));
        Assertions.assertFalse(Validator.test(pastTime, LocalTime.class));
    }

    @Test
    void testIsValidDatePattern() {
        Assertions.assertTrue(Validator.isValidDatePattern("01/01/2026"));
        Assertions.assertFalse(Validator.isValidDatePattern("2023-01-01"));
    }

    @Test
    void testIsValidTimePattern() {
        Assertions.assertTrue(Validator.isValidTimePattern("12:34"));
        Assertions.assertFalse(Validator.isValidTimePattern("25:34"));
    }

    @Test
    void testIsValidDate() {
        Assertions.assertTrue(Validator.isValidDate(LocalDate.now()));
        Assertions.assertFalse(Validator.isValidDate(LocalDate.now().minusDays(1)));
    }

    @Test
    void testIsValidTime() {
        Assertions.assertTrue(Validator.isValidTime(LocalTime.now().plusHours(2)));
        Assertions.assertTrue(Validator.isValidTime(LocalTime.now().plusHours(4)));
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
    void testIsValidInput() {
        LocalDate validDate = LocalDate.now().plusDays(1);
        LocalDate invalidDate = LocalDate.now().minusDays(1);
        LocalTime validTime = LocalTime.now().plusHours(1);
        LocalTime invalidTime = LocalTime.now().minusHours(1);

        try (MockedStatic<Validator> mockedValidator = Mockito.mockStatic(Validator.class)) {
            mockedValidator.when(() -> Validator.isValidDate(validDate)).thenReturn(true);
            mockedValidator.when(() -> Validator.isValidDate(invalidDate)).thenReturn(false);
            mockedValidator.when(() -> Validator.isValidTime(validTime)).thenReturn(true);
            mockedValidator.when(() -> Validator.isValidTime(invalidTime)).thenReturn(false);

            assertTrue(InputHandler.isValidInput(validDate, LocalDate.class));
            assertFalse(InputHandler.isValidInput(invalidDate, LocalDate.class));
            assertTrue(InputHandler.isValidInput(validTime, LocalTime.class));
            assertFalse(InputHandler.isValidInput(invalidTime, LocalTime.class));
        }
    }
}
