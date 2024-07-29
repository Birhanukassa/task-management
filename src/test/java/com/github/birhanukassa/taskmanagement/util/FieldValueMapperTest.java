package com.github.birhanukassa.taskmanagement.util;

import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class FieldValueMapperTest {

    @Test
    void testGetInitializedVars() {
        TestObject testObj = new TestObject("value1", 42, 3.14);
        List<NamedTypedValue<Object>> initializedVars = FieldValueMapper.getInitializedVars(testObj);
        Assertions.assertEquals(3, initializedVars.size());
        Assertions.assertTrue(initializedVars.contains(new NamedTypedValue<>("java.lang.String", "strField", "value1")));
        Assertions.assertTrue(initializedVars.contains(new NamedTypedValue<>("java.lang.Integer", "intField", 42)));
        Assertions.assertTrue(initializedVars.contains(new NamedTypedValue<>("java.lang.Double", "doubleField", 3.14)));
    }

    @Test
    void testIsFieldInitialized() {
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
        Assertions.assertTrue(FieldValueMapper.isCustomObject(new com.github.birhanukassa.taskmanagement.util.TimePeriod.Builder().build()));
        Assertions.assertFalse(FieldValueMapper.isCustomObject(new Object()));
    }

    private static class TestObject {
        String strField;
        int intField;
        double doubleField;

        TestObject(String str, int i, double d) {
            strField = str;
            intField = i;
            doubleField = d;
        }
    }
}
