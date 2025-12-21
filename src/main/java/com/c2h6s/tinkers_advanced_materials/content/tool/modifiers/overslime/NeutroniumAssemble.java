package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.overslime;

import com.c2h6s.etstlib.register.EtSTLibHooks;
import com.c2h6s.etstlib.tool.hooks.CustomBarDisplayModifierHook;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import com.c2h6s.tinkers_advanced_materials.util.CommonMeUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.SlotStackModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

import static com.c2h6s.tinkers_advanced_materials.TiAcMeConfig.COMMON;

public class NeutroniumAssemble extends MultiArgsDescModifier implements SlotStackModifierHook, CustomBarDisplayModifierHook, TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.SLOT_STACK, EtSTLibHooks.CUSTOM_BAR,ModifierHooks.TOOLTIP);
    }

    @Override
    public boolean isNoLevels() {
        return true;
    }

    protected static int CFG_CHARGE_PER_ANTI_NEUTRONIUM = 8000;
    protected static int CFG_OVERSLIME_PER_ANTI_NEUTRONIUM = 10;

    public static final ResourceLocation KEY_ANTI_NEUTRONIUM = TinkersAdvanced.getLocation("anti_neutronium");

    @Override
    public boolean overrideOtherStackedOnMe(IToolStackView slotTool, ModifierEntry modifier, ItemStack held, Slot slot, Player player, SlotAccess access) {
        if (held.is(TiAcMeMaterials.ANTI_NEUTRONIUM.getItemObject().get())&&slot.allowModification(player)){
            if (!player.level().isClientSide){
                var toolData = slotTool.getPersistentData();
                var max = 128000*modifier.getLevel();
                var cur = toolData.getInt(KEY_ANTI_NEUTRONIUM);
                if (max-cur>=CFG_CHARGE_PER_ANTI_NEUTRONIUM){
                    int count = (max-cur)/CFG_CHARGE_PER_ANTI_NEUTRONIUM;
                    count = Math.min(held.getCount(),count);
                    held.shrink(count);
                    cur+=count*CFG_CHARGE_PER_ANTI_NEUTRONIUM;
                    toolData.putInt(KEY_ANTI_NEUTRONIUM,cur);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void modifierOnInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (isCorrectSlot && world.getGameTime()%10==0) consumeAntiNeutronium(tool);
    }

    public static boolean consumeAntiNeutronium(IToolStackView tool){
        var OSModule = OverslimeModule.INSTANCE;
        var max = OverslimeModule.getCapacity(tool);
        var cur = OSModule.getAmount(tool);
        var toolData = tool.getPersistentData();
        var anti = toolData.getInt(KEY_ANTI_NEUTRONIUM);
        if (max-cur>=CFG_OVERSLIME_PER_ANTI_NEUTRONIUM){
            int count = (max-cur)/CFG_OVERSLIME_PER_ANTI_NEUTRONIUM;
            count = Math.min(anti,count);
            anti-=count;
            OSModule.addAmount(tool,count*CFG_OVERSLIME_PER_ANTI_NEUTRONIUM);
            toolData.putInt(KEY_ANTI_NEUTRONIUM,anti);
            return true;
        }
        return false;
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{CFG_CHARGE_PER_ANTI_NEUTRONIUM,CFG_OVERSLIME_PER_ANTI_NEUTRONIUM};
    }

    @Override
    public void refreshConfig() {
        CFG_CHARGE_PER_ANTI_NEUTRONIUM = COMMON.CHARGE_PER_ANTI_NEUTRONIUM.get();
        CFG_OVERSLIME_PER_ANTI_NEUTRONIUM = COMMON.OVERSLIME_PER_ANTI_NEUTRONIUM.get();
    }

    @Override
    public int modifierDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {
        return 0;
    }

    @Override
    public String barId(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return "anti_neutronium";
    }

    @Override
    public boolean showBar(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return iToolStackView.getPersistentData().getInt(KEY_ANTI_NEUTRONIUM)>0;
    }

    @Override
    public Vec2 getBarXYSize(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return new Vec2(1,-CommonMeUtils.getBarLength(iToolStackView.getPersistentData().getInt(KEY_ANTI_NEUTRONIUM),128000));
    }

    @Override
    public boolean isCustomPosition(IToolStackView tool, ModifierEntry entry, int barsHadBeenShown) {
        return true;
    }
    @Override
    public int getBarRGB(IToolStackView iToolStackView, ModifierEntry modifierEntry, int i) {
        return 0xFFFF0000;
    }

    @Override
    public Vec2 getBarXYPos(IToolStackView tool, ModifierEntry entry, int barsHadBeenShown) {
        return new Vec2(1,13);
    }

    @Override
    public Vec2 getShadowXYSize(IToolStackView tool, ModifierEntry entry, int barsHadBeenShown) {
        return new Vec2(1,-13);
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        tooltip.add(Component.translatable("material.tinkers_advanced.anti_neutronium")
                .append(": "+tool.getPersistentData().getInt(KEY_ANTI_NEUTRONIUM))
                .withStyle(style -> style.withColor(0x850000)));
    }
}
