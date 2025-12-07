package com.c2h6s.tinkers_advanced_materials.content.event.handler;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import slimeknights.tconstruct.library.modifiers.ModifierManager;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TiAcMeModEventHandler {
    @SubscribeEvent
    public static void updateConfig(ServerStartedEvent event) {
        TinkersAdvanced.LOGGER.info("Refreshing Modifier Configs.");
        ModifierManager.INSTANCE.getAllValues().filter(modifier -> modifier instanceof MultiArgsDescModifier)
                .forEach(modifier -> {
                    TinkersAdvanced.LOGGER.info("Refresh config for modifier {} .", modifier.getId());
                    ((MultiArgsDescModifier) modifier).refreshConfig();
                });
    }
}
