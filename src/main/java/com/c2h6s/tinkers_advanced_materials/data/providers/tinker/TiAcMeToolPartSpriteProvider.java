package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class TiAcMeToolPartSpriteProvider extends AbstractPartSpriteProvider{

    public TiAcMeToolPartSpriteProvider() {
        super("tinkers_advanced");
    }

    public String getName() {
        return "Tinkers' Advanced-Materials Part Sprite Provider";
    }

    protected void addAllSpites() {
        this.addSprite("part/ionize_chamber/ionize_chamber", new MaterialStatsId[]{HandleMaterialStats.ID});
        this.buildTool("ionized_cannon").addBreakableHead("broad_blade_1").addBreakableHead("broad_blade_2").addBreakablePart("ionize_chamber", new MaterialStatsId[]{HandleMaterialStats.ID}).addBinding("tough_collar");
        this.addSprite("part/particle_container/particle_container", new MaterialStatsId[]{HandleMaterialStats.ID});
        this.buildTool("matter_manipulator").addHead("pick_head").addBinding("tough_collar").addHandle("tough_handle").addHandle("particle_container");
        this.addSprite("part/flux_core/flux_core", new MaterialStatsId[]{StatlessMaterialStats.REPAIR_KIT.getIdentifier()});
        this.buildTool("electron_tuner").addBreakableHead("small_blade").addHead("mode_cleaver/small_blade").addHead("mode_rapier/small_blade").addHead("mode_sword/small_blade").addPart("flux_core", new MaterialStatsId[]{StatlessMaterialStats.REPAIR_KIT.getIdentifier()}).addHandle("tool_handle").addHandle("tough_handle").withLarge();
    }

}
