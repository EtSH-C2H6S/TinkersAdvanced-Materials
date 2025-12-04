package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.etstlib.content.block.SelfDroppingTieredBlock;
import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.TiAcCrModule;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TiAcMeBlockTagProvider extends BlockTagsProvider {
    public TiAcMeBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersAdvanced.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(TiAcMeBlocks.IRIDIUM_LEAN_ORE.get());
        tag(Tiers.DIAMOND.getTag()).add(TiAcMeBlocks.BISMUTHINITE.get(),
                TiAcMeBlocks.STIBNITE_ORE.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(TiAcMeBlocks.IRIDIUM_LEAN_ORE.get())
                .add(TiAcMeBlocks.BISMUTHINITE.get())
                .add(TiAcMeBlocks.STIBNITE_ORE.get())
        ;
        TiAcCrModule.BLOCKS.getEntries().forEach(reg->{
            if (reg.isPresent()&&reg.get() instanceof SelfDroppingTieredBlock block){
                block.getMiningTierOptional().ifPresent(blockTagKey -> tag(blockTagKey).add(block));
            }
        });
    }
}
