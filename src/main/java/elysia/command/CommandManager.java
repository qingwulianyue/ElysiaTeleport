package elysia.command;

import elysia.ElysiaTeleport;
import elysia.command.subcommands.HelpCommand;
import elysia.command.subcommands.OpenCommand;
import elysia.command.subcommands.ReloadCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/*
/ElysiaTeleport reload 重载插件
/ElysiaTeleport help 获取帮助
/ElysiaTeleport open player id 为指定玩家打开指定id的传送界面
 */
public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings == null) return false;
        if (strings.length == 0)
            commandSender.sendMessage(
                    ElysiaTeleport.getConfigManager().getConfigData().getPrefix() +
                            "请输入/ElysiaTeleport help 以获取指令帮助"
            );
        switch (strings[0].toLowerCase()){
            case "help" : new HelpCommand().execute(commandSender,strings);break;
            case "reload" : new ReloadCommand().execute(commandSender,strings);break;
            case "open" : new OpenCommand().execute(commandSender,strings);break;
        }
        return true;
    }
}
