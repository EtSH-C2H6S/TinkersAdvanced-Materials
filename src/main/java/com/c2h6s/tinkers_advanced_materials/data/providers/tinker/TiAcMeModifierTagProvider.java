package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierTagProvider;

import static com.c2h6s.tinkers_advanced_materials.init.TiAcMeModifiers.*;
import static com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider.ModifierIds.*;

public class TiAcMeModifierTagProvider extends AbstractModifierTagProvider {
    public TiAcMeModifierTagProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersAdvanced.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(TinkerTags.Modifiers.OVERSLIME_FRIEND)
                .addOptionalTag(
                        RETURN_TO_SLIME.getId(),
                        NUTRITIVE_SLIME.getId(),
                        NEUTRONIUM_ASSEMBLE.getId(),
                        ANNIHILATING_SLIME.getId(),
                        ANNIHILATING_SLIME_ARMOR.getId()
                );
        this.tag(TinkerTags.Modifiers.SLOTLESS)
                .addOptionalTag(
                        THERMAL_FOUNDATION,
                        BASALZ_ENHANCE,
                        BLITZ_ENHANCE,
                        BLIZZ_ENHANCE
                );
        this.tag(TinkerTags.Modifiers.UPGRADES)
                .addOptionalTag(
                        OBSIDIANITE,
                        RAINBOW_KIT
                );
    }

    @Override
    public String getName() {
        return "Tinker's Advanced-Materials Modifier Tag Provider.";
    }
}
