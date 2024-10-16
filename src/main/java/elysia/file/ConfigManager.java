package elysia.file;

import elysia.ElysiaTeleport;
import elysia.file.data.ConfigData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfigManager {
    private ConfigManager(){}
    private static final ConfigManager instance = new ConfigManager();
    private ElysiaTeleport Instance;
    private ConfigData configData;
    public static ConfigManager getInstance() {
        return instance;
    }
    public ConfigData getConfigData() {
        return configData;
    }
    public void loadConfig(){
        configData = null;
        Instance = ElysiaTeleport.getInstance();
        Instance.reloadConfig();
        FileConfiguration config = Instance.getConfig();
        HashMap<String,String> messages = new HashMap<>();
        messages.put("onOpen",config.getString("messages.onOpen"));
        messages.put("onTeleport",config.getString("messages.onTeleport"));
        configData = new ConfigData(
                config.getBoolean("debug"),
                config.getString("prefix"),
                messages
        );
        logConfigIfDebug();
    }
    private void logConfigIfDebug(){
        if (!configData.isDebug())
            return;
        Instance.getLogger().info("§6----------------------------");
        Instance.getLogger().info("§a debug模式: " + configData.isDebug());
        Instance.getLogger().info("§a 消息前缀: " + configData.getPrefix());
        Instance.getLogger().info("§6----------------------------");
    }
}
