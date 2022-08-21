package com.laudynetwork.challenges.modifications.gameModifications.randomizer;

import com.google.gson.Gson;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.IntObjekt;
import com.laudynetwork.challenges.util.UTILS;
import net.minecraft.server.v1_16_R3.*;
import org.bson.json.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

public class BlockRandomizer extends Mod {
    public BlockRandomizer() {
        super("Block Randomizer", "br", Material.BEACON, ModManager.ModType.GAME_MODIFICATION, ModManager.ModStatus.BETA, "Randomizes all block drops.");


        List<ConfigEntry> configEntries = new ArrayList<>();

        List<String> description = new ArrayList<>();

        configEntries.add(new ConfigEntry("Drop Item Only With Tool", description, dropItemOnlyWithTool, ConfigEntry.ConfigEntryType.TOGGLE));


        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    public void init() {
        while (partners == null) {
            partners = UTILS.shuffle(List.of(Material.values()), "item");
        }
    }

    private Map<Material, Material> partners = null;
    public ChallengeConfig challengeConfig;

    private final IntObjekt dropItemOnlyWithTool = new IntObjekt(1);


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (this.enabled) {
            event.setDropItems(false);
            if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) return;


            ItemStack item = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());
            if (item == null) item = new ItemStack(Material.AIR);

            if (!isUsableTool(item, event.getBlock().getType()) && dropItemOnlyWithTool.value >= 1) return;


            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                    new ItemStack(getPartner(event.getBlock().getType())));

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

    @Override
    public void onSettingsClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        player.openInventory(challengeConfig.getInventory());
    }


    public static boolean isUsableTool(ItemStack tool, Material block) {
        Block nmsBlock = CraftMagicNumbers.getBlock(block);
        if (nmsBlock == null) {
            return false;
        }
        IBlockData data = nmsBlock.getBlockData();
        if (!data.isRequiresSpecialTool()) return true;
        return tool != null && tool.getType() != Material.AIR && org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers.getItem(tool.getType()).canDestroySpecialBlock(data);
    }
}