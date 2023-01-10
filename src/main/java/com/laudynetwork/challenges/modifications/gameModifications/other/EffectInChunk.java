package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.Cords;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class EffectInChunk extends GameMod {
    private final Map<Player, PotionEffect> currentType = new HashMap<>();
    private final Map<Cords, PotionEffect> potionEffectTypeMap = new HashMap<>();

    public EffectInChunk() {
        super("Effect in Chunk", "eic", Material.LINGERING_POTION, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "Different Effect in each Chunk");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;
        if (!isDifferentChunk(event.getFrom(), event.getTo())) return;

        enterDifferentChunk(event.getTo().getChunk(), player);
    }

    private boolean isDifferentChunk(Location from, Location to) {
        return from.getChunk().getX() != to.getChunk().getX() ||
                from.getChunk().getZ() != to.getChunk().getZ() ||
                from.getWorld() != to.getWorld();
    }

    private void enterDifferentChunk(Chunk chunk, Player player) {
        Cords cords = new Cords(chunk.getX(), chunk.getZ(), chunk.getWorld());
        if (isNewChunk(cords)) enterNewChunk(cords);

        if (currentType.get(player) != null) {
            player.removePotionEffect(currentType.get(player).getType());
        }
        currentType.put(player, potionEffectTypeMap.get(cords));


        player.addPotionEffect(currentType.get(player));
    }

    private boolean isNewChunk(Cords cords) {
        return !potionEffectTypeMap.containsKey(cords);
    }

    private void enterNewChunk(Cords cords) {
        potionEffectTypeMap.put(cords, getRandomPotionEffectType());
    }

    private PotionEffect getRandomPotionEffectType() {
        Random random = new Random();

        List<PotionEffectType> list = Arrays.stream(PotionEffectType.values()).toList();
        int maxAmplifier = 3;
        PotionEffectType effectType = list.get(random.nextInt(list.size()));

        if (effectType.equals(PotionEffectType.HARM)) maxAmplifier = 1;

        return new PotionEffect(effectType, Integer.MAX_VALUE, random.nextInt(maxAmplifier));
    }
}

