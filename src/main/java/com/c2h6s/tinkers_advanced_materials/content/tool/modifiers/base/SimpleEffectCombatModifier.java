package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SimpleEffectCombatModifier extends MultiArgsDescModifier{
    public final Supplier<Integer> EFFECT_LEVEL_FLAT;
    public final Supplier<Integer> EFFECT_LEVEL_EACH;
    public final Supplier<Integer> EFFECT_TIME_FLAT;
    public final Supplier<Integer> EFFECT_TIME_EACH;
    public final Supplier<MobEffect> EFFECT;

    public SimpleEffectCombatModifier(Supplier<Integer> effectLevelFlat, Supplier<Integer> effectLevelEach, Supplier<Integer> effectTimeFlat, Supplier<Integer> effectTimeEach, Supplier<MobEffect> effect) {
        EFFECT_LEVEL_FLAT = effectLevelFlat;
        EFFECT_LEVEL_EACH = effectLevelEach;
        EFFECT_TIME_FLAT = effectTimeFlat;
        EFFECT_TIME_EACH = effectTimeEach;
        EFFECT = effect;
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{EFFECT.get().getDisplayName().getString()};
    }

    @Override
    public Component getDisplayName() {
        return EFFECT.get().getDisplayName();
    }

    @Override
    public @NotNull Component getDisplayName(int level) {
        return getDisplayName().copy().append(" ").append(RomanNumeralHelper.getNumeral(level));
    }

    @Override
    public List<Component> getDescriptionList() {
        if (descriptionList == null) {
            descriptionList = Arrays.asList(
                    EFFECT.get().getDisplayName(),
                    Component.translatable("modifier.tinkers_advanced.add_mob_effect.description",
                            getDescArgs()).withStyle(ChatFormatting.GRAY));
        }
        return descriptionList;
    }

    @Override
    public void refreshConfig() {

    }

    @Override
    public void postMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt, float damage) {
        if (context.getTarget() instanceof LivingEntity living) living.forceAddEffect(
                new MobEffectInstance(EFFECT.get(),
                        (EFFECT_TIME_EACH.get()*modifier.getLevel())+EFFECT_TIME_FLAT.get(),
                        (EFFECT_LEVEL_EACH.get()*modifier.getLevel())+EFFECT_LEVEL_FLAT.get()-1
                ),context.getAttacker()
        );
    }

    @Override
    public void afterArrowHit(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull LivingEntity target, float damageDealt) {
        target.forceAddEffect(
                new MobEffectInstance(EFFECT.get(),
                        (EFFECT_TIME_EACH.get()*entry.getLevel())+EFFECT_TIME_FLAT.get(),
                        (EFFECT_LEVEL_EACH.get()*entry.getLevel())+EFFECT_LEVEL_FLAT.get()-1
                ),attacker
        );
    }
}
