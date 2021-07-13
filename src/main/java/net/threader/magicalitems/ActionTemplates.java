package net.threader.magicalitems;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.function.BiConsumer;

public class ActionTemplates {
    public static BiConsumer<Player, PotionEffect> applyEffect = LivingEntity::addPotionEffect;
}
