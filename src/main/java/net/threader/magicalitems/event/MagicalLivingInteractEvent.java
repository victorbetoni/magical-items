package net.threader.magicalitems.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class MagicalLivingInteractEvent extends MagicalItemEvent {
    private LivingEntity target;

    public MagicalLivingInteractEvent(LivingEntity livingEntity, LivingEntity target, ItemStack source) {
        super(livingEntity, source);
        this.target = target;
    }

    public LivingEntity getTarget() {
        return target;
    }
}
