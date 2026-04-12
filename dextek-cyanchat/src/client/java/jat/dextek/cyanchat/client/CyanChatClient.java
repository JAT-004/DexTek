package jat.dextek.cyanchat.client;

import jat.dextek.cyanchat.client.chat.ChatReceiver;
import jat.dextek.cyanchat.client.chat.ChatSender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class CyanChatClient implements ClientModInitializer {
    public static final String MOD_ID = "dextek-cyanchat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private final ChatReceiver chatReceiver = new ChatReceiver();
    private final ChatSender chatSender = new ChatSender();

	@Override
	public void onInitializeClient() {
        ClientSendMessageEvents.ALLOW_CHAT.register((message) -> {
            // process own messages before sending
            return chatSender.process(message, Instant.now());
        });

        ClientReceiveMessageEvents.ALLOW_CHAT.register((message, _signed, sender, _params, time) -> {
            // process received player messages with relevant information
            return chatReceiver.process(message, time, sender);
        });

        ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> {
            // process received server messages in chat
            if(!overlay) return chatReceiver.processServer(message, Instant.now());
            // forward actionbar messages as normal
            return true;
        });

        // load message
        LOGGER.info("loaded DexTek CyanChat");
	}
}