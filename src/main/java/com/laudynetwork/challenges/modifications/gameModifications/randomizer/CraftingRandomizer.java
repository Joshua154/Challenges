package com.laudynetwork.challenges.modifications.gameModifications.randomizer;

import com.google.gson.Gson;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.UTILS;
import org.bson.json.JsonObject;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class CraftingRandomizer extends Mod {
    public CraftingRandomizer() {
        super("Crafting Randomizer", "cr", Material.BEACON, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.BETA, "Randomizes all crafting recipes.");
    }

    public void init() {
        while (partners == null || !isPossible()) {
            partners = UTILS.shuffle(List.of(Material.values()), "item");
        }
    }

    private boolean isPossible() {
        boolean enderEye = false;
        boolean blazePowder = false;
        for (Material material : partners.keySet()) {
            if (partners.get(material) == Material.ENDER_EYE) {
                enderEye = true;
            } else if (partners.get(material) == Material.BLAZE_POWDER) {
                blazePowder = true;
            }
            if (enderEye && blazePowder) {
                return true;
            }
        }
        return false;
    }

    private Map<Material, Material> partners;


    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (this.enabled) {
            event.getInventory().setResult(new ItemStack(getPartner(event.getRecipe().getResult().getType())));
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

    @Override
    public String generateConfigString() {
        Gson gson = new Gson();
        JsonObject config = new JsonObject("{\"" + super.getName().replaceAll(" ", "_") + ".partners\":" + gson.toJson(partners) + "}");
        return config.toString();
    }

    /*@Override
    public void loadConfig(String configString) {
        org.json.JSONObject config = new org.json.JsonObject();
        config.put("partners", partners);

        partners = gson.fromJson(config.get("" + super.getName().replaceAll(" ", "_") + ".partners"), Map.class);
    }*/
}