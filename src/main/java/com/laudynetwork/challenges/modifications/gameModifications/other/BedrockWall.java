package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BedrockWall extends GameMod {
    public ChallengeConfig challengeConfig;
    private final DoubleObject delay = new DoubleObject(3, 1, 20);

    private final int range = 100;

    public BedrockWall() {
        super("Bedrock Wall", "bw", Material.BEDROCK, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "");

        super.setPermission("challenges.bw");


        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        description.add("delay: {{variable}}s");

        configEntries.add(new ConfigEntry("Delay before Wall generates", description, delay, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!enabled) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getPlayer())) return;

        if (leaveBlock(event.getFrom(), event.getTo())) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!enabled) return;
                    placeWall(event.getTo());
                }
            }.runTaskLater(Challenges.get(), 20L * delay.getIntValue());
        }
    }

    private void placeWall(Location location) {
        World world = Objects.requireNonNull(location.getWorld());
        for (int i = world.getMinHeight(); i < world.getMaxHeight(); i++) {
            Location loc = new Location(location.getWorld(), location.getBlockX(), i, location.getBlockZ());

            //setBlock(loc.getWorld(), loc, Blocks.BEDROCK, false);
            loc.getBlock().setType(Material.BEDROCK);
        }
    }

    private boolean leaveBlock(Location from, Location to) {
        boolean x = from.getBlockX() != to.getBlockX();
        boolean z = from.getBlockZ() != to.getBlockZ();

        return x || z;
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }

    /*private void setBlock(World world, Location location, net.minecraft.server.v1_16_R3.Block block, boolean applyPhysics) {
        net.minecraft.server.v1_16_R3.World nmsWorld = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_16_R3.Chunk nmsChunk = nmsWorld.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
        BlockPosition bp = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        IBlockData ibd = block.getBlockData();
        nmsChunk.setType(bp, ibd, applyPhysics);

        nmsWorld.notify(bp, ibd, ibd, 0);
    }*/
}
