package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class Semiconductor extends MultiArgsDescModifier {

    protected static float CFG_DAMAGE_BOOST = 0.25f;

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if (context.isCritical()) damage+=baseDamage*CFG_DAMAGE_BOOST*modifier.getLevel();
        else damage-=baseDamage*CFG_DAMAGE_BOOST*modifier.getLevel();
        return damage;
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.0f",CFG_DAMAGE_BOOST*100)+"%"
        };
    }

    @Override
    public void refreshConfig() {
        CFG_DAMAGE_BOOST = TiAcMeConfig.COMMON.SEMI_CONDUCTOR_DAMAGE_MODIFICATION.get().floatValue();
    }
}
