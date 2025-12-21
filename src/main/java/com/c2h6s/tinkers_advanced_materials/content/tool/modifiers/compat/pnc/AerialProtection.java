package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.pnc;

import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.etstlib.tool.hooks.IndividualProtectionModifierHook;
import com.c2h6s.etstlib.tool.modifiers.base.BasicPressurizableModifier;
import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.etstlib.tool.modifiers.capabilityProvider.PnCIntegration.AirStorageProvider;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.IHaveConfigToInit;
import me.desht.pneumaticcraft.common.core.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

import static com.c2h6s.etstlib.tool.modifiers.capabilityProvider.PnCIntegration.AirStorageProvider.*;

public class AerialProtection extends BasicPressurizableModifier implements DamageBlockModifierHook, ModifyDamageModifierHook, IndividualProtectionModifierHook, IHaveConfigToInit {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.DAMAGE_BLOCK,ModifierHooks.MODIFY_DAMAGE, EtSTLibHooks.INDIVIDUAL_PROTECTION);
        hookBuilder.addModule(EtSTLibModifier.indiProtectionModule);
    }
    @Override
    public List<Component> getDescriptionList() {
        if (descriptionList == null)
            descriptionList = IHaveConfigToInit.createDescriptions(getTranslationKey(),this);
        return descriptionList;
    }

    protected static int CFG_CONSUMPTION = 20;
    protected static float CFG_BYPASS_ARMOR_REDUCTION = 0.6f;
    protected static float CFG_DAMAGE_BLOCK = 2.5f;
    protected static float CFG_PROTECTION = 2f;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.1f",CFG_DAMAGE_BLOCK),
                String.format("%.0f",CFG_BYPASS_ARMOR_REDUCTION*100)+"%"
        };
    }

    @Override
    public void refreshConfig() {
        CFG_CONSUMPTION = TiAcMeConfig.COMMON.AERIAL_PROTECTION_AIR_CONSUMPTION.get();
        CFG_PROTECTION = TiAcMeConfig.COMMON.AERIAL_PROTECTION_MAX_PROTECTION_LEVEL.get().floatValue();
        CFG_DAMAGE_BLOCK = TiAcMeConfig.COMMON.AERIAL_PROTECTION_DAMAGE_BLOCK_AFTER_ARMOR.get().floatValue();
        CFG_BYPASS_ARMOR_REDUCTION = TiAcMeConfig.COMMON.AERIAL_PROTECTION_BYPASS_ARMOR_REDUCTION.get().floatValue();
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
    }

    @Override
    public int getBaseVolume(ModifierEntry modifierEntry) {
        return 5000*modifierEntry.getLevel();
    }

    @Override
    public float getMaxPressure(ModifierEntry modifierEntry) {
        return 0;
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifierEntry, EquipmentContext equipmentContext, EquipmentSlot equipmentSlot, DamageSource damageSource, float damage) {
        if (damage<=CFG_DAMAGE_BLOCK*modifierEntry.getLevel()&&getAir(tool)>= CFG_CONSUMPTION*damage &&!equipmentContext.getLevel().isClientSide){
            LivingEntity entity = equipmentContext.getEntity();
            addAir(tool, (int) (-damage*CFG_CONSUMPTION));
            entity.level().playSound(null,entity.blockPosition(), ModSounds.SHORT_HISS.get(), entity.getSoundSource(),1,1);
            return true;
        }
        return false;
    }


    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (source.is(DamageTypeTags.BYPASSES_ARMOR)) amount-=amount*CFG_BYPASS_ARMOR_REDUCTION;
        if (getAir(tool)>0&&!context.getLevel().isClientSide){
            float reduce = Math.min(CFG_DAMAGE_BLOCK*modifier.getLevel(), (float) getAir(tool) /CFG_CONSUMPTION);
            if (reduce>0){
                addAir(tool, (int) (-CFG_CONSUMPTION*reduce));
                amount-=reduce;
                LivingEntity entity = context.getEntity();
                entity.level().playSound(null,entity.blockPosition(), ModSounds.SHORT_HISS.get(), entity.getSoundSource(),1,1);
            }
        }
        return amount;
    }

    @Override
    public float getIndividualProtectionModifier(IToolStackView tool, ModifierEntry modifier, EquipmentContext equipmentContext, EquipmentSlot equipmentSlot, DamageSource damageSource, float v) {
        float multiplier = ((float) getAir(tool) / AirStorageProvider.getBaseVolume(tool))/20f;
        addAir(tool, (int) (-multiplier*CFG_CONSUMPTION));
        multiplier*=modifier.getLevel();
        return v + CFG_PROTECTION*multiplier;
    }

    @Override
    public float getProtectionModifierForDisplay(IToolStackView tool, ModifierEntry modifier, Player player, float v) {
        float multiplier = ((float) getAir(tool) / AirStorageProvider.getBaseVolume(tool))/20f;
        multiplier*=modifier.getLevel();
        return v + CFG_PROTECTION*multiplier;
    }

    @Override
    public Component getTranslationName(IToolStackView tool, ModifierEntry modifier, Player player) {
        return getDisplayName();
    }
}
