package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import static org.bukkit.entity.EntityType.*;

public class KillAllBosses extends Mod {
    public KillAllBosses() {
        super("Kill All Bosses Challenge", Material.BEACON, ModManager.ModType.CHALLENGE, ModManager.ModStatus.WORK_IN_PROGRESS);
    }

    private boolean killed_EnderDragon = false;
    private boolean killed_ElderGuardian = false;
    private boolean killed_Wither = false;


    @EventHandler
    public void onEnderDragonKill(EntityDeathEvent event) {

        if(event.getEntity().getType() == ENDER_DRAGON) {
            killed_EnderDragon = true;
        } else if (event.getEntity().getType() == ELDER_GUARDIAN) {
            killed_ElderGuardian = true;
        } else if (event.getEntity().getType() == WITHER) {
            killed_Wither = true;
        }

        if (killed_EnderDragon && killed_ElderGuardian && killed_Wither) {
            Bukkit.broadcastMessage("All bosses killed");
        }
    }
}
