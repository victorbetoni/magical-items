package net.threader.magicalitems.util;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTUtils {
    public static boolean isMagical(ItemStack stack) {
        net.minecraft.server.v1_16_R3.ItemStack craftStack = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound compound = craftStack.getTag();
        return compound != null && compound.getKeys().contains("magical_item_id");
    }

    public static String extractIdentifier(ItemStack stack) {
        net.minecraft.server.v1_16_R3.ItemStack craftStack = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound compound = craftStack.getTag();
        return compound.getString("magical_item_id");
    }

    public static void injectIdentifier(ItemStack stack, String id) {
        net.minecraft.server.v1_16_R3.ItemStack craftStack = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound compound = craftStack.hasTag() ? craftStack.getTag() : new NBTTagCompound();
        compound.setString("magical_item_id", id);
        craftStack.save(compound);
    }
}
