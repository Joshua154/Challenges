package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.awt.*;

public class NoDamage extends GameMod {
    public NoDamage() {
        super("No Damage Challenge", "nda", Material.BARRIER, ModManager.ModType.GOALS, ModManager.ModStatus.OPEN, "No Damage Challenge");
        super.color = Color.decode("#FF5555");
    }

    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        if (!this.enabled) return;
        if (event.getEntity() instanceof Player player) {

            if (Challenges.get().getPlayingPlayers().contains(player)) {
                event.setCancelled(true);
                super.failed(player, "{{player}} took damage");
            }
        }
    }
}
