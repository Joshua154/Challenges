package com.laudynetwork.challenges.modifications.gameModifications.randomizer;

import com.google.gson.Gson;
import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.UTILS;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntitySpawnRandomizer extends GameMod {
    public EntitySpawnRandomizer() {
        super("Entity Spawn Randomizer", "esr", Material.BEACON, ModManager.ModType.PLAYER, ModManager.ModStatus.WORK_IN_PROGRESS, "Randomizes all Entities.");
    }

    public void init() {
        while (partners == null || !isPossible()) {
            partners = UTILS.shuffleEntities();
        }
    }

    private boolean isPossible() {
        boolean endermann = false;
        boolean blaze = false;
        for (EntityType mob : partners.keySet()) {
            if (partners.get(mob) == EntityType.ENDERMAN) {
                endermann = true;
            } else if (partners.get(mob) == EntityType.BLAZE) {
                blaze = true;
            }

            if (endermann && blaze) {
                return true;
            }
        }
        return false;
    }

    private Map<EntityType, EntityType> partners;
    private final List<EntityType> killedEntities = new ArrayList<>();


    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (this.enabled) {
            if (!(event.getEntity() instanceof Mob)) return;

            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
            scheduler.runTaskLater(Challenges.get(), () -> {
                if (event.getEntity().getScoreboardTags().contains("SpawnedByMod")) return;
                //if (!canSpawn(event.getEntity())) return;

                Entity spawnedEntity = event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), getReversedPartner(event.getEntity().getType()));
                spawnedEntity.addScoreboardTag("SpawnedByMod");

                ((Mob) event.getEntity()).setAI(false);
                ((Mob) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
                event.getEntity().setSilent(true);
                event.getEntity().setInvulnerable(true);
                event.getEntity().setGravity(false);
                Challenges.get().addHiddenEntity(event.getEntity());
            }, 1);
        }
    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (this.enabled) {
            if (!(event.getEntity() instanceof Mob)) return;
            if (killedEntities.contains(event.getEntity().getType())) return;

            if (event.getEntity().getKiller() != null) {
                Bukkit.broadcastMessage(event.getEntity().getType() + " was " + getPartner(event.getEntity().getType()));
            }
        }
    }

    private EntityType getPartner(EntityType entityType) {
        EntityType partner;
        try {
            partner = partners.get(entityType);
        } catch (Exception e) {
            partner = entityType;
        }
        return partner;
    }

    private EntityType getReversedPartner(EntityType entityType) {
        EntityType partner = entityType;
        try {
            for (Map.Entry<EntityType, EntityType> entry : partners.entrySet()) {
                if (entry.getValue() == entityType) {
                    partner = entry.getKey();
                    return partner;
                }
            }
        } catch (Exception ignored) {
        }
        return partner;
    }

    /*@Override
    public String generateConfigString() {
        Gson gson = new Gson();
        JsonObject config = new JsonObject("{\"" + super.getName().replaceAll(" ", "_") + ".partners\":" + gson.toJson(partners) + "}");
        return config.toString();
    }*/

    /*@Override
    public void loadConfig(String configString) {
        org.json.JSONObject config = new org.json.JsonObject();
        config.put("partners", partners);

        partners = gson.fromJson(config.get("" + super.getName().replaceAll(" ", "_") + ".partners"), Map.class);
    }*/
}