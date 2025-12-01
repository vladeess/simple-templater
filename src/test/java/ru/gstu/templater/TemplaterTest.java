package ru.gstu.templater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplaterTest {
    private Templater templater;
    private Map<String, String> data;

    @BeforeEach
    void setUp() {
        templater = new Templater();
        data = new HashMap<>();
        data.put("user.name", "Alice");
        data.put("order.id", "XYZ-789");
    }

    @Test
    void testProcessWithValidPlaceholders() {
        String template = "Hello, ${user.name}! Your order is ${order.id}.";
        String expected = "Hello, Alice! Your order is XYZ-789.";
        String actual = templater.process(template, data);
        assertEquals(expected, actual);
    }

    @Test
    void testProcessWithMissingPlaceholder() {
        String template = "Hello, ${user.name}! Your secret code is ${secret.code}.";
        String expected = "Hello, Alice! Your secret code is ${secret.code}.";
        String actual = templater.process(template, data);
        assertEquals(expected, actual);
    }

    @Test
    void testProcessWithNoPlaceholders() {
        String template = "This is a simple text without placeholders.";
        String actual = templater.process(template, data);
        assertEquals(template, actual);
    }
}