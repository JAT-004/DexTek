package jat.dextek.resistor.client;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ResistorConfig {
    public String prefix = "#";

    public Map<String, List<String>> suggestionMap = Map.of(
        "home", Arrays.asList("home")
    );

    public List<CommandMapping> commandList = Arrays.asList(
        new CommandMapping(
            "h <name:string>",
            "/home <name:string>"
        ),
        new CommandMapping(
            "home set <name:string>",
            "/sethome <name:string>",
            Map.of("name", "home")
        ),
        new CommandMapping(
            "home del <name:string>",
            "/deletehome <name:string>",
            Map.of("name", "home")
        )
    );
}
