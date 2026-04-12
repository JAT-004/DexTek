package jat.dextek.cyanchat.client.entry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a simple segment with text and optional formatting.
 * <p>
 * Allows only one single formatting. It is not possible to combine, e.g. italic and a color.
 * This is a design choice, it simplifies handling and configuration files.
 */
public class Segment extends ChatSegment {
    public static Segment EMPTY = new Segment(null);

    private String text;
    private final ChatFormatting format;
    private final List<String> placeholderList;

    /**
     * Creates a segment from characters without formatting.
     * <p>
     * The default formatting defined by the configuration file will be used.
     *
     * @param text the characters for this segment
     */
    public Segment(@Nullable String text) {
        this(text, null);
    }

    /**
     * Creates a segment from characters with formatting.
     *
     * @param text the characters for this segment
     * @param format the formatting for this segment or null
     */
    public Segment(@Nullable String text, @Nullable ChatFormatting format) {
        this.text = text;
        this.format = format;
        this.placeholderList = new ArrayList<>();
        if(text != null) locatePlaceholders();
    }

    /**
     * Creates a segment from characters with formatting.
     * <p>
     * The provided default formatting will be used if the provided primary formatting is empty.
     * The default formatting defined by the configuration file will be used
     * if the provided default formatting is empty too
     *
     * @param text the characters for this segment
     * @param format the formatting for this segment, may be null
     * @param defaultFormat alternative used formatting if the primary formatting is null
     */
    public Segment(@Nullable String text, @Nullable ChatFormatting format, @Nullable ChatFormatting defaultFormat) {
        this(text, format != null ? format : defaultFormat);
    }

    /**
     * Locates and saves placeholders within the segment based on a pattern.
     */
    private void locatePlaceholders() {
        Pattern pattern = Pattern.compile("\\$\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) placeholderList.add(matcher.group());
    }

    /**
     * Replaces all placeholders, if any.
     *
     * @param valueMap map with key value pairs for replacing placeholders
     */
    private void replacePlaceholders(@NotNull Map<String, String> valueMap) {
        for(String placeholder : placeholderList) {
            String value = valueMap.get(placeholder);
            if(value != null) text = text.replace(placeholder, value);
        }
    }

    /**
     * Returns true if the segment does not contain any characters.
     *
     * @return true if the segment does not contain any characters
     */
    @Override
    public boolean isEmpty() {
        return text == null || text.isEmpty();
    }

    /**
     * Builds the segment and applies the formatting, if present.
     *
     * @param valueMap map with key value pairs for replacing placeholders
     * @return the component with formatting or null if the segment is empty
     */
    @Override
    public Component toComponent(@Nullable Map<String, String> valueMap) {
        // return nothing when there is no text
        if(isEmpty()) return null;
        // replace placeholders if necessary
        if(valueMap != null && !valueMap.isEmpty()) replacePlaceholders(valueMap);
        // use format when present
        if(format != null) return Component.literal(text).withStyle(format);
        // use default format when present
        // TODO use ConfigManager
        //ChatFormatting defaultFormat = ConfigManager.get().defaultFormat;
        //if(defaultFormat != null) return Component.literal(text).withStyle(defaultFormat);
        // create without format
        return Component.literal(text);
    }
}
