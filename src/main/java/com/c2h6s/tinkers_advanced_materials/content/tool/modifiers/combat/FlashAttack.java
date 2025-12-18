package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.etstlib.tool.modifiers.base.EtSTBaseModifier;
import com.c2h6s.tinkers_advanced_materials.network.TiAcMePacketHandler;
import com.c2h6s.tinkers_advanced_materials.network.packets.PSetClientAttackStrengthS2C;
import com.c2h6s.tinkers_advanced_materials.util.CommonMeUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

@Mod.EventBusSubscriber
public class FlashAttack extends EtSTBaseModifier implements EquipmentChangeModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.EQUIPMENT_CHANGE);
    }

    @Override
    public boolean isNoLevels() {
        return true;
    }

    public static final String KEY_FLASH_ATTACK = "attack_strength_regulator";

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        var living = context.getEntity();
        if (context.getChangedSlot() == EquipmentSlot.MAINHAND&&CommonMeUtils.validateUnequipResult(tool,context.getReplacementTool()))
            living.getPersistentData().putInt(KEY_FLASH_ATTACK,living.attackStrengthTicker);
    }

    @Override
    public void onEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        var living = context.getEntity();
        if (context.getChangedSlot() == EquipmentSlot.MAINHAND&& CommonMeUtils.validateEquipResult(tool, context.getOriginalTool()))
            living.getPersistentData().putInt(KEY_FLASH_ATTACK,living.attackStrengthTicker);
    }

    @SubscribeEvent
    public static void onPlayerTickEnd(TickEvent.PlayerTickEvent event){
        if (event.phase== TickEvent.Phase.END&&event.player instanceof ServerPlayer serverPlayer){
            if (event.player.getPersistentData().contains(KEY_FLASH_ATTACK, Tag.TAG_INT)){
                event.player.attackStrengthTicker = event.player.getPersistentData().getInt(KEY_FLASH_ATTACK)+1;
                event.player.getPersistentData().remove(KEY_FLASH_ATTACK);
                TiAcMePacketHandler.sendToPlayer(new PSetClientAttackStrengthS2C(event.player.attackStrengthTicker),serverPlayer);
            }
        }
    }
}
