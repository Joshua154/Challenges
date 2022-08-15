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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ChallengesScreen implements IGUI {
    ItemStack pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName(ChatColor.RED + "").build();

    @Override
    public void onClick(Player player, int i, ItemStack itemStack, boolean b) {
        if (itemStack == null) return;
        if (itemStack.equals(pane)) return;
        if (itemStack.getItemMeta() == null) return;
        if (itemStack.getItemMeta().getLocalizedName().isEmpty()) return;

        Challenges.get().getModManager().getMod(itemStack.getItemMeta().getLocalizedName()).toggle();
        player.openInventory(getInventory());
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Inventory gui = Bukkit.createInventory(this, 9 * 6, "Challenge Types");
        gui.setContents(generateItems(gui));
        return gui;
    }

    private ItemStack[] generateItems(Inventory gui) {
        int count = 0;
        ItemStack[] items = new ItemStack[9 * 6];
        for (int i = 0; i < gui.getSize(); i++) {
            if (i < 9 || (i + 1) % 9 == 0 || (i) % 9 == 0 || i >= 45) {
                items[i] = pane;
            } else {

                if (count == ModManager.get().getMods().stream().filter(mod -> mod.getType().equals(ModManager.ModType.CHALLENGE)).count()) {
                    items[i] = new ItemStack(Material.AIR);
                } else {
                    items[i] = generateItem(ModManager.get().getMods().stream().filter(mod -> mod.getType().equals(ModManager.ModType.CHALLENGE)).toList().get(count));
                    count++;
                }
            }
        }
        return items;
    }

    private ItemStack generateItem(Mod mod) {
        return new ItemBuilder(mod.getMaterial())
                .setDisplayName(HexColor.translate(ChatColor.BOLD + mod.getType().getColorString() + mod.getName()))
                .setLore(mod.getDescription() + " " + (mod.isEnabled() ? ChatColor.GREEN + "Enabled" : ChatColor.DARK_RED + "Disabled"))
                .setLocalizedName(mod.getShortName())
                .build();
    }
}
