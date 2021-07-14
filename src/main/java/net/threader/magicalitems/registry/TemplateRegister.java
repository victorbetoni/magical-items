package net.threader.magicalitems.registry;

import net.threader.magicalitems.action.Action;
import net.threader.magicalitems.template.ActionTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TemplateRegister {
    private Map<String, ActionTemplate> templates = new HashMap<>();

    public <T extends ActionTemplate> ActionTemplate register(T template) {
        templates.put(template.getIdentifier(), template);
        return template;
    }

    public <T extends ActionTemplate> Optional<T> find(String id) {
        return Optional.ofNullable((T) templates.get(id));
    }
 }
