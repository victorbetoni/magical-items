package net.threader.magicalitems.factory;

import net.threader.magicalitems.BasicTemplates;
import net.threader.magicalitems.cast.JSONCasters;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONTemplateAdaptors {
    public static TemplateAdaptor<JSONArray> APPLY_EFFECTS = (array) -> {
        PotionEffect[] effects = new PotionEffect[array.size()];
        for(int i = 0; i < array.size(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            effects[i] = JSONCasters.JSON_TO_POTION_EFFECT.apply(obj);
        }
        return BasicTemplates.APPLY_EFFECTS.apply(effects);
    };
}
