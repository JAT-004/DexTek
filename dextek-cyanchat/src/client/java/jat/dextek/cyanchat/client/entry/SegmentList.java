package jat.dextek.cyanchat.client.entry;

import jat.dextek.cyanchat.client.ComponentBuilder;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Combines multiple segments into one.
 */
public class SegmentList extends ChatSegment {
    protected final List<ChatSegment> segmentList;

    /**
     * Creates a combined segment from multiple segments.
     *
     * @param segments all segments that should be combined.
     */
    public SegmentList(@NotNull ChatSegment... segments) {
        segmentList = List.of(segments);
    }

    /**
     * Returns true if the combined segment is considered as empty.
     * <p>
     * The combined segment is empty if there are no segments at all or if all segments themselves are empty.
     *
     * @return true if the combined segment is empty
     */
    @Override
    public boolean isEmpty() {
        // return true if there is no segment
        if(segmentList.isEmpty()) return true;
        // return false if there is at least one not empty segment within the list
        for(ChatSegment segment : segmentList) if(segment != null && !segment.isEmpty()) return false;
        // return true since all segments within the list are empty
        return true;
    }

    /**
     * Builds all segments with formatting and combines them.
     *
     * @param valueMap map with key value pairs for replacing placeholders
     * @return the component combined from all segments or null if empty
     */
    @Override
    public Component toComponent(@Nullable Map<String, String> valueMap) {
        if(isEmpty()) return null;
        ComponentBuilder builder = new ComponentBuilder();
        for(ChatSegment segment : segmentList) builder.replace(valueMap).append(segment);
        return builder.finish();
    }
}
