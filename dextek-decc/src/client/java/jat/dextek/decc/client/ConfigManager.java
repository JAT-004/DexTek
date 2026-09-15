package jat.dextek.decc.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigManager {
    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static String load(String configName) {
        String fileName = configName + ".json";
        Path path = DeccClient.CONFIG_PATH.resolve(fileName);

        if(Files.exists(path)) try {
            String json = Files.readString(path);
            DeccClient.LOGGER.info("loaded config " + fileName);
            return json;
        } catch(IOException exception) {
            DeccClient.LOGGER.error("invalid config " + fileName, exception);
            // TODO error message in chat
            return null;
        }
        else {
            DeccClient.LOGGER.info("no config " + fileName);
            // TODO first usage messages in chat
            return null;
        }
    }

    public static void save(String configName, String json) {
        String fileName = configName + ".json";
        Path path = DeccClient.CONFIG_PATH.resolve(fileName);

        try {
            Files.createDirectories(DeccClient.CONFIG_PATH);
            Files.writeString(path, json);
            DeccClient.LOGGER.info("saved config " + fileName);
            // TODO chat confirmation message
        } catch(IOException exception) {
            DeccClient.LOGGER.error("unable to save config " + fileName, exception);
            // TODO error message in chat
        }
    }
}
