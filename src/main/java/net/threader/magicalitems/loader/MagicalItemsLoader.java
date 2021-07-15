package net.threader.magicalitems.loader;

import net.threader.magicalitems.MagicalItem;
import net.threader.magicalitems.MagicalItems;
import net.threader.magicalitems.cast.JSONCasters;
import net.threader.magicalitems.adaptor.JSONTemplateAdaptors;
import net.threader.magicalitems.template.ActionTemplate;
import net.threader.magicalitems.util.NBTUtils;
import net.threader.magicalitems.util.Tuple;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MagicalItemsLoader {

    private static final File ITEMS_DIR = new File(MagicalItems.instance().getDataFolder(), "items");

    @SuppressWarnings("all")
    public static Optional<MagicalItem> load(String id) throws IOException {
        JSONParser parser = new JSONParser();
        JSONObject object = null;

        InputStream fileStream = new FileInputStream(new File(ITEMS_DIR, id + ".json"));

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

        NBTUtils.injectIdentifier(stack, id);

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.stripColor((String) object.get("name")));
        JSONArray loreArray = (JSONArray) object.get("lore");
        List<String> lore = new ArrayList<>();
        loreArray.forEach(x -> lore.add(ChatColor.stripColor((String) x)));

        Set<ActionTemplate<?>> actions = new HashSet<>();

        if(object.containsKey("enchantments")) {
            ((JSONArray) object.get("enchantments"))
                    .forEach(x -> {
                        Tuple<Enchantment, Integer> tuple = JSONCasters.JSON_TO_ENCHANTMENT.apply((JSONObject) x);
                        stack.addUnsafeEnchantment(tuple.getFirst(), tuple.getSecond());
                    });
        }

        if(object.containsKey("onHit")) {
            JSONObject templates = (JSONObject) ((JSONObject) object.get("onHit")).get("templates");
            templates.keySet().forEach(x -> JSONTemplateAdaptors.findAndApply((String) x, templates.get(x)).ifPresent(actions::add));
        }

        return Optional.of(new MagicalItem(actions, stack));
    }
}
