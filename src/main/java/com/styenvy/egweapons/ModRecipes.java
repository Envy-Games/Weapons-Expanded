package com.styenvy.egweapons;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipes extends RecipeProvider {

    public ModRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        // Red Lightsaber
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.RED_LIGHTSABER.get())
                .pattern(" R ")
                .pattern(" R ")
                .pattern(" N ")
                .define('R', Items.REDSTONE_BLOCK)
                .define('N', Items.NETHER_STAR)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(output);

        // Blue Lightsaber
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.BLUE_LIGHTSABER.get())
                .pattern(" D ")
                .pattern(" D ")
                .pattern(" N ")
                .define('D', Items.DIAMOND_BLOCK)
                .define('N', Items.NETHER_STAR)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(output);

        // Green Lightsaber
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.GREEN_LIGHTSABER.get())
                .pattern(" E ")
                .pattern(" E ")
                .pattern(" N ")
                .define('E', Items.EMERALD_BLOCK)
                .define('N', Items.NETHER_STAR)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(output);

        // Purple Lightsaber
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.PURPLE_LIGHTSABER.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" N ")
                .define('A', Items.AMETHYST_BLOCK)
                .define('N', Items.NETHER_STAR)
                .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                .save(output);
    }

    // NOTE: Do not redeclare a private has(ItemLike) here;
    // RecipeProvider already provides a protected static has(...) helper.
}