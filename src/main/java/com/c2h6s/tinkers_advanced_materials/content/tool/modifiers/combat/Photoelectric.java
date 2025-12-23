package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.util.ToolEnergyUtil;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Photoelectric extends MultiArgsDescModifier {
    protected static int CFG_FE_PER_BRIGHT = 2;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                CFG_FE_PER_BRIGHT
        };
    }

    @Override
    public void refreshConfig() {
        CFG_FE_PER_BRIGHT = TiAcMeConfig.COMMON.PHOTOELECTRIC_FE_PER_BRIGHT.get();
    }

    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (holder.level().isClientSide) return;
        var totalLight = world.getBrightness(LightLayer.BLOCK, holder.blockPosition()) + (15-world.getSkyDarken());
        if (totalLight>0){
            if (ToolEnergyCapability.getMaxEnergy(tool)>0){
                var toProduce = Math.min(CFG_FE_PER_BRIGHT*totalLight, ToolEnergyUtil.receiveEnergy(tool,CFG_FE_PER_BRIGHT*totalLight,true));
                if (toProduce>0) ToolEnergyUtil.receiveEnergy(tool,toProduce,false);
            }
        }
    }
}
