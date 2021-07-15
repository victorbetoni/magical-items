package net.threader.magicalitems.action;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Action {
    void trigger(LivingEntity holder, ItemStack source, Object param, Object... args);
}
