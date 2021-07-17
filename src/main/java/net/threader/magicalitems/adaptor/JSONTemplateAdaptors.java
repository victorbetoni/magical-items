package net.threader.magicalitems.adaptor;

import net.threader.magicalitems.template.ActionTemplateTargetSpec;
import net.threader.magicalitems.template.InteractTemplates;
import net.threader.magicalitems.cast.JSONCasters;
import net.threader.magicalitems.registry.Registry;
import net.threader.magicalitems.template.ActionTemplate;
import net.threader.magicalitems.util.Tuple;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class JSONTemplateAdaptors {

    static {
        Registry<ActionTemplateTargetSpec> dummy = new Registry<>();
        dummy.register("living.interact.hit", ActionTemplateTargetSpec.LIVING_HIT_TARGET);
        JSON_PATH_ADAPTOR = dummy;
    }

    public static Registry<ActionTemplateTargetSpec> JSON_PATH_ADAPTOR;
    public static final Registry<TemplateAdaptor<?>> REGISTRY = new Registry<>();

    public static TemplateAdaptor<JSONArray> APPLY_EFFECTS = REGISTRY.register("apply_effects", new TemplateAdaptor<JSONArray>() {
        @Override
        public String getTargetTemplate() {
            return "apply_effects";
        }

        @Override
        public ActionTemplate<?> adapt(String path, JSONArray param) {
            Object[] effects = new Object[param.size()];
            for(int i = 0; i < param.size(); i++) {
                JSONObject obj = (JSONObject) param.get(i);
                effects[i] = JSONCasters.JSON_TO_POTION_EFFECT.apply(obj);
                double chance = ((Number) obj.get("")).doubleValue();
                effects[i] = new Tuple<>(JSONCasters.JSON_TO_POTION_EFFECT.apply(obj), chance);
            }

            if(!JSON_PATH_ADAPTOR.find(path).isPresent()) {
                return null;
            }

            return InteractTemplates.REGISTRY.find(getTargetTemplate()).get().apply(JSON_PATH_ADAPTOR.find(path).get(), effects);
        }

        @Override
        public Class<JSONArray> getTargetClass() {
            return JSONArray.class;
        }
    });

    public static Optional<ActionTemplate<?>> findAndApply(String id, Object param, String path) {
        AtomicReference<Optional<ActionTemplate<?>>> template = new AtomicReference<>(Optional.empty());
        REGISTRY.find(id).ifPresent(x -> template.set(Optional.of(x.forceAdapt(path, param))));
        return template.get();
    }

}
