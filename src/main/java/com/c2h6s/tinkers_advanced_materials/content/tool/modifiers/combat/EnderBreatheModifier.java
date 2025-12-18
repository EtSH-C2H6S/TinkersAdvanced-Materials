package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.tinkers_advanced.core.util.CommonUtil;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.network.TiAcMePacketHandler;
import com.c2h6s.tinkers_advanced_materials.network.packets.PParticleRingS2C;
import com.c2h6s.tinkers_advanced_materials.util.CommonMeUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class EnderBreatheModifier extends MultiArgsDescModifier {
    protected float CFG_AOE_RADIUS = 2.5f;
    protected float CFG_EXTRA_DAMAGE_PERC = 0.25f;
    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.1f",CFG_AOE_RADIUS),
                String.format("%.0f",CFG_EXTRA_DAMAGE_PERC*100)+"%"
        };
    }
    @Override
    public void refreshConfig() {
        this.CFG_EXTRA_DAMAGE_PERC = TiAcMeConfig.COMMON.ENDER_BREATHE_AOE_DAMAGE_PERC.get().floatValue();
        this.CFG_AOE_RADIUS = TiAcMeConfig.COMMON.ENDER_BREATHE_AOE_RADIUS.get().floatValue();
    }

    @Override
    public void postMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt, float damage) {
        if (context.isFullyCharged()&&!context.isExtraAttack()){
            conductAOE(context.getTarget().position().add(0,context.getTarget().getBbHeight()/2,0),
                    damage*CFG_EXTRA_DAMAGE_PERC*modifier.getLevel(),CFG_AOE_RADIUS*modifier.getLevel(),
                    context.getAttacker());
        }
    }

    @Override
    public void afterArrowHit(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull LivingEntity target, float damageDealt) {
        if (arrow.isCritArrow()&&attacker!=null){
            conductAOE(target.position().add(0,target.getBbHeight()/2,0),
                    damageDealt*CFG_EXTRA_DAMAGE_PERC*entry.getLevel(),CFG_AOE_RADIUS*entry.getLevel(),
                    attacker);
        }
    }

    public static void conductAOE(Vec3 pos, float damage, float radius, LivingEntity attacker){
        attacker.level().getEntitiesOfClass(Entity.class,
                new AABB(pos.add(radius,radius,radius),pos.subtract(radius,radius,radius)),entity ->
                entity.isAttackable()&&entity!=attacker&& CommonMeUtils.checkTarget(attacker,entity) && entity.position().subtract(pos).normalize().length()<=radius).forEach(entity ->{
                    var it = entity.invulnerableTime;
                    entity.hurt(LegacyDamageSource.indirectMagic(attacker).setBypassInvulnerableTime().setBypassArmor(),damage);
                    entity.invulnerableTime = it;
        });
        if (attacker instanceof ServerPlayer serverPlayer){
            TiAcMePacketHandler.sendToPlayer(new PParticleRingS2C(0.25f,radius/50f,pos, ParticleTypes.DRAGON_BREATH,5f),serverPlayer);
        }
    }
}
