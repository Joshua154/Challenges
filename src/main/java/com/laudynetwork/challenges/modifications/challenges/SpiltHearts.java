package com.laudynetwork.challenges.modifications.challenges;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class SpiltHearts extends GameMod {
    private double hearts;
    private double saturation;

    public SpiltHearts() {
        super("Split Hearts", "sh", Material.POPPY, ModManager.ModType.GOALS, ModManager.ModStatus.WORK_IN_PROGRESS, "");
    }

    @Override
    public void init() {
        hearts = Challenges.get().getPlayingPlayers().get(0).getHealth();
        saturation = Challenges.get().getPlayingPlayers().get(0).getSaturation();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        hearts = Math.max(0, hearts - event.getFinalDamage());
        updateAllPlayers();
    }

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (!(event.getEntity() instanceof Player player)) return;
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        hearts += event.getAmount() / Challenges.get().getPlayingPlayers().size();
        updateAllPlayers();
    }

    private void updateAllPlayers() {
        for (Player player : Challenges.get().getPlayingPlayers()) {
            updatePlayer(player);
        }
    }

    private void updatePlayer(Player player) {
        player.setHealth(Math.min(Math.round(hearts), player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()));
    }
}
