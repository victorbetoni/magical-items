package net.threader.magicalitems.action;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Action<T> {
    void trigger(Player p, ItemStack source, T param, Object... args);
}
