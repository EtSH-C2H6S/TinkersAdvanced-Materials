package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base;

import lombok.Getter;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.VolatileDataModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.ToolDataNBT;

import java.util.Arrays;
import java.util.function.Function;

public class SlotAddingModifier extends Modifier implements VolatileDataModifierHook {
    private final Function<Integer,SlotsEntry>[] slots;

    public SlotAddingModifier(Function<Integer,SlotsEntry>... slots) {
        super();
        this.slots = slots;
    }
    public static SlotsEntry slot(SlotType type,int count){
        return new SlotsEntry(type,count);
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.VOLATILE_DATA);
    }

    @Override
    public void addVolatileData(IToolContext context, ModifierEntry modifier, ToolDataNBT volatileData) {
        if (modifier.getModifier() instanceof SlotAddingModifier slotAddingModifier){
            Arrays.stream(slotAddingModifier.slots).toList().forEach(function ->{
                var slotEntry = function.apply(modifier.getLevel());
                volatileData.addSlots(slotEntry.slotType,slotEntry.slotCount);
            });
        }
    }

    public static class SlotsEntry{
        @Getter
        private final SlotType slotType;
        @Getter
        private final int slotCount;

        public SlotsEntry(SlotType slotType, int slotCount) {
            this.slotType = slotType;
            this.slotCount = slotCount;
        }
    }
}
