package com.laudynetwork.challenges.modifications;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.challenges.EnderDragonKill;
import com.laudynetwork.challenges.modifications.challenges.KillAllBosses;
import com.laudynetwork.challenges.modifications.challenges.NoDamage;
import com.laudynetwork.challenges.modifications.challenges.NoDeath;
import com.laudynetwork.challenges.modifications.gameModifications.BlockRandomizer;
import com.laudynetwork.challenges.modifications.gameModifications.CraftingRandomizer;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    public void registerChallenges() {
        registerMod(new EnderDragonKill());
        registerMod(new KillAllBosses());
        registerMod(new NoDamage());
        registerMod(new NoDeath());

        //getMod("ked").enable();
        //getMod("nda").enable();
    }


    public void registerGameMods() {
        registerMod(new BlockRandomizer());
        registerMod(new CraftingRandomizer());

        //getMod("br").enable();
        //getMod("cr").enable();
    }

    private static ModManager instance;
    private static List<Mod> mods;
    private boolean ended = false;


    public ModManager() {
        mods = new ArrayList<>();
        instance = this;
    }

    public static ModManager get() {
        return instance;
    }

    public void registerMod(Mod mod) {
        mods.add(mod);
    }

    public Mod getMod(String name) {
        return mods.stream().filter(mod -> mod.getShortName().equals(name)).findFirst().orElse(null);
    }

    public void endChallenge(Player player, String cause, boolean failed) {
        if (ended) return;
        ended = true;

        Challenges.get().timer.setRunning(false);

        if (failed) {
            for (Player p : Challenges.get().getPlayingPlayers()) {
                p.setGameMode(GameMode.SPECTATOR);
            }
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (player != null) {
                p.sendMessage(HexColor.translate(cause.replace("{{player}}", "&l" + player.getName())));
            } else {
                p.sendMessage(HexColor.translate(cause));
            }
        }
    }

    public List<Mod> getMods() {
        return mods;
    }

    public List<Mod> getActiveMods() {
        return mods.stream().filter(Mod::isEnabled).collect(Collectors.toList());
    }

    public enum ModType {
        GAME_MODIFICATION("Game Mod", "mod", Color.RED, "Game Modifications"),
        CHALLENGE("Challenge", "challenge", Color.RED, "Win/Lose Condition");

        private final String displayName;
        private final String shortName;
        private final Color color;
        private final String description;

        ModType(String displayName, String shortName, Color color, String description) {
            this.displayName = displayName;
            this.shortName = shortName;
            this.color = color;
            this.description = description;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getShortName() {
            return shortName;
        }

        public Color getColor() {
            return color;
        }

        public String getColorString() {
            return ChatColor.COLOR_CHAR + "" + color.getRed() + color.getGreen() + color.getBlue();
        }

        public String getDescription() {
            return description;
        }
    }

    public enum ModStatus {
        OPEN("Open", Color.GREEN),
        BETA("Beta", Color.YELLOW),
        CLOSED_BETA("Closed Beta", Color.ORANGE),
        WORK_IN_PROGRESS("Work in Progress", Color.RED);

        private final String name;
        private final Color color;

        ModStatus(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public Color getColor() {
            return color;
        }
    }
}
