package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.common;

import com.c2h6s.etstlib.util.UUIDUtil;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.AttributesModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.function.BiConsumer;

import static com.c2h6s.etstlib.util.MathUtil.toPercentage;

public class HeavyMaterial extends MultiArgsDescModifier implements ToolStatsModifierHook, AttributesModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOL_STATS,ModifierHooks.ATTRIBUTES);
    }
    protected static float CFG_DAMAGE_PERC = 0.15f;
    protected static float CFG_MOVEMENT_PERC = 0.1f;
    protected static float CFG_ATK_SPEED_PERC = 0.05f;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                toPercentage(CFG_DAMAGE_PERC,0),
                toPercentage(CFG_MOVEMENT_PERC,0),
                toPercentage(CFG_ATK_SPEED_PERC,0)
        };
    }

    @Override
    public void refreshConfig() {

    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        ToolStats.ATTACK_DAMAGE.percent(builder,CFG_DAMAGE_PERC*modifier.getLevel());
        ToolStats.ATTACK_SPEED.percent(builder,-CFG_ATK_SPEED_PERC*modifier.getLevel());
    }

    @Override
    public void addAttributes(IToolStackView tool, ModifierEntry modifier, EquipmentSlot slot, BiConsumer<Attribute, AttributeModifier> consumer) {
        var Uuid = UUIDUtil.UUIDFromSlot(slot,modifier.getId());
        consumer.accept(Attributes.MOVEMENT_SPEED,
                new AttributeModifier(Uuid,Attributes.MOVEMENT_SPEED.getDescriptionId(),-CFG_MOVEMENT_PERC*modifier.getLevel(), AttributeModifier.Operation.MULTIPLY_BASE));
    }
}
