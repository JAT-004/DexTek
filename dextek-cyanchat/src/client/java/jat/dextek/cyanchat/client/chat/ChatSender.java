package jat.dextek.cyanchat.client.chat;

import jat.dextek.cyanchat.client.CyanChatClient;
import jat.dextek.cyanchat.client.entry.Segment;
import net.minecraft.ChatFormatting;

import java.time.Instant;

public class ChatSender {
    public boolean process(String message, Instant time) {
        CyanChatClient.LOGGER.info("JAT sendChat: {}", message);
        ChatDisplay.show(new Segment(message, ChatFormatting.YELLOW));
        return true;
    }
}
