package net.threader.magicalitems.adaptor;

import net.threader.magicalitems.template.ActionTemplate;

public interface TemplateAdaptor<U> {
    default ActionTemplate<?> forceAdapt(String path, Object param) {
        return adapt(path, getTargetClass().cast(param));
    }

    String getTargetTemplate();

    ActionTemplate<?> adapt(String path, U param);

    Class<U> getTargetClass();
}
