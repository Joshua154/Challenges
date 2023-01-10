package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class BlocksDisappearUnderPlayer extends GameMod {
    /*public ChallengeConfig challengeConfig;
    private final DoubleObject toggleEntities = new DoubleObject(0);*/

    public BlocksDisappearUnderPlayer() {
        super("Holes", "bdup", Material.BARRIER, ModManager.ModType.WORLD, ModManager.ModStatus.OPEN, "");


        /*List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        configEntries.add(new ConfigEntry("Entities remove Blocks", description, toggleEntities, ConfigEntry.ConfigEntryType.TOGGLE));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));*/
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!enabled) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getPlayer())) return;

        if (leaveBlock(event.getFrom(), event.getTo())) {
            removeBlocks(event.getFrom());
        }
    }

    private void removeBlocks(Location location) {
        World world = Objects.requireNonNull(location.getWorld());
        for (int i = world.getMinHeight(); i < world.getMaxHeight(); i++) {
            location.getWorld().getBlockAt(location.getBlockX(), i, location.getBlockZ()).setType(Material.AIR);
        }
    }

    private boolean leaveBlock(Location from, Location to) {
        boolean x = from.getBlockX() != to.getBlockX();
        boolean z = from.getBlockZ() != to.getBlockZ();

        return x || z;
    }
}
