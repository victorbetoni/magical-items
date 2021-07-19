package net.threader.magicalitems.event;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class MagicalBlockHitEvent extends MagicalItemEvent {
    private Block block;

    public MagicalBlockHitEvent(LivingEntity holder, ItemStack source, Block block) {
        super(holder, source);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
