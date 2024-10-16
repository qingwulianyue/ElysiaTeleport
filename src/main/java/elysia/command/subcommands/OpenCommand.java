package elysia.command.subcommands;

import elysia.ElysiaTeleport;
import elysia.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenCommand {
    public void execute(CommandSender commandSender, String[] strings){
        if (strings.length != 3) return;
        String name = strings[1];
        String id = strings[2];
        if (Bukkit.getPlayer(name) == null) {
            commandSender.sendMessage("当前玩家 " + name + " 不在线");
            return;
        }
        Player player = Bukkit.getPlayer(name);
        if (!ElysiaTeleport.getWaypointsManager().getWaypointsDataHashMap().containsKey(id)){
            commandSender.sendMessage("找不到指定的传送界面 " + id);
            return;
        }
        new GuiManager(player.getUniqueId(),id).createGui();
        commandSender.sendMessage((ElysiaTeleport.getConfigManager().getConfigData().getPrefix() + ElysiaTeleport.getConfigManager().getConfigData().getMessages().get("onOpen")
                .replaceAll("%group%",id))
                .replaceAll("&","§"));
    }
}
