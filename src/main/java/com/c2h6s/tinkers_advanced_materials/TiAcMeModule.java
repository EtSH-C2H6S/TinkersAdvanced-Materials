package com.c2h6s.tinkers_advanced_materials;

import com.c2h6s.etstlib.util.ModListConstants;
import com.c2h6s.tinkers_advanced_materials.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class TiAcMeModule {
    public static void register(IEventBus modEventBus){
        TiAcMePlacementModifier.PLACEMENT_MODIFIER.register(modEventBus);
        TiAcMeMaterials.MATERIALS.register(modEventBus);
        if (ModListConstants.MekLoaded){
            TiAcMeItems.MEK_ITEMS.register(modEventBus);
            TiAcMeFluids.MEK_FLUIDS.register(modEventBus);
            TiAcMeModifiers.MEK_MODIFIERS.register(modEventBus);
        }
        if (ModListConstants.PnCLoaded){
            TiAcMeItems.PNC_ITEMS.register(modEventBus);
            TiAcMeModifiers.PNC_MODIFIERS.register(modEventBus);
        }
        if (ModList.get().isLoaded("thermal")){
            TiAcMeItems.THERMAL_ITEMS.register(modEventBus);
            TiAcMeFluids.THERMAL_FLUIDS.register(modEventBus);
            TiAcMeModifiers.THERMAL_MODIFIERS.register(modEventBus);
            TiAcMeEntities.THERMAL_ENTITIES.register(modEventBus);
        }
        if (ModListConstants.AE2Loaded){
            TiAcMeModifiers.AE_MODIFIERS.register(modEventBus);
        }
        if (ModList.get().isLoaded("industrialforegoing")){
            TiAcMeFluids.IF_FLUIDS.register(modEventBus);
            TiAcMeItems.IF_ITEMS.register(modEventBus);
        }
        if (ModList.get().isLoaded("createutilities")){
            TiAcMeFluids.CREATE_UTILITIES_FLUIDS.register(modEventBus);
        }
    }
}
