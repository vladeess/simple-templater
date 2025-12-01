package ru.gstu.templater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {
    private DataReader dataReader;

    @BeforeEach
    void setUp() {
        dataReader = new DataReader();
    }

    @Test
    void testReadProperties() throws IOException, URISyntaxException {
        String path = Paths.get(getClass().getClassLoader().getResource("test.properties").toURI()).toString();
        Map<String, String> data = dataReader.readProperties(path);

        assertNotNull(data);
        assertEquals(2, data.size());
        assertEquals("TestUser", data.get("user.name"));
        assertEquals("test@example.com", data.get("user.email"));
    }

    @Test
    void testReadAndFlattenJson() throws IOException, URISyntaxException {
        String path = Paths.get(getClass().getClassLoader().getResource("test.json").toURI()).toString();
        Map<String, String> data = dataReader.readJson(path);

        assertNotNull(data);
        assertEquals(3, data.size());
        assertEquals("A-123", data.get("order.id"));
        assertEquals("99.5", data.get("order.total"));
        assertEquals("DELIVERED", data.get("status"));
    }
}