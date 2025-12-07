package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced_materials.data.TiAcMeMaterialIds;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class TiAcMeMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public TiAcMeMaterialRenderInfoProvider(PackOutput packOutput, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, materialSprites, existingFileHelper);
    }

    @Override
    protected void addMaterialRenderInfo() {
        TiAcMeMaterials.MATERIALS.getEntryMap().values().forEach(object -> {
            var renderInfo = object.getRenderInfo();
            if (renderInfo!=null){
                buildRenderInfo(object.getMaterialId()).color(renderInfo.vertexColor())
                        .fallbacks(renderInfo.fallbacks()).luminosity(renderInfo.luminosity());
            }
        });
        buildRenderInfo(TiAcMeMaterialIds.BISMUTH).color(0xFFCFBFD1).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.BISMUTHINITE).color(0xFF424242).fallbacks("crystal", "rock", "stick");
        buildRenderInfo(TiAcMeMaterialIds.ANTIMONY).color(0xFFC7D6CC).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.STIBNITE).color(0xFF728c39).fallbacks("crystal", "rock", "stick");
        buildRenderInfo(TiAcMeMaterialIds.AE2.CERTUS).color(0xFFB8D8FC).fallbacks("crystal", "rock", "stick");
        buildRenderInfo(TiAcMeMaterialIds.AE2.FLUIX).color(0xFFB8D8FC).fallbacks("crystal", "rock", "stick").luminosity(5);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.ALLOY_ATOMIC).color(0xFFD896FF).fallbacks("crystal", "metal").luminosity(6);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.REFINED_GLOWSTONE).color(0xFFFEFF8C).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.REFINED_OBSIDIAN).color(0xFF391375).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.ANTIMATTER).color(0xFFD479E5).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.IRRADIUM).color(0xFF17CBEB).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.PnC.PNEUMATIC_STEEL).color(0xFF9797B1).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Thermal.BASALZ_SIGNALUM).color(0xFFFF4E11).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Thermal.BLITZ_LUMIUM).color(0xFFFFFB9F).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Thermal.BLIZZ_ENDERIUM).color(0xFF39FFD1).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.BLAZE_NETHERITE).color(0xFFCF6D4F).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Thermal.ACTIVATED_CHROMATIC_STEEL).color(0xFFFFC7E7).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Thermal.Variant.ACTIVATED_CHROMATIC_STEEL_ACTIVATED).color(0xFFFFC7E7).fallbacks("metal").luminosity(6);
        buildRenderInfo(TiAcMeMaterialIds.Thermal.Variant.ACTIVATED_CHROMATIC_STEEL_EMPOWERED).color(0xFFFFC7E7).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.IRIDIUM).color(0xFFE0D6FF).fallbacks("metal").luminosity(6);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.NEUTRONITE).color(0xFF30003E).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.OSGLOGLAS).color(0xFF72FF7B).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.DISINTEGRATE_CRYSTAL).color(0xFFFFB968).fallbacks("crystal", "rock").luminosity(10);
        buildRenderInfo(TiAcMeMaterialIds.CommonIntegration.PLASTIC).color(0xFFbebebe).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.RESONANCE_CRYSTAL).color(0xFF93d7c1).fallbacks("crystal", "rock").luminosity(7);
        buildRenderInfo(TiAcMeMaterialIds.VOLTAIC_CRYSTAL).color(0xFFa19aff).fallbacks("crystal", "rock").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.PROTOCITE).color(0xFFD7FF6B).fallbacks("metal").luminosity(15);
        buildRenderInfo(TiAcMeMaterialIds.PnC.COMPRESSED_IRON).color(0xFFa1a1a1).fallbacks("metal");
        buildRenderInfo(TiAcMeMaterialIds.Mekanism.NUTRITIVE_SLIMESTEEL).color(0xFFf77dbf).fallbacks("slime_metal","metal");
        buildRenderInfo(TiAcMeMaterialIds.IndustrialForgoing.PINK_SLIME_METAL).color(0xFFd08cc5).fallbacks("slime_metal","metal");
        buildRenderInfo(TiAcMeMaterialIds.CreateUtilities.VOID_STEEL).color(0xFF1a7565).fallbacks("metal");
    }

    @Override
    public String getName() {
        return "Tinkers' Advanced-Materials Material Info Provider";
    }
}
