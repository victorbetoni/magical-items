package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class LivingEntityActionTemplate extends ActionTemplate {
    private Action<LivingEntity> action;

    public LivingEntityActionTemplate(String id, Action<LivingEntity> action) {
        super(id);
        this.action = action;
    }

    public Action<LivingEntity> getAction() {
        return action;
    }
}
