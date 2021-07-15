package net.threader.magicalitems.action;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Action {
    void trigger(Player p, ItemStack source, Object param, Object... args);
}
