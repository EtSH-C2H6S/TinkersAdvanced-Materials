package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class OceanicWhirl extends EtSTBaseModifier implements GeneralInteractionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player, InteractionHand hand, InteractionSource source) {
        if (tool.getCurrentDurability()>= TiAcMeConfig.COMMON.OCEANIC_WHIRL_CONSUMPTION.get()){
            GeneralInteractionModifierHook.startUsing(tool,modifier.getId(),player,hand);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void onFinishUsing(IToolStackView tool, ModifierEntry modifier, LivingEntity entity) {
        if (entity instanceof Player player){
            ToolDamageUtil.damageAnimated(tool,TiAcMeConfig.COMMON.OCEANIC_WHIRL_CONSUMPTION.get(),entity);
            player.getCooldowns().addCooldown(tool.getItem(),
                    TiAcMeConfig.COMMON.OCEANIC_WHIRL_CD.get());
            player.setDeltaMovement(player.getDeltaMovement().add(
                    player.getLookAngle().normalize().scale(3 * ((modifier.getLevel()*TiAcMeConfig.COMMON.OCEANIC_WHIRL_DRILL_STRENGTH.get())+1)/4f)));
            player.startAutoSpinAttack(30);
            if (player.onGround()) {
                player.move(MoverType.SELF, new Vec3(0.0D,1.2, 0.0D));
            }
            player.level().playSound(null, player,
                    modifier.getLevel()>1? SoundEvents.TRIDENT_RIPTIDE_3:SoundEvents.TRIDENT_RIPTIDE_2,
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public int getUseDuration(IToolStackView tool, ModifierEntry modifier) {
        return (int) (20/tool.getStats().get(ToolStats.ATTACK_SPEED));
    }

    @Override
    public UseAnim getUseAction(IToolStackView tool, ModifierEntry modifier) {
        return UseAnim.SPEAR;
    }
}
