package net.threader.magicalitems;

import net.threader.lib.util.Registry;
import net.threader.magicalitems.command.MagicalItemCommand;
import net.threader.magicalitems.listener.BlockListeners;
import net.threader.magicalitems.listener.LivingEntityListeners;
import net.threader.magicalitems.listener.magical.MagicalListener;
import net.threader.magicalitems.loader.MagicalItemsLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MagicalItems extends JavaPlugin {
    private static MagicalItems instance;
    public static Registry<String, MagicalItem> MAGICAL_REGISTRY;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new BlockListeners(), this);
        Bukkit.getPluginManager().registerEvents(new LivingEntityListeners(), this);
        Bukkit.getPluginManager().registerEvents(new MagicalListener(), this);

        getCommand("magicalitem").setExecutor(new MagicalItemCommand());

        this.reload();
    }

    public static MagicalItems instance() {
        return instance;
    }

    public void reload() {
        this.reloadConfig();
        MAGICAL_REGISTRY = new Registry<>();
        this.getConfig().getStringList("items").forEach(x -> {
            try {
                MagicalItemsLoader.load(x).ifPresent(y -> MAGICAL_REGISTRY.register(x, y));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
