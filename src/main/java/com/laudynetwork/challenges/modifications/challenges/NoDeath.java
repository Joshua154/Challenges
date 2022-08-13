package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class NoDeath extends Mod {
    public NoDeath() {
        super("No Death Challenge", Material.SKELETON_SKULL, ModManager.ModType.CHALLENGE, ModManager.ModStatus.WORK_IN_PROGRESS);
    }

    @EventHandler
    public void onDeath(EntityDamageEvent event){
        if(event.getEntity() instanceof Player player){

            if(Challenges.get().getPlayingPlayers().contains(player)){
                if(player.getHealth() - event.getDamage() <= 0){
                    event.setCancelled(true);
                    Challenges.get().timer.setRunning(false);
                    super.failed(player, "he died");
                }
            }
        }
    }
}
