package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class oneDurability extends GameMod {

    public oneDurability() {
        super("1 Durability", "od", Material.AIR, ModManager.ModType.PLAYER, ModManager.ModStatus.WORK_IN_PROGRESS, "");

        ItemStack displayItemStack = new ItemStack(Material.IRON_PICKAXE);
        Damageable damageable = (Damageable) displayItemStack.getItemMeta();
        damageable.setDamage(displayItemStack.getType().getMaxDurability());
        displayItemStack.setItemMeta((ItemMeta) damageable);

        super.itemStack = displayItemStack;
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;

        event.getInventory().setResult(getItem(Objects.requireNonNull(event.getInventory().getResult())));
    }

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;

        event.getItem().setItemStack(getItem(event.getItem().getItemStack()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;

        event.setCurrentItem(getItem(Objects.requireNonNull(event.getCurrentItem())));
    }


    private ItemStack getItem(@NotNull ItemStack itemStack) {
        if (itemStack.getType().getMaxDurability() > 0) {
            Damageable damageable = (Damageable) itemStack.getItemMeta();
            assert damageable != null;
            damageable.setDamage(itemStack.getType().getMaxDurability());
            itemStack.setItemMeta((ItemMeta) damageable);
        }
        return itemStack;
    }
}
