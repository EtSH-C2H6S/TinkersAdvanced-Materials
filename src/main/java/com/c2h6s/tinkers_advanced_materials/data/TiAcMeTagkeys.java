package com.c2h6s.tinkers_advanced_materials.data;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierManager;


public class TiAcMeTagkeys {
    public static class Fluids {
        private static TagKey<Fluid> forgeTag(String name){
            return TagKey.create(ForgeRegistries.FLUIDS.getRegistryKey(),new ResourceLocation("forge",name));
        }

        public static final TagKey<Fluid> MOLTEN_ANTIMATTER = forgeTag("molten_antimatter");
        public static final TagKey<Fluid> MOLTEN_BISMUTH = forgeTag("molten_bismuth");
        public static final TagKey<Fluid> MOLTEN_IRIDIUM = forgeTag("molten_iridium");
        public static final TagKey<Fluid> MOLTEN_ANTIMONY = forgeTag("molten_antimony");
        public static final TagKey<Fluid> MOLTEN_VOID_STEEL = forgeTag("molten_void_steel");
        public static final TagKey<Fluid> MOLTEN_PRISMALIC_ALLOY = forgeTag("molten_prismalic_alloy");
        public static final TagKey<Fluid> MOLTEN_STELLAR_ALLOY = forgeTag("molten_stellar_alloy");
        public static final TagKey<Fluid> MOLTEN_MELODIC_ALLOY = forgeTag("molten_melodic_alloy");
    }

    public static class Items{
        private static TagKey<Item> forgeTag(String name){
            return TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation("forge",name));
        }
        private static TagKey<Item> tiacTag(String name){
            return TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(),new ResourceLocation(TinkersAdvanced.MODID,name));
        }

        public static final TagKey<Item> BISMUTH_INGOT = forgeTag("ingots/bismuth");
        public static final TagKey<Item> BISMUTH_NUGGET = forgeTag("nuggets/bismuth");
        public static final TagKey<Item> BISMUTH_ORE = forgeTag("ores/bismuthinite");
        public static final TagKey<Item> IRIDIUM_INGOT = forgeTag("ingots/iridium");
        public static final TagKey<Item> ANTIMONY_INGOT = forgeTag("ingots/antimony");
        public static final TagKey<Item> ANTIMONY_NUGGET = forgeTag("nuggets/antimony");
        public static final TagKey<Item> STIBNITE_ORE = forgeTag("ores/stibnite");
        public static final TagKey<Item> IRIDIUM_NUGGET = forgeTag("nuggets/iridium");
        public static final TagKey<Item> IRIDIUM_BLOCK = forgeTag("storage_blocks/iridium");
        public static final TagKey<Item> PLASTIC = tiacTag("plastic");
//        public static final TagKey<Item> PRIAMALIC_ALLOY_INGOT = forgeTag("ingots/prismalic_alloy");
//        public static final TagKey<Item> STELLAR_ALLOY_INGOT = forgeTag("ingots/stellar_alloy");
//        public static final TagKey<Item> MELODIC_ALLOY_INGOT = forgeTag("ingots/melodic_alloy");
//        public static final TagKey<Item> PRIAMALIC_ALLOY_NUGGET = forgeTag("nuggets/prismalic_alloy");
//        public static final TagKey<Item> STELLAR_ALLOY_NUGGET = forgeTag("nuggets/stellar_alloy");
//        public static final TagKey<Item> MELODIC_ALLOY_NUGGET = forgeTag("nuggets/melodic_alloy");
//        public static final TagKey<Item> PRIAMALIC_ALLOY_BLOCK = forgeTag("storage_blocks/prismalic_alloy");
//        public static final TagKey<Item> STELLAR_ALLOY_BLOCK = forgeTag("storage_blocks/stellar_alloy");
//        public static final TagKey<Item> MELODIC_ALLOY_BLOCK = forgeTag("storage_blocks/melodic_alloy");
    }

    public static class Modifiers{
        private static TagKey<Modifier> tiacTag(String name){
            return ModifierManager.getTag(new ResourceLocation(TinkersAdvanced.MODID,name));
        }

        public static final TagKey<Modifier> GENERATOR_MODIFIERS = tiacTag("generator_modifiers");
        public static final TagKey<Modifier> SPECIAL_TOOL = tiacTag("special_tool");
    }
}
