package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.data.condition.CompatConfigCondition;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.mantle.data.predicate.entity.MobTypePredicate;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.json.RandomLevelingValue;
import slimeknights.tconstruct.library.json.variable.melee.MeleeVariable;
import slimeknights.tconstruct.library.json.variable.mining.BlockLightVariable;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.modules.armor.BlockDamageSourceModule;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.MaterialRepairModule;
import slimeknights.tconstruct.library.modifiers.modules.build.*;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalMeleeDamageModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.MobEffectModule;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorLevelModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.IndestructibleItemEntity;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.data.ModifierProvider;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import static com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider.ModifierIds.*;
import static com.c2h6s.tinkers_advanced_materials.data.providers.tinker.TiAcMeModifierProvider.DataKeys.*;
public class TiAcMeModifierProvider extends AbstractModifierProvider {
    public TiAcMeModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        buildModifier(THERMAL_FOUNDATION,modLoaded("thermal",false))
                .addModule(ModifierSlotModule.slot(SlotType.UPGRADE).amount(0,1));
        buildModifier(BASALZ_ENHANCE,modLoaded("thermal",false))
                .addModule(ModifierSlotModule.slot(SlotType.DEFENSE).amount(0,1));
        buildModifier(BLITZ_ENHANCE,modLoaded("thermal",false))
                .addModule(ModifierSlotModule.slot(SlotType.UPGRADE).amount(0,1));
        buildModifier(BLIZZ_ENHANCE,modLoaded("thermal",false))
                .addModule(ModifierSlotModule.slot(SlotType.ABILITY).amount(0,1));
        buildModifier(OBSIDIANITE,modLoaded("rainbowcompound",false)).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(SetStatModule.set(ToolStats.HARVEST_TIER).value(Tiers.DIAMOND))
                .addModule(StatBoostModule.multiplyBase(ToolStats.ATTACK_DAMAGE).amount(0,0.1f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.DURABILITY).amount(0,0.25f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.MINING_SPEED).amount(0,0.1f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.VELOCITY).amount(0,0.05f))
                .addModule(StatBoostModule.add(ToolStats.ARMOR_TOUGHNESS).amount(0,0.5f))
                .addModule(StatBoostModule.add(ToolStats.KNOCKBACK_RESISTANCE).amount(0,0.025f))
                .addModule(MaterialRepairModule.material(MaterialIds.obsidian).constant(200));
        buildModifier(RAINBOW_KIT,modLoaded("rainbowcompound",false)).levelDisplay(ModifierLevelDisplay.DEFAULT)
                .addModule(SetStatModule.set(ToolStats.HARVEST_TIER).value(Tiers.NETHERITE))
                .addModule(StatBoostModule.multiplyBase(ToolStats.ATTACK_DAMAGE).amount(0,0.4f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.DURABILITY).amount(0,0.4f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.MINING_SPEED).amount(0,0.4f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.VELOCITY).amount(0,0.2f))
                .addModule(StatBoostModule.add(ToolStats.ARMOR_TOUGHNESS).amount(0,2f))
                .addModule(StatBoostModule.add(ToolStats.ARMOR).amount(0,2f))
                .addModule(StatBoostModule.add(ToolStats.KNOCKBACK_RESISTANCE).amount(0,0.1f))
                .addModule(new ArmorLevelModule(KEY_RAINBOW_KIT,false,null))
                .addModule(MobEffectModule.builder(MobEffects.GLOWING)
                        .chance(LevelingValue.flat(1)).time(RandomLevelingValue.perLevel(0,200)).build()
                ).addModule(new VolatileFlagModule(IndestructibleItemEntity.INDESTRUCTIBLE_ENTITY));
        buildModifier(RESPIRATION_ENHANCED).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(EnchantmentModule.builder(Enchantments.RESPIRATION).level(5).constant());

        buildModifier(NETHERSTAR_BLESSING).levelDisplay(ModifierLevelDisplay.DEFAULT)
                .addModule(ProtectionModule.builder()
                        .source(DamageSourcePredicate.tag(TinkerTags.DamageTypes.MAGIC_PROTECTION)).amount(0,10))
                .addModule(BlockDamageSourceModule.source(DamageSourcePredicate.or(
                                DamageSourcePredicate.tag(DamageTypeTags.IS_FIRE),
                                DamageSourcePredicate.tag(DamageTypeTags.IS_EXPLOSION),
                                DamageSourcePredicate.tag(DamageTypeTags.IS_LIGHTNING))).build());
        buildModifier(REDSTONE_PLATED).levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(MaterialRepairModule.material(TiAcMeMaterials.REDSTONE_ALLOY.getMaterialId()).constant(200));

    }
    public static ICondition modLoaded(String modId, boolean isOriginal){
        return new AndCondition(new CompatConfigCondition(modId,isOriginal),new ModLoadedCondition(modId));
    }

    @Override
    public String getName() {
        return "TiAcMe Modifier Provider";
    }

    public static class ModifierIds{
        public static final ModifierId THERMAL_FOUNDATION = new ModifierId(TinkersAdvanced.getLocation("thermal_foundation"));
        public static final ModifierId BASALZ_ENHANCE = new ModifierId(TinkersAdvanced.getLocation("basalz_enhance"));
        public static final ModifierId BLITZ_ENHANCE = new ModifierId(TinkersAdvanced.getLocation("blitz_enhance"));
        public static final ModifierId BLIZZ_ENHANCE = new ModifierId(TinkersAdvanced.getLocation("blizz_enhance"));
        public static final ModifierId OBSIDIANITE = new ModifierId(TinkersAdvanced.getLocation("obsidianite"));
        public static final ModifierId RAINBOW_KIT = new ModifierId(TinkersAdvanced.getLocation("rainbow_kit"));
        public static final ModifierId RESPIRATION_ENHANCED = new ModifierId(TinkersAdvanced.getLocation("respiration_enhanced"));
        public static final ModifierId NETHERSTAR_BLESSING = new ModifierId(TinkersAdvanced.getLocation("netherstar_blessing"));
        public static final ModifierId REDSTONE_PLATED = new ModifierId(TinkersAdvanced.getLocation("redstone_plated"));
        public static final ModifierId ATOM_GRADE = new ModifierId(TinkersAdvanced.getLocation("atom_grade"));
        public static final ModifierId EDGING_TECHNOLOGY = new ModifierId(TinkersAdvanced.getLocation("edging_technology"));
        public static final ModifierId QUANTUM_TECHNOLOGY = new ModifierId(TinkersAdvanced.getLocation("quantum_technology"));
    }
    public static class DataKeys{
        public static final TinkerDataCapability.TinkerDataKey<Integer> KEY_RAINBOW_KIT = TinkerDataCapability.TinkerDataKey.of(TinkersAdvanced.getLocation("rainbow_kit"));
    }
}
