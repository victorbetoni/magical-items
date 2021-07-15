package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ActionTemplate<T> {
    protected String id;
    protected Action action;
    protected Class<T> targetClass;
    protected ActionTemplateTargetSpec reflectionSpecs;

    public ActionTemplate(String id, Class<T> targetClass, Action action, ActionTemplateTargetSpec reflectionSpecs) {
        this.id = id;
        this.action = action;
        this.targetClass = targetClass;
        this.reflectionSpecs = reflectionSpecs;
    }

    public void apply(LivingEntity holder, ItemStack stack, Object param) {
        action.trigger(holder, stack, param);
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public String getIdentifier() {
        return id;
    }

    public ActionTemplateTargetSpec getReflectionSpecs() {
        return reflectionSpecs;
    }
}
