package elysia.file.data;

import java.util.HashMap;

public class ConfigData {
    private final boolean debug;
    private final String prefix;
    private final HashMap<String,String> messages;
    public ConfigData(boolean debug, String prefix, HashMap<String, String> messages) {
        this.debug = debug;
        this.prefix = prefix;
        this.messages = messages;
    }
    public boolean isDebug() {
        return debug;
    }
    public String getPrefix() {
        return prefix;
    }
    public HashMap<String, String> getMessages() {
        return messages;
    }
}
