package com.github.birhanukassa.taskmanagement;

import com.github.birhanukassa.taskmanagement.commands.PriorityQueueCommand;
import com.github.birhanukassa.taskmanagement.commands.TaskFactory;
import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;
import com.github.birhanukassa.taskmanagement.util.ScannerWrapper;
import com.github.birhanukassa.taskmanagement.util.TaskSelector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Arrays;
import java.util.List;

// ... (other imports)

@ExtendWith(MockitoExtension.class)
class AppTest {

    @BeforeEach
    void setUp() {
        // Create an instance of ScannerWrapper
        ScannerWrapper scannerWrapper = new ScannerWrapper();
        // Set the ScannerWrapper instance in InputHandler
        InputHandler.setScannerWrapper(scannerWrapper);
    }

    @Test
    void testPromptUserForChoice() {
        // Arrange
        String[] validChoices = {"T", "P", "M", "E"};
        String[] invalidChoices = {"X", "Y", "Z"};

        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            // Mock the getUserInput method for valid choices
            for (String choice : validChoices) {
                mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(String.class)))
                    .thenReturn(new NamedTypedValue<>("userInput", "userName", choice));
                String userInput = App.promptUserForChoice();
                assertEquals(choice, userInput);
            }

            // Mock the getUserInput method for invalid choices
            for (String choice : invalidChoices) {
                mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(String.class)))
                    .thenReturn(new NamedTypedValue<>("userInput", "userName", ""));
                String userInput = App.promptUserForChoice();
                assertNotNull(userInput);
                assertTrue(userInput.isEmpty());
            }
        }
    }
    
    @Test
    void testHandleUserChoice() {
        // Arrange
        String userChoice = "E";
        NamedTypedValue<String> input = new NamedTypedValue<>("userInput", "userName", userChoice);

        try (MockedStatic<InputHandler> mockedInputHandler = Mockito.mockStatic(InputHandler.class)) {
            mockedInputHandler.when(() -> InputHandler.getUserInput(anyString(), eq(String.class)))
                    .thenReturn(input);

            // Create a spy instance of the App class
            App appSpy = Mockito.spy(App.class);

            // Act
            boolean shouldExit = appSpy.handleUserChoice(userChoice);

            // Assert
            assertTrue(shouldExit);
        }
    }

}
