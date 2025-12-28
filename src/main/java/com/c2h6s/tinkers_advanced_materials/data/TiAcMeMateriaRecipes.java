package com.c2h6s.tinkers_advanced_materials.data;

import cofh.lib.init.tags.ItemTagsCoFH;
import com.LunaGlaze.rainbowcompound.Projects.Items.Basic.ItemsItemRegistry;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import com.enderio.base.common.tag.EIOTags;
import com.simibubi.create.AllItems;
import dev.epicsquid.thermalendergy.registry.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;

import java.util.function.Supplier;

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
        recipeInfo.addMetalNugget(ItemTagsCoFH.NUGGETS_SIGNALUM,null,SIGNALUM,true)
                .addMetalBlock(ItemTagsCoFH.STORAGE_BLOCKS_SIGNALUM,null,SIGNALUM,true);
        recipeInfo = LUMIUM.addRecipeInfo().setFluidAmount(90).setTemp(1050).setIngot().setItemIn(ItemTagsCoFH.INGOTS_LUMIUM,null)
                .setFluidIn(TinkerFluids.moltenLumium.getCommonTag(), TinkerFluids.moltenLumium::get)
                .setLeftover(ItemTagsCoFH.NUGGETS_LUMIUM,null).build().getRecipeInfo();
        recipeInfo.addMetalNugget(ItemTagsCoFH.NUGGETS_LUMIUM,null,LUMIUM,true)
                .addMetalBlock(ItemTagsCoFH.STORAGE_BLOCKS_LUMIUM,null,LUMIUM,true);
        recipeInfo = ENDERIUM.addRecipeInfo().setFluidAmount(90).setTemp(1350).setIngot().setItemIn(ItemTagsCoFH.INGOTS_ENDERIUM,null)
                .setFluidIn(TinkerFluids.moltenEnderium.getCommonTag(), TinkerFluids.moltenEnderium::get)
                .setLeftover(ItemTagsCoFH.NUGGETS_ENDERIUM,null).build().getRecipeInfo();
        recipeInfo.addMetalNugget(ItemTagsCoFH.NUGGETS_ENDERIUM,null,ENDERIUM,true)
                .addMetalBlock(ItemTagsCoFH.STORAGE_BLOCKS_ENDERIUM,null,ENDERIUM,true);
        recipeInfo = PRISMALIC_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(1250).setIngot()
                .setFluidIn(PRISMALIC_ALLOY.getFluidObject().getCommonTag(), (Supplier<Fluid>) null)
                .setItemIn(null,ItemRegistry.INSTANCE.getPrismaliumIngot())
                .setLeftover(null, ForgeRegistries.ITEMS.
                        getValue(new ResourceLocation("thermalendergy:prismalium_nugget"))).
                build().getRecipeInfo();
        recipeInfo.addMetalNugget(null,()->
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalendergy:prismalium_nugget")),
                        PRISMALIC_ALLOY,false)
                .addMetalBlock(null,ItemRegistry.INSTANCE::getPrismaliumBlock,PRISMALIC_ALLOY,false);

        recipeInfo = MELODIC_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(1500).setIngot()
                .setFluidIn(MELODIC_ALLOY.getFluidObject().getCommonTag(),(Supplier<Fluid>) null)
                .setItemIn(null,ItemRegistry.INSTANCE.getMelodiumIngot())
                .setLeftover(null, ForgeRegistries.ITEMS.
                        getValue(new ResourceLocation("thermalendergy:melodium_nugget"))).
                build().getRecipeInfo();
        recipeInfo.addMetalNugget(null,()->
                        ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalendergy:melodium_nugget")),
                        MELODIC_ALLOY,false)
                .addMetalBlock(null, ItemRegistry.INSTANCE::getMelodiumBlock,MELODIC_ALLOY,false)
                .addGeneralRecipes("melodium");

        recipeInfo = STELLAR_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(2000).setIngot()
                .setFluidIn(STELLAR_ALLOY.getFluidObject().getCommonTag(), (Supplier<Fluid>) null)
                .setItemIn(null,ItemRegistry.INSTANCE.getStellariumIngot())
                .setLeftover(null, ForgeRegistries.ITEMS.
                        getValue(new ResourceLocation("thermalendergy:stellarium_nugget"))).
                build().getRecipeInfo();
        recipeInfo.addMetalNugget(null,()->
                                ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermalendergy:stellarium_nugget")),
                        STELLAR_ALLOY,false)
                .addMetalBlock(null, ItemRegistry.INSTANCE::getStellariumBlock,STELLAR_ALLOY,false)
                .addGeneralRecipes("stellarium");

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

        recipeInfo = COPPER_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(650).setIngot()
                .setFluidIn(COPPER_ALLOY.getFluidObject().getCommonTag(), (Supplier<Fluid>) null)
                .setItemIn(EIOTags.Items.INGOTS_COPPER_ALLOY, null)
                .setLeftover(EIOTags.Items.NUGGETS_COPPER_ALLOY, null)
                .build().getRecipeInfo();
        recipeInfo.addMetalNugget(EIOTags.Items.NUGGETS_COPPER_ALLOY,null,COPPER_ALLOY,false)
                .addMetalBlock(EIOTags.Items.BLOCKS_COPPER_ALLOY, null,COPPER_ALLOY,false);

        recipeInfo = REDSTONE_ALLOY.addRecipeInfo().setFluidAmount(90).setTemp(600).setIngot()
                .setFluidIn(REDSTONE_ALLOY.getFluidObject().getCommonTag(), (Supplier<Fluid>) null)
                .setItemIn(EIOTags.Items.INGOTS_REDSTONE_ALLOY, null)
                .setLeftover(EIOTags.Items.NUGGETS_REDSTONE_ALLOY,null)
                .build().getRecipeInfo();
        recipeInfo.addMetalNugget(EIOTags.Items.NUGGETS_REDSTONE_ALLOY,null,REDSTONE_ALLOY,false)
                .addMetalBlock(EIOTags.Items.BLOCKS_REDSTONE_ALLOY, null,REDSTONE_ALLOY,false);
    }
}
