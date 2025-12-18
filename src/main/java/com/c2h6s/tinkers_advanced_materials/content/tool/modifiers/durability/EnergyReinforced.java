package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.durability;

import com.c2h6s.etstlib.tool.modifiers.base.BasicFEModifier;
import com.c2h6s.etstlib.util.MathUtil;
import com.c2h6s.etstlib.util.ToolEnergyUtil;
import com.c2h6s.tinkers_advanced.core.util.CommonUtil;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.util.CommonMeUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.Util;

import java.util.Arrays;
import java.util.List;

import static com.c2h6s.tinkers_advanced_materials.TiAcMeConfig.COMMON;
public class EnergyReinforced extends BasicFEModifier implements TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP);
    }

    @Override
    public int getCapacity(ModifierEntry modifierEntry) {
        return COMMON.ENERGY_REINFORCED_ENERGY_CAPACITY.get();
    }

    @Override
    public int modifierDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        int afterDecrease = CommonUtil.processConsumptionInt(amount,modifier.getLevel()*0.2f);
        if (afterDecrease<amount){
            int energy = COMMON.ENERGY_REINFORCED_CONSUMPTION.get()*(amount-afterDecrease);
            if (ToolEnergyUtil.extractEnergy(tool,energy,true)>=energy) {
                amount = afterDecrease;
                ToolEnergyUtil.extractEnergy(tool,energy,false);
            }
        }
        return amount;
    }

    @Override
    public List<Component> getDescriptionList() {
        if (descriptionList == null) {
            descriptionList = Arrays.asList(
                    Component.translatable(getTranslationKey() + ".flavor").withStyle(ChatFormatting.ITALIC),
                    Component.translatable(getTranslationKey() + ".description"
                    ,MathUtil.getEnergyString(COMMON.ENERGY_REINFORCED_CONSUMPTION.get()),
                            MathUtil.getEnergyString(COMMON.ENERGY_REINFORCED_ENERGY_CAPACITY.get()),
                            String.format("%.1f",COMMON.ENERGY_REINFORCED_DURABILITY_ENHANCE.get().floatValue()*100f)+"%")
                            .withStyle(ChatFormatting.GRAY));
        }
        return descriptionList;
    }

    @Override
    public @NotNull Component getDisplayName(int level) {
        return ModifierLevelDisplay.PLUSES.nameForLevel(this,level);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry entry, @javax.annotation.Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        float percent =COMMON.ENERGY_REINFORCED_DURABILITY_ENHANCE.get().floatValue()*entry.getLevel();
        if (percent > 0) {
            Modifier modifier = entry.getModifier();
            Component name = modifier.getDisplayName();
            tooltip.add(modifier.applyStyle(Component.literal(Util.PERCENT_FORMAT.format(percent) + " ").append(name)));
        }
    }

}
