package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.eio;

import com.c2h6s.etstlib.register.EtSTLibToolStat;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.enderio.api.capacitor.CapacitorModifier;
import com.enderio.api.capacitor.ICapacitorData;
import com.enderio.base.common.capacitor.CapacitorUtil;
import com.enderio.base.common.init.EIOItems;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.SlotStackModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.ToolEnergyCapability;
import slimeknights.tconstruct.library.tools.capability.fluid.ToolTankHelper;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.INumericToolStat;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.*;

public class CapacitorCapable extends MultiArgsDescModifier implements SlotStackModifierHook, ToolStatsModifierHook , TooltipModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.SLOT_STACK,ModifierHooks.TOOL_STATS,ModifierHooks.TOOLTIP);
    }

    public static ResourceLocation KEY_CAPACITOR = TinkersAdvanced.getLocation("eio_capacitor");

    public static Multimap<CapacitorModifier, INumericToolStat<?>> CFG_CAPACITOR_MAP = ArrayListMultimap.create();

    protected static Map<CapacitorModifier,Float> CFG_CAPACITOR_MUL;
    static {
        CFG_CAPACITOR_MUL = new HashMap<>();
        CFG_CAPACITOR_MUL.put(CapacitorModifier.FUEL_EFFICIENCY,0.1f);
        CFG_CAPACITOR_MUL.put(CapacitorModifier.BURNING_ENERGY_GENERATION,1f);
        CFG_CAPACITOR_MUL.put(CapacitorModifier.ENERGY_USE,0.1f);
        CFG_CAPACITOR_MUL.put(CapacitorModifier.ENERGY_CAPACITY,1f);
        CFG_CAPACITOR_MAP.putAll(CapacitorModifier.ENERGY_CAPACITY,List.of(ToolEnergyCapability.MAX_STAT, ToolTankHelper.CAPACITY_STAT,
                EtSTLibToolStat.BASIC_AIR_CAPACITY,EtSTLibToolStat.MAX_PRESSURE));
        CFG_CAPACITOR_MAP.putAll(CapacitorModifier.ENERGY_USE, List.of(ToolStats.ARMOR,ToolStats.ARMOR_TOUGHNESS,ToolStats.ATTACK_DAMAGE,
                        ToolStats.ATTACK_SPEED,ToolStats.ACCURACY,ToolStats.VELOCITY,ToolStats.DRAW_SPEED,ToolStats.DURABILITY));
        CFG_CAPACITOR_MAP.putAll(CapacitorModifier.BURNING_ENERGY_GENERATION, List.of(EtSTLibToolStat.POWER_MULTIPLIER,EtSTLibToolStat.SCALE));
        CFG_CAPACITOR_MAP.putAll(CapacitorModifier.FUEL_EFFICIENCY,List.of(EtSTLibToolStat.FLUID_EFFICIENCY));
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[0];
    }

    @Override
    public void refreshConfig() {
        CFG_CAPACITOR_MUL.put(CapacitorModifier.FUEL_EFFICIENCY, TiAcMeConfig.COMMON.CAPACITOR_CAPABLE_EFFICIENCY_STAT_MUL.get().floatValue());
        CFG_CAPACITOR_MUL.put(CapacitorModifier.BURNING_ENERGY_GENERATION, TiAcMeConfig.COMMON.CAPACITOR_CAPABLE_POWER_STATS_MUL.get().floatValue());
        CFG_CAPACITOR_MUL.put(CapacitorModifier.ENERGY_CAPACITY, TiAcMeConfig.COMMON.CAPACITOR_CAPABLE_CAPACITY_STATS_MUL.get().floatValue());
        CFG_CAPACITOR_MUL.put(CapacitorModifier.ENERGY_USE, TiAcMeConfig.COMMON.CAPACITOR_CAPABLE_GENERAL_STATS_MUL.get().floatValue());
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        readCapacitorData(context).ifPresent(capData->{
            CFG_CAPACITOR_MAP.asMap().forEach((cap,stats)->
                    stats.forEach(stat->
                            stat.percent(builder,CFG_CAPACITOR_MUL.getOrDefault(cap,0f)*
                                    capData.getModifier(cap)*modifier.getLevel())));
        });
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        var opt = readCapacitorData(tool);
        opt.ifPresent(capacitorDat ->
                CFG_CAPACITOR_MUL.forEach((capacitorModifier, mul) ->
                        tooltip.add(Component.literal("+" + String.format("%.0f", mul * capacitorDat.getModifier(capacitorModifier)*100*modifier.getLevel())+"% ")
                                .append(Component.translatable("tooltip.tinkers_advanced.modifiers.capacitor_capable." + capacitorModifier.name().toLowerCase(Locale.ROOT)))
                                .withStyle(getDisplayName().getStyle()))));
    }

    @Override
    public boolean overrideOtherStackedOnMe(IToolStackView slotTool, ModifierEntry modifier, ItemStack held, Slot slot, Player player, SlotAccess access) {
        if (held.isEmpty()) return false;
        if (CapacitorUtil.isCapacitor(held)){
            if (!player.level().isClientSide) {
                replaceCapacitor(slotTool, new ItemStack(held.getItem()), player);
                held.shrink(1);
            }
            return true;
        } else if (held.is(EIOItems.YETA_WRENCH.get())) {
            if (!player.level().isClientSide)
                replaceCapacitor(slotTool,ItemStack.EMPTY,player);
            return true;
        }
        return false;
    }

    public static Optional<ICapacitorData> readCapacitorData(IToolContext context){
        return CapacitorUtil.getCapacitorData(readCapacitor(context));
    }
    public static ItemStack readCapacitor(IToolContext context){
        return context.getPersistentData().contains(KEY_CAPACITOR, Tag.TAG_COMPOUND) ?
                ItemStack.of(context.getPersistentData().getCompound(KEY_CAPACITOR)) : ItemStack.EMPTY;
    }

    public static void replaceCapacitor(IToolStackView tool, ItemStack stack,Player player){
        var originalStack = readCapacitor(tool);
        setCapacitor(tool,stack);
        if (!originalStack.isEmpty()){
            if (!player.getInventory().add(originalStack)) player.drop(originalStack,false);
        }
    }

    public static void setCapacitor(IToolStackView tool, ItemStack stack){
        if (stack.isEmpty()) tool.getPersistentData().remove(KEY_CAPACITOR);
        else {
            var tag = stack.serializeNBT();
            tool.getPersistentData().put(KEY_CAPACITOR,tag);
        }
        ((ToolStack) tool).rebuildStats();
    }
}
