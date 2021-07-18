package net.threader.magicalitems.adaptor;

import net.threader.magicalitems.template.ActionTemplate;
import net.threader.magicalitems.template.ActionTemplateTargetSpec;

public interface TemplateAdaptor<U> {
    default ActionTemplate<?> forceAdapt(ActionTemplateTargetSpec spec, Object param) {
        return adapt(spec, getTargetClass().cast(param));
    }

    String getTargetTemplate();

    ActionTemplate<?> adapt(ActionTemplateTargetSpec spec, U param);

    Class<U> getTargetClass();
}
