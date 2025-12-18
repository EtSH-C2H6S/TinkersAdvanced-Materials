package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.hookProviders;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public class FrostChargeModule implements HookProvider, InventoryTickModifierHook, TooltipModifierHook {
    public static float CFG_CHARGE_EACH_SECOND = 0.1f;
    public static float CFG_CHARGE_CONSUMPTION_EACH_HIT = 0.05f;
    public static final ResourceLocation KEY_FROSTED_SHIELD = TinkersAdvanced.getLocation("frosted_charge");

    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (isCorrectSlot&&world.getGameTime()%20==0) {
            var shield = tool.getPersistentData().getFloat(KEY_FROSTED_SHIELD);
            if (shield < 1 && holder.getTicksFrozen() > 10) {
                shield += CFG_CHARGE_EACH_SECOND;
                shield = Mth.clamp(shield,0,1);
                tool.getPersistentData().putFloat(KEY_FROSTED_SHIELD,shield);
                holder.setTicksFrozen(Math.max(0, holder.getTicksFrozen()-10));
            }
        }
    }

    public static void consumeFrostCharge(IToolStackView toolStackView,float value){
        var charge = toolStackView.getPersistentData().getFloat(KEY_FROSTED_SHIELD);
        charge-=value;
        charge = Mth.clamp(charge,0,1);
        toolStackView.getPersistentData().putFloat(KEY_FROSTED_SHIELD,charge);
    }
    public static void consumeFrostCharge(IToolStackView toolStackView){
        consumeFrostCharge(toolStackView,CFG_CHARGE_CONSUMPTION_EACH_HIT);
    }
    public static boolean isChargeReady(IToolStackView tool){
        return tool.getPersistentData().getFloat(KEY_FROSTED_SHIELD)>CFG_CHARGE_CONSUMPTION_EACH_HIT;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return List.of(ModifierHooks.INVENTORY_TICK,ModifierHooks.TOOLTIP);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        var comp = Component.translatable("tooltip.tinkers_advanced.modifiers.frosted_charge")
                .append(String.format("%.1f",tool.getPersistentData().getFloat(KEY_FROSTED_SHIELD)*100)+"%")
                .withStyle(style -> style.withColor(0x99D1FF));
        if (!tooltip.contains(comp)) tooltip.add(comp);
    }
}
