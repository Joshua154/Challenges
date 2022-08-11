package com.laudynetwork.challenges.commands;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class GamemodeCommand implements CommandExecutor {

    private final String USAGE = Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Nutze: /gms /gmc /gma /gmsp oder /gm <0/1/2/3> [<player>]";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("gms")) {
            if (args.length == 0) {
                if (isNotAPlayer(sender)) return true;
                handle(null, (Player) sender, GameMode.SURVIVAL);
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (playerDoesNotExist(sender, target)) return true;
                assert target != null;
                handle(sender, target, GameMode.SURVIVAL);
            }
        } else if (label.equalsIgnoreCase("gmc")) {
            if (args.length == 0) {
                if (isNotAPlayer(sender)) return true;
                handle(null, (Player) sender, GameMode.CREATIVE);
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (playerDoesNotExist(sender, target)) return true;
                assert target != null;
                handle(sender, target, GameMode.CREATIVE);
            }
        } else if (label.equalsIgnoreCase("gma")) {
            if (args.length == 0) {
                if (isNotAPlayer(sender)) return true;
                handle(null, (Player) sender, GameMode.ADVENTURE);
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (playerDoesNotExist(sender, target)) return true;
                assert target != null;
                handle(sender, target, GameMode.ADVENTURE);
            }
        } else if (label.equalsIgnoreCase("gmsp")) {
            if (args.length == 0) {
                if (isNotAPlayer(sender)) return true;
                handle(null, (Player) sender, GameMode.SPECTATOR);
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (playerDoesNotExist(sender, target)) return true;
                assert target != null;
                handle(sender, target, GameMode.SPECTATOR);
            }
        } else {
            if (args.length == 1) {
                Player player = (Player) sender;
                if (Stream.of("0", "s", "survival", "überleben").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    if (isNotAPlayer(sender)) return true;
                    handle(null, player, GameMode.SURVIVAL);
                } else if (Stream.of("1", "c", "creative", "kreativ").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    if (isNotAPlayer(sender)) return true;
                    handle(null, player, GameMode.CREATIVE);
                } else if (Stream.of("2", "a", "adventure", "abenteuer").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    if (isNotAPlayer(sender)) return true;
                    handle(null, player, GameMode.ADVENTURE);
                } else if (Stream.of("3", "sp", "spectator", "zuschauer").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    if (isNotAPlayer(sender)) return true;
                    handle(null, player, GameMode.SPECTATOR);
                } else {
                    sender.sendMessage(USAGE);
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (playerDoesNotExist(sender, target)) return true;
                if (Stream.of("0", "s", "survival", "überleben").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    assert target != null;
                    handle(sender, target, GameMode.SURVIVAL);
                } else if (Stream.of("1", "c", "creative", "kreativ").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    assert target != null;
                    handle(sender, target, GameMode.CREATIVE);
                } else if (Stream.of("2", "a", "adventure", "abenteuer").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    assert target != null;
                    handle(sender, target, GameMode.ADVENTURE);
                } else if (Stream.of("3", "sp", "spectator", "zuschauer").anyMatch(s -> args[0].equalsIgnoreCase(s))) {
                    assert target != null;
                    handle(sender, target, GameMode.SPECTATOR);
                } else {
                    sender.sendMessage(USAGE);
                }
            } else {
                sender.sendMessage(USAGE);
            }
        }
        return false;
    }

    private boolean isNotAPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "This command is only for players."));
            return true;
        }
        return false;
    }

    private boolean playerDoesNotExist(CommandSender sender, Player player) {
        if (player == null) {
            sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Player is not online or does not exists."));
            return true;
        }
        return false;
    }

    private void handle(CommandSender sender, @NotNull Player target, GameMode gameMode) {
        if (target.getGameMode().equals(gameMode)) return;
        target.setGameMode(gameMode);
        if (sender == null) {
            target.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Set own game mode to " + Challenges.PRIMARY_COLOR + gameMode.name().toUpperCase() + Challenges.SECONDARY_COLOR + " Mode"));
        } else if (target != sender) {
            target.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Your game mode has been updated to " + Challenges.PRIMARY_COLOR + gameMode.name().toUpperCase() + Challenges.SECONDARY_COLOR + " Mode"));
            sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Set " + Challenges.PRIMARY_COLOR + target.getName() + Challenges.SECONDARY_COLOR + "'s game mode to " + Challenges.PRIMARY_COLOR + gameMode.name().toUpperCase() + Challenges.SECONDARY_COLOR + " Mode"));
        }
    }
}
