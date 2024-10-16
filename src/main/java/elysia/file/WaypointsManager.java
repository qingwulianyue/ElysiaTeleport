package elysia.file;

import elysia.ElysiaTeleport;
import elysia.file.data.WaypointsData;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WaypointsManager {
    private WaypointsManager(){}
    private static final WaypointsManager instance = new WaypointsManager();
    private ElysiaTeleport Instance;
    private final HashMap<String, List<WaypointsData>> waypointsDataHashMap = new HashMap<>();
    public HashMap<String, List<WaypointsData>> getWaypointsDataHashMap() {
        return waypointsDataHashMap;
    }
    public static WaypointsManager getInstance(){return instance;}
    public void loadWaypointsData(){
        Instance = ElysiaTeleport.getInstance();
        waypointsDataHashMap.clear();
        File file = new File(Instance.getDataFolder() + "/waypoints");
        findAllYmlFiles(file);
    }
    private void findAllYmlFiles(File folder) {
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是文件夹则递归查找
                findAllYmlFiles(file);
            } else if (file.getName().endsWith(".yml")) {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                loadAllData(config);
            }
        }
    }
    private void loadAllData(YamlConfiguration config) {
        for (String firstKey : config.getKeys(false)){
            List<WaypointsData> waypointsDataList = new ArrayList<>();
            for (String secondKey : config.getConfigurationSection(firstKey).getKeys(false)){
                WaypointsData waypointsData = new WaypointsData(
                        secondKey,
                        config.getString(firstKey + "." + secondKey + ".coordinate"),
                        config.getString(firstKey + "." + secondKey + ".world"),
                        config.contains(firstKey + "." + secondKey + ".permission")?config.getString(firstKey + "." + secondKey + ".permission"):null
                );
                waypointsDataList.add(waypointsData);
            }
            waypointsDataHashMap.put(firstKey,waypointsDataList);
        }
        logWayPointsDataIfDebug();
    }
    private void logWayPointsDataIfDebug(){
        if (ElysiaTeleport.getConfigManager().getConfigData().isDebug()){
            for (String key : waypointsDataHashMap.keySet()){
                Instance.getLogger().info("§a 载入传送组: " + key);
                for (WaypointsData waypointsData : waypointsDataHashMap.get(key)){
                    Instance.getLogger().info("§a -载入传送点: " + waypointsData.getId());
                    Instance.getLogger().info("§a  传送点坐标: " + waypointsData.getCoordinate());
                    Instance.getLogger().info("§a  传送点所在世界: " + waypointsData.getWorld());
                    Instance.getLogger().info("§a  传送点所需权限: " + waypointsData.getPermission());
                }
                Instance.getLogger().info("§6-------------");
            }
        }
    }
}
