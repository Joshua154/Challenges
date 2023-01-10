package com.laudynetwork.challenges.screen;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.api.chatutils.HexColor;
import com.laudynetwork.challenges.api.gui.IGUI;
import com.laudynetwork.networkutils.api.item.itembuilder.HeadBuilder;
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

import java.util.*;

public class SearchScreen implements IGUI {
    private final int height = 5;
    private final int width = 9;
    ItemStack pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).displayName(Component.text(ChatColor.RED + "RETURN")).customModelData(1).build();
    ItemStack upArrow = new HeadBuilder().skullOwner("1ad6c81f899a785ecf26be1dc48eae2bcfe777a862390f5785e95bd83bd14d").persistentData(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING, "upArrow").build();
    ItemStack downArrow = new HeadBuilder().skullOwner("882faf9a584c4d676d730b23f8942bb997fa3dad46d4f65e288c39eb471ce7").persistentData(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING, "downArrow").build();
    ItemStack backArrow = new HeadBuilder().skullOwner("ad73cf66d31b83cd8b8644c15958c1b73c8d97323b801170c1d8864bb6a846d").persistentData(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING, "backArrow").build();
    List<GameMod> mods = new ArrayList<>();

    private int page = 0;

    public SearchScreen(String searchName, Player player) {
        player.openInventory(new MainScreen().getInventory());
        mods.addAll(Challenges.get().getModManager().getModsAsSearch(searchName, player));

        mods.sort(Comparator.comparing(GameMod::getName));
    }


    @Override
    public void onClick(Player player, int i, ItemStack itemStack, ClickType clickType) {
        if (itemStack == null) return;
        if (itemStack.equals(pane)) {
            player.openInventory(new MainScreen().getInventory());
            return;
        }
        if (itemStack.getItemMeta() == null) return;
        String localName = itemStack.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING);
        if (localName == null) return;


        if (localName.equals("downArrow")) {
            page += 1;
            player.openInventory(getInventory());
            return;
        } else if (localName.equals("upArrow")) {
            page -= 1;
            player.openInventory(getInventory());
            return;
        }


        Objects.requireNonNull(getMod(localName, mods)).defaultClick(player, i, itemStack, clickType);

        if (clickType.isLeftClick()) {
            player.openInventory(getInventory());
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        Inventory gui = Bukkit.createInventory(this, width * height, Component.text(HexColor.translate(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Search Results:")));
        int count = 0;
        ItemStack[] items = new ItemStack[gui.getSize()];
        /*for (int i = 0; i < gui.getSize(); i++) {
            if (i % width == 0) {
                items[i] = pane;

                if (i == 0) {
                    if (page > 0) {
                        items[i] = upArrow;
                    }
                } else if (i == width * (height - 1)) {
                    if (page < Math.ceil(mods.size() / (gui.getSize() - height))) {
                        items[i] = downArrow;
                    }
                }
            } else {

                if (count + (page * (gui.getSize() - height)) == mods.size()) {
                    items[i] = new ItemStack(Material.AIR);
                } else {
                    items[i] = generateItem(mods.get(count + (page * (gui.getSize() - height))));
                    count++;
                }
            }
        }*/
        gui.setContents(items);
        return gui;
    }

    private ItemStack generateItem(GameMod gameMod) {
        ArrayList<Component> lore = new ArrayList<>();

        if (!gameMod.getDescription().isEmpty()) {
            lore.add(Component.text(HexColor.translate(gameMod.getDescription())));
        }
        if (!gameMod.getModStatus().equals(ModManager.ModStatus.OPEN)) {
            lore.add(Component.text(HexColor.translate(gameMod.getModStatus().getColorString() + gameMod.getModStatus().getName())));
        }
        lore.add(Component.text((gameMod.isEnabled() ? ChatColor.GREEN + "Enabled" : ChatColor.DARK_RED + "Disabled")));

        if (hasOnClick(gameMod)) {
            lore.add(Component.text(ChatColor.GREEN + "R-Click to configure"));
        }


        return new ItemBuilder(gameMod.getItemStack())
                .displayName(Component.text(HexColor.translate(ChatColor.BOLD + gameMod.getColorString() + gameMod.getName())))
                .lore(lore)
                .persistentData(new NamespacedKey(Challenges.get(), "name"), PersistentDataType.STRING, gameMod.getShortName())
                .build();
    }

    private boolean hasOnClick(GameMod gameMod) {
        return Arrays.stream(gameMod.getClass().getDeclaredMethods()).anyMatch(method -> method.getName().equals("onClick"));
    }

    private GameMod getMod(String shortName, List<GameMod> gameMods) {
        for (GameMod mod : gameMods) {
            if (Objects.equals(mod.getShortName(), shortName)) {
                return mod;
            }
        }
        return null;
    }
}