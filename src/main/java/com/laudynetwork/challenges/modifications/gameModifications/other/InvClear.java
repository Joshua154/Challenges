package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;

public class InvClear extends GameMod {
    public InvClear() {
        super("Inv Clear", "ic", Material.CHEST, ModManager.ModType.PLAYER, ModManager.ModStatus.OPEN, "Inventory Clear on Damage");
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        player.getInventory().setContents(new ItemStack[]{});
    }
}

