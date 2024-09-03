package com.github.birhanukassa.taskmanagement;

// Import necessary classes and libraries
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.util.InputHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

/**
 * This class contains unit tests for the App class in the task management application.
 * It uses JUnit 5 for testing and Mockito for mocking dependencies.
 */
@ExtendWith(MockitoExtension.class)
class AppTest {

    /**
     * Test the promptUserForChoice method of the App class.
     * This test verifies that the method correctly handles both valid and invalid user inputs.
     */
    @Test
    void testPromptUserForChoice() {
        // Define valid and invalid choices for testing
        String[] validChoices = {"T", "P", "M", "E"};
        String[] invalidChoices = {"X", "Y", "Z"};

        // Use Mockito to mock the static InputHandler class
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            // Test valid choices
            for (String choice : validChoices) {
                // Mock the getUserInput method to return a valid choice
                mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(String.class)))
                    .thenReturn(new NamedTypedValue<>("userInput", "userName", choice));
                
                // Call the method under test
                String userInput = App.promptUserForChoice();
                
                // Assert that the returned input matches the mocked choice
                assertEquals(choice, userInput);
            }

            // Test invalid choices
            for (@SuppressWarnings("unused") String choice : invalidChoices) {
                // Mock the getUserInput method to return an empty string for invalid choices
                mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(String.class)))
                    .thenReturn(new NamedTypedValue<>("userInput", "userName", ""));
                
                // Call the method under test
                String userInput = App.promptUserForChoice();
                
                // Assert that the returned input is not null and is empty
                assertNotNull(userInput);
                assertTrue(userInput.isEmpty());
            }
        }
    }
    
    /**
     * Test the handleUserChoice method of the App class.
     * This test verifies that the method correctly handles the exit choice 'E'.
     */
    @Test
    void testHandleUserChoice() {
        // Set up the test scenario with the exit choice
        String userChoice = "E";
        NamedTypedValue<String> input = new NamedTypedValue<>("userInput", "userName", userChoice);

        // Mock the InputHandler class
        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            // Mock the getUserInput method
            mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(String.class)))
                    .thenReturn(input);

            // Create a spy instance of the App class to partially mock it
            App appSpy = Mockito.spy(App.class);

            // Call the method under test
            boolean shouldExit = appSpy.handleUserChoice(userChoice);

            // Assert that the method returns true, indicating the program should exit
            assertTrue(shouldExit);
        }
    }
}
