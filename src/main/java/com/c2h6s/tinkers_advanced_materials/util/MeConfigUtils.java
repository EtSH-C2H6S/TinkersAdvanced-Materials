package com.c2h6s.tinkers_advanced_materials.util;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.item.EffectStarItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class MeConfigUtils {
    public static <T extends Item> List<T> readItemsFromConfig(ForgeConfigSpec.ConfigValue<List<String>> listConfigValue,Class<T> classFilter){
        List<T> list = new ArrayList<>();
        for (var name: listConfigValue.get()){
            var id = new ResourceLocation(name);
            var item = ForgeRegistries.ITEMS.getValue(id);
            if (!(classFilter.isInstance(item))) {
                TinkersAdvanced.LOGGER.warn("Skipped item with id:\"{}\" since it didn't implement/inherit class \"{}\" or is unregistered.",name,classFilter.getName());
                continue;
            }
            list.add(classFilter.cast(item));
        }
        return list;
    }
    public static List<Item> readItemsFromConfig(ForgeConfigSpec.ConfigValue<List<String>> listConfigValue){
        return readItemsFromConfig(listConfigValue,Item.class);
    }
}
