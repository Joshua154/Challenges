package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class XP_Death extends GameMod {
    public XP_Death() {
        super("XP = Death", "xpd", Material.EXPERIENCE_BOTTLE, ModManager.ModType.PLAYER, ModManager.ModStatus.OPEN, "You die if you collect XP-Orbs");
    }

    @EventHandler
    public void onXPCollect(PlayerExpChangeEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        failed(player, "{{player}} collected XP");
    }
}

