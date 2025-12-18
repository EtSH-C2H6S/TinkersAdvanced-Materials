package com.c2h6s.tinkers_advanced_materials;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Objects;

public class TiAcMeConfig {
    public static final String KEY_MATERIAL_BEHAVIOUR = "Modifiers behaviour";
    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;
    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }
    public static final ForgeConfigSpec clientSpec;
    public static final Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        clientSpec = specPair.getRight();
        CLIENT = specPair.getLeft();
    }
    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TiAcMeConfig.commonSpec);
    }
    public static class Common{
        public final ForgeConfigSpec.IntValue SHAPING_MAX_SLOT;
        public final ForgeConfigSpec.IntValue SHAPING_DAMAGES_EACH_SLOT;

        public final ForgeConfigSpec.IntValue PROTO_REFINING_BONUS_LEVEL;
        public final ForgeConfigSpec.IntValue PROTO_REFINING_TIMES_REQUIRED;

        public final ForgeConfigSpec.DoubleValue IRRADIUM_RADIATION_INFLICT;
        public final ForgeConfigSpec.DoubleValue IRRADIUM_MAX_BONUS;
        public final ForgeConfigSpec.DoubleValue IRRADIUM_MAX_BONUS_ARMOR;
        public final ForgeConfigSpec.DoubleValue IRRADIUM_BONUS_PER_Sv;

        public final ForgeConfigSpec.DoubleValue FRAGILE_CHANCE;
        public final ForgeConfigSpec.IntValue FRAGILE_EXTRA_COST;

        public final ForgeConfigSpec.DoubleValue REACTIVE_EXPLOSIVE_ARMOR_REDUCTION;
        public final ForgeConfigSpec.BooleanValue REACTIVE_EXPLOSIVE_ARMOR_IMMUNITY;

        public final ForgeConfigSpec.IntValue NUTRITIVE_SLIME_COST;
        public final ForgeConfigSpec.IntValue NUTRITIVE_SLIME_RECOVER;

        public final ForgeConfigSpec.DoubleValue RETURN_TO_SLIME_CHANCE;

        public final ForgeConfigSpec.DoubleValue ANNIHILATE_EXPLOSION_ATTACK_MULTIPLIER;
        public final ForgeConfigSpec.DoubleValue ANNIHILATE_EXPLOSION_SELF_MULTIPLIER;

        public final ForgeConfigSpec.IntValue DISINTEGRATE_EACH_BONUS;
        public final ForgeConfigSpec.IntValue DISINTEGRATE_MAX_BONUS;
        public final ForgeConfigSpec.IntValue DISINTEGRATE_EACH_DECREASE;

        public final ForgeConfigSpec.DoubleValue VOID_DODGING_CHANCE;

        public static ForgeConfigSpec.IntValue FLUX_INFUSE_CONSUMPTION;
        public static ForgeConfigSpec.IntValue FLUX_SLASH_CONSUMPTION;
        public static ForgeConfigSpec.DoubleValue FLUX_SLASH_FLUX_DAMAGE;
        public static ForgeConfigSpec.DoubleValue FLUX_SLASH_BASIC_SLASH_DAMAGE;
        public static ForgeConfigSpec.DoubleValue FLUX_SLASH_SLASH_DAMAGE_PER_SHARPNESS;
        public static ForgeConfigSpec.DoubleValue FLUX_SLASH_SLASH_DAMAGE_FROM_ATTACK_DAMAGE;
        public static ForgeConfigSpec.DoubleValue FLUX_SLASH_DIG_SPEED_BONUS;
        public static ForgeConfigSpec.IntValue FLUX_ARROW_CONSUMPTION;
        public static ForgeConfigSpec.DoubleValue FLUX_ARROW_BASE_EXPLOSION_DAMAGE;
        public static ForgeConfigSpec.DoubleValue FLUX_ARROW_EXPLOSION_DAMAGE_FROM_DAMAGE;
        public static ForgeConfigSpec.IntValue FLUX_ARMOR_CONSUMPTION;
        public static ForgeConfigSpec.DoubleValue FLUX_ARMOR_DODGE_RATE;
        public static ForgeConfigSpec.DoubleValue FLUX_ARMOR_DAMAGE_REDUCTION;

        public final ForgeConfigSpec.DoubleValue ANTI_NEUTRONIUM_OVERSLIME_MULTIPLIER;
        public final ForgeConfigSpec.IntValue ANTI_NEUTRONIUM_OVERSLIME_MAX_CONSUMPTION;
        public final ForgeConfigSpec.DoubleValue ANTI_NEUTRONIUM_MAX_DAMAGE_MUL;
        public final ForgeConfigSpec.IntValue CHARGE_PER_ANTI_NEUTRONIUM;
        public final ForgeConfigSpec.IntValue OVERSLIME_PER_ANTI_NEUTRONIUM;
        public final ForgeConfigSpec.IntValue ANTI_NEUTRONIUM_OVERSLIME_MAX_CONSUMPTION_ARMOR;
        public final ForgeConfigSpec.BooleanValue ANTI_NEUTRONIUM_EXPLOSION_BYPASS_INVUL;

        public final ForgeConfigSpec.DoubleValue HEAVY_MATERIAL_DAMAGE_PERC;
        public final ForgeConfigSpec.DoubleValue HEAVY_MATERIAL_MOVEMENT_PERC;
        public final ForgeConfigSpec.DoubleValue HEAVY_MATERIAL_ATK_SPEED_PERC;

        public final ForgeConfigSpec.DoubleValue ENERGY_REINFORCED_DURABILITY_ENHANCE;
        public final ForgeConfigSpec.IntValue ENERGY_REINFORCED_ENERGY_CAPACITY;
        public final ForgeConfigSpec.IntValue ENERGY_REINFORCED_CONSUMPTION;

        public final ForgeConfigSpec.DoubleValue SUPERCONDUCT_ARMOR_PIERCE;
        public final ForgeConfigSpec.IntValue SUPERCONDUCT_CONSUMPTION;

        public final ForgeConfigSpec.IntValue BLAZING_DISTANCE;

        public final ForgeConfigSpec.DoubleValue LIFE_MELODY_BONUS_PERC;
        public final ForgeConfigSpec.DoubleValue LIFE_MELODY_DAMAGE_CAP;

        public final ForgeConfigSpec.IntValue OCEANIC_WHIRL_DRILL_STRENGTH;
        public final ForgeConfigSpec.IntValue OCEANIC_WHIRL_CONSUMPTION;
        public final ForgeConfigSpec.IntValue OCEANIC_WHIRL_CD;

        public final ForgeConfigSpec.DoubleValue STAR_GAZING_DROP_CHANCE;
        public final ForgeConfigSpec.IntValue STAR_GAZING_UNIVERSAL_RESTRICTION;
        public final ForgeConfigSpec.ConfigValue<List<String>> STAR_GAZING_ITEM_LIST;

        public final ForgeConfigSpec.DoubleValue EFFECT_TETANUS_DAMAGE_MULTIPLIER;
        public final ForgeConfigSpec.DoubleValue EFFECT_PROTO_POISON_HEALTH_DECREASE;
        public final ForgeConfigSpec.DoubleValue EFFECT_PROTO_POISON_REGENERATION_DECREASE;
        public final ForgeConfigSpec.DoubleValue EFFECT_PLAGUE_DAMAGE;
        public final ForgeConfigSpec.DoubleValue EFFECT_PLAGUE_REGENERATION_DECREASE;

        public final ForgeConfigSpec.DoubleValue FROSTED_CHARGE_EACH_SEC;
        public final ForgeConfigSpec.DoubleValue FROSTED_CHARGE_CONSUMPTION;

        public final ForgeConfigSpec.DoubleValue FROSTED_SHIELD_DAMAGE_BLOCK;
        public final ForgeConfigSpec.DoubleValue FROSTED_ATTACK_EXTRA_DAMAGE;

        public final ForgeConfigSpec.IntValue DEFENSE_MELODY_INVULNERABLE_TICKS;

        public final ForgeConfigSpec.IntValue NEURO_EXCITEMENT_TICKS_LASTING;
        public final ForgeConfigSpec.DoubleValue NEURO_EXCITEMENT_CRITICAL_MODIFIER_BONUS;

        public final ForgeConfigSpec.DoubleValue ENDER_BREATHE_AOE_RADIUS;
        public final ForgeConfigSpec.DoubleValue ENDER_BREATHE_AOE_DAMAGE_PERC;

        public final ForgeConfigSpec.DoubleValue STAR_HEAL_MIN_DAMAGE;
        public final ForgeConfigSpec.IntValue STAR_HEAL_DP_CD;

        public final ForgeConfigSpec.DoubleValue NIGHT_SLAYER_MAX_DAMAGE_PERC;
        public final ForgeConfigSpec.DoubleValue NIGHT_SLAYER_MAX_PROTECTION_LEVEL;

        public final ForgeConfigSpec.DoubleValue LUMINOUS_PIERCER_MAX_DAMAGE_PERC;
        public final ForgeConfigSpec.DoubleValue LUMINOUS_PIERCER_MAX_PROTECTION_LEVEL;

        public final ForgeConfigSpec.ConfigValue<List<String>> SLIMEFUL_OVERWORLD_ITEMS;
        public final ForgeConfigSpec.ConfigValue<List<String>> SLIMEFUL_NETHER_ITEMS;
        public final ForgeConfigSpec.ConfigValue<List<String>> SLIMEFUL_END_ITEMS;


        public final ForgeConfigSpec.BooleanValue ALLOW_BISMUTHINITE;
        public final ForgeConfigSpec.BooleanValue ALLOW_STIBNITE;
        public final ForgeConfigSpec.BooleanValue ALLOW_LEAN_IRIDIUM;
        public final ForgeConfigSpec.BooleanValue ALLOW_STIBNITE_UNSTABLE;


        public final ForgeConfigSpec.BooleanValue EXPLODING_FUSION_REACTOR;



        public Common(ForgeConfigSpec.Builder builder){
            builder.comment("***注意！").comment("***Notice")
                    .comment("这些配置并不会同步修改语言文件中的描述，当你修改了某个工具/工具属性的性质后如果想要更改描述则需要手动覆盖语言文件！")
                    .comment("This configure won't affect language display. When a certain modifier or tool is configured, please manually replace the description of the relating language contents.");
            builder.comment("Worldgen").comment("世界生成").push("worldgen");
            this.ALLOW_BISMUTHINITE = builder.comment("Allows Bismuthinite generation, true by default.").comment("允许辉铋矿生成，默认是。")
                    .define("allow_bismuthinite",true);
            this.ALLOW_STIBNITE = builder.comment("Allows Stibnite generation, true by default.").comment("允许辉锑矿生成，默认是。")
                    .define("allow_stibnite",true);
            this.ALLOW_STIBNITE_UNSTABLE = builder.comment("Allows Unstable Stibnite generation when mining Stibnite, true by default.").comment("允许挖掘辉锑矿时使周围的辉锑矿在稳定和不稳定之间切换，默认是。")
                    .define("allow_stibnite_unstable",true);
            this.ALLOW_LEAN_IRIDIUM = builder.comment("Allows Iridium generation, true by default.").comment("允许贫铱矿生成，默认是。")
                    .define("allow_iridium",true);
            builder.pop();

            builder.comment("Modifiers behaviour").comment("词条性质").push("modifier_behaviour");

            builder.comment("Flux Infuse").comment("通量注入");
            FLUX_INFUSE_CONSUMPTION = builder.comment("通量注入抵消耐久消耗需要的能量，默认250FE")
                    .comment("FE required for each durability loss, 250FE by default")
                    .defineInRange("flux_infuse_consumption",250,0,Integer.MAX_VALUE);
            builder.comment("Flux Slash").comment("通量赋能-近战");
            FLUX_SLASH_CONSUMPTION = builder.comment("通量斩击的基础消耗（通量伤害消耗采用此值，剑气消耗为此值的2倍），默认250FE")
                    .comment("Basic consumption for Flux Slash, 250FE by default.")
                    .defineInRange("flux_slash_consumption",250,0,Integer.MAX_VALUE);
            FLUX_SLASH_FLUX_DAMAGE = builder.comment("通量斩击的额外通量伤害，默认8")
                    .comment("Additional Flux damage for Flux Slash, 8 by default.")
                    .defineInRange("flux_slash_flux_damage",8f,0,Float.MAX_VALUE);
            FLUX_SLASH_BASIC_SLASH_DAMAGE = builder.comment("通量斩击的剑气基础伤害，默认8")
                    .comment("Base damage for the slash from Flux Slash, 8 by default.")
                    .defineInRange("flux_slash_base_slash_damage",8,0,Float.MAX_VALUE);
            FLUX_SLASH_SLASH_DAMAGE_PER_SHARPNESS = builder.comment("通量斩击的剑气受锋利词条的伤害加成，默认1每级")
                    .comment("Sharpness modifier bonus damage for Slash from Flux Slash, 1 by default.")
                    .defineInRange("flux_slash_damage_per_sharpness",1,0,Float.MAX_VALUE);
            FLUX_SLASH_SLASH_DAMAGE_FROM_ATTACK_DAMAGE = builder.comment("通量斩击的剑气继承自工具伤害的伤害比例，默认0.75x")
                    .comment("Damage bonus from tool's attack damage, 0.75x by default.")
                    .defineInRange("flux_slash_damage_from_attack_damage",0.75,0,Float.MAX_VALUE);
            FLUX_SLASH_DIG_SPEED_BONUS = builder.comment("通量斩击的挖掘加速（注能模式下为此值2倍），默认100%")
                    .comment("Mining boost for Flux Slash, 100% by default.")
                    .defineInRange("flux_slash_dig_speed_bonus",1,0,Float.MAX_VALUE);
            builder.comment("Flux Arrow").comment("通量赋能-远程");
            FLUX_ARROW_CONSUMPTION = builder.comment("通量箭矢的基础消耗（箭矢增伤消耗采用此值，产生箭矢消耗为此值的2倍，箭矢爆炸为此值的3倍，复制药水箭消耗为此值的4倍），默认250FE")
                    .comment("Basic consumption for Flux Arrow, 250FE by default.")
                    .defineInRange("flux_arrow_consumption",250,0,Integer.MAX_VALUE);
            FLUX_ARROW_BASE_EXPLOSION_DAMAGE = builder.comment("通量箭矢的爆炸伤害，默认8")
                    .comment("Arrow explosion damage for Flux Arrow, 8 by default.")
                    .defineInRange("flux_arrow_explosion_damage",8,0,Float.MAX_VALUE);
            FLUX_ARROW_EXPLOSION_DAMAGE_FROM_DAMAGE = builder.comment("通量箭矢爆炸受到箭矢伤害的增幅，默认0.5x")
                    .comment("Additional damage for the arrow explosion boosted by arrow damage from Flux Arrow, 0.5x by default.")
                    .defineInRange("flux_arrow_explosion_damage_from_arrow_damage",0.5,0,Float.MAX_VALUE);
            builder.comment("Flux Armor").comment("通量赋能-护甲");
            FLUX_ARMOR_CONSUMPTION = builder.comment("通量护甲的基础消耗（减伤消耗采用此值，闪避消耗为此值的10倍），默认2000FE")
                    .comment("Basic consumption for Flux Armor, 2000FE by default.")
                    .defineInRange("flux_armor_consumption",2000,0,Integer.MAX_VALUE);
            FLUX_ARMOR_DAMAGE_REDUCTION = builder.comment("通量护甲的伤害减免，默认0.3x")
                    .comment("Damage Reduction for Flux Armor, 0.3x by default.")
                    .defineInRange("flux_armor_damage_reduction",0.3,0,1f);
            FLUX_ARMOR_DODGE_RATE = builder.comment("通量护甲的闪避率，默认0.1")
                    .comment("Dodge rate for Flux Armor, 0.05 by default.")
                    .defineInRange("flux_armor_dodge_rate",0.1,0,1f);

            builder.comment("Shaping").comment("塑性");
            this.SHAPING_MAX_SLOT = builder.comment("最大加槽，默认3。")
                    .comment("Max upgrade slot bonus for Shaping modifier, 3 by default.")
                    .defineInRange("shaping_max_slot",3,1,Integer.MAX_VALUE);
            this.SHAPING_DAMAGES_EACH_SLOT = builder.comment("How many durability loss is needed for Shaping modifier to gain 1 slot, 500 by default.").comment("磨损多少耐久才加1升级槽，默认500。")
                    .defineInRange("shaping_damages_each_slot",500,1,Integer.MAX_VALUE);

            builder.comment("Proto Refining").comment("原体精炼");
            this.PROTO_REFINING_BONUS_LEVEL = builder.comment("每级最大时运增益，默认10。")
                    .comment("Bonus enchantment level each trait level for Proto Refining, 3 by default.")
                    .defineInRange("proto_refining_bonus",10,0,Integer.MAX_VALUE);
            this.PROTO_REFINING_TIMES_REQUIRED = builder.comment("挖多少次增加1级时运，默认5。")
                    .comment("How many times needed for Proto Refining to gain bonus, 5 by default.")
                    .defineInRange("proto_refining_requirement",5,1,Integer.MAX_VALUE);

            builder.comment("Radiation Burning and Radioactive Armor").comment("镭光合金相关");
            this.IRRADIUM_MAX_BONUS = builder.comment("辐射灼伤最大增益，默认0.75。")
                    .comment("Max bonus for Radiation Burning, 0.75 by default.")
                    .defineInRange("irradium_max_bonus",0.75,0,Float.MAX_VALUE);
            this.IRRADIUM_MAX_BONUS_ARMOR = builder.comment("放射性护甲最大增益，默认0.75。")
                    .comment("Max bonus for Radioactive Armor, 0.75 by default.")
                    .defineInRange("irradium_max_bonus_armor",0.75,0,1);
            this.IRRADIUM_RADIATION_INFLICT = builder.comment("辐射施加，默认1.0Sv。")
                    .comment("Radiation amount for Radiation Burning and Radioactive Armor each trait level, 1.0 Sv by default.")
                    .defineInRange("irradium_radiation_inflict",1d,0,Double.MAX_VALUE);
            this.IRRADIUM_BONUS_PER_Sv = builder.comment("每Sv辐射造成的增幅，默认0.05。")
                    .comment("Bonus for Radiation Burning and Radioactive Armor each Sv, 0.05 by default.")
                    .defineInRange("irradium_bonus_per_sv",0.05d,0,1);

            builder.comment("Fragile").comment("脆性");
            this.FRAGILE_CHANCE = builder
                    .comment("Chance for extra durability draw for Fragile Modifier, 0.1 by default.")
                    .comment("脆性额外消耗耐久的概率，默认0.1。")
                    .defineInRange("fragile_chance",0.1d,0,1);
            this.FRAGILE_EXTRA_COST = builder
                    .comment("Extra durability cost for Fragile, 1 by default.").comment("额外消耗的耐久，默认1。")
                    .defineInRange("fragile_cost",1,0,Integer.MAX_VALUE);

            builder.comment("Reactive Explosive Armor");
            this.REACTIVE_EXPLOSIVE_ARMOR_IMMUNITY = builder.comment("允许爆炸反应装甲免疫火焰与爆炸伤害，默认是。")
                    .comment("Allows Reactive Explosive Armor Modifier to block explosion and fire damage, true by default.")
                    .define("reactive_explosive_armor_immunity",true);
            this.REACTIVE_EXPLOSIVE_ARMOR_REDUCTION = builder.comment("爆炸反应装甲的伤害减免，默认0.25（25%）。")
                    .comment("Damage Reduction for Reactive Explosive Armor, 0.25 by default.")
                    .defineInRange("reactive_explosive_armor_reduction",0.25d,0,1);

            builder.comment("Nutritive Slime").comment("营养黏液");
            this.NUTRITIVE_SLIME_COST = builder.comment("每点饱食度需要的黏液覆层消耗量，默认20。")
                    .comment("Overslime consumption for each food level, 20 by default.")
                    .defineInRange("nutritive_slime_cost",20,0,Integer.MAX_VALUE);
            this.NUTRITIVE_SLIME_RECOVER = builder.comment("每级一次性加的饱食度，默认1。")
                    .comment("Food level to add each trait level, 1 by default.")
                    .defineInRange("nutritive_slime_recover",1,0,Integer.MAX_VALUE);

            builder.comment("Return to Slime").comment("万物归于黏液");
            this.RETURN_TO_SLIME_CHANCE = builder.comment("恢复黏液覆层的概率，默认20%")
                    .comment("Overslime recover chance for Return to Slime each trait level, 20% by default.")
                    .defineInRange("return_to_slime_chance",0.2,0,1d);

            builder.comment("Annihilation").comment("湮灭");
            this.ANNIHILATE_EXPLOSION_ATTACK_MULTIPLIER = builder
                    .comment("湮灭词条造成的爆炸伤害倍率，默认5倍。")
                    .comment("Explosion damage multiplier for Annihilation Modifier, 5 by default.")
                    .defineInRange("annihilation_attack_multiplier",5d,0,Float.MAX_VALUE);
            this.ANNIHILATE_EXPLOSION_SELF_MULTIPLIER = builder
                    .comment("湮灭词条对自己造成的爆炸伤害倍率，默认2.5倍")
                    .comment("Explosion damage multiplier that deals to attacker, 2.5 by default.")
                    .defineInRange("annihilation_attack_self_multiplier",2.5d,0,Float.MAX_VALUE);

            builder.comment("Disintegrate").comment("解离");
            this.DISINTEGRATE_EACH_BONUS = builder
                    .comment("挖掘每个方块带来的加成（单位为%），默认20%。注：耐久惩罚是加成的一半。")
                    .comment("Bonus each block mined (%), 20(%) by default")
                    .defineInRange("disintegrate_each_bonus",20,0,Integer.MAX_VALUE);
            this.DISINTEGRATE_MAX_BONUS = builder
                    .comment("最大加成（单位为%），默认2000%。")
                    .comment("Max bonus, 2000% by default.")
                    .defineInRange("disintegrate_max_bonus",2000,0,Integer.MAX_VALUE);
            this.DISINTEGRATE_EACH_DECREASE = builder
                    .comment("停止挖掘多久后清除加成，默认5秒。")
                    .comment("Clear bonus after ticks of stopping, 5s by default.")
                    .defineInRange("disintegrate_each_decrease",5,0,Integer.MAX_VALUE);

            builder.comment("Void Dodging").comment("虚无闪避");
            this.VOID_DODGING_CHANCE = builder
                    .comment("每级闪避箭矢的概率，默认0.25。")
                    .comment("Chance for dodging arrow each level, 0.25 by default.")
                    .defineInRange("void_dodging_chance",0.25,0,1);

            builder.comment("Anti-Neutronium").comment("反中子");
            this.ANTI_NEUTRONIUM_MAX_DAMAGE_MUL = builder
                    .comment("湮灭性黏液词条产生的湮灭爆炸的最大伤害倍率，默认每级 2.0x。")
                    .comment("Max damage multiplier for Anihilating Slime each level, 1.0x by default.")
                    .defineInRange("AnnihilatingSlimeMaxDamageMul",2,0,Float.MAX_VALUE);
            this.ANTI_NEUTRONIUM_OVERSLIME_MAX_CONSUMPTION = builder
                    .comment("湮灭性黏液词条湮灭爆炸达到1倍伤害需要消耗的黏液覆层量，默认1000。")
                    .comment("Overslime consumption rate for Anihilating Slime, 1000 by default.")
                    .defineInRange("AnnihilatingSlimeOverslimeConsumption",1000,0,Integer.MAX_VALUE);
            this.ANTI_NEUTRONIUM_OVERSLIME_MULTIPLIER = builder
                    .comment("湮灭性黏液词条增幅黏液覆层上限的倍率，默认10。")
                    .comment("Overslime stat multiplier for Anihilating Slime, 10 by default.")
                    .defineInRange("AnnihilatingSlimeOverslimeMul",10,0,Float.MAX_VALUE);
            this.CHARGE_PER_ANTI_NEUTRONIUM = builder
                    .comment("分型自组装词条每个反中子锭的充能量，默认8000。")
                    .comment("Anti Neutronium charge per ingot for Neutronium Assemble, 8000 by default.")
                    .defineInRange("NeutroniumAssemblePerIngot",8000,1,128000);
            this.OVERSLIME_PER_ANTI_NEUTRONIUM = builder
                    .comment("分型自组装词条每点反中子充能能转化多少黏液覆层，不建议设置的过高，默认10。")
                    .comment("Overslime convertion per Anti-Neutronium Ingot")
                    .defineInRange("NeutroniumAssembleOverslimePerAntiNeutronium",10,1,32767);
            this.ANTI_NEUTRONIUM_OVERSLIME_MAX_CONSUMPTION_ARMOR = builder
                    .comment("湮灭性黏液词条护甲达成减伤需要消耗的黏液覆层量，默认2000。")
                    .comment("Overslime consumption rate for Anihilating Slime Armor, 2000 by default.")
                    .defineInRange("AnnihilatingSlimeOverslimeConsumptionArmor",2000,0,Integer.MAX_VALUE);
            this.ANTI_NEUTRONIUM_EXPLOSION_BYPASS_INVUL = builder
                    .comment("将湮灭爆炸的伤害改为无视无敌的类型，默认true。")
                    .comment("Transforms damage source from annihilate explosion to bypass invulnerable source, true by default.")
                    .define("AnnihilatingSlimeBypassInvulnerable",true);

            builder.comment("Heavy Material").comment("重质");
            this.HEAVY_MATERIAL_DAMAGE_PERC = builder
                    .comment("重质词条伤害增幅，默认0.15（+15%）。")
                    .comment("Heavy Material damage bonus, 0.15 (+15%) by default")
                    .defineInRange("HeavyMaterialDamagePerc",0.15,0.01,Float.MAX_VALUE);
            this.HEAVY_MATERIAL_MOVEMENT_PERC = builder
                    .comment("重质词条移速降低，默认0.1（-10%）。")
                    .comment("Heavy Material movement decrease, 0.1 (-10%) by default")
                    .defineInRange("HeavyMaterialMSpeedPerc",0.1,0.01,Float.MAX_VALUE);
            this.HEAVY_MATERIAL_ATK_SPEED_PERC = builder
                    .comment("重质词条攻速减少，默认0.05（-5%）。")
                    .comment("Heavy Material attack speed decrease, 0.05 (-5%) by default")
                    .defineInRange("HeavyMaterialATKSpeedPerc",0.05,0.01,Float.MAX_VALUE);

            builder.comment("Energy Enhanced").comment("通量加固");
            this.ENERGY_REINFORCED_DURABILITY_ENHANCE = builder
                    .comment("通量加固词条每级减免的耐久消耗，默认20%。")
                    .comment("Durability save percentage for Energy Enhanced, 20% by default.")
                    .defineInRange("EnergyEnhancedEffect",0.20f,0.001f,1f);
            this.ENERGY_REINFORCED_ENERGY_CAPACITY = builder
                    .comment("通量加固词条每级增加的能量上限，默认100000FE。")
                    .comment("Energy capacity addition per level, 100000 FE by default.")
                    .defineInRange("EnergyEnhancedEnergyCap",10000,0,Integer.MAX_VALUE);
            this.ENERGY_REINFORCED_CONSUMPTION = builder
                    .comment("通量加固词条每格挡1耐久消耗需要的能量，默认250FE。")
                    .comment("Energy Reinforced energy each durability loss, 250FE by default.")
                    .defineInRange("EnergyReinforcedConsumption",250,0,Integer.MAX_VALUE);

            builder.comment("Superconduct").comment("超导");
            this.SUPERCONDUCT_ARMOR_PIERCE = builder
                    .comment("超导的护甲穿透，默认每级50%。")
                    .comment("Superconduct armor pierce value, 50% by default.")
                    .defineInRange("SuperconductArmorPierce",0.5f,0.001f,Float.MAX_VALUE/2);
            this.SUPERCONDUCT_CONSUMPTION = builder
                    .comment("超导的能量消耗，默认100FE。")
                    .comment("Superconduct energy consumption, 100 FE by default.")
                    .defineInRange("SuperconductConsumption",100,0,Integer.MAX_VALUE);

            builder.comment("Blazing").comment("烈焰");
            this.BLAZING_DISTANCE = builder
                    .comment("烈焰的点燃范围，默认5格。")
                    .comment("Blazing ignite range, 5 by default.")
                    .defineInRange("BlazingIgniteRange",5,1,Integer.MAX_VALUE);

            builder.comment("Life Melody").comment("生命旋律");
            this.LIFE_MELODY_BONUS_PERC = builder
                    .comment("生命旋律的已损生命值增伤比例，默认0.05x。")
                    .comment("Life Melody damage bonus, 0.05x by default.")
                    .defineInRange("LifeMelodyDamageBonus",0.05,0.01,Float.MAX_VALUE);
            this.LIFE_MELODY_DAMAGE_CAP = builder
                    .comment("生命旋律的最大增伤，默认无上限")
                    .comment("Life Melody damage bonus cap, no cap by default.")
                    .defineInRange("LifeMelodyDamageBonusCap",Float.MAX_VALUE,0.01,Float.MAX_VALUE);

            builder.comment("Defense Melody").comment("防守旋律");
            this.DEFENSE_MELODY_INVULNERABLE_TICKS = builder
                    .comment("防守旋律的受击后无敌帧增量，默认每级50刻。")
                    .defineInRange("DefenseMelodyInvulnerableTicks",50,10,Integer.MAX_VALUE);

            builder.comment("Oceanic Whirl").comment("海洋涡流");
            this.OCEANIC_WHIRL_DRILL_STRENGTH = builder
                    .comment("海洋涡流词条的激流速度，默认每级2，表明一级时其强度相当于激流II。")
                    .comment("Oceanic Whirl drill attack strength, 2 by default.")
                    .defineInRange("OceanicWhirlDrillAttackStrength",2,1,Integer.MAX_VALUE);
            this.OCEANIC_WHIRL_CD = builder
                    .comment("海洋涡流词条的激流CD，默认10刻。")
                    .comment("Oceanic Whirl drill attack CD, 10ticks by default.")
                    .defineInRange("OceanicWhirlDrillAttackCD",10,0,Integer.MAX_VALUE);
            this.OCEANIC_WHIRL_CONSUMPTION = builder
                    .comment("海洋涡流词条的耐久消耗，默认10。")
                    .comment("Oceanic Whirl durability cost, 10 by default.")
                    .defineInRange("OceanicWhirlDrillAttackCost",10,0,Integer.MAX_VALUE);

            builder.comment("Frosted Charge").comment("霜冻充能");
            this.FROSTED_CHARGE_CONSUMPTION = builder
                    .comment("结霜值的消耗量，默认2.5%。")
                    .defineInRange("FrostChargeConsumptionEachUse",0.025,0,1);
            this.FROSTED_CHARGE_EACH_SEC = builder
                    .comment("结霜值每秒增量，默认10%")
                    .defineInRange("FrostChargeIncreasementEachSecond",0.1,0.001,1);

            builder.comment("Frosted Attack").comment("结霜攻击");
            this.FROSTED_ATTACK_EXTRA_DAMAGE = builder
                    .comment("结霜攻击造成的额外寒冷伤害，默认20%（乘算）。")
                    .defineInRange("FrostAttackExtraDamagePerc",0.2f,0,Float.MAX_VALUE);
            builder.comment("Frosted Shield").comment("结霜防御");
            this.FROSTED_SHIELD_DAMAGE_BLOCK = builder
                    .comment("结霜防御格挡的伤害值，默认每级0.25（加算）。")
                    .defineInRange("FrostShieldDamageBlock",0.25f,0,Float.MAX_VALUE);

            builder.comment("Star Gazing").comment("星语");
            this.STAR_GAZING_DROP_CHANCE = builder
                    .comment("星语生效时掉落碎星的概率，默认0.2（20%）。")
                    .defineInRange("StarGazingDropChance",0.2f,0.01,1);
            this.STAR_GAZING_UNIVERSAL_RESTRICTION = builder
                    .comment("星语每刻生成碎星的全局最大数量，默认16。整个服务器共享此数目，到上限后全服玩家都不再能生成碎星。")
                    .defineInRange("StarGazingServerEntityGenerationRetriction",16,1,Integer.MAX_VALUE);
            this.STAR_GAZING_ITEM_LIST = builder
                    .comment("星语词条能产生的碎星类型，你也可以添加你整合包里的自定义星，但是必定是EffectStarItem。")
                    .define("StarGazingEffectItemList",
                            List.of(
                                    "tinkers_advanced:resistance_star",
                                    "tinkers_advanced:haste_star",
                                    "tinkers_advanced:regeneration_star",
                                    "tinkers_advanced:saturation_star",
                                    "tinkers_advanced:power_star"
                            ), Objects::nonNull);

            builder.comment("Neuro Excitement").comment("刺激");
            this.NEURO_EXCITEMENT_TICKS_LASTING = builder
                    .comment("刺激词条增幅持续时间，默认5tick。")
                    .defineInRange("NeuroExcitementBonusLastingTicks",5,0,Integer.MAX_VALUE);
            this.NEURO_EXCITEMENT_CRITICAL_MODIFIER_BONUS = builder
                    .comment("刺激词条暴击伤害倍率增幅，默认每级0.5（50%）。")
                    .defineInRange("NeuroExcitementCriticalDamageMultiplier",0.5f,0,Float.MAX_VALUE);

            builder.comment("Ender Breathe").comment("末影之息");
            this.ENDER_BREATHE_AOE_RADIUS = builder
                    .comment("末影之息的群伤半径，默认2.5格。")
                    .defineInRange("EnderBreatheAOERadius",2.5f,1f,16f);
            this.ENDER_BREATHE_AOE_DAMAGE_PERC = builder
                    .comment("末影之息的伤害比例，默认每级0.25（25%）。")
                    .defineInRange("EnderBreatheAOEDamageMultiplier",0.25f,0.01f,Float.MAX_VALUE);

            builder.comment("Star Heal").comment("星愈");
            this.STAR_HEAL_DP_CD = builder
                    .comment("星愈的免死冷却，默认90秒。")
                    .defineInRange("StarHealRevivalCD",90,0,Integer.MAX_VALUE);
            this.STAR_HEAL_MIN_DAMAGE = builder
                    .comment("星愈产生治疗星需要受到的最低伤害，默认10。")
                    .defineInRange("StarHealMinDamageRequirement",10f,0f,Float.MAX_VALUE);

            builder.comment("Night Slayer").comment("暗夜克星");
            this.NIGHT_SLAYER_MAX_DAMAGE_PERC = builder
                    .comment("暗夜克星的最高每级最高伤害增幅，默认0.25（25%）。")
                    .defineInRange("NightSlayerMaxDamageBoostEachLevel",0.25f,0.01f,Float.MAX_VALUE);
            this.NIGHT_SLAYER_MAX_PROTECTION_LEVEL = builder
                    .comment("暗夜克星的最高每级最高保护提升，默认每级5（注：匠魂每级保护提供4%减伤，有80%固有上限。此词条的保护独立于保护类强化）。")
                    .defineInRange("NightSlayerMaxProtectionEachLevel",2.5f,0.25f,Float.MAX_VALUE);

            builder.comment("Luminous Piercer").comment("曜日穿刺");
            this.LUMINOUS_PIERCER_MAX_DAMAGE_PERC = builder
                    .comment("曜日穿刺的最高每级最高穿甲，默认2（200%）。")
                    .defineInRange("LuminousPiercerMaxArmorPierceEachLevel",2f,0.01f,Float.MAX_VALUE);
            this.LUMINOUS_PIERCER_MAX_PROTECTION_LEVEL = builder
                    .comment("曜日穿刺的最高每级最高保护提升，默认每级5（注：匠魂每级保护提供4%减伤，有80%固有上限。此词条的保护独立于保护类强化）。")
                    .defineInRange("LuminousPiercerMaxProtectionEachLevel",2.5f,0.25f,Float.MAX_VALUE);

            builder.comment("Slimeful").comment("黏液富集");
            this.SLIMEFUL_OVERWORLD_ITEMS = builder
                    .comment("黏液富集在主世界的群系生成的粘液球。")
                    .define("SlimefulOverworldSlimeBalls",
                            List.of(
                                    "minecraft:slime_ball",
                                    "tconstruct:sky_slime_ball"
                            ), Objects::nonNull);
            this.SLIMEFUL_NETHER_ITEMS = builder
                    .comment("黏液富集在下界的群系生成的粘液球。")
                    .define("SlimefulNetherSlimeBalls",
                            List.of(
                                    "minecraft:magma_cream",
                                    "tconstruct:ichor_slime_ball"
                            ), Objects::nonNull);
            this.SLIMEFUL_END_ITEMS = builder
                    .comment("黏液富集在末地的群系生成的粘液球。")
                    .define("SlimefulTheEndSlimeBalls",
                            List.of(
                                    "minecraft:ender_pearl",
                                    "tconstruct:ender_slime_ball"
                            ), Objects::nonNull);




            builder.comment("Generator Modules").comment("能量模块类强化");

            builder.pop();

            builder.comment("Effects").comment("状态效果行为").push("mob_effect");
            this.EFFECT_TETANUS_DAMAGE_MULTIPLIER = builder
                    .comment("Damage multiplier for Tetanus, 1.2 by default.")
                    .comment("破伤风效果导致的受击伤害倍率。默认1.2。")
                    .defineInRange("tetanus_damage_boost",1.2,0,Integer.MAX_VALUE);

            this.EFFECT_PROTO_POISON_HEALTH_DECREASE = builder
                    .comment("Permanent health decrease for Proto poison, 25% by default.")
                    .comment("原毒效果的永久生命上限减少，默认25%。")
                    .defineInRange("proto_poison_health_decrease",0.25,0,Integer.MAX_VALUE);
            this.EFFECT_PROTO_POISON_REGENERATION_DECREASE = builder
                    .comment("Regeneration reduction for Proto Poison each level, 20% by default")
                    .comment("原毒效果的每级生命再生减少，默认20%。")
                    .defineInRange("proto_poison_regen_decrease",0.2,0,Integer.MAX_VALUE);

            this.EFFECT_PLAGUE_DAMAGE = builder
                    .comment("Damage for Plague each level, 2 by default.")
                    .comment("疫毒效果的伤害，默认2。")
                    .defineInRange("plague_health_decrease",2d,0,Integer.MAX_VALUE);
            this.EFFECT_PLAGUE_REGENERATION_DECREASE = builder
                    .comment("Regeneration reduction for Plague each level, 50% by default")
                    .comment("疫毒效果的每级生命再生减少，默认50%。")
                    .defineInRange("plague_regen_decrease",0.5,0,Integer.MAX_VALUE);
            builder.pop();


            builder.comment("HiddenMaterials").comment("隐藏材料").push("hidden_materials");
            this.EXPLODING_FUSION_REACTOR = builder.comment("Enables Mekanism fusion reactor exploding when throwing iron ingots into it. true by default.")
                    .comment("让你能够通过向朋友的Mek聚变反应堆丢入铁锭/块/粒/砧来增进彼此的感情，默认是。")
                    .define("allow_neutronite_crafting",true);
            builder.pop();
        }
    }
    public static class Client{
        public Client(ForgeConfigSpec.Builder builder){

        }
    }
}




