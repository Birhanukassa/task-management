package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    @Test
    void testGetUserInput_WithValidInput_ShouldReturnCorrectValue() {
        // Arrange
        String input = "2023-05-01";
        String prompt = "Enter a date (yyyy-MM-dd):";

        // Act
        NamedTypedValue<LocalDate> result = InputHandler.getUserInput(prompt, LocalDate.class);

        // Assert
        assertNotNull(result);
        assertEquals(LocalDate.of(2023, 5, 1), result.getValue());
    }

    @Test
    void testGetUserInput_WithInvalidInput_ShouldThrowException() {
        // Arrange
        String input = "invalid-date";
        String prompt = "Enter a date (yyyy-MM-dd):";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            InputHandler.getUserInput(prompt, LocalDate.class);
        });
    }
}
