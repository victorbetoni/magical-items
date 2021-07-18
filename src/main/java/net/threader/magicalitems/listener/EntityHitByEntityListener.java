package net.threader.magicalitems.listener;

import net.threader.magicalitems.event.MagicalEntityHitEvent;
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
                MagicalEntityHitEvent magicalEvent = new MagicalEntityHitEvent((LivingEntity) event.getDamager(), (LivingEntity) event.getEntity(), ArrowEntityWatcher.ARROW_ID_MAP.get(entityId));
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
                MagicalEntityHitEvent magicalEvent =
                        new MagicalEntityHitEvent(damager, (LivingEntity) event.getEntity(), damager.getEquipment().getItemInMainHand());
                Bukkit.getPluginManager().callEvent(magicalEvent);
            }
        }
    }
}
