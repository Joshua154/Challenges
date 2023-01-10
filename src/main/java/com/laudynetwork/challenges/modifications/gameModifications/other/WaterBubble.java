package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaterBubble extends GameMod {
    public WaterBubble() {
        super("Water Bubble", "wb", Material.WATER_BUCKET, ModManager.ModType.PLAYER, ModManager.ModStatus.BETA, "Randomizes all block drops.");
    }

    private Map<Player, List<Location>> placedBlocks = new HashMap<>();


    @EventHandler
    public void WaterFlow(BlockSpreadEvent event){
        if(!event.getBlock().getType().equals(Material.WATER)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        if (!this.enabled) return;

        if(placedBlocks.get(event.getPlayer()) != null){
            for (Location loc : placedBlocks.get(event.getPlayer())){
                //loc.getBlock().setType(Material.SPONGE);
                loc.getBlock().setType(Material.AIR);
            }
        }
        List<Location> locs = new ArrayList<>();
        for (int x = -2; x < 2; x++) {
            for (int y = 0; y < 4; y++) {
                for (int z = -2; z < 2; z++) {
                    Location loc = event.getPlayer().getLocation().clone().add(x, y, z);
                    if(loc.getBlock().getType().isAir()){
                        loc.getBlock().setType(Material.WATER);
                        locs.add(loc);
                    }
                }
            }
        }
        placedBlocks.put(event.getPlayer(), locs);
    }
}