package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
import com.laudynetwork.challenges.util.DoubleObject;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HeartLimit extends GameMod {
    public ChallengeConfig challengeConfig;
    DoubleObject hearts = new DoubleObject(10, 0.5, 10, 0.5);
    private double defaultHearts = 10;

    public HeartLimit() {
        super("Heart Limit", "hl", Material.POPPY, ModManager.ModType.GOALS, ModManager.ModStatus.BETA, "");

        List<ConfigEntry> configEntries = new ArrayList<>();
        List<String> description = new ArrayList<>();

        description.add("Hearts: {{variable}}<3");

        configEntries.add(new ConfigEntry("Amount of Hearst", description, hearts, ConfigEntry.ConfigEntryType.SLIDER, Material.CLOCK));

        challengeConfig = new ChallengeConfig("Config", configEntries.toArray(new ConfigEntry[0]));
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onPlayerChange(PlayerGameModeChangeEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (!Challenges.get().getPlayingPlayers().contains(event.getPlayer())) return;

        updatePlayer(event.getPlayer());
    }

    @Override
    public void onUpdate() {
        updateAllPlayers();
    }

    private void updateAllPlayers() {
        for (Player player : Challenges.get().getPlayingPlayers()) {
            updatePlayer(player);
        }
    }

    private void updatePlayer(Player player) {
        if (!enabled) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(defaultHearts);

        else player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hearts.getValue() * 2);
    }

    @Override
    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isRightClick()) {
            player.openInventory(challengeConfig.getInventory());
        }
    }
}
