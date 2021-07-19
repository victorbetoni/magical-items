package net.threader.magicalitems.listener;

import net.threader.magicalitems.event.MagicalLivingHitEvent;
import net.threader.magicalitems.event.MagicalLivingInteractEvent;
import net.threader.magicalitems.util.NBTUtils;
import net.threader.magicalitems.watcher.ArrowEntityWatcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class LivingEntityListeners implements Listener {

    @EventHandler
    public void onShot(EntityShootBowEvent event) {
        if(NBTUtils.isMagical(event.getBow())) {
            ArrowEntityWatcher.ARROW_ID_MAP.put(event.getProjectile().getEntityId(), event.getBow());
        }
    }

    @EventHandler
    public void handleArrowHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow) {
            int entityId = event.getDamager().getEntityId();
            if(ArrowEntityWatcher.ARROW_ID_MAP.containsKey(entityId)) {
                MagicalLivingHitEvent magicalEvent = new MagicalLivingHitEvent((LivingEntity) event.getDamager(), (LivingEntity) event.getEntity(), ArrowEntityWatcher.ARROW_ID_MAP.get(entityId));
                ArrowEntityWatcher.ARROW_ID_MAP.remove(entityId);
                Bukkit.getPluginManager().callEvent(magicalEvent);
            }
        }
    }

    @EventHandler
    public void handleCommonHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof LivingEntity && event.getEntity() instanceof LivingEntity) {
            LivingEntity damager = (LivingEntity) event.getDamager();
            if(damager.getEquipment().getItemInMainHand().getType() != Material.AIR
                    && NBTUtils.isMagical(damager.getEquipment().getItemInMainHand())) {
                MagicalLivingHitEvent magicalEvent =
                        new MagicalLivingHitEvent(damager, (LivingEntity) event.getEntity(), damager.getEquipment().getItemInMainHand());
                Bukkit.getPluginManager().callEvent(magicalEvent);
            }
        }
    }

    @EventHandler
    public void handleInteract(PlayerInteractEntityEvent event) {
        if(event.getRightClicked() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) event.getRightClicked();
            ItemStack stack = event.getPlayer().getEquipment().getItemInMainHand();
            if(stack.getType() != Material.AIR && NBTUtils.isMagical(stack)) {
                MagicalLivingInteractEvent magicalEvent =
                        new MagicalLivingInteractEvent(event.getPlayer(), target, stack);
                Bukkit.getPluginManager().callEvent(magicalEvent);
            }
        }
    }

}
