package jat.dextek.resistor.client;

import java.util.Collections;
import java.util.Map;

public class CommandMapping {
    public String from;
    public String to;
    
    public Map<String, String> suggestionMap;

    public CommandMapping(String from, String to, Map<String, String> suggestionMap) {
        this.from = from;
        this.to = to;

        this.suggestionMap = suggestionMap;
    }

    public CommandMapping(String from, String to) {
        this(from, to, Collections.emptyMap());
    }
}
