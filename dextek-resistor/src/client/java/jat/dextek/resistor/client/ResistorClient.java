package jat.dextek.resistor.client;

import jat.dextek.decc.client.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class ResistorClient implements ClientModInitializer {
    public static final String MOD_ID = "dextek-resistor";
    public static final String CONFIG_NAME = "resistor";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
        // load message
        LOGGER.info("loaded DexTek Resistor");

        ResistorConfig config = Config.get();
	}

    public static class Config {
        private static ResistorConfig config;

        public static ResistorConfig get() {
            if(config == null) load();
            return config;
        }

        public static void load() {
            String json = ConfigManager.load(CONFIG_NAME);
            if(json == null) save();
            config = ConfigManager.GSON.fromJson(json, ResistorConfig.class);
        }

        public static void save() {
            if(config == null) config = new ResistorConfig();
            ConfigManager.save(CONFIG_NAME, ConfigManager.GSON.toJson(config));
        }
    }
}