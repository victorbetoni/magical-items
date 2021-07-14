package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;

public class ActionTemplate<T> {
    private String id;
    private Action<T> action;

    public ActionTemplate(String id, Action<T> action) {
        this.id = id;
        this.action = action;
    }

    public Action<T> getAction() {
        return action;
    }

    public String getIdentifier() {
        return id;
    }
}
