package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat.ammo;

import com.c2h6s.tinkers_advanced_materials.network.TiAcMePacketHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ScheduledProjectileTaskModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.utils.Schedule;
import slimeknights.tconstruct.tools.network.EntityMovementChangePacket;

public class AerialUnstable extends Modifier implements ScheduledProjectileTaskModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.SCHEDULE_PROJECTILE_TASK);
    }

    @Override
    public void scheduleProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, Schedule.Scheduler scheduler) {
        scheduler.add(0,4+RANDOM.nextInt(4));
    }

    @Override
    public void onScheduledProjectileTask(IToolStackView tool, ModifierEntry modifier, ItemStack ammo, Projectile projectile, @Nullable AbstractArrow arrow, ModDataNBT persistentData, int task) {
        if (!projectile.isRemoved()&&task==0) {
            var vec3 = projectile.getDeltaMovement();
            float factor = (float) (vec3.length() * 0.2f * modifier.getLevel());
            projectile.setDeltaMovement(vec3.offsetRandom(RandomSource.create(), factor));
            if (projectile.level() instanceof ServerLevel serverLevel){
                TiAcMePacketHandler.sendToClient(new EntityMovementChangePacket(projectile));
            }
        }
    }
}
