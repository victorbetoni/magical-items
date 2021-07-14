package net.threader.magicalitems;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.function.BiConsumer;

public class MagicalItem {
    private Set<Action<?>> actions;
    private ItemStack itemStack;

    public MagicalItem(Set<Action<?>> actions, ItemStack itemStack) {
        this.actions = actions;
        this.itemStack = itemStack;
    }

    public Set<Action<?>> getActions() {
        return actions;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public <T> void applyActions(Player player1, ItemStack stack, Object obj, Object[] args) {
        actions.stream()
                .filter(action -> obj.getClass().isInstance(action.getTargetClass()))
                .forEach(action -> action.trigger(player1, stack, obj, args));
    }
}
