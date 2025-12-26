package com.c2h6s.tinkers_advanced_materials.data.enums;

import com.c2h6s.etstlib.data.EtSTLibModifierIds;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.tinkers_advanced.core.content.tool.tinkering.materialStat.FluxCoreMaterialStat;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeModifiers;
import com.c2h6s.tinkers_advanced_tools.init.TiAcTModifiers;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.stats.*;

public enum EnumMaterialModifier {
    ALLOY_ATOMIC(StatlessMaterialStats.BINDING.getIdentifier(),entry(EtSTLibModifierIds.ATOMIC_DECOMPOSE)),
    BISMUTH(null,entry(TiAcMeModifiers.TETANUS.getId()),entry(TiAcMeModifiers.HEAVY_MATERIAL.getId())),
    BISMUTH_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.TETANUS.getId()),entry(ModifierIds.heavy)),
    BISMUTHINITE(null,entry(TiAcMeModifiers.FRAGILE.getId()),entry(EtSTLibModifier.ANISOTROPY.getId())),
    BISMUTHINITE_FLUX_CORE(FluxCoreMaterialStat.ID,entry(TiAcTModifiers.PIEZOELECTRIC_EFFECT.getId(),2)),
    BISMUTHINITE_AMMO(MaterialRegistry.AMMO,entry(TiAcMeModifiers.AERIAL_UNSTABLE.getId()),entry(ModifierIds.keen)),
    BISMUTHINITE_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.FRAGILE.getId()),entry(EtSTLibModifier.CRYSTAL_ARMOR.getId())),

    CERTUS_DEFAULT(null,entry(EtSTLibModifier.ANISOTROPY.getId())),
    CERTUS_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.CRYSTAL_ARMOR.getId())),
    CERTUS_AMMO(MaterialRegistry.AMMO,entry(EtSTLibModifier.CRYSTALINE_ARROW.getId())),

    FLUIX_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.CRYSTAL_ARMOR.getId()),entry(EtSTLibModifier.clearing.getId())),
    FLUIX_GRIP(GripMaterialStats.ID,entry(EtSTLibModifier.ANISOTROPY.getId()),entry(EtSTLibModifier.EtSTLibModifierAE.applied_fixing.getId())),
    FLUIX_LIMB(LimbMaterialStats.ID,entry(EtSTLibModifier.ANISOTROPY.getId()),entry(EtSTLibModifier.EtSTLibModifierAE.energetic_attack.getId())),
    FLUIX_HEAD(HeadMaterialStats.ID,entry(EtSTLibModifier.ANISOTROPY.getId()),entry(EtSTLibModifier.EtSTLibModifierAE.energetic_attack.getId())),
    FLUIX_HANDLE(HandleMaterialStats.ID,entry(ModifierIds.looting),entry(EtSTLibModifier.EtSTLibModifierAE.applied_fixing.getId())),
    FLUIX_BINDING(StatlessMaterialStats.BINDING.getIdentifier(),entry(ModifierIds.fortune),entry(EtSTLibModifier.EtSTLibModifierAE.applied_fixing.getId())),
    FLUIX_FLUX_CORE(FluxCoreMaterialStat.ID,entry(ModifierIds.looting),entry(TiAcTModifiers.PIEZOELECTRIC_EFFECT.getId())),
    FLUIX_AMMO(MaterialRegistry.AMMO,entry(EtSTLibModifier.CRYSTALINE_ARROW.getId(),2),entry(EtSTLibModifier.EtSTLibModifierAE.energetic_attack.getId())),

    ANTIMATTER_MELEE(MaterialRegistry.MELEE_HARVEST,entry(TiAcMeModifiers.ANNIHILATE.getId()),entry(EtSTLibModifierIds.ATOMIC_DECOMPOSE)),
    ANTIMATTER_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.REACTIVE_EXPLOSIVE_ARMOR.getId())),

    REFINED_GLOWSTONE_DEFAULT(null,entry(EtSTLibModifier.glowing.getId())),
    REFINED_GLOWSTONE_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.glowing.getId()),entry(TinkerModifiers.golden.getId())),

    REFINED_OBSIDIAN_DEFAULT(null,entry(EtSTLibModifier.momentum_accelerate.getId()),entry(ModifierIds.dense)),
    REFINED_OBSIDIAN_ARMOR(MaterialRegistry.ARMOR,entry(ModifierIds.ductile,2),entry(ModifierIds.dense)),

    IRRADIUM_DEFAULT(null,entry(TiAcMeModifiers.RADIATION_BURNING.getId()),entry(EtSTLibModifier.glowing.getId())),
    IRRADIUM_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.RADIOACTIVE_ARMOR.getId()),entry(EtSTLibModifier.glowing.getId())),

    PNEUMATIC_STEEL_DEFAULT(null,entry(EtSTLibModifier.EtSTLibModifierPnC.aerial_reinforced.getId()),entry(TiAcMeModifiers.AIR_SLASH.getId())),
    PNEUMATIC_STEEL_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.EtSTLibModifierPnC.aerial_reinforced.getId()),entry(TiAcMeModifiers.AERIAL_PROTECTION.getId())),
    PNEUMATIC_STEEL_AMMO(MaterialRegistry.AMMO,entry(EtSTLibModifier.ACCELERATE.getId(),2)),

    BASALZ_SIGNALUM_DEFAULT(null,entry(EtSTLibModifier.SHORT_CIRCUIT.getId()),entry(TiAcMeModifiers.BASALZ_INFLICT.getId())),
    BASALZ_SIGNALUM_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.SENSOR_INTERRUPT.getId()),entry(TiAcMeModifiers.BASALZ_DEFENSE.getId())),

    BLITZ_LUMIUM_DEFAULT(null,entry(EtSTLibModifier.glowing.getId()),entry(TiAcMeModifiers.Blitz_INFLICT.getId()),entry(new ModifierId("etstlib:static_discharge"))),
    BLITZ_LUMIUM_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.glowing.getId()),entry(TiAcMeModifiers.Blitz_DEFENSE.getId())),

    BLIZZ_ENDERIUM_DEFAULT(null,entry(TinkerModifiers.enderference.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(TiAcMeModifiers.BLIZZ_INFLICT.getId())),
    BLIZZ_ENDERIUM_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(TiAcMeModifiers.BLIZZ_DEFENSE.getId()),entry(TiAcMeModifiers.VOID_DODGING.getId())),

    ACTIVATED_CHROMA_STEEL_MELEE(MaterialRegistry.MELEE_HARVEST,entry(TiAcMeModifiers.FLUX_INFUSED.getId()),entry(TiAcMeModifiers.THERMAL_ENHANCE.getId()),entry(TiAcMeModifiers.THERMAL_SLASH.getId())),
    ACTIVATED_CHROMA_STEEL_RANGED(MaterialRegistry.RANGED,entry(TiAcMeModifiers.FLUX_INFUSED.getId()),entry(TiAcMeModifiers.THERMAL_ENHANCE.getId()),entry(TiAcMeModifiers.FLUX_ARROW.getId())),
    ACTIVATED_CHROMA_STEEL_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.FLUX_INFUSED.getId()),entry(TiAcMeModifiers.THERMAL_ENHANCE.getId()),entry(TiAcMeModifiers.FLUX_DEFENSE.getId())),

    BLAZE_NETHERITE(null,entry(TiAcMeModifiers.FLAME_ADAPTIVE.getId()),entry(ModifierIds.netherite)),

    IRIDIUM_DEFAULT(null,entry(EtSTLibModifier.INERT_METAL.getId()),entry(ModifierIds.dense)),
    IRIDIUM_FLUX_CORE(FluxCoreMaterialStat.ID,entry(EtSTLibModifier.INERT_METAL.getId()),entry(TiAcTModifiers.PLATINOID_CATALYST.getId())),
    IRIDIUM_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.INERT_METAL.getId()),entry(EtSTLibModifier.SECONDARY_ARMOR.getId())),

    DENSIUM_DEFAULT(null,entry(EtSTLibModifier.EXTRA_DENSE.getId())),
    DENSIUM_HEAD(HeadMaterialStats.ID,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifierIds.RUDE)),
    DENSIUM_HANDLE(HandleMaterialStats.ID,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifier.momentum_accelerate.getId(),5),entry(TiAcMeModifiers.HEAVY_MATERIAL.getId())),
    DENSIUM_BINDING(StatlessMaterialStats.BINDING.getIdentifier(),entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifier.momentum_accelerate.getId())),
    DENSIUM_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifier.HYPER_DENSITY.getId())),
    DENSIUM_RANGED(MaterialRegistry.RANGED,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(TinkerModifiers.momentum.getId(),5),entry(TiAcMeModifiers.HEAVY_MATERIAL.getId())),

    NEUTRONITE_DEFAULT(null,entry(TiAcMeModifiers.SUPREME_DENSITY.getId()),entry(TiAcMeModifiers.IONIZED.getId())),
    NEUTRONITE_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.SUPREME_DENSITY_ARMOR.getId()),entry(TiAcMeModifiers.IONIZED.getId())),

    OSGLOGLAS_DEFAULT(null,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifier.GLOBAL_TRAVELER.getId())),

    DISINTEGRATE_CRYSTAL_DEFAULT(null,entry(TiAcMeModifiers.DIS_INTEGRATE.getId()),entry(ModifierIds.fortune,3)),

    RESONATE_CRYSTAL_DEFAULT(null,entry(EtSTLibModifier.ANISOTROPY.getId()),entry(TiAcMeModifiers.ECHO_LOCATING.getId())),
    RESONATE_CRYSTAL_ARMOR(MaterialRegistry.ARMOR,entry(EtSTLibModifier.CRYSTAL_ARMOR.getId()),entry(EtSTLibModifier.RESONATING.getId())),

    VOLTAIC_CRYSTAL_DEFAULT(null,entry(EtSTLibModifier.energy_loaded.getId()),entry(TiAcMeModifiers.ELECTRIC.getId())),

    PLASTIC_DEFAULT(null,entry(TiAcMeModifiers.ELASTIC.getId()),entry(TiAcMeModifiers.SHAPING.getId())),
    PLASTIC_AMMO(MaterialRegistry.AMMO,entry(EtSTLibModifier.CHEAP.getId())),

    PROTOCITE_DEFAULT(null,entry(TiAcMeModifiers.PROTO_REFINING.getId()),entry(EtSTLibModifier.glowing.getId())),
    PROTOCITE_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.PROTO_DEFENSE.getId()),entry(EtSTLibModifier.glowing.getId())),

    COMPRESSED_IRON_DEFAULT(null,entry(ModifierIds.dense),entry(TinkerModifiers.magnetic.getId())),
    COMPRESSED_IRON_ARMOR(MaterialRegistry.ARMOR,entry(ModifierIds.blastProtection),entry(ModifierIds.projectileProtection)),

    PINK_SLIME_METAL(null,entry(TinkerModifiers.overslime.getId()),entry(TiAcMeModifiers.RETURN_TO_SLIME.getId())),

    NUTRITIVE_SLIMESTEEL(null,entry(TinkerModifiers.overslime.getId()),entry(TiAcMeModifiers.NUTRITIVE_SLIME.getId())),

    CINDER_SLIME_FLUX_CORE(FluxCoreMaterialStat.ID,entry(TinkerModifiers.overslime.getId()),entry(TiAcTModifiers.OVERSLIME_GENERATOR.getId(),2)),

    PIG_IRON_FLUX_CORE(FluxCoreMaterialStat.ID,entry(TiAcTModifiers.ELECTRIC_FOOD.getId())),

    COBALT_FLUX_CORE(FluxCoreMaterialStat.ID,entry(ModifierIds.lightweight),entry(TiAcTModifiers.TRANSITION_CATALYST.getId())),

    STIBNITE_FLUX_CORE(FluxCoreMaterialStat.ID,entry(TiAcMeModifiers.UNSTABLE.getId()),entry(TiAcTModifiers.PIEZOELECTRIC_EFFECT.getId(),4)),
    STIBNITE_DEFAULT(null,entry(TiAcMeModifiers.UNSTABLE.getId()),entry(EtSTLibModifier.ANISOTROPY.getId(),2)),
    STIBNITE_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.UNSTABLE.getId()),entry(EtSTLibModifier.CRYSTAL_ARMOR.getId(),2)),
    STIBNITE_AMMO(MaterialRegistry.AMMO,entry(TiAcMeModifiers.CHEMICAL_UNSTABLE.getId()),entry(ModifierIds.keen,3)),


    ANTIMONY_DEFAULT(null,entry(TiAcMeModifiers.PLAGUE.getId()),entry(TiAcMeModifiers.STABILIZE.getId())),
    ANTIMONY_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.POISON_DEFENSE.getId()),entry(TiAcMeModifiers.STABILIZE.getId())),

    VOID_STEEL_DEFAULT(null,entry(EtSTLibModifier.GLOBAL_TRAVELER.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId())),
    VOID_STEEL_ARMOR(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.VOID_DODGING.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId())),
    ;

    public final ModifierEntry[] modifiers;
    public final MaterialStatsId statType;
    EnumMaterialModifier(MaterialStatsId statType, ModifierEntry... modifiers){
        this.modifiers = modifiers;
        this.statType = statType;
    }
    public static ModifierEntry entry(ModifierId id,int level){
        return new ModifierEntry(id,level);
    }
    public static ModifierEntry entry(ModifierId id){
        return new ModifierEntry(id,1);
    }
}
