package com.laudynetwork.challenges.modifications;

import com.laudynetwork.challenges.modifications.challenges.EnderDragonKill;
import com.laudynetwork.challenges.modifications.challenges.KillAllBosses;
import com.laudynetwork.challenges.modifications.challenges.NoDamage;
import com.laudynetwork.challenges.modifications.challenges.NoDeath;
import com.laudynetwork.challenges.modifications.gameModifications.BlockRandommizer;
import org.bukkit.ChatColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModManager {
    public void registerChallenges() {
        registerMod(new EnderDragonKill());
        registerMod(new KillAllBosses());
        registerMod(new NoDamage());
        registerMod(new NoDeath());

        getMod("Ender Dragon Kill Challenge").enable();
        getMod("No Damage Challenge").enable();
    }


    public void registerGameMods() {
        registerMod(new BlockRandommizer());
        mods.stream().filter(mod -> mod.getType() == ModType.GAME_MODIFICATION).forEach(Mod::enable);
    }

    private static ModManager instance;
    private static List<Mod> mods;


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
        return mods.stream().filter(mod -> mod.getName().equals(name)).findFirst().orElse(null);
    }

    public enum ModType {
        GAME_MODIFICATION("Game Mod", "test", Color.RED),
        CHALLENGE("Challenge", "c", Color.RED);

        private final String displayName;
        private final String shortName;
        private final Color color;

        ModType(String displayName, String shortName, Color color) {
            this.displayName = displayName;
            this.shortName = shortName;
            this.color = color;
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
    }

    public enum ModStatus{
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
