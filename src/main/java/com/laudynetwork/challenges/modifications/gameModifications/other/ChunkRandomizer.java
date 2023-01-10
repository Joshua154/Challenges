package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ChunkRandomizer extends GameMod {

    public ChunkRandomizer() {
        super("Chunk Randomizer", "cr", Material.GRASS_BLOCK, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "");

        super.setPermission("challenges.cr");

    }

    public List<Material> blocks = Arrays.asList(
            Material.ACACIA_LEAVES,
            Material.ACACIA_LOG,

            // I'll leave this out, just every full, solid block

            Material.YELLOW_STAINED_GLASS,
            Material.YELLOW_TERRACOTTA,
            Material.YELLOW_WOOL
    );

    public Material getRandomMaterial(List l) {
        int rnd = ThreadLocalRandom.current().nextInt(l.size());
        return (Material) l.get(rnd);
    }


    /*@EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        if (e.isNewChunk()) {
            Chunk chunk = e.getChunk();
            Block b;
            Material m = Material.MELON;
            for (int y = chunk.getWorld().getMinHeight(); y <= chunk.getWorld().getMaxHeight(); y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        b = chunk.getBlock(x, y, z);
                        if (!b.getType().isAir()
                                && b.getType() != Material.BEDROCK
                                && b.getType() != Material.WATER
                                && b.getType() != Material.LAVA
                                && b.getType() != Material.END_PORTAL_FRAME
                                && b.getType() != Material.END_PORTAL) {
                            b.setType(m);
                        }
                    }
                }
            }
        }
    }*/
}
