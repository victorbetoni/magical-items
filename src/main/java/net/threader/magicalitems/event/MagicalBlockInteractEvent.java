package net.threader.magicalitems.event;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class MagicalBlockInteractEvent extends MagicalItemEvent {
    private Block block;

    public MagicalBlockInteractEvent(ItemStack source, Block block) {
        super(source);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
