package com.c2h6s.tinkers_advanced_materials.content.event.handler;

import com.c2h6s.tinkers_advanced_materials.network.TiAcMePacketHandler;
import com.c2h6s.tinkers_advanced_materials.network.packets.PInitializeModifierConfigS2C;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier.initializeConfig;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TiAcMeForgeEventHandler {
    @SubscribeEvent
    public static void updateConfig(ServerStartedEvent event) {
        initializeConfig();
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void updateConfig(OnDatapackSyncEvent event){
        if (event.getPlayer()!=null) TiAcMePacketHandler.sendToPlayer(new PInitializeModifierConfigS2C(),event.getPlayer());
        else TiAcMePacketHandler.sendToClient(new PInitializeModifierConfigS2C());
    }


}
