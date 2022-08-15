package com.laudynetwork.challenges.Listener;

import com.laudynetwork.challenges.Challenges;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!Challenges.get().timer.isRunning()) Challenges.get().timer.setPaused(true);

        Challenges.get().addPlayingPlayer(event.getPlayer());
    }
}
