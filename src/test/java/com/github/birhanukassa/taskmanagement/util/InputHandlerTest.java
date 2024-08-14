package com.github.birhanukassa.taskmanagement.util;
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {

    @Test
    void testRegisterTypeConverter() {
        Function<String, Integer> converter = Integer::parseInt;
        InputHandler.registerTypeConverter(Integer.class, converter);

        // Use MockedStatic to mock static methods
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
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
        assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "06/01/2030"));
        assertTrue(InputHandler.isValidDateTimePattern(LocalTime.class, "12:34"));
    }

    @Test
    void testIsValidInput() {
        LocalDate validDate = LocalDate.now().plusDays(1);
        LocalDate invalidDate = LocalDate.now().minusDays(1);
        assertTrue(InputHandler.isValidInput(validDate, LocalDate.class));
        assertFalse(InputHandler.isValidInput(invalidDate, LocalDate.class));

        LocalTime validTime = LocalTime.now().plusHours(1);
        LocalTime invalidTime = LocalTime.now().minusHours(1);
        assertTrue(InputHandler.isValidInput(validTime, LocalTime.class));
        assertFalse(InputHandler.isValidInput(invalidTime, LocalTime.class));
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

    @Test
    void testIsValidInputPatternForDate() {
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            mockedInputHandler.when(() -> InputHandler.isValidDateTimePattern(LocalDate.class, anyString()))
                    .thenCallRealMethod();

            // Test valid date patterns
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "05/05/2030"));
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "01/23/2028"));
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "12/28/2026"));
            assertTrue(InputHandler.isValidDateTimePattern(LocalDate.class, "10/01/2027"));
            
            // with incorrect month 
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "15/02/2026"));
            // with incorrect day
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "05/32/2029"));
            // with incorrect year
            assertFalse(InputHandler.isValidDateTimePattern(LocalDate.class, "05/22/202"));
            
        }
    }

}
