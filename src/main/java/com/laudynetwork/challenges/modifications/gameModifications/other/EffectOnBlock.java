package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class EffectOnBlock extends GameMod {
    private final Map<Player, Material> currentType = new HashMap<>();
    private final Map<Material, PotionEffect> potionEffectTypeMap = new HashMap<>();

    public EffectOnBlock() {
        super("Effect on Block Type", "eom", Material.LINGERING_POTION, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "Different Effect on each Block Type");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;
        Player player = event.getPlayer();
        if (!Challenges.get().getPlayingPlayers().contains(player)) return;

        Location loc = event.getTo().clone();

        Material material = loc.getBlock().getType();

        while (material == Material.AIR) {
            material = loc.subtract(0, 1, 0).getBlock().getType();

            if (Objects.requireNonNull(loc.getWorld()).getMinHeight() >= loc.getBlockY()) return;
        }

        if (isDifferentMaterial(material, currentType.get(player))) {
            enterDifferentMaterial(material, player);
        }
    }

    private void enterDifferentMaterial(Material material, Player player) {
        if (isNewMaterial(material)) enterNewMaterial(material);

        if (currentType.get(player) != null) {
            player.removePotionEffect(potionEffectTypeMap.get(currentType.get(player)).getType());
        }
        currentType.put(player, material);


        player.addPotionEffect(potionEffectTypeMap.get(currentType.get(player)));
    }

    private boolean isNewMaterial(Material material) {
        return !potionEffectTypeMap.containsKey(material);
    }

    private boolean isDifferentMaterial(Material material, Material fromMaterial) {
        return !material.equals(fromMaterial);
    }

    private void enterNewMaterial(Material material) {
        potionEffectTypeMap.put(material, getRandomPotionEffectType());
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

