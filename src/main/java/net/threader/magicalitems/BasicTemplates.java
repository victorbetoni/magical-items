package net.threader.magicalitems;

import net.threader.magicalitems.registry.TemplateRegister;
import net.threader.magicalitems.template.LivingEntityActionTemplate;
import org.bukkit.potion.PotionEffect;

import java.util.function.Function;

public class BasicTemplates {

    public static final TemplateRegister REGISTER = new TemplateRegister();

    public static final Function<Object[], LivingEntityActionTemplate> APPLY_EFFECTS = (args) ->
            (LivingEntityActionTemplate) REGISTER.register(
                    new LivingEntityActionTemplate("apply_effects", (p, i, e, a) -> {
                        for (Object o : a) {
                            e.addPotionEffect((PotionEffect) o);
                        }
                    }, args));
}
