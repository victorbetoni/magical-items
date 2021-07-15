package net.threader.magicalitems.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Registry<T> {
    private Map<String, T> registry = new HashMap<>();

    public <U extends T> U register(String id, U template) {
        registry.put(id, template);
        return template;
    }

    public Optional<T> find(String id) {
        return Optional.ofNullable(registry.get(id));
    }
 }
