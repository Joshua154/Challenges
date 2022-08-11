package com.laudynetwork.challenges.modifications.gameModifications;

import com.google.gson.Gson;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bson.json.JsonObject;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockRandommizer extends Mod {
    public BlockRandommizer() {
        super("Block Randomizer", Material.BEACON, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.WORK_IN_PROGRESS);

        shuffle();
    }

    private final Map<Material, Material> partners = new HashMap<>();


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

    private void shuffle() {
        List<Material> remaining = new ArrayList<>();

        partners.clear();

        for (Material mat : Material.values()) {
            if (mat.isItem()) {
                remaining.add(mat);
            }
        }

        for (Material mat : Material.values()) {
            if (!remaining.isEmpty()) {
                if (mat.isBlock()) {
                    Random r = new Random();
                    int rand;
                    if (remaining.size() != 1) {
                        rand = r.nextInt(remaining.size() - 1);
                    } else {
                        rand = 0;
                    }
                    partners.put(mat, remaining.get(rand));
                    remaining.remove(rand);
                }

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
    public String generateConfigString(){
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