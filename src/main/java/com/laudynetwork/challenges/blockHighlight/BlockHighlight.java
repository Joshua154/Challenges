package com.laudynetwork.challenges.blockHighlight;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.util.Vector;

import java.awt.*;

@AllArgsConstructor
public class BlockHighlight {
    @Getter
    private int x;
    @Getter
    private int y;
    @Getter
    private int z;
    @Getter
    private int color;
    @Getter
    private String text;
    @Getter
    private int time;

    public BlockHighlight(Vector cords, Color color, String text, int time) {
        this.x = (int) cords.getX();
        this.y = (int) cords.getY();
        this.z = (int) cords.getZ();
        this.color = color.getRGB();
        this.text = text;
        this.time = time;
    }

    public static BlockHighlight getHideBehindBlocks(int time) {
        return new BlockHighlight(0, 0, 0, new Color(0, 0, 0, 0).getRGB(), " ", time);
    }
}