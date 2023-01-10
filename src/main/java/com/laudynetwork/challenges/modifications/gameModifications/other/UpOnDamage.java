package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UpOnDamage extends GameMod {
    public ChallengeConfig challengeConfig;
    private final DoubleObject blocks = new DoubleObject(50, 20, 70, 5);

    public UpOnDamage() {
        super("Up on Damage", "uod", Material.CHEST, ModManager.ModType.PLAYER, ModManager.ModStatus.OPEN, "Teleport x Blocks up on Damage");

        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        description.add("blocks: {{variable}}");

        configEntries.add(new ConfigEntry("Amount of blocks to get teleported Up", description, blocks, ConfigEntry.ConfigEntryType.SLIDER, Material.GRASS_BLOCK));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        tpPlayer(player);
    }

    private void tpPlayer(Player player) {
        Block block = player.getLocation().clone().add(0, blocks.value, 0).getBlock();

        if (block.getType() == Material.AIR && block.getRelative(BlockFace.UP, 1).getType() == Material.AIR) {
            player.teleport(block.getLocation());
        } else {
            Block temp = block;
            int i = 0;

            while (!(temp.getType() == Material.AIR && temp.getRelative(BlockFace.UP, 1).getType() == Material.AIR)) {
                i += 1;

                if (i >= blocks.value) {
                    return;
                }

                temp = block.getRelative(BlockFace.DOWN, i);
            }
        }
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }
}

