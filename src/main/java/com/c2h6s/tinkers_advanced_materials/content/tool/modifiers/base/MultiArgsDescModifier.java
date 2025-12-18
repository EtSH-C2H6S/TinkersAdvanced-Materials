package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base;

import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.hookProviders.FrostChargeModule;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.library.modifiers.ModifierManager;

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
    public static void initializeConfig(){
        TinkersAdvanced.LOGGER.info("Initializing TiAcMe Modifier Configs.");
        ModifierManager.INSTANCE.getAllValues().filter(modifier -> modifier instanceof MultiArgsDescModifier)
                .forEach(modifier -> {
                    ((MultiArgsDescModifier) modifier).refreshConfig();
                    TinkersAdvanced.LOGGER.info("Initialized config for modifier {} .", modifier.getId());
                });
        FrostChargeModule.CFG_CHARGE_CONSUMPTION_EACH_HIT = TiAcMeConfig.COMMON.FROSTED_CHARGE_CONSUMPTION.get().floatValue();
        FrostChargeModule.CFG_CHARGE_EACH_SECOND = TiAcMeConfig.COMMON.FROSTED_CHARGE_EACH_SEC.get().floatValue();
    }
    public abstract Object[] getDescArgs();
    public abstract void refreshConfig();
}
