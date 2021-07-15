package net.threader.magicalitems.factory;

import net.threader.magicalitems.template.ActionTemplate;
import org.json.simple.JSONObject;

public interface TemplateAdaptor<U> {
    String getTargetTemplate();
    ActionTemplate<?> adapt(U param);
    Class<U> getTargetClass();
}
