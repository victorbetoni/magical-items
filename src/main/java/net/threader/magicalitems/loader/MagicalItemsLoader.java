package net.threader.magicalitems.loader;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.threader.magicalitems.MagicalItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MagicalItemsLoader {
    public static Optional<MagicalItem> load(String id) throws IOException {
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        InputStream fileStream = MagicalItemsLoader.class.getResourceAsStream("/items/" + id + ".json");
        if(fileStream == null) {
            throw new IOException("Item's json file not found: " + id + ".json");
        }
        try(Reader reader = new InputStreamReader(fileStream)) {
            object = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
        ItemStack stack = new ItemStack((Objects.requireNonNull(Material.matchMaterial((String) object.get("material")))));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.stripColor((String) object.get("name")));
        JSONArray loreArray = (JSONArray) object.get("lore");
        List<String> lore = new ArrayList<>();
        loreArray.forEach(x -> lore.add(ChatColor.stripColor((String) x)));

        net.minecraft.server.v1_16_R3.ItemStack craftStack = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound compound = craftStack.getTag();
        compound.setString("magical_item_id", id);
        craftStack.save(compound);

        return Optional.empty();
    }
}
