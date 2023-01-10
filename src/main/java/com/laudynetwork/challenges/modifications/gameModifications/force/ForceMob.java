package com.laudynetwork.challenges.modifications.gameModifications.force;/*package com.laudynetwork.challenges.modifications.gameModifications.force;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class ForceMob extends GameMod {
    private EntityType forceEntity;
    private final List<EntityType> entityTypes = getEntitys();
    private ChallengeConfig challengeConfig;
    private DoubleObject

    public ForceMob() {
        super("Force Entity", "fe", Material.ZOMBIE_SPAWN_EGG, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.WORK_IN_PROGRESS, "Forces a item you to have an Item.");

        List<ConfigEntry> configEntries = new ArrayList<>();

        List<String> description = new ArrayList<>();

        description.add("delay: {{variable}}");

        configEntries.add(new ConfigEntry("Min Delay between Forces", description, delayBetweenForces, ConfigEntry.ConfigEntryType.TOGGLE));


        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (event.getEntity().getKiller() == null) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getEntity().getKiller())) return;

        if (event.getEntity().getType() != forceEntity) {
            failed(event.getEntity().getKiller(), "{{player}} killed wrong Entity");
        }
    }
}*/
