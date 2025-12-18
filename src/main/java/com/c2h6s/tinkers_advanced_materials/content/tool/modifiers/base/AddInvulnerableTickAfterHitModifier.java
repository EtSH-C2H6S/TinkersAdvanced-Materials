package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class AddInvulnerableTickAfterHitModifier extends MultiArgsDescModifier implements ModifyDamageModifierHook {
    protected final Supplier<Integer> TICKS_TO_ADD;

    public AddInvulnerableTickAfterHitModifier(Supplier<Integer> ticksToAdd) {
        super();
        TICKS_TO_ADD = ticksToAdd;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MODIFY_HURT);
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
               String.format("%.1f", TICKS_TO_ADD.get()/20f)
        };
    }

    @Override
    public void refreshConfig() {

    }

    @Override
    public List<Component> getDescriptionList() {
        if (descriptionList == null) {
            descriptionList = Arrays.asList(
                    Component.translatable(getTranslationKey() + ".flavor").withStyle(ChatFormatting.ITALIC),
                    Component.translatable("modifier.tinkers_advanced.add_invulnerable_time.description",
                            getDescArgs()).withStyle(ChatFormatting.GRAY));
        }
        return descriptionList;
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        DelayInvulnerableTimeHandler.addLiving(context.getEntity(),modifier.getLevel()*TICKS_TO_ADD.get());
        return amount;
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DelayInvulnerableTimeHandler{
        private static final Map<LivingEntity,Integer> TO_ADD_MAP = new ConcurrentHashMap<>();
        @SubscribeEvent
        public static void onServerTick(TickEvent.ServerTickEvent event){
            TO_ADD_MAP.forEach((living, integer) -> {
                if (living.isAlive()) living.invulnerableTime+=integer;
            });
            TO_ADD_MAP.clear();
        }
        public static void addLiving(LivingEntity living,int ticks){
            TO_ADD_MAP.put(living,ticks);
        }
    }
}
