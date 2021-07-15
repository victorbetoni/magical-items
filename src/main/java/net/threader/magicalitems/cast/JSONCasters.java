package net.threader.magicalitems.cast;

import net.threader.magicalitems.MagicalItems;
import net.threader.magicalitems.util.Tuple;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.json.simple.JSONObject;

import java.util.Objects;
import java.util.function.Function;

public class JSONCasters {
    public static Function<JSONObject, PotionEffect> JSON_TO_POTION_EFFECT = (json) -> {
        Long amplifier = (Long) json.get("amplifier");
        String id = (String) json.get("id");
        Long duration = (Long) json.get("duration");
        return new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(id)), duration.intValue(), amplifier.intValue());
    };
    public static Function<JSONObject, Tuple<Enchantment, Integer>> JSON_TO_ENCHANTMENT = (json) -> {
        Long amplifier = (Long) json.get("amplifier");
        String id = (String) json.get("id");
        return new Tuple<>(Enchantment.getByKey(NamespacedKey.minecraft(id)), amplifier.intValue());
    };
}
