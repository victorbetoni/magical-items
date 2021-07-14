package net.threader.magicalitems.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class LivingEntityHitByMagicalItemEvent extends MagicalItemInteractEvent {

    public LivingEntityHitByMagicalItemEvent(LivingEntity entity, LivingEntity target, ItemStack source) {
        super(entity, target, source);
    }

}
