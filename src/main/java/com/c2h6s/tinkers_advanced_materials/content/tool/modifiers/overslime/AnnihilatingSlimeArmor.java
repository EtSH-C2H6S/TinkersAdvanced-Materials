package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.overslime;

import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import static com.c2h6s.tinkers_advanced_materials.TiAcMeConfig.COMMON;

public class AnnihilatingSlimeArmor extends MultiArgsDescModifier implements ModifyDamageModifierHook, ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MODIFY_DAMAGE,ModifierHooks.TOOL_STATS);
    }

    @Override
    public int getPriority() {
        return -1000;
    }

    protected static int CFG_MAX_OVERSLIME_CONSUMPTION = 2000;
    protected static float CONFIG_OVERSLIME_MULTIPLIER = 10;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                CONFIG_OVERSLIME_MULTIPLIER,
                CFG_MAX_OVERSLIME_CONSUMPTION
        };
    }

    @Override
    public void refreshConfig() {
        CFG_MAX_OVERSLIME_CONSUMPTION = COMMON.ANTI_NEUTRONIUM_OVERSLIME_MAX_CONSUMPTION_ARMOR.get();
        CONFIG_OVERSLIME_MULTIPLIER = COMMON.ANTI_NEUTRONIUM_OVERSLIME_MULTIPLIER.get().floatValue();
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        var OSModule = OverslimeModule.INSTANCE;
        NeutroniumAssemble.consumeAntiNeutronium(tool);
        var cur = OSModule.getAmount(tool);
        if (cur>CFG_MAX_OVERSLIME_CONSUMPTION){
            var consume =(int) Math.min(CFG_MAX_OVERSLIME_CONSUMPTION,100*amount);
            OSModule.removeAmount(tool,consume);
            return 0;
        }
        NeutroniumAssemble.consumeAntiNeutronium(tool);
        return amount;
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        OverslimeModule.OVERSLIME_STAT.multiply(builder,CONFIG_OVERSLIME_MULTIPLIER);
    }
}
