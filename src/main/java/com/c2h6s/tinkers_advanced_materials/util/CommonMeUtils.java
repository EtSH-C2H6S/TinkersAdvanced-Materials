package com.c2h6s.tinkers_advanced_materials.util;

import com.c2h6s.tinkers_advanced.TiAcCrConfig;
import com.c2h6s.tinkers_advanced.core.library.interfaces.IHiddenMaterial;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.FluidObject;

public class CommonMeUtils {
    public static boolean shouldHide(RegistryObject<? extends Item> object){
        if (!object.isPresent()) return true;
        if (object.get() instanceof IHiddenMaterial hiddenMaterial&&!hiddenMaterial.getConfig().get()) return true;
        boolean allowOriginal = TiAcCrConfig.COMMON.ALLOW_ORIGINAL_MATERIALS.get();
        if (!allowOriginal&&TiAcMeItems.ITEM_ORIGINAL_MAP.getOrDefault(object,false)) return true;
        var listCp = TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT.get();
        var listCpO = TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT_ORIGINAL.get();
        if(TiAcMeItems.ITEM_ORIGINAL_MAP.getOrDefault(object, false)){
            return listCpO.contains(TiAcMeItems.ITEM_MODID_MAP.getOrDefault(object,"NULL_MOD_ID"));
        } else return listCp.contains(TiAcMeItems.ITEM_MODID_MAP.getOrDefault(object,"NULL_MOD_ID"));
    }
    public static boolean shouldHide(FluidObject<? extends ForgeFlowingFluid> object){
        try {
            object.get();
        } catch (Exception ignored){
            return true;
        }
        boolean allowOriginal = TiAcCrConfig.COMMON.ALLOW_ORIGINAL_MATERIALS.get();
        if (!allowOriginal&& TiAcMeFluids.FLUID_ORIGINAL_MAP.getOrDefault(object,false)) return true;
        var listCp = TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT.get();
        var listCpO = TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT_ORIGINAL.get();
        if(TiAcMeFluids.FLUID_ORIGINAL_MAP.getOrDefault(object, false)){
            return listCpO.contains(TiAcMeFluids.FLUID_MODID_MAP.getOrDefault(object,"NULL_MOD_ID"));
        } else return listCp.contains(TiAcMeFluids.FLUID_MODID_MAP.getOrDefault(object,"NULL_MOD_ID"));
    }
    public static boolean shouldHide(SimpleMaterialObject object){
        boolean allowOriginal = TiAcCrConfig.COMMON.ALLOW_ORIGINAL_MATERIALS.get();
        var listCp = TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT.get();
        var listCpO = TiAcCrConfig.COMMON.LIST_BLACKLIST_COMPAT_ORIGINAL.get();
        var item = object.getItemObject();
        if (item==null) return true;
        try {
            if (item.get() instanceof IHiddenMaterial hiddenMaterial&&!hiddenMaterial.getConfig().get()) return true;
        } catch (Exception ignored){}
        var mInfo = object.getMaterialInfo();
        if (mInfo==null) return false;
        if (mInfo.isOriginal()&&!allowOriginal) return true;
        return mInfo.isOriginal()? listCpO.contains(mInfo.getCompatModId()):listCp.contains(mInfo.getCompatModId());
    }

    public static int getBarLength(float amount, float max){
        return max > 0.0F ? (int) Math.min(13.0F, 13.0F * amount / max) : 0;
    }
    public static Vec2 getBarVec2(float amount, float max){
        return new Vec2(getBarLength(amount,max),1);
    }
}
