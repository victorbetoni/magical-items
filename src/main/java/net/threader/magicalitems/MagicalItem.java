package net.threader.magicalitems;

import net.threader.magicalitems.template.ActionTemplate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class MagicalItem {
    private Set<ActionTemplate<?>> actions;
    private ItemStack itemStack;

    public MagicalItem(Set<ActionTemplate<?>> actions, ItemStack itemStack) {
        this.actions = actions;
        this.itemStack = itemStack;
    }

    public Set<ActionTemplate<?>> getActions() {
        return actions;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

}
