package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierTagProvider;

import static com.c2h6s.tinkers_advanced_materials.init.TiAcMeModifiers.*;

public class TiAcMeModifierTagProvider extends AbstractModifierTagProvider {
    public TiAcMeModifierTagProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersAdvanced.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(TinkerTags.Modifiers.OVERSLIME_FRIEND)
                .add(
                        RETURN_TO_SLIME.getId(),
                        NUTRITIVE_SLIME.getId()
                );
    }

    @Override
    public String getName() {
        return "Tinker's Advanced-Materials Modifier Tag Provider.";
    }
}
