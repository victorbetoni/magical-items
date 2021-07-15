package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ActionTemplate<T> {
    protected String id;
    protected Action action;
    protected Class<T> targetClass;
    protected ActionTargetSpec reflectionSpecs;

    public ActionTemplate(String id, Class<T> targetClass, Action action, ActionTargetSpec reflectionSpecs) {
        this.id = id;
        this.action = action;
        this.targetClass = targetClass;
        this.reflectionSpecs = reflectionSpecs;
    }

    public void apply(Player holder, ItemStack stack, Object param) {
        action.trigger(holder, stack, param);
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public String getIdentifier() {
        return id;
    }

    public ActionTargetSpec getReflectionSpecs() {
        return reflectionSpecs;
    }
}
