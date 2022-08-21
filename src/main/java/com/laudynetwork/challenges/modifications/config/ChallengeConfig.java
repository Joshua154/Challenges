package com.laudynetwork.challenges.modifications.config;

import com.laudynetwork.challenges.screen.MainScreen;
import com.laudynetwork.laudynetworkapi.builder.ItemBuilder;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import com.laudynetwork.laudynetworkapi.gui.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ChallengeConfig implements IGUI {
    ItemStack pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "RETURN").build();
    private final String name;
    private final ConfigEntry[] configEntries;

    public ChallengeConfig(String name, ConfigEntry[] configEntries) {
        this.name = name;
        this.configEntries = configEntries;
    }


    @Override
    public void onClick(Player player, int i, ItemStack itemStack, ClickType clickType) {
        if (itemStack == null) return;
        if (itemStack.equals(pane)) {
            player.openInventory(new MainScreen().getInventory());
            return;
        }

        if (rawSlot(i) >= 0) {
            this.configEntries[rawSlot(i)].onClick(clickType);

            player.openInventory(this.getInventory());
        }
    }

    private int rawSlot(int i) {
        if (i < 9) {
            return -1;
        } else if (i % 9 == 0) {
            return -1;
        } else if ((i + 1) % 9 == 0) {
            return -1;
        } else if (i >= 45) {
            return -1;
        } else {
            return i - Math.round(i / 9) * 2 - 8; //????
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Inventory gui = Bukkit.createInventory(this, 9 * 6, HexColor.translate(name));
        int count = 0;
        ItemStack[] items = new ItemStack[9 * 6];
        for (int i = 0; i < gui.getSize(); i++) {
            if (i < 9 || (i + 1) % 9 == 0 || (i) % 9 == 0 || i >= 45) {
                items[i] = pane;
            } else {
                if (count == configEntries.length) {
                    items[i] = new ItemStack(Material.AIR);
                } else {
                    items[i] = configEntries[count].getItem();
                    count++;
                }
            }
        }
        gui.setContents(items);
        return gui;
    }
}
