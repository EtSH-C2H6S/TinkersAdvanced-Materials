package com.c2h6s.tinkers_advanced_materials.init;

import com.c2h6s.etstlib.data.EtSTLibModifierIds;
import com.c2h6s.etstlib.entity.specialDamageSources.LegacyDamageSource;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialWrappedRegister;
import com.c2h6s.tinkers_advanced.core.content.tool.tinkering.materialStat.FluxCoreMaterialStat;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
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
import slimeknights.tconstruct.tools.TinkerModifiers;
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

    public static final SimpleMaterialObject DENSIUM = MATERIALS.buildMaterial("densium")
            .registerItem(()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON))).setItemIngot()
            .registerBurningFluid(2250,false,7,256,7).setUnit()
            .addCompatModId("mekanism",true).buildMaterial(4,false,ORDER_ORIGINAL,false)
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
                    .addARGB(63,0xFFA500FF)
                    .addARGB(102,0xFFCD49FF)
                    .addARGB(140,0xFF000000)
                    .addARGB(178,0xFF10002B)
                    .addARGB(216,0xFF210047)
                    .addARGB(255,0xFF390063).build()).build().build();

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
            .addCompatModId("mekanism",true).buildMaterial(4,false,ORDER_ORIGINAL,false)
            .buildStats().setStats(StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(2f,2f,-0.75f,2f),
                    new HeadMaterialStats(16384,10f, Tiers.NETHERITE,12f),
                    new FluxCoreMaterialStat(262144f,262144f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(128,30f,30f,30f,30f)
                    .toughness(20f).knockbackResistance(10)).setAllowShield().build().buildModifiers()
            .buildPerStat(MaterialRegistry.MELEE_HARVEST,entry(EtSTLibModifier.EXTRA_DENSE.getId())
                    ,entry(TiAcMeModifiers.ANNIHILATING_SLIME.getId()),entry(TiAcMeModifiers.NEUTRONIUM_ASSEMBLE.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(EtSTLibModifier.EXTRA_DENSE.getId())
                    ,entry(TiAcMeModifiers.ANNIHILATING_SLIME_ARMOR.getId()),entry(TiAcMeModifiers.NEUTRONIUM_ASSEMBLE.getId()))
            .build().build().setRenderInfo(materialId -> getRenderInfo(materialId,new String[]{
                    "metal"
            },0xFF270053,15)).buildMaterialSprite().meleeHarvest().armor().fallbacks("metal").colorMapper(GreyToColorMapping.builder()
                    .addARGB(0,0xFF000000)
                    .addARGB(63,0xFFD10003)
                    .addARGB(102,0xFFFF0000)
                    .addARGB(140,0xFF000000)
                    .addARGB(178,0xFF2B0000)
                    .addARGB(216,0xFF470000)
                    .addARGB(255,0xFF630000).build()).build().build();

    protected static FluidType.Properties hot(String name, int Temp, boolean gas) {
        return FluidType.Properties.create().density(gas ? -2000 : 2000).viscosity(10000).temperature(Temp).descriptionId("fluid.tinkers_advanced." + name).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA).motionScale(0.0023333333333333335).canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA).adjacentPathType((BlockPathTypes)null);
    }
    public static MaterialRenderInfo getRenderInfo(MaterialId id,String[] fallbacks,int color,int luminosity){
        return new MaterialRenderInfo(id,id.getLocation('_'),fallbacks,color,luminosity);
    }
}
