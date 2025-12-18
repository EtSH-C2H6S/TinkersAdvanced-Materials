package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.hookProviders.FrostChargeModule;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import static com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.hookProviders.FrostChargeModule.*;

public class FrostedAttack extends MultiArgsDescModifier  {
    public static float CFG_EXTRA_DAMAGE = 0.2f;

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addModule(new FrostChargeModule());
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.1f",CFG_EXTRA_DAMAGE*100)+"%",
                String.format("%.1f",CFG_CHARGE_EACH_SECOND*100)+"%",
                String.format("%.1f",CFG_CHARGE_CONSUMPTION_EACH_HIT*100)+"%"
        };
    }

    @Override
    public void refreshConfig() {
        CFG_EXTRA_DAMAGE = TiAcMeConfig.COMMON.FROSTED_ATTACK_EXTRA_DAMAGE.get().floatValue();
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, boolean primary) {
        var shield = tool.getPersistentData().getFloat(KEY_FROSTED_SHIELD);
        if (FrostChargeModule.isChargeReady(tool)){
            persistentData.putFloat(KEY_FROSTED_SHIELD,shield);
            FrostChargeModule.consumeFrostCharge(tool);
        }
    }

    @Override
    public void postMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt, float damage) {
        var shield = tool.getPersistentData().getFloat(KEY_FROSTED_SHIELD);
        if (FrostChargeModule.isChargeReady(tool)&&damage>0){
            var iT = context.getTarget().invulnerableTime;
            context.getTarget().hurt(
                    LegacyDamageSource.any(context.getTarget().damageSources().freeze().typeHolder(),context.getAttacker())
                            .setBypassInvulnerableTime(),damage*CFG_EXTRA_DAMAGE*modifier.getLevel()*shield
            );
            context.getTarget().invulnerableTime = iT;
            FrostChargeModule.consumeFrostCharge(tool);
        }
    }

    @Override
    public void afterArrowHit(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull LivingEntity target, float damageDealt) {
        var shield = persistentData.getFloat(KEY_FROSTED_SHIELD);
        if (damageDealt>0){
            target.hurt(
                    LegacyDamageSource.any(target.damageSources().freeze().typeHolder(),attacker)
                            .setBypassInvulnerableTime(),damageDealt*CFG_EXTRA_DAMAGE*entry.getLevel()*shield
            );
        }
    }
}
