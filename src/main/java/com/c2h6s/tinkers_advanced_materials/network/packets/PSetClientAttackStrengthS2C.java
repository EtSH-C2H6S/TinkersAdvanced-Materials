package com.c2h6s.tinkers_advanced_materials.network.packets;

import lombok.RequiredArgsConstructor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import slimeknights.mantle.client.SafeClientAccess;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class PSetClientAttackStrengthS2C {
    private final int ticks;

    public PSetClientAttackStrengthS2C(FriendlyByteBuf byteBuf){
        this.ticks = byteBuf.readInt();
    }

    public void toByte(FriendlyByteBuf byteBuf){
        byteBuf.writeInt(this.ticks);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        var player = SafeClientAccess.getPlayer();
        if (player!=null){
            supplier.get().enqueueWork(()->player.attackStrengthTicker = ticks);
        }
        return true;
    }
}
