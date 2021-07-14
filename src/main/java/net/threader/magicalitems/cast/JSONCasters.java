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
        int amplifier = (int) json.get("amplifier");
        String id = (String) json.get("id");
        int duration = (int) json.get("duration");
        return new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(id)), duration, amplifier);
    };
    public static Function<JSONObject, Tuple<Enchantment, Integer>> JSON_TO_ENCHANTMENT = (json) -> {
        int amplifier = (int) json.get("amplifier");
        String id = (String) json.get("id");
        return new Tuple<>(Enchantment.getByKey(new NamespacedKey(MagicalItems.instance(), id)), amplifier);
    };
}
