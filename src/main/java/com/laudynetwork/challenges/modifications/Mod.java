package com.laudynetwork.challenges.modifications;


import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.config.ChallengeConfig;
import com.laudynetwork.challenges.modifications.config.ConfigEntry;
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
import java.util.ArrayList;
import java.util.List;

public class Mod implements Listener, CommandExecutor, Comparable<Mod> {
    protected String name;
    protected final String shortName;
    protected final String description;
    protected Material material;
    protected Color color = Color.WHITE;
    protected ModManager.ModType modType;
    protected ModManager.ModStatus modStatus;
    protected boolean enabled = false;
    protected boolean init = false;

    public Mod(String name, String shortName, Material material, ModManager.ModType modType, ModManager.ModStatus modStatus, String description) {
        this.name = name;
        this.shortName = shortName;
        this.material = material;
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
        }
    }

    @Override
    public int compareTo(@NotNull Mod mod) {
        return 0;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    public void onSettingsClick(Player player, int slot, ItemStack itemStack, ClickType clickType) {
        //
    }

    public void init() {
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
        return material;
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
}
