package net.threader.magicalitems;

import net.threader.magicalitems.command.MagicalItemCommand;
import net.threader.magicalitems.listener.EntityHitByEntityListener;
import net.threader.magicalitems.listener.EntityShootBowListener;
import net.threader.magicalitems.listener.magical.MagicalListener;
import net.threader.magicalitems.loader.MagicalItemsLoader;
import net.threader.magicalitems.registry.Registry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

public class MagicalItems extends JavaPlugin {
    private static MagicalItems instance;
    public static Registry<MagicalItem> MAGICAL_REGISTRY;

    @Override
    public void onEnable() {
        instance = this;

        Map<String, ?> bro = null;
        try {
            Field field = PotionEffectType.class.getDeclaredField("byName");
            field.setAccessible(true);
            bro = (Map<String, ?>) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        bro.keySet().forEach(System.out::println);

        this.saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EntityHitByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityShootBowListener(), this);
        Bukkit.getPluginManager().registerEvents(new MagicalListener(), this);

        getCommand("magicalitem").setExecutor(new MagicalItemCommand());

        MAGICAL_REGISTRY = new Registry<>();
        this.getConfig().getStringList("items").forEach(x -> {
            try {
                MagicalItemsLoader.load(x).ifPresent(y -> MAGICAL_REGISTRY.register(x, y));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static MagicalItems instance() {
        return instance;
    }
}
