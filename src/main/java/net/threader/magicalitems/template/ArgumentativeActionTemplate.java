package net.threader.magicalitems.template;

import net.threader.magicalitems.action.Action;

public class ArgumentativeActionTemplate<T> extends ActionTemplate<T> {
    private Object[] args;

    public ArgumentativeActionTemplate(String id, Action action, Object[] args) {
        super(id, action);
    }

    public Object[] getArguments() {
        return args;
    }
}
