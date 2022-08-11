package com.laudynetwork.challenges.timer;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import com.laudynetwork.laudynetworkapi.chatutils.TextUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private boolean running;
    private int time;
    private TimerMode timerMode;
    private DisplayMode displayMode;

    private boolean paused;

    public Timer(boolean running, int time, TimerMode timerMode, DisplayMode displayMode) {
        this.running = running;
        this.time = time;
        this.timerMode = timerMode;
        this.displayMode = displayMode;

        run();
    }


    //Running Logic
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    //Time
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    //Timer Mode
    public TimerMode getTimerMode() {
        return timerMode;
    }

    public void setTimerMode(TimerMode timerMode) {
        this.timerMode = timerMode;
    }


    //Display Mode
    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }



    private void sendActionBar(){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(!isRunning()){
                //continue;
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, HexColor.asTextComponent("#FF4545" + ChatColor.BOLD + "Timer pausiert"));
                continue;
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Challenges.PRIMARY_COLOR + ChatColor.BOLD + TextUtils.formatTime(getTime())));
        }
    }

    private void run(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(displayMode == DisplayMode.ACTIONBAR){
                    sendActionBar();
                }else if (displayMode == DisplayMode.RETURN){
                    return;
                }else if(displayMode == DisplayMode.NOTHING){
                    return;
                }

                if(!isRunning()){
                    return;
                }
                if(time <= 0){
                    running = false;
                    return;
                }

                setTime(getTime() + timerMode.toInt());
            }
        }.runTaskTimer(Challenges.get(), 20, 20);
    }

    public void setPaused(boolean bool) {
        paused = bool;
        if (bool) {
            Challenges.get().getTimerPauseDisable().onTimerPause();
        }else {
            Challenges.get().getTimerPauseDisable().onTimerUnPause();
        }
    }

    public boolean isUnPaused() {
        return !paused;
    }
}

