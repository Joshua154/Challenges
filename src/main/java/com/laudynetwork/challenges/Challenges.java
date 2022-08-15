package com.laudynetwork.challenges;

import com.laudynetwork.challenges.Listener.FunctionsOnTimerPause;
import com.laudynetwork.challenges.Listener.GameModeSwitchListener;
import com.laudynetwork.challenges.Listener.JoinListener;
import com.laudynetwork.challenges.commands.*;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.timer.DisplayMode;
import com.laudynetwork.challenges.timer.Timer;
import com.laudynetwork.challenges.timer.TimerMode;
import com.laudynetwork.laudynetworkapi.gui.GUIEH;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Challenges extends JavaPlugin implements Listener {

    public static final String PRIMARY_COLOR = ChatColor.GOLD.toString();
    public static final String SECONDARY_COLOR = ChatColor.DARK_GRAY.toString();
    public static final String PREFIX = SECONDARY_COLOR + "[" + PRIMARY_COLOR + "ChallengeManager" + SECONDARY_COLOR + "] " + ChatColor.RESET;

    private static Challenges instance;
    @Getter
    private ModManager modManager;

    public Timer timer;

    @Getter
    private Scoreboard hiddenPlayerScoreBord;
    @Getter
    private Team hiddenPlayerTeam;

    private final List<Player> hiddenPlayers = new ArrayList<>();
    FileConfiguration config = getConfig();
    @Getter
    private final FunctionsOnTimerPause timerPauseDisable = new FunctionsOnTimerPause();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(timerPauseDisable, this);
        Bukkit.getPluginManager().registerEvents(new GameModeSwitchListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new GUIEH(), this);

        modManager = new ModManager();
        modManager.registerChallenges();
        modManager.registerGameMods();


        hiddenPlayerScoreBord = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        hiddenPlayerScoreBord.registerNewTeam("hiddenPlayer");

        hiddenPlayerTeam = hiddenPlayerScoreBord.getTeam("hiddenPlayer");
        assert hiddenPlayerTeam != null;
        hiddenPlayerTeam.setColor(ChatColor.DARK_GRAY);
        hiddenPlayerTeam.setPrefix(ChatColor.ITALIC + "Spec" + ChatColor.RESET + " | ");
        hiddenPlayerTeam.setCanSeeFriendlyInvisibles(true);
        hiddenPlayerTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        hiddenPlayerTeam.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.NEVER);


        config.addDefault("PluginManagerConfig", true);
        config.options().copyDefaults(true);
        saveDefaultConfig();


        timer = new Timer(false, 1, TimerMode.COUNTUP, DisplayMode.ACTIONBAR);
        timer.setPaused(true);


        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand());
        Objects.requireNonNull(getCommand("timer")).setExecutor(new TimerCommand());
        Objects.requireNonNull(getCommand("location")).setExecutor(new SaveLocationPoint());
        Objects.requireNonNull(getCommand("test")).setExecutor(new Test());
        Objects.requireNonNull(getCommand("gui")).setExecutor(new Select());
    }

    @Override
    public void onDisable() {
        //Plugin shutdown logic
        if (this.timer != null) {
            config.set("timer.time", timer.getTime());
            config.set("timer.display-mode", timer.getDisplayMode());
            config.set("timer.timer-mode", timer.getTimerMode());
        }
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    public static Challenges get() {
        return instance;
    }

    public void addHiddenPlayer(Player player) {
        hiddenPlayers.add(player);
    }

    public List<Player> getHiddenPlayers() {
        return hiddenPlayers;
    }

    public void removeHiddenPlayer(Player player) {
        hiddenPlayers.remove(player);
    }

    public void addPlayingPlayer(Player player) {
        removeHiddenPlayer(player);
    }

    public void removePlayingPlayer(Player player) {
        addHiddenPlayer(player);
    }

    public List<Player> getPlayingPlayers() {
        List<Player> players = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!hiddenPlayers.contains(player)) {
                players.add(player);
            }
        }
        return players;
    }

    public void addHiddenEntity(Entity entity) {
        hiddenPlayerTeam.addEntry(entity.getUniqueId().toString());
    }
}
