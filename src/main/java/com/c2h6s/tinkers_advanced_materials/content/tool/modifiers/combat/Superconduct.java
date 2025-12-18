package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.etstlib.tool.modifiers.base.BasicFEModifier;
import com.c2h6s.etstlib.util.MathUtil;
import com.c2h6s.etstlib.util.ToolEnergyUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.Util;

import java.util.Arrays;
import java.util.List;

import static com.c2h6s.tinkers_advanced_materials.TiAcMeConfig.COMMON;
public class Superconduct extends BasicFEModifier implements TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP);
    }

    @Override
    public int getCapacity(ModifierEntry modifierEntry) {
        return 10000*modifierEntry.getLevel();
    }

    public static int getEffectiveLevel(IToolStackView tool,ModifierEntry entry){
        return ToolEnergyUtil.extractEnergy(tool,
                entry.getLevel()*COMMON.SUPERCONDUCT_CONSUMPTION.get(),
                true)/COMMON.SUPERCONDUCT_CONSUMPTION.get();
    }
    public static LegacyDamageSource processSource(IToolStackView tool,LegacyDamageSource source,int effectiveLevel){
        ToolEnergyUtil.extractEnergy(tool,effectiveLevel*COMMON.SUPERCONDUCT_CONSUMPTION.get(),false);
        return source.addPercentageBypassArmor(effectiveLevel*COMMON.SUPERCONDUCT_ARMOR_PIERCE.get().floatValue());
    }

    @Override
    public LegacyDamageSource modifyDamageSource(IToolStackView tool, ModifierEntry entry, LivingEntity attacker, InteractionHand hand, Entity target, EquipmentSlot sourceSlot, boolean isFullyCharged, boolean isExtraAttack, boolean isCritical, LegacyDamageSource source) {
        return processSource(tool,source,getEffectiveLevel(tool,entry));
    }

    @Override
    public List<Component> getDescriptionList() {
        if (descriptionList == null) {
            descriptionList = Arrays.asList(
                    Component.translatable(getTranslationKey() + ".flavor").withStyle(ChatFormatting.ITALIC),
                    Component.translatable(getTranslationKey() + ".description",
                                    MathUtil.getEnergyString(COMMON.SUPERCONDUCT_CONSUMPTION.get()),
                                    String.format("%.1f",COMMON.SUPERCONDUCT_ARMOR_PIERCE.get().floatValue()*100f)+"%")
                            .withStyle(ChatFormatting.GRAY));
        }
        return descriptionList;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry entry, @javax.annotation.Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        float percent =COMMON.SUPERCONDUCT_ARMOR_PIERCE.get().floatValue()*entry.getLevel();
        if (percent > 0) {
            Modifier modifier = entry.getModifier();
            Component name = modifier.getDisplayName();
            tooltip.add(modifier.applyStyle(Component.literal(Util.PERCENT_FORMAT.format(percent) + " ").append(name)));
        }
    }
}
