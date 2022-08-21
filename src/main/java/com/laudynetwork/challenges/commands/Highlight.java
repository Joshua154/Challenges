package com.laudynetwork.challenges.commands;

import com.laudynetwork.challenges.blockHighlight.BlockHighlight;
import com.laudynetwork.challenges.blockHighlight.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Highlight implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return false;
        for (Player playerToSend : Bukkit.getOnlinePlayers()) {
            Util.sendBlockHighlight(playerToSend, new BlockHighlight(new Vector(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])), new Color(Integer.parseInt(strings[3]), Integer.parseInt(strings[4]), Integer.parseInt(strings[5]), Integer.parseInt(strings[6])), strings[7], Integer.parseInt(strings[8])));
        }
        return true;
    }
}
