package net.threader.magicalitems.loader;

import net.threader.magicalitems.MagicalItem;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MagicalItemsLoader {
    public static MagicalItem load(String id) throws IOException {
        JSONParser parser = new JSONParser();
        JSONObject object;
        InputStream fileStream = MagicalItemsLoader.class.getResourceAsStream("/items/" + id + ".json")
        if(fileStream == null) {
            throw new IOException("Item's json file not found: " + id + ".json");
        }
        try(Reader reader = new InputStreamReader(fileStream)) {
            object = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
