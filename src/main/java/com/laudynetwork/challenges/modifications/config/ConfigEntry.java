package com.laudynetwork.challenges.modifications.config;

import com.laudynetwork.challenges.api.chatutils.HexColor;
import com.laudynetwork.challenges.util.DoubleObject;
import com.laudynetwork.networkutils.api.item.itembuilder.ItemBuilder;
import net.kyori.adventure.text.Component;
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
    private final DoubleObject settingsVariables;


    private int amplifier = 5;


    public ConfigEntry(String name, List<String> description, DoubleObject settingsVariables, ConfigEntryType type) {
        this.name = name;
        this.description = description;
        this.settingsVariables = settingsVariables;
        this.type = type;
    }


    public ConfigEntry(String name, List<String> description, DoubleObject settingsVariables, ConfigEntryType type, Material material) {
        this.name = name;
        this.description = description;
        this.settingsVariables = settingsVariables;
        this.type = type;
        this.activeMaterial = material;
    }

    public ItemStack getItem() {
        ArrayList<Component> tempLore = new ArrayList<>();
        if (description != null) {
            for (String s : description) {
                tempLore.add(Component.text(HexColor.translate(s.replace("{{variable}}", settingsVariables.value + ""))));
            }
        }

        if (type == ConfigEntryType.TOGGLE) {
            tempLore.add(Component.text(HexColor.translate("#FFFFFFClick to toggle")));
            return new ItemBuilder(settingsVariables.value == 0 ? inactiveMaterial : activeMaterial).displayName(Component.text(HexColor.translate(name))).lore(tempLore).build();
        } else {
            tempLore.add(Component.text(HexColor.translate("#00FF00L-Click #FFFFFFto increase by #00FF00" + settingsVariables.stepSize)));
            tempLore.add(Component.text(HexColor.translate("#00FF00R-Click #FFFFFFto decrease by #00FF00" + settingsVariables.stepSize)));
            tempLore.add(Component.text(HexColor.translate("#00FF00SHIFT #FFFFFFto amplify by #00FF00" + amplifier)));
            return new ItemBuilder(activeMaterial).displayName(Component.text(HexColor.translate(name))).lore(tempLore).build();
        }
    }

    public void onClick(ClickType clickType) {
        if (type == ConfigEntryType.TOGGLE) {
            settingsVariables.value = settingsVariables.value == 0 ? 1 : 0;
        } else {
            if (clickType == ClickType.LEFT) {
                if (settingsVariables.value < settingsVariables.max) {
                    settingsVariables.value += settingsVariables.stepSize;
                }
            } else if (clickType == ClickType.RIGHT) {
                if (settingsVariables.value > settingsVariables.min) {
                    settingsVariables.value -= settingsVariables.stepSize;
                }
            } else if (clickType == ClickType.SHIFT_LEFT) {
                if (settingsVariables.value + settingsVariables.stepSize * amplifier < settingsVariables.max) {
                    settingsVariables.value += settingsVariables.stepSize * amplifier;
                } else {
                    settingsVariables.value = settingsVariables.max;
                }
            } else if (clickType == ClickType.SHIFT_RIGHT) {
                if (settingsVariables.value - settingsVariables.stepSize * amplifier > settingsVariables.min) {
                    settingsVariables.value -= settingsVariables.stepSize * amplifier;
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
