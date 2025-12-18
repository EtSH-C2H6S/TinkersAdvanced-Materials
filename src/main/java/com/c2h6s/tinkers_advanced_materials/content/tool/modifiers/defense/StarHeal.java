package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.defense;

import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.tool.hooks.OnHoldingPreventDeathHook;
import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.entity.EffectStarEntity;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import com.c2h6s.tinkers_advanced_materials.network.TiAcMePacketHandler;
import com.c2h6s.tinkers_advanced_materials.network.packets.PParticleRingS2C;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.modifiers.modules.technical.SlotInChargeModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class StarHeal extends MultiArgsDescModifier implements OnHoldingPreventDeathHook , OnAttackedModifierHook {
    public static final TinkerDataCapability.TinkerDataKey<Integer> KEY_STAR_HEAL = TinkerDataCapability.TinkerDataKey.of(TinkersAdvanced.getLocation("star_heal"));
    public static final ResourceLocation KEY_DEATH_PREVENT_CD = TinkersAdvanced.getLocation("star_dp_cd");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, EtSTLibHooks.PREVENT_DEATH, ModifierHooks.ON_ATTACKED);
        hookBuilder.addModule(new ArmorLevelModule(KEY_STAR_HEAL,false, TinkerTags.Items.SHIELDS));
    }

    protected static int CFG_DEATH_PREVENT_CD = 90;
    protected static float CFG_MIN_DAMAGE_FOR_STAR = 10f;
    @Override
    public float onHoldingPreventDeath(LivingEntity livingEntity, IToolStackView tool, ModifierEntry modifierEntry, EquipmentContext equipmentContext, EquipmentSlot equipmentSlot, DamageSource damageSource) {
        if (!(livingEntity instanceof Player player)) return 0;
        var toolData = tool.getPersistentData();
        if (toolData.contains(KEY_DEATH_PREVENT_CD, Tag.TAG_INT))
            return 0;
        toolData.putInt(KEY_DEATH_PREVENT_CD,CFG_DEATH_PREVENT_CD);
        livingEntity.invulnerableTime = 200;
        livingEntity.level().playSound(null,livingEntity.blockPosition(), SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS,0.5f,1.3f);
        var pos = livingEntity.position().add(0, 0.5 * livingEntity.getBbHeight(), 0);
        if (player instanceof ServerPlayer serverPlayer)
            TiAcMePacketHandler.sendToPlayer(new PParticleRingS2C(1f,0.25f,pos, ParticleTypes.FIREWORK,5f),serverPlayer);
        for (int i=0;i<=livingEntity.getMaxHealth();){
            if (RANDOM.nextInt(3) == 0) {
                i += 8;
                EffectStarEntity entity = new EffectStarEntity(livingEntity.level(), pos,
                        0.2f, TiAcMeItems.HEAL_STAR_1.get());
                livingEntity.level().addFreshEntity(entity);
            } else {
                i += 4;
                EffectStarEntity entity = new EffectStarEntity(livingEntity.level(),pos,
                        0.15f, TiAcMeItems.HEAL_STAR.get());
                livingEntity.level().addFreshEntity(entity);
            }
            if (i>256) break;
        }
        return 1;
    }

    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide&&world.getGameTime()%20==0&&isCorrectSlot) {
            var cd = tool.getPersistentData().getInt(KEY_DEATH_PREVENT_CD)-1;
            if (cd<=0) tool.getPersistentData().remove(KEY_DEATH_PREVENT_CD);
            else tool.getPersistentData().putInt(KEY_DEATH_PREVENT_CD,cd);
        }
    }

    @Override
    public Component getDisplayName(IToolStackView tool, ModifierEntry entry, @Nullable RegistryAccess access) {
        return super.getDisplayName().copy().append(" [CD: "+tool.getPersistentData().getInt(KEY_DEATH_PREVENT_CD)+"]");
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (context.getEntity().invulnerableTime==0&&
                amount>=CFG_MIN_DAMAGE_FOR_STAR&&
                !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)&&RANDOM.nextFloat()<0.25f){
            EffectStarEntity entity = new EffectStarEntity(context.getEntity().level(),
                    context.getEntity().position().add(0, 0.5 * context.getEntity().getBbHeight(), 0),
                    0.2f, TiAcMeItems.HEAL_STAR.get());
            context.getEntity().level().addFreshEntity(entity);
        }
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.1f",CFG_MIN_DAMAGE_FOR_STAR),
                CFG_DEATH_PREVENT_CD
        };
    }

    @Override
    public void refreshConfig() {
        CFG_DEATH_PREVENT_CD = TiAcMeConfig.COMMON.STAR_HEAL_DP_CD.get();
        CFG_MIN_DAMAGE_FOR_STAR = TiAcMeConfig.COMMON.STAR_HEAL_MIN_DAMAGE.get().floatValue();
    }
}
