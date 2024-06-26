package com.laudynetwork.challenges.api.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class GUIEH implements Listener {

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof IGUI igui)) return;
        if (event.getClickedInventory() == null || event.getClickedInventory().getHolder() instanceof Player) return;
        if (event.getCurrentItem() == null) return;
        event.setCancelled(true);
        igui.onClick((Player) event.getWhoClicked(), event.getRawSlot(), event.getCurrentItem(),
                event.getClick());
    }
}
