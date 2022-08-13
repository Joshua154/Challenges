package com.laudynetwork.challenges.Listener;

import com.laudynetwork.challenges.Challenges;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Challenges.get().addPlayingPlayer(event.getPlayer());
    }
}
