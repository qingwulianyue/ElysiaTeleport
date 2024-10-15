package elysia;

import org.bukkit.plugin.java.JavaPlugin;

public final class ElysiaTeleport extends JavaPlugin {
    private static ElysiaTeleport instance;
    public static ElysiaTeleport getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
