package com.laudynetwork.challenges.modifications;


import com.laudynetwork.challenges.Challenges;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class Mod implements Listener, CommandExecutor, Comparable<Mod> {
    @Getter
    protected String name;
    @Getter
    protected Material material;
    @Getter
    protected ModManager.ModType modType;
    @Getter
    protected ModManager.ModStatus modStatus;
    protected boolean enabled = true;

    public Mod(String name, Material material, ModManager.ModType modType, ModManager.ModStatus modStatus) {
        this.name = name;
        this.material = material;
        this.modType = modType;
        this.modStatus = modStatus;
    }

    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, Challenges.get());
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

    public void parseConfig() {

    }

    public ModManager.ModType getType() {
        return modType;
    }
}
