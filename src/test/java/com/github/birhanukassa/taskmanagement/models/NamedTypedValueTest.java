import com.github.birhanukassa.taskmanagement.models.NamedTypedValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NamedTypedValueTest {

    @Test
    public void testConstructor() {
        NamedTypedValue<String> namedTypedValue = new NamedTypedValue<>("String", "name", "value");
        Assertions.assertEquals("String", namedTypedValue.getType());
        Assertions.assertEquals("name", namedTypedValue.getName());
        Assertions.assertEquals("value", namedTypedValue.getValue());
    }

    @Test
    public void testConstructorWithNullType() {
        Assertions.assertThrows(NullPointerException.class, () -> new NamedTypedValue<>(null, "name", "value"));
    }

    @Test
    public void testConstructorWithNullName() {
        Assertions.assertThrows(NullPointerException.class, () -> new NamedTypedValue<>("String", null, "value"));
    }

    @Test
    public void testSetValue() {
        NamedTypedValue<String> namedTypedValue = new NamedTypedValue<>("String", "name", "value");
        namedTypedValue.setValue("newValue");
        Assertions.assertEquals("newValue", namedTypedValue.getValue());
    }

    @Test
    public void testEquals() {
        NamedTypedValue<String> namedTypedValue1 = new NamedTypedValue<>("String", "name", "value");
        NamedTypedValue<String> namedTypedValue2 = new NamedTypedValue<>("String", "name", "value");
        Assertions.assertEquals(namedTypedValue1, namedTypedValue2);
    }

    @Test
    public void testHashCode() {
        NamedTypedValue<String> namedTypedValue1 = new NamedTypedValue<>("String", "name", "value");
        NamedTypedValue<String> namedTypedValue2 = new NamedTypedValue<>("String", "name", "value");
        Assertions.assertEquals(namedTypedValue1.hashCode(), namedTypedValue2.hashCode());
    }

    @Test
    public void testToString() {
        NamedTypedValue<String> namedTypedValue = new NamedTypedValue<>("String", "name", "value");
        Assertions.assertNotNull(namedTypedValue.toString());
    }
}
