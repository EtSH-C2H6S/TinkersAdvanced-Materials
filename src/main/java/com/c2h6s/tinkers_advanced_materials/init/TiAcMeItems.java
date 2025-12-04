package com.c2h6s.tinkers_advanced_materials.init;

import com.c2h6s.tinkers_advanced.core.init.TiAcCrItem;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.content.item.HiddenMaterial;
import com.c2h6s.tinkers_advanced_materials.TinkersAdvancedMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.c2h6s.tinkers_advanced.TinkersAdvanced.MODID;
import static com.c2h6s.tinkers_advanced.core.init.TiAcCrItem.registerBlockItem;
import static com.c2h6s.tinkers_advanced.core.init.TiAcCrItem.registerSimpleBlockItem;
import static com.c2h6s.tinkers_advanced.core.init.TiAcCrItem.ITEMS;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeItems {
    @SubscribeEvent
    public static void init(TiAcLoadRegistryClassEvent event){}
    public static final DeferredRegister<Item> MEK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Item> PNC_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Item> THERMAL_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Item> IF_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static Map<RegistryObject<? extends Item>,String> ITEM_MODID_MAP = new HashMap<>();
    public static Map<RegistryObject<? extends Item>,Boolean> ITEM_ORIGINAL_MAP = new HashMap<>();

    public static RegistryObject<Item> registerMaterial(String compatModId,boolean isOriginal,String modId, DeferredRegister<Item> register, String name, Supplier<? extends Item> sup, boolean simpleModel){
        var obj = TiAcCrItem.registerMaterial(modId,register,name,sup,simpleModel);
        ITEM_MODID_MAP.put(obj,compatModId);
        ITEM_ORIGINAL_MAP.put(obj,isOriginal);
        return obj;
    }

    public static final RegistryObject<BlockItem> BISMUTHINITE_ORE = registerSimpleBlockItem(TinkersAdvancedMaterials.MODID,ITEMS, TiAcMeBlocks.BISMUTHINITE);
    public static final RegistryObject<BlockItem> IRIDIUM_LEAN_ORE = registerSimpleBlockItem(TinkersAdvancedMaterials.MODID,ITEMS, TiAcMeBlocks.IRIDIUM_LEAN_ORE);
    public static final RegistryObject<BlockItem> STIBNITE_ORE = registerBlockItem(TinkersAdvancedMaterials.MODID,ITEMS, TiAcMeBlocks.STIBNITE_ORE);


    public static final RegistryObject<Item> BISMUTH_INGOT = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"bismuth_ingot",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> BISMUTH_NUGGET = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"bismuth_nugget",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> BISMUTHINITE = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"bismuthinite",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> BLAZE_NETHERITE = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"blaze_netherite",()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()),true);
    public static final RegistryObject<Item> IRIDIUM_CHUNK = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"iridium_chunk",()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON)),true);
    public static final RegistryObject<Item> ANTIMONY_INGOT = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"antimony_ingot",()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON)),true);
    public static final RegistryObject<Item> ANTIMONY_NUGGET = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"antimony_nugget",()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON)),true);
    public static final RegistryObject<Item> STIBNITE = registerMaterial(MODID,true,TinkersAdvancedMaterials.MODID,ITEMS,"stibnite",()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON)),true);


    public static final RegistryObject<Item> IRRADIUM_INGOT = registerMaterial("mekanism",true,TinkersAdvancedMaterials.MODID,MEK_ITEMS,"irradium_ingot",()->new Item(new Item.Properties().rarity(Rarity.RARE)),true);
    public static final RegistryObject<Item> PROTOCITE_PELLET = registerMaterial("mekanism",true,TinkersAdvancedMaterials.MODID,MEK_ITEMS,"protocite_pellet",()->new Item(new Item.Properties().rarity(Rarity.RARE)),true);
    public static final RegistryObject<Item> OSGLOGLAS_INGOT = registerMaterial("mekanism",true,TinkersAdvancedMaterials.MODID,MEK_ITEMS,"osgloglas_ingot",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> NEUTRONITE_INGOT = registerMaterial("mekanism",true,TinkersAdvancedMaterials.MODID,MEK_ITEMS,"neutronite_ingot",()->new HiddenMaterial(new Item.Properties().rarity(Rarity.EPIC).fireResistant(),List.of(
            Component.translatable("tooltip.tinkers_advanced.hidden_material_mek").withStyle(ChatFormatting.LIGHT_PURPLE),
            Component.translatable("tooltip.tinkers_advanced.neutronite_1").withStyle(ChatFormatting.GRAY),
            Component.translatable("tooltip.tinkers_advanced.neutronite_2"),
            Component.translatable("tooltip.tinkers_advanced.neutronite_3").withStyle(ChatFormatting.DARK_AQUA)
    ), TiAcMeConfig.COMMON.EXPLODING_FUSION_REACTOR),true);
    public static final RegistryObject<Item> NUTRITION_SLIME_INGOT = registerMaterial("mekanism",true,TinkersAdvancedMaterials.MODID,MEK_ITEMS,"nutrition_slime_ingot",()->new Item(new Item.Properties()),true);



    public static final RegistryObject<Item> PNEUMATIC_STEEL = registerMaterial("pneumaticcraft",true,TinkersAdvancedMaterials.MODID,PNC_ITEMS,"penumatic_reinforced_steel",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> PNEUMATIC_STEEL_HOT = registerMaterial("pneumaticcraft",true,TinkersAdvancedMaterials.MODID,PNC_ITEMS,"hot_reinforced_steel",()->new Item(new Item.Properties()),true);



    public static final RegistryObject<Item> BASALZ_SIGNALUM = registerMaterial("thermal",true,TinkersAdvancedMaterials.MODID,THERMAL_ITEMS,"basalz_signalum",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> BLITZ_LUMIUM = registerMaterial("thermal",true,TinkersAdvancedMaterials.MODID,THERMAL_ITEMS,"blitz_lumium",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> BLITZ_LUMIUM_NUGGET = registerMaterial("thermal",true,TinkersAdvancedMaterials.MODID,THERMAL_ITEMS,"blitz_lumium_nugget",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> BLIZZ_ENDERIUM = registerMaterial("thermal",true,TinkersAdvancedMaterials.MODID,THERMAL_ITEMS,"blizz_enderium",()->new Item(new Item.Properties()),true);
    public static final RegistryObject<Item> ACTIVATED_CHROMATIC_STEEL = registerMaterial("thermal",true,TinkersAdvancedMaterials.MODID,THERMAL_ITEMS,"activated_chromatic_steel",()->new Item(new Item.Properties().rarity(Rarity.EPIC).fireResistant()),true);
}
