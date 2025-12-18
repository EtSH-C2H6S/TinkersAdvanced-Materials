package com.c2h6s.tinkers_advanced_materials.content.event.handler;

import com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.tools.capability.TinkerDataKeys;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeModEventHandler {
    @SubscribeEvent
    public static void onRegister(RegisterEvent event){
        if (event.getRegistryKey()== Registries.RECIPE_SERIALIZER){
            TinkerDataKeys.INTEGER_REGISTRY.register(TiAcMeModifierProvider.DataKeys.KEY_RAINBOW_KIT);
        }
    }
}
