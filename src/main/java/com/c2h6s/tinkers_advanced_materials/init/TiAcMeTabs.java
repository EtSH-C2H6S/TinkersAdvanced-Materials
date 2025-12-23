package com.c2h6s.tinkers_advanced_materials.init;

import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.content.item.HiddenMaterial;
import com.c2h6s.tinkers_advanced.core.init.TiAcCrItem;
import com.c2h6s.tinkers_advanced_materials.TinkersAdvancedMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import static com.c2h6s.tinkers_advanced.core.init.TiAcCrTabs.CREATIVE_MODE_TABS;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeTabs {
    @SubscribeEvent
    public static void init(TiAcLoadRegistryClassEvent event){}

    public static final RegistryObject<CreativeModeTab> MATERIAL_TAB = CREATIVE_MODE_TABS.register("tiac_material", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tinkers_advanced.tiac_material"))
            .icon(() -> TiAcMeItems.BISMUTH_INGOT.get().getDefaultInstance())
            .build());
    public static final RegistryObject<CreativeModeTab> BLOCK_TAB = CREATIVE_MODE_TABS.register("tiac_block", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tinkers_advanced.tiac_block"))
            .icon(() -> TiAcMeItems.BISMUTHINITE_ORE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                for (RegistryObject<BlockItem> object:TiAcCrItem.getListSimpleBlock(TinkersAdvancedMaterials.MODID)){
                    if (object.isPresent()) {
                        output.accept(object.get());
                    }
                }
                for (RegistryObject<BlockItem> object:TiAcCrItem.getListMiscBlock(TinkersAdvancedMaterials.MODID)){
                    if (object.isPresent()) {
                        output.accept(object.get());
                    }
                }
            })
            .build());

}
