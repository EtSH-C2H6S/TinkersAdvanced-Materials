package com.c2h6s.tinkers_advanced_materials.data;

import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;

import static com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials.*;

public class TiAcMeMateriaRecipes {
    public static void addRecipeInfos() {
        var recipeInfo = DENSIUM.addRecipeInfo().setFluidAmount(90).setTemp(1775).setIngot().build().getRecipeInfo();
        recipeInfo.addAlloyRecipes(
                AlloyRecipeBuilder.alloy(recipeInfo.getFluidOutput(), recipeInfo.getMeltTemp())
                        .addInput(TiAcMeFluids.MOLTEN_IRIDIUM.get(), 30)
                        .addInput(TinkerFluids.moltenRefinedObsidian.get(), 90)
                        .addInput(TinkerFluids.moltenOsmium.get(), 180)
        );

        recipeInfo = ANTI_NEUTRONIUM.addRecipeInfo().setFluidAmount(90).setTemp(14273).setIngot().build().getRecipeInfo();
    }
}
