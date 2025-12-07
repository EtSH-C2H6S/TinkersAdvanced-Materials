package com.c2h6s.tinkers_advanced_materials;

import com.c2h6s.tinkers_advanced.core.init.TiAcCrItem;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeTabs;
import com.c2h6s.tinkers_advanced_materials.network.TiAcMePacketHandler;
import com.c2h6s.tinkers_advanced_materials.util.CommonMeUtils;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(TinkersAdvancedMaterials.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TinkersAdvancedMaterials {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "tinkers_advanced_materials";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public TinkersAdvancedMaterials() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        TiAcMeModule.register(modEventBus);
        TiAcMeConfig.init();
        TiAcMePacketHandler.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public static void addTabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey()== TiAcMeTabs.MATERIAL_TAB.getKey()){
            TiAcMeMaterials.MATERIALS.getEntryMap().values().forEach(object -> {
                if (!CommonMeUtils.shouldHide(object)&&object.getItemObject()!=null){
                    event.accept(object.getItemObject().get());
                }
            });
            TiAcCrItem.getListMaterial(MODID).forEach(object -> {
                if (object.isPresent()&&!CommonMeUtils.shouldHide(object)) event.accept(object.get());
            });
        }
    }
}
