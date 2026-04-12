package jat.dextek.cyanchat.client.entry;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents text with optional formatting.
 */
public abstract class ChatSegment {
    /**
     * Returns true if the segment should not be displayed.
     *
     * @return true if the segment should not be displayed
     */
    public abstract boolean isEmpty();


    /**
     * Builds the segment and applies all formatting, if any.
     * <p>
     * Does not replace placeholders.
     *
     * @return the component with all formatting or null if the segment is empty
     */
    public final Component toComponent() {
        return toComponent(null);
    }


    /**
     * Replaces all placeholders and builds the segment with formatting, if present.
     * <p>
     * If the map does not contain a value for a placeholder the key will be used as value.
     *
     * @param valueMap map with key value pairs for replacing the placeholders
     * @return the component with all formatting or null if the segment is empty
     */
    public abstract Component toComponent(@Nullable Map<String, String> valueMap);
}
