package net.threader.magicalitems.factory;

import net.threader.magicalitems.BasicTemplates;
import net.threader.magicalitems.cast.JSONCasters;
import net.threader.magicalitems.template.ActionTemplate;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONTemplateAdaptors {
    public static TemplateAdaptor<JSONArray> APPLY_EFFECTS = new TemplateAdaptor<JSONArray>() {
        @Override
        public String getTargetTemplate() {
            return "apply_effects";
        }

        @Override
        public ActionTemplate<?> adapt(JSONArray param) {
            PotionEffect[] effects = new PotionEffect[param.size()];
            for(int i = 0; i < param.size(); i++) {
                JSONObject obj = (JSONObject) param.get(i);
                effects[i] = JSONCasters.JSON_TO_POTION_EFFECT.apply(obj);
            }
            return BasicTemplates.APPLY_EFFECTS.apply(effects);
        }

        @Override
        public Class<JSONArray> getTargetClass() {
            return JSONArray.class;
        }
    };

    
}
