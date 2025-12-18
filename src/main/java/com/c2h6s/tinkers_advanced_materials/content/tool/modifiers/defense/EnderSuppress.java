package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.defense;

import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.helper.ToolAttackUtil;

@Mod.EventBusSubscriber
public class EnderSuppress extends EtSTBaseModifier {
    public static int triggers = 0;
    public static final TinkerDataCapability.TinkerDataKey<Integer> KEY_ENDER_SUPPRESS = TinkerDataCapability.TinkerDataKey.of(TinkersAdvanced.getLocation("ender_suppress"));

    @Override
    public boolean isNoLevels() {
        return true;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addModule(new ArmorLevelModule(KEY_ENDER_SUPPRESS,false, TinkerTags.Items.SHIELDS));
    }

    @SubscribeEvent
    public static void stopEnderTeleport(EntityTeleportEvent event){
        if (event.getEntity() instanceof Mob mob&&mob.getTarget()!=null) {
            var entity = mob.getTarget();
            entity.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(cap -> {
                var lvl = cap.get(KEY_ENDER_SUPPRESS, 0);
                if (lvl > 0){
                    triggers = 0;
                    event.setCanceled(true);
                    if (entity instanceof Player player &&entity.getMainHandItem().is(TinkerTags.Items.MELEE_PRIMARY)&&triggers<=0){
                        ToolAttackUtil.attackEntity(entity.getMainHandItem(),player,mob);
                        triggers++;
                    }
                }
            });
        }
    }
}
