package com.laudynetwork.challenges.animetedGUI;

import com.laudynetwork.challenges.Challenges;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AnimatedGUI {
    private final Player player;
    private final Inventory inventory;
    private final ItemStack[] itemsTo;
    private final int duration;
    private final String orientation;
    private final int width = 9;
    private final int heigth;

    private int step = 0;

    public AnimatedGUI(Player player, Inventory inventory, ItemStack[] itemsTo, int duration, String orientation) {
        this.player = player;
        this.inventory = inventory;
        this.itemsTo = itemsTo;
        this.duration = duration;
        this.orientation = orientation;

        this.heigth = inventory.getSize() / width;

        animate();
    }

    public void animate() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (step >= width) {
                    step = 0;
                }
                inventory.setContents(getItems());
                player.openInventory(inventory);
                step++;
            }
        }.runTaskTimer(Challenges.get(), 20, 20);
    }

    private ItemStack[] getItems() {
        ItemStack[] result = inventory.getContents();

        if (orientation.toLowerCase().equals("up")) {
            for (int i = 0; i < inventory.getSize(); i++) {
                result[i] = inventory.getItem(i + width >= inventory.getSize() ? i + width - inventory.getSize() : i + width);
            }
            for (int i = 0; i < width; i++) {
                result[i] = itemsTo[i + (step * width)];
            }


        } else if (orientation.toLowerCase().equals("down")) {
            for (int i = 0; i < inventory.getSize(); i++) {
                result[i] = inventory.getItem((i - width + 1 <= 0 ? i + inventory.getSize() - width : i - width));
            }
            for (int i = inventory.getSize() - width; i < inventory.getSize(); i++) {
                result[i] = itemsTo[i + (step * width)];
            }


        } else if (orientation.toLowerCase().equals("left")) {
            for (int i = 0; i < inventory.getSize(); i++) {
                result[i] = inventory.getItem(Math.round(i / width) * width + (i + 1) % width);
            }
            for (int i = 0; i < heigth; i++) {
                result[i] = itemsTo[(i * width + width - 1) + (step * width)];
            }


        } else if (orientation.toLowerCase().equals("right")) {
            for (int i = 0; i < inventory.getSize(); i++) {
                result[i] = inventory.getItem((i - 1) + (i % width == 0 ? width : 0));
            }
            for (int i = 0; i < heigth; i++) {
                result[i] = itemsTo[(i * width) + (step * width)];
            }
        }
        return result;
    }
}
