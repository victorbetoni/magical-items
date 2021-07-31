package net.threader.magicalitems.loader;

import net.threader.magicalitems.MagicalItem;
import net.threader.magicalitems.MagicalItems;
import net.threader.magicalitems.cast.JSONCasters;
import net.threader.magicalitems.adaptor.JSONTemplateAdaptors;
import net.threader.magicalitems.template.ActionTemplate;
import net.threader.magicalitems.template.ActionTemplateTargetSpec;
import net.threader.magicalitems.util.MagicalItemUtils;
import net.threader.magicalitems.util.Pair;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class MagicalItemsLoader {

    static {
        Map<String, ActionTemplateTargetSpec> dummy = new HashMap<>();
        dummy.put("living.hit", ActionTemplateTargetSpec.LIVING_HIT_TARGET);
        dummy.put("block.interact", ActionTemplateTargetSpec.BLOCK_INTERACT_TARGET);
        dummy.put("block.hit", ActionTemplateTargetSpec.BLOCK_HIT_TARGET);
        PATH_TO_TARGET_MAPPING = dummy;
    }

    private static final Map<String, ActionTemplateTargetSpec> PATH_TO_TARGET_MAPPING;
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

        MagicalItemUtils.injectIdentifier(stack, id);

        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',(String) object.get("name")));
        JSONArray loreArray = (JSONArray) object.get("lore");
        meta.getPersistentDataContainer().set(new NamespacedKey(MagicalItems.instance(), "magical_item"), PersistentDataType.STRING, id);
        List<String> lore = new ArrayList<>();
        loreArray.forEach(x -> lore.add(ChatColor.translateAlternateColorCodes('&', (String) x)));
        meta.setLore(lore);
        stack.setItemMeta(meta);

        Set<ActionTemplate<?>> actions = new HashSet<>();

        if(object.containsKey("enchantments")) {
            ((JSONArray) object.get("enchantments"))
                    .forEach(x -> {
                        Pair<Enchantment, Integer> pair = JSONCasters.JSON_TO_ENCHANTMENT.apply((JSONObject) x);
                        stack.addUnsafeEnchantment(pair.getFirst(), pair.getSecond());
                    });
        }

        if(object.containsKey("templates")) {
            AtomicReference<Object> currentObject = new AtomicReference<>(object.get("templates"));
            PATH_TO_TARGET_MAPPING.forEach((pathStr, spec) -> {
                String[] path = pathStr.split(".");
                for(int i = 0; i<path.length; i++) {
                    if(i == path.length-1) {
                        JSONObject obj = (JSONObject) currentObject.get();
                        obj.keySet().forEach(x -> JSONTemplateAdaptors.findAndApply((String) x, obj.get(x), spec).ifPresent(actions::add));
                        continue;
                    }
                    JSONObject obj = (JSONObject) currentObject.get();
                    currentObject.set(obj.get(path[i]));
                 }
            });
        }

        return Optional.of(new MagicalItem(actions, stack));
    }
}
