package ru.gstu.templater;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class ConfigurationLoaderTest {

    @Test
    void testLoadsPropertiesSuccessfully() throws IOException, URISyntaxException {
        // Получаем правильный путь к нашему тестовому файлу
        String path = Paths.get(getClass().getClassLoader().getResource("test-config.properties").toURI()).toString();

        // Создаем экземпляр класса, который мы тестируем
        ConfigurationLoader loader = new ConfigurationLoader(path);

        // Проверяем, что свойства загрузились корректно
        assertEquals("test/template.txt", loader.getProperty("template.path"));
        assertEquals("JSON", loader.getProperty("data.format"));
        assertNull(loader.getProperty("non.existent.key")); // Проверяем несуществующий ключ
    }

    @Test
    void testThrowsExceptionForMissingFile() {
        // Проверяем, что конструктор выбросит исключение, если файл не существует
        assertThrows(IOException.class, () -> {
            new ConfigurationLoader("non_existent_file.properties");
        });
    }
}