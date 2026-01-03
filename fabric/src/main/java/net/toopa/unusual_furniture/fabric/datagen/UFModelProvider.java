package net.toopa.unusual_furniture.fabric.datagen;

import static net.minecraft.data.models.BlockModelGenerators.createHorizontalFacingDispatch;

import java.util.Map;
import java.util.Optional;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;
import net.toopa.unusual_furniture.common.block.properties.ModularBenchProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularCarvedPlanksProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularCurtainProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularSofaProperty;
import net.toopa.unusual_furniture.common.block.properties.RailingDirectionProperty;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.utils.DyeSet;
import net.toopa.unusual_furniture.common.utils.WoodSet;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

public class UFModelProvider extends FabricModelProvider {
	private static final TextureSlot SLOT_0 = TextureSlot.create("0");
	private static final TextureSlot SLOT_1 = TextureSlot.create("1");

	public UFModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
		UFObjects.INDUSTRIAL_TABLE_BLOCKS.forEach((block, reLo) -> {
			registerIndustrialTable(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block)));
		});
		UFObjects.TABLE_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			registerTable(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.INDUSTRIAL_COFFEE_TABLE_BLOCKS.forEach((block, reLo) -> {
			Block industrialTable = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_table"));
			registerCoffeeTable(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(industrialTable))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(industrialTable)));
		});
		UFObjects.COFFEE_TABLE_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			registerSmallTable(blockModelGenerators, block,
					new TextureMapping().put(SLOT_1, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.CHAIR_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			Block stool = woodSet.stool();
			registerChair(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(stool))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.STOOL_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			registerStool(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.SOFA_BLOCKS.forEach((block, reLo) -> {
			DyeSet dyeSet = UFObjects.getDyeSet(block);
			if (dyeSet == null) throw new AssertionError("DyeSet is null");
			Block wool = dyeSet.base();
			registerSofa(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(wool)),
					new TextureMapping().put(SLOT_1, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(wool)));
		});
		UFObjects.CEILING_LAMP_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			Block planks;
			if (woodSet != null) {
				planks = woodSet.base();
			} else {
				planks = Blocks.COPPER_BLOCK; // Copper lamp, if there's more maybe need a better system
			}
			registerCeilingLamp(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)),
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(SLOT_1, UnusualFurniture.id("block/light"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.DRAWER_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			registerDrawer(blockModelGenerators, block,
					new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block, "_java"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)),
					new TextureMapping().put(SLOT_1, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			registerBench(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)),
					new TextureMapping().put(SLOT_1, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> {
			DyeSet dyeSet = UFObjects.getDyeSet(block);
			if (dyeSet == null) throw new AssertionError("DyeSet is null");
			Block wool = dyeSet.base();
			registerCurtain(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(wool))
							.put(TextureSlot.ALL, UnusualFurniture.id("block/curtains")));
		});
		UFObjects.SHELF_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			Block coffe_table = woodSet.coffee_table();
			registerShelf(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(coffe_table))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.CARVED_PLANK_BLOCKS.forEach((block, reLo) -> {
			registerCarvedPlanks(blockModelGenerators, block,
					new TextureMapping()
							.put(TextureSlot.UP, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_single"))
							.put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_single"))
							.put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_single"))
							.put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_single"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block)),
					new TextureMapping()
							.put(TextureSlot.UP, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_middle"))
							.put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_middle"))
							.put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_middle"))
							.put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_middle"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block)),
					new TextureMapping()
							.put(TextureSlot.UP, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_top"))
							.put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_top"))
							.put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_top"))
							.put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_top"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block)),
					new TextureMapping()
							.put(TextureSlot.UP, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_bottom"))
							.put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_bottom"))
							.put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_bottom"))
							.put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_bottom"))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block))
			);
		});
		UFObjects.OPEN_RISER_STAIR_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			Block coffe_table = woodSet.coffee_table();
			registerOpenRiserStair(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(coffe_table))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.RAILING_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			registerRailing(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(block))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		UFObjects.BEAM_BLOCKS.forEach((block, reLo) -> {
			WoodSet woodSet = UFObjects.getWoodSet(block);
			if (woodSet == null) throw new AssertionError("WoodSet is null");
			Block planks = woodSet.base();
			Block coffee_table = woodSet.coffee_table();
			registerBeam(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(coffee_table))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerators) {
		itemModelGenerators.generateFlatItem(UFObjects.DISCORD_ITEM, ModelTemplates.FLAT_ITEM);
		UFObjects.DRAWER_BLOCKS.forEach((block, reLo) -> {
			DRAWER_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()),
					new TextureMapping()
							.put(SLOT_0, TextureMapping.getBlockTexture(block)), itemModelGenerators.output);
		});
	}

	private static final ModelTemplate INDUSTRIAL_TABLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/industrial_table")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate WOODEN_TABLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/wodden_table")), // TODO: the original model has a typo
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate COFFEE_TABLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/coffee_table")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SMALL_TABLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/small_table")),
			Optional.empty(),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate CHAIR = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/chair")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate STOOL = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/stool")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_SINGLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_INNER_RIGHT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_corner")),
			Optional.of("_corner"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_INNER_LEFT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_corner2")),
			Optional.of("_corner2"),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_LEFT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_right")),
			Optional.of("_right"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_MIDDLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_middle")),
			Optional.of("_middle"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_OUTER_LEFT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_outer_corner")),
			Optional.of("_outer_corner"),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_OUTER_RIGHT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_outer_corner2")),
			Optional.of("_outer_corner2"),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate SOFA_RIGHT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sofa_left")),
			Optional.of("_left"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate CEILING_LAMP = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/ceiling_lamp")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate CEILING_LAMP_ON = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/ceiling_lamp_on")),
			Optional.of("_on"),
			SLOT_0, SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate DRAWER_OPEN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/drawer_open")),
			Optional.of("_open"),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate DRAWER_ITEM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/drawer_item")),
			Optional.of("_item"),
			SLOT_0);

	private static final ModelTemplate BENCH = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bench")),
			Optional.empty(),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate BENCH_LEFT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bench_left")),
			Optional.of("_left"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate BENCH_RIGHT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bench_right")),
			Optional.of("_right"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate BENCH_MIDDLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bench_middle")),
			Optional.of("_middle"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SINGLE_CLOSED_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/top_close_lonely")),
			Optional.of("_top_close_lonely"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate TOP_CLOSED_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/top_close")),
			Optional.of("_top_close"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate MIDDLE_CLOSED_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/middle_close")),
			Optional.of("_middle_close"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate BOTTOM_CLOSED_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bottom_close")),
			Optional.of("_bottom_close"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate MIDDLE_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/middle_open")),
			Optional.of("_middle_open"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate TOP_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/top_open")),
			Optional.of("_top_open"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate LEFT_MIDDLE_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/middle_open_left")),
			Optional.of("_middle_open_left"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate RIGHT_MIDDLE_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/middle_open_right")),
			Optional.of("_middle_open_right"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate LEFT_BOTTOM_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bottom_open_left")),
			Optional.of("_bottom_open_left"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate RIGHT_BOTTOM_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bottom_open_right")),
			Optional.of("_bottom_open_right"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate LEFT_TOP_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/top_open_left")),
			Optional.of("_top_open_left"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate RIGHT_TOP_OPEN_CURTAIN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/top_open_right")),
			Optional.of("_top_open_right"),
			SLOT_0, TextureSlot.PARTICLE, TextureSlot.ALL);

	private static final ModelTemplate SHELF = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/shelf")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate OPEN_RAISER_STAIRS = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/stairs")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate RAILING = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/railing")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate LEFT_DOWN_RIGHT_UP_RAILING = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/stair_railing")),
			Optional.of("_stair"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate LEFT_DOWN_RAILING = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/up_stair_railing")),
			Optional.of("_up_stair"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate RIGHT_UP_RAILING = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/down_stair_railing")),
			Optional.of("_down_stair"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate BEAM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/beam")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private void registerIndustrialTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = INDUSTRIAL_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = WOODEN_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerCoffeeTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = COFFEE_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerSmallTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = SMALL_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerChair(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = CHAIR.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerStool(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = STOOL.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerSofa(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm0, TextureMapping tm1) {
		ResourceLocation middleModel = SOFA_MIDDLE.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation leftModel = SOFA_LEFT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation rightModel = SOFA_RIGHT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation singleModel = SOFA_SINGLE.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation innerLeftModel = SOFA_INNER_LEFT.create(block, tm1, blockModelGenerators.modelOutput);
		ResourceLocation innerRightModel = SOFA_INNER_RIGHT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation outerLeftModel = SOFA_OUTER_LEFT.create(block, tm1, blockModelGenerators.modelOutput);
		ResourceLocation outerRightModel = SOFA_OUTER_RIGHT.create(block, tm1, blockModelGenerators.modelOutput);
		Map<ModularSofaProperty, ResourceLocation> modelMap = Map.of(
				ModularSofaProperty.SINGLE, singleModel,
				ModularSofaProperty.LEFT, leftModel,
				ModularSofaProperty.MIDDLE, middleModel,
				ModularSofaProperty.RIGHT, rightModel,
				ModularSofaProperty.INNER_LEFT, innerLeftModel,
				ModularSofaProperty.INNER_RIGHT, innerRightModel,
				ModularSofaProperty.OUTER_LEFT, outerLeftModel,
				ModularSofaProperty.OUTER_RIGHT, outerRightModel
		);

		PropertyDispatch.C2<ModularSofaProperty, Direction> map = PropertyDispatch.properties(SofaBlock.SHAPE, SofaBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(shape, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(shape, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
		}
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, singleModel);
	}

	private void registerCeilingLamp(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm, TextureMapping tmOn) {
		ResourceLocation ceilingLampId = CEILING_LAMP.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation ceilingLampOnId = CEILING_LAMP_ON.create(block, tmOn, blockModelGenerators.modelOutput);

		PropertyDispatch.C1<Boolean> propertyDispatch = PropertyDispatch.property(CeilingLampBlock.LIT);
		propertyDispatch.select(true, Variant.variant().with(VariantProperties.MODEL, ceilingLampOnId));
		propertyDispatch.select(false, Variant.variant().with(VariantProperties.MODEL, ceilingLampId));

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(propertyDispatch));
		blockModelGenerators.delegateItemModel(block, ceilingLampId);
	}

	private void registerDrawer(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm, TextureMapping tmOpen) {
		ResourceLocation drawerId = ModelTemplates.CUBE_ALL.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation drawerOpenId = DRAWER_OPEN.create(block, tmOpen, blockModelGenerators.modelOutput);

		PropertyDispatch.C1<Boolean> propertyDispatch = PropertyDispatch.property(DrawerBlock.OPEN);
		propertyDispatch.select(true, Variant.variant().with(VariantProperties.MODEL, drawerOpenId));
		propertyDispatch.select(false, Variant.variant().with(VariantProperties.MODEL, drawerId));

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(createHorizontalFacingDispatch()).with(propertyDispatch));
	}

	private void registerBench(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm0, TextureMapping tm1) {
		ResourceLocation middleModel = BENCH_MIDDLE.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation leftModel = BENCH_LEFT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation rightModel = BENCH_RIGHT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation singleModel = BENCH.create(block, tm1, blockModelGenerators.modelOutput);
		Map<ModularBenchProperty, ResourceLocation> modelMap = Map.of(
				ModularBenchProperty.SINGLE, singleModel,
				ModularBenchProperty.LEFT, leftModel,
				ModularBenchProperty.MIDDLE, middleModel,
				ModularBenchProperty.RIGHT, rightModel
		);

		PropertyDispatch.C2<ModularBenchProperty, Direction> map = PropertyDispatch.properties(BenchBlock.SHAPE, BenchBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(shape, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(shape, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
		}
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, singleModel);
	}

	private void registerCurtain(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation singleClosedCurtain = SINGLE_CLOSED_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation topClosedModel = TOP_CLOSED_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation middleClosedModel = MIDDLE_CLOSED_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation bottomClosedModel = BOTTOM_CLOSED_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation middleOpenModel = MIDDLE_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation topOpenModel = TOP_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation leftMiddleOpenModel = LEFT_MIDDLE_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation rightMiddleOpenModel = RIGHT_MIDDLE_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation leftBottomOpenModel = LEFT_BOTTOM_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation rightBottomOpenModel = RIGHT_BOTTOM_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation leftTopOpenModel = LEFT_TOP_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation rightTopOpenModel = RIGHT_TOP_OPEN_CURTAIN.create(block, tm, blockModelGenerators.modelOutput);
		Map<ModularCurtainProperty, ResourceLocation> modelMap = Map.ofEntries(
				Map.entry(ModularCurtainProperty.SINGLE_CLOSED, singleClosedCurtain),
				Map.entry(ModularCurtainProperty.TOP_CLOSED, topClosedModel),
				Map.entry(ModularCurtainProperty.MIDDLE_CLOSED, middleClosedModel),
				Map.entry(ModularCurtainProperty.BOTTOM_CLOSED, bottomClosedModel),
				Map.entry(ModularCurtainProperty.MIDDLE_OPEN, middleOpenModel),
				Map.entry(ModularCurtainProperty.TOP_OPEN, topOpenModel),
				Map.entry(ModularCurtainProperty.LEFT_MIDDLE_OPEN, leftMiddleOpenModel),
				Map.entry(ModularCurtainProperty.RIGHT_MIDDLE_OPEN, rightMiddleOpenModel),
				Map.entry(ModularCurtainProperty.LEFT_BOTTOM_OPEN, leftBottomOpenModel),
				Map.entry(ModularCurtainProperty.RIGHT_BOTTOM_OPEN, rightBottomOpenModel),
				Map.entry(ModularCurtainProperty.LEFT_TOP_OPEN, leftTopOpenModel),
				Map.entry(ModularCurtainProperty.RIGHT_TOP_OPEN, rightTopOpenModel)
		);

		PropertyDispatch.C2<ModularCurtainProperty, Direction> map = PropertyDispatch.properties(CurtainBlock.SHAPE, CurtainBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(shape, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(shape, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
		}
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, singleClosedCurtain);
	}

	private void registerShelf(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = SHELF.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerCarvedPlanks(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmSingle, TextureMapping tmMiddle, TextureMapping tmTop, TextureMapping tmBottom) {
		ResourceLocation single = ModelTemplates.CUBE.createWithSuffix(block, "_single", tmSingle, blockModelGenerators.modelOutput);
		ResourceLocation middle = ModelTemplates.CUBE.createWithSuffix(block, "_middle", tmMiddle, blockModelGenerators.modelOutput);
		ResourceLocation top = ModelTemplates.CUBE.createWithSuffix(block, "_top", tmTop, blockModelGenerators.modelOutput);
		ResourceLocation bottom = ModelTemplates.CUBE.createWithSuffix(block, "_bottom", tmBottom, blockModelGenerators.modelOutput);
		Map<ModularCarvedPlanksProperty, ResourceLocation> modelMap = Map.of(
				ModularCarvedPlanksProperty.SINGLE, single,
				ModularCarvedPlanksProperty.MIDDLE, middle,
				ModularCarvedPlanksProperty.TOP, top,
				ModularCarvedPlanksProperty.BOTTOM, bottom
		);

		PropertyDispatch.C2<ModularCarvedPlanksProperty, Direction.Axis> map = PropertyDispatch.properties(CarvedPlanksBlock.SHAPE, CarvedPlanksBlock.AXIS);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.Axis.Y, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.Axis.X, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(shape, Direction.Axis.Z, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, single);
	}

	private void registerOpenRiserStair(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = OPEN_RAISER_STAIRS.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerRailing(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation railing = RAILING.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation leftDownRightUpRailing = LEFT_DOWN_RIGHT_UP_RAILING.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation leftDownRailing = LEFT_DOWN_RAILING.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation rightUpRailing = RIGHT_UP_RAILING.create(block, tm, blockModelGenerators.modelOutput);

		PropertyDispatch.C3<RailingDirectionProperty, RailingDirectionProperty, Direction.Axis> dispatch =
				PropertyDispatch.properties(RailingBlock.LEFT, RailingBlock.RIGHT, RailingBlock.AXIS);

		for (RailingDirectionProperty left : RailingDirectionProperty.values()) {
			for (RailingDirectionProperty right : RailingDirectionProperty.values()) {
				for (Direction.Axis axis : new Direction.Axis[]{Direction.Axis.X, Direction.Axis.Z}) {
					ResourceLocation model;
					int yRot = 0;

					if (left == right && left != RailingDirectionProperty.NONE) {
						model = railing;
						yRot = 180;
					} else if (left == RailingDirectionProperty.NONE && right == RailingDirectionProperty.NONE) {
						model = railing;
						yRot = 180;
					} else if (left == RailingDirectionProperty.DOWN && right == RailingDirectionProperty.UP) {
						model = leftDownRightUpRailing;
						yRot = 180;
					} else if (left == RailingDirectionProperty.UP && right == RailingDirectionProperty.DOWN) {
						model = leftDownRightUpRailing;
					} else if (left == RailingDirectionProperty.DOWN && right == RailingDirectionProperty.NONE) {
						model = leftDownRailing;
						yRot = 180;
					} else if (left == RailingDirectionProperty.UP && right == RailingDirectionProperty.NONE) {
						model = rightUpRailing;
					} else if (left == RailingDirectionProperty.NONE && right == RailingDirectionProperty.DOWN) {
						model = leftDownRailing;
					} else if (left == RailingDirectionProperty.NONE && right == RailingDirectionProperty.UP) {
						model = rightUpRailing;
						yRot = 180;
					} else {
						model = railing;
						yRot = 180;
					}

					int finalRotation = yRot;
					if (axis == Direction.Axis.X) {
						finalRotation = (yRot + 90) % 360;
					}

					Variant variant = Variant.variant()
							.with(VariantProperties.MODEL, model)
							.with(VariantProperties.Y_ROT, getRotation(finalRotation));

					dispatch.select(left, right, axis, variant);
				}
			}
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
		blockModelGenerators.delegateItemModel(block, railing);
	}

	private void registerBeam(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = BEAM.create(block, tm, blockModelGenerators.modelOutput);

		var propDispatch = PropertyDispatch.property(BlockStateProperties.AXIS)
				.select(Direction.Axis.Y, Variant.variant().with(VariantProperties.MODEL, identifier))
				.select(Direction.Axis.Z, Variant.variant().with(VariantProperties.MODEL, identifier).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
				.select(
						Direction.Axis.X,
						Variant.variant()
								.with(VariantProperties.MODEL, identifier)
								.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
				);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(propDispatch));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private VariantProperties.Rotation getRotation(int degrees) {
		return switch (degrees % 360) {
			case 90 -> VariantProperties.Rotation.R90;
			case 180 -> VariantProperties.Rotation.R180;
			case 270 -> VariantProperties.Rotation.R270;
			default -> VariantProperties.Rotation.R0;
		};
	}
}
