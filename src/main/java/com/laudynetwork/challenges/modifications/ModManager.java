package com.laudynetwork.challenges.modifications;

import com.laudynetwork.challenges.api.chatutils.HexColor;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.ClassPath;
import com.laudynetwork.challenges.Challenges;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    public void registerChallenges() {
        registerAllMods("com.laudynetwork.challenges.modifications.challenges");
    }


    public void registerGameMods() {
        registerAllMods("com.laudynetwork.challenges.modifications.gameModifications.randomizer");

        registerAllMods("com.laudynetwork.challenges.modifications.gameModifications.other");

        registerAllMods("com.laudynetwork.challenges.modifications.gameModifications.force");

        registerAllMods("com.laudynetwork.challenges.modifications.gameModifications.worldModification");

        //getMod("cr").enable();
    }

    private static ModManager instance;
    private static List<GameMod> gameMods;
    private boolean ended = false;


    public ModManager() {
        gameMods = new ArrayList<>();
        instance = this;
    }

    public static ModManager get() {
        return instance;
    }

    public void registerMod(GameMod gameMod) {
        gameMods.add(gameMod);
    }

    public GameMod getMod(String name) {
        GameMod result = gameMods.stream().filter(mod -> mod.getShortName().equals(name)).findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        return gameMods.stream().filter(mod -> mod.getName().equals(name)).findFirst().orElse(null);
    }

    public GameMod getMod(GameMod gameMod) {
        return gameMods.stream().filter(mod1 -> mod1.equals(gameMod)).findFirst().orElse(null);
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

    public List<GameMod> getMods() {
        return gameMods;
    }

    public List<GameMod> getActiveMods() {
        return gameMods.stream().filter(GameMod::isEnabled).collect(Collectors.toList());
    }

    public List<GameMod> getMods(Player player) {
        List<GameMod> mods = new ArrayList<>();

        for (GameMod mod : gameMods) {
            if (player.hasPermission("*")) {
                mods.add(mod);
            } else if (mod.getModStatus() == ModStatus.OPEN || mod.getModStatus() == ModStatus.BETA) {
                mods.add(mod);
            } else if (player.hasPermission(mod.getPermission())) {
                mods.add(mod);
            }
        }
        return mods;
    }

    public enum ModType {
        WORLD("World Specific", "world", Color.GREEN, new ItemStack(Material.GRASS_BLOCK), "temp"),
        PLAYER("Player Specific", "player", Color.YELLOW, new ItemStack(Material.PLAYER_HEAD), "temp"),
        GOALS("Goals", "goals", Color.CYAN, new ItemStack(Material.EMERALD), "temp");

        private final String displayName;
        private final String shortName;
        private final Color color;
        private final ItemStack material;
        private final String description;

        private final String[] TeamUUIDS = {
                "596b9acc-d337-4bed-a7a5-7c407d2938cf", //TimetraveIIer
                "0093ca6d-26bb-43df-9b4f-47eddc5772bb", //LaudyTV
                "65669928-00b6-4849-becb-b792bcb3f881", //CreepertimeHD
                "1d476fa1-9588-4369-b775-f41c0e7751d6", //LangtJetzt
                "7ef8247a-886e-486e-9805-8555438d2aeb", //justTreme
                "7754fba5-c933-44f0-83fb-6ee56f2c77af", //_Almi_
        };

        ModType(String displayName, String shortName, Color color, ItemStack material, String description) {
            this.displayName = displayName;
            this.shortName = shortName;
            this.color = color;
            this.material = material;
            this.description = description;
        }

        public static boolean contains(String string) {
            for (ModType modType : ModType.values()) {
                if (modType.name().equals(string)) {
                    return true;
                }
            }
            return false;
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
            return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        }

        public ItemStack getMaterial() {
            /*if (material.getType().equals(Material.PLAYER_HEAD)) {
                return new CustomHeadBuilder().setSkullOwner(getRandomTeamMemberUUID()).build();
            }*/
            return material;
        }

        private String getRandomTeamMemberUUID() {
            Random random = new Random();

            return TeamUUIDS[random.nextInt(TeamUUIDS.length)];
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

        public String getColorString() {
            return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        }
    }

    public List<GameMod> getModsAsSearch(String search, Player player) {
        List<GameMod> result = new ArrayList<>();

        for (GameMod mod : getMods(player)) {
            if (mod.getName().toLowerCase().contains(search.toLowerCase()) ||
                    mod.getDescription().toLowerCase().contains(search.toLowerCase()) ||
                    mod.getModStatus().getName().toLowerCase().contains(search.toLowerCase())) {
                result.add(mod);
            }
        }

        return result;
    }

    public void registerAllMods(String classPath) {
        Plugin plugin = Challenges.get();
        try {
            UnmodifiableIterator var2 = ClassPath.from(plugin.getClass().getClassLoader()).getTopLevelClasses(classPath).iterator();

            while (var2.hasNext()) {
                ClassPath.ClassInfo classInfo = (ClassPath.ClassInfo) var2.next();
                Object object = Class.forName(classInfo.getName(), true, plugin.getClass().getClassLoader()).getDeclaredConstructor().newInstance();
                if (object instanceof GameMod) {
                    registerMod((GameMod) object);
                }
            }

        } catch (IOException | InvocationTargetException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException ignored) {
        }
    }
}
