package jat.dextek.decc.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.sql.SQLException;

public class DeccClient implements ClientModInitializer {
    public static final String MOD_ID = "dextek-decc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("dextek");

	@Override
	public void onInitializeClient() {
        // open database connection on client startup
        try {
            DatabaseManager.get().init();
        } catch(SQLException exception) {
            LOGGER.error("database initialization failed", exception);
            throw new RuntimeException("database initialization failed", exception);
        }

        // load message
        LOGGER.info("loaded DexTek DECC");

        // close database connection on client shutdown
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> DatabaseManager.get().close());
	}
}