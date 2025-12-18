package com.c2h6s.tinkers_advanced_materials.content.effect;

import com.c2h6s.etstlib.content.effects.EtSTBaseEffect;
import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.NotNull;

public class Frozen extends EtSTBaseEffect {
    public static final String FROZEN_UUID = "be274b33-0d6c-1570-24f4-bc92b7d88725";
    public Frozen() {
        super(MobEffectCategory.HARMFUL, 0x83D3FF);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,FROZEN_UUID,-0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.setTicksFrozen(500);
        var it = pLivingEntity.invulnerableTime;
        pLivingEntity.hurt(LegacyDamageSource.any(pLivingEntity.damageSources().freeze()).setBypassInvulnerableTime(),
                pAmplifier+1);
        pLivingEntity.invulnerableTime = it;
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration%10==0;
    }
}
