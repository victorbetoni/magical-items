package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;

public abstract class ActionTemplate {
    private String id;

    public ActionTemplate(String id) {
        this.id = id;
    }

    public abstract Action<?> getAction();

    public String getIdentifier() {
        return id;
    }
}
