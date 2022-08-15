package com.laudynetwork.challenges.modifications.gameModifications;

import com.google.gson.Gson;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.UTILS;
import org.bson.json.JsonObject;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockRandomizer extends Mod {
    public BlockRandomizer() {
        super("Block Randomizer", "br", Material.BEACON, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.WORK_IN_PROGRESS, "Randomizes all block drops.");
    }

    private final Map<Material, Material> partners = UTILS.shuffle(List.of(Material.values()), "block");


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (this.enabled) {
            event.setDropItems(false);
            if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                        new ItemStack(getPartner(event.getBlock().getType())));
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