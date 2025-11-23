package com.myname.mymod.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.function.Consumer;

public class MyModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public MyModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> exporter) {

        // Example Recipe - stone to diamond
        ShapedRecipeBuilder.shaped(Items.DIAMOND)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', Items.STONE)
                .unlockedBy("has_stone", has(Items.STONE))
                .save(exporter, "diamond_from_stone");

    }

}
