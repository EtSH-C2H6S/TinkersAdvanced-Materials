package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.defense;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Prismatic extends NoLevelsModifier {
    public static final TinkerDataCapability.TinkerDataKey<Integer> KEY_PRISMATIC = TinkerDataCapability.TinkerDataKey.of(TinkersAdvanced.getLocation("prismatic"));
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addModule(new ArmorLevelModule(KEY_PRISMATIC,false, TinkerTags.Items.SHIELDS));
    }
    @SubscribeEvent
    public static void onChangeTarget(LivingChangeTargetEvent event){
        if (event.getNewTarget()!=null&&event.getEntity()!=null&&(event.getNewTarget().isInWaterRainOrBubble()||event.getEntity().isInWaterRainOrBubble()))
            event.getNewTarget().getCapability(TinkerDataCapability.CAPABILITY).ifPresent(cap->{
                int lvl = cap.get(KEY_PRISMATIC,0);
                if (lvl>0&&event.getEntity().getLastAttacker()!=event.getNewTarget()){
                    if (event.getOriginalTarget()==null)
                        event.setNewTarget(null);
                    event.setCanceled(true);
                }
            });
    }
}
