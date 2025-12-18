package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced_materials.data.enums.EnumMaterial;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import com.mojang.logging.LogUtils;
import net.minecraft.data.PackOutput;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

import static com.c2h6s.tinkers_advanced.core.util.CommonConstants.Materials.ORDER_ORIGINAL;

public class TiAcMeMaterialProvider extends AbstractMaterialDataProvider {
    public TiAcMeMaterialProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addMaterials() {
        Logger logger = LogUtils.getLogger();
        for (EnumMaterial material:EnumMaterial.values()){
            //logger.info("Now generating: {}", material.id);
            addMaterial(material.id,material.tier,material.original?ORDER_ORIGINAL :ORDER_COMPAT, material.craftable, material.hidden, material.condition);
        }
        TiAcMeMaterials.MATERIALS.getEntryMap().values().forEach(object->{
            var info = object.getMaterialInfo();
            if (info!=null&&object.isUnit()) {
                addMaterial(object.getMaterialId(), info.getTier(), info.getSortOrder(), info.isCraftable(), info.isHidden(),
                        object.getCondition());
            }
        });
    }

    @Override
    public String getName() {
        return "Tinkers' Advanced-Materials Material Data Provider";
    }
}
