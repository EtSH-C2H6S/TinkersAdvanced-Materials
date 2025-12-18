package com.c2h6s.tinkers_advanced_materials.data;

import cofh.lib.init.tags.ItemTagsCoFH;
import com.LunaGlaze.rainbowcompound.Projects.Items.Basic.ItemsItemRegistry;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import com.simibubi.create.AllItems;
import dev.epicsquid.thermalendergy.registry.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
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

        ANTI_NEUTRONIUM.addRecipeInfo().setFluidAmount(90).setTemp(14273).setIngot().build();
        recipeInfo = SIGNALUM.addRecipeInfo().setFluidAmount(90).setTemp(999).setIngot().setItemIn(ItemTagsCoFH.INGOTS_SIGNALUM,null)
                .setFluidIn(TinkerFluids.moltenSignalum.getCommonTag(), TinkerFluids.moltenSignalum::get)
                .setLeftover(ItemTagsCoFH.NUGGETS_SIGNALUM,null).build().getRecipeInfo();
        recipeInfo.addMetalNugget(ItemTagsCoFH.NUGGETS_SIGNALUM,null,SIGNALUM)
                .addMetalBlock(ItemTagsCoFH.STORAGE_BLOCKS_SIGNALUM,null,SIGNALUM);
        recipeInfo = LUMIUM.addRecipeInfo().setFluidAmount(90).setTemp(1050).setIngot().setItemIn(ItemTagsCoFH.INGOTS_LUMIUM,null)
                .setFluidIn(TinkerFluids.moltenLumium.getCommonTag(), TinkerFluids.moltenLumium::get)
                .setLeftover(ItemTagsCoFH.NUGGETS_LUMIUM,null).build().getRecipeInfo();
        recipeInfo.addMetalNugget(ItemTagsCoFH.NUGGETS_LUMIUM,null,LUMIUM)
                .addMetalBlock(ItemTagsCoFH.STORAGE_BLOCKS_LUMIUM,null,LUMIUM);
        recipeInfo = ENDERIUM.addRecipeInfo().setFluidAmount(90).setTemp(1350).setIngot().setItemIn(ItemTagsCoFH.INGOTS_ENDERIUM,null)
                .setFluidIn(TinkerFluids.moltenEnderium.getCommonTag(), TinkerFluids.moltenEnderium::get)
                .setLeftover(ItemTagsCoFH.NUGGETS_ENDERIUM,null).build().getRecipeInfo();
        recipeInfo.addMetalNugget(ItemTagsCoFH.NUGGETS_ENDERIUM,null,ENDERIUM)
                .addMetalBlock(ItemTagsCoFH.STORAGE_BLOCKS_ENDERIUM,null,ENDERIUM);
        recipeInfo = PRISMALIC_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(1250).setIngot()
                .setItemIn(null,ItemRegistry.INSTANCE.getPrismaliumIngot())
                .setLeftover(null, ForgeRegistries.ITEMS.
                        getValue(new ResourceLocation("thermalendergy:prismalium_nugget"))).
                build().getRecipeInfo();
        recipeInfo.addMetalNugget(null,()->
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalendergy:prismalium_nugget")),
                        PRISMALIC_ALLOY)
                .addMetalBlock(null,ItemRegistry.INSTANCE::getPrismaliumBlock,PRISMALIC_ALLOY);

        recipeInfo = MELODIC_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(1500).setIngot()
                .setItemIn(null,ItemRegistry.INSTANCE.getMelodiumIngot())
                .setLeftover(null, ForgeRegistries.ITEMS.
                        getValue(new ResourceLocation("thermalendergy:melodium_nugget"))).
                build().getRecipeInfo();
        recipeInfo.addMetalNugget(null,()->
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalendergy:melodium_nugget")),
                        MELODIC_ALLOY)
                .addMetalBlock(null, ItemRegistry.INSTANCE::getMelodiumBlock,MELODIC_ALLOY);

        recipeInfo = STELLAR_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(2000).setIngot()
                .setItemIn(null,ItemRegistry.INSTANCE.getStellariumIngot())
                .setLeftover(null, ForgeRegistries.ITEMS.
                        getValue(new ResourceLocation("thermalendergy:stellarium_nugget"))).
                build().getRecipeInfo();
        recipeInfo.addMetalNugget(null,()->
                                ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalendergy:stellarium_nugget")),
                        STELLAR_ALLOY)
                .addMetalBlock(null, ItemRegistry.INSTANCE::getStellariumBlock,STELLAR_ALLOY);

        FROSTITE.addRecipeInfo().setFluidAmount(90).setTemp(550).setIngot()
                .setItemIn(null,ItemsItemRegistry.frostiteingot.get()).build();
        ENDERITE.addRecipeInfo().setFluidAmount(90).setTemp(1000).setIngot()
                .setItemIn(null,ItemsItemRegistry.enderiteingot.get()).build();
        BLAZEITE.addRecipeInfo().setFluidAmount(90).setTemp(1200).setIngot()
                .setItemIn(null,ItemsItemRegistry.blazeiteingot.get()).build();
        GLOWSTONEITE.addRecipeInfo().setFluidAmount(90).setTemp(960).setIngot()
                .setItemIn(null,ItemsItemRegistry.glowstoneiteingot.get()).build();
        NETHERWARTITE.addRecipeInfo().setItemIn(null,ItemsItemRegistry.netherwartiteingot.get()).build();
        WARPEDITE.addRecipeInfo().setItemIn(null,ItemsItemRegistry.warpediteingot.get()).build();
        SLIMEITE.addRecipeInfo().setFluidAmount(90).setTemp(100).setIngot()
                .setItemIn(null,ItemsItemRegistry.slimeiteingot.get()).build();
        CHORUSITE.addRecipeInfo().setFluidAmount(90).setTemp(1500).setIngot()
                .setItemIn(null,ItemsItemRegistry.chorusiteingot.get()).build();
        REFINED_RADIANCE.addRecipeInfo().setFluidAmount(90).setTemp(1750).setIngot()
                .setItemIn(null, AllItems.REFINED_RADIANCE).build();
        SHADOW_STEEL.addRecipeInfo().setFluidAmount(90).setTemp(1750).setIngot()
                .setItemIn(null,AllItems.SHADOW_STEEL).build();
        NETHERSTAR_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(2000).setIngot()
                .setItemIn(null,ItemsItemRegistry.netherstaringot.get()).build();


    }
}
