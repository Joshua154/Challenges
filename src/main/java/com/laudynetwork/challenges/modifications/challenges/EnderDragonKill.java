package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.awt.*;
import java.util.Objects;

import static org.bukkit.entity.EntityType.ENDER_DRAGON;

public class EnderDragonKill extends GameMod {
    public EnderDragonKill() {
        super("Kill Ender Dragon Challenge", "ked", Material.DRAGON_EGG, ModManager.ModType.GOALS, ModManager.ModStatus.OPEN, "Kill Ender Dragon Challenge");
        super.color = new Color(99, 15, 15);
    }

    @EventHandler
    public void onEnderDragonKill(EntityDeathEvent event) {
        if (!this.enabled) return;
        if (event.getEntity().getType() == ENDER_DRAGON) {
            complete(Objects.requireNonNull(event.getEntity().getKiller()), "{{player}} killed the Ender Dragon");
        }
    }
}
