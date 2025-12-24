package com.c2h6s.tinkers_advanced_materials.init;

import com.c2h6s.etstlib.tool.modifiers.Combat.Defense.*;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.TiAcCrModule;
import com.c2h6s.tinkers_advanced.core.content.event.TiAcLoadRegistryClassEvent;
import com.c2h6s.tinkers_advanced.core.content.tool.modifiers.*;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.AddInvulnerableTickAfterHitModifier;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.SimpleEffectCombatModifier;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.SlotAddingModifier;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.common.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.eio.CapacitorCapable;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.mekanism.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.pnc.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.compat.thermal.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.defense.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.durability.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.harvest.*;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.interaction.EnderPearlModifier;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.overslime.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.SlotType;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TiAcMeModifiers {
    @SubscribeEvent
    public static void init(TiAcLoadRegistryClassEvent event){}

    public static final ModifierDeferredRegister MEK_MODIFIERS = ModifierDeferredRegister.create(TinkersAdvanced.MODID);
    public static final ModifierDeferredRegister PNC_MODIFIERS = ModifierDeferredRegister.create(TinkersAdvanced.MODID);
    public static final ModifierDeferredRegister THERMAL_MODIFIERS = ModifierDeferredRegister.create(TinkersAdvanced.MODID);
    public static final ModifierDeferredRegister AE_MODIFIERS = ModifierDeferredRegister.create(TinkersAdvanced.MODID);
    public static final ModifierDeferredRegister TIACT_MODIFIERS = ModifierDeferredRegister.create(TinkersAdvanced.MODID);
    public static final ModifierDeferredRegister EIO_MODIFIERS = ModifierDeferredRegister.create(TinkersAdvanced.MODID);

    //无联动属性
    //注：只要没有用联动模组的方法或类就算无联动
    public static final StaticModifier<Fragile> FRAGILE = TiAcCrModule.MODIFIERS.register("fragile", Fragile::new);
    public static final StaticModifier<TetanusModifier> TETANUS = TiAcCrModule.MODIFIERS.register("tetanus", TetanusModifier::new);
    public static final StaticModifier<Annihilate> ANNIHILATE = TiAcCrModule.MODIFIERS.register("annihilate", Annihilate::new);
    public static final StaticModifier<ReactiveExplosiveArmor> REACTIVE_EXPLOSIVE_ARMOR = TiAcCrModule.MODIFIERS.register("reactive_explosive_armor", ReactiveExplosiveArmor::new);
    public static final StaticModifier<SensorInterrupt> SENSOR_INTERRUPT = TiAcCrModule.MODIFIERS.register("sensor_interrupt", SensorInterrupt::new);
    public static final StaticModifier<Metamorphium> METAMORPHIUM = TiAcCrModule.MODIFIERS.register("metamorphium", Metamorphium::new);
    public static final StaticModifier<FlameAdaptive> FLAME_ADAPTIVE = TiAcCrModule.MODIFIERS.register("flame_adaptive", FlameAdaptive::new);
    public static final StaticModifier<SupremeDensity> SUPREME_DENSITY = TiAcCrModule.MODIFIERS.register("supreme_density", SupremeDensity::new);
    public static final StaticModifier<SupremeDensityArmor> SUPREME_DENSITY_ARMOR = TiAcCrModule.MODIFIERS.register("supreme_density_armor", SupremeDensityArmor::new);
    public static final StaticModifier<IonizedModifier> IONIZED = TiAcCrModule.MODIFIERS.register("ionized", IonizedModifier::new);
    public static final StaticModifier<DisIntegrate> DIS_INTEGRATE = TiAcCrModule.MODIFIERS.register("disintegrate", DisIntegrate::new);
    public static final StaticModifier<Electric> ELECTRIC = TiAcCrModule.MODIFIERS.register("electric", Electric::new);
    public static final StaticModifier<EchoLocating> ECHO_LOCATING = TiAcCrModule.MODIFIERS.register("echo_locating", EchoLocating::new);
    public static final StaticModifier<Elastic> ELASTIC = TiAcCrModule.MODIFIERS.register("elastic", Elastic::new);
    public static final StaticModifier<Shaping> SHAPING = TiAcCrModule.MODIFIERS.register("shaping", Shaping::new);
    public static final StaticModifier<ProtoRefining> PROTO_REFINING = TiAcCrModule.MODIFIERS.register("proto_refining", ProtoRefining::new);
    public static final StaticModifier<ProtoDefense> PROTO_DEFENSE = TiAcCrModule.MODIFIERS.register("proto_defense", ProtoDefense::new);
    public static final StaticModifier<ReturnToSlime> RETURN_TO_SLIME = TiAcCrModule.MODIFIERS.register("return_to_slime", ReturnToSlime::new);
    public static final StaticModifier<NutritiveSlime> NUTRITIVE_SLIME = TiAcCrModule.MODIFIERS.register("nutritive_slime", NutritiveSlime::new);
    public static final StaticModifier<Unstable> UNSTABLE = TiAcCrModule.MODIFIERS.register("unstable", Unstable::new);
    public static final StaticModifier<PlagueModifier> PLAGUE = TiAcCrModule.MODIFIERS.register("plague", PlagueModifier::new);
    public static final StaticModifier<Clearing> POISON_DEFENSE = TiAcCrModule.MODIFIERS.register("poison_defense", Clearing::new);
    public static final StaticModifier<VoidDodging> VOID_DODGING = TiAcCrModule.MODIFIERS.register("void_dodging", VoidDodging::new);
    public static final StaticModifier<Stabilize> STABILIZE = TiAcCrModule.MODIFIERS.register("stabilize", Stabilize::new);
    public static final StaticModifier<SlotAddingModifier> ISO_CHROME = TiAcCrModule.MODIFIERS.register("iso_chrome",()-> new SlotAddingModifier(
            i -> SlotAddingModifier.slot(SlotType.ABILITY,i),
            i -> SlotAddingModifier.slot(SlotType.UPGRADE,i),
            i -> SlotAddingModifier.slot(SlotType.DEFENSE,i)
    ));
    public static final StaticModifier<SlotAddingModifier> EXTRA_ENHANCE = TiAcCrModule.MODIFIERS.register("extra_enhance",()-> new SlotAddingModifier(
            i -> SlotAddingModifier.slot(SlotType.ABILITY,i)
    ));
    public static final StaticModifier<AnnihilatingSlime> ANNIHILATING_SLIME = TiAcCrModule.MODIFIERS.register("annihilating_slime", AnnihilatingSlime::new);
    public static final StaticModifier<AnnihilatingSlimeArmor> ANNIHILATING_SLIME_ARMOR = TiAcCrModule.MODIFIERS.register("annihilating_slime_armor", AnnihilatingSlimeArmor::new);
    public static final StaticModifier<NeutroniumAssemble> NEUTRONIUM_ASSEMBLE = TiAcCrModule.MODIFIERS.register("neutronium_assemble", NeutroniumAssemble::new);
    public static final StaticModifier<HeavyMaterial> HEAVY_MATERIAL = TiAcCrModule.MODIFIERS.register("heavy_material",HeavyMaterial::new);
    public static final StaticModifier<EnergyReinforced> ENERGY_REINFORCED = TiAcCrModule.MODIFIERS.register("energy_reinforced",EnergyReinforced::new);
    public static final StaticModifier<Superconduct> SUPER_CONDUCT = TiAcCrModule.MODIFIERS.register("super_conduct",Superconduct::new);
    public static final StaticModifier<LifeMelody> LIFE_MELODY = TiAcCrModule.MODIFIERS.register("life_melody",LifeMelody::new);
    public static final StaticModifier<Blazing> BLAZING = TiAcCrModule.MODIFIERS.register("blazing",Blazing::new);
    public static final StaticModifier<SimpleEffectCombatModifier> FROZEN = TiAcCrModule
            .MODIFIERS.register("frozen",()->new SimpleEffectCombatModifier(()->1,()->0,()->100,()->100,TiAcMeEffects.FROZEN));
    public static final StaticModifier<Prismatic> PRISMATIC = TiAcCrModule.MODIFIERS.register("prismatic",Prismatic::new);
    public static final StaticModifier<OceanicWhirl> OCEANIC_WHIRL = TiAcCrModule.MODIFIERS.register("oceanic_whirl",OceanicWhirl::new);
    public static final StaticModifier<FrostedAttack> FROSTED_ATTACK = TiAcCrModule.MODIFIERS.register("frosted_attack",FrostedAttack::new);
    public static final StaticModifier<FrostedShield> FROSTED_SHIELD = TiAcCrModule.MODIFIERS.register("frosted_shield",FrostedShield::new);
    public static final StaticModifier<AddInvulnerableTickAfterHitModifier> DEFENSE_MELODY = TiAcCrModule.MODIFIERS.register("defense_melody",()->new AddInvulnerableTickAfterHitModifier(TiAcMeConfig.COMMON.DEFENSE_MELODY_INVULNERABLE_TICKS));
    public static final StaticModifier<EnderSuppress> ENDER_SUPRESS = TiAcCrModule.MODIFIERS.register("ender_supress",EnderSuppress::new);
    public static final StaticModifier<EnderPearlModifier> ENDER_PEARL = TiAcCrModule.MODIFIERS.register("ender_pearl",EnderPearlModifier::new);
    public static final StaticModifier<EnderProtocol> ENDER_PROTOCOL = TiAcCrModule.MODIFIERS.register("ender_protocol",EnderProtocol::new);
    public static final StaticModifier<NeuroExcitement> NEURO_EXCITEMENT = TiAcCrModule.MODIFIERS.register("neuro_excitement",NeuroExcitement::new);
    public static final StaticModifier<FlashAttack> FLASH_ATTACK = TiAcCrModule.MODIFIERS.register("flash_attack",FlashAttack::new);
    public static final StaticModifier<StarGazing> STAR_GAZING = TiAcCrModule.MODIFIERS.register("star_gazing",StarGazing::new);
    public static final StaticModifier<EnderBreatheModifier> ENDER_BREATHE = TiAcCrModule.MODIFIERS.register("ender_breathe",EnderBreatheModifier::new);
    public static final StaticModifier<NightsSlayer> NIGHT_SLAYER = TiAcCrModule.MODIFIERS.register("night_slayer",NightsSlayer::new);
    public static final StaticModifier<StarHeal> STAR_HEAL = TiAcCrModule.MODIFIERS.register("star_heal",StarHeal::new);
    public static final StaticModifier<LuminousPiercer> LUMINOUS_PIERCER = TiAcCrModule.MODIFIERS.register("luminous_piercer",LuminousPiercer::new);
    public static final StaticModifier<Slimeful> SLIMEFUL = TiAcCrModule.MODIFIERS.register("slimeful",Slimeful::new);
    public static final StaticModifier<Semiconductor> SEMI_CONDUCTOR = TiAcCrModule.MODIFIERS.register("semiconductor",Semiconductor::new);
    public static final StaticModifier<Photoelectric> PHOTOELECTRIC = TiAcCrModule.MODIFIERS.register("photoelectric",Photoelectric::new);




    //mek联动属性
    public static final StaticModifier<RadioactiveArmor> RADIOACTIVE_ARMOR = MEK_MODIFIERS.register("radioactive_armor", RadioactiveArmor::new);
    public static final StaticModifier<AtomGrade> ATOM_GRADE = MEK_MODIFIERS.register("atom_grade", AtomGrade::new);
    public static final StaticModifier<RadiationBurning> RADIATION_BURNING = MEK_MODIFIERS.register("radiation_burning", RadiationBurning::new);


    //PnC联动属性
    public static final StaticModifier<AirSlash> AIR_SLASH = PNC_MODIFIERS.register("air_slash", AirSlash::new);
    public static final StaticModifier<AerialProtection> AERIAL_PROTECTION = PNC_MODIFIERS.register("aerial_protection", AerialProtection::new);


    //热力联动属性
    public static final StaticModifier<BasalzDefense> BASALZ_DEFENSE = THERMAL_MODIFIERS.register("basalz_defense", BasalzDefense::new);
    public static final StaticModifier<BasalzInflict> BASALZ_INFLICT = THERMAL_MODIFIERS.register("basalz_inflict", BasalzInflict::new);
    public static final StaticModifier<BlitzDefense> Blitz_DEFENSE = THERMAL_MODIFIERS.register("blitz_defense", BlitzDefense::new);
    public static final StaticModifier<BlitzInflict> Blitz_INFLICT = THERMAL_MODIFIERS.register("blitz_inflict", BlitzInflict::new);
    public static final StaticModifier<BlizzDefense> BLIZZ_DEFENSE = THERMAL_MODIFIERS.register("blizz_defense", BlizzDefense::new);
    public static final StaticModifier<BlizzInflict> BLIZZ_INFLICT = THERMAL_MODIFIERS.register("blizz_inflict", BlizzInflict::new);
    public static final StaticModifier<FluxInfused> FLUX_INFUSED = THERMAL_MODIFIERS.register("flux_infused", FluxInfused::new);
    public static final StaticModifier<ThermalSlashModifier> THERMAL_SLASH = THERMAL_MODIFIERS.register("thermal_slash", ThermalSlashModifier::new);
    public static final StaticModifier<ThermalEnhance> THERMAL_ENHANCE = THERMAL_MODIFIERS.register("thermal_enhance", ThermalEnhance::new);
    public static final StaticModifier<FluxArrow> FLUX_ARROW = THERMAL_MODIFIERS.register("flux_arrow", FluxArrow::new);
    public static final StaticModifier<FluxDefense> FLUX_DEFENSE = THERMAL_MODIFIERS.register("flux_defense", FluxDefense::new);


    //AE联动属性



    //TiAcT联动属性



    //EIO联动属性
    public static final StaticModifier<CapacitorCapable> CAPACITOR_CAPABLE = EIO_MODIFIERS.register("capacitor_capable", CapacitorCapable::new);

}
