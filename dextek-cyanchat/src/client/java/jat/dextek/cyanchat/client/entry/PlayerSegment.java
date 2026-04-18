package jat.dextek.cyanchat.client.entry;

import jat.dextek.decc.client.model.Player;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents a player within chat.
 */
public class PlayerSegment extends ChatSegment {
    private final Player player;

    /**
     * Creates a ChatSegment for representing a player.
     *
     * @param player the player data
     */
    public PlayerSegment(@Nullable Player player) {
        this.player = player;
    }

    /**
     * Returns true if the player data is missing.
     * <p>
     * Two parts are important. The UUID is necessary for identifying a player.
     * In addition, the name is necessary for displaying something in chat.
     *
     * @return true if the player data is missing
     */
    @Override
    public boolean isEmpty() {
        return player == null || player.getUuid() == null || player.getName() == null;
    }

    /**
     * Builds the segment and applies the formatting, if present.
     *
     * @param valueMap map with key value pairs for replacing the placeholders
     * @return the component with formatting or null if the player can not be identified
     */
    @Override
    public Component toComponent(@Nullable Map<String, String> valueMap) {
        if(isEmpty()) return null;
        // TODO
        return new SegmentList(new Segment(player.getUuid().toString()), new Segment(player.getName())).toComponent();
    }
}
