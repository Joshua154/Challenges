package com.laudynetwork.challenges.modifications.gameModifications.oldForce;

import com.laudynetwork.challenges.api.chatutils.HexColor;
import com.laudynetwork.challenges.api.chatutils.TextUtils;
import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class oldForceCoordinates extends GameMod {
    private int timeLeft = 1;
    private int delay;

    private int orgTimeLeft;
    private int orgDelay;
    private final int coordinatesRange = 1000;
    private final int range = 60 * 2;
    private boolean completed = true;
    public ChallengeConfig challengeConfig;
    private final DoubleObject delayBetweenForces = new DoubleObject(60 * 7, 60 * 7, 60 * 10);
    private final DoubleObject timeToCompleteForce = new DoubleObject(60 * 2, 60 * 2, 60 * 5);
    private final DoubleObject xVar = new DoubleObject(1);
    private final DoubleObject yVar = new DoubleObject(1);
    private final DoubleObject zVar = new DoubleObject(1);
    private final Vector forceVector = new Vector(0, 0, 0);
    private Vector startPosition = new Vector(0, 0, 0);
    private BossBar bar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);
    private BossBar specBar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);


    public oldForceCoordinates() {
        super("Force Coordinates", "ofc", Material.BLAZE_ROD, ModManager.ModType.PLAYER, ModManager.ModStatus.BETA, "Forces a block to be on in a Specific Time.");

        List<ConfigEntry> configEntries = new ArrayList<>();

        List<String> description = new ArrayList<>();

        description.add("delay: {{variable}}");

        configEntries.add(new ConfigEntry("Min Delay between Forces", description, delayBetweenForces, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));
        configEntries.add(new ConfigEntry("Min Delay to Complete", description, timeToCompleteForce, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));

        configEntries.add(new ConfigEntry("Enable force X-Coordinate", null, xVar, ConfigEntry.ConfigEntryType.TOGGLE));
        configEntries.add(new ConfigEntry("Enable force Y-Coordinate", null, yVar, ConfigEntry.ConfigEntryType.TOGGLE));
        configEntries.add(new ConfigEntry("Enable force Z-Coordinate", null, zVar, ConfigEntry.ConfigEntryType.TOGGLE));


        challengeConfig = new ChallengeConfig("Test", configEntries.toArray(new ConfigEntry[0]));
    }

    @Override
    public void init() {
        completed = true;
        generateNewDelay();
        generateNewCord();
        timeLeft = -1;
        bar.setVisible(false);
        run();
    }

    private void generateNewDelay() {
        Random random = new Random();
        delay = random.nextInt(delayBetweenForces.getIntMin(), delayBetweenForces.getIntMax() + 1);
        orgDelay = delay;

        for (Player player : Challenges.get().getHiddenPlayers()) {
            specBar.addPlayer(player);
        }
    }

    private void generateNewTimeToComplete() {
        Random random = new Random();
        timeLeft = random.nextInt(timeToCompleteForce.getIntMin(), timeToCompleteForce.getIntMax() + 1);
        orgTimeLeft = timeLeft;

        bar.setVisible(true);
        for (Player player : Bukkit.getOnlinePlayers()) {
            bar.addPlayer(player);
        }
    }

    private void generateNewCord() {
        Random random = new Random();
        startPosition = getAvgPos();
        forceVector.setX(random.nextInt(coordinatesRange * 2) - coordinatesRange);
        forceVector.setY(random.nextInt(Bukkit.getWorlds().get(0).getMaxHeight() - 1) + 1);
        forceVector.setZ(random.nextInt(coordinatesRange * 2) - coordinatesRange);
    }

    private Vector getAvgPos() {
        Vector avgPos = new Vector(0, 0, 0);
        if (Challenges.get().getPlayingPlayers().size() > 0) {
            for (Player player : Challenges.get().getPlayingPlayers()) {
                if (player.getWorld().getEnvironment() != World.Environment.NETHER) {
                    avgPos.add(player.getLocation().clone().toVector());
                } else {
                    avgPos.add(player.getLocation().clone().toVector().divide(new Vector(8, 8, 8)));
                }
            }
            int size = Challenges.get().getPlayingPlayers().size();
            avgPos.divide(new Vector(size, size, size));
        }

        avgPos.setX(Math.round(avgPos.getX()));
        avgPos.setY(Math.round(avgPos.getY()));
        avgPos.setZ(Math.round(avgPos.getZ()));
        return avgPos.clone();
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (timeLeft < 0) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getPlayer())) return;

        Vector to = startPosition.clone().add(forceVector);

        boolean x = Math.round(event.getTo().toVector().clone().getX()) == Math.round(to.clone().add(new Vector(0.5, 0.5, 0.5)).getX());
        boolean y = Math.round(event.getTo().toVector().clone().getY()) == Math.round(to.clone().getY());
        boolean z = Math.round(event.getTo().toVector().clone().getZ()) == Math.round(to.clone().add(new Vector(0.5, 0.5, 0.5)).getZ());

        if (xVar.isTrue()) {
            if (!x) return;
        }
        if (yVar.isTrue()) {
            if (!y) return;
        }
        if (zVar.isTrue()) {
            if (!z) return;
        }

        walkedToCord(event.getPlayer());
    }


    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Vector to = startPosition.clone().add(forceVector);

                /*if (xVar.isTrue() && yVar.isTrue() && zVar.isTrue()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Util.sendBlockHighlight(player, new BlockHighlight(to, new Color(0, 255, 0, 50), "The Coordinates", 1000));
                    }
                }*/

                if (!Challenges.get().timer.isRunning()) return;
                if (!enabled) return;
                if (timeLeft < 0) {
                    bar.setVisible(false);
                    if (!completed) {
                        failed(null, "Time ran out");
                    }
                } else {

                    timeLeft--;
                    bar.setVisible(true);
                    specBar.setVisible(false);
                    bar.setProgress(((timeLeft + 1) / (double) orgTimeLeft));
                    String barString = "";

                    if (xVar.value > 0) barString += "X: " + Math.round(to.getX());
                    if (yVar.value > 0) barString += " Y: " + Math.round(to.getY());
                    if (zVar.value > 0) barString += " Z: " + Math.round(to.getZ());

                    bar.setTitle(HexColor.translate("Force Cords: #00FF00" + barString));
                }

                if (delay == -1) {
                    return;
                } else if (delay != 0) {
                    delay--;

                    specBar.setVisible(true);
                    specBar.setProgress(((delay + 1) / (double) orgDelay));
                    specBar.setTitle(HexColor.translate("Cords Delay: &l#00FF00" + TextUtils.formatTime(delay, false)));
                } else {
                    generateNewTimeToComplete();
                    completed = false;
                    delay = -1;

                    bar.setVisible(false);
                }
            }
        }.runTaskTimer(Challenges.get(), 20, 20);
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }

    private void walkedToCord(Player player) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(HexColor.translate("&l" + player.getName() + " &7is standing on the Location!"));
        }
        completed = true;
        generateNewDelay();
        generateNewCord();
        timeLeft = -1;
    }
}
