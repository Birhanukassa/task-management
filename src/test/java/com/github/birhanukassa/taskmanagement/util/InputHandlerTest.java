package com.github.birhanukassa.taskmanagement.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {

    @BeforeEach
    public void setUp() {
        ScannerWrapper scannerWrapper = Mockito.mock(ScannerWrapper.class);
        InputHandler.setScannerWrapper(scannerWrapper);
    }

    private void mockValidatorMethods(MockedStatic<Validator> mockedValidator) {
        mockedValidator.when(() -> Validator.isValidDatePattern("05/05/2030")).thenReturn(true);
        mockedValidator.when(() -> Validator.isValidDatePattern("invalid-date")).thenReturn(false);
        mockedValidator.when(() -> Validator.isValidTimePattern("12:30:00")).thenReturn(true);
        mockedValidator.when(() -> Validator.isValidTimePattern("invalid-time")).thenReturn(false);
    }

    @Test
    void testIsValidInputPatternForDate() {
        try (MockedStatic<Validator> mockedValidator = Mockito.mockStatic(Validator.class)) {
            mockValidatorMethods(mockedValidator);

            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "05/05/2030"));
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "invalid-date"));
        }
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
