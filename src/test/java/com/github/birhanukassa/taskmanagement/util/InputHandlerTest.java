package com.github.birhanukassa.taskmanagement.util;

// Project-specific imports
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;

// JUnit 5 imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// Mockito imports for mocking and testing
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// Java time API imports
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
// Functional interface import
import java.util.function.Function;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
// Static imports for JUnit assertions
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Static imports for Mockito argument matchers
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
@ExtendWith(MockitoExtension.class)
class InputHandlerTest {

    @Test
    void testRegisterTypeConverter() {
        // Define a converter function to parse String to Integer
        Function<String, Integer> converter = Integer::parseInt;
        // Register the converter for Integer class
        InputHandler.registerTypeConverter(Integer.class, converter);

        // Use MockedStatic to mock static methods of InputHandler
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            // Set up mock behavior for convertInput method
            mockedInputHandler.when(() -> InputHandler.convertInput(anyString(), eq(Integer.class)))
                    .thenReturn(123);

            // Test the converter registration
            Integer result = InputHandler.convertInput("123", Integer.class);
            assertNotNull(result);
            assertEquals(123, result);
        }
    }

    @Test
    void testIsValidDateTimePattern() {
        // Define arrays of valid and invalid date/time inputs
        String[] validDateInputs = { "05/05/2026", "12/31/2030"};
        String[] invalidDateInputs = { "", "13/29/27", "00/02/2029", "06/2029", "10-02-2026"};
        String[] validTimeInputs = { "00:00", "00:12"};
        String[] invalidTimeInputs = { "", "12:40:00", "12-40-02"};

        // Collect all input arrays into a list
        List<String[]> collOfInputs = new ArrayList<>();
        collOfInputs.add(validDateInputs);
        collOfInputs.add(invalidDateInputs);
        collOfInputs.add(validTimeInputs);
        collOfInputs.add(invalidTimeInputs);

        // Iterate through all inputs and test validity
        for (String[] inputs: collOfInputs) {
            for (String input: inputs) {
                if (inputs == validDateInputs) {
                    assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, input));
                } 
                
                if (inputs == invalidDateInputs) {
                    assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, input));
                } 
                
                if (inputs == validTimeInputs) {
                    assertTrue(InputHandler.isValidDateTimePattern(LocalTime.class, input));
                } 
                
                if (inputs == invalidDateInputs) {
                    assertTrue(InputHandler.isValidDateTimePattern(LocalTime.class, input));
                }
            }
        }
    }

    @Test
    void testIsValidInput() {
            
        LocalDate validDate = LocalDate.now().plusDays(1);
        LocalDate invalidDate = LocalDate.now().minusDays(1);
        assertTrue(InputHandler.isValidInput(validDate, LocalDate.class));
        assertFalse(InputHandler.isValidInput(invalidDate, LocalDate.class));
    }

    @Test
    void testConvertInput() {
        InputHandler.registerTypeConverter(String.class, String::toString);
        String input = "test";

        // Use MockedStatic to mock static methods
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            mockedInputHandler.when(() -> InputHandler.convertInput(anyString(), eq(String.class)))
                    .thenReturn("test");

            assertEquals("test", InputHandler.convertInput(input, String.class));
        }
    }

    @Test
    void testGetUserInput() {
        String message = "Enter a date:";
        String input = "2023-08-06";
        InputHandler.registerTypeConverter(LocalDate.class, LocalDate::parse);

        // Use MockedStatic to mock static methods
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(LocalDate.class)))
                    .thenReturn(new NamedTypedValue<>("LocalDate", "convertedValue", LocalDate.parse(input)));

            NamedTypedValue<LocalDate> result = InputHandler.getUserInput(message, LocalDate.class);
            assertNotNull(result);
            assertEquals(LocalDate.parse(input), result.getValue());
        }
    }

    // Testing with mock 
    @Test
    void testIsValidInputPatternForDateWithMock() {
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            mockedInputHandler.when(() -> InputHandler.isValidDateTimePattern(eq(LocalDate.class), anyString()))
                    .thenCallRealMethod();

            // Test valid date patterns
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "05/05/2030"));
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "01/23/2028"));
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "12/28/2026"));
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "10/01/2027"));
            
            // with incorrect month 
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "15/02/2028"));
            // with incorrect day
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "05/00/2029"));
            // with incorrect year
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "05/22/202"));
            
        }
    }
}
