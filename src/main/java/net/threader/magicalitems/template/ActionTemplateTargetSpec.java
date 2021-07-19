package net.threader.magicalitems.template;

import net.threader.magicalitems.event.MagicalBlockInteractEvent;
import net.threader.magicalitems.event.MagicalLivingHitEvent;
import net.threader.magicalitems.event.MagicalItemEvent;

public class ActionTemplateTargetSpec {

    public static ActionTemplateTargetSpec LIVING_HIT_TARGET = new ActionTemplateTargetSpec(MagicalLivingHitEvent.class, "getTarget");
    public static ActionTemplateTargetSpec BLOCK_INTERACT_TARGET = new ActionTemplateTargetSpec(MagicalBlockInteractEvent.class, "getBlock");
    public static ActionTemplateTargetSpec BLOCK_HIT_TARGET = new ActionTemplateTargetSpec(MagicalBlockInteractEvent.class, "getBlock");

    private Class<?> clazz;
    private String method;

    public <T extends MagicalItemEvent> ActionTemplateTargetSpec(Class<T> clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }

    public Class<?> getTargetEventClass() {
        return clazz;
    }

    public String getMethod() {
        return method;
    }
}
