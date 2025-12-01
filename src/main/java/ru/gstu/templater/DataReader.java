package ru.gstu.templater;

import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Читает и парсит данные из файлов .properties и .json.
 */
public class DataReader {

    /**
     * Читает данные из .properties файла.
     *
     * @param filePath путь к файлу.
     * @return Map с данными.
     * @throws IOException если файл не найден.
     */
    public Map<String, String> readProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(filePath)) {
            properties.load(reader);
        }
        Map<String, String> dataMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            dataMap.put(key, properties.getProperty(key));
        }
        return dataMap;
    }

    /**
     * Читает и "уплощает" данные из .json файла.
     *
     * @param filePath путь к файлу.
     * @return Map с "плоскими" ключами.
     * @throws IOException если файл не найден.
     */
    public Map<String, String> readJson(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(content);
        Map<String, String> dataMap = new HashMap<>();
        flattenJson("", jsonObject, dataMap);
        return dataMap;
    }

    /**
     * Рекурсивный метод для преобразования вложенного JSON в плоскую Map.
     *
     * @param currentPath текущий префикс ключа.
     * @param jsonObject  текущий JSON-объект.
     * @param map         карта для сохранения результатов.
     */
    private void flattenJson(String currentPath, JSONObject jsonObject, Map<String, String> map) {
        for (String key : jsonObject.keySet()) {
            String newPath = currentPath.isEmpty() ? key : currentPath + "." + key;
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                flattenJson(newPath, (JSONObject) value, map);
            } else {
                map.put(newPath, value.toString());
            }
        }
    }
}