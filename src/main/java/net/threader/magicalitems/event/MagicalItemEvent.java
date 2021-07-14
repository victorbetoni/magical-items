package net.threader.magicalitems.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class MagicalItemEvent extends Event {
    protected static final HandlerList handlers = new HandlerList();
    protected ItemStack source;
    protected LivingEntity holder;

    public MagicalItemEvent(ItemStack source) {
        this.source = source;
    }

    public ItemStack getItem() {
        return source;
    }

    public LivingEntity getHolder() {
        return holder;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
