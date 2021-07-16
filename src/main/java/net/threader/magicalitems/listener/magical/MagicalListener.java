package net.threader.magicalitems.listener.magical;

import net.threader.magicalitems.MagicalItem;
import net.threader.magicalitems.MagicalItems;
import net.threader.magicalitems.event.MagicalItemEvent;
import net.threader.magicalitems.util.NBTUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MagicalListener implements Listener {

    @EventHandler
    public void handleMagicalEvent(MagicalItemEvent event) {
        String id = NBTUtils.extractIdentifier(event.getItem());
        MagicalItem instance = MagicalItems.MAGICAL_REGISTRY.find(id).get();
        System.out.println("Clazz: " + event.getClass().toString());
        instance.getActions().forEach(action -> {
            System.out.println("Encontrou uma ação");
            if(action.getReflectionSpecs().getTargetEventClass().equals(event.getClass())) {
                System.out.println(this.getClass().toString() + " 1");
                try {
                    System.out.println("Passou 2");
                    Class<?> eventClass = event.getClass();
                    Method method = eventClass.getMethod(action.getReflectionSpecs().getMethod());
                    Object param = method.invoke(event, null);
                    action.apply(event.getHolder(), event.getItem(), param);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
