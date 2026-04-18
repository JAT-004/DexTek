package jat.dextek.cyanchat.client.chat;

import com.mojang.authlib.GameProfile;
import jat.dextek.cyanchat.client.CyanChatClient;
import jat.dextek.cyanchat.client.entry.PlayerSegment;
import jat.dextek.cyanchat.client.entry.Segment;
import jat.dextek.decc.client.model.Player;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.time.Instant;

public class ChatReceiver {
    public boolean process(Component message, Instant time, GameProfile sender) {
        CyanChatClient.LOGGER.info("JAT receiveChat: {}", message.getString());
        ChatDisplay.show(new PlayerSegment(new Player(new Player.Builder().uuid(sender.id()).name(sender.name()))));
        //ChatDisplay.show(new Segment(message.getString(), ChatFormatting.GREEN));
        return true;
    }

    public boolean processServer(Component message, Instant time) {
        CyanChatClient.LOGGER.info("JAT receiveServer: {}", message.getString());
        ChatDisplay.show(new Segment(message.getString(), ChatFormatting.BLUE));
        return true;
    }
}
