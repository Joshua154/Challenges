package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoDamage extends Mod {
    public NoDamage() {
        super("No Damage Challenge", Material.BARRIER, ModManager.ModType.CHALLENGE, ModManager.ModStatus.WORK_IN_PROGRESS);
    }

    @EventHandler
    public void onDeath(EntityDamageEvent event){
        if(event.getEntity() instanceof Player player){

            if(Challenges.get().getPlayingPlayers().contains(player)){
                event.setCancelled(true);
                Challenges.get().timer.setRunning(false);
                super.failed(player, "he took damage");
            }
        }
    }
}
