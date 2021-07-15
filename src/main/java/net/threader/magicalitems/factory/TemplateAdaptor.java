package net.threader.magicalitems.factory;

import net.threader.magicalitems.template.ActionTemplate;
import org.json.simple.JSONObject;

public interface TemplateAdaptor<U> {
    ActionTemplate<?> adapt(U param);
}
