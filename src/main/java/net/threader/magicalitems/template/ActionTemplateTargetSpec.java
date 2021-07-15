package net.threader.magicalitems.template;

import net.threader.magicalitems.event.LivingEntityHitByMagicalItemEvent;
import net.threader.magicalitems.event.MagicalItemEvent;

public class ActionTemplateTargetSpec {

    public static ActionTemplateTargetSpec LIVING_HIT_TARGET = new ActionTemplateTargetSpec(LivingEntityHitByMagicalItemEvent.class, "getTarget");

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
