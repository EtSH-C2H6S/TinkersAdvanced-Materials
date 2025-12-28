package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import appeng.datagen.providers.tags.ConventionTags;
import cofh.lib.init.tags.ItemTagsCoFH;
import com.LunaGlaze.rainbowcompound.Projects.Items.Basic.ItemsItemRegistry;
import com.buuz135.industrial.module.ModuleCore;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.data.condition.CompatConfigCondition;
import com.c2h6s.tinkers_advanced.core.data.condition.GeneralMaterialConfigCondition;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeMaterialIds;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeTagkeys;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeModifiers;
import com.c2h6s.tinkers_advanced_tools.init.TiAcTItems;
import me.desht.pneumaticcraft.api.data.PneumaticCraftTags;
import mekanism.api.datagen.recipe.builder.CombinerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.NucleosynthesizingRecipeBuilder;
import mekanism.common.recipe.ingredient.creator.GasStackIngredientCreator;
import mekanism.common.recipe.ingredient.creator.ItemStackIngredientCreator;
import mekanism.common.registries.MekanismFluids;
import mekanism.common.registries.MekanismGases;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import mekanism.common.tags.MekanismTags;
import mekanism.generators.common.registries.GeneratorsFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.recipe.condition.TagFilledCondition;
import slimeknights.mantle.recipe.helper.FluidOutput;
import slimeknights.mantle.recipe.helper.ItemOutput;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.material.MaterialFluidRecipeBuilder;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelBuilder;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.IMeltingRecipe;
import slimeknights.tconstruct.library.recipe.melting.MaterialMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerMaterials;

import java.util.function.Consumer;

import static com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider.ModifierIds.*;

