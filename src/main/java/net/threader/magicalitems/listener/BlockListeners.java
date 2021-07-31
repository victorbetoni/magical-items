package net.threader.magicalitems.listener;

import net.threader.magicalitems.event.MagicalBlockHitEvent;
import net.threader.magicalitems.event.MagicalBlockInteractEvent;
import net.threader.magicalitems.util.MagicalItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListeners implements Listener {

    @EventHandler
    public void handleBlockInteract(PlayerInteractEvent event) {
        LivingEntity entity = event.getPlayer();
        ItemStack stack = entity.getEquipment().getItemInMainHand();
        if (stack.getType() != Material.AIR && MagicalItemUtils.isMagical(stack)) {
            Block block = event.getClickedBlock();
            switch (event.getAction()) {
                case LEFT_CLICK_BLOCK:
                    MagicalBlockHitEvent hit = new MagicalBlockHitEvent(entity, stack, block);
                    Bukkit.getPluginManager().callEvent(hit);
                    break;
                case RIGHT_CLICK_BLOCK:
                    MagicalBlockInteractEvent interact = new MagicalBlockInteractEvent(entity, stack, block);
                    Bukkit.getPluginManager().callEvent(interact);
                    break;
            }
        }
    }
}
