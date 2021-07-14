package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class LivingEntityActionTemplate extends ActionTemplate {
    private Action<LivingEntity> action;
    private Object[] args;

    public LivingEntityActionTemplate(String id, Action<LivingEntity> action, Object[] args) {
        super(id);
        this.action = action;
        this.args = args;
    }

    public Action<LivingEntity> getAction() {
        return action;
    }
}
