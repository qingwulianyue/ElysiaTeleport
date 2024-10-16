package elysia.command;

import elysia.ElysiaTeleport;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> result = new ArrayList<>();
        if (strings == null) return result;
        if (strings.length == 1){
            result.add("reload");
            result.add("help");
        } else if (strings.length == 2 && strings[0].equals("open")){
            for (Player player : Bukkit.getOnlinePlayers())
                result.add(player.getName());
        } else if (strings.length == 3 && strings[0].equals("open")){
            result.addAll(ElysiaTeleport.getWaypointsManager().getWaypointsDataHashMap().keySet());
        }
        return result;
    }
}
