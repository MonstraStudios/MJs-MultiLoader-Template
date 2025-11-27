package com.myname.mymod.datagen;

import com.myname.mymod.MyModConstants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class MyModRecipeProvider extends FabricRecipeProvider {

    public MyModRecipeProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {

        // Example Recipe - stone to diamond
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.DIAMOND)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.STONE)
                .unlockedBy("has_stone",
                        InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                .save(exporter, new ResourceLocation(MyModConstants.MOD_ID, "diamond_from_stone"));

    }

}
