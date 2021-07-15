package net.threader.magicalitems.template;

import net.threader.magicalitems.registry.Registry;
import net.threader.magicalitems.template.ActionTemplate;
import net.threader.magicalitems.template.ArgumentativeActionTemplate;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.function.Function;

public class BasicTemplates {

    public static final Registry<Function<Object[], ArgumentativeActionTemplate<LivingEntity>>> REGISTRY = new Registry<>();

    public static final Function<Object[], ArgumentativeActionTemplate<LivingEntity>> APPLY_EFFECTS = REGISTRY.register(
            "apply_effects", (args) ->
                    new ArgumentativeActionTemplate<LivingEntity>("apply_effects", LivingEntity.class,
                            (p, i, e, a) -> {
                                for (Object o : a) {
                                    ((LivingEntity) e).addPotionEffect((PotionEffect) o);
                                }
                            }, args));
}
