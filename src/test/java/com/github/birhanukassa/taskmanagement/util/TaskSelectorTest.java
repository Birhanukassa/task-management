package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class TaskSelectorTest {

    @Test
    void testPromptUserForTaskSelection() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Description 1"));
        tasks.add(new Task("Task 2", "Description 2"));
        tasks.add(new Task("Task 3", "Description 3"));

        try (MockedStatic<ScannerWrapper> mockedScanner = Mockito.mockStatic(ScannerWrapper.class)) {
            // Mock the behavior of ScannerWrapper.nextLine()
            when(ScannerWrapper.nextLine()).thenReturn("1");

            // Test valid task selection
            com.github.birhanukassa.taskmanagement.models.NamedTypedValue<Task> selection = TaskSelector.promptUserForTaskSelection(tasks);
            Assertions.assertEquals("SelectedTask", selection.getName());
            Assertions.assertEquals(tasks.get(0), selection.getValue());

            // Mock the behavior of ScannerWrapper.nextLine() for exit input
            when(ScannerWrapper.nextLine()).thenReturn("E");

            // Test exit selection
            selection = TaskSelector.promptUserForTaskSelection(tasks);
            Assertions.assertEquals("ExitSelection", selection.getName());
            Assertions.assertNull(selection.getValue());

            // Mock the behavior of ScannerWrapper.nextLine() for invalid input
            when(ScannerWrapper.nextLine()).thenReturn("invalid");

            // Test invalid input
            selection = TaskSelector.promptUserForTaskSelection(tasks);
            Assertions.assertEquals("ErrorSelection", selection.getName());
            Assertions.assertNull(selection.getValue());
        }
    }
}
