package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.combat;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.entity.EffectStarEntity;
import com.c2h6s.tinkers_advanced_materials.content.item.EffectStarItem;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import com.c2h6s.tinkers_advanced_materials.util.MeConfigUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class StarGazing extends MultiArgsDescModifier {
    protected static float CFG_DROP_CHANCE = 0.2f;
    protected static List<EffectStarItem> CFG_ALL_STARS = TiAcMeItems.EFFECT_STARS.getEntries()
            .stream().filter(RegistryObject::isPresent).map(RegistryObject::get).filter(item -> item instanceof EffectStarItem)
            .map(item -> (EffectStarItem) item).toList();
    protected static int CFG_UNIVERSAL_COUNT_LIMIT_PER_TICK = 16;

    private static int STARS_SPAWNED = 0;

    @Override
    public Object[] getDescArgs() {
        return new Object[]{
                String.format("%.0f",CFG_DROP_CHANCE*100)
        };
    }

    @Override
    public void refreshConfig() {
        CFG_DROP_CHANCE = TiAcMeConfig.COMMON.STAR_GAZING_DROP_CHANCE.get().floatValue();
        CFG_UNIVERSAL_COUNT_LIMIT_PER_TICK = TiAcMeConfig.COMMON.STAR_GAZING_UNIVERSAL_RESTRICTION.get();
        List<EffectStarItem> list = MeConfigUtils.readItemsFromConfig(TiAcMeConfig.COMMON.STAR_GAZING_ITEM_LIST,EffectStarItem.class);
        if (!list.isEmpty()) CFG_ALL_STARS = list;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event){
        if (event.phase == TickEvent.Phase.START) STARS_SPAWNED = 0;
    }

    @Override
    public void postMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt, float damage) {
        if (context.isExtraAttack()||!context.isFullyCharged()) return;
        trySpawnStars(context.getLevel(),modifier.getLevel()*CFG_DROP_CHANCE,context.getTarget().position().add(0,context.getTarget().getBbHeight()/2,0));
    }

    @Override
    public void afterArrowHit(ModDataNBT persistentData, ModifierEntry entry, ModifierNBT modifiers, AbstractArrow arrow, @Nullable LivingEntity attacker, @NotNull LivingEntity target, float damageDealt) {
        if (!arrow.isCritArrow()) return;
        trySpawnStars(target.level(),entry.getLevel()*CFG_DROP_CHANCE,target.position().add(0,target.getBbHeight()/2,0));
    }

    public static void trySpawnStars(Level level, float chance, Vec3 pos){
        if (CFG_ALL_STARS.isEmpty()) return;
        int count = (int) chance+ RANDOM.nextFloat()<(chance-(int) chance)?1:0;
        for (int i=0;i<count;i++){
            if (STARS_SPAWNED>CFG_UNIVERSAL_COUNT_LIMIT_PER_TICK) return;
            EffectStarEntity entity = new EffectStarEntity(level,pos,0.1F,CFG_ALL_STARS.get(RANDOM.nextInt(CFG_ALL_STARS.size())));
            level.addFreshEntity(entity);
            STARS_SPAWNED++;
        }
    }
}
