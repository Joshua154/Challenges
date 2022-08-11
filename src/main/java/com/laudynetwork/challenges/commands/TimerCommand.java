package com.laudynetwork.challenges.commands;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.timer.DisplayMode;
import com.laudynetwork.challenges.timer.Timer;
import com.laudynetwork.challenges.timer.TimerMode;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {

    private final String USAGE = Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Nutze: /start /reset /pause /resume";
    private final List<Entity> disabledEntities = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(isNotAPlayer(sender)) return false;
        if(args.length == 0) {
            handle(label, sender);
            return true;
        }
        else if(args.length == 1) {
            handle(args[0], sender);
            return true;
        }
        else {
            sender.sendMessage(USAGE);
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

    private boolean timerIsNotValid(){
        return Challenges.get().timer == null;
    }

    private void handle(String label, CommandSender sender) {
        if (label.equalsIgnoreCase("start")) {
            if(timerIsNotValid()) {
                Challenges.get().timer = new Timer(true, 1, TimerMode.COUNTUP, DisplayMode.ACTIONBAR);
                return;
            }
            if(Challenges.get().timer.isRunning()) {
                sender.sendMessage(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Timer ist bereits aktiv!");
            }
        }
        else if (label.equalsIgnoreCase("resume")) {
            if(timerIsNotValid()) {
                sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Bitte starte zuerst einen Timer."));
                return;
            }
            if(Challenges.get().timer.isRunning()) {
                sender.sendMessage(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Es ist kein Timer pausiert!");
                return;
            }

            Challenges.get().timer.setRunning(true);
            Challenges.get().timer.setPaused(false);
            disableAIOfEntitys(false);
        }
        else if (label.equalsIgnoreCase("reset")) {
            if(timerIsNotValid()) {
                sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Bitte starte zuerst einen Timer."));
                return;
            }
            Challenges.get().timer.setTime(1);
            Challenges.get().timer.setRunning(false);
            Challenges.get().timer.setPaused(false);
            sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Timer wurde resetet!"));
            disableAIOfEntitys(false);
        }
        else if (label.equalsIgnoreCase("pause")) {
            if(timerIsNotValid()) {
                sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Bitte starte zuerst einen Timer."));
                return;
            }
            if(!Challenges.get().timer.isRunning()) {
                sender.sendMessage(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Es ist kein Timer aktiv!");
                return;
            }

            Challenges.get().timer.setRunning(false);
            Challenges.get().timer.setPaused(true);
            disableAIOfEntitys(true);
        }
        else if (label.equalsIgnoreCase("toggle")) {
            if(timerIsNotValid()) {
                Challenges.get().timer = new Timer(true, 1, TimerMode.COUNTUP, DisplayMode.ACTIONBAR);
                return;
            }
            if(Challenges.get().timer.isRunning()) {
                Challenges.get().timer.setRunning(false);
                Challenges.get().timer.setPaused(true);
                disableAIOfEntitys(true);
                sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Timer wurde pausiert!"));
                return;
            }
            if(!Challenges.get().timer.isRunning()) {
                Challenges.get().timer.setRunning(true);
                Challenges.get().timer.setPaused(false);
                disableAIOfEntitys(false);
                sender.sendMessage(HexColor.translate(Challenges.PREFIX + Challenges.SECONDARY_COLOR + "Timer wurde fortgesetzt!"));
            }
        }
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("timer")){
            return new ArrayList<>(){{
                add("start");
                add("resume");
                add("reset");
                add("pause");
                add("toggle");
            }};
        }
        return null;
    }

    public void updateAIOfEntity(Entity entity, boolean disable) {
        if(entity instanceof LivingEntity) {
            if(disable) {
                if(!disabledEntities.contains(entity)) {
                    disabledEntities.add(entity);
                    ((LivingEntity) entity).setAI(false);
                }
            }
            else {
                if(disabledEntities.contains(entity)) {
                    disabledEntities.remove(entity);
                    ((LivingEntity) entity).setAI(true);
                }
            }
        }
    }

    public void disableAIOfEntitys(boolean disable) {
        /*Bukkit.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if(entity instanceof LivingEntity) {
                    updateAIOfEntity(entity, disable);
                }
            });
        });*/
    }
}
