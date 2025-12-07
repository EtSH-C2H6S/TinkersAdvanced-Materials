package com.c2h6s.tinkers_advanced_materials.content.entity;

import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.etstlib.util.AttackUtil;
import com.c2h6s.tinkers_advanced.core.content.entity.VisualScaledProjectile;
import com.c2h6s.tinkers_advanced.core.util.CommonUtil;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class AnnihilateExplosionEntity extends VisualScaledProjectile {
    public AnnihilateExplosionEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public AnnihilateExplosionEntity(Level level){
        this(TiAcMeEntities.ANNIHILATE_EXPLOSION.get(),level);
    }
    protected static boolean CONFIG_BYPASS_INVULNERABLE = false;

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    protected AABB makeBoundingBox() {
        return super.makeBoundingBox().move(new Vec3(0,-this.getBbHeight()/2,0)).inflate(this.getScale()*2);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount<4||this.tickCount%2==0)
            this.level().getEntitiesOfClass(LivingEntity.class,this.getBoundingBoxForCulling(),this::canHitEntity)
                .forEach(this::hitEntity);
        if (this.tickCount>=9) this.discard();
    }

    protected void hitEntity(LivingEntity living){
        if (this.getOwner() instanceof Player player)
            if (CONFIG_BYPASS_INVULNERABLE) {
                AttackUtil.hurtEntity(living, this.baseDamage, LegacyDamageSource.playerAttack(player)
                        .setMsgId("attack.player_"+random.nextInt(4)).setBypassInvulnerableTime());
            } else living.hurt(LegacyDamageSource.playerAttack(player)
                    .setMsgId("attack.player_"+random.nextInt(4))
                    .setBypassInvulnerableTime().setBypassArmor().setBypassMagic().setBypassEnchantment().setBypassShield()
                    ,this.baseDamage);
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return CommonUtil.checkTarget(this,entity);
    }
}
