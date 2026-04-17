package net.toopa.unusual_furniture.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import java.util.concurrent.CompletableFuture;

public class UFRecipeProvider extends FabricRecipeProvider {

	public UFRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput exporter) {
		//This is an example of how to make a recipe
		//TODO: remove when recipes are done
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COOKED_CHICKEN)
				.requires(Items.LAVA_BUCKET)
				.requires(Items.CHICKEN)
				.unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
				.unlockedBy("has_chicken", has(Items.CHICKEN))
				.save(exporter, UnusualFurniture.id("lava chicken"));
	}
}
