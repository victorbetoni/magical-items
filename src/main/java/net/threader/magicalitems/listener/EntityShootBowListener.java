package net.threader.magicalitems.listener;

import net.threader.magicalitems.util.NBTUtils;
import net.threader.magicalitems.watcher.ArrowEntityWatcher;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityShootBowListener implements Listener {
    @EventHandler
    public void onShot(EntityShootBowEvent event) {
        if(NBTUtils.isMagical(event.getBow())) {
            ArrowEntityWatcher.ARROW_ID_MAP.put(event.getProjectile().getEntityId(), event.getBow());
        }
    }
}
