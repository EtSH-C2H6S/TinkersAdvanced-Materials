package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.tinkers_advanced.core.util.CommonUtil;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.tools.entity.ToolProjectile;

public class LifeMelody extends MultiArgsDescModifier {
    public static float CFG_DAMAGE_BOOST_PERC = 0.025f;
    public static float CFG_MAX_DAMAGE_BOOST = Float.MAX_VALUE;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.1f",CFG_DAMAGE_BOOST_PERC*100)+"%",CFG_MAX_DAMAGE_BOOST};
    }

    @Override
    public void refreshConfig() {

    }

    @Override
    public float onGetMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if (context.getTarget() instanceof LivingEntity living&&context.isFullyCharged()) damage+=Math.min(CFG_MAX_DAMAGE_BOOST,
                CFG_DAMAGE_BOOST_PERC*modifier.getLevel()*(living.getMaxHealth()-living.getHealth()));
        return damage;
    }

    @Override
    public float onGetArrowDamage(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull Entity target, float baseDamage, float damage) {
        if (target instanceof LivingEntity living&&arrow.isCritArrow()) damage+=Math.min(CFG_MAX_DAMAGE_BOOST,
                CFG_DAMAGE_BOOST_PERC*entry.getLevel()*0.25f*(living.getMaxHealth()-living.getHealth()));
        return damage;
    }

    @Override
    public float onGetProjectileDamage(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, Projectile projectile, @Nullable LivingEntity attacker, @NotNull Entity target, float baseDamage, float damage) {
        if (target instanceof LivingEntity living&&projectile instanceof ToolProjectile) damage+=Math.min(CFG_MAX_DAMAGE_BOOST,
                CFG_DAMAGE_BOOST_PERC*entry.getLevel()*0.25f*(living.getMaxHealth()-living.getHealth()));
        return damage;
    }
}
