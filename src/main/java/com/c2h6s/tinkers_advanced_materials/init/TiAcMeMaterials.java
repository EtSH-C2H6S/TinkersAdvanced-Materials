package com.c2h6s.tinkers_advanced_materials.init;

import com.c2h6s.etstlib.data.EtSTLibModifierIds;
import com.c2h6s.etstlib.register.EtSTLibModifier;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialWrappedRegister;
import com.c2h6s.tinkers_advanced.core.content.tool.tinkering.materialStat.FluxCoreMaterialStat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierId;
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

    public static final SimpleMaterialObject DENSIUM = MATERIALS.buildMaterial("densium")
            .registerItem(()->new Item(new Item.Properties().rarity(Rarity.UNCOMMON))).setItemIngot()
            .registerBurningFluid(2250,false,7,256,7).setUnit()
            .addCompatModId("mekanism",true).buildMaterial(4,false,ORDER_ORIGINAL,false)
            .buildStats().setStats(StatlessMaterialStats.BINDING,
                    new HandleMaterialStats(1.2f,0.2f,-0.5f,0.8f),
                    new HeadMaterialStats(2048,10f, Tiers.NETHERITE,5.5f),
                    new GripMaterialStats(1.2f,0.2f,5.5f),
                    new FluxCoreMaterialStat(6.4f,8.6f),
                    new LimbMaterialStats(2048,-0.5f,0.75f,0.2f),
                    StatlessMaterialStats.MAILLE)
            .setArmorBuilder(armor(128,3.5f,8.25f,6.25f,3.5f)
                    .toughness(3.5f).knockbackResistance(10)).setAllowShield().build().buildModifiers()
            .buildDefault(entry(EtSTLibModifier.EXTRA_DENSE.getId()))
            .buildPerStat(HeadMaterialStats.ID,entry(EtSTLibModifier.EXTRA_DENSE.getId()),entry(EtSTLibModifierIds.RUDE))
            .buildPerStat(HandleMaterialStats.ID,entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(EtSTLibModifier.momentum_accelerate.getId(),5),entry(ModifierIds.heavy))
            .buildPerStat(StatlessMaterialStats.BINDING.getIdentifier(),entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(EtSTLibModifier.momentum_accelerate.getId()))
            .buildPerStat(MaterialRegistry.ARMOR,entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(EtSTLibModifier.HYPER_DENSITY.getId()))
            .buildPerStat(MaterialRegistry.RANGED,entry(EtSTLibModifier.EXTRA_DENSE.getId()),
                    entry(TinkerModifiers.momentum.getId(),5),entry(ModifierIds.heavy))
            .build().build().build();

}
