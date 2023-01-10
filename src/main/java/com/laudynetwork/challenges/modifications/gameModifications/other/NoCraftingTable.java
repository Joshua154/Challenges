package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class NoCraftingTable extends GameMod {
    public NoCraftingTable() {
        super("No Crafting Table", "nct", Material.CRAFTING_TABLE, ModManager.ModType.PLAYER, ModManager.ModStatus.OPEN, "");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!enabled) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getPlayer())) return;

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && Objects.equals(Objects.requireNonNull(event.getClickedBlock()).getType(), Material.CRAFTING_TABLE)) {
            event.setCancelled(true);
        }
    }
}
