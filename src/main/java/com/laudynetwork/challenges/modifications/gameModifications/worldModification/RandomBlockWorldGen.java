package com.laudynetwork.challenges.modifications.gameModifications.worldModification;

import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import com.laudynetwork.challenges.util.UTILS;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomBlockWorldGen extends GameMod {
    World world;

    public RandomBlockWorldGen() {
        super("Random Block World Gen", "rbwg", Material.GRASS_BLOCK, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "");

        super.setPermission("challenges.rbwg");
    }

    @Override
    public void init(){
        if(Bukkit.getWorld("RandomBlockWorld") == null){
            WorldCreator worldCreator = new WorldCreator("RandomBlockWorld");
            worldCreator.generator(new RandomChunkGenerator());
            world = Bukkit.createWorld(worldCreator);

            /*new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getOnlinePlayers().forEach(player -> player.teleport(world.getSpawnLocation()));
                }
            }.runTaskLater(Challenges.get(), 20*5);*/
        }
    }

    static class RandomChunkGenerator extends ChunkGenerator {
        Map<Material, Material> partener = UTILS.shuffle(List.of(Material.values()), "block");

        @Override
        public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
            /*for (int x = 0; x < 16; x++) {
                for (int y = chunkData.getMinHeight() + 1; y < chunkData.getMaxHeight(); y++) {
                    for (int z = 0; z < 16; z++) {
                        if(chunkData.getType(x, y, z) == Material.GRASS_BLOCK)
                            chunkData.setBlock(x, y, z, partener.get(chunkData.getType(x, y, z)));
                    }
                }
            }*/

            int a = chunkX * chunkX;
            int b = chunkZ * chunkZ;
            double dis = Math.sqrt(.0 + a + b);
            double halt = Math.abs(Math.sin(dis / 10) * 50);
            chunkData.setRegion(0, (int) halt, 0, worldInfo.getMaxHeight(), worldInfo.getMaxHeight(), 16, Material.AIR);
        }

        @Override
        public int getBaseHeight(@NotNull WorldInfo worldInfo, @NotNull Random random, int x, int z, @NotNull HeightMap heightMap){
            int a = x / 16 * x / 16;
            int b = z / 16 * z / 16;
            return (int) Math.abs(Math.sqrt(Math.sin(.0 + a + b) / 10) * 50);
        }

        @Override public boolean shouldGenerateNoise(){ return true; }
        @Override public boolean shouldGenerateSurface(){ return true; }
        @Override public boolean shouldGenerateCaves(){ return true; }
        @Override public boolean shouldGenerateDecorations(){ return true; }
        @Override public boolean shouldGenerateMobs(){ return true; }
        @Override public boolean shouldGenerateStructures(){ return true; }
    }
}

