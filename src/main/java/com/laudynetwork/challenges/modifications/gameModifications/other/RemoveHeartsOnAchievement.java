package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import com.laudynetwork.networkutils.api.item.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RemoveHeartsOnAchievement extends GameMod {
    private DoubleObject removeAmount = new DoubleObject(0.5, 0.5, 20, 0.5);
    protected ChallengeConfig challengeConfig;

    public RemoveHeartsOnAchievement() {
        super("Achievement = -0,5 Hearts permanent", "rhoa", Material.AIR, ModManager.ModType.PLAYER, ModManager.ModStatus.BETA, "");

        super.itemStack = new ItemBuilder(Material.POTION).build();


        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        description.add("remove Amount: {{variable}} Hearts");

        configEntries.add(new ConfigEntry("Amount of Hearts", description, removeAmount, ConfigEntry.ConfigEntryType.SLIDER, Material.HEART_OF_THE_SEA));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (Challenges.get().getHiddenPlayers().contains(event.getPlayer())) return;

        if (event.getAdvancement().getKey().getKey().contains("root") ||
                event.getAdvancement().getKey().getKey().contains("recipes")) {
            return;
        }


        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() == 1) {
                failed(player, "Collected to many Achievements");
                return;
            }


            AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(attribute.getBaseValue() - (removeAmount.value * 2));
        }
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }
}