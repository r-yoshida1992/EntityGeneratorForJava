package app.utils;

import java.util.function.BiFunction;

/**
 * 文字列のutil
 *
 */
public class StringUtil {
    /**
     * スネークケースからキャメルケースの文字列に変換する
     */
    public static String snakeToCamel(String snake, boolean isFirstCharLower) {
        // スネークケースでない場合は小文字にして返却
        if (!snake.contains("_")) {
            return snake.toLowerCase();
        }
        String[] splitWord = snake.split("_");
        StringBuilder camel = new StringBuilder();
        // 連結用のfunction
        BiFunction<String, String, String> concatFunction = (a, b) -> {
            return new StringBuilder(a).append(b).toString();
        };
        for (String s : splitWord) {
            // 区切り文字の連結などの場合、length = 0となる為ため、無視。
            if (s.length() == 0) {
                continue;
            }
            camel.append(camel.length() == 0 && isFirstCharLower
                    ? s.toLowerCase()
                    : concatFunction.apply(s.substring(0, 1).toUpperCase(), s.substring(1, s.length()).toLowerCase()));
        }
        return camel.toString();
    }

}
