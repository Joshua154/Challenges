package com.laudynetwork.challenges.util;

import net.minecraft.server.v1_16_R3.Block;
import net.minecraft.server.v1_16_R3.MaterialMapColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.md_5.bungee.api.ChatColor;

public class UTILS {
    public static Map<Material, Material> shuffle(List<Material> materials, String matType) {
        List<Material> remaining = new ArrayList<>();
        Map<Material, Material> partners = new HashMap<>();


        for (Material mat : materials) {
            if (mat.isItem()) {
                remaining.add(mat);
            }
        }

        for (Material mat : materials) {
            if (!remaining.isEmpty()) {
                if ((mat.isBlock() && matType.equals("block")) ||
                        (mat.isItem() && matType.equals("item")) ||
                        (mat.isSolid() && matType.equals("solid")) ||
                        matType.equals("all")) {
                    Random r = new Random();
                    int rand;
                    if (remaining.size() != 1) {
                        rand = r.nextInt(remaining.size() - 1);
                    } else {
                        rand = 0;
                    }
                    partners.put(mat, remaining.get(rand));
                    remaining.remove(rand);
                }
            }
        }
        return partners;
    }

    public static Map<EntityType, EntityType> shuffleEntities() {
        Map<EntityType, EntityType> partners = new HashMap<>();
        List<EntityType> remaining = new ArrayList<>();

        for (EntityType type : EntityType.values()) {
            if (!type.isAlive()) continue;
            if (type == EntityType.PLAYER) continue;
            if (type == EntityType.UNKNOWN) continue;

            remaining.add(type);
        }

        List<EntityType> temp = new ArrayList<>(remaining);

        Collections.shuffle(temp);


        for (EntityType type : remaining) {
            partners.put(type, temp.get(0));
            temp.remove(0);
        }
        return partners;
    }

    /*private boolean isEntityLoot(Material mat) {
        for(TileEntityLootable)
    }

    private List<Mob> getMobs() {
        for(EntityType type : EntityType.values()) {
            LootTable lootTable = (LootTable) type.getEntityClass();
        }
    }*/


    public static List<Material> getTypes(List<Material> materials, String matType) {
        List<Material> result = new ArrayList<>();

        for (Material mat : materials) {
            if ((mat.isBlock() && matType.equals("block")) ||
                    (mat.isItem() && matType.equals("item")) ||
                    (mat.isSolid() && matType.equals("solid")) ||
                    matType.equals("all")) {
                result.add(mat);
            }
        }
        return result;
    }

    public static Color getColorOfBlock(Material mat) {
        int id = mat.getId();

        MaterialMapColor colorMap = Block.getByCombinedId(id).getBlock().s();
        return Color.fromBGR(colorMap.rgb);
    }


    public static String getHexColor(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }
}
