package com.c2h6s.tinkers_advanced_materials.util;

import com.c2h6s.tinkers_advanced.TiAcCrConfig;
import com.c2h6s.tinkers_advanced.core.content.entity.VisualScaledProjectile;
import com.c2h6s.tinkers_advanced.core.library.interfaces.IHiddenMaterial;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeFluids;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

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
        if (mInfo==null||mInfo.getCompatModId()==null) return false;
        if (mInfo.isOriginal()&&!allowOriginal) return true;
        return mInfo.isOriginal()? listCpO.contains(mInfo.getCompatModId()):listCp.contains(mInfo.getCompatModId());
    }

    public static int getBarLength(float amount, float max){
        return max > 0.0F ? (int) Math.min(13.0F, 13.0F * amount / max) : 0;
    }
    public static Vec2 getBarVec2(float amount, float max){
        return new Vec2(getBarLength(amount,max),1);
    }

    public static boolean validateUnequipResult(@NotNull IToolStackView tool, IToolStackView replacement){
        return replacement == null || replacement.getItem() != tool.getItem() || !replacement.getModifiers().equals(tool.getModifiers());
    }
    public static boolean validateEquipResult(@NotNull IToolStackView tool,IToolStackView originalTool){
        return originalTool == null || originalTool.getItem() != tool.getItem() || !originalTool.getModifiers().equals(tool.getModifiers());
    }

    public static boolean checkTarget(Projectile projectile , Entity target){
        return checkTarget(projectile.getOwner() instanceof LivingEntity living?living:null,target);
    }
    public static boolean checkTarget(@Nullable LivingEntity attacker , Entity target){
        if (!TiAcCrConfig.COMMON.ALLOW_AOE_ATTACK_PLAYER.get()&&target instanceof Player) return false;
        if (target==attacker) return false;
        if (target instanceof VisualScaledProjectile ||target instanceof ItemEntity ||target instanceof ExperienceOrb)
            return false;
        if (attacker instanceof Player player&&target instanceof Player player1) return player.canHarmPlayer(player1);

        return attacker == null || !target.isAlliedTo(attacker);
    }

    public static void setFly(Player player){
        player.getAbilities().mayfly = true;
        if (player.getPersistentData().getBoolean("tiac_legacy_fly")) {
            player.getAbilities().flying = true;
        }
    }
    public static void setNotFly(Player player){
        player.getPersistentData().putBoolean("tiac_legacy_fly",player.getAbilities().flying);
        player.getAbilities().mayfly = false;
        player.getAbilities().flying = false;
    }
}
