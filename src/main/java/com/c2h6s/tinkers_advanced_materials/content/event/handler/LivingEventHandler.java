package com.c2h6s.tinkers_advanced_materials.content.event.handler;

import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEventHandler {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        MobEffectInstance instance = event.getEntity().getEffect(TiAcMeEffects.TETANUS.get());
        if (instance!=null&&instance.getDuration()>0){
            event.setAmount((float) (event.getAmount()*TiAcMeConfig.COMMON.EFFECT_TETANUS_DAMAGE_MULTIPLIER.get()));
        }
    }
    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event){
        LivingEntity living = event.getEntity();
        MobEffectInstance instance = living.getEffect(TiAcMeEffects.PROTO_POISON.get());
        if (instance!=null){
            event.setAmount((float) (event.getAmount()*(1-(TiAcMeConfig.COMMON.EFFECT_PROTO_POISON_REGENERATION_DECREASE.get() +instance.getAmplifier()*TiAcMeConfig.COMMON.EFFECT_PROTO_POISON_REGENERATION_DECREASE.get()))));
        }
        instance = living.getEffect(TiAcMeEffects.PLAGUE.get());
        if (instance!=null){
            event.setAmount((float) (event.getAmount()*(1-(instance.getAmplifier()+1)*TiAcMeConfig.COMMON.EFFECT_PLAGUE_REGENERATION_DECREASE.get())));
        }
    }
}
