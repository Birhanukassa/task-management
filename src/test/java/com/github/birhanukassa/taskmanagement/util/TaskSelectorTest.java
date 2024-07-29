package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.TaskSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TaskSelectorTest {

    @Test
    void testPromptUserForTaskSelection() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Description 1"));
        tasks.add(new Task("Task 2", "Description 2"));
        tasks.add(new Task("Task 3", "Description 3"));

        // Test valid task selection
        NamedTypedValue<Task> selection = TaskSelector.promptUserForTaskSelection(tasks, "1");
        Assertions.assertEquals("SelectedTask", selection.getName());
        Assertions.assertEquals(tasks.get(0), selection.getValue());

        // Test exit selection
        selection = TaskSelector.promptUserForTaskSelection(tasks, "E");
        Assertions.assertEquals("ExitSelection", selection.getName());
        Assertions.assertNull(selection.getValue());

        // Test invalid input
        selection = TaskSelector.promptUserForTaskSelection(tasks, "invalid");
        Assertions.assertEquals("ErrorSelection", selection.getName());
        Assertions.assertNull(selection.getValue());
    }

    @Test
    void testUserInputMocking() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1", "Description 1"));

        // Test valid input
        NamedTypedValue<Task> selection = TaskSelector.promptUserForTaskSelection(tasks, "1");
        Assertions.assertEquals("SelectedTask", selection.getName());
        Assertions.assertEquals(tasks.get(0), selection.getValue());

        // Test exit input
        selection = TaskSelector.promptUserForTaskSelection(tasks, "E");
        Assertions.assertEquals("ExitSelection", selection.getName());
        Assertions.assertNull(selection.getValue());

        // Test invalid input
        selection = TaskSelector.promptUserForTaskSelection(tasks, "invalid");
        Assertions.assertEquals("ErrorSelection", selection.getName());
        Assertions.assertNull(selection.getValue());
    }
}
