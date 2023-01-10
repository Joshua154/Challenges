package com.laudynetwork.challenges.modifications;


import com.laudynetwork.challenges.Challenges;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class GameMod implements Listener, CommandExecutor, Comparable<GameMod> {
    protected String name;
    protected final String shortName;
    protected final String description;
    protected ItemStack itemStack;
    protected Material material;
    protected Color color = Color.WHITE;
    protected ModManager.ModType modType;
    protected ModManager.ModStatus modStatus;
    protected boolean enabled = false;
    protected boolean init = false;
    protected String permission = "*";

    public GameMod(String name, String shortName, Material material, ModManager.ModType modType, ModManager.ModStatus modStatus, String description) {
        this.name = name;
        this.shortName = shortName;
        this.itemStack = new ItemStack(material);
        this.material = material;
        this.modType = modType;
        this.modStatus = modStatus;
        this.description = description;

        Bukkit.getPluginManager().registerEvents(this, Challenges.get());
    }

    public GameMod(String name, String shortName, ItemStack itemStack, ModManager.ModType modType, ModManager.ModStatus modStatus, String description) {
        this.name = name;
        this.shortName = shortName;
        this.itemStack = itemStack;
        this.material = itemStack.getType();
        this.modType = modType;
        this.modStatus = modStatus;
        this.description = description;

        Bukkit.getPluginManager().registerEvents(this, Challenges.get());
    }

    public void enable() {
        if (!init) init();
        init = true;
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
            onUpdate();
        }
    }

    @Override
    public int compareTo(@NotNull GameMod gameMod) {
        return 0;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    public void onClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        //
    }

    public void defaultClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        if (clickType.isLeftClick()) {
            this.toggle();
        }
        if (!player.hasPermission("*")) {
            return;
        }

        onClick(player, slot, itemStack, clickType);
    }

    public void init() {
        //
    }

    public void onUpdate() {
        //
    }

    public String generateConfigString() {
        return "";
    }

    /*public void parseConfig(String configString) {

    }*/

    public ModManager.ModType getType() {
        return modType;
    }

    protected void failed(Player player, String cause) {
        Challenges.get().getModManager().endChallenge(player, cause, true);
    }

    protected void complete(Player player, String cause) {
        Challenges.get().getModManager().endChallenge(player, cause, false);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getDescription() {
        return description;
    }

    public Material getMaterial() {
        return itemStack.getType();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public ModManager.ModStatus getModStatus() {
        return modStatus;
    }

    public Color getColor() {
        return color;
    }

    public String getColorString() {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public int compare(GameMod o2) {
        return this.getName().compareTo(o2.getName());
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
