package net.threader.magicalitems.cast;

import net.threader.magicalitems.BasicTemplates;
import net.threader.magicalitems.template.LivingEntityActionTemplate;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.json.simple.JSONObject;

import java.util.function.Function;

public class TemplateCasters {
    public static Function<JSONObject, LivingEntityActionTemplate> APPLY_EFFECTS_CASTER = (obj) -> {
        PotionEffectType effect = PotionEffectType.getByName((String) obj.get("id"));
        int amplifier = (int) obj.get("amplifier");
        int duration = (int) obj.get("duration");
        return BasicTemplates.APPLY_EFFECTS.apply(new Object[]{new PotionEffect(effect, duration, amplifier)});
    };
}
