package elysiateleport.listener;

import elysiateleport.ElysiaTeleport;
import elysiateleport.file.data.WaypointsData;
import elysiateleport.gui.TeleportGuiHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;
import java.util.UUID;

public class ElysiaTeleportEvent implements Listener {
    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event){
        if (!(event.getInventory().getHolder() instanceof TeleportGuiHolder)) return;
        if (event.getClickedInventory() == null) return;
        if (!event.isLeftClick()) return;
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        String title = event.getInventory().getTitle();
        List<WaypointsData> waypointsDataList = ElysiaTeleport.getWaypointsManager().getWaypointsDataHashMap().get(title);
        String name = event.getCurrentItem().getItemMeta().getDisplayName();
        for (WaypointsData waypointsData : waypointsDataList){
            if (waypointsData.getId().equals(name))
                teleportStart(waypointsData,event.getWhoClicked().getUniqueId());
        }
    }
    private void teleportStart(WaypointsData waypointsData, UUID uuid){
        String coordinateString = waypointsData.getCoordinate();
        World world = Bukkit.getWorld(waypointsData.getWorld());
        String[] coordinateStrings = coordinateString.split(",");
        Location location = new Location(world,Double.parseDouble(coordinateStrings[0]),Double.parseDouble(coordinateStrings[1]),Double.parseDouble(coordinateStrings[2]));
        Player player = Bukkit.getPlayer(uuid);
        player.teleport(location);
        player.sendMessage((ElysiaTeleport.getConfigManager().getConfigData().getPrefix() + ElysiaTeleport.getConfigManager().getConfigData().getMessages().get("onTeleport")
                .replaceAll("%id%", waypointsData.getId()))
                .replace("&","§"));
    }
}
