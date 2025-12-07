package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base;

import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MultiArgsDescModifier extends EtSTBaseModifier {
    public MultiArgsDescModifier(){
        super();
    }
    @Override
    public List<Component> getDescriptionList() {
        if (descriptionList == null) {
            descriptionList = Arrays.asList(
                    Component.translatable(getTranslationKey() + ".flavor").withStyle(ChatFormatting.ITALIC),
                    Component.translatable(getTranslationKey() + ".description",
                            getDescArgs()).withStyle(ChatFormatting.GRAY));
        }
        return descriptionList;
    }
    public abstract Object[] getDescArgs();
    public abstract void refreshConfig();
}
