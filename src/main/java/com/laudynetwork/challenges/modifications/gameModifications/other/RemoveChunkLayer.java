package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.Cords;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class RemoveChunkLayer extends GameMod {
    /*public ChallengeConfig challengeConfig;
    private final DoubleObject time = new DoubleObject(1, 1, 5, 1);*/

    public RemoveChunkLayer() {
        super("Remove Chunk Layer", "rcl", Material.GRASS_BLOCK, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "Remove Layer of Chunk");

        /*List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        description.add("delay: {{variable}}s");

        configEntries.add(new ConfigEntry("Time in Chunk", description, time, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));*/
    }

    @Override
    public void init() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!enabled) return;
                if (!Challenges.get().timer.isRunning()) return;


                updateChunkOfAllPlayers();
            }
        }.runTaskTimer(Challenges.get(), 20L, 20L);
    }

    /*@Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }*/

    @Override
    public void disable() {
        enabled = false;
        /*if (task != null && !task.isCancelled()) {
            task.cancel();
        }*/
    }

    private void updateChunkOfAllPlayers() {
        List<Cords> updatedChunks = new ArrayList<>();

        for (Player player : Challenges.get().getPlayingPlayers()) {
            Chunk chunk = player.getLocation().getChunk();
            Cords cords = new Cords(chunk.getX(), chunk.getZ(), chunk.getWorld());
            if (!updatedChunks.contains(cords)) {
                removeChunkLayer(cords);
                updatedChunks.add(cords);
            }
        }
    }

    private void removeChunkLayer(Cords cords) {
        Chunk chunk = cords.getChunk();

        int bx = chunk.getX() << 4;
        int bz = chunk.getZ() << 4;

        for (int xx = bx; xx < bx + 16; xx++) {
            for (int zz = bz; zz < bz + 16; zz++) {
                Block block = chunk.getWorld().getHighestBlockAt(xx, zz);

                if (block.getType() != Material.BEDROCK) {
                    block.setType(Material.AIR);
                }
            }
        }
    }
}
