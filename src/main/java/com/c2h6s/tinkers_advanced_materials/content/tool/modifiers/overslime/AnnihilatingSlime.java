package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.overslime;

import com.c2h6s.tinkers_advanced_materials.content.entity.AnnihilateExplosionEntity;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import static com.c2h6s.tinkers_advanced_materials.TiAcMeConfig.COMMON;

public class AnnihilatingSlime extends MultiArgsDescModifier implements ToolStatsModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS);
    }

    protected static float CONFIG_OVERSLIME_MULTIPLIER = 10;
    protected static int CONFIG_OVERSLIME_MAX_CONSUMPTION = 2000;
    protected static float CONFIG_MAX_DAMAGE_MUL = 2;

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        OverslimeModule.OVERSLIME_STAT.multiply(builder,CONFIG_OVERSLIME_MULTIPLIER);
    }

    @Override
    public void postMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt, float damage) {
        if (!(context.getTarget() instanceof LivingEntity living)||context.isExtraAttack()||!context.isFullyCharged()) return;
        NeutroniumAssemble.consumeAntiNeutronium(tool);
        var OSModule = OverslimeModule.INSTANCE;
        var overslime = OSModule.getAmount(tool);
        int toConsume = (int) Math.min(overslime,CONFIG_OVERSLIME_MAX_CONSUMPTION*CONFIG_MAX_DAMAGE_MUL*modifier.getLevel());
        float mul = (float) toConsume /CONFIG_OVERSLIME_MAX_CONSUMPTION;
        if (mul>0.1){
            OSModule.removeAmount(tool,modifier,toConsume);
            AnnihilateExplosionEntity entity = new AnnihilateExplosionEntity(context.getLevel());
            entity.setOwner(context.getAttacker());
            entity.baseDamage = damage*mul;
            entity.setScale(2+RANDOM.nextFloat());
            entity.setPos(living.position().add(0,0.5*living.getBbHeight(),0));
            context.getLevel().addFreshEntity(entity);
            NeutroniumAssemble.consumeAntiNeutronium(tool);
        }
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{CONFIG_OVERSLIME_MULTIPLIER,CONFIG_OVERSLIME_MAX_CONSUMPTION,CONFIG_MAX_DAMAGE_MUL};
    }

    @Override
    public void refreshConfig() {
        CONFIG_MAX_DAMAGE_MUL = COMMON.ANTI_NEUTRONIUM_MAX_DAMAGE_MUL.get().floatValue();
        CONFIG_OVERSLIME_MULTIPLIER = COMMON.ANTI_NEUTRONIUM_OVERSLIME_MULTIPLIER.get().floatValue();
        CONFIG_OVERSLIME_MAX_CONSUMPTION = COMMON.ANTI_NEUTRONIUM_OVERSLIME_MAX_CONSUMPTION.get();
    }
}
