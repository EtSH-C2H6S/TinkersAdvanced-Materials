package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.defense;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EnderProtocol extends NoLevelsModifier {
    public static final TinkerDataCapability.TinkerDataKey<Integer> KEY_ENDER_PROTOCOL = TinkerDataCapability.TinkerDataKey.of(TinkersAdvanced.getLocation("ender_protocol"));

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event){
        if (event.getEntity() instanceof EnderMan enderMan&&event.getNewTarget()!=null&&enderMan.getLastAttacker()!=event.getNewTarget())
            event.getNewTarget().getCapability(TinkerDataCapability.CAPABILITY).ifPresent(cap->{
                if (cap.get(KEY_ENDER_PROTOCOL,0)>0) {
                    if (event.getOriginalTarget()==null)
                        event.setNewTarget(null);
                    event.setCanceled(true);
                }
            });
    }
}
