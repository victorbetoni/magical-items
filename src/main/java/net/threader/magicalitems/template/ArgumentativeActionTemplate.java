package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArgumentativeActionTemplate<T> extends ActionTemplate<T> {
    private Object[] args;

    public ArgumentativeActionTemplate(String id, Class<T> targetClass, Action action, Object[] args, ActionTemplateTargetSpec spec) {
        super(id, targetClass, action, spec);
    }

    @Override
    public void apply(LivingEntity holder, ItemStack stack, Object param) {
        action.trigger(holder, stack, param, args);
    }

}
