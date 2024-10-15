package elysia.file.data;

import java.util.List;

public class WaypointsData {
    private final String id;
    private final List<WaypointData> waypointDataList;
    public WaypointsData(String id, List<WaypointData> waypointDataList) {
        this.id = id;
        this.waypointDataList = waypointDataList;
    }
    public String getId() {
        return id;
    }
    public List<WaypointData> getWaypointDataList() {
        return waypointDataList;
    }
    public static class WaypointData{
        private final String id;
        private final String coordinate;
        private final String world;
        private final String permission;

        public WaypointData(String id, String coordinate, String world, String permission) {
            this.id = id;
            this.coordinate = coordinate;
            this.world = world;
            this.permission = permission;
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
    }
}
