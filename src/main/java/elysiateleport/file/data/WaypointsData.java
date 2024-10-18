package elysiateleport.file.data;

import java.util.List;

public class WaypointsData {
    private final String id;
    private final String coordinate;
    private final String world;
    private final String permission;
    private final List<String> lore;

    public WaypointsData(String id, String coordinate, String world, String permission, List<String> lore) {
        this.id = id;
        this.coordinate = coordinate;
        this.world = world;
        this.permission = permission;
        this.lore = lore;
    }

    public String getId() {
        return id;
    }
    public String getCoordinate() {
        return coordinate;
    }
    public String getWorld() {
        return world;
    }
    public String getPermission() {
        return permission;
    }
    public List<String> getLore() {
        return lore;
    }
}
