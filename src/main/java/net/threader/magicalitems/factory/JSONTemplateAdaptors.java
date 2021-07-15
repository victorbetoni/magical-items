package net.threader.magicalitems.factory;

import net.threader.magicalitems.BasicTemplates;
import net.threader.magicalitems.cast.JSONCasters;
import net.threader.magicalitems.registry.Registry;
import net.threader.magicalitems.template.ActionTemplate;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class JSONTemplateAdaptors {
    public static final Registry<TemplateAdaptor<?>> REGISTRY = new Registry<>();

    public static TemplateAdaptor<JSONArray> APPLY_EFFECTS = REGISTRY.register("apply_effects", new TemplateAdaptor<JSONArray>() {
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
            return BasicTemplates.REGISTRY.find(getTargetTemplate()).get().apply(effects);
        }

        @Override
        public Class<JSONArray> getTargetClass() {
            return JSONArray.class;
        }
    });

    public static Optional<ActionTemplate<?>> findAndApply(String id, Object param) {
        AtomicReference<Optional<ActionTemplate<?>>> template = new AtomicReference<>(Optional.empty());
        REGISTRY.find(id).ifPresent(x -> template.set(Optional.of(x.forceAdapt(param))));
        return template.get();
    }

}
