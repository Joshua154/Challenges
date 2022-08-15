package com.laudynetwork.challenges.Listener;

import com.laudynetwork.challenges.Challenges;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class FunctionsOnTimerPause implements Listener {
    private final List<LivingEntity> disabledEntities = new ArrayList<>();

    private boolean DO_DAYLIGHT_CYCLE = true;
    private boolean DO_WEATHER_CYCLE = true;
    private boolean DO_MOB_SPAWNING = true;
    private boolean DO_FIRE_TICK = true;
    private int RANDOM_TICK_SPEED = 3;


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE ||
                event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;

        if (event.getPlayer().getLocation().subtract(0, 0.1, 0).getBlock().getType() != Material.AIR) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (Challenges.get().timer == null) return;
        if (Challenges.get().timer.isUnPaused()) return;

        event.setCancelled(true);
    }


    public void onTimerPause() {
        toggleAIOOfEntitys(true);
        updateGameRules(false);
    }

    public void onTimerUnPause() {
        toggleAIOOfEntitys(false);
        updateGameRules(true);
    }

    private void updateAIOfEntity(Entity entity, boolean disable) {
        if (entity instanceof LivingEntity) {
            if (disable) {
                if (!disabledEntities.contains(entity)) {
                    disabledEntities.add((LivingEntity) entity);
                    ((LivingEntity) entity).setAI(false);
                }
            } else {
                if (disabledEntities.contains(entity)) {
                    disabledEntities.remove(entity);
                    ((LivingEntity) entity).setAI(true);
                }
            }
        }
    }

    private void toggleAIOOfEntitys(boolean disable) {
        Bukkit.getWorlds().forEach(world ->
                world.getEntities().forEach(entity -> {
                    if (entity instanceof LivingEntity) {
                        updateAIOfEntity(entity, disable);
                    }
                })
        );
    }

    private void updateGameRules(boolean disable) {
        for (World world : Bukkit.getWorlds()) {
            if (!disable) {
                DO_DAYLIGHT_CYCLE = Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE));
                DO_WEATHER_CYCLE = Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_WEATHER_CYCLE));
                DO_MOB_SPAWNING = Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_MOB_SPAWNING));
                DO_FIRE_TICK = Boolean.TRUE.equals(world.getGameRuleValue(GameRule.DO_FIRE_TICK));
                RANDOM_TICK_SPEED = Math.max(3, world.getGameRuleValue(GameRule.RANDOM_TICK_SPEED));
            }


            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, disable && DO_DAYLIGHT_CYCLE);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, disable && DO_WEATHER_CYCLE);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, disable && DO_MOB_SPAWNING);
            world.setGameRule(GameRule.DO_FIRE_TICK, disable && DO_FIRE_TICK);
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, disable ? 0 : RANDOM_TICK_SPEED);
        }
    }
}
