package com.laudynetwork.challenges.Listener;

import com.laudynetwork.challenges.Challenges;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class GameModeSwitchListener implements Listener {
    @EventHandler
    public void onGameModeSwitch(PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode() == GameMode.CREATIVE ||
                event.getNewGameMode() == GameMode.CREATIVE) {

            Challenges.get().addHiddenPlayer(event.getPlayer());
            Challenges.get().removePlayingPlayer(event.getPlayer());
            event.getPlayer().setScoreboard(Challenges.get().getHiddenPlayerScoreBord());
            Challenges.get().getHiddenPlayerTeam().addEntry(event.getPlayer().getName());
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, false));
        }
        else {
            Challenges.get().removeHiddenPlayer(event.getPlayer());
            Challenges.get().addPlayingPlayer(event.getPlayer());
            event.getPlayer().setScoreboard(Challenges.get().getHiddenPlayerScoreBord());
            Challenges.get().getHiddenPlayerTeam().removeEntry(event.getPlayer().getName());
            event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }
        updateVisibility();
    }

    public void updateVisibility(){
        List<Player> hiddenPlayer = Challenges.get().getHiddenPlayers();

        for(Player playerToCheckOn : Bukkit.getOnlinePlayers()) {
            for(Player playerToUpDate : Bukkit.getOnlinePlayers()) {
                if(hiddenPlayer.contains(playerToCheckOn) && hiddenPlayer.contains(playerToUpDate)) {
                    playerToUpDate.showPlayer(Challenges.get(), playerToCheckOn);
                }
                else if(hiddenPlayer.contains(playerToCheckOn)) {
                    playerToUpDate.hidePlayer(Challenges.get(), playerToCheckOn);
                }
                else {
                    playerToUpDate.showPlayer(Challenges.get(), playerToCheckOn);
                }
            }
        }
    }
}
