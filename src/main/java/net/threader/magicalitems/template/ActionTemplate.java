package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ActionTemplate<T> {
    protected String id;
    protected Action action;

    public ActionTemplate(String id, Action action) {
        this.id = id;
        this.action = action;
    }

    public void apply(Player holder, ItemStack stack, Object param) {
        action.trigger(holder, stack, param);
    }

    public String getIdentifier() {
        return id;
    }
}
