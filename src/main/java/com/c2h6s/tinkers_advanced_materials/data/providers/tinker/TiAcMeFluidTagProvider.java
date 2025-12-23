package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeTagkeys;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.concurrent.CompletableFuture;

public class TiAcMeFluidTagProvider extends FluidTagsProvider {
    public TiAcMeFluidTagProvider(PackOutput p_255941_, CompletableFuture<HolderLookup.Provider> p_256600_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255941_, p_256600_, TinkersAdvanced.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(TiAcMeTagkeys.Fluids.MOLTEN_BISMUTH).addOptional(TiAcMeFluids.MOLTEN_BISMUTH.getId());
        tag(TiAcMeTagkeys.Fluids.MOLTEN_ANTIMONY).addOptional(TiAcMeFluids.MOLTEN_ANTIMONY.getId());
        tag(TiAcMeTagkeys.Fluids.MOLTEN_IRIDIUM).addOptional(TiAcMeFluids.MOLTEN_IRIDIUM.getId());
        var metalTags = tag(TinkerTags.Fluids.METAL_TOOLTIPS)
                .addOptional(TiAcMeFluids.MOLTEN_IRRADIUM.getId())
                .addOptional(TiAcMeFluids.MOLTEN_BASALZ_SIGNALUM.getId())
                .addOptional(TiAcMeFluids.MOLTEN_BLIZZ_ENDERIUM.getId())
                .addOptional(TiAcMeFluids.MOLTEN_VOID_STEEL.getId())
                .addOptional(TiAcMeFluids.MOLTEN_BILTZ_LUMIUM.getId())
                .addOptional(TiAcMeFluids.MOLTEN_BLAZE_NETHERITE.getId())
                .addOptional(TiAcMeFluids.MOLTEN_VOID_STEEL.getId())
                .addOptional(TiAcMeFluids.MOLTEN_ANTIMONY.getId())
                .addOptional(TiAcMeFluids.MOLTEN_BISMUTH.getId())
                .addOptional(TiAcMeFluids.MOLTEN_ACTIVATED_CHROMATIC_STEEL.getId())
                .addOptional(TiAcMeFluids.MOLTEN_IRIDIUM.getId())
                .addOptional(TiAcMeFluids.MOLTEN_NUTRITIVE_SLIMESTEEL.getId())
                .addOptional(TiAcMeFluids.MOLTEN_OSGLOGLAS.getId())
                .addOptional(TiAcMeFluids.MOLTEN_PROTOCITE.getId())
                .addOptional(TiAcMeFluids.MOLTEN_PINK_SLIME.getId());

        TiAcMeMaterials.MATERIALS.getEntryMap().values().stream()
                .filter(object -> object.getFluidObject()!=null && object.getRecipeInfo()!=null &&
                        object.getRecipeInfo().getCastItemOptional().stream()
                                .anyMatch(castItemObject -> castItemObject == TinkerSmeltery.ingotCast))
                .forEach(object ->{
                    metalTags.addOptional(object.getFluidObject().getId());
                    tag(object.getFluidObject().getCommonTag()).addOptional(object.getFluidObject().getId());
                });

        tag(TiAcMeTagkeys.Fluids.MOLTEN_ANTIMATTER).addOptional(TiAcMeFluids.MOLTEN_ANTIMATTER.getId());
        tag(TiAcMeTagkeys.Fluids.MOLTEN_VOID_STEEL).addOptional(TiAcMeFluids.MOLTEN_VOID_STEEL.getId());
        tag(TinkerTags.Fluids.SLIME_TOOLTIPS).addOptional(TiAcMeFluids.MOLTEN_ANTIMATTER.getId());
        tag(TiAcMeTagkeys.Fluids.MOLTEN_MELODIC_ALLOY).addOptional(TiAcMeMaterials.MELODIC_ALLOY.getFluidObject().getId());
        tag(TiAcMeTagkeys.Fluids.MOLTEN_PRISMALIC_ALLOY).addOptional(TiAcMeMaterials.PRISMALIC_ALLOY.getFluidObject().getId());
    }
}
