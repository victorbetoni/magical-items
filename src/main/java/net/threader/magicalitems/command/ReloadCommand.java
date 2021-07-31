package net.threader.magicalitems.command;

import net.threader.magicalitems.MagicalItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.hasPermission("magicalitems.adm")) {
            commandSender.sendMessage("§cVocê não tem permissão");
            return true;
        }
        MagicalItems.instance().reload();
        return false;
    }
}
