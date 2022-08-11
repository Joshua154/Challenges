package com.laudynetwork.challenges.commands;

import com.laudynetwork.challenges.Challenges;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SaveLocationPoint implements CommandExecutor, TabCompleter {
    Map<String, Location> locations = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(Challenges.get().getConfig().getConfigurationSection("locations") != null){
            Objects.requireNonNull(Challenges.get().getConfig().getConfigurationSection("locations")).getKeys(false).forEach(key -> locations.put(key, Challenges.get().getConfig().getLocation("locations." + key)));
        }

        if(!(sender instanceof Player)) return false;
        if(args.length == 0) {
            sender.sendMessage("/location <name>");
            return false;
        }
        if(locations.containsKey(args[0])){
            sender.sendMessage(Challenges.PRIMARY_COLOR + ChatColor.BOLD + args[0] + Challenges.SECONDARY_COLOR + ChatColor.BOLD + ": " + Challenges.PRIMARY_COLOR + ChatColor.BOLD + Math.round(locations.get(args[0]).getX()) + " " + Math.round(locations.get(args[0]).getY()) + " " + Math.round(locations.get(args[0]).getZ()));
        }else {
            Player player = (Player) sender;
            locations.put(args[0], player.getLocation());
            player.sendMessage("Location saved!");

            locations.forEach((key, value) -> Challenges.get().getConfig().set("locations." + key, value));
            Challenges.get().saveConfig();
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> locList = new ArrayList<>();
        if(Challenges.get().getConfig().getConfigurationSection("locations") != null){
            locList.addAll(Objects.requireNonNull(Challenges.get().getConfig().getConfigurationSection("locations")).getKeys(false));
        }
        System.out.println(locList);
        return locList;
    }
}
