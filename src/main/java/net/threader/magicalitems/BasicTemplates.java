package net.threader.magicalitems;

import net.threader.magicalitems.registry.TemplateRegister;
import net.threader.magicalitems.template.ActionTemplate;
import net.threader.magicalitems.template.ArgumentativeActionTemplate;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.function.Function;

public class BasicTemplates {

    public static final TemplateRegister REGISTER = new TemplateRegister();

    public static final Function<Object[], ArgumentativeActionTemplate<LivingEntity>> APPLY_EFFECTS = (args) -> REGISTER.register(
                    new ArgumentativeActionTemplate<LivingEntity>("apply_effects", (p, i, e, a) -> {
                        for (Object o : a) {
                            ((LivingEntity)e).addPotionEffect((PotionEffect) o);
                        }
                    }, args));
}
