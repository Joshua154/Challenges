package com.laudynetwork.challenges.modifications.gameModifications.force;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.IntObjekt;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import com.laudynetwork.laudynetworkapi.chatutils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ForceEntity extends Mod {

    private int timeLeft = 1;
    private int delay;
    private EntityType forceEntity;

    private int orgTimeLeft;
    private int orgDelay;
    private final int range = 60 * 2;
    private boolean completed = true;
    public ChallengeConfig challengeConfig;
    private final IntObjekt delayBetweenForces = new IntObjekt(60 * 7, 60 * 7, 60 * 10);
    private final IntObjekt timeToCompleteForce = new IntObjekt(60 * 2, 60 * 2, 60 * 5);
    private final List<EntityType> entityTypes = getEntitys();
    private BossBar bar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);
    private BossBar specBar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SOLID);


    public ForceEntity() {
        super("Force Entity", "fe", Material.BLAZE_ROD, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.WORK_IN_PROGRESS, "Forces a item you to have an Item.");

        /*List<ConfigEntry> configEntries = new ArrayList<>();

        List<String> description = new ArrayList<>();

        description.add("delay: {{variable}}");

        configEntries.add(new ConfigEntry("Min Delay between Forces", description, delayBetweenForces, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));
        configEntries.add(new ConfigEntry("Min Delay to Complete", description, timeToCompleteForce, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));


        //challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));*/
    }

    @Override
    public void init() {
        completed = true;
        generateNewDelay();
        generateNewMob();
        timeLeft = -1;
        bar.setVisible(false);
        run();
    }

    private void generateNewDelay() {
        Random random = new Random();
        delay = random.nextInt(delayBetweenForces.min, delayBetweenForces.max + 1);
        orgDelay = delay;

        for (Player player : Challenges.get().getHiddenPlayers()) {
            specBar.addPlayer(player);
        }
    }

    private void generateNewTimeToComplete() {
        Random random = new Random();
        timeLeft = random.nextInt(timeToCompleteForce.min, timeToCompleteForce.max + 1);
        orgTimeLeft = timeLeft;

        bar.setVisible(true);
        specBar.setVisible(false);
        for (Player player : Bukkit.getOnlinePlayers()) {
            bar.addPlayer(player);
        }
    }

    private List<EntityType> getEntitys() {
        List<EntityType> entitys = new ArrayList<>();
        for (EntityType entity : EntityType.values()) {
            if (entity.isAlive()) {
                entitys.add(entity);
            }
        }
        return entitys;
    }

    private void generateNewMob() {
        Random random = new Random();
        forceEntity = entityTypes.get(random.nextInt(entityTypes.size()));
    }


    @EventHandler
    public void onEntityDeathe(EntityDeathEvent event) {
        if (timeLeft < 0) return;
        if (event.getEntity().getKiller() == null) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getEntity().getKiller())) return;

        if (event.getEntity().getType() == forceEntity) {
            killedEntity(event.getEntity().getKiller());
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
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
                    bar.setTitle(HexColor.translate("Force Entity: &l#00FF00" + forceEntity.name().replaceAll("_", " ") + "    " + TextUtils.formatTime(timeLeft + 1, false)));

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getInventory().contains(material)) {
                            killedEntity(player);
                        }
                    }
                }

                if (delay == -1) {
                    return;
                } else if (delay != 0) {
                    delay--;

                    specBar.setVisible(true);
                    specBar.setProgress(((delay + 1) / (double) orgDelay));
                    specBar.setTitle(HexColor.translate("Entity Delay: &l#00FF00" + TextUtils.formatTime(delay, false)));
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
    public void onSettingsClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        player.openInventory(challengeConfig.getInventory());
    }

    private void killedEntity(Player player) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(HexColor.translate("&l" + player.getName() + " &7killed &l" + forceEntity));
        }
        completed = true;
        generateNewDelay();
        generateNewMob();
        timeLeft = -1;
    }
}
