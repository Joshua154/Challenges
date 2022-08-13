package com.laudynetwork.challenges.util;

import org.bukkit.Material;

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
                        (mat.isSolid() && matType.equals("solid"))) {
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
}
