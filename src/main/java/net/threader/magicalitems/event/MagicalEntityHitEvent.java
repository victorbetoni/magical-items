package net.threader.magicalitems.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class MagicalEntityHitEvent extends MagicalItemEvent {
    private LivingEntity livingEntity;
    private LivingEntity target;

    public MagicalEntityHitEvent(LivingEntity livingEntity, LivingEntity target, ItemStack source) {
        super(source);
        this.target = target;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    public LivingEntity getTarget() {
        return target;
    }
}