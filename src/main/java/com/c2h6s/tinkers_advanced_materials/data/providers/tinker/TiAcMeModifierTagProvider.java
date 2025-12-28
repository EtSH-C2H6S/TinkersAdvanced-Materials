package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.etstlib.register.EtSTLibModifier;
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
                .addOptional(
                        RETURN_TO_SLIME.getId(),
                        NUTRITIVE_SLIME.getId(),
                        NEUTRONIUM_ASSEMBLE.getId(),
                        ANNIHILATING_SLIME.getId(),
                        ANNIHILATING_SLIME_ARMOR.getId()
                );
        this.tag(TinkerTags.Modifiers.SLOTLESS)
                .addOptional(
                        THERMAL_FOUNDATION,
                        BASALZ_ENHANCE,
                        BLITZ_ENHANCE,
                        BLIZZ_ENHANCE,
                        TiAcMeModifierProvider.ModifierIds.ATOM_GRADE,
                        EDGING_TECHNOLOGY,
                        QUANTUM_TECHNOLOGY,
                        ISO_CHROME.getId()
                );
        this.tag(TinkerTags.Modifiers.BONUS_SLOTLESS).addOptional(
                THERMAL_FOUNDATION,
                BASALZ_ENHANCE,
                BLITZ_ENHANCE,
                BLIZZ_ENHANCE,
                TiAcMeModifierProvider.ModifierIds.ATOM_GRADE,
                EDGING_TECHNOLOGY,
                QUANTUM_TECHNOLOGY,
                ISO_CHROME.getId()
        );
        this.tag(TinkerTags.Modifiers.UPGRADES)
                .addOptional(
                        OBSIDIANITE,
                        RAINBOW_KIT
                );
        this.tag(TinkerTags.Modifiers.GENERAL_UPGRADES)
                .addOptional(
                        OBSIDIANITE,
                        RAINBOW_KIT
                );
        this.tag(TinkerTags.Modifiers.DEFENSE)
                .addOptional(
                        EtSTLibModifier.EtSTLibModifierMek.radiation_shielding.getId()
                );
        this.tag(TinkerTags.Modifiers.SPECIAL_DEFENSE)
                .addOptional(
                        EtSTLibModifier.EtSTLibModifierMek.radiation_shielding.getId()
                );
        this.tag(TinkerTags.Modifiers.ABILITIES)
                .addOptional(
                        RADIATION_REMOVAL.getId()
                );
        this.tag(TinkerTags.Modifiers.GENERAL_ABILITIES)
                .addOptional(
                        RADIATION_REMOVAL.getId()
                );
    }

    @Override
    public String getName() {
        return "Tinker's Advanced-Materials Modifier Tag Provider.";
    }
}
