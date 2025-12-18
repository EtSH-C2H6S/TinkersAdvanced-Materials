package com.c2h6s.tinkers_advanced_materials.content.item;

import lombok.Getter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EffectStarItem extends Item {
    @Getter
    private final MobEffect effect;
    @Getter
    private final int duration;
    @Getter
    private final int amplifier;
    public EffectStarItem(MobEffect effect, int duration, int amplifier) {
        super(new Properties());
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        var stack = pPlayer.getItemInHand(pUsedHand);
        if (stack.getItem() instanceof EffectStarItem effectStarItem){
            stack.shrink(1);
            effectStarItem.addEffectTo(pPlayer,1);
            return InteractionResultHolder.success(stack);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void addEffectTo(LivingEntity living,int count){
        if (getEffect()== MobEffects.HEAL)
            living.heal(4*(getAmplifier()+1)*count);
        else living.addEffect(new MobEffectInstance(getEffect(),
                getDuration()*count,getAmplifier(),false,false));
    }
}
