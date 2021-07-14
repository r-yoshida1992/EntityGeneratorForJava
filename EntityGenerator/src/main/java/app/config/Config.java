package app.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import lombok.Getter;

@Getter
public class Config {
    private String url;
    private String user;
    private String password;

    static Gson gson = new Gson();

    public static Config getInstance() {
        try {
            return gson.fromJson(Files.lines(Paths.get("src/main/resources/setting.json"), Charset.forName("UTF-8"))
                    .collect(Collectors.joining(System.lineSeparator())), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("setting.jsonが不正です。");
        }
    }

}
