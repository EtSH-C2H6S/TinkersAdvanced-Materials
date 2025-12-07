package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.data.condition.CompatConfigCondition;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierSlotModule;
import slimeknights.tconstruct.library.tools.SlotType;

import static com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider.ModifierIds.*;
public class TiAcMeModifierProvider extends AbstractModifierProvider {
    public TiAcMeModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        buildModifier(THERMAL_FOUNDATION,modLoaded("thermal",true))
                .addModule(ModifierSlotModule.slot(SlotType.UPGRADE).amount(0,1)).build();
        buildModifier(BASALZ_ENHANCE,modLoaded("thermal",true))
                .addModule(ModifierSlotModule.slot(SlotType.DEFENSE).amount(0,1)).build();
        buildModifier(BLITZ_ENHANCE,modLoaded("thermal",true))
                .addModule(ModifierSlotModule.slot(SlotType.UPGRADE).amount(0,1)).build();
        buildModifier(BLIZZ_ENHANCE,modLoaded("thermal",true))
                .addModule(ModifierSlotModule.slot(SlotType.ABILITY).amount(0,1)).build();
    }
    public static ICondition modLoaded(String modId, boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(modId,isOriginal),new ModLoadedCondition(modId));
    }

    @Override
    public String getName() {
        return "TiAcMe Modifier Provider";
    }

    public static class ModifierIds{
        public static final ModifierId THERMAL_FOUNDATION = new ModifierId(TinkersAdvanced.getLocation("thermal_foundation"));
        public static final ModifierId BASALZ_ENHANCE = new ModifierId(TinkersAdvanced.getLocation("basalz_enhance"));
        public static final ModifierId BLITZ_ENHANCE = new ModifierId(TinkersAdvanced.getLocation("blitz_enhance"));
        public static final ModifierId BLIZZ_ENHANCE = new ModifierId(TinkersAdvanced.getLocation("blizz_enhance"));
    }
}
