package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider;

public class TiAcMeMaterialTagProvider extends AbstractMaterialTagProvider {
    public TiAcMeMaterialTagProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersAdvanced.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(TinkerTags.Materials.EXCLUDE_FROM_LOOT).addOptional(TiAcMeMaterialIds.Mekanism.IRRADIUM,TiAcMeMaterialIds.Mekanism.ANTIMATTER,TiAcMeMaterialIds.Mekanism.NEUTRONITE,TiAcMeMaterialIds.Thermal.ACTIVATED_CHROMATIC_STEEL,TiAcMeMaterialIds.Mekanism.PROTOCITE);
        this.tag(TinkerTags.Materials.NETHER).add(TiAcMeMaterialIds.ANTIMONY,TiAcMeMaterialIds.STIBNITE,TiAcMeMaterialIds.BLAZE_NETHERITE);
    }

    @Override
    public String getName() {
        return "TiAcMe Material Tag Provider";
    }
}
