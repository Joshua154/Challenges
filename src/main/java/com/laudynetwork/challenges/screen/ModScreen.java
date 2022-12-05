package com.laudynetwork.challenges.screen;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.Mod;
import com.laudynetwork.challenges.modifications.ModManager;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModScreen implements IGUI {
    ItemStack pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "RETURN").build();
    ModManager.ModType type;

    public ModScreen(String type) {
        this.type = ModManager.ModType.valueOf(type);
    }

    @Override
    public void onClick(Player player, int i, ItemStack itemStack, ClickType clickType) {
        if (itemStack == null) return;
        if (itemStack.equals(pane)) {
            player.openInventory(new MainScreen().getInventory());
            return;
        }
        if (itemStack.getItemMeta() == null) return;
        if (itemStack.getItemMeta().getLocalizedName().isEmpty()) return;

        if (clickType.isLeftClick()) {
            Challenges.get().getModManager().getMod(itemStack.getItemMeta().getLocalizedName()).toggle();
            player.openInventory(getInventory());
        } else if (clickType.isRightClick()) {
            Challenges.get().getModManager().getMod(itemStack.getItemMeta().getLocalizedName()).onSettingsClick(player, i, itemStack, clickType);
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Inventory gui = Bukkit.createInventory(this, 9 * 6, HexColor.translate(type.getColorString() + type.getDisplayName()));
        int count = 0;
        ItemStack[] items = new ItemStack[9 * 6];
        for (int i = 0; i < gui.getSize(); i++) {
            if (i < 9 || (i + 1) % 9 == 0 || (i) % 9 == 0 || i >= 45) {
                items[i] = pane;
            } else {

                if (count == ModManager.get().getMods().stream().filter(mod -> mod.getType().equals(type)).count()) {
                    items[i] = new ItemStack(Material.AIR);
                } else {
                    items[i] = generateItem(ModManager.get().getMods().stream().filter(mod -> mod.getType().equals(type)).toList().get(count));
                    count++;
                }
            }
        }
        gui.setContents(items);
        return gui;
    }

    private ItemStack generateItem(Mod mod) {
        List<String> lore = new ArrayList<>();

        lore.add(HexColor.translate(mod.getDescription()));
        lore.add(HexColor.translate(mod.getModStatus().getColorString() + mod.getModStatus().getName()));
        lore.add((mod.isEnabled() ? ChatColor.GREEN + "Enabled" : ChatColor.DARK_RED + "Disabled"));

        if (hasOnSettingsClick(mod)) {
            lore.add(ChatColor.GREEN + "R-Click to configure");
        }


        return new ItemBuilder(mod.getMaterial())
                .setDisplayName(HexColor.translate(ChatColor.BOLD + mod.getColorString() + mod.getName()))
                .setLore(lore)
                .setLocalizedName(mod.getShortName())
                .build();
    }

    private boolean hasOnSettingsClick(Mod mod) {
        return Arrays.stream(mod.getClass().getDeclaredMethods()).anyMatch(method -> method.getName().equals("onSettingsClick"));
    }
}