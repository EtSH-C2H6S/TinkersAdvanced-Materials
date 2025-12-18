package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.defense;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.hookProviders.FrostChargeModule;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.hookProviders.FrostChargeModule.*;

public class FrostedShield extends MultiArgsDescModifier implements ModifyDamageModifierHook, DamageBlockModifierHook {
    public static float CFG_MAX_DAMAGE_REDUCTION_EACH_LEVEL = 0.25f;

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MODIFY_DAMAGE,ModifierHooks.DAMAGE_BLOCK);
        hookBuilder.addModule(new FrostChargeModule());
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.1f",CFG_MAX_DAMAGE_REDUCTION_EACH_LEVEL),
                String.format("%.1f",CFG_CHARGE_EACH_SECOND*100)+"%",
                String.format("%.1f",CFG_CHARGE_CONSUMPTION_EACH_HIT*100)+"%"
        };
    }

    @Override
    public void refreshConfig() {
        CFG_MAX_DAMAGE_REDUCTION_EACH_LEVEL = TiAcMeConfig.COMMON.FROSTED_SHIELD_DAMAGE_BLOCK.get().floatValue();
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        var shield = tool.getPersistentData().getFloat(KEY_FROSTED_SHIELD);
        if (FrostChargeModule.isChargeReady(tool)&&amount>0){
            amount-=shield*CFG_MAX_DAMAGE_REDUCTION_EACH_LEVEL*modifier.getLevel();
            FrostChargeModule.consumeFrostCharge(tool);
        }
        return amount;
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount) {
        return source.is(DamageTypeTags.IS_FREEZING);
    }
}
