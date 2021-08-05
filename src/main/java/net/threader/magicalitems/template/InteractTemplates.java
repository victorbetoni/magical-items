package net.threader.magicalitems.template;

import net.threader.lib.util.ObjectRetrievePath;
import net.threader.lib.util.Pair;
import net.threader.lib.util.Registry;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.function.BiFunction;

public class InteractTemplates {

    public static final Registry<BiFunction<ActionTemplateTargetSpec, Object[], ? extends ActionTemplate<?>>> REGISTRY = new Registry<>();

    public static final BiFunction<ActionTemplateTargetSpec, Object[], ArgumentativeActionTemplate<LivingEntity>> APPLY_EFFECTS =
            REGISTRY.register("apply_effects", (spec, args) ->
                    new ArgumentativeActionTemplate<>("apply_effects", LivingEntity.class, (p, i, e, a) -> {
                        for (Object obj : a) {
                            Pair<PotionEffect, Double> pair = (Pair<PotionEffect, Double>) obj;
                            if (Math.random() <= pair.getSecond()) {
                                ((LivingEntity) e).addPotionEffect(pair.getFirst());
                            }
                        }
                    }, args, spec));

    public static final BiFunction<ActionTemplateTargetSpec, Object[], ArgumentativeActionTemplate<Object>> SUMMON_THUNDER =
            REGISTRY.register("summon_thunder", (spec, args) ->
                    new ArgumentativeActionTemplate<>("summon_thunder", Object.class, (p, i, e, a) -> {
                        ObjectRetrievePath<Location> loc = new ObjectRetrievePath<>(e);
                        if(e instanceof Entity) {
                            loc.thenCall(Entity.class, "getLocation");
                        } else if (e instanceof Block) {
                            loc.thenCall(Block.class, "getLocation");
                        }
                        double chance = (double) a[0];
                        Location location = loc.apply();
                        if(Math.random() <= chance) {
                            loc.apply().getWorld().strikeLightning(location);
                        }
                    }, args, spec));
}
