package net.threader.magicalitems.cast;

import net.threader.lib.util.Pair;
import net.threader.lib.util.Triple;
import net.threader.magicalitems.MagicalItems;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.persistence.PersistentDataType;
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

    public static Function<JSONObject, Pair<Enchantment, Integer>> JSON_TO_ENCHANTMENT = (json) -> {
        Long amplifier = (Long) json.get("amplifier");
        String id = (String) json.get("id");
        return new Pair<>(Enchantment.getByKey(NamespacedKey.minecraft(id)), amplifier.intValue());
    };

    public static Function<JSONObject, Triple<NamespacedKey, PersistentDataType<?,?>, Object>> JSON_TO_PERSISTENT_DATA = (json) -> {
        NamespacedKey key = new NamespacedKey(MagicalItems.instance(), (String) json.get("key"));
        Object value = json.get("value");
        PersistentDataType<?,?> type = PersistentDataType.STRING;
        if(value instanceof Number) {
            type = PersistentDataType.INTEGER;
        }
        return new Triple<>(key, type, value);
    };
}
