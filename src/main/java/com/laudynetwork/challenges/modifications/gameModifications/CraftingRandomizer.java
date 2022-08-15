package com.laudynetwork.challenges.modifications.gameModifications;

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
        super("Crafting Randomizer", "cr", Material.BEACON, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.WORK_IN_PROGRESS, "Randomizes all crafting recipes.");
    }

    private final Map<Material, Material> partners = UTILS.shuffle(List.of(Material.values()), "item");


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