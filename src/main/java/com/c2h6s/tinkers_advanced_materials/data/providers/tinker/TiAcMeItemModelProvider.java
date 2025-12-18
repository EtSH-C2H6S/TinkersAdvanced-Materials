package com.c2h6s.tinkers_advanced_materials.data.providers.tinker;

import com.c2h6s.tinkers_advanced.TinkersAdvanced;
import com.c2h6s.tinkers_advanced.core.library.registry.SimpleMaterialObject;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeItems;
import com.c2h6s.tinkers_advanced_materials.init.TiAcMeMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.registration.object.ItemObject;

import static com.c2h6s.tinkers_advanced_materials.TinkersAdvancedMaterials.MODID;
import static com.c2h6s.tinkers_advanced.core.init.TiAcCrItem.*;

public class TiAcMeItemModelProvider extends ItemModelProvider {
    public static final String PARENT_SIMPLE_ITEM ="item/generated";
    public static final String PARENT_BUCKET_FLUID ="forge:item/bucket_drip";

    public TiAcMeItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersAdvanced.MODID, existingFileHelper);
    }

    public void generateItemModel(RegistryObject<Item> object,String typePath){
        withExistingParent( object.getId().getPath(), PARENT_SIMPLE_ITEM).texture("layer0",getItemLocation(object.getId().getPath(),typePath));
    }
    public void generateItemModel(ItemObject<?> object, String typePath){
        withExistingParent( object.getId().getPath(), PARENT_SIMPLE_ITEM).texture("layer0",getItemLocation(object.getId().getPath(),typePath));
    }
    public void generateBlockItemModel(RegistryObject<BlockItem> object){
        withExistingParent(object.getId().getPath(), getBlockItemLocation(object.getId().getPath()));
    }
    public void generateBucketItemModel(FluidObject<ForgeFlowingFluid> object,boolean flip){
        withExistingParent(object.getId().getPath()+"_bucket",PARENT_BUCKET_FLUID).customLoader((itemModelBuilder,existingFileHelper)->DynamicFluidContainerModelBuilder
                .begin(itemModelBuilder,existingFileHelper)
                .fluid(object.get())
                .flipGas(flip));
    }

    public ResourceLocation getItemLocation(String path,String typePath){
        return new ResourceLocation(TinkersAdvanced.MODID,"item/"+typePath+"/"+path);
    }
    public ResourceLocation getBlockItemLocation(String path){
        return new ResourceLocation(TinkersAdvanced.MODID,"block/"+path);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> object: getListSimpleMaterialModel(MODID)){
            generateItemModel(object,"material");
        }
        for (RegistryObject<BlockItem> object:getListSimpleBlock(MODID)){
            generateBlockItemModel(object);
        }
        for (SimpleMaterialObject object: TiAcMeMaterials.MATERIALS.getEntryMap().values()){
            if (object.isSimpleModel()&&object.getItemObject()!=null){
                generateItemModel(object.getItemObject(),"material");
            }
        }
        TiAcMeItems.EFFECT_STARS.getEntries().forEach(object ->
                withExistingParent(object.getId().getPath(),PARENT_SIMPLE_ITEM)
                        .texture("layer0",getItemLocation("broken_star","misc")));
        generateBlockItemModel(TiAcMeItems.STIBNITE_ORE);
    }
}
