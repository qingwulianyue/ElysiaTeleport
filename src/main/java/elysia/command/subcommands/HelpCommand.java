package elysia.command.subcommands;

import org.bukkit.command.CommandSender;

public class HelpCommand {
    public void execute(CommandSender commandSender,String[] strings){
        if (strings.length != 1) return;
        commandSender.sendMessage("§6----------[ElysiaPotion]----------");
        commandSender.sendMessage("§a/ElysiaPotion reload   §7---重载插件");
        commandSender.sendMessage("§a/ElysiaPotion help   §7---查看帮助");
        commandSender.sendMessage("§a/ElysiaPotion open player id   §7---为玩家打开指定id的传送界面");
        commandSender.sendMessage("§6----------------------------------");
    }
}
