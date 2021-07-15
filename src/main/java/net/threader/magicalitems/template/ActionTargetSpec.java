package net.threader.magicalitems.template;

import net.threader.magicalitems.event.LivingEntityHitByMagicalItemEvent;
import net.threader.magicalitems.event.MagicalItemEvent;

public class ActionTargetSpec {

    public static ActionTargetSpec LIVING_HIT_TARGET = new ActionTargetSpec(LivingEntityHitByMagicalItemEvent.class, "getTarget");

    private Class<?> clazz;
    private String method;

    public <T extends MagicalItemEvent> ActionTargetSpec(Class<T> clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }
}
