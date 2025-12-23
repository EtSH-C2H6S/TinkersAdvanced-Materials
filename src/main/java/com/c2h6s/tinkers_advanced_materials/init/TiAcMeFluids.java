package com.c2h6s.tinkers_advanced_materials.init;

import cofh.core.init.CoreMobEffects;
import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.TiAcCrModule;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import mekanism.common.lib.radiation.RadiationManager;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.fluids.block.BurningLiquidBlock;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeFluids {
    @SubscribeEvent
    public static void init(TiAcLoadRegistryClassEvent event){}

    public static final FluidDeferredRegister MEK_FLUIDS = new FluidDeferredRegister(TinkersAdvanced.MODID);
    public static final FluidDeferredRegister THERMAL_FLUIDS = new FluidDeferredRegister(TinkersAdvanced.MODID);
    public static final FluidDeferredRegister IF_FLUIDS = new FluidDeferredRegister(TinkersAdvanced.MODID);
    public static final FluidDeferredRegister CREATE_UTILITIES_FLUIDS = new FluidDeferredRegister(TinkersAdvanced.MODID);
    protected static Map<FluidObject<?>,Boolean> FLUID_MAP = new HashMap<>();
    public static Map<FluidObject<?>,String> FLUID_MODID_MAP = new HashMap<>();
    public static Map<FluidObject<?>,Boolean> FLUID_ORIGINAL_MAP = new HashMap<>();
    public static Set<FluidObject<?>> getFluids(){
        return FLUID_MAP.keySet();
    }
    public static Map<FluidObject<?>,Boolean> getFluidMap(){
        return FLUID_MAP;
    }
    private static FluidObject<?> registerHotBurning(String compatModId,boolean isOriginal,FluidDeferredRegister register,String name,int temp,int lightLevel,int burnTime,float damage,boolean gas){
        var builder = register.register(name).type(hot(name,temp,gas)).bucket().block(createBurning(MapColor.COLOR_GRAY,lightLevel,burnTime,damage)).commonTag();
        var object = gas?builder.invertedFlowing():builder.flowing();
        FLUID_MAP.put(object,gas);
        FLUID_MODID_MAP.put(object,compatModId);
        FLUID_ORIGINAL_MAP.put(object,isOriginal);
        return object;
    }
    private static FluidObject<?> registerFluid(String compatModId,boolean isOriginal,FluidDeferredRegister register, String name,int temp, Function<Supplier<? extends FlowingFluid>, LiquidBlock> blockFunction, boolean gas){
        var builder = register.register(name).type(hot(name,temp,gas)).bucket().block(blockFunction).commonTag();
        var object = gas?builder.invertedFlowing():builder.flowing();
        FLUID_MAP.put(object,gas);
        FLUID_MODID_MAP.put(object,compatModId);
        FLUID_ORIGINAL_MAP.put(object,isOriginal);
        return object;
    }

    public static final FluidObject<?> MOLTEN_BISMUTH = registerHotBurning(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"molten_bismuth",770,1,4,0.5f,false);
    public static final FluidObject<?> MOLTEN_BLAZE_NETHERITE = registerHotBurning(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"molten_blaze_netherite",1920,15,1920,9f,false);
    public static final FluidObject<?> MOLTEN_IRIDIUM = registerHotBurning(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"molten_iridium",1375,10,20,3f,false);
    public static final FluidObject<?> MOLTEN_ANTIMONY = registerHotBurning(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"molten_antimony",970,5,16,2f,false);


    public static final FluidObject<?> OVER_HEATED_LAVA = registerHotBurning(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"over_heated_lava",2300,15,200,6.5f,false);
    public static final FluidObject<?> GASEOUS_LAVA = registerFluid(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"gaseous_lava",3300, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 200, 8){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (entity instanceof LivingEntity living) {
                living.hurt(LegacyDamageSource.any(living.damageSources().lava()).setBypassInvulnerableTime().setBypassArmor().setBypassShield(),0.5f);
                living.setSecondsOnFire(1000);
            }
        }
    },true);
    public static final FluidObject<?> PLASMATIC_LAVA = registerFluid(TinkersAdvanced.MODID,true,TiAcCrModule.FLUIDS,"plasmatic_lava",4300, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 200, 8){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (entity instanceof LivingEntity living) {
                living.hurt(LegacyDamageSource.any(living.damageSources().generic()).setBypassInvulnerableTime().setBypassArmor().setBypassEnchantment().setBypassMagic().setBypassShield().setMsgId("plasma"),2);
                living.setSecondsOnFire(100000);
            }
        }
    },true);


    public static final FluidObject<?> PYROTHEUM = registerHotBurning("thermal",true,THERMAL_FLUIDS,"pyrotheum",3273,15,2560,15f,false);
    public static final FluidObject<?> MOLTEN_BASALZ_SIGNALUM = registerFluid("thermal",true,THERMAL_FLUIDS,"molten_basalz_signalum",950, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 7), 200, 5){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            super.entityInside(state, level, pos, entity);
            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(CoreMobEffects.SUNDERED.get(),100));
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_BILTZ_LUMIUM = registerFluid("thermal",true,THERMAL_FLUIDS,"molten_biltz_lumium",1440, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 200, 5){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            super.entityInside(state, level, pos, entity);
            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(CoreMobEffects.SHOCKED.get(),100));
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_BLIZZ_ENDERIUM = registerFluid("thermal",true,THERMAL_FLUIDS,"molten_blizz_enderium",0, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 0, 0){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(CoreMobEffects.CHILLED.get(),100));
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_ACTIVATED_CHROMATIC_STEEL = registerFluid("thermal",true,THERMAL_FLUIDS,"molten_activated_chromatic_steel",2440, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 0, 0){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(CoreMobEffects.CHILLED.get(),100));
                living.addEffect(new MobEffectInstance(CoreMobEffects.SHOCKED.get(),100));
                living.addEffect(new MobEffectInstance(CoreMobEffects.SUNDERED.get(),100));
                living.invulnerableTime=0;
                living.hurt(level.damageSources().lava(),0.5f);
            }
        }
    },false);


    public static final FluidObject<?> MOLTEN_IRRADIUM = registerFluid("mekanism",true,MEK_FLUIDS,"molten_irradium",2250, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 200, 8){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            super.entityInside(state, level, pos, entity);
            if (entity instanceof LivingEntity living) {
                RadiationManager.get().radiate(living,0.01);
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_OSGLOGLAS = registerFluid("mekanism",true,MEK_FLUIDS,"molten_osgloglas",1750, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 200, 8){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            super.entityInside(state, level, pos, entity);
            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(MobEffects.GLOWING,20000,0,false,false));
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_ANTIMATTER = registerHotBurning("mekanism",false,MEK_FLUIDS,"molten_antimatter",2980,15,16384,17.5f,true);
    public static final FluidObject<?> MOLTEN_NEUTRONITE = registerFluid("mekanism",true,MEK_FLUIDS,"molten_neutronite",9973, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 262144, 15){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            super.entityInside(state, level, pos, entity);
            if (entity instanceof LivingEntity living) {
                living.hurt(LegacyDamageSource.any(living.damageSources().generic()).setBypassInvulnerableTime().setBypassArmor().setBypassEnchantment().setBypassMagic().setBypassShield().setMsgId("plasma"),25);
                living.invulnerableTime=0;
                RadiationManager.get().radiate(living,1000);
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_PROTOCITE = registerFluid("mekanism",true,MEK_FLUIDS,"molten_protocite",2250, supplier -> new BurningLiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15), 200, 8){
        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            super.entityInside(state, level, pos, entity);
            if (entity instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(TiAcMeEffects.PROTO_POISON.get(),200,0));
            }
        }
    },false);
    public static final FluidObject<?> MOLTEN_NUTRITIVE_SLIMESTEEL = registerHotBurning("mekanism",true,MEK_FLUIDS,"molten_nutritive_slime",980,7,19,3f,false);


    public static final FluidObject<?> MOLTEN_PINK_SLIME = registerHotBurning("industrialforegoing",false,IF_FLUIDS,"molten_pink_slime",990,7,20,3f,false);


    public static final FluidObject<?> MOLTEN_VOID_STEEL = registerHotBurning("createutilities",false,CREATE_UTILITIES_FLUIDS,"molten_void_steel",1400,15,100,3f,false);


    private static FluidType.Properties hot(String name,int Temp,boolean gas) {
        return FluidType.Properties.create().density(gas?-2000:2000).viscosity(10000).temperature(Temp)
                .descriptionId("fluid."+TinkersAdvanced.MODID+"."+name)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                .motionScale(0.0023333333333333335D)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }
}
