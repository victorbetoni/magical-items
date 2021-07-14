package net.threader.magicalitems.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class MagicalItemInteractEvent extends MagicalItemEvent {
    private LivingEntity livingEntity;
    private LivingEntity target;

    public MagicalItemInteractEvent(LivingEntity livingEntity, LivingEntity target, ItemStack source) {
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
