package ru.gstu.templater;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Загружает конфигурацию из файла app.properties.
 */
public class ConfigurationLoader {
    private final Properties properties;

    /**
     * Конструктор загружает свойства из указанного файла.
     *
     * @param configFilePath путь к файлу конфигурации.
     * @throws IOException если файл не найден или не может быть прочитан.
     */
    public ConfigurationLoader(String configFilePath) throws IOException {
        properties = new Properties();
        try (FileReader reader = new FileReader(configFilePath)) {
            properties.load(reader);
        }
    }

    /**
     * Получает значение свойства по ключу.
     *
     * @param key ключ свойства.
     * @return значение свойства.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}