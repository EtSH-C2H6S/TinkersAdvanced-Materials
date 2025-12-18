package com.c2h6s.tinkers_advanced_materials.data;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.data.providers.tinker.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.fluids.data.FluidBlockstateModelProvider;
import slimeknights.tconstruct.fluids.data.FluidBucketModelProvider;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        TiAcMeMateriaRecipes.addRecipeInfos();
        DataGenerator generator=event.getGenerator();
        PackOutput output=generator.getPackOutput();
        ExistingFileHelper helper=event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider=event.getLookupProvider();

        generator.addProvider(event.includeClient(),new TiAcMeMaterialProvider(output));
        generator.addProvider(event.includeClient(),new TiAcMeMaterialStatProvider(output));
        generator.addProvider(event.includeClient(),new TiAcMeMaterialModifierProvider(output));
        generator.addProvider(event.includeClient(),new TiAcMeFluidEffectProvider(output));
        generator.addProvider(event.includeClient(),new TiAcMeMaterialTagProvider(output,helper));
        generator.addProvider(event.includeClient(),new TiAcMeModifierTagProvider(output,helper));
        generator.addProvider(event.includeClient(),new TiAcMeRecipeProvider(output));
        generator.addProvider(event.includeClient(),new TiAcMeFluidTextureProvider(output));
        generator.addProvider(event.includeClient(),new FluidBucketModelProvider(output,TinkersAdvanced.MODID));
        generator.addProvider(event.includeClient(),new TiAcMeMaterialRenderInfoProvider(output,new TiAcMeMaterialSpriteProvider(),helper));
        generator.addProvider(event.includeClient(),new MaterialPartTextureGenerator(output,helper,new TinkerPartSpriteProvider(),new TiAcMeMaterialSpriteProvider()));
        if (ModList.get().isLoaded("tinkers_advanced_tools"))
            generator.addProvider(event.includeClient(),new MaterialPartTextureGenerator(output,helper, new TiAcMeToolPartSpriteProvider(),new TiAcMeMaterialSpriteProvider()));

        generator.addProvider(event.includeClient(),new TiAcMeBlockStateProvider(output,TinkersAdvanced.MODID,helper));
        generator.addProvider(event.includeClient(),new TiAcMeItemModelProvider(output,helper));
        TiAcMeBlockTagProvider blockTags = new TiAcMeBlockTagProvider(output,lookupProvider , helper);
        generator.addProvider(event.includeClient(),blockTags);
        generator.addProvider(event.includeServer(),new TiAcMeItemTagProvider(output,lookupProvider,blockTags.contentsGetter(),helper));
        generator.addProvider(event.includeClient(),new TiAcMeFluidTagProvider(output,lookupProvider,helper));

        generator.addProvider(event.includeClient(),new FluidBlockstateModelProvider(output,TinkersAdvanced.MODID));
        generator.addProvider(event.includeClient(),new TiAcMeModifierProvider(output));
    }
}
