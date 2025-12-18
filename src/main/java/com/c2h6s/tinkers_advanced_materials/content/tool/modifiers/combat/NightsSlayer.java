package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.etstlib.tool.hooks.IndividualProtectionModifierHook;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.LightLayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.List;

public class NightsSlayer extends MultiArgsDescModifier implements TooltipModifierHook, IndividualProtectionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.TOOLTIP, EtSTLibHooks.INDIVIDUAL_PROTECTION);
        hookBuilder.addModule(EtSTLibModifier.indiProtectionModule);
    }
    protected static float CFG_MAX_DAMAGE_BONUS_EACH_LEVEL = 0.25f;
    protected static float CFG_MAX_PROTECTION_LEVEL_EACH_LEVEL = 2.5f;

    public static float getBonus(LivingEntity living,int modifierLevel){
        var level = living.level();
        level.updateSkyBrightness();
        var totalLight = level.getBrightness(LightLayer.BLOCK,living.blockPosition())+(15-level.getSkyDarken());
        return Mth.clamp((7f-totalLight) / 7,0,1)*modifierLevel * CFG_MAX_DAMAGE_BONUS_EACH_LEVEL;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        if (player!=null) {
            if (tool.hasTag(TinkerTags.Items.ARMOR)||tool.hasTag(TinkerTags.Items.SHIELDS)) return;
            tooltip.add(Component.literal("+" +
                    String.format("%.0f", getBonus(player, modifier.getLevel()) * 100) + "% ")
                    .withStyle(getDisplayName().getStyle()).append(getDisplayName()));
        }
    }

    @Override
    public float onGetMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        return damage+baseDamage*getBonus(context.getAttacker(),modifier.getLevel());
    }

    @Override
    public float onGetArrowDamage(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull Entity target, float baseDamage, float damage) {
        if (attacker!=null)
            return damage+baseDamage*getBonus(attacker,entry.getLevel());
        return damage;
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.0f", CFG_MAX_DAMAGE_BONUS_EACH_LEVEL *100)+"%",
                String.format("%.1f", CFG_MAX_PROTECTION_LEVEL_EACH_LEVEL)
        };
    }

    @Override
    public void refreshConfig() {
        CFG_MAX_PROTECTION_LEVEL_EACH_LEVEL = TiAcMeConfig.COMMON.NIGHT_SLAYER_MAX_PROTECTION_LEVEL.get().floatValue();
        CFG_MAX_DAMAGE_BONUS_EACH_LEVEL = TiAcMeConfig.COMMON.NIGHT_SLAYER_MAX_DAMAGE_PERC.get().floatValue();
    }

    @Override
    public float getIndividualProtectionModifier(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot equipmentSlot, DamageSource damageSource, float v) {
        if (!tool.hasTag(TinkerTags.Items.ARMOR)&&!tool.hasTag(TinkerTags.Items.SHIELDS))
            return v;
        if (equipmentSlot.isArmor()||equipmentSlot==EquipmentSlot.OFFHAND)
            return v + getBonus(context.getEntity(),modifier.getLevel())*CFG_MAX_PROTECTION_LEVEL_EACH_LEVEL;
        return v;
    }

    @Override
    public float getProtectionModifierForDisplay(IToolStackView tool, ModifierEntry modifier, Player player, float v) {
        if (!tool.hasTag(TinkerTags.Items.ARMOR)&&!tool.hasTag(TinkerTags.Items.SHIELDS))
            return v;
        return v + getBonus(player,modifier.getLevel())*CFG_MAX_PROTECTION_LEVEL_EACH_LEVEL;
    }

    @Override
    public String getProtectionName(IToolStackView tool, ModifierEntry modifier, Player player) {
        if (!tool.hasTag(TinkerTags.Items.ARMOR)||!tool.hasTag(TinkerTags.Items.SHIELDS)) return null;
        return IndividualProtectionModifierHook.super.getProtectionName(tool, modifier, player);
    }
}
