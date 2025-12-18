package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.tool.hooks.CriticalAttackModifierHook;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.util.CommonMeUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class NeuroExcitement extends MultiArgsDescModifier implements CriticalAttackModifierHook, EquipmentChangeModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.EQUIPMENT_CHANGE, EtSTLibHooks.CRITICAL_ATTACK);
    }
    protected static int CFG_TICKS_LASTING = 5;
    protected static float CFG_CRITICAL_MODIFIER = 0.5f;

    public static final ResourceLocation KEY_NEURO_EXCITEMENT = TinkersAdvanced.getLocation("neuro_excitement");

    @Override
    public void onEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if (context.getChangedSlot()==EquipmentSlot.MAINHAND&& validateEquipResult(tool,context.getOriginalTool()))
            tool.getPersistentData().putInt(KEY_NEURO_EXCITEMENT,5);

    }

    @Override
    public boolean setCritical(IToolStackView tool, ModifierEntry modifierEntry, LivingEntity livingEntity, InteractionHand interactionHand, Entity entity, EquipmentSlot equipmentSlot, boolean b, boolean b1, boolean b2) {
        return tool.getPersistentData().contains(KEY_NEURO_EXCITEMENT, Tag.TAG_INT)&&b;
    }

    @Override
    public float getCriticalModifier(IToolStackView tool, ModifierEntry entry, ToolAttackContext context, float originalModifier, float modifier) {
        if (tool.getPersistentData().contains(KEY_NEURO_EXCITEMENT, Tag.TAG_INT)&&context.isFullyCharged())
            modifier+=CFG_CRITICAL_MODIFIER*entry.getLevel();
        return modifier;
    }

    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        var excitement = tool.getPersistentData().getInt(KEY_NEURO_EXCITEMENT);
        if (excitement>0){
            excitement-=1;
            if (excitement<=0) tool.getPersistentData().remove(KEY_NEURO_EXCITEMENT);
            else tool.getPersistentData().putInt(KEY_NEURO_EXCITEMENT,excitement);
        }
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{String.format("%.2f",CFG_TICKS_LASTING/20f),
        String.format("%.0f",100*CFG_CRITICAL_MODIFIER)+"%"};
    }

    @Override
    public void refreshConfig() {
        CFG_CRITICAL_MODIFIER = TiAcMeConfig.COMMON.NEURO_EXCITEMENT_CRITICAL_MODIFIER_BONUS.get().floatValue();
        CFG_TICKS_LASTING = TiAcMeConfig.COMMON.NEURO_EXCITEMENT_TICKS_LASTING.get();
    }

    public static boolean validateEquipResult(@NotNull IToolStackView tool, IToolStackView originalTool){
        return originalTool == null || originalTool.getItem() != tool.getItem() ;
    }
}
