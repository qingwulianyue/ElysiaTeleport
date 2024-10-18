package elysiateleport.gui;

import elysiateleport.ElysiaTeleport;
import elysiateleport.file.data.WaypointsData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class GuiManager {
    private final UUID uuid;
    private final String id;

    public GuiManager(UUID uuid, String id) {
        this.uuid = uuid;
        this.id = id;
    }
    public void createGui(){
        Inventory inventory = Bukkit.createInventory(new TeleportGuiHolder(),54,id);
        int slot = 0;
        Player player = Bukkit.getPlayer(uuid);
        for (WaypointsData waypointsData : ElysiaTeleport.getWaypointsManager().getWaypointsDataHashMap().get(id)){
            String permission = waypointsData.getPermission();
            if (permission != null && !player.hasPermission(permission)) continue;
            ItemStack itemStack = new ItemStack(Material.PAPER,1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(waypointsData.getId());
            if (waypointsData.getLore() != null)
                itemMeta.setLore(waypointsData.getLore());
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(slot,itemStack);
            slot++;
        }
        player.openInventory(inventory);
    }
}
