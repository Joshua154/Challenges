package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

import static org.bukkit.entity.EntityType.ENDER_DRAGON;

public class EnderDragonKill extends Mod {
    public EnderDragonKill() {
        super("Ender Dragon Kill Challenge", Material.DRAGON_EGG, ModManager.ModType.CHALLENGE, ModManager.ModStatus.WORK_IN_PROGRESS);
    }

    @EventHandler
    public void onEnderDragonKill(org.bukkit.event.entity.EntityDeathEvent event) {
        if(event.getEntity().getType() == ENDER_DRAGON) {
            Bukkit.broadcastMessage("Ender Dragon Killed");
        }
    }
}
