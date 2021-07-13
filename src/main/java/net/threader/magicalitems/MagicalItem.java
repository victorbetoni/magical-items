package net.threader.magicalitems;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.function.BiConsumer;

public class MagicalItem {
    private Set<BiConsumer<Player, Player>> actions;
}
