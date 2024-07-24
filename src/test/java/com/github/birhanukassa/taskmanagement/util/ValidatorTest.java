package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class ValidatorTest {

    @Test
    public void testTestWithValidString() {
        Assertions.assertTrue(Validator.test("valid string", String.class));
    }

    @Test
    public void testTestWithNullString() {
        Assertions.assertFalse(Validator.test(null, String.class));
    }

    @Test
    public void testTestWithValidInteger() {
        Assertions.assertTrue(Validator.test(42, Integer.class));
    }

    @Test
    public void testTestWithNullInteger() {
        Assertions.assertFalse(Validator.test(null, Integer.class));
    }

    @Test
    public void testTestWithValidDouble() {
        Assertions.assertTrue(Validator.test(3.14, Double.class));
    }

    @Test
    public void testTestWithNullDouble() {
        Assertions.assertFalse(Validator.test(null, Double.class));
    }

    @Test
    public void testTestWithValidFutureDateAndTime() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        LocalTime futureTime = LocalTime.now().plusHours(1);
        Assertions.assertTrue(Validator.test(futureDate, LocalDate.class));
        Assertions.assertTrue(Validator.test(futureTime, LocalTime.class));
    }

    @Test
    public void testTestWithInvalidDateAndTime() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        LocalTime pastTime = LocalTime.now().minusHours(1);
        Assertions.assertFalse(Validator.test(pastDate, LocalDate.class));
        Assertions.assertFalse(Validator.test(pastTime, LocalTime.class));
    }

    @Test
    public void testIsValidDatePattern() {
        Assertions.assertTrue(Validator.isValidDatePattern("01/01/2023"));
        Assertions.assertFalse(Validator.isValidDatePattern("2023-01-01"));
    }

    @Test
    public void testIsValidTimePattern() {
        Assertions.assertTrue(Validator.isValidTimePattern("12:34:56"));
        Assertions.assertFalse(Validator.isValidTimePattern("12:34"));
    }

    @Test
    public void testIsValidDate() {
        Assertions.assertTrue(Validator.isValidDate(LocalDate.now()));
        Assertions.assertFalse(Validator.isValidDate(LocalDate.now().minusDays(1)));
    }

    @Test
    public void testIsValidTime() {
        Assertions.assertTrue(Validator.isValidTime(LocalTime.now().plusHours(1)));
        Assertions.assertFalse(Validator.isValidTime(LocalTime.now().minusHours(1)));
    }

    @Test
    public void testIsInt() {
        Assertions.assertTrue(Validator.isInt(42));
        Assertions.assertTrue(Validator.isInt("42"));
        Assertions.assertFalse(Validator.isInt(3.14));
        Assertions.assertFalse(Validator.isInt("3.14"));
        Assertions.assertFalse(Validator.isInt("invalid"));
    }
}
