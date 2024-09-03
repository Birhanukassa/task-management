package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import com.github.birhanukassa.taskmanagement.models.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

// Test class for FieldValueMapper utility
class FieldValueMapperTest {

    @Test
    void testGetInitializedVars() {
        // Create a test Task object with initialized fields
        Task testObj = new Task("name", "description");
        testObj.setInterval(42);
        testObj.setPriorityScore(3.14);
        testObj.setStartDate(LocalDate.parse("2029-01-01"));

        // Get initialized variables from the test object
        List<NamedTypedValue<Object>> initializedVars = FieldValueMapper.getInitializedVars(testObj);

        // Assert that the number of initialized variables is correct
        Assertions.assertEquals(5, initializedVars.size());

        // Assert that specific NamedTypedValue objects are not in the list
        // Note: These assertions seem to be checking for false negatives
        Assertions.assertFalse(initializedVars.contains(new NamedTypedValue<>("java.lang.String", "description", "description")));
        Assertions.assertFalse(initializedVars.contains(new NamedTypedValue<>("java.lang.String", "name", "name")));
        Assertions.assertFalse(initializedVars.contains(new NamedTypedValue<>("java.lang.Integer", "Interval", 42)));
        Assertions.assertFalse(initializedVars.contains(new NamedTypedValue<>("java.lang.Double", "Priority", 3.14)));
    }

    @Test
    void testIsFieldInitialized() {
        // Test various scenarios for field initialization
        Assertions.assertTrue(FieldValueMapper.isFieldInitialized("non-empty"));
        Assertions.assertFalse(FieldValueMapper.isFieldInitialized(""));
        Assertions.assertFalse(FieldValueMapper.isFieldInitialized(null));
        Assertions.assertTrue(FieldValueMapper.isFieldInitialized(42));
        Assertions.assertFalse(FieldValueMapper.isFieldInitialized(0));
        Assertions.assertTrue(FieldValueMapper.isFieldInitialized(3.14));
        Assertions.assertFalse(FieldValueMapper.isFieldInitialized(0.0));
    }

    @Test
    void testIsCustomObject() {
        // Test if objects are correctly identified as custom or not
        Assertions.assertTrue(FieldValueMapper.isCustomObject(new com.github.birhanukassa.taskmanagement.util.TimePeriod.Builder().build()));
        Assertions.assertFalse(FieldValueMapper.isCustomObject(new Object()));
    }
}
