package com.laudynetwork.challenges.modifications.gameModifications.randomizer;

import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import com.laudynetwork.challenges.util.UTILS;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BlockRandomizer extends GameMod {
    public BlockRandomizer() {
        super("Block Randomizer", "br", Material.BEACON, ModManager.ModType.PLAYER, ModManager.ModStatus.BETA, "Randomizes all block drops.");


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

    private final DoubleObject dropItemOnlyWithTool = new DoubleObject(1);


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (this.enabled) {
            event.setDropItems(false);
            if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) return;


            ItemStack item = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());
            if (item == null) item = new ItemStack(Material.AIR);

            //if (!isUsableTool(item, event.getBlock().getType()) && dropItemOnlyWithTool.value >= 1) return;


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
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }


    /*public static boolean isUsableTool(ItemStack tool, Material block) {
        Block nmsBlock = CraftMagicNumbers.getBlock(block);
        if (nmsBlock == null) {
            return false;
        }
        IBlockData data = nmsBlock.getBlockData();
        if (!data.isRequiresSpecialTool()) return true;
        return tool != null && tool.getType() != Material.AIR && org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers.getItem(tool.getType()).canDestroySpecialBlock(data);
    }*/
}