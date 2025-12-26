package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat.ammo;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileFuseModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ScheduledProjectileTaskModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.utils.Schedule;

public class ChemicalUnstable extends Modifier implements ScheduledProjectileTaskModifierHook, ProjectileFuseModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this,ModifierHooks.SCHEDULE_PROJECTILE_TASK,ModifierHooks.PROJECTILE_FUSE);
    }

    @Override
    public void onProjectileFuseFinish(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow) {
        projectile.level().explode(projectile,projectile.getX(),projectile.getY(),projectile.getZ(),modifier.getLevel()+1f, Level.ExplosionInteraction.TNT);
    }

    @Override
    public void scheduleProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, Schedule.Scheduler scheduler) {
        scheduler.add(0, RANDOM.nextInt(40));
    }

    @Override
    public void onScheduledProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, int task) {
        //Original by KnightMiner
        //略微修改，即便是箭矢也销毁
        if (task == 0 && !projectile.isRemoved()) {
            ModifierNBT modifiers = EntityModifierCapability.getOrEmpty(projectile);
            for (ModifierEntry entry : modifiers) {
                entry.getHook(ModifierHooks.PROJECTILE_FUSE).onProjectileFuseFinish(modifiers, persistentData, entry, ammo, projectile, arrow);
            }
            if (!projectile.level().isClientSide) {
                projectile.discard();
            }
        }
    }
}
