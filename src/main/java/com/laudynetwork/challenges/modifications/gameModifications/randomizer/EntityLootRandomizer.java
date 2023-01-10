package com.laudynetwork.challenges.modifications.gameModifications.randomizer;

import com.google.gson.Gson;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.UTILS;
import org.bukkit.Material;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;
import java.util.Map;

public class EntityLootRandomizer extends GameMod {
    public EntityLootRandomizer() {
        super("Entity Loot Randomizer", "elr", Material.BEACON, ModManager.ModType.PLAYER, ModManager.ModStatus.BETA, "Randomizes all Entities drops.");
    }

    public void init() {
        while (partners == null || !isPossible()) {
            partners = UTILS.shuffle(List.of(Material.values()), "all");
        }
    }

    private boolean isPossible() {
        boolean enderPearl = false;
        boolean blazeRod = false;
        for (Material material : partners.keySet()) {
            if (partners.get(material) == Material.ENDER_PEARL) {
                enderPearl = true;
            } else if (partners.get(material) == Material.BLAZE_ROD) {
                blazeRod = true;
            }
            if (enderPearl && blazeRod) {
                return true;
            }
        }
        return false;
    }

    private Map<Material, Material> partners;


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (this.enabled) {
            if (event.getEntity() instanceof Mob) {
                event.getDrops().forEach(item -> {
                    if (partners.containsKey(item.getType())) {
                        item.setType(getPartner(item.getType()));
                    }
                });
            }
        }
    }

    private Material getPartner(Material material) {
        Material partner;
        try {
            partner = partners.get(material);
        } catch (Exception e) {
            partner = material;
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