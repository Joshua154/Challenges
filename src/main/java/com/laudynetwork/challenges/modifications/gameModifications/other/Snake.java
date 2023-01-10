package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Snake extends GameMod {
    private final DoubleObject trailVisible = new DoubleObject(1);
    public ChallengeConfig challengeConfig;
    List<Location> trails = new ArrayList<>();
    Map<Location, Material> trailsBlock = new HashMap<>();
    Map<Player, Material> blockMap = new HashMap<>();

    public Snake() {
        super("Snake", "s", Material.ORANGE_CONCRETE, ModManager.ModType.WORLD, ModManager.ModStatus.BETA, "Each player leaves a trail that must not be touched.");

        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        configEntries.add(new ConfigEntry("Trail Visible", description, trailVisible, ConfigEntry.ConfigEntryType.TOGGLE));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        Location location = roundLocation(event.getFrom().clone());

        if (isInList(location) || isInList(location.clone().subtract(0, 1, 0))) {
            for (Player playerToSend : Bukkit.getOnlinePlayers()) sendAllTrailBlocks(playerToSend);

            failed(event.getPlayer(), "{{player}} stepped on a trail");
            return;
        }

        if (!leaveBlock(event.getFrom(), event.getTo())) return;

        if (!location.getBlock().getType().isSolid()) {
            location = event.getFrom().clone().subtract(0, 1, 0);
        }

        if (!location.getBlock().getType().isSolid()) {
            location.subtract(0, 1, 0);
        }


        if (!location.getBlock().getType().isAir()) {
            Material material = blockMap.get(event.getPlayer());
            if (material == null) {
                blockMap.put(event.getPlayer(), getNewRandomMaterial());
                material = blockMap.get(event.getPlayer());
            }

            trails.add(location);
            trailsBlock.put(location, material);


            if (trailVisible.isTrue()) {
                for (Player playerToSend : Bukkit.getOnlinePlayers()) {
                    sendBlockPackages(playerToSend, location, material);
                }
            } else {
                for (Player playerToSend : Challenges.get().getHiddenPlayers()) {
                    sendBlockPackages(playerToSend, location, material);
                }
            }
        }
    }

    @EventHandler
    public void gameModeSwitch(PlayerGameModeChangeEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;

        if (Challenges.get().getPlayingPlayers().contains(event.getPlayer()) && trailVisible.isFalse()) {
            sendAllOrgBlocks(event.getPlayer());
        } else if (trailVisible.isFalse()) {
            sendAllTrailBlocks(event.getPlayer());
        }
    }

    private Material getNewRandomMaterial() {
        List<trailBlocks> trailBlocks = getValidBlocks();

        Random random = new Random();

        return Material.getMaterial(trailBlocks.get(random.nextInt(trailBlocks.size())).name());
    }

    private List<trailBlocks> getValidBlocks() {
        List<trailBlocks> returnList = new ArrayList<>();

        Iterator<trailBlocks> blocksIterator = Arrays.stream(trailBlocks.values()).iterator();

        while (blocksIterator.hasNext()) {
            trailBlocks trailBlock = blocksIterator.next();

            if (!blockMap.containsValue(Material.valueOf(trailBlock.name()))) {
                returnList.add(trailBlock);
            }
        }

        if (returnList.isEmpty()) returnList.addAll(Arrays.asList(trailBlocks.values()));

        return returnList;
    }

    private boolean isOnLocation(Location location, Location location2) {
        return location2.getWorld() == (location.getWorld())
                && location2.getX() == location.getBlockX()
                && location2.getY() == location.getBlockY()
                && location2.getZ() == location.getBlockZ();
    }

    private boolean isInList(Location location) {
        for (Location location2 : trails) {
            if (isOnLocation(location2, location)) {
                return true;
            }
        }
        return false;
    }

    private Location roundLocation(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return new Location(location.getWorld(), x, y, z);
    }

    private boolean leaveBlock(Location from, Location to) {
        boolean x = from.getBlockX() != to.getBlockX();
        boolean z = from.getBlockZ() != to.getBlockZ();

        return x || z;
    }

    private void sendBlockPackages(Player player, Location location, Material material) {
        player.sendBlockChange(location, material.createBlockData());
    }

    private void sendAllOrgBlocks(Player player) {
        for (Location loc : trails) {
            player.sendBlockChange(loc, loc.getBlock().getBlockData());
        }
    }

    private void sendAllTrailBlocks(Player player) {
        for (Location loc : trails) {
            player.sendBlockChange(loc, trailsBlock.get(loc).createBlockData());
        }
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }

    private enum trailBlocks {
        WHITE_CONCRETE,
        ORANGE_CONCRETE,
        MAGENTA_CONCRETE,
        LIGHT_BLUE_CONCRETE,
        YELLOW_CONCRETE,
        LIME_CONCRETE,
        PINK_CONCRETE,
        GRAY_CONCRETE,
        LIGHT_GRAY_CONCRETE,
        CYAN_CONCRETE,
        PURPLE_CONCRETE,
        BLUE_CONCRETE,
        BROWN_CONCRETE,
        GREEN_CONCRETE,
        RED_CONCRETE,
        BLACK_CONCRETE;
    }
}
