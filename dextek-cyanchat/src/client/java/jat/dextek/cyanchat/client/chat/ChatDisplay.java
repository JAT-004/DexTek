package jat.dextek.cyanchat.client.chat;

import jat.dextek.cyanchat.client.entry.ChatSegment;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.Nullable;

public class ChatDisplay {
    public static void show(@Nullable ChatSegment message) {
        if(message == null || message.isEmpty()) return;
        Minecraft client = Minecraft.getInstance();
        client.gui.getChat().addMessage(message.toComponent());
    }
}
