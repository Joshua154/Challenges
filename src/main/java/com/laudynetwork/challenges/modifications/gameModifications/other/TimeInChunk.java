package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.Cords;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeInChunk extends GameMod {
    private final Map<Player, Cords> currentChunk = new HashMap<>();
    private final Map<Cords, Integer> timeMap = new HashMap<>();
    private final Map<Player, BossBar> barMap = new HashMap<>();
    public ChallengeConfig challengeConfig;
    private final DoubleObject time = new DoubleObject(10, 60, 10 * 60, 60);
    BukkitTask task;

    public TimeInChunk() {
        super("Time in Chunk", "tic", Material.GRASS_BLOCK, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "Limited Time in each Chunk");

        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        description.add("delay: {{variable}}s");

        configEntries.add(new ConfigEntry("Time in Chunk", description, time, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }

    @Override
    public void onUpdate() {
        task = run();
        for (BossBar bar : barMap.values()) {
            bar.setVisible(true);
        }
    }

    @Override
    public void disable() {
        enabled = false;
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
        for (BossBar bar : barMap.values()) {
            bar.setVisible(false);
        }
    }

    private BukkitTask run() {
        return new BukkitRunnable() {
            @Override
            public void run() {
                if (!enabled) {
                    task.cancel();
                    return;
                }
                if (!Challenges.get().timer.isRunning()) return;

                Bukkit.getOnlinePlayers().forEach((player -> {
                    BossBar bar = barMap.get(player);

                    if (bar == null) {
                        bar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);
                        bar.addPlayer(player);
                        barMap.put(player, bar);
                    }


                    bar.setProgress(1 - (timeMap.get(currentChunk.get(player)) / time.value));
                    bar.setTitle(time.value - timeMap.get(currentChunk.get(player)) + "");
                }));


                updateTimeOfAllPlayers();

                Cords failedCords = checkForTimeOut();

                if (failedCords != null) {
                    failed(getPlayer(failedCords), "{{player}} spend to much time in one Chunk");
                }
            }
        }.runTaskTimer(Challenges.get(), 20L, 20L);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();

        BossBar bar = barMap.get(player);
        if (bar != null) {
            bar.setProgress(1 - (timeMap.get(currentChunk.get(player)) / time.value));
            bar.setTitle(Math.ceil(time.value - timeMap.get(currentChunk.get(player))) + "");
        }

        if (!Challenges.get().getPlayingPlayers().contains(player)) return;
        Cords cords = new Cords(event.getTo().getChunk().getX(), event.getTo().getChunk().getZ(), event.getTo().getChunk().getWorld());
        if (isNewChunk(cords)) {
            timeMap.put(cords, 0);
        }
        if (!isDifferentChunk(event.getFrom(), event.getTo())) return;

        enterDifferentChunk(event.getTo().getChunk(), player);
    }

    private boolean isDifferentChunk(Location from, Location to) {
        return from.getChunk().getX() != to.getChunk().getX() ||
                from.getChunk().getZ() != to.getChunk().getZ() ||
                from.getWorld() != to.getWorld();
    }

    private void enterDifferentChunk(Chunk chunk, Player player) {
        Cords cords = new Cords(chunk.getX(), chunk.getZ(), chunk.getWorld());
        if (isNewChunk(cords)) enterNewChunk(cords);

        currentChunk.put(player, cords);
    }

    private boolean isNewChunk(Cords cords) {
        return !timeMap.containsKey(cords);
    }

    private void enterNewChunk(Cords cords) {
        timeMap.put(cords, 0);
    }

    private void updateTimeOfAllPlayers() {
        List<Cords> updatedChunks = new ArrayList<>();

        for (Player player : Challenges.get().getPlayingPlayers()) {
            Cords cords = currentChunk.get(player);
            if (!updatedChunks.contains(cords)) {
                timeMap.put(cords, timeMap.get(cords) + 1);
                updatedChunks.add(cords);
            }
        }
    }

    private Cords checkForTimeOut() {
        for (Cords cords : timeMap.keySet()) {
            if (timeMap.get(cords) > this.time.value) return cords;
        }
        return null;
    }

    private Player getPlayer(Cords cords) {
        for (Player player : currentChunk.keySet()) {
            if (currentChunk.get(player) == cords) return player;
        }
        return null;
    }
}
