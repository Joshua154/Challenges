package com.laudynetwork.challenges.util;

import org.bukkit.Chunk;
import org.bukkit.World;

public record Cords(int x, int z, World world) {
    public Chunk getChunk() {
        return world().getChunkAt(x, z);
    }
}