package net.threader.magicalitems.listener;

import net.threader.magicalitems.event.LivingEntityHitByMagicalItemEvent;
import net.threader.magicalitems.util.NBTUtils;
import net.threader.magicalitems.watcher.ArrowEntityWatcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityHitByEntityListener implements Listener {
    @EventHandler
    public void handleArrowHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow) {
            int entityId = event.getDamager().getEntityId();
            if(ArrowEntityWatcher.ARROW_ID_MAP.containsKey(entityId)) {
                LivingEntityHitByMagicalItemEvent magicalEvent = new LivingEntityHitByMagicalItemEvent((LivingEntity) event.getDamager(), (LivingEntity) event.getEntity(), ArrowEntityWatcher.ARROW_ID_MAP.get(entityId));
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
                LivingEntityHitByMagicalItemEvent magicalEvent =
                        new LivingEntityHitByMagicalItemEvent(damager, (LivingEntity) event.getEntity(), damager.getEquipment().getItemInMainHand());
                Bukkit.getPluginManager().callEvent(magicalEvent);
            }
        }
    }
}
