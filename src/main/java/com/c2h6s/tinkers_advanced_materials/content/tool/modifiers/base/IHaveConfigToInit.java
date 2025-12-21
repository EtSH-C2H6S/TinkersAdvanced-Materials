package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.List;

public interface IHaveConfigToInit {
    Object[] getDescArgs();
    void refreshConfig();

    static List<Component> createDescriptions(String transKey,IHaveConfigToInit modifier){
        return Arrays.asList(
                Component.translatable(transKey + ".flavor").withStyle(ChatFormatting.ITALIC),
                Component.translatable(transKey + ".description",
                        modifier.getDescArgs()).withStyle(ChatFormatting.GRAY));
    }
}
