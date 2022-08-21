package com.laudynetwork.challenges.modifications.config;

import com.laudynetwork.challenges.util.IntObjekt;
import com.laudynetwork.laudynetworkapi.builder.ItemBuilder;
import com.laudynetwork.laudynetworkapi.chatutils.HexColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntry {
    public String name;
    private List<String> description;
    public ConfigEntryType type;
    private Material activeMaterial = Material.LIME_STAINED_GLASS_PANE;
    private final Material inactiveMaterial = Material.RED_STAINED_GLASS_PANE;
    private final IntObjekt settingsVariables;


    private int amplifier = 5;
    private int step = 1;


    public ConfigEntry(String name, List<String> description, IntObjekt settingsVariables, ConfigEntryType type) {
        this.name = name;
        this.description = description;
        this.settingsVariables = settingsVariables;
        this.type = type;
    }


    public ConfigEntry(String name, List<String> description, IntObjekt settingsVariables, ConfigEntryType type, Material material) {
        this.name = name;
        this.description = description;
        this.settingsVariables = settingsVariables;
        this.type = type;
        this.activeMaterial = material;
    }

    public ItemStack getItem() {
        List<String> tempLore = new ArrayList<>();
        if (description != null) {
            for (String s : description) {
                tempLore.add(HexColor.translate(s.replace("{{variable}}", settingsVariables.value + "")));
            }
        }

        if (type == ConfigEntryType.TOGGLE) {
            tempLore.add(HexColor.translate("#FFFFFFClick to toggle"));
            return new ItemBuilder(settingsVariables.value == 0 ? inactiveMaterial : activeMaterial).setDisplayName(HexColor.translate(name)).setLore(tempLore).build();
        } else {
            tempLore.add(HexColor.translate("#00FF00L-Click #FFFFFFto increase by #00FF00" + step));
            tempLore.add(HexColor.translate("#00FF00R-Click #FFFFFFto decrease by #00FF00" + step));
            tempLore.add(HexColor.translate("#00FF00SHIFT #FFFFFFto amplify by #00FF00" + amplifier));
            return new ItemBuilder(activeMaterial).setDisplayName(HexColor.translate(name)).setLore(tempLore).build();
        }
    }

    public void onClick(ClickType clickType) {
        if (type == ConfigEntryType.TOGGLE) {
            settingsVariables.value = settingsVariables.value == 0 ? 1 : 0;
        } else {
            if (clickType == ClickType.LEFT) {
                if (settingsVariables.value < settingsVariables.max) {
                    settingsVariables.value += step;
                }
            } else if (clickType == ClickType.RIGHT) {
                if (settingsVariables.value > settingsVariables.min) {
                    settingsVariables.value -= step;
                }
            } else if (clickType == ClickType.SHIFT_LEFT) {
                if (settingsVariables.value + step * amplifier < settingsVariables.max) {
                    settingsVariables.value += step * amplifier;
                } else {
                    settingsVariables.value = settingsVariables.max;
                }
            } else if (clickType == ClickType.SHIFT_RIGHT) {
                if (settingsVariables.value - step * amplifier > settingsVariables.min) {
                    settingsVariables.value -= step * amplifier;
                } else {
                    settingsVariables.value = settingsVariables.min;
                }
            }
        }
    }


    public enum ConfigEntryType {
        TOGGLE,
        SLIDER
    }
}
