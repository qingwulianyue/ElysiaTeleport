package elysiateleport.command.subcommands;

import elysiateleport.ElysiaTeleport;
import org.bukkit.command.CommandSender;

public class ReloadCommand {
    public void execute(CommandSender commandSender, String[] strings){
        if (strings.length != 1) return;
        ElysiaTeleport.getWaypointsManager().loadWaypointsData();
        ElysiaTeleport.getConfigManager().loadConfig();
        commandSender.sendMessage(ElysiaTeleport.getConfigManager().getConfigData().getMessages().get("reload").replaceAll("&","§"));
    }
}
