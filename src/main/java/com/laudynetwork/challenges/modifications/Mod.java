package com.laudynetwork.challenges.modifications;


import com.laudynetwork.challenges.Challenges;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class Mod implements Listener, CommandExecutor, Comparable<Mod> {
    protected String name;
    protected final String shortName;
    protected final String description;
    protected Material material;
    protected ModManager.ModType modType;
    protected ModManager.ModStatus modStatus;
    protected boolean enabled = false;

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
}
