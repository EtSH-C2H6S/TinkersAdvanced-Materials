package com.c2h6s.tinkers_advanced_materials.init;

import com.c2h6s.etstlib.data.EtSTLibModifierIds;
import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialWrappedRegister;
import com.c2h6s.tinkers_advanced.core.content.tool.tinkering.materialStat.FluxCoreMaterialStat;
import com.c2h6s.tinkers_advanced_materials.data.TiAcMeMaterialIds;
import com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.client.materials.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.utils.TeleportHelper;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.stats.*;

import static com.c2h6s.tinkers_advanced.core.util.CommonConstants.Materials.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeMaterials {
    @SubscribeEvent
    public static void init(TiAcLoadRegistryClassEvent event){}
    public static final SimpleMaterialWrappedRegister MATERIALS = new SimpleMaterialWrappedRegister(TinkersAdvanced.MODID);
    public static PlatingMaterialStats.Builder armor(int durabilityFactor, float helmet, float chestplate, float leggings, float boots){
        return PlatingMaterialStats.builder().durabilityFactor(durabilityFactor).armor(boots,leggings,chestplate,helmet);
    }
    public static ModifierEntry entry(ModifierId id, int level){
        return new ModifierEntry(id,level);
    }
    public static ModifierEntry entry(ModifierId id){
        return new ModifierEntry(id,1);
    }


    //mek原创
    public static final SimpleMaterialObject DENSIUM = MATERIALS.buildMaterial("densium")
            .registerItem(()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON))).setItemIngot()
            .registerBurningFluid(2250,false,7,256,7).setUnit()
            .addCompatModId("mekanism",true).buildMaterial(5,false,ORDER_ORIGINAL,false)
            .buildStats().setStats(StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(1.2f,0.2f,-0.55f,1.15f),
                    new HeadMaterialStats(2048,10f, Tiers.NETHERITE,8f),
                    new GripMaterialStats(1.2f,0.2f,8f),
                    new FluxCoreMaterialStat(16.4f,18.6f),
                    new LimbMaterialStats(2048,-0.55f,1.75f,0.2f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(128,10f,12f,11f,10f)
                    .toughness(3.5f).knockbackResistance(10)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(EtSTLibModifier.EXTRA_DENSE.getId()))
            .buildPerStat(HeadMaterialStats.ID,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifierIds.RUDE))
            .buildPerStat(HandleMaterialStats.ID,entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(EtSTLibModifier.momentum_accelerate.getId(),5),entry(TiAcMeModifiers.HEAVY_MATERIAL.getId()))
            .buildPerStat(StatlessMaterialStats.BINDING.getIdentifier(),entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(EtSTLibModifier.momentum_accelerate.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(EtSTLibModifier.HYPER_DENSITY.getId()))
            .buildPerStat(MaterialRegistry.RANGED,entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(TinkerModifiers.momentum.getId(),5),entry(TiAcMeModifiers.HEAVY_MATERIAL.getId()))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{
                    "metal"
            },0xFF270053,15)).buildMaterialSprite().ranged().meleeHarvest().armor().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFF6000C0)
                    .addARGB(102,0xFF8000C0)
                    .addARGB(140,0xFF0B0020)
                    .addARGB(178,0xFF200040)
                    .addARGB(216,0xFF400060)
                    .addARGB(255,0xFF550080).build()).build().build().setFluidCustomTexture();

    public static final SimpleMaterialObject ANTI_NEUTRONIUM = MATERIALS.buildMaterial("anti_neutronium")
            .registerItem(()->new Item(new Item.Properties().rarity(Rarity.EPIC))).setItemIngot()
            .registerFluid(262144,false,supplier ->
                    new LiquidBlock(supplier, FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY, 15)){
                        @Override
                        public void entityInside(BlockState state, Level level, BlockPos p_60497_, Entity entity) {
                            if (entity instanceof Player)
                                entity.hurt(LegacyDamageSource.explosion(entity,entity).setBypassArmor().setBypassInvulnerableTime()
                                    .setBypassMagic().setBypassEnchantment().setBypassShield(),100);
                        }
                    }
            ).setUnit()
            .addCompatModId("mekanism",true).buildMaterial(5,false,ORDER_ORIGINAL,false)
            .buildStats().setStats(StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(2f,2f,-0.6f,2f),
                    new HeadMaterialStats(16384,10f, Tiers.NETHERITE,14f),
                    new FluxCoreMaterialStat(262144f,262144f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(128,30f,30f,30f,30f)
                    .toughness(20f).knockbackResistance(10)).setAllowShield().build().buildModifiers()
            .buildPerStat(MaterialRegistry.MELEE_HARVEST,entry(EtSTLibModifier.EXTRA_DENSE.getId())
                    ,entry(TiAcMeModifiers.ANNIHILATING_SLIME.getId()),entry(TiAcMeModifiers.NEUTRONIUM_ASSEMBLE.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(EtSTLibModifier.EXTRA_DENSE.getId())
                    ,entry(TiAcMeModifiers.ANNIHILATING_SLIME_ARMOR.getId()),entry(TiAcMeModifiers.NEUTRONIUM_ASSEMBLE.getId()))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF270053,15)).buildMaterialSprite().meleeHarvest().armor().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFFD10003)
                    .addARGB(102,0xFFFF0000)
                    .addARGB(140,0xFF000000)
                    .addARGB(178,0xFF2B0000)
                    .addARGB(216,0xFF470000)
                    .addARGB(255,0xFF630000).build()).build().build().setFluidCustomTexture();


    //热力联动
    public static final SimpleMaterialObject SIGNALUM = MATERIALS.buildMaterial("signalum")
            .addCompatModId("thermal",false).setUnit()
            .buildMaterial(3,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.2f,0.2f,-0.25f,0.15f),
                    new HeadMaterialStats(525,7f, Tiers.IRON,2.9f),
                    new FluxCoreMaterialStat(10.9f,1.6f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(30,1f,4f,3f,1f)
                    .toughness(2)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.SUPER_CONDUCT.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.SENSOR_INTERRUPT.getId())).build()
            .build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFF86014,5))
            .buildMaterialSprite().armor().meleeHarvest().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFF8A0003)
                    .addARGB(102,0xFFA20F00)
                    .addARGB(140,0xFFEC3606)
                    .addARGB(178,0xFFD01B00)
                    .addARGB(216,0xFFF86014)
                    .addARGB(255,0xFFFC9342).build()).build().build().setFluidCustomTexture();
    public static final SimpleMaterialObject LUMIUM = MATERIALS.buildMaterial("lumium")
            .addCompatModId("thermal",false).setUnit()
            .buildMaterial(4,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.2f,-0.15f,0.3f,-0.1f),
                    new HeadMaterialStats(798,7f, Tiers.DIAMOND,2.25f),
                    new GripMaterialStats(-0.2f,0.02f,2.25f),
                    new FluxCoreMaterialStat(0.4f,5.6f),
                    new LimbMaterialStats(798,0.25f,-0.15f,-0.02f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(37,1.5f,5f,4f,1.5f)
                    .toughness(1)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(new ModifierId("etstlib:static_discharge")))
            .buildPerStat(MaterialRegistry.ARMOR,entry(ModifierIds.fortified)).build()
            .build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFFCEEA8,15))
            .buildMaterialSprite().armor().ranged().meleeHarvest().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFFB4601C)
                    .addARGB(102,0xFFBC6E27)
                    .addARGB(140,0xFFDA9C4B)
                    .addARGB(178,0xFFF1DA70)
                    .addARGB(216,0xFFFCEEA8)
                    .addARGB(255,0xFFFCF9E0).build()).build().build().setFluidCustomTexture();
    public static final SimpleMaterialObject ENDERIUM = MATERIALS.buildMaterial("enderium")
            .addCompatModId("thermal",false).setUnit()
            .buildMaterial(4,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.15f,0.15f,0.1f,0.1f),
                    new HeadMaterialStats(1890,6.5f, Tiers.NETHERITE,3.75f),
                    new GripMaterialStats(0.2f,0.1f,3.25f),
                    new FluxCoreMaterialStat(5.4f,4.6f),
                    new LimbMaterialStats(1890,0.1f,0.1f,0.05f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(68,2f,7f,5f,2f)
                    .toughness(4).knockbackResistance(0.05f)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(TinkerModifiers.enderference.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(TiAcMeModifiers.VOID_DODGING.getId())).build()
            .build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF2BA6AD,10))
            .buildMaterialSprite().armor().meleeHarvest().ranged().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFF011224)
                    .addARGB(102,0xFF011D36)
                    .addARGB(140,0xFF0A2F56)
                    .addARGB(178,0xFF0C5D7B)
                    .addARGB(216,0xFF1D7D8A)
                    .addARGB(255,0xFF2BA6AD).build()).build().build().setFluidCustomTexture();

    public static final SimpleMaterialObject PRISMALIC_ALLOY = MATERIALS.buildMaterial("prismalium")
            .addCompatModId("thermalendergy",false).setUnit()
            .registerBurningFluid(1250,false,15,100,3)
            .buildMaterial(4,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.2f,0.1f,0.25f,-0.2f),
                    new HeadMaterialStats(1530,5.5f, Tiers.NETHERITE,2f),
                    new FluxCoreMaterialStat(17.4f,17.6f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(50,1.5f,5f,3.5f,1f)
                    .toughness(8).knockbackResistance(0.05f)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.OCEANIC_WHIRL.getId()),entry(ModifierIds.antiaquatic,5))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.PRISMATIC.getId()),entry(TiAcMeModifierProvider.ModifierIds.RESPIRATION_ENHANCED)).build()
            .build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFB3E2D2,10))
            .buildMaterialSprite().armor().meleeHarvest().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFF32685A)
                    .addARGB(102,0xFF427E6D)
                    .addARGB(140,0xFF7BB1A2)
                    .addARGB(178,0xFF8FC0AA)
                    .addARGB(216,0xFFA2CFC0)
                    .addARGB(255,0xFFB3E2D2).build()).build().build();
    public static final SimpleMaterialObject MELODIC_ALLOY = MATERIALS.buildMaterial("melodium")
            .addCompatModId("thermalendergy",false).setUnit()
            .registerBurningFluid(1500,false,15,150,4)
            .buildMaterial(5,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.1f,-0.1f,1.28f,-0.1f),
                    new HeadMaterialStats(1775,6.5f, Tiers.NETHERITE,4f),
                    new GripMaterialStats(-0.1f,-0.1f,4.75f),
                    new FluxCoreMaterialStat(25.4f,24.6f),
                    new LimbMaterialStats(1775,1.28f,-0.1f,-0.1f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(65,3f,8f,6f,3f)
                    .toughness(8)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.LIFE_MELODY.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(TinkerModifiers.enderference.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.DEFENSE_MELODY.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(TiAcMeModifiers.ENDER_SUPRESS.getId())).build()
            .setExcludeFromAncients().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFFAF8D7,10))
            .buildMaterialSprite().armor().meleeHarvest().ranged().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFF513989)
                    .addARGB(102,0xFF684DA9)
                    .addARGB(140,0xFFB28AF4)
                    .addARGB(178,0xFFD2A2F1)
                    .addARGB(216,0xFFF7C7E5)
                    .addARGB(255,0xFFFAF8D7).build()).build().build().setFluidCustomTexture();
    public static final SimpleMaterialObject STELLAR_ALLOY = MATERIALS.buildMaterial("stellarium")
            .addCompatModId("thermalendergy",false).setUnit()
            .registerBurningFluid(2000,false,15,300,10)
            .buildMaterial(5,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.85f,0.85f,0.45f,0.45f),
                    new HeadMaterialStats(2990,10.5f, Tiers.NETHERITE,8f),
                    new GripMaterialStats(0.8f,0.6f,8f),
                    new FluxCoreMaterialStat(79.9f,72.2f),
                    new LimbMaterialStats(2990,0.5f,0.5f,-0.05f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(220,4f,10f,8f,4f)
                    .toughness(12).knockbackResistance(0.1f)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.STAR_GAZING.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(EtSTLibModifier.EXTRA_DENSE.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.STAR_HEAL.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId()),entry(EtSTLibModifier.EXTRA_DENSE.getId())).build()
            .setExcludeFromAncients().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFFAFCA6,10))
            .buildMaterialSprite().armor().meleeHarvest().ranged().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFF546A6A)
                    .addARGB(102,0xFF638E8E)
                    .addARGB(140,0xFF86A2A2)
                    .addARGB(178,0xFFA0C2C2)
                    .addARGB(216,0xFFB7C7C7)
                    .addARGB(255,0xFFFAFCA6).build()).build().build().setFluidCustomTexture();

    //CRC联动
    public static final SimpleMaterialObject FROSTITE = MATERIALS.buildMaterial("frostite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerFluid(0,false,supplier ->
                    new LiquidBlock(supplier,FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY,15)) {
                        @Override
                        public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
                            pEntity.setTicksFrozen(200);
                            if (pEntity instanceof LivingEntity living)
                                living.addEffect(new MobEffectInstance(TiAcMeEffects.FROZEN.get(), 200, 1));
                        }
                    })
            .buildMaterial(2,false,ORDER_COMPAT,true).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.2f,-0.2f,0.1f,0.0f),
                    new HeadMaterialStats(107,7f, Tiers.IRON,1.5f),
                    new GripMaterialStats(-0.2f,0,2.25f),
                    new LimbMaterialStats(107,0.1f,0f,0f),
                    new FluxCoreMaterialStat(1.9f,1.6f),
                    StatlessMaterialStats.MAILLE,
                    StatlessMaterialStats.SHIELD_CORE,
                    StatlessMaterialStats.ARROW_HEAD
            )
            .setArmorBuilder(armor(10,1f,4f,3f,1f))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.FROSTED_ATTACK.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.FROSTED_SHIELD.getId()))
            .buildPerStat(MaterialRegistry.AMMO,entry(TiAcMeModifiers.FROZEN.getId())).build()
            .build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF8FBEFC,2)).build();
    public static final SimpleMaterialObject BLAZEITE = MATERIALS.buildMaterial("blazeite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerBurningFluid(1200,false,15,200,5)
            .buildMaterial(4,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.2f,0f,0.05f,0.2f),
                    new HeadMaterialStats(653,6f, Tiers.DIAMOND,3.25f),
                    new GripMaterialStats(-0.2f,0,3.25f),
                    new LimbMaterialStats(653,0.05f,0.2f,0f),
                    new FluxCoreMaterialStat(3f,2.2f),
                    StatlessMaterialStats.MAILLE
            )
            .setArmorBuilder(armor(48,2f,7f,5f,2f).toughness(6))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.BLAZING.getId())).build()
            .build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFFBE89B,15)).build();
    public static final SimpleMaterialObject GLOWSTONEITE = MATERIALS.buildMaterial("glowstoneite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerFluid(1000,false,supplier ->
                    new LiquidBlock(supplier,FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY,15)){
                        @Override
                        public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
                            if (pEntity instanceof LivingEntity living) living.addEffect(new MobEffectInstance(MobEffects.GLOWING,200,0));
                        }
                    })
            .buildMaterial(3,false,ORDER_COMPAT,true).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0f,0.3f,0.05f,0f),
                    new HeadMaterialStats(595,8f, Tiers.IRON,1.5f),
                    new GripMaterialStats(0f,0,1.5f),
                    new LimbMaterialStats(595,0.05f,0f,0f),
                    new FluxCoreMaterialStat(3f,2.2f),
                    StatlessMaterialStats.MAILLE,
                    StatlessMaterialStats.SHIELD_CORE
            )
            .setArmorBuilder(armor(45,1.5f,6f,4f,1.5f))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(ModifierIds.glowing),entry(ModifierIds.smite))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TinkerModifiers.golden.getId()),entry(ModifierIds.glowing),entry(ModifierIds.consecrated))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFFBF359,15)).build();
    public static final SimpleMaterialObject ENDERITE = MATERIALS.buildMaterial("enderite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerFluid(1000,false,supplier ->
                    new LiquidBlock(supplier,FluidDeferredRegister.createProperties(MapColor.COLOR_GRAY,15)){
                        @Override
                        public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
                            if (pLevel.getGameTime()%10==0&&pEntity instanceof LivingEntity living){
                                TeleportHelper.randomNearbyTeleport(living, EntityTeleportEvent::new,3,1);
                            }
                        }
                    })
            .buildMaterial(3,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.1f,0.1f,0.05f,0.05f),
                    new HeadMaterialStats(1090,6f, Tiers.DIAMOND,3f),
                    new FluxCoreMaterialStat(4f,3f),
                    StatlessMaterialStats.MAILLE
            )
            .setArmorBuilder(armor(66,2f,7f,5f,2f).toughness(2))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.ENDER_PEARL.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.ENDER_PROTOCOL.getId()))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF8FFCF2,7)).build();
    public static final SimpleMaterialObject WARPEDITE = MATERIALS.buildMaterial("warpedite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .buildMaterial(3,false,ORDER_COMPAT,true).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.3f,-0.3f,0.35f,-0.05f),
                    new HeadMaterialStats(212,3f, Tiers.IRON,1.5f)
            ).build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.NEURO_EXCITEMENT.getId()),entry(ModifierIds.entangled))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF41b79d,0)).build();
    public static final SimpleMaterialObject NETHERWARTITE = MATERIALS.buildMaterial("netherwartite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .buildMaterial(3,false,ORDER_COMPAT,true).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.2f,0.2f,-0.35f,0.3f),
                    new HeadMaterialStats(1375,7.2f, Tiers.DIAMOND,2.95f)
            ).build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.FLASH_ATTACK.getId()),entry(ModifierIds.flamestance))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFd8535e,0)).build();
    public static final SimpleMaterialObject SLIMEITE = MATERIALS.buildMaterial("slimeite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .buildMaterial(2,false,ORDER_COMPAT,true).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0f,-0.2f,0f,0.05f),
                    new HeadMaterialStats(107,3.2f, Tiers.DIAMOND,1f)
            ).build().buildModifiers()
            .buildDefault(entry(TinkerModifiers.overslime.getId()),entry(TiAcMeModifiers.SLIMEFUL.getId()))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF7AEB8A,0)).build();
    public static final SimpleMaterialObject CHORUSITE = MATERIALS.buildMaterial("chorusite")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerBurningFluid(1500,false,15,200,7)
            .buildMaterial(5,false,ORDER_COMPAT,true).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.1f,0.1f,0.35f,0.15f),
                    new HeadMaterialStats(1440,6f, Tiers.NETHERITE,2.5f),
                    new GripMaterialStats(-0.1f,0,2.5f),
                    new LimbMaterialStats(1440,0.45f,0.05f,0f),
                    new FluxCoreMaterialStat(5.9f,7.6f),
                    StatlessMaterialStats.MAILLE,
                    StatlessMaterialStats.SHIELD_CORE
            )
            .setArmorBuilder(armor(64,3f,8f,6f,3f).knockbackResistance(0.05f).toughness(5))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TinkerModifiers.enderference.getId()),entry(TiAcMeModifiers.ENDER_BREATHE.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifiers.ENDER_SUPRESS.getId()),entry(TiAcMeModifiers.METAMORPHIUM.getId())).build()
            .setExcludeFromAncients().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFd291bb,2)).build();
    public static final SimpleMaterialObject REFINED_RADIANCE = MATERIALS.buildMaterial("refined_radiance")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerBurningFluid(1750,false,15,16384,10)
            .buildMaterial(5,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.5f,0.5f,0.8f,-0.2f),
                    new HeadMaterialStats(4096,10f, Tiers.NETHERITE,6f),
                    new GripMaterialStats(0.5f,0.5f,6f),
                    new LimbMaterialStats(4096,1f,-0.1f,0f),
                    new FluxCoreMaterialStat(32.9f,22.6f),
                    StatlessMaterialStats.MAILLE
            )
            .setArmorBuilder(armor(256,3f,8f,6f,3f).knockbackResistance(0.05f).toughness(8))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.LUMINOUS_PIERCER.getId()),entry(ModifierIds.shiny)).build()
            .setExcludeFromAncients().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFF8E9FF,15)).build();
    public static final SimpleMaterialObject SHADOW_STEEL = MATERIALS.buildMaterial("shadow_steel")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerBurningFluid(1750,false,0,16384,10)
            .buildMaterial(5,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.5f,0.5f,-0.5f,0.8f),
                    new HeadMaterialStats(4096,10f, Tiers.NETHERITE,6f),
                    new GripMaterialStats(0.5f,0.5f,6f),
                    new LimbMaterialStats(4096,-0.4f,1f,0f),
                    new FluxCoreMaterialStat(1.9f,56.6f),
                    StatlessMaterialStats.MAILLE
            )
            .setArmorBuilder(armor(256,3f,8f,6f,3f).knockbackResistance(0.05f).toughness(8))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.NIGHT_SLAYER.getId())).build()
            .setExcludeFromAncients().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFF4c4760,0)).build();
    public static final SimpleMaterialObject NETHERSTAR_ALLOY = MATERIALS.buildMaterial("netherstar_alloy")
            .addCompatModId("rainbowcompound",false).setUnit()
            .registerBurningFluid(2000,false,0,500,20)
            .buildMaterial(5,false,ORDER_COMPAT,false).buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.5f,0.5f,0.3f,0.3f),
                    new HeadMaterialStats(3840,12f, Tiers.NETHERITE,7.5f),
                    new GripMaterialStats(0.75f,0.5f,7.5f),
                    new LimbMaterialStats(3840,0.5f,0.5f,0f),
                    new FluxCoreMaterialStat(69.9f,64.6f),
                    StatlessMaterialStats.MAILLE
            )
            .setArmorBuilder(armor(204,4f,10f,8f,4f).knockbackResistance(0.1f).toughness(10))
            .setAllowShield().build().buildModifiers()
            .buildDefault(entry(TiAcMeModifiers.STAR_GAZING.getId()),entry(TiAcMeModifierProvider.ModifierIds.RAINBOW_KIT),entry(ModifierIds.shiny))
            .buildPerStat(MaterialRegistry.ARMOR,entry(TiAcMeModifierProvider.ModifierIds.NETHERSTAR_BLESSING),
                    entry(TiAcMeModifierProvider.ModifierIds.RAINBOW_KIT),
                    entry(ModifierIds.shiny)).build()
            .setExcludeFromAncients().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{"metal"},
                    0xFFE5C7FF,15)).build();

    //EIO联动
    public static final SimpleMaterialObject COPPER_ALLOY = MATERIALS.buildMaterial("copper_alloy")
            .addCompatModId("enderio",false).setUnit()
            .registerBurningFluid(650,false,7,20,3)
            .buildMaterial(2,false,ORDER_COMPAT,true)
            .buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(0.15f,0.05f,-0.05f,-0.05f),
                    new HeadMaterialStats(650,6.75f, Tiers.IRON,2.0f),
                    new GripMaterialStats(0.15f,0.01f,2f),
                    new LimbMaterialStats(650,-0.05f,-0.05f,0.01f),
                    new FluxCoreMaterialStat(1.4f,2.3f),
                    StatlessMaterialStats.MAILLE
            ).setArmorBuilder(armor(26,2,6,4,2)).setAllowShield().build()
            .buildModifiers().buildDefault(entry(ModifierIds.maintained)).build().build().setRenderInfo(id ->
                    getRenderInfo(id,new String[]{"metal"},
                            0xFFF2BA4C,0)).build();
    public static final SimpleMaterialObject REDSTONE_ALLOY = MATERIALS.buildMaterial("redstone_alloy")
            .addCompatModId("enderio",false).setUnit()
            .registerBurningFluid(500,false,7,20,3)
            .buildMaterial(2,false,ORDER_COMPAT,true)
            .buildStats().setStats(
                    StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(-0.05f,0.15f,0.05f,-0.05f),
                    new HeadMaterialStats(262,7.75f, Tiers.IRON,2.0f),
                    new GripMaterialStats(0.15f,0.01f,2f),
                    new FluxCoreMaterialStat(1.4f,2.3f),
                    StatlessMaterialStats.MAILLE
            ).setArmorBuilder(armor(26,2,6,4,2)).setAllowShield().build()
            .buildModifiers().buildDefault(entry(TiAcMeModifierProvider.ModifierIds.REDSTONE_PLATED))
            .buildPerStat(HeadMaterialStats.ID,entry(ModifierIds.haste))
            .buildPerStat(PlatingMaterialStats.BOOTS.getId(),entry(ModifierIds.haste))
            .buildPerStat(PlatingMaterialStats.LEGGINGS.getId(),entry(ModifierIds.haste))
            .buildPerStat(PlatingMaterialStats.CHESTPLATE.getId(),entry(ModifierIds.haste))
            .buildPerStat(PlatingMaterialStats.HELMET.getId(),entry(ModifierIds.haste))
            .build().build().setRenderInfo(id ->
                    getRenderInfo(id,new String[]{"metal"},
                            0xFFF65B5B,4)).build();

    protected static FluidType.Properties hot(String name, int Temp, boolean gas) {
        return FluidType.Properties.create().density(gas ? -2000 : 2000).viscosity(10000).temperature(Temp).descriptionId("fluid.tinkers_advanced." + name).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA).motionScale(0.0023333333333333335).canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA).adjacentPathType((BlockPathTypes)null);
    }
    public static MaterialRenderInfo getRenderInfo(MaterialId id,String[] fallbacks,int color,int luminosity){
        return new MaterialRenderInfo(id,id.getLocation('_'),fallbacks,color,luminosity);
    }
}
