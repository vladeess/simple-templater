package ru.gstu.templater;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Загружаем конфигурацию
            ConfigurationLoader config = new ConfigurationLoader("app.properties");
            String templatePath = config.getProperty("template.file.path");
            String dataPath = config.getProperty("data.file.path");
            String dataFormat = config.getProperty("data.format");

            // 2. Читаем файл шаблона
            String templateContent = new String(Files.readAllBytes(Paths.get(templatePath)));

            // 3. Читаем данные в зависимости от формата
            DataReader dataReader = new DataReader();
            Map<String, String> data;
            if ("PROPERTIES".equalsIgnoreCase(dataFormat)) {
                data = dataReader.readProperties(dataPath);
            } else if ("JSON".equalsIgnoreCase(dataFormat)) {
                data = dataReader.readJson(dataPath);
            } else {
                throw new IllegalArgumentException("Unsupported data format: " + dataFormat);
            }

            // 4. Обрабатываем шаблон
            Templater templater = new Templater();
            String result = templater.process(templateContent, data);

            // 5. Выводим результат
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Error executing templater: " + e.getMessage());
            e.printStackTrace();
        }
    }
}