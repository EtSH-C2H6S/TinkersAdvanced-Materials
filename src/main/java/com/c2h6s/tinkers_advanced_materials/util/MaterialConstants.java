package com.c2h6s.tinkers_advanced_materials.util;

import com.c2h6s.etstlib.register.EtSTLibToolStat;
import slimeknights.tconstruct.library.tools.stat.INumericToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;
import java.util.UUID;

public class MaterialConstants {
    public static final UUID PROTO_POISON_UUID = UUID.fromString("18038687-2500-7a0c-ba0c-744c441ad40e");

    public static final List<? extends INumericToolStat<?>> COMMON_STATS = List.of(
            ToolStats.ATTACK_DAMAGE,ToolStats.ARMOR,ToolStats.ACCURACY,ToolStats.ARMOR_TOUGHNESS,ToolStats.ATTACK_SPEED,
            ToolStats.PROJECTILE_DAMAGE,ToolStats.VELOCITY,ToolStats.KNOCKBACK_RESISTANCE,ToolStats.BLOCK_AMOUNT,
            ToolStats.BLOCK_ANGLE,ToolStats.DURABILITY, EtSTLibToolStat.BASIC_AIR_CAPACITY,EtSTLibToolStat.PIERCE,EtSTLibToolStat.MAX_PRESSURE,
            EtSTLibToolStat.FLUID_EFFICIENCY,EtSTLibToolStat.POWER_MULTIPLIER,EtSTLibToolStat.RANGE,EtSTLibToolStat.SCALE
    );
}
