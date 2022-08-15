package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

import static org.bukkit.entity.EntityType.ENDER_DRAGON;

public class EnderDragonKill extends Mod {
    public EnderDragonKill() {
        super("Kill Ender Dragon Challenge", "ked", Material.DRAGON_EGG, ModManager.ModType.CHALLENGE, ModManager.ModStatus.WORK_IN_PROGRESS, "Kill Ender Dragon Challenge");
    }

    @EventHandler
    public void onEnderDragonKill(EntityDeathEvent event) {
        if (event.getEntity().getType() == ENDER_DRAGON) {
            super.complete(Objects.requireNonNull(event.getEntity().getKiller()), "{{player}} killed the Ender Dragon");
        }
    }
}
