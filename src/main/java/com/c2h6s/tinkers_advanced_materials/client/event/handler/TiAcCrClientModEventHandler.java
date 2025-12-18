package com.c2h6s.tinkers_advanced_materials.client.event.handler;

import com.c2h6s.tinkers_advanced_materials.client.renderer.entity.AirSlashRenderer;
import com.c2h6s.tinkers_advanced_materials.client.renderer.entity.AnnihilateExplosionRenderer;
import com.c2h6s.tinkers_advanced_materials.client.renderer.entity.RenderThermalSlash;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeEntities;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TiAcCrClientModEventHandler {
    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(TiAcMeEntities.AIR_SLASH.get(), AirSlashRenderer::new);
        event.registerEntityRenderer(TiAcMeEntities.ANNIHILATE_EXPLOSION.get(), AnnihilateExplosionRenderer::new);
        event.registerEntityRenderer(TiAcMeEntities.EFFECT_STAR.get(), ItemEntityRenderer::new);
        if (ModList.get().isLoaded("thermal")){
            event.registerEntityRenderer(TiAcMeEntities.THERMAL_SLASH.get(), RenderThermalSlash::new);
        }
    }
}
