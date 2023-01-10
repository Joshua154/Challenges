package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NoMiss extends GameMod {
    Map<Player, Long> interaction = new HashMap<>();

    public NoMiss() {
        super("1 Miss = death", "nm", Material.DEAD_BUSH, ModManager.ModType.PLAYER, ModManager.ModStatus.WORK_IN_PROGRESS, "");
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (event.getHitBlock() == null) return;

        Projectile projectile = event.getEntity();
        if (!(projectile.getShooter() instanceof Player player)) return;
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;
        if (projectile instanceof EnderPearl) return;

        failed(player, "{{player}} shot an Projectile and hit nothing");
    }

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        if (!(event.getDamager() instanceof Player player)) return;
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        interaction.put(player, System.currentTimeMillis() + 40L);
    }


    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onBlockDamage(BlockDamageEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        Block block = event.getBlock();

        if (!block.getType().equals(Material.AIR)
                && !block.getType().equals(Material.CAVE_AIR)
                && !block.getType().equals(Material.VOID_AIR)) {

            interaction.put(player, System.currentTimeMillis() + 5L);
        }
    }


    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onBlockBreak(BlockBreakEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        Block block = event.getBlock();

        if (!block.getType().equals(Material.AIR)
                && !block.getType().equals(Material.CAVE_AIR)
                && !block.getType().equals(Material.VOID_AIR)) {

            interaction.put(player, System.currentTimeMillis() + 5L);
        }
    }


    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onInteract(PlayerInteractEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        //TODO: Filter Item Drop
        //TODO: Make it work


        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

            if (interaction.containsKey(player)) {
                if (interaction.get(player) > System.currentTimeMillis()) {
                    return;
                }
            }


            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!enabled) return;
                    if (interaction.containsKey(player)) {
                        if (interaction.get(player) > System.currentTimeMillis()) {
                            return;
                        }
                    }


                    failed(player, "{{player}} missed an Entity");
                }
            }.runTaskLater(Challenges.get(), 1L);
        }
    }
}
