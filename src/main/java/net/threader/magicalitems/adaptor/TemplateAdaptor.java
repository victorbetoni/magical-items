package net.threader.magicalitems.adaptor;

import net.threader.magicalitems.template.ActionTemplate;

public interface TemplateAdaptor<U> {
    default ActionTemplate<?> forceAdapt(Object param) {
        return adapt(getTargetClass().cast(param));
    }

    String getTargetTemplate();

    ActionTemplate<?> adapt(U param);

    Class<U> getTargetClass();
}
