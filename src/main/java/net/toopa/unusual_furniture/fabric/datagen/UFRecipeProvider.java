package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.mixin.content.registry.AxeItemAccessor;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import net.minecraft.world.item.crafting.Ingredient;

import net.toopa.unusual_furniture.common.reg.UFItemTags;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.util.DyeSet;
import net.toopa.unusual_furniture.common.util.WoodSet;

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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.INDUSTRIAL_TABLE)
				.pattern("aaa")
				.pattern(" a ")
				.pattern(" b ")
				.define('a', Items.IRON_NUGGET)
				.define('b', Items.IRON_INGOT)
				.unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.INDUSTRIAL_COFFEE_TABLE)
				.pattern("aaa")
				.pattern(" b ")
				.define('a', Items.IRON_NUGGET)
				.define('b', Items.IRON_INGOT)
				.unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.COPPER_CEILING_LAMP)
				.pattern(" a ")
				.pattern("aba")
				.define('a', Items.COPPER_INGOT)
				.define('b', Items.TORCH)
				.unlockedBy(getHasName(Items.TORCH), has(Items.TORCH))
				.unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
				.save(exporter);

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
					.unlockedBy(getHasName(plankSlab), has(plankSlab))
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

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UFObjects.TROPICAL_PLANT, 3)
				.requires(UFItemTags.TROPICAL_PLANTS)
				.requires(Items.BONE_MEAL)
				.unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
				.save(exporter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UFObjects.MUSHROOM_PATCH, 3)
				.requires(ConventionalItemTags.MUSHROOMS)
				.requires(Items.BONE_MEAL)
				.unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
				.save(exporter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UFObjects.WATER_PLANTS, 3)
				.requires(UFItemTags.WATER_PLANTS)
				.requires(Items.BONE_MEAL)
				.unlockedBy(getHasName(Items.BONE_MEAL), has(Items.BONE_MEAL))
				.save(exporter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UFObjects.PEBBLE_BAG, 2)
				.requires(Items.COBBLESTONE)
				.unlockedBy(getHasName(Items.COBBLESTONE), has(Items.COBBLESTONE))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.GREEK_POT)
				.pattern(" a ")
				.pattern("aba")
				.pattern(" a ")
				.define('a', Items.QUARTZ)
				.define('b', Items.LAPIS_LAZULI)
				.unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
				.unlockedBy(getHasName(Items.LAPIS_LAZULI), has(Items.LAPIS_LAZULI))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.HUGE_POT)
				.pattern(" a ")
				.pattern("a a")
				.pattern(" a ")
				.define('a', Items.BRICKS)
				.unlockedBy(getHasName(Items.BRICKS), has(Items.BRICKS))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.STONE_POT)
				.pattern(" a ")
				.pattern("a a")
				.pattern(" a ")
				.define('a', Items.STONE)
				.unlockedBy(getHasName(Items.STONE), has(Items.STONE))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.TALL_TERRACOTTA_POT)
				.pattern("a")
				.pattern("a")
				.pattern("a")
				.define('a', Items.BRICK)
				.unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.BAUHAUS_POT)
				.pattern(" a ")
				.pattern("bcb")
				.pattern(" b ")
				.define('a', Items.LIGHT_BLUE_DYE)
				.define('b', Items.QUARTZ)
				.define('c', Items.YELLOW_DYE)
				.unlockedBy(getHasName(Items.LIGHT_BLUE_DYE), has(Items.LIGHT_BLUE_DYE))
				.unlockedBy(getHasName(Items.QUARTZ), has(Items.QUARTZ))
				.unlockedBy(getHasName(Items.YELLOW_DYE), has(Items.YELLOW_DYE))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.BLACKSTONE_POT)
				.pattern(" a ")
				.pattern("b b")
				.pattern(" a ")
				.define('a', Items.BLACKSTONE)
				.define('b', Items.GOLD_NUGGET)
				.unlockedBy(getHasName(Items.BLACKSTONE), has(Items.BLACKSTONE))
				.unlockedBy(getHasName(Items.GOLD_NUGGET), has(Items.GOLD_NUGGET))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.FUDGE_POT)
				.pattern(" a ")
				.pattern("a a")
				.pattern(" a ")
				.define('a', Items.TERRACOTTA)
				.unlockedBy(getHasName(Items.TERRACOTTA), has(Items.TERRACOTTA))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.HANGING_POT)
				.pattern(" a ")
				.pattern("b b")
				.pattern(" b ")
				.define('a', Items.STRING)
				.define('b', Items.BRICK)
				.unlockedBy(getHasName(Items.STRING), has(Items.STRING))
				.unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.LARGE_HANGING_POT)
				.pattern(" a ")
				.pattern("bbb")
				.pattern(" b ")
				.define('a', Items.STRING)
				.define('b', Items.BRICK)
				.unlockedBy(getHasName(Items.STRING), has(Items.STRING))
				.unlockedBy(getHasName(Items.BRICK), has(Items.BRICK))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.WOODEN_HANGING_POT)
				.pattern(" a ")
				.pattern("b b")
				.pattern(" b ")
				.define('a', Items.STRING)
				.define('b', ItemTags.PLANKS)
				.unlockedBy(getHasName(Items.STRING), has(Items.STRING))
				.save(exporter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UFObjects.POSTER, 2)
				.requires(Items.INK_SAC)
				.requires(Items.PAPER)
				.unlockedBy(getHasName(Items.INK_SAC), has(Items.INK_SAC))
				.unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.TRASH)
				.pattern("aba")
				.pattern("aca")
				.pattern("aca")
				.define('a', Items.IRON_NUGGET)
				.define('b', UFObjects.SCREW)
				.define('c', ItemTags.WOODEN_SLABS)
				.unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.FIRE_HYDRANT)
				.pattern(" a ")
				.pattern("bcb")
				.pattern(" a ")
				.define('a', Items.IRON_INGOT)
				.define('b', UFObjects.SCREW)
				.define('c', Items.WATER_BUCKET)
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
				.save(exporter);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, UFObjects.EMERGENCY_FIRE_HYDRANT)
				.requires(UFObjects.FIRE_HYDRANT)
				.requires(Items.RED_DYE)
				.unlockedBy(getHasName(UFObjects.FIRE_HYDRANT), has(UFObjects.FIRE_HYDRANT))
				.unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.MANHOLE)
				.pattern("aba")
				.define('a', UFObjects.SCREW)
				.define('b', Items.IRON_TRAPDOOR)
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_TRAPDOOR), has(Items.IRON_TRAPDOOR))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.DECORATIVE_TOOLBOX)
				.pattern(" a ")
				.pattern("bcb")
				.pattern(" a ")
				.define('a', UFObjects.SCREW)
				.define('b', Items.IRON_TRAPDOOR)
				.define('c', Items.CHEST)
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_TRAPDOOR), has(Items.IRON_TRAPDOOR))
				.unlockedBy(getHasName(Items.CHEST), has(Items.CHEST))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.WARNING_BARRIER, 4)
				.pattern("aba")
				.pattern("cdc")
				.pattern("d d")
				.define('a', Items.WHITE_DYE)
				.define('b', Items.RED_DYE)
				.define('c', UFObjects.SCREW)
				.define('d', Items.IRON_INGOT)
				.unlockedBy(getHasName(Items.WHITE_DYE), has(Items.WHITE_DYE))
				.unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.ROAD_WORKS_BARRIER, 4)
				.pattern("aba")
				.pattern("cdc")
				.pattern("d d")
				.define('a', Items.BLACK_DYE)
				.define('b', Items.YELLOW_DYE)
				.define('c', UFObjects.SCREW)
				.define('d', Items.IRON_INGOT)
				.unlockedBy(getHasName(Items.BLACK_DYE), has(Items.BLACK_DYE))
				.unlockedBy(getHasName(Items.YELLOW_DYE), has(Items.YELLOW_DYE))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.DANGER_BARRIER, 4)
				.pattern("aba")
				.pattern("cdc")
				.pattern("d d")
				.define('a', Items.WHITE_DYE)
				.define('b', Items.YELLOW_DYE)
				.define('c', UFObjects.SCREW)
				.define('d', Items.IRON_INGOT)
				.unlockedBy(getHasName(Items.WHITE_DYE), has(Items.WHITE_DYE))
				.unlockedBy(getHasName(Items.YELLOW_DYE), has(Items.YELLOW_DYE))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.WOODEN_BARRIER, 4)
				.pattern("aba")
				.pattern("c c")
				.define('a', UFObjects.SCREW)
				.define('b', ItemTags.WOODEN_SLABS)
				.define('c', Items.IRON_INGOT)
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.BLACKBOARD_MENU)
				.pattern("aba")
				.pattern("cbc")
				.pattern("c c")
				.define('a', UFObjects.SCREW)
				.define('b', Items.COBBLED_DEEPSLATE_SLAB)
				.define('c', Items.STICK)
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.COBBLED_DEEPSLATE_SLAB), has(Items.COBBLED_DEEPSLATE_SLAB))
				.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.WOODEN_CLOCK)
				.pattern(" a ")
				.pattern("bcb")
				.pattern(" a ")
				.define('a', ItemTags.SLABS)
				.define('b', UFObjects.SCREW)
				.define('c', Items.CLOCK)
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.CLOCK), has(Items.CLOCK))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.PIG_PLUSH)
				.pattern("aaa")
				.pattern("aba")
				.pattern("aaa")
				.define('a', Items.PINK_WOOL)
				.define('b', UFObjects.SCREW)
				.unlockedBy(getHasName(Items.PINK_WOOL), has(Items.PINK_WOOL))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.COW_PLUSH)
				.pattern("aaa")
				.pattern("aba")
				.pattern("aaa")
				.define('a', Items.BROWN_WOOL)
				.define('b', UFObjects.SCREW)
				.unlockedBy(getHasName(Items.BROWN_WOOL), has(Items.BROWN_WOOL))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.CAT_PLUSH)
				.pattern("aaa")
				.pattern("aba")
				.pattern("aaa")
				.define('a', Items.LIGHT_GRAY_WOOL)
				.define('b', UFObjects.SCREW)
				.unlockedBy(getHasName(Items.LIGHT_GRAY_WOOL), has(Items.LIGHT_GRAY_WOOL))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.save(exporter);

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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.BROOM)
				.pattern("a")
				.pattern("b")
				.pattern("c")
				.define('a', Items.STICK)
				.define('b', UFObjects.SCREW)
				.define('c', Items.WHEAT)
				.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.RAKE)
				.pattern(" a ")
				.pattern(" b ")
				.pattern("ccc")
				.define('a', Items.STICK)
				.define('b', UFObjects.SCREW)
				.define('c', Items.IRON_NUGGET)
				.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.GRAVE_BROKEN)
				.pattern("a")
				.pattern("b")
				.pattern("c")
				.define('a', Items.BRICK_SLAB)
				.define('b', UFObjects.SCREW)
				.define('c', Items.CHISELED_STONE_BRICKS)
				.unlockedBy(getHasName(Items.BRICK_SLAB), has(Items.BRICK_SLAB))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.CHISELED_STONE_BRICKS), has(Items.CHISELED_STONE_BRICKS))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.GRAVE_SKELETON)
				.pattern("a")
				.pattern("b")
				.pattern("c")
				.define('a', Items.BONE)
				.define('b', UFObjects.SCREW)
				.define('c', Items.CHISELED_STONE_BRICKS)
				.unlockedBy(getHasName(Items.BONE), has(Items.BONE))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.CHISELED_STONE_BRICKS), has(Items.CHISELED_STONE_BRICKS))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.GRAVE_CREEPER)
				.pattern("a")
				.pattern("b")
				.pattern("c")
				.define('a', Items.GUNPOWDER)
				.define('b', UFObjects.SCREW)
				.define('c', Items.CHISELED_STONE_BRICKS)
				.unlockedBy(getHasName(Items.GUNPOWDER), has(Items.GUNPOWDER))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.CHISELED_STONE_BRICKS), has(Items.CHISELED_STONE_BRICKS))
				.save(exporter);

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
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
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
					.unlockedBy(getHasName(Items.STICK), has(Items.STICK))
					.save(exporter);
		});

		UFObjects.WOODEN_BEAM_BLOCKS.forEachEntry((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
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

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.DECORATED_IRON_BEAM)
				.pattern(" a ")
				.pattern("bab")
				.pattern(" a ")
				.define('a', Items.IRON_INGOT)
				.define('b', UFObjects.SCREW)
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.IRON_BEAM)
				.pattern("a")
				.pattern("b")
				.pattern("a")
				.define('a', UFObjects.SCREW)
				.define('b', Items.IRON_INGOT)
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.save(exporter);

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(UFObjects.DECORATED_IRON_BEAM), RecipeCategory.DECORATIONS, UFObjects.FLOOR_LAMP_DECORATION_BAT)
				.unlockedBy(getHasName(UFObjects.DECORATED_IRON_BEAM), has(UFObjects.DECORATED_IRON_BEAM))
				.save(exporter);
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(UFObjects.DECORATED_IRON_BEAM), RecipeCategory.DECORATIONS, UFObjects.FLOOR_LAMP_DECORATION_VILLAGER)
				.unlockedBy(getHasName(UFObjects.DECORATED_IRON_BEAM), has(UFObjects.DECORATED_IRON_BEAM))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.IRON_LAMP, 2)
				.pattern("a")
				.pattern("b")
				.pattern("c")
				.define('a', Items.IRON_INGOT)
				.define('b', Items.REDSTONE_LAMP)
				.define('c', UFObjects.SCREW)
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.unlockedBy(getHasName(Items.REDSTONE_LAMP), has(Items.REDSTONE_LAMP))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.save(exporter);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, UFObjects.SPHERE_LAMP, 2)
				.pattern(" a ")
				.pattern("bcb")
				.pattern(" d ")
				.define('a', Items.IRON_INGOT)
				.define('b', UFObjects.SCREW)
				.define('c', Items.REDSTONE_LAMP)
				.define('d', Items.GLASS)
				.unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
				.unlockedBy(getHasName(UFObjects.SCREW), has(UFObjects.SCREW))
				.unlockedBy(getHasName(Items.REDSTONE_LAMP), has(Items.REDSTONE_LAMP))
				.unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
				.save(exporter);
	}
}
//?}
