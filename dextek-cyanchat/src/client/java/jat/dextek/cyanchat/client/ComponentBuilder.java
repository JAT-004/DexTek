package jat.dextek.cyanchat.client;

import jat.dextek.cyanchat.client.entry.ChatSegment;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Builds a component from combined chat segments.
 */
public class ComponentBuilder {
    private final MutableComponent builder = Component.empty();
    private Map<String, String> valueMap = null;
    private boolean isEmpty = true;


    /**
     * Sets the value map for replacing placeholders.
     *
     * @param valueMap map with key value pairs for replacing placeholders
     * @return the instance itself
     */
    public ComponentBuilder replace(@Nullable Map<String, String> valueMap) {
        this.valueMap = valueMap;
        return this;
    }

    /**
     * Appends a segment.
     *
     * @param segment the chat segment
     * @return the instance itself
     */
    public ComponentBuilder append(@Nullable ChatSegment segment) {
        if(segment != null && !segment.isEmpty()) {
            builder.append(segment.toComponent(valueMap));
            isEmpty = false;
        }
        return this;
    }

    /**
     * Returns the combined component or null if empty.
     *
     * @return the combined component or null if empty
     */
    public Component finish() {
        if(isEmpty) return null;
        return builder;
    }
}
