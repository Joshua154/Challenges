package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class WaterRising extends GameMod {
    private int currentLevel = 70;
    public ChallengeConfig challengeConfig;
    private final DoubleObject delay = new DoubleObject(1, 30, 5 * 60);
    BukkitTask task;

    public WaterRising() {
        super("Water rising", "wr", Material.WATER_BUCKET, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "Waterlevel rises in a specific time.");

        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        delay.stepSize = 30;

        description.add("delay: {{variable}}s");

        configEntries.add(new ConfigEntry("Delay between Water rising", description, delay, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @Override
    public void onUpdate() {
        task = run();
    }

    @Override
    public void disable() {
        enabled = false;
        if (task != null && !task.isCancelled()) {
            task.cancel();
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


                rise(currentLevel);
            }
        }.runTaskTimer(Challenges.get(), delay.getIntValue() * 20L, 1L);
    }

    void rise(int amount) {
        //for(Player player : Bukkit.getOnlinePlayers()) player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a The sea level has risen from &l" + currentLevel + "&r&a to &l" + (currentLevel + amount)));
        currentLevel += amount;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill -50 " + currentLevel + " -50 " + "50 " + currentLevel + " 50 minecraft:water replace minecraft:air");
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }
}
