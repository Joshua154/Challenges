package com.laudynetwork.challenges.modifications.gameModifications.other;

import com.laudynetwork.challenges.modifications.GameMod;
import com.laudynetwork.challenges.modifications.ModManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class OneLine extends GameMod {
    World empty;
    World empty_nether;

    public OneLine() {
        super("One Line", "ol", Material.GRASS_BLOCK, ModManager.ModType.WORLD, ModManager.ModStatus.WORK_IN_PROGRESS, "Generates a World of one Line");
    }

    @Override
    public void init() {
        generateWorld();

        Bukkit.getOnlinePlayers().forEach((player -> player.teleport(new Location(empty, 0, empty.getHighestBlockYAt(0, 0) + 1, 0))));
    }

    private void generateWorld() {
        WorldCreator worldCreator = new WorldCreator("empty");
        worldCreator.generator(new EmptyChunkGenerator());

        this.empty = worldCreator.createWorld();


        WorldCreator nether_worldCreator = new WorldCreator("empty_nether");
        nether_worldCreator.environment(World.Environment.NETHER);

        nether_worldCreator.generator(new EmptyChunkGenerator());

        nether_worldCreator.generateStructures(true);

        this.empty_nether = nether_worldCreator.createWorld();
    }

    private static class EmptyChunkGenerator extends ChunkGenerator {
        @Override
        public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
            ChunkData chunk = createChunkData(world);

            for (int X = 0; X < 16; X++)
                for (int Z = 0; Z < 16; Z++) {
                    chunk.setBlock(X, 0, Z, Material.BEDROCK);
                }

            return chunk;
        }
    }
}
