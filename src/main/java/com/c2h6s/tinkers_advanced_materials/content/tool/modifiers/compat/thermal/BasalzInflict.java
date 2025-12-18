package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.thermal;

import cofh.core.init.CoreMobEffects;
import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.UUID;

public class BasalzInflict extends EtSTBaseModifier {
    public static final UUID UUID_ARMOR_DECREASE = UUID.fromString("9f91543f-5b19-aada-8cce-f733a6c37299");
    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        if (context.isFullyCharged()&&context.getTarget() instanceof LivingEntity living){
            living.forceAddEffect(new MobEffectInstance(CoreMobEffects.SUNDERED.get(),80+40*modifier.getLevel(),modifier.getLevel()-1),context.getAttacker());
            living.invulnerableTime=0;
            if (context.getTarget() instanceof Player) return;
            if (living.getArmorValue()>0){
                var atI = living.getAttribute(Attributes.ARMOR);
                if (atI==null) return;
                var atM = atI.getModifier(UUID_ARMOR_DECREASE);
                var d0 = 0d;
                if (atM!=null){
                    d0+=atM.getAmount();
                    atI.removeModifier(UUID_ARMOR_DECREASE);
                }
                d0-=atI.getValue()*0.25f;
                atI.addTransientModifier(new AttributeModifier(UUID_ARMOR_DECREASE,"attribute.tinkers_advanced.armor_decrease.desc",d0, AttributeModifier.Operation.ADDITION));
            }
        }
    }
    @Override
    public void afterArrowHit(ModDataNBT persistentData, ModifierEntry modifier, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull LivingEntity target, float damageDealt) {
        if (arrow.isCritArrow()){
            target.forceAddEffect(new MobEffectInstance(CoreMobEffects.SUNDERED.get(),80+40*modifier.getLevel(),modifier.getLevel()-1),attacker);
            target.invulnerableTime=0;
        }
    }
}
