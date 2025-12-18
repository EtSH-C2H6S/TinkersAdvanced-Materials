package com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.harvest;

import com.c2h6s.tinkers_advanced_materials.TiAcMeConfig;
import com.c2h6s.tinkers_advanced_materials.content.tool.modifiers.base.MultiArgsDescModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.Tags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.SlimeType;

import java.util.List;

import static com.c2h6s.tinkers_advanced_materials.util.MeConfigUtils.readItemsFromConfig;

public class Slimeful extends MultiArgsDescModifier implements ProcessLootModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT);
    }

    protected static List<Item> CFG_ENDSLIME = List.of(TinkerCommons.slimeball.get(SlimeType.ENDER),Items.ENDER_PEARL);
    protected static List<Item> CFG_NETHERSLIME = List.of(TinkerCommons.slimeball.get(SlimeType.ICHOR),Items.MAGMA_CREAM);
    protected static List<Item> CFG_OVERWORLDSLIME = List.of(TinkerCommons.slimeball.get(SlimeType.SKY),Items.SLIME_BALL);

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        if (context.hasParam(LootContextParams.ORIGIN)) {
            var pos = context.getParam(LootContextParams.ORIGIN);
            var blockPos = BlockPos.containing(pos);
            var level = context.getLevel();
            if (RANDOM.nextFloat() * 5f - 1 <= context.getLuck()) {
                if (level.getBiome(blockPos).is(BiomeTags.IS_OVERWORLD)&&!CFG_OVERWORLDSLIME.isEmpty())
                    generatedLoot.add(new ItemStack(CFG_OVERWORLDSLIME.get(RANDOM.nextInt(CFG_OVERWORLDSLIME.size()))));
                if (level.getBiome(blockPos).is(BiomeTags.IS_NETHER)&&!CFG_NETHERSLIME.isEmpty())
                    generatedLoot.add(new ItemStack(CFG_NETHERSLIME.get(RANDOM.nextInt(CFG_NETHERSLIME.size()))));
                if (level.getBiome(blockPos).is(BiomeTags.IS_END)&&!CFG_ENDSLIME.isEmpty())
                    generatedLoot.add(new ItemStack(CFG_ENDSLIME.get(RANDOM.nextInt(CFG_ENDSLIME.size()))));
            }
        }
    }

    @Override
    public Object[] getDescArgs() {
        return new Object[0];
    }

    @Override
    public void refreshConfig() {
        CFG_ENDSLIME = readItemsFromConfig(TiAcMeConfig.COMMON.SLIMEFUL_END_ITEMS);
        CFG_NETHERSLIME = readItemsFromConfig(TiAcMeConfig.COMMON.SLIMEFUL_NETHER_ITEMS);
        CFG_OVERWORLDSLIME = readItemsFromConfig(TiAcMeConfig.COMMON.SLIMEFUL_OVERWORLD_ITEMS);
    }
}
