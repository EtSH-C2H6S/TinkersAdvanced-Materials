package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.mekanism;

import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import mekanism.common.capabilities.Capabilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class RadiationRemoval extends EtSTBaseModifier {
    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (isCorrectSlot&&!world.isClientSide){
            holder.getCapability(Capabilities.RADIATION_ENTITY).ifPresent(cap->{
                if (cap.getRadiation()>10E-7){
                    cap.set(cap.getRadiation()*Math.pow(TiAcMeConfig.COMMON.RADIATION_REMOVAL_DECAY_SPEED.get(),modifier.getLevel()));
                }
            });
        }
    }
}
