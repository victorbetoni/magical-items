package net.threader.magicalitems.util;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.threader.magicalitems.MagicalItems;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NBTUtils {

    private static final NamespacedKey MAGICAL_KEY = new NamespacedKey(MagicalItems.instance(), "magical-item-id");

    public static boolean isMagical(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        return meta.getPersistentDataContainer().has(MAGICAL_KEY, PersistentDataType.STRING);
    }

    public static String extractIdentifier(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        return meta.getPersistentDataContainer().get(MAGICAL_KEY, PersistentDataType.STRING);
    }

    public static void injectIdentifier(ItemStack stack, String id) {
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(MAGICAL_KEY, PersistentDataType.STRING, id);
        stack.setItemMeta(meta);
    }
}
