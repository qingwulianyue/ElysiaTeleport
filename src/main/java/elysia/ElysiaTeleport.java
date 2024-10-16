package elysia;

import elysia.command.CommandManager;
import elysia.command.CommandTabComplete;
import elysia.file.ConfigManager;
import elysia.file.FileListener;
import elysia.file.WaypointsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class ElysiaTeleport extends JavaPlugin {
    private static ElysiaTeleport instance;
    private static ConfigManager configManager;
    private static WaypointsManager waypointsManager;
    public static ElysiaTeleport getInstance() {
        return instance;
    }
    public static ConfigManager getConfigManager() {
        return configManager;
    }
    public static WaypointsManager getWaypointsManager() {
        return waypointsManager;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        instance = this;
        createFile();
        configManager = ConfigManager.getInstance();
        waypointsManager = WaypointsManager.getInstance();
        configManager.loadConfig();
        waypointsManager.loadWaypointsData();
        FileListener.startWatching(getDataFolder());
        Bukkit.getPluginCommand("ElysiaTeleport").setExecutor(new CommandManager());
        Bukkit.getPluginCommand("ElysiaTeleport").setTabCompleter(new CommandTabComplete());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void createFile() {
        saveDefaultConfig();
        createPotionFile();
    }
    private void createPotionFile() {
        Path waypointsFolderPath = getDataFolder().toPath().resolve("waypoints");
        createDirectoryIfNotExists(waypointsFolderPath);
        Path waypointsFilePath = waypointsFolderPath.resolve("示例传送.yml");
        if (!Files.exists(waypointsFilePath)) {
            try (InputStream resourceStream = getResourceAsStream()) {
                Files.copy(resourceStream, waypointsFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to create sample file.", e);
            }
        }
    }
    private void createDirectoryIfNotExists(Path directoryPath) {
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to create directory.", e);
            }
        }
    }
    private InputStream getResourceAsStream() {
        InputStream resourceStream = getResource("waypoints/示例传送.yml");
        if (resourceStream == null) {
            throw new RuntimeException("Resource " + "waypoints/示例传送.yml" + " not found in classpath.");
        }
        return resourceStream;
    }
}
