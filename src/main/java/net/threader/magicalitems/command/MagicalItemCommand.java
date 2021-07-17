package net.threader.magicalitems.command;

import net.threader.magicalitems.MagicalItem;
import net.threader.magicalitems.MagicalItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;
import java.util.Optional;

public class MagicalItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("§cComando apenas para jogadores");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("magicalitem.adm")) {
            commandSender.sendMessage("§cVocê não tem permissão");
            return true;
        }

        if(strings.length < 1) {
            player.sendMessage("§f§l[§a§lMAGICAL§f§l] §aComandos disponíveis:");
            player.sendMessage("§f§l[§a§lMAGICAL§f§l]   §e- /magicalitem clone <id>");
            return true;
        }

        if(strings[0].equalsIgnoreCase("clone")) {
            if(strings.length < 2) {
                player.sendMessage("§cComando correto: /magicalitem clone <id>");
                return true;
            }

            String id = strings[1];

            Optional<MagicalItem> item = MagicalItems.MAGICAL_REGISTRY.find(id);
            if(item.isPresent()) {
                int firstEmpty = player.getInventory().firstEmpty();
                if(firstEmpty != -1) {
                    player.getInventory().setItem(firstEmpty, item.get().getItemStack().clone());
                    return true;
                }else {
                    player.sendMessage("§cVocê não tem espaço no inventário");
                }
            }
        }
        return false;
    }
}
