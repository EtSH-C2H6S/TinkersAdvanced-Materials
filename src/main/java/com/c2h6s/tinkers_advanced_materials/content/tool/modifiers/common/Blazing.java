package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.common;

import com.c2h6s.etstlib.util.EntityInRangeUtil;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Optional;

public class Blazing extends MultiArgsDescModifier {
    @Override
    public boolean isNoLevels() {
        return true;
    }

    protected static int CFG_DISTANCE = 5;
    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (world.getGameTime()%20==0){
            holder.setSecondsOnFire(200);
            Optional.ofNullable(EntityInRangeUtil.getNearestLivingEntity(holder,CFG_DISTANCE,
                    new IntOpenHashSet(), entity -> !entity.isOnFire())).ifPresent(living -> living.setSecondsOnFire(200));
        }
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{CFG_DISTANCE};
    }

    @Override
    public void refreshConfig() {

    }
}
