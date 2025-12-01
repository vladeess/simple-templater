package ru.gstu.templater;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Выполняет замену плейсхолдеров в шаблоне на основе предоставленных данных.
 */
public class Templater {

    // Паттерн для поиска плейсхолдеров вида ${key.name}
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{([^}]+)\\}");

    /**
     * Заменяет плейсхолдеры в строке-шаблоне на значения из карты данных.
     *
     * @param template исходный шаблон.
     * @param data     карта с данными для замены.
     * @return строка с замененными плейсхолдерами.
     */
    public String process(String template, Map<String, String> data) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = data.getOrDefault(key, matcher.group(0)); // Если ключа нет, оставляем как было
            matcher.appendReplacement(result, Matcher.quoteReplacement(value));
        }
        matcher.appendTail(result);

        return result.toString();
    }
}