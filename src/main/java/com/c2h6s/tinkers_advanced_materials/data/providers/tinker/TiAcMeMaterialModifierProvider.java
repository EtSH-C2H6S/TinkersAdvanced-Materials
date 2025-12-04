package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced_materials.data.enums.EnumMaterial;
import com.c2h6s.tinkers_advanced_materials.data.enums.EnumMaterialModifier;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;

public class TiAcMeMaterialModifierProvider extends AbstractMaterialTraitDataProvider {
    public TiAcMeMaterialModifierProvider(PackOutput packOutput) {
        super(packOutput, new TiAcMeMaterialProvider(packOutput));
    }

    @Override
    protected void addMaterialTraits() {
        for (EnumMaterial material : EnumMaterial.values()){
            for (EnumMaterialModifier materialModifier:material.modifiers){
                if (materialModifier.statType==null){
                    addDefaultTraits(material.id,materialModifier.modifiers);
                }
                else addTraits(material.id,materialModifier.statType,materialModifier.modifiers);
            }
        }
        for (SimpleMaterialObject object: TiAcMeMaterials.MATERIALS.getEntryMap().values()){
            if (object.getMaterialInfo()!=null&&object.isUnit()) {
                for (var statPair : object.getMaterialInfo().getModifiers().modifierPairs()) {
                    if (statPair.getA() == null) addDefaultTraits(object.getMaterialId(), statPair.getB());
                    else addTraits(object.getMaterialId(), statPair.getA(), statPair.getB());
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Tinkers' Advanced-Materials Material Modifier Provider";
    }
}
