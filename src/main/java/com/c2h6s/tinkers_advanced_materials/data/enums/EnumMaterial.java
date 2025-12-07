package com.c2h6s.tinkers_advanced_materials.data.enums;

import appeng.datagen.providers.tags.ConventionTags;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.data.condition.CompatConfigCondition;
import com.c2h6s.tinkers_advanced.core.init.TiAcCrConditions;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeMaterialIds;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeTagkeys;
import me.desht.pneumaticcraft.api.data.PneumaticCraftTags;
import mekanism.common.tags.MekanismTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.condition.TagFilledCondition;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import static com.c2h6s.tinkers_advanced_materials.data.enums.EnumMaterialModifier.*;

public enum EnumMaterial {
    BISMUTH(true,TiAcMeMaterialIds.BISMUTH,4,false,false,EnumMaterialStats.BISMUTH,noopCompat(true),EnumMaterialModifier.BISMUTH),
    BISMUTHINITE(true,TiAcMeMaterialIds.BISMUTHINITE,2,true,false,EnumMaterialStats.BISMUTHINITE,noopCompat(true),EnumMaterialModifier.BISMUTHINITE,BISMUTHINITE_FLUX_CORE),
    ANTIMONY(true,TiAcMeMaterialIds.ANTIMONY,4,false,false,EnumMaterialStats.ANTIMONY,noopCompat(true), ANTIMONY_ARMOR,ANTIMONY_DEFAULT),
    STIBNITE(true,TiAcMeMaterialIds.STIBNITE,4,true,false,EnumMaterialStats.STIBNITE,noopCompat(true), STIBNITE_DEFAULT,STIBNITE_FLUX_CORE),
    ALLOY_ATOMIC(TiAcMeMaterialIds.Mekanism.ALLOY_ATOMIC,3,true,false,EnumMaterialStats.ALLOY_ATOMIC, tagFilled(MekanismTags.Items.ALLOYS_ATOMIC,"mekanism",false),EnumMaterialModifier.ALLOY_ATOMIC),
    FLUIX(TiAcMeMaterialIds.AE2.FLUIX,2,true,false,EnumMaterialStats.FLUIX, tagFilled(ConventionTags.FLUIX_CRYSTAL,"ae2",false),FLUIX_ARMOR,FLUIX_BINDING,FLUIX_GRIP,FLUIX_HEAD,FLUIX_HANDLE,FLUIX_LIMB, FLUIX_FLUX_CORE),
    CERTUS(TiAcMeMaterialIds.AE2.CERTUS,1,true,false,EnumMaterialStats.CERTUS, tagFilled(ConventionTags.CERTUS_QUARTZ,"ae2",false),CERTUS_ARMOR,CERTUS_DEFAULT),
    ANTIMATTER(TiAcMeMaterialIds.Mekanism.ANTIMATTER,4,false,false,EnumMaterialStats.ANTIMATTER, tagFilled(MekanismTags.Items.PELLETS_ANTIMATTER,"mekanism",false),ANTIMATTER_ARMOR,ANTIMATTER_MELEE),
    REFINED_GLOWSTONE(TiAcMeMaterialIds.Mekanism.REFINED_GLOWSTONE,3,true,false,EnumMaterialStats.REFINED_GLOWSTONE, tagFilled(MekanismTags.Items.INGOTS_REFINED_GLOWSTONE,"mekanism",false),REFINED_GLOWSTONE_DEFAULT,REFINED_GLOWSTONE_ARMOR),
    REFINED_OBSIDIAN(TiAcMeMaterialIds.Mekanism.REFINED_OBSIDIAN,4,true,false,EnumMaterialStats.REFINED_OBSIDIAN, tagFilled(MekanismTags.Items.INGOTS_REFINED_OBSIDIAN,"mekanism",false),REFINED_OBSIDIAN_ARMOR,REFINED_OBSIDIAN_DEFAULT),
    IRRADIUM(true,TiAcMeMaterialIds.Mekanism.IRRADIUM,4,false,false,EnumMaterialStats.IRRADIUM,modLoaded("mekanism",true),IRRADIUM_DEFAULT,IRRADIUM_ARMOR),
    PNEUMATIC_STEEL(true,TiAcMeMaterialIds.PnC.PNEUMATIC_STEEL,4,true,false,EnumMaterialStats.PNEUMATIC_STEEL,modLoaded("pneumaticcraft",true),PNEUMATIC_STEEL_ARMOR,PNEUMATIC_STEEL_DEFAULT),
    BASALZ_SIGNALUM(true,TiAcMeMaterialIds.Thermal.BASALZ_SIGNALUM,3,false,false,EnumMaterialStats.BASALZ_SIGNALUM,modLoaded("thermal",true),BASALZ_SIGNALUM_ARMOR,BASALZ_SIGNALUM_DEFAULT),
    BLITZ_LUMIUM(true,TiAcMeMaterialIds.Thermal.BLITZ_LUMIUM,4,false,false,EnumMaterialStats.BLITZ_LUMIUM,modLoaded("thermal",true),BLITZ_LUMIUM_ARMOR,BLITZ_LUMIUM_DEFAULT),
    BLIZZ_ENDERIUM(true,TiAcMeMaterialIds.Thermal.BLIZZ_ENDERIUM,4,false,false,EnumMaterialStats.BLIZZ_ENDERIUM,modLoaded("thermal",true),BLIZZ_ENDERIUM_ARMOR,BLIZZ_ENDERIUM_DEFAULT),
    ACTIVATED_CHROMATIC_STEEL(true,TiAcMeMaterialIds.Thermal.ACTIVATED_CHROMATIC_STEEL,4,false,false,EnumMaterialStats.ACTIVATED_CHROMA_STEEL,modLoaded("thermal",true),ACTIVATED_CHROMA_STEEL_MELEE,ACTIVATED_CHROMA_STEEL_ARMOR,ACTIVATED_CHROMA_STEEL_RANGED),
    BLAZE_NETHERITE(true,TiAcMeMaterialIds.BLAZE_NETHERITE,4,false,false,EnumMaterialStats.BLAZE_NETHERITE,noopCompat(true),EnumMaterialModifier.BLAZE_NETHERITE),
    IRIDIUM(true,TiAcMeMaterialIds.IRIDIUM,4,false,false,EnumMaterialStats.IRIDIUM,noopCompat(true), IRIDIUM_DEFAULT,IRIDIUM_ARMOR,IRIDIUM_FLUX_CORE),
    NEUTRONITE(true,TiAcMeMaterialIds.Mekanism.NEUTRONITE,128,false,true,EnumMaterialStats.NEUTRONITE,modLoaded("mekanism",true), NEUTRONITE_DEFAULT,NEUTRONITE_ARMOR),
    OSGLOGLAS(true,TiAcMeMaterialIds.Mekanism.OSGLOGLAS,4,false,false,EnumMaterialStats.OSGLOGLAS,modLoaded("mekanism",true), OSGLOGLAS_DEFAULT),
    DISINTEGRATE_CRYSTAL(true,TiAcMeMaterialIds.DISINTEGRATE_CRYSTAL,4,true,false,EnumMaterialStats.DISINTEGRATE_CRYSTAL,modLoaded("tinkers_advanced_tools",true),DISINTEGRATE_CRYSTAL_DEFAULT),
    RESONANCE_CRYSTAL(true,TiAcMeMaterialIds.RESONANCE_CRYSTAL,4,true,false,EnumMaterialStats.RESONANCE_CRYSTAL,modLoaded("tinkers_advanced_tools",true),RESONATE_CRYSTAL_ARMOR,RESONATE_CRYSTAL_DEFAULT),
    VOLTAIC_CRYSTAL(true,TiAcMeMaterialIds.VOLTAIC_CRYSTAL,4,true,false,EnumMaterialStats.VOLTAIC_CRYSTAL,modLoaded("tinkers_advanced_tools",true),VOLTAIC_CRYSTAL_DEFAULT),
    PLASTIC(TiAcMeMaterialIds.CommonIntegration.PLASTIC,2,true,false,EnumMaterialStats.PLASTIC,tagFilled(TiAcMeTagkeys.Items.PLASTIC,TinkersAdvanced.MODID,false),PLASTIC_DEFAULT),
    PROTOCITE(true,TiAcMeMaterialIds.Mekanism.PROTOCITE,4,false,false,EnumMaterialStats.PROTOCITE,modLoaded("mekanism",true),PROTOCITE_ARMOR,PROTOCITE_DEFAULT),
    COMPRESSED_IRON(TiAcMeMaterialIds.PnC.COMPRESSED_IRON,2,true,false,EnumMaterialStats.COMPRESSED_IRON,tagFilled(PneumaticCraftTags.Items.INGOTS_COMPRESSED_IRON,"pneumaticcraft",false),COMPRESSED_IRON_ARMOR,COMPRESSED_IRON_DEFAULT),
    PINK_SLIME_STEEL(TiAcMeMaterialIds.IndustrialForgoing.PINK_SLIME_METAL,3,false,false,EnumMaterialStats.PINK_SLIME_METAL,modLoaded("industrialforegoing",true),PINK_SLIME_METAL),
    NUTRITIVE_SLIMESTEEL(true,TiAcMeMaterialIds.Mekanism.NUTRITIVE_SLIMESTEEL,3,false,false,EnumMaterialStats.NUTRITIVE_SLIMESTEEL,modLoaded("mekanism",true),EnumMaterialModifier.NUTRITIVE_SLIMESTEEL),
    VOID_STEEL(TiAcMeMaterialIds.CreateUtilities.VOID_STEEL,4,false,false,EnumMaterialStats.VOID_STEEL,modLoaded("createutilities",true),VOID_STEEL_ARMOR,VOID_STEEL_DEFAULT),
    ;
    public final MaterialId id;
    public final int tier;
    public final boolean craftable;
    public final boolean hidden;
    public final EnumMaterialStats stats;
    public final EnumMaterialModifier[] modifiers;
    public final ICondition condition;
    public final boolean original;
    EnumMaterial(boolean original,MaterialId id, int tier, boolean craftable, boolean hidden, EnumMaterialStats stats,@NotNull ICondition condition, EnumMaterialModifier... modifiers){
        this.original = original;
        this.id = id;
        this.tier =tier;
        this.craftable = craftable;
        this.hidden = hidden;
        this.stats = stats;
        this.modifiers = modifiers;
        if (original) condition = new AndCondition(condition, TiAcCrConditions.ALLOW_ORIGINAL_MATERIALS);
        this.condition = condition;
    }
    EnumMaterial(MaterialId id, int tier, boolean craftable, boolean hidden, EnumMaterialStats stats, @NotNull ICondition condition, EnumMaterialModifier... modifiers){
        this(false,id,tier,craftable,hidden,stats,condition,modifiers);
    }
    public static ICondition modLoaded(String modId,boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(modId,isOriginal),new ModLoadedCondition(modId));
    }
    public static ICondition tagFilled(TagKey<Item> tagKey,String modId,boolean isOriginal){
        return new AndCondition( new TagFilledCondition<>(tagKey),new CompatConfigCondition(modId,isOriginal));
    }
    public static ICondition noopCompat(boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(TinkersAdvanced.MODID,isOriginal));
    }
}
