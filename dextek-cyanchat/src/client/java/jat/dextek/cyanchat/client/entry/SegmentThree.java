package jat.dextek.cyanchat.client.entry;

import jat.dextek.cyanchat.client.ComponentBuilder;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Combines a segment with an optional prefix and suffix.
 * <p>
 * The center segment will be the primary segment.
 * The prefix and suffix will also be dropped if the center segment is considered as empty.
 * Supports an empty String or even null for prefix and suffix.
 */
public class SegmentThree extends ChatSegment {
    private final ChatSegment prefix;
    private final ChatSegment center;
    private final ChatSegment suffix;

    /**
     * Combines a segment with an optional prefix and suffix.
     *
     * @param prefix the prefix segment, may be empty or null
     * @param center the primary segment as center
     * @param suffix the suffix segment, may be empty or null
     */
    public SegmentThree(@Nullable ChatSegment prefix, @Nullable ChatSegment center, @Nullable ChatSegment suffix) {
        this.prefix = prefix;
        this.center = center;
        this.suffix = suffix;
    }

    /**
     * Returns true if the center segment is considered as empty.
     *
     * @return true if the center segment is empty.
     */
    @Override
    public boolean isEmpty() {
        return center != null && center.isEmpty();
    }

    /**
     * Combines the prefix, primary and suffix segment or returns null if the primary is empty.
     *
     * @param valueMap map with key value pairs for replacing placeholders
     * @return the component from prefix, center and suffix or null if empty
     */
    @Override
    public Component toComponent(@Nullable Map<String, String> valueMap) {
        if(isEmpty()) return null;
        return new ComponentBuilder().replace(valueMap).append(prefix).append(center).append(suffix).finish();
    }
}
