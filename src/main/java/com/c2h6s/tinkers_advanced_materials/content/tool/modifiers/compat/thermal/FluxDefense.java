package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.thermal;

import cofh.core.common.network.packet.client.OverlayMessagePacket;
import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.etstlib.tool.hooks.IndividualProtectionModifierHook;
import com.c2h6s.etstlib.util.ToolEnergyUtil;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeMaterialIds;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.DamageBlockModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.MaterialNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

import static com.c2h6s.etstlib.util.ToolEnergyUtil.extractEnergy;

import static com.c2h6s.tinkers_advanced_materials.TiAcMeConfig.Common.*;

public class FluxDefense extends FluxInfused implements DamageBlockModifierHook , IndividualProtectionModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this,ModifierHooks.DAMAGE_BLOCK, EtSTLibHooks.INDIVIDUAL_PROTECTION);
        hookBuilder.addModule(EtSTLibModifier.indiProtectionModule);
    }
    protected int CFG_CONSUMPTION = 2000;
    protected float CFG_REDUCTION = 5f;
    protected float CFG_DODGE_RATE =0.1f;
    protected float CFG_BPA_REDUCTION =0.75f;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.0f",CFG_DODGE_RATE*100)+"%",
                String.format("%.0f",CFG_BPA_REDUCTION*100)+"%"
        };
    }

    @Override
    public void refreshConfig() {
        CFG_CONSUMPTION = FLUX_ARMOR_CONSUMPTION.get();
        CFG_DODGE_RATE = FLUX_ARMOR_DODGE_RATE.get().floatValue();
        CFG_REDUCTION = FLUX_ARMOR_DAMAGE_REDUCTION.get().floatValue();
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
    }

    @Override
    public void onModeSwitch(IToolStackView tool, ServerPlayer player, ModifierEntry entry) {
        OverlayMessagePacket.sendToClient(Component.translatable("msg.tinkers_advanced.flux_mode.thermal_slash."+getMode(tool)),player);
        MaterialNBT nbt = tool.getMaterials();
        ToolStack toolStack = (ToolStack) tool;
        for (int i=0;i<nbt.size();i++){
            MaterialVariant variant = nbt.get(i);
            MaterialId materialId = variant.getId();
            if (materialId.getNamespace().equals(TinkersAdvanced.MODID)&&materialId.getPath().equals("activated_chromatic_steel")){
                switch (getMode(tool)){
                    case 1->toolStack.replaceMaterial(i,TiAcMeMaterialIds.Thermal.Variant.ACTIVATED_CHROMATIC_STEEL_ACTIVATED);
                    case 2->toolStack.replaceMaterial(i,TiAcMeMaterialIds.Thermal.Variant.ACTIVATED_CHROMATIC_STEEL_EMPOWERED);
                    default -> toolStack.replaceMaterial(i, TiAcMeMaterialIds.Thermal.ACTIVATED_CHROMATIC_STEEL);
                }
            }
        }
    }

    @Override
    public boolean isDamageBlocked(IToolStackView tool, ModifierEntry modifierEntry, EquipmentContext context, EquipmentSlot equipmentSlot, DamageSource damageSource, float amount) {
        float rate = CFG_DODGE_RATE*modifierEntry.getLevel();
        LivingEntity living = context.getEntity();
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return false;
        if (living.invulnerableTime>0){
            return true;
        }
        if (living.isInvulnerableTo(damageSource)) return true;
        if (RANDOM.nextFloat()<rate&&getMode(tool)>=1&&extractEnergy(tool,CFG_CONSUMPTION*10,true)>=CFG_CONSUMPTION*10){
            extractEnergy(tool,CFG_CONSUMPTION*10,false);
            living.invulnerableTime+=10;
            living.level().playSound(null,living.blockPosition(), SoundEvents.FIREWORK_ROCKET_LAUNCH,living.getSoundSource(),1,2);
            return true;
        }
        return false;
    }


    @Override
    public float getIndividualProtectionModifier(IToolStackView tool, ModifierEntry modifierEntry, EquipmentContext equipmentContext, EquipmentSlot equipmentSlot, DamageSource damageSource, float v) {
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)||getMode(tool)<=1) return v;
        float percentage = (float) ToolEnergyCapability.getEnergy(tool)/ToolEnergyCapability.getMaxEnergy(tool);
        percentage*=modifierEntry.getLevel();
        percentage = Math.min(percentage,(20-v)/CFG_REDUCTION);
        ToolEnergyUtil.extractEnergy(tool, (int) (percentage*CFG_CONSUMPTION*modifierEntry.getLevel()),false);
        return v+percentage*CFG_REDUCTION;
    }

    @Override
    public float getProtectionModifierForDisplay(IToolStackView tool, ModifierEntry modifierEntry, Player player, float v) {
        if (getMode(tool)<=1) return v;
        float percentage = (float) ToolEnergyCapability.getEnergy(tool)/ToolEnergyCapability.getMaxEnergy(tool);
        percentage*=modifierEntry.getLevel();
        percentage = Math.min(percentage,(20-v)/CFG_REDUCTION);
        return v+percentage*CFG_REDUCTION;
    }
}
