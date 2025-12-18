package com.c2h6s.tinkers_advanced_materials.network.packets;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import lombok.RequiredArgsConstructor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import slimeknights.mantle.client.SafeClientAccess;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class PInitializeModifierConfigS2C {
    public PInitializeModifierConfigS2C(FriendlyByteBuf byteBuf){}
    public void toByte(FriendlyByteBuf byteBuf){}
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        var player = SafeClientAccess.getPlayer();
        if (player!=null) TinkersAdvanced.LOGGER.info("Initialize Modifier Config for client player: {}",player.getName());
        MultiArgsDescModifier.initializeConfig();
        return true;
    }
}