public class TiAcMeRecipeProvider extends RecipeProvider implements ISmelteryRecipeHelper {
    public TiAcMeRecipeProvider(PackOutput generator) {
        super(generator);
    }
    public static final TagKey<Item> GEM_MULTICAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/multi_use/gem"));
    public static final TagKey<Item> GEM_SINGLECAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/single_use/gem"));
    public static final TagKey<Item> INGOT_MULTICAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/multi_use/ingot"));
    public static final TagKey<Item> INGOT_SINGLECAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/single_use/ingot"));
    public static final TagKey<Item> PLATE_MULTICAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/multi_use/plate"));
    public static final TagKey<Item> PLATE_SINGLECAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/single_use/plate"));
    public static final TagKey<Item> NUGGET_MULTICAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/multi_use/nugget"));
    public static final TagKey<Item> NUGGET_SINGLECAST = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), TConstruct.getResource("casts/single_use/nugget"));

    public static final ResourceLocation baseFolder = new ResourceLocation(TinkersAdvanced.MODID,"materials/");
    public static ResourceLocation namedFolder(String name){
        return ResourceLocation.tryParse(baseFolder+name+"/"+name);
    }
    public static ResourceLocation modifierFolder(String name){
        return ResourceLocation.tryParse(TinkersAdvanced.getLocation("modifiers/")+name);
    }
    public static ResourceLocation modifierFolder(ModifierId modifierId){
        return modifierFolder(modifierId.getPath());
    }
    public static ResourceLocation salvageFolder(String name){
        return ResourceLocation.tryParse(TinkersAdvanced.getLocation("modifiers/salvage/")+name);
    }
    public static ResourceLocation salvageFolder(ModifierId modifierId){
        return salvageFolder(modifierId.getPath());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        TiAcMeMaterials.MATERIALS.getEntryMap().values()
                .forEach(object -> object.assembleRecipes(consumer,this));
        ResourceLocation folder;
        Consumer<FinishedRecipe> conditional;
        folder = namedFolder("fuel");
        fuel("over_heated_lava", FluidIngredient.of(TiAcMeFluids.OVER_HEATED_LAVA.get(), 100), 1000, 2000, consumer);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.OVER_HEATED_LAVA.get(), 500)), 1500)
                .addCatalyst(FluidIngredient.of(TinkerFluids.moltenCinderslime.get(), 270))
                .addInput(FluidIngredient.of(TinkerFluids.blazingBlood.get(), 500))
                .addInput(Fluids.LAVA, 1000)
                .save(consumer, new ResourceLocation(folder + "_over_heated_lava"));
        fuel("gaseous_lava", FluidIngredient.of(TiAcMeFluids.GASEOUS_LAVA.get(), 10), 1000, 4000, consumer);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.GASEOUS_LAVA.get(), 500)), 2000)
                .addCatalyst(FluidIngredient.of(TinkerFluids.moltenManyullyn.get(), 360))
                .addInput(FluidIngredient.of(TiAcMeFluids.MOLTEN_BLAZE_NETHERITE.get(), 10))
                .addInput(FluidIngredient.of(TinkerFluids.honey.get(), 500))
                .addInput(TiAcMeFluids.OVER_HEATED_LAVA.get(), 1000)
                .save(consumer, new ResourceLocation(folder + "_gaseous_lava"));
        fuel("plasmatic_lava", FluidIngredient.of(TiAcMeFluids.PLASMATIC_LAVA.get(), 10), 10000, 8000, consumer);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.PLASMATIC_LAVA.get(), 500)), 3000)
                .addCatalyst(FluidIngredient.of(TiAcMeFluids.MOLTEN_IRIDIUM.get(), 450))
                .addInput(FluidIngredient.of(TinkerFluids.enderSlime.get(), 250))
                .addInput(FluidIngredient.of(TinkerFluids.moltenEnder.get(), 750))
                .addInput(TiAcMeFluids.GASEOUS_LAVA.get(), 1000)
                .save(consumer, new ResourceLocation(folder + "_plasmatic_lava"));
        conditional = withCondition(consumer, modLoaded("mekanism",false));
        fuel("antimatter", FluidIngredient.of(TiAcMeFluids.MOLTEN_ANTIMATTER.get(), 10), 50000, 32768, conditional);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.OVER_HEATED_LAVA.get(), 500)), 1500)
                .addCatalyst(FluidIngredient.of(TinkerFluids.moltenRefinedGlowstone.get(), 90))
                .addInput(MekanismFluids.ETHENE.getFluid(), 250)
                .addInput(MekanismFluids.OXYGEN.getFluid(), 750)
                .addInput(Fluids.LAVA, 1000)
                .save(conditional, new ResourceLocation(folder + "_over_heated_lava_mek"));
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.GASEOUS_LAVA.get(), 500)), 2000)
                .addCatalyst(FluidIngredient.of(TinkerFluids.moltenRefinedObsidian.get(), 270))
                .addInput(MekanismFluids.URANIUM_HEXAFLUORIDE.getFluid(), 250)
                .addInput(MekanismFluids.HEAVY_WATER.getFluid(), 500)
                .addInput(TiAcMeFluids.OVER_HEATED_LAVA.get(), 1000)
                .save(conditional, new ResourceLocation(folder + "_gaseous_lava_mek"));
        conditional = withCondition(consumer, modLoaded("mekanismgenerators",true));
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.PLASMATIC_LAVA.get(), 500)), 3000)
                .addCatalyst(FluidIngredient.of(TiAcMeFluids.MOLTEN_IRRADIUM.get(), 270))
                .addInput(GeneratorsFluids.DEUTERIUM.getFluid(), 500)
                .addInput(GeneratorsFluids.TRITIUM.getFluid(), 500)
                .addInput(TiAcMeFluids.GASEOUS_LAVA.get(), 1000)
                .save(conditional, new ResourceLocation(folder + "_plasmatic_lava_mek"));
        conditional = withCondition(consumer, modLoaded("thermal",true));
        fuel("pyrotheum", FluidIngredient.of(TiAcMeFluids.PYROTHEUM.get(), 10), 500, 3000, conditional);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.PYROTHEUM.get(), 500)), 1320)
                .addInput(FluidIngredient.of(TinkerFluids.blazingBlood.get(), 1250))
                .addInput(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("thermal", "refined_fuel")), 1000)
                .addInput(ForgeRegistries.FLUIDS.getValue(new ResourceLocation("thermal", "creosote")), 750)
                .save(conditional, new ResourceLocation(folder + "_pyrotheum"));


        folder = namedFolder("bismuth");
        meltMaterial(TiAcMeTagkeys.Fluids.MOLTEN_BISMUTH, 90, TiAcMeMaterialIds.BISMUTH, 770, consumer, folder);
        melt1Ingot(TiAcMeTagkeys.Fluids.MOLTEN_BISMUTH, TiAcMeTagkeys.Items.BISMUTH_INGOT, 770, consumer, folder);
        materialRecipe(TiAcMeMaterialIds.BISMUTH, Ingredient.of(TiAcMeTagkeys.Items.BISMUTH_INGOT), 1, 1, consumer, folder);
        tableNugget(TiAcMeItems.BISMUTH_INGOT.get(), TiAcMeItems.BISMUTH_NUGGET.get(), Ingredient.of(TiAcMeTagkeys.Items.BISMUTH_INGOT), Ingredient.of(TiAcMeTagkeys.Items.BISMUTH_NUGGET), consumer, folder);
        melt1Nugget(TiAcMeTagkeys.Fluids.MOLTEN_BISMUTH, TiAcMeTagkeys.Items.BISMUTH_NUGGET, 770, consumer, folder);
        folder = namedFolder("bismuthinite");
        materialRecipe(TiAcMeMaterialIds.BISMUTHINITE, Ingredient.of(TiAcMeItems.BISMUTHINITE.get()), 1, 1, consumer, folder);
        folder = namedFolder("antimony");
        meltMaterial(TiAcMeTagkeys.Fluids.MOLTEN_ANTIMONY, 90, TiAcMeMaterialIds.ANTIMONY, 970, consumer, folder);
        melt1Ingot(TiAcMeTagkeys.Fluids.MOLTEN_ANTIMONY, TiAcMeTagkeys.Items.ANTIMONY_INGOT, 970, consumer, folder);
        materialRecipe(TiAcMeMaterialIds.ANTIMONY, Ingredient.of(TiAcMeTagkeys.Items.ANTIMONY_INGOT), 1, 1, consumer, folder);
        tableNugget(TiAcMeItems.ANTIMONY_INGOT.get(), TiAcMeItems.ANTIMONY_NUGGET.get(), Ingredient.of(TiAcMeTagkeys.Items.ANTIMONY_INGOT), Ingredient.of(TiAcMeTagkeys.Items.ANTIMONY_NUGGET), consumer, folder);
        melt1Nugget(TiAcMeTagkeys.Fluids.MOLTEN_ANTIMONY, TiAcMeTagkeys.Items.ANTIMONY_NUGGET, 970, consumer, folder);
        folder = namedFolder("stibnite");
        materialRecipe(TiAcMeMaterialIds.STIBNITE, Ingredient.of(TiAcMeItems.STIBNITE.get()), 1, 1, consumer, folder);
        folder = namedFolder("disintegrate_crystal");
        materialRecipe(TiAcMeMaterialIds.DISINTEGRATE_CRYSTAL, Ingredient.of(TiAcTItems.DISINTEGRATE_CRYSTAL.get()), 1, 1, consumer, folder);
        folder = namedFolder("resonance_crystal");
        materialRecipe(TiAcMeMaterialIds.RESONANCE_CRYSTAL, Ingredient.of(TiAcTItems.RESONANCE_CRYSTAL.get()), 1, 1, consumer, folder);
        folder = namedFolder("voltaic_crystal");
        materialRecipe(TiAcMeMaterialIds.VOLTAIC_CRYSTAL, Ingredient.of(TiAcTItems.VOLTAIC_CRYSTAL.get()), 1, 1, consumer, folder);
        folder = namedFolder("blaze_netherite");
        meltMaterial(TiAcMeFluids.MOLTEN_BLAZE_NETHERITE.get(), 90, TiAcMeMaterialIds.BLAZE_NETHERITE, 1480, consumer, folder);
        melt1Ingot(TiAcMeFluids.MOLTEN_BLAZE_NETHERITE.get(), TiAcMeItems.BLAZE_NETHERITE.get(), 1480, consumer, folder);
        materialRecipe(TiAcMeMaterialIds.BLAZE_NETHERITE, Ingredient.of(TiAcMeItems.BLAZE_NETHERITE.get()), 1, 1, consumer, folder);
        ItemCastingRecipeBuilder.tableRecipe(TiAcMeItems.BLAZE_NETHERITE.get()).setFluid(TinkerFluids.blazingBlood.get(), 200).setCast(Tags.Items.INGOTS_NETHERITE, true).setCoolingTime(1500, 200).save(consumer, new ResourceLocation(folder + "_made"));
        folder = namedFolder("iridium");
        materialRecipe(TiAcMeMaterialIds.IRIDIUM, Ingredient.of(TiAcMeItems.IRIDIUM_CHUNK.get()), 3, 1, consumer, folder);
        MeltingRecipeBuilder.melting(Ingredient.of(TiAcMeItems.IRIDIUM_CHUNK.get()), FluidOutput.fromTag(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM, 30), 1375, 30).save(consumer, new ResourceLocation(folder + "_melting_chunk"));
        ItemCastingRecipeBuilder.tableRecipe(TiAcMeItems.IRIDIUM_CHUNK.get()).setCoolingTime(10).setFluid(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM, 30).save(consumer, new ResourceLocation(folder + "_casting_chunk"));
        MeltingRecipeBuilder.melting(Ingredient.of(TiAcMeItems.IRIDIUM_LEAN_ORE.get()),
                FluidOutput.fromTag(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM, 90), 1575, 30)
                .addByproduct(FluidOutput.fromTag(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM, 90))
                .addByproduct(FluidOutput.fromTag(TinkerFluids.enderSlime.getTag(), 250))
                .addByproduct(FluidOutput.fromTag(TinkerFluids.moltenEnder.getTag(), 250))
                .save(consumer, new ResourceLocation(folder + "_melting_ore"));
        meltMaterial(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM, 90, TiAcMeMaterialIds.IRIDIUM, 1375, consumer, folder);
        conditional = withCondition(consumer, tagFilled(TiAcMeTagkeys.Items.IRIDIUM_INGOT,TinkersAdvanced.MODID,true));
        melt1Ingot(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM, TiAcMeTagkeys.Items.IRIDIUM_INGOT, 1375, conditional, folder);
        //AE2
        conditional = withCondition(consumer, tagFilled(ConventionTags.FLUIX_CRYSTAL,"ae2",false));
        folder = namedFolder("fluix");
        materialRecipe(TiAcMeMaterialIds.AE2.FLUIX, Ingredient.of(ConventionTags.FLUIX_CRYSTAL), 1, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(ConventionTags.CERTUS_QUARTZ,"ae2",false));
        folder = namedFolder("certus_quartz");
        materialRecipe(TiAcMeMaterialIds.AE2.CERTUS, Ingredient.of(ConventionTags.CERTUS_QUARTZ), 1, 1, conditional, folder);
        //Mekanism
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.ALLOYS_ATOMIC,"mekanism",false));
        folder = namedFolder("alloy_atomic");
        materialRecipe(TiAcMeMaterialIds.Mekanism.ALLOY_ATOMIC, Ingredient.of(MekanismTags.Items.ALLOYS_ATOMIC), 1, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.INGOTS_REFINED_GLOWSTONE,"mekanism",false));
        folder = namedFolder("refined_glowstone");
        meltMaterial(TinkerFluids.moltenRefinedGlowstone.get(), 90, TiAcMeMaterialIds.Mekanism.REFINED_GLOWSTONE, 825, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.REFINED_GLOWSTONE, Ingredient.of(MekanismTags.Items.INGOTS_REFINED_GLOWSTONE), 1, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.NUGGETS_REFINED_GLOWSTONE,"mekanism",false));
        materialRecipe(TiAcMeMaterialIds.Mekanism.REFINED_GLOWSTONE, Ingredient.of(MekanismTags.Items.NUGGETS_REFINED_GLOWSTONE), 9, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.STORAGE_BLOCKS_REFINED_GLOWSTONE,"mekanism",false));
        materialRecipe(TiAcMeMaterialIds.Mekanism.REFINED_GLOWSTONE, Ingredient.of(MekanismTags.Items.STORAGE_BLOCKS_REFINED_GLOWSTONE), MekanismTags.Items.INGOTS_REFINED_GLOWSTONE, 1, 9, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.INGOTS_REFINED_GLOWSTONE,"mekanism",false));
        folder = namedFolder("refined_obsidian");
        meltMaterial(TinkerFluids.moltenRefinedObsidian.get(), 90, TiAcMeMaterialIds.Mekanism.REFINED_OBSIDIAN, 1475, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.REFINED_OBSIDIAN, Ingredient.of(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN), 1, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.NUGGETS_REFINED_OBSIDIAN,"mekanism",false));
        materialRecipe(TiAcMeMaterialIds.Mekanism.REFINED_OBSIDIAN, Ingredient.of(MekanismTags.Items.NUGGETS_REFINED_OBSIDIAN), 9, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.STORAGE_BLOCKS_REFINED_OBSIDIAN,"mekanism",false));
        materialRecipe(TiAcMeMaterialIds.Mekanism.REFINED_OBSIDIAN, Ingredient.of(MekanismTags.Items.STORAGE_BLOCKS_REFINED_OBSIDIAN), MekanismTags.Items.INGOTS_REFINED_OBSIDIAN, 1, 9, conditional, folder);
        conditional = withCondition(consumer, tagFilled(MekanismTags.Items.PELLETS_ANTIMATTER,"mekanism",false));
        folder = namedFolder("antimatter");
        meltMaterial(TiAcMeTagkeys.Fluids.MOLTEN_ANTIMATTER, 250, TiAcMeMaterialIds.Mekanism.ANTIMATTER, 4996, conditional, folder);
        melt1Slimeball(TiAcMeFluids.MOLTEN_ANTIMATTER.get(), MekanismTags.Items.PELLETS_ANTIMATTER, 4996, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.ANTIMATTER, Ingredient.of(MekanismTags.Items.PELLETS_ANTIMATTER), 1, 1, conditional, folder);
        folder = namedFolder("irradium");
        conditional = withCondition(consumer, modLoaded("mekanism",true));
        melt1Ingot(TiAcMeFluids.MOLTEN_IRRADIUM.get(), TiAcMeItems.IRRADIUM_INGOT.get(), 2750, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_IRRADIUM.get(), 90, TiAcMeMaterialIds.Mekanism.IRRADIUM, 2750, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.IRRADIUM, Ingredient.of(TiAcMeItems.IRRADIUM_INGOT.get()), 1, 1, conditional, folder);
        CombinerRecipeBuilder.combining(ItemStackIngredientCreator.INSTANCE.from(TinkerMaterials.manyullyn.getIngot()), ItemStackIngredientCreator.INSTANCE.from(MekanismItems.POLONIUM_PELLET), new ItemStack(TiAcMeItems.IRRADIUM_INGOT.get())).build(consumer, new ResourceLocation(folder + "_ingot_create"));
        folder = namedFolder("protocite");
        melt1Ingot(TiAcMeFluids.MOLTEN_PROTOCITE.get(), TiAcMeItems.PROTOCITE_PELLET.get(), 2750, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_PROTOCITE.get(), 90, TiAcMeMaterialIds.Mekanism.PROTOCITE, 2750, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.PROTOCITE, Ingredient.of(TiAcMeItems.PROTOCITE_PELLET.get()), 1, 1, conditional, folder);
        CombinerRecipeBuilder.combining(ItemStackIngredientCreator.INSTANCE.from(TinkerMaterials.hepatizon.getIngot()), ItemStackIngredientCreator.INSTANCE.from(MekanismItems.PLUTONIUM_PELLET), new ItemStack(TiAcMeItems.PROTOCITE_PELLET.get())).build(consumer, new ResourceLocation(folder + "_ingot_create"));
        folder = namedFolder("osgloglas");
        melt1Ingot(TiAcMeFluids.MOLTEN_OSGLOGLAS.get(), TiAcMeItems.OSGLOGLAS_INGOT.get(), 1755, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_OSGLOGLAS.get(), 90, TiAcMeMaterialIds.Mekanism.OSGLOGLAS, 1755, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.OSGLOGLAS, Ingredient.of(TiAcMeItems.OSGLOGLAS_INGOT.get()), 1, 1, conditional, folder);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.MOLTEN_OSGLOGLAS.get(), 90)), 1755)
                .addInput(TinkerFluids.moltenOsmium.get(), 90)
                .addInput(TinkerFluids.moltenRefinedObsidian.get(), 180)
                .addInput(TinkerFluids.moltenRefinedGlowstone.get(), 360)
                .save(conditional, new ResourceLocation(folder + "_alloy"));
        folder = namedFolder("nutritive_slime");
        melt1Ingot(TiAcMeFluids.MOLTEN_NUTRITIVE_SLIMESTEEL.get(), TiAcMeItems.NUTRITION_SLIME_INGOT.get(), 990, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_NUTRITIVE_SLIMESTEEL.get(), 90, TiAcMeMaterialIds.Mekanism.NUTRITIVE_SLIMESTEEL, 990, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.Mekanism.NUTRITIVE_SLIMESTEEL, Ingredient.of(TiAcMeItems.NUTRITION_SLIME_INGOT.get()), 1, 1, conditional, folder);
        ItemCastingRecipeBuilder.tableRecipe(TiAcMeItems.NUTRITION_SLIME_INGOT.get()).setCoolingTime(990, 90).setFluid(MekanismFluids.NUTRITIONAL_PASTE.getFluid(), 250).setCast(TinkerMaterials.slimesteel.getIngot(), true).save(conditional, new ResourceLocation(folder + "ingot_create"));
        folder = namedFolder("neutronite");
        meltMaterial(TiAcMeFluids.MOLTEN_NEUTRONITE.get(), 90, TiAcMeMaterialIds.Mekanism.NEUTRONITE, 16384, conditional, folder);
        MeltingRecipeBuilder.melting(Ingredient.of(TiAcMeItems.NEUTRONITE_INGOT.get()), FluidOutput.fromFluid(TiAcMeFluids.MOLTEN_NEUTRONITE.get(), 1), 16384, 1).save(conditional, new ResourceLocation(folder + "_melting_1mb"));
        ItemCastingRecipeBuilder.tableRecipe(TiAcMeItems.NEUTRONITE_INGOT.get()).setCoolingTime(1).setFluid(TiAcMeFluids.MOLTEN_NEUTRONITE.get(), 1).save(conditional, new ResourceLocation(folder + "_casting_1mb"));
        materialRecipe(TiAcMeMaterialIds.Mekanism.NEUTRONITE, Ingredient.of(TiAcMeItems.NEUTRONITE_INGOT.get()), 90, 1, conditional, folder);
        folder = namedFolder("anti_neutronium");
        NucleosynthesizingRecipeBuilder.nucleosynthesizing(
                ItemStackIngredientCreator.INSTANCE
                        .from(TiAcMeMaterials.DENSIUM.getItemObject()),
                GasStackIngredientCreator.INSTANCE.from(MekanismGases.ANTIMATTER,250),
                new ItemStack(TiAcMeMaterials.ANTI_NEUTRONIUM.getItemObject()),200).build(consumer,new ResourceLocation(folder+"_creation"));
        //PnC
        conditional = withCondition(consumer, modLoaded("pneumaticcraft",false));
        folder = namedFolder("compressed_iron");
        conditional = withCondition(consumer, tagFilled(PneumaticCraftTags.Items.INGOTS_COMPRESSED_IRON,"pneumaticcraft",false));
        materialRecipe(TiAcMeMaterialIds.PnC.COMPRESSED_IRON, Ingredient.of(PneumaticCraftTags.Items.INGOTS_COMPRESSED_IRON), 1, 1, conditional, folder);
        conditional = withCondition(consumer, tagFilled(PneumaticCraftTags.Items.STORAGE_BLOCKS_COMPRESSED_IRON,"pneumaticcraft",false));
        materialRecipe(TiAcMeMaterialIds.PnC.COMPRESSED_IRON, Ingredient.of(PneumaticCraftTags.Items.STORAGE_BLOCKS_COMPRESSED_IRON),PneumaticCraftTags.Items.INGOTS_COMPRESSED_IRON, 1, 9, conditional, folder);

        conditional = withCondition(consumer, modLoaded("pneumaticcraft",true));
        folder = namedFolder("pneumatic_steel");
        materialRecipe(TiAcMeMaterialIds.PnC.PNEUMATIC_STEEL, Ingredient.of(TiAcMeItems.PNEUMATIC_STEEL.get()), 1, 1, conditional, folder);
        //Thermal
        conditional = withCondition(consumer, modLoaded("thermal",true));
        folder = namedFolder("basalz_signalum");
        materialRecipe(TiAcMeMaterialIds.Thermal.BASALZ_SIGNALUM, Ingredient.of(TiAcMeItems.BASALZ_SIGNALUM.get()), 1, 1, conditional, folder);
        melt1Ingot(TiAcMeFluids.MOLTEN_BASALZ_SIGNALUM.get(), TiAcMeItems.BASALZ_SIGNALUM.get(), 995, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_BASALZ_SIGNALUM.get(), 90, TiAcMeMaterialIds.Thermal.BASALZ_SIGNALUM, 995, conditional, folder);
        folder = namedFolder("blitz_lumium");
        materialRecipe(TiAcMeMaterialIds.Thermal.BLITZ_LUMIUM, Ingredient.of(TiAcMeItems.BLITZ_LUMIUM.get()), 1, 1, conditional, folder);
        cast1Ingot(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.get(), TiAcMeItems.BLITZ_LUMIUM.get(), 995, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.get(), 90, TiAcMeMaterialIds.Thermal.BLITZ_LUMIUM, 995, conditional, folder);
        MeltingRecipeBuilder.melting(Ingredient.of(TiAcMeItems.BLITZ_LUMIUM.get()), FluidOutput.fromFluid(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.get(), 30), 1000, 30).addByproduct(new FluidStack(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.get(), 60)).save(conditional, new ResourceLocation(folder + "_melting_foundry"));
        tableNugget(TiAcMeItems.BLITZ_LUMIUM.get(), TiAcMeItems.BLITZ_LUMIUM_NUGGET.get(), consumer, folder);
        cast1Nugget(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.get(), TiAcMeItems.BLITZ_LUMIUM_NUGGET.get(), 995, conditional, folder);
        folder = namedFolder("blizz_enderium");
        materialRecipe(TiAcMeMaterialIds.Thermal.BLIZZ_ENDERIUM, Ingredient.of(TiAcMeItems.BLIZZ_ENDERIUM.get()), 1, 1, conditional, folder);
        melt1Ingot(TiAcMeFluids.MOLTEN_BLIZZ_ENDERIUM.get(), TiAcMeItems.BLIZZ_ENDERIUM.get(), 1640, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_BLIZZ_ENDERIUM.get(), 90, TiAcMeMaterialIds.Thermal.BLIZZ_ENDERIUM, 1640, conditional, folder);
        folder = namedFolder("activated_chromatic_steel");
        materialRecipe(TiAcMeMaterialIds.Thermal.ACTIVATED_CHROMATIC_STEEL, Ingredient.of(TiAcMeItems.ACTIVATED_CHROMATIC_STEEL.get()), 1, 1, conditional, folder);
        melt1Plate(TiAcMeFluids.MOLTEN_ACTIVATED_CHROMATIC_STEEL.get(), TiAcMeItems.ACTIVATED_CHROMATIC_STEEL.get(), 2720, conditional, folder);
        meltMaterial(TiAcMeFluids.MOLTEN_ACTIVATED_CHROMATIC_STEEL.get(), 180, TiAcMeMaterialIds.Thermal.ACTIVATED_CHROMATIC_STEEL, 2720, conditional, folder);
        AlloyRecipeBuilder.alloy(FluidOutput.fromStack(new FluidStack(TiAcMeFluids.MOLTEN_ACTIVATED_CHROMATIC_STEEL.get(), 90)), 2720)
                .addInput(TiAcMeFluids.MOLTEN_BASALZ_SIGNALUM.get(), 90)
                .addInput(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.get(), 90)
                .addInput(TiAcMeFluids.MOLTEN_BLIZZ_ENDERIUM.get(), 90)
                .addInput(TiAcMeFluids.MOLTEN_BLAZE_NETHERITE.get(), 90)
                .save(conditional, new ResourceLocation(folder + "_alloy"));
        conditional = withCondition(consumer,modLoaded("thermal",false));
        ItemCastingRecipeBuilder.tableRecipe(TiAcMeItems.SIGNALUM_REINFORCEMENT.get())
                .setCast(TinkerCommons.obsidianPane.asItem(),true)
                .setCoolingTime(999,90).setFluid(TinkerFluids.moltenSignalum.getCommonTag(),90)
                .save(consumer,new ResourceLocation(namedFolder("signalum")+"_reinforcement"));
        //IndustrialForgoing
        conditional = withCondition(consumer, modLoaded("industrialforegoing",false));
        folder = namedFolder("pink_slime_metal");
        meltMaterial(TiAcMeFluids.MOLTEN_PINK_SLIME.get(), 90, TiAcMeMaterialIds.IndustrialForgoing.PINK_SLIME_METAL, 980, conditional, folder);
        melt1Ingot(TiAcMeFluids.MOLTEN_PINK_SLIME.get(), ModuleCore.PINK_SLIME_INGOT.get(), 980, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.IndustrialForgoing.PINK_SLIME_METAL, Ingredient.of(ModuleCore.PINK_SLIME_INGOT.get()), 1, 1, conditional, folder);
        //CreateUtilities
        conditional = withCondition(consumer, modLoaded("createutilities",false));
        folder = namedFolder("void_steel");
        meltMaterial(TiAcMeTagkeys.Fluids.MOLTEN_VOID_STEEL, 90, TiAcMeMaterialIds.CreateUtilities.VOID_STEEL, 1400, conditional, folder);
        melt1Ingot(TiAcMeTagkeys.Fluids.MOLTEN_VOID_STEEL, ForgeRegistries.ITEMS.getValue(new ResourceLocation("createutilities", "void_steel_ingot")), 1400, conditional, folder);
        melt1Plate(TiAcMeTagkeys.Fluids.MOLTEN_VOID_STEEL, ForgeRegistries.ITEMS.getValue(new ResourceLocation("createutilities", "void_steel_sheet")), 1400, conditional, folder);
        materialRecipe(TiAcMeMaterialIds.CreateUtilities.VOID_STEEL, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("createutilities", "void_steel_ingot"))), 1, 1, conditional, folder);
        melt9Ingot(TiAcMeTagkeys.Fluids.MOLTEN_VOID_STEEL, ForgeRegistries.ITEMS.getValue(new ResourceLocation("createutilities", "void_steel_block")),1400,conditional,folder);
        //Common Integration
        folder = namedFolder("plastic");
        conditional = withCondition(consumer, generalTag(TiAcMeTagkeys.Items.PLASTIC,"plastic"));
        materialRecipe(TiAcMeMaterialIds.CommonIntegration.PLASTIC, Ingredient.of(TiAcMeTagkeys.Items.PLASTIC), 1, 1, conditional, folder);

        //Modifiers
        folder = modifierFolder("thermal_foundation");
        conditional = withCondition(consumer,modLoaded("thermal",false));
        ModifierRecipeBuilder.modifier(THERMAL_FOUNDATION).addInput(ItemTagsCoFH.INGOTS_SIGNALUM)
                .addInput(ItemTagsCoFH.INGOTS_LUMIUM).addInput(ItemTagsCoFH.INGOTS_ENDERIUM)
                .addInput(ItemTagsCoFH.INGOTS_INVAR).addInput(ItemTagsCoFH.INGOTS_STEEL).allowCrystal()
                .setTools(TinkerTags.Items.BONUS_SLOTS).setMaxLevel(1).save(conditional,folder);
        folder = modifierFolder("energy_reinforced");
        ModifierRecipeBuilder.modifier(TiAcMeModifiers.ENERGY_REINFORCED.getId())
                .addInput(TiAcMeItems.SIGNALUM_REINFORCEMENT.get(),4).setSlots(SlotType.UPGRADE,1).setMaxLevel(5).setTools(TinkerTags.Items.DURABILITY)
                .allowCrystal().save(consumer,folder);
        conditional = withCondition(consumer,modLoaded("thermal",true));
        folder = modifierFolder("iso_chrome");
        ModifierRecipeBuilder.modifier(TiAcMeModifiers.ISO_CHROME)
                .addInput(TiAcMeItems.ACTIVATED_CHROMATIC_STEEL.get())
                .addInput(TiAcMeItems.BASALZ_SIGNALUM.get())
                .addInput(TiAcMeItems.BLAZE_NETHERITE.get())
                .addInput(TiAcMeItems.BLITZ_LUMIUM.get())
                .addInput(TiAcMeItems.BLIZZ_ENDERIUM.get()).allowCrystal()
                .setTools(TinkerTags.Items.BONUS_SLOTS).setMaxLevel(1).save(conditional,folder);

        conditional = withCondition(consumer,modLoaded("rainbowcompound",false));
        folder = modifierFolder("obsidianite");
        ModifierRecipeBuilder.modifier(OBSIDIANITE)
                .addInput(ItemsItemRegistry.obsidianiteingot.get())
                .addInput(ItemsItemRegistry.obsidianiteupgradekit.get())
                .setSlots(SlotType.UPGRADE,1).allowCrystal().setMaxLevel(1)
                .setTools(TinkerTags.Items.DURABILITY).save(conditional,folder);
        folder = modifierFolder("rainbow_kit");
        ModifierRecipeBuilder.modifier(RAINBOW_KIT)
                .addInput(ItemsItemRegistry.rainbowcompound.get())
                .addInput(ItemsItemRegistry.rainbowupgradekit.get())
                .setSlots(SlotType.UPGRADE,1).allowCrystal().setMaxLevel(1)
                .setTools(TinkerTags.Items.DURABILITY).save(conditional,folder);

        conditional = withCondition(consumer,modLoaded("mekanism",false));
        ModifierRecipeBuilder.modifier(EtSTLibModifier.EtSTLibModifierMek.radiation_shielding)
                .addInput(ItemTagsCoFH.STORAGE_BLOCKS_LEAD)
                .addInput(Tags.Items.DYES_ORANGE)
                .addInput(ItemTagsCoFH.STORAGE_BLOCKS_LEAD)
                .setSlots(SlotType.DEFENSE,1)
                .setMaxLevel(1).useSalvageMax()
                .setTools(TinkerTags.Items.ARMOR)
                .disallowCrystal()
                .saveSalvage(conditional,salvageFolder("radiation_shielding"))
                .save(conditional,modifierFolder("radiation_shielding"));
        ModifierRecipeBuilder.modifier(EtSTLibModifier.EtSTLibModifierMek.radiation_shielding)
                .addInput(ItemTagsCoFH.INGOTS_LEAD)
                .addInput(Tags.Items.DYES_ORANGE)
                .addInput(ItemTagsCoFH.INGOTS_LEAD)
                .setLevelRange(2,5)
                .setTools(TinkerTags.Items.ARMOR)
                .save(conditional,modifierFolder("radiation_shielding_2_5"));
        ModifierRecipeBuilder.modifier(TiAcMeModifiers.RADIATION_REMOVAL)
                .addInput(MekanismTags.Items.PROCESSED_RESOURCES.get(ResourceType.CRYSTAL, PrimaryResource.LEAD),16)
                .addInput(MekanismTags.Items.CIRCUITS_ULTIMATE,4)
                .addInput(TinkerFluids.moltenLead.getBucket())
                .setTools(TinkerTags.Items.MODIFIABLE)
                .setSlots(SlotType.ABILITY,1)
                .setMaxLevel(10)
                .saveSalvage(conditional,salvageFolder("radiation_removal"))
                .save(conditional,modifierFolder("radiation_removal"));
    }

    public void melt1B(Fluid fluid, ItemLike ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,1000),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(1000))).save(consumer, new  ResourceLocation(location+"_melting_1b"));
        ItemCastingRecipeBuilder.basinRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,1000))).setCoolingTime(temperature,1000).save(consumer,new  ResourceLocation(location+"_casting_1b"));
    }
    public void melt1B(Fluid fluid, TagKey<Item> ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,1000),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(1000))).save(consumer, new  ResourceLocation(location+"_melting_1b"));
        ItemCastingRecipeBuilder.basinRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,1000))).setCoolingTime(temperature,1000).save(consumer,new  ResourceLocation(location+"_casting_1b"));
    }
    public void melt9Gem(Fluid fluid,ItemLike ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,900),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(900))).save(consumer, new  ResourceLocation(location+"_melting_gem_block"));
        ItemCastingRecipeBuilder.basinRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,900))).setCoolingTime(temperature,900).save(consumer,new  ResourceLocation(location+"_casting_gem_block"));
    }
    public void melt9Gem(Fluid fluid,TagKey<Item> ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,900),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(900))).save(consumer, new  ResourceLocation(location+"_melting_gem_block"));
        ItemCastingRecipeBuilder.basinRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,900))).setCoolingTime(temperature,900).save(consumer,new  ResourceLocation(location+"_casting_gem_block"));
    }
    public void melt9Ingot(TagKey<Fluid> fluid,ItemLike ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),FluidOutput.fromTag(fluid,810),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(810))).save(consumer,new  ResourceLocation(location+"_melting_metal_block"));
        ItemCastingRecipeBuilder.basinRecipe(ingredient).setFluid(FluidIngredient.of(fluid,810)).setCoolingTime(temperature,810).save(consumer,new  ResourceLocation(location+"_casting_metal_block"));
    }
    public void melt9Ingot(Fluid fluid,TagKey<Item> ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,810),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(810))).save(consumer,new  ResourceLocation(location+"_melting_metal_block"));
        ItemCastingRecipeBuilder.basinRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,810))).setCoolingTime(temperature,810).save(consumer,new  ResourceLocation(location+"_casting_metal_block"));
    }
    public void melt1Slimeball(Fluid fluid,ItemLike ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,250),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(250))).save(consumer,new  ResourceLocation(location+"_melting_250mb"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,250))).setCoolingTime(temperature,250).save(consumer,new  ResourceLocation(location+"_casting_250mb"));
    }
    public void melt1Slimeball(Fluid fluid,TagKey<Item> ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,250),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(250))).save(consumer,new  ResourceLocation(location+"_melting_250mb"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setFluid(FluidIngredient.of(new FluidStack(fluid,250))).setCoolingTime(temperature,250).save(consumer,new  ResourceLocation(location+"_casting_250mb"));
    }
    public void melt1Gem(Fluid fluid,ItemLike ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,100),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(100))).save(consumer,new  ResourceLocation(location+"_melting_gem"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,100).setFluid(FluidIngredient.of(new FluidStack(fluid,100))).setCast(GEM_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_gem_multi"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,100).setFluid(FluidIngredient.of(new FluidStack(fluid,100))).setCast(GEM_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_gem_single"));
    }
    public void melt1Gem(Fluid fluid,TagKey<Item> ingredient,int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,100),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(100))).save(consumer,new  ResourceLocation(location+"_melting_gem"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,100).setFluid(FluidIngredient.of(new FluidStack(fluid,100))).setCast(GEM_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_gem_multi"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,100).setFluid(FluidIngredient.of(new FluidStack(fluid,100))).setCast(GEM_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_gem_single"));
    }
    public void melt1Ingot(Fluid fluid, ItemLike ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,90),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(90))).save(consumer,new  ResourceLocation(location+"_melting_ingot"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(new FluidStack(fluid,90))).setCast(INGOT_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_ingot_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(new FluidStack(fluid,90))).setCast(INGOT_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_ingot_multi"));
    }
    public void melt1Plate(Fluid fluid, ItemLike ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,90),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(90))).save(consumer,new  ResourceLocation(location+"_melting_plate"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(new FluidStack(fluid,90))).setCast(PLATE_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_plate_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(new FluidStack(fluid,90))).setCast(PLATE_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_plate_multi"));
    }
    public void melt1Plate(TagKey<Fluid> fluid, Item ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),FluidOutput.fromTag(fluid,90),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(90))).save(consumer,new  ResourceLocation(location+"_melting_plate"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(FluidIngredient.of(fluid,90))).setCast(PLATE_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_plate_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(FluidIngredient.of(fluid,90))).setCast(PLATE_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_plate_multi"));
    }
    public void melt1Ingot(TagKey<Fluid> fluid, Item ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),FluidOutput.fromTag(fluid,90),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(90))).save(consumer,new  ResourceLocation(location+"_melting_ingot"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(FluidIngredient.of(fluid,90))).setCast(INGOT_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_ingot_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(FluidIngredient.of(fluid,90))).setCast(INGOT_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_ingot_multi"));
    }
    public void melt1Ingot(TagKey<Fluid> fluid, TagKey<Item> ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),FluidOutput.fromTag(fluid,90),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(90))).save(consumer,new  ResourceLocation(location+"_melting_ingot"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(fluid,90)).setCast(INGOT_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_ingot_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(fluid,90)).setCast(INGOT_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_ingot_multi"));
    }
    public void cast1Ingot(Fluid fluid, ItemLike ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(new FluidStack(fluid,90))).setCast(INGOT_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_ingot_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,90).setFluid(FluidIngredient.of(new FluidStack(fluid,90))).setCast(INGOT_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_ingot_multi"));
    }

    public void melt1Nugget(Fluid fluid, TagKey<Item> ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),new FluidStack(fluid,10),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(10))).save(consumer,new  ResourceLocation(location+"_melting_nugget"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,10).setFluid(FluidIngredient.of(new FluidStack(fluid,10))).setCast(NUGGET_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_nugget_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,10).setFluid(FluidIngredient.of(new FluidStack(fluid,10))).setCast(NUGGET_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_nugget_multi"));
    }
    public void melt1Nugget(TagKey<Fluid> fluid, TagKey<Item> ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MeltingRecipeBuilder.melting(Ingredient.of(ingredient),FluidOutput.fromTag(fluid,10),temperature, IMeltingRecipe.calcTime(temperature, IMeltingRecipe.calcTimeFactor(10))).save(consumer,new  ResourceLocation(location+"_melting_nugget"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,10).setFluid(FluidIngredient.of(fluid,10)).setCast(NUGGET_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_nugget_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,10).setFluid(FluidIngredient.of(fluid,10)).setCast(NUGGET_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_nugget_multi"));
    }
    public void cast1Nugget(Fluid fluid, ItemLike ingredient, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,10).setFluid(FluidIngredient.of(new FluidStack(fluid,10))).setCast(NUGGET_MULTICAST,false).save(consumer,new  ResourceLocation(location+"_casting_nugget_single"));
        ItemCastingRecipeBuilder.tableRecipe(ingredient).setCoolingTime(temperature,10).setFluid(FluidIngredient.of(new FluidStack(fluid,10))).setCast(NUGGET_SINGLECAST,true).save(consumer,new  ResourceLocation(location+"_casting_nugget_multi"));
    }

    public void tableNugget(ItemLike ingot,ItemLike nugget,Ingredient ingotIng,Ingredient nuggetIng,Consumer<FinishedRecipe> consumer,ResourceLocation location){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ingot,1).requires(nuggetIng,9).unlockedBy(getHasName(nugget),has(nugget)).save(consumer,new ResourceLocation(location+"to_ingot"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,nugget,9).requires(ingotIng).unlockedBy(getHasName(ingot),has(ingot)).save(consumer,new ResourceLocation(location+"to_nugget"));
    }
    public void tableNugget(ItemLike ingot,ItemLike nugget,Consumer<FinishedRecipe> consumer,ResourceLocation location){
        this.tableNugget(ingot,nugget,Ingredient.of(ingot),Ingredient.of(nugget),consumer,location);
    }


    public void meltMaterial(TagKey<Fluid> fluid,int amount, MaterialVariantId id, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MaterialMeltingRecipeBuilder.material(id,temperature, FluidOutput.fromTag(fluid,amount)).save(consumer, new  ResourceLocation(location+"_material_melt"));
        MaterialFluidRecipeBuilder.material(id).setFluid(fluid,amount).setTemperature(temperature).save(consumer, new  ResourceLocation(location+"_material_cast"));
    }
    public void meltMaterial(Fluid fluid,int amount, MaterialVariantId id, int temperature, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MaterialMeltingRecipeBuilder.material(id,temperature, FluidOutput.fromFluid(fluid,amount)).save(consumer, new  ResourceLocation(location+"_material_melt"));
        MaterialFluidRecipeBuilder.material(id).setTemperature(temperature).setFluid(FluidIngredient.of(new FluidStack(fluid,amount))).save(consumer, new  ResourceLocation(location+"_material_cast"));
    }
    public void materialRecipe(MaterialVariantId id, Ingredient ingredient, int needed, int value, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MaterialRecipeBuilder.materialRecipe(id).setIngredient(ingredient).setNeeded(needed).setValue(value).save(consumer,new  ResourceLocation(location+"_material"+needed+value));
    }
    public void materialRecipe(MaterialVariantId id, Ingredient ingredient,TagKey<Item> leftOver, int needed, int value, Consumer<FinishedRecipe> consumer, ResourceLocation location){
        MaterialRecipeBuilder.materialRecipe(id).setIngredient(ingredient).setNeeded(needed).setValue(value).setLeftover(ItemOutput.fromTag(leftOver)).save(consumer,new  ResourceLocation(location+"_material"+needed+value));
    }

    public void fuel(String name,FluidIngredient ingredient,int duration,int temp,Consumer<FinishedRecipe> consumer){
        MeltingFuelBuilder.fuel(ingredient,duration,temp).save(consumer,new ResourceLocation(namedFolder("fuel")+"_"+name+"fuel"));
    }

    public static ICondition noopCompat(boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(TinkersAdvanced.MODID,isOriginal));
    }
    public static ICondition modLoaded(String modId,boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(modId,isOriginal),new ModLoadedCondition(modId));
    }
    public static ICondition tagFilled(TagKey<Item> tagKey,String modId,boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(modId,isOriginal), new TagFilledCondition<>(tagKey));
    }
    public static ICondition generalTag(TagKey<Item> tagKey,String name){
        return new AndCondition(new GeneralMaterialConfigCondition(name), new TagFilledCondition<>(tagKey));
    }


    @Override
    public String getModId() {
        return TinkersAdvanced.MODID;
    }
}
