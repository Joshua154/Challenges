package com.laudynetwork.challenges.screen;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.api.chatutils.HexColor;
import com.laudynetwork.challenges.api.gui.IGUI;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.networkutils.api.item.itembuilder.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class MainScreen implements IGUI {
    ItemStack pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).displayName(Component.text(ChatColor.RED + "")).customModelData(1).build();
    ItemStack search = new ItemBuilder(Material.COMPASS).displayName(Component.text(ChatColor.RED + "SEARCH")).customModelData(1).build();

    @Override
    public void onClick(Player player, int i, ItemStack itemStack, ClickType clickType) {
        if (itemStack == null) return;
        if (itemStack.equals(pane)) return;
        if (itemStack.equals(search)) {
            onSearchClick(player);
            return;
        }
        if (itemStack.getItemMeta() == null) return;
        String localName = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING);
        if (localName == null) return;


        if (ModManager.ModType.contains(localName)) {
            player.openInventory(new ModScreen(localName, player).getInventory());
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Inventory gui = Bukkit.createInventory(this, 9 * 5, HexColor.translate("&lChallenge Types"));
        int count = 0;
        Integer[] slots = new Integer[]{9 * 2 + 2, 9 * 2 + 4, 9 * 2 + 6};
        ItemStack[] items = new ItemStack[gui.getSize()];
        for (int i = 0; i < gui.getSize(); i++) {
            if (i < 9 || (i + 1) % 9 == 0 || (i) % 9 == 0 || i >= 9 * 4) {
                items[i] = pane;
            } else {
                if (count == ModManager.ModType.values().length || !Arrays.asList(slots).contains(i)) {
                    items[i] = new ItemStack(Material.AIR);
                } else {
                    items[i] = generateItem(ModManager.ModType.values()[count]);
                    count++;
                }
            }
            if (i == 8) {
                items[i] = search;
            }
        }

        gui.setContents(items);
        return gui;
    }

    private ItemStack generateItem(ModManager.ModType value) {
        return new ItemBuilder(value.getMaterial())
                .displayName(Component.text(HexColor.translate(ChatColor.BOLD + value.getColorString() + value.getDisplayName())))
                .lore(HexColor.translate(value.getDescription()).lines().toList().stream().map(Component::text).collect(Collectors.toCollection(ArrayList::new)))
                .persistentData(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING, value.name())
                .build();
    }

    public void generateScreen(Player commandSender) {
        commandSender.openInventory(getInventory());
    }

    private void onSearchClick(Player player) {
        /*SignGUI signGUI = new SignGUI(Challenges.get().getSignManager(), new SignClickCompleteHandler() {
            @Override
            public void onAnvilClick(SignCompleteEvent event) {
                String line = event.getLines()[0];
                if(!Objects.equals(line, "")){
                    Bukkit.getScheduler().runTaskLater(Challenges.get(), () -> {
                        event.getPlayer().openInventory(new SearchScreen(line, event.getPlayer()).getInventory());
                    }, 1L);
                    return;
                }
            }
        }).withLines("", "Enter your", "Search", "");

        signGUI.open(player);*/

        //player.openInventory(new SearchScreen("chunk", player).getInventory());



        /*AnvilGUI.Builder builder = new AnvilGUI.Builder();
        builder.plugin(Challenges.get());
        builder.title("Search");
        builder.itemLeft(new ItemBuilder(Material.COMPASS).setDisplayName(ChatColor.RED.toString()).build());
        // builder.itemRight(new CustomHeadBuilder().setSkullOwner(media.getTexture()).setDisplayName(media.getHexCode() + WordUtils.capitalizeFully(media.name())).build());
        // builder.preventClose();
        builder.onComplete((p, given) -> {
            if (given.contains(" ") || given.isEmpty()) return AnvilGUI.Response.text(ChatColor.RED + "Invalid");
            return AnvilGUI.Response.openInventory();
        });
        builder.open(player);*/
    }
}
