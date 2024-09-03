// This package contains model classes for the task management system
package com.github.birhanukassa.taskmanagement.models;

// Importing necessary classes for testing
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Test class for NamedTypedValue
class NamedTypedValueTest {

    // Test to ensure the constructor sets values correctly
    @Test
    void testConstructor() {
        // Create a new NamedTypedValue object
        NamedTypedValue<String> namedTypedValue = new NamedTypedValue<>("String", "name", "value");
        
        // Check if the type, name, and value are set correctly
        Assertions.assertEquals("String", namedTypedValue.getType());
        Assertions.assertEquals("name", namedTypedValue.getName());
        Assertions.assertEquals("value", namedTypedValue.getValue());
    }

    // Test to ensure the constructor throws an exception when type is null
    @Test
    void testConstructorWithNullType() {
        // Assert that a NullPointerException is thrown when type is null
        Assertions.assertThrows(NullPointerException.class, () -> new NamedTypedValue<>(null, "name", "value"));
    }

    // Test to ensure the constructor throws an exception when name is null
    @Test
    void testConstructorWithNullName() {
        // Assert that a NullPointerException is thrown when name is null
        Assertions.assertThrows(NullPointerException.class, () -> new NamedTypedValue<>("String", null, "value"));
    }

    // Test to ensure the setValue method works correctly
    @Test
    void testSetValue() {
        // Create a new NamedTypedValue object
        NamedTypedValue<String> namedTypedValue = new NamedTypedValue<>("String", "name", "value");
        
        // Set a new value
        namedTypedValue.setValue("newValue");
        
        // Check if the new value is set correctly
        Assertions.assertEquals("newValue", namedTypedValue.getValue());
    }

    // Test to ensure the toString method returns a non-null value
    @Test
    void testToString() {
        // Create a new NamedTypedValue object
        NamedTypedValue<String> namedTypedValue = new NamedTypedValue<>("String", "name", "value");
        
        // Check if the toString method returns a non-null value
        Assertions.assertNotNull(namedTypedValue.toString());
    }
}
