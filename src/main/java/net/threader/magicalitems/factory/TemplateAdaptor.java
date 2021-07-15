package net.threader.magicalitems.factory;

import net.threader.magicalitems.template.ActionTemplate;

public interface TemplateAdaptor<U> {
    String getTargetTemplate();
    ActionTemplate<?> adapt(U param);
    Class<U> getTargetClass();
}
