package com.laudynetwork.challenges.commands;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.laudynetworkapi.database.mongodb.MongoManager;
import org.bson.Document;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.List;

public class Test implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        Document document = new Document();
        document.put("challengeProperties", Challenges.get().getModManager().getMod("br").generateConfigString());
        document.put("lastTry", new Date());
        document.put("uniqueId", player.getUniqueId().toString());

        MongoManager.getInstance().getDatabase().getCollection("challenges_saves").insertOne(document);
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
