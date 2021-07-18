package net.threader.magicalitems.template;

import net.threader.magicalitems.registry.Registry;
import net.threader.magicalitems.util.Tuple;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.function.BiFunction;
import java.util.function.Function;

public class InteractTemplates {

    public static final Registry<BiFunction<ActionTemplateTargetSpec, Object[], ? extends ActionTemplate<?>>> REGISTRY = new Registry<>();

    public static final BiFunction<ActionTemplateTargetSpec, Object[], ArgumentativeActionTemplate<LivingEntity>> APPLY_EFFECTS =
            REGISTRY.register("apply_effects", (spec, args) ->
                    new ArgumentativeActionTemplate<>("apply_effects", LivingEntity.class, (p, i, e, a) -> {
                        for (Object obj : a) {
                            Tuple<PotionEffect, Double> tuple = (Tuple<PotionEffect, Double>) obj;
                            if (Math.random() <= tuple.getSecond()) {
                                ((LivingEntity) e).addPotionEffect(tuple.getFirst());
                            }
                        }
                    }, args, spec));

    public static final BiFunction<ActionTemplateTargetSpec, Object[], ArgumentativeActionTemplate<Location>> SUMMON_THUNDER =
            REGISTRY.register("summon_thunder", (spec, args) ->
                    new ArgumentativeActionTemplate<>("apply_effects", Location.class, (p, i, e, a) -> {
                        double chance = (double) a[0];
                        if(Math.random() <= chance) {
                            Location loc = (Location) e;
                            loc.getWorld().strikeLightning(loc);
                        }
                    }, args, spec));
}