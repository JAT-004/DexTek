package jat.dextek.decc.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeccClient implements ClientModInitializer {
    public static final String MOD_ID = "dextek-decc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
        // load message
        LOGGER.info("loaded DexTek DECC");
	}
}