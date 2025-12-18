package com.c2h6s.tinkers_advanced_materials.network;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.network.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class TiAcMePacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE ;
    static int id = 0;

    public static void init() {
        INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(TinkersAdvanced.MODID, "tiacme_message")).networkProtocolVersion(() -> "1").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE.messageBuilder(PCofhModSwitchC2S.class, id++, NetworkDirection.PLAY_TO_SERVER).decoder(PCofhModSwitchC2S::new).encoder(PCofhModSwitchC2S::toByte).consumerMainThread(PCofhModSwitchC2S::handle).add();
        INSTANCE.messageBuilder(PSetClientAttackStrengthS2C.class, id++, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PSetClientAttackStrengthS2C::new)
                .encoder(PSetClientAttackStrengthS2C::toByte)
                .consumerMainThread(PSetClientAttackStrengthS2C::handle).add();
        INSTANCE.messageBuilder(PParticleRingS2C.class, id++, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PParticleRingS2C::new)
                .encoder(PParticleRingS2C::toByte)
                .consumerMainThread(PParticleRingS2C::handle).add();
        INSTANCE.messageBuilder(PInitializeModifierConfigS2C.class, id++, NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PInitializeModifierConfigS2C::new)
                .encoder(PInitializeModifierConfigS2C::toByte)
                .consumerMainThread(PInitializeModifierConfigS2C::handle).add();
    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToPlayer(MSG msg, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player),msg);
    }

    public static <MSG> void sendToClient(MSG msg){
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}
