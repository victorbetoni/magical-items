package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ActionTemplate<T> {
    private String id;
    private Action action;

    public ActionTemplate(String id, Action action) {
        this.id = id;
        this.action = action;
    }

    public void apply(Player holder, ItemStack stack, Object param, Object[] args) {
        action.trigger(holder, stack, param, args);
    }

    public String getIdentifier() {
        return id;
    }
}
