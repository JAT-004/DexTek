package jat.dextek.cyanchat.client.chat;

import com.mojang.authlib.GameProfile;
import jat.dextek.cyanchat.client.CyanChatClient;
import jat.dextek.cyanchat.client.entry.PlayerSegment;
import jat.dextek.cyanchat.client.entry.Segment;
import jat.dextek.decc.client.model.Player;
import jat.dextek.decc.client.repository.PlayerRepo;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.time.Instant;
import java.util.Optional;

public class ChatReceiver {
    private static final PlayerRepo playerRepo = new PlayerRepo();

    public boolean process(Component message, Instant time, GameProfile sender) {
        CyanChatClient.LOGGER.info("JAT receiveChat: {}", message.getString());
        getPlayer(sender).ifPresent((player) -> ChatDisplay.show(new PlayerSegment(player)));
        //ChatDisplay.show(new PlayerSegment(new Player(new Player.Builder().uuid(sender.id()).name(sender.name()))));
        //ChatDisplay.show(new Segment(message.getString(), ChatFormatting.GREEN));
        return true;
    }

    public boolean processServer(Component message, Instant time) {
        CyanChatClient.LOGGER.info("JAT receiveServer: {}", message.getString());
        ChatDisplay.show(new Segment(message.getString(), ChatFormatting.BLUE));
        return true;
    }

    private Optional<Player> getPlayer(GameProfile sender) {
        // TODO check if player in database needs an update, update if necessary
        if(sender == null) return Optional.empty();
        Optional<Player> result = playerRepo.byUuid(sender.id());
        if(result.isPresent()) return result;
        Player player = new Player(new Player.Builder().uuid(sender.id()).name(sender.name()));
        playerRepo.upsert(player);
        return Optional.of(player);
    }
}
