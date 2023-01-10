package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.Challenges;
import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.networkutils.api.item.itembuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AllMobsInvisible extends GameMod {

    public AllMobsInvisible() {
        super("Mobs Invisible", "im", new ItemBuilder(Material.ZOMBIE_HEAD).build(), ModManager.ModType.WORLD, ModManager.ModStatus.CLOSED_BETA, "");

        super.setPermission("challenges.im");
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        if (!enabled) return;
        if (!Challenges.get().timer.isRunning()) return;

        if (!(event.getEntity() instanceof LivingEntity entity)) return;

        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 255, true, false));
    }
}
