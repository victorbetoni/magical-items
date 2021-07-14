package net.threader.magicalitems;

import net.threader.magicalitems.template.LivingEntityActionTemplate;
import org.bukkit.potion.PotionEffect;

public class BasicTemplates {
    public static LivingEntityActionTemplate applyEffect =
            new LivingEntityActionTemplate("apply_effects", (p, i, e, a) -> {
                for (Object o : a) {
                    e.addPotionEffect((PotionEffect) o);
                }
    });
}
