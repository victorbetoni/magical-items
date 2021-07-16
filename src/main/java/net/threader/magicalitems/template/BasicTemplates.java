package net.threader.magicalitems.template;

import net.threader.magicalitems.registry.Registry;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.function.Function;

public class BasicTemplates {

    public static final Registry<Function<Object[], ArgumentativeActionTemplate<LivingEntity>>> REGISTRY = new Registry<>();

    public static final Function<Object[], ArgumentativeActionTemplate<LivingEntity>> APPLY_EFFECTS = REGISTRY.register(
            "apply_effects", args ->
                    new ArgumentativeActionTemplate<>("apply_effects", LivingEntity.class, (p, i, e, a) -> {
                        System.out.println("Entrou na ação");
                        for (Object obj : a) {
                            ((LivingEntity) e).addPotionEffect((PotionEffect) obj);
                        }
            }, args, ActionTemplateTargetSpec.LIVING_HIT_TARGET));
}
