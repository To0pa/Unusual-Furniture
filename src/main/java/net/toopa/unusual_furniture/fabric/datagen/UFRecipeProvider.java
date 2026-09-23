package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.fabricmc.fabric.mixin.content.registry.AxeItemAccessor;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.utils.DyeSet;
import net.toopa.unusual_furniture.common.utils.WoodSet;

import java.util.concurrent.CompletableFuture;

public class UFRecipeProvider extends FabricRecipeProvider {

	public UFRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput exporter) {

		UFObjects.WOODEN_TABLE_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			var strippedLog = AxeItemAccessor.getStrippedBlocks().get(woodSet.log());
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aaa")
					.pattern("b b")
					.pattern("b b")
					.define('a', plankSlab)
					.define('b', strippedLog)
					.unlockedBy(getHasName(strippedLog), has(strippedLog))
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.save(exporter);
		});

		UFObjects.WOODEN_COFFEE_TABLE_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			var strippedLog = AxeItemAccessor.getStrippedBlocks().get(woodSet.log());
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aa")
					.pattern("bb")
					.pattern("bb")
					.define('a', plankSlab)
					.define('b', strippedLog)
					.unlockedBy(getHasName(strippedLog), has(strippedLog))
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.save(exporter);
		});

		UFObjects.CHAIR_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aa")
					.pattern("aa")
					.pattern("bb")
					.define('a', plankSlab)
					.define('b', Items.STICK)
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.save(exporter);
		});

		UFObjects.STOOL_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aa")
					.pattern("bb")
					.define('a', plankSlab)
					.define('b', Items.STICK)
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.save(exporter);
		});

		UFObjects.SOFA_BLOCKS.forEachEntry((block, reLo) -> {
			DyeSet dyeSet = UFObjects.getDyeSet(block);
			if (dyeSet == null) throw new AssertionError("DyeSet is null");
			var wool = dyeSet.base();
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("a  ")
					.pattern("aaa")
					.pattern("b b")
					.define('a', wool)
					.define('b', ItemTags.PLANKS)
					.unlockedBy("has_planks", has(ItemTags.PLANKS))
					.unlockedBy(getHasName(wool), has(wool))
					.save(exporter);
		});

		UFObjects.WOODEN_CEILING_LAMP_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			// TODO: copper lamp
			var planks = woodSet.plank();
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern(" a ")
					.pattern("aba")
					.define('a', planks)
					.define('b', Items.TORCH)
					.unlockedBy(getHasName(Items.TORCH), has(Items.TORCH))
					.unlockedBy(getHasName(planks), has(planks))
					.save(exporter);
		});

		UFObjects.DRAWER_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aba")
					.pattern("aaa")
					.pattern("aba")
					.define('a', plankSlab)
					.define('b', Items.STICK)
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
					.unlockedBy(getHasName(woodSet.log()), has(woodSet.log()))
					.save(exporter);
		});

		UFObjects.BENCH_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("a  ")
					.pattern("aba")
					.pattern("a a")
					.define('a', woodSet.log())
					.define('b', Items.IRON_INGOT)
					.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
					.unlockedBy(getHasName(woodSet.log()), has(woodSet.log()))
					.save(exporter);
		});

		UFObjects.CURTAIN_BLOCKS.forEachEntry((block, reLo) -> {
			DyeSet dyeSet = UFObjects.getDyeSet(block);
			if (dyeSet == null) throw new AssertionError("DyeSet is null");
			var wool = dyeSet.base();
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("a")
					.pattern("b")
					.define('a', Items.STICK)
					.define('b', wool)
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
					.unlockedBy(getHasName(wool), has(wool))
					.save(exporter);
		});

		UFObjects.SHELF_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aaa")
					.pattern("b b")
					.define('a', plankSlab)
					.define('b', Items.STICK)
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.save(exporter);
		});

		UFObjects.TABLE_LAMP_BLOCKS.forEachEntry((block, reLo) -> {
			DyeSet dyeSet = UFObjects.getDyeSet(block);
			if (dyeSet == null) throw new AssertionError("DyeSet is null");
			var wool = dyeSet.base();
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern(" a ")
					.pattern("bcb")
					.pattern(" d ")
					.define('a', UFObjects.SCREW)
					.define('b', wool)
					.define('c', Items.TORCH)
					.define('d', ItemTags.WOODEN_SLABS)
					.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
					.unlockedBy(getHasName(wool), has(wool))
					.unlockedBy(getHasName(Items.TORCH), has(Items.TORCH))
					.unlockedBy("has_wooden_slabs", has(ItemTags.WOODEN_SLABS))
					.save(exporter);
		});

		UFObjects.CARVED_PLANK_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("a")
					.pattern("b")
					.pattern("a")
					.define('a', plankSlab)
					.define('b', UFObjects.SCREW)
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
					.save(exporter);
		});

		UFObjects.OPEN_RISER_STAIR_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var plankStairs = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.STAIRS);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern(" ab")
					.pattern("ab ")
					.define('a', plankStairs)
					.define('b', Items.STICK)
					.unlockedBy(getHasName(plankStairs), has(plankStairs))
					.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
					.save(exporter);
		});

		UFObjects.RAILING_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			var strippedWood = AxeItemAccessor.getStrippedBlocks().get(woodSet.wood());
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("aaa")
					.pattern(" b ")
					.define('a', strippedWood)
					.define('b', Items.STICK)
					.unlockedBy(getHasName(strippedWood), has(strippedWood))
					.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
					.save(exporter);
		});

		UFObjects.WOODEN_BEAM_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			// TODO: iron variants
			var plankSlab = BlockFamilies.getAllFamilies()
					.filter(f -> f.getBaseBlock() == woodSet.plank())
					.findFirst()
					.orElseThrow()
					.get(BlockFamily.Variant.SLAB);
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, block)
					.pattern("a")
					.pattern("a")
					.pattern("a")
					.define('a', plankSlab)
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
					.save(exporter);
		});
	}
}
//?}
