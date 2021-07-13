package net.threader.magicalitems;

import org.bukkit.plugin.java.JavaPlugin;

public class MagicalItems extends JavaPlugin {
    private static MagicalItems instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static MagicalItems instance() {
        return instance;
    }
}
