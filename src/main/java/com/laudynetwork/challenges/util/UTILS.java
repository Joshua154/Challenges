package com.laudynetwork.challenges.util;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.*;

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
}
