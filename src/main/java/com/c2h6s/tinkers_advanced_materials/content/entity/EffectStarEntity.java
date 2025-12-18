package com.c2h6s.tinkers_advanced_materials.content.entity;

import com.c2h6s.tinkers_advanced_materials.content.item.EffectStarItem;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EffectStarEntity extends ItemEntity {
    public EffectStarEntity(EntityType<? extends ItemEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
        this.setGlowingTag(true);
        this.lifespan = 200;
    }
    public EffectStarEntity(Level level){
        this(TiAcMeEntities.EFFECT_STAR.get(),level);
    }
    public EffectStarEntity(Level level, Vec3 pos,double velocity,EffectStarItem item){
        this(level);
        this.setPos(pos);
        this.setDeltaMovement(new Vec3(random.nextDouble()-0.5,random.nextDouble()-0.5,random.nextDouble()-0.5).normalize().scale(velocity));
        this.setPickUpDelay(30);
        this.setItem(new ItemStack(item));
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event){
        if (event.getItem() instanceof EffectStarEntity entity){
            if (entity.hasPickUpDelay()) return;
            if (entity.getItem().getItem() instanceof EffectStarItem item)
                item.addEffectTo(event.getEntity(),entity.getItem().getCount());
            entity.discard();
            entity.level().playSound(null,entity.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS,0.2F, (entity.random.nextFloat() - entity.random.nextFloat()) * 1.4F + 2.0F);
            event.setCanceled(true);
        }
    }

    @Override
    public int getTeamColor() {
        if (this.getItem().getItem() instanceof EffectStarItem starItem) return starItem.getEffect().getColor();
        return super.getTeamColor();
    }

    @Override
    public void tick() {
        if (getItem().getItem() instanceof EffectStarItem effectStarItem&&tickCount%2==0)
            this.makeParticle(1,effectStarItem.getEffect());
        super.tick();
    }

    private void makeParticle(int particleAmount, MobEffect mobEffect) {
        int i = mobEffect.getColor();
        if (i != -1 && particleAmount > 0) {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i & 255) / 255.0D;

            for(int j = 0; j < particleAmount; ++j) {
                this.level().addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.1D), this.getRandomY(), this.getRandomZ(0.1D), d0, d1, d2);
            }
        }
    }
}
