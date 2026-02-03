package net.toopa.unusual_furniture.fabric.datagen;

import static net.minecraft.data.models.BlockModelGenerators.createHorizontalFacingDispatch;

import java.util.Map;
import java.util.Optional;

import com.mojang.datafixers.util.Pair;
import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationBatBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationVillagerBlock;
import net.toopa.unusual_furniture.common.block.MushroomPatchBlock;
import net.toopa.unusual_furniture.common.block.PebbleBagBlock;
import net.toopa.unusual_furniture.common.block.PosterBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;
import net.toopa.unusual_furniture.common.block.SphereLampBlock;
import net.toopa.unusual_furniture.common.block.TrashBlock;
import net.toopa.unusual_furniture.common.block.TropicalPlantBlock;
import net.toopa.unusual_furniture.common.block.WallMushroomPatchBlock;
import net.toopa.unusual_furniture.common.block.WaterPlantsBlock;
import net.toopa.unusual_furniture.common.block.WaterPlantsLandBlock;
import net.toopa.unusual_furniture.common.block.properties.ModularBenchProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularCarvedPlanksProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularCurtainProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularSofaProperty;
import net.toopa.unusual_furniture.common.block.properties.RailingDirectionProperty;
import net.toopa.unusual_furniture.common.block.properties.WaterPlantProperty;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

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
			if (woodSet == null) return; // iron beams
			Block planks = woodSet.base();
			Block coffee_table = woodSet.coffee_table();
			registerBeam(blockModelGenerators, block,
					new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(coffee_table))
							.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(planks)));
		});
		Block decoratedIronBeam = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("decorated_iron_beam"));
		Block ironBeam = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("iron_beam"));
		registerDecoratedIronBeam(blockModelGenerators, decoratedIronBeam,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(decoratedIronBeam))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerIronBeam(blockModelGenerators, ironBeam,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(decoratedIronBeam))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		Block floorLampDecorationBat = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("floor_lamp_decoration_bat"));
		Block floorLampDecorationVillager = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("floor_lamp_decoration_villager"));
		registerFloorLampDecorationBat(blockModelGenerators, floorLampDecorationBat,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(floorLampDecorationBat))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerFloorLampDecorationVillager(blockModelGenerators, floorLampDecorationVillager,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(floorLampDecorationVillager))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		Block ironLamp = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("iron_lamp"));
		Block sphereLamp = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("sphere_lamp"));
		registerIronLamp(blockModelGenerators, ironLamp,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/street_lamps"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/street_lamps_off"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerSphereLamp(blockModelGenerators, sphereLamp,
				new TextureMapping().put(SLOT_1, UnusualFurniture.id("block/street_lamps"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.YELLOW_STAINED_GLASS)),
				new TextureMapping().put(SLOT_1, UnusualFurniture.id("block/street_lamps_off"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.YELLOW_STAINED_GLASS)));
		Block greekPot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("greek_pot"));
		Block hugePot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("huge_pot"));
		Block stonePot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("stone_pot"));
		Block tallTerracottaPot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tall_terracotta_pot"));
		Block bauhausPot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("bauhaus_pot"));
		Block blackstonePot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("blackstone_pot"));
		Block fudgePot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("fudge_pot"));
		Block hangingPot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("hanging_pot"));
		Block largeHangingPot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("large_hanging_pot"));
		Block woodenHangingPot = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("wooden_hanging_pot"));
		registerPot(blockModelGenerators, greekPot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(greekPot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(greekPot)),
				GREEK_POT);
		registerPot(blockModelGenerators, hugePot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(hugePot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hugePot)),
				HUGE_POT);
		registerPot(blockModelGenerators, stonePot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(stonePot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(stonePot)),
				STONE_POT);
		registerPot(blockModelGenerators, tallTerracottaPot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(hangingPot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hangingPot)),
				TALL_TERRACOTTA_POT);
		registerPot(blockModelGenerators, bauhausPot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(bauhausPot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(bauhausPot)),
				BAUHAUS_POT);
		registerPot(blockModelGenerators, blackstonePot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(blackstonePot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(blackstonePot)),
				BLACKSTONE_POT);
		registerPot(blockModelGenerators, fudgePot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(fudgePot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(fudgePot)),
				FUDGE_POT);
		registerPot(blockModelGenerators, hangingPot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(hangingPot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hangingPot)),
				HANGING_POT);
		registerPot(blockModelGenerators, largeHangingPot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(hangingPot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(hangingPot)),
				LARGE_HANGING_POT);
		registerPot(blockModelGenerators, woodenHangingPot,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(woodenHangingPot))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(woodenHangingPot)),
				WOODEN_HANGING_POT);
		Block tropicalPlant = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant"));
		Block tropicalPlantWall = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall"));
		registerTropicalPlant(blockModelGenerators, tropicalPlant,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/jungle_plant"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/jungle_plant")));
		registerWallTropicalPlant(blockModelGenerators, tropicalPlantWall,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/jungle_plant"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/jungle_plant")));
		Block mushroomPatch = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("mushroom_patch"));
		Block mushroomPatchWall = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("mushroom_patch_wall"));
		registerMushroomPatch(blockModelGenerators, mushroomPatch,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/red_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/red_mushroom")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/brown_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/brown_mushroom")));
		registerWallMushroomPatch(blockModelGenerators, mushroomPatchWall,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/red_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/red_mushroom")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/brown_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/brown_mushroom")));
		Block waterPlant = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants"));
		Block waterPlantWater = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water"));
		registerWaterPlants(blockModelGenerators, waterPlant,
				new TextureMapping().put(TextureSlot.CROSS, UnusualFurniture.id("block/small_cattail"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/small_cattail")),
				new TextureMapping().put(TextureSlot.CROSS, UnusualFurniture.id("block/large_cattail1"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/large_cattail1")),
				new TextureMapping().put(TextureSlot.CROSS, UnusualFurniture.id("block/large_cattail2"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/large_cattail2")));
		registerWaterPlantsWater(blockModelGenerators, waterPlantWater,
				new TextureMapping().put(TextureSlot.TEXTURE, UnusualFurniture.id("block/small_lily"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/small_lily")),
				new TextureMapping().put(TextureSlot.TEXTURE, UnusualFurniture.id("block/scum1"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/scum1")),
				new TextureMapping().put(TextureSlot.TEXTURE, UnusualFurniture.id("block/scum2"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/scum2")));
		Block pebbleBag = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("pebble_bag"));
		registerPebbleBag(blockModelGenerators, pebbleBag,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/pebbles"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.COBBLESTONE)));
		Block poster = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("poster"));
		registerPoster(blockModelGenerators, poster,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster1"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster1")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster2"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster2")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster3"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster3")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster4"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster4")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster5"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster5")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster6"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster6")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/poster7"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/poster7")));
		Block trash = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("trash"));
		registerTrash(blockModelGenerators, trash,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/trash_can"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/carved_spruce_alternate")));
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerators) {
		itemModelGenerators.generateFlatItem(UFObjects.DISCORD_ITEM, ModelTemplates.FLAT_ITEM);
		itemModelGenerators.generateFlatItem(UFObjects.SCREW_ITEM, ModelTemplates.FLAT_ITEM);
		ModelTemplates.FLAT_ITEM.create(
				ModelLocationUtils.getModelLocation(
						BuiltInRegistries.ITEM.get(UnusualFurniture.id("tropical_plant"))),
				TextureMapping.layer0(
						UnusualFurniture.id("item/tropical_plant_bag")),
				itemModelGenerators.output);
		itemModelGenerators.generateFlatItem(BuiltInRegistries.ITEM.get(UnusualFurniture.id("mushroom_patch")), ModelTemplates.FLAT_ITEM);
		ModelTemplates.FLAT_ITEM.create(
				ModelLocationUtils.getModelLocation(
						BuiltInRegistries.ITEM.get(UnusualFurniture.id("water_plants"))),
				TextureMapping.layer0(
						UnusualFurniture.id("item/water_plant_bag")),
				itemModelGenerators.output);
		itemModelGenerators.generateFlatItem(BuiltInRegistries.ITEM.get(UnusualFurniture.id("pebble_bag")), ModelTemplates.FLAT_ITEM);
		ModelTemplates.FLAT_ITEM.create(
				ModelLocationUtils.getModelLocation(
						BuiltInRegistries.ITEM.get(UnusualFurniture.id("poster"))),
				TextureMapping.layer0(
						UnusualFurniture.id("block/poster2")),
				itemModelGenerators.output);
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

	private static final ModelTemplate IRON_BEAM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/iron_beam")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate DECORATED_IRON_BEAM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/decorated_iron_beam")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FLOOR_LAMP_DECORATION_BAT1 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/floor_lamp_decoration_bat1")),
			Optional.of("1"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FLOOR_LAMP_DECORATION_BAT2 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/floor_lamp_decoration_bat2")),
			Optional.of("2"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FLOOR_LAMP_DECORATION_BAT4 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/floor_lamp_decoration_bat4")),
			Optional.of("4"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FLOOR_LAMP_DECORATION_VILLAGER1 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/floor_lamp_decoration_villager1")),
			Optional.of("1"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FLOOR_LAMP_DECORATION_VILLAGER2 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/floor_lamp_decoration_villager2")),
			Optional.of("2"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FLOOR_LAMP_DECORATION_VILLAGER4 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/floor_lamp_decoration_villager4")),
			Optional.of("4"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate METAL_LAMP = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/metal_lamp")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate METAL_LAMP_WALL = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/metal_lamp_wall")),
			Optional.of("_wall"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate METAL_LAMP_FLOOR = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/metal_lamp_floor")),
			Optional.of("_floor"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate SPHERE_LAMP = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/sphere_lamp")),
			Optional.empty(),
			SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate TROPICAL_PLANT_1 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/tropical_plant_1")),
			Optional.of("_1"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TROPICAL_PLANT_2 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/tropical_plant_2")),
			Optional.of("_2"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TROPICAL_PLANT_3 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/tropical_plant_3")),
			Optional.of("_3"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TROPICAL_PLANT_WALL = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/tropical_plant_wall")),
			Optional.of("_wall"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate MUSHROOM_PATCH = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/mushroom_patch")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate MUSHROOM_PATCH_WALL1 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/mushroom_wall1")),
			Optional.of("1"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate MUSHROOM_PATCH_WALL2 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/mushroom_wall2")),
			Optional.of("2"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate LILY_PAD = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/lily_pad")),
			Optional.empty(),
			TextureSlot.TEXTURE, TextureSlot.PARTICLE);

	private static final ModelTemplate SCUM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/scum")),
			Optional.empty(),
			TextureSlot.TEXTURE, TextureSlot.PARTICLE);

	private static final ModelTemplate PEBBLE1 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/pebble1")),
			Optional.of("1"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate PEBBLE2 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/pebble2")),
			Optional.of("2"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate PEBBLE3 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/pebble3")),
			Optional.of("3"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate PEBBLE4 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/pebble4")),
			Optional.of("4"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate PEBBLE5 = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/pebble5")),
			Optional.of("5"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate GREEK_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/greek_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate HUGE_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/huge_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate STONE_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/stone_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TALL_TERRACOTTA_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/tall_terracotta_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate BAUHAUS_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/bauhaus_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate BLACKSTONE_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/blackstone_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate FUDGE_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/fudge_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate HANGING_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/hanging_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate LARGE_HANGING_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/large_hanging_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate WOODEN_HANGING_POT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/barrel_hanging_pot")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate POSTER = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/poster")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TRASH_ITEM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/trash_item")),
			Optional.of("_item"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TRASH_BOTTOM = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/trash_bottom")),
			Optional.of("_bottom"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TRASH_TOP = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/trash_top")),
			Optional.of("_top"),
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

		handleBeam(blockModelGenerators, block, identifier);
	}

	private void registerIronBeam(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = IRON_BEAM.create(block, tm, blockModelGenerators.modelOutput);

		handleBeam(blockModelGenerators, block, identifier);
	}

	private void registerDecoratedIronBeam(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = DECORATED_IRON_BEAM.create(block, tm, blockModelGenerators.modelOutput);

		handleBeam(blockModelGenerators, block, identifier);
	}

	private void registerFloorLampDecorationBat(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation one = FLOOR_LAMP_DECORATION_BAT1.createWithSuffix(block, "1", tm, blockModelGenerators.modelOutput);
		ResourceLocation two = FLOOR_LAMP_DECORATION_BAT2.createWithSuffix(block, "2", tm, blockModelGenerators.modelOutput);
		ResourceLocation four = FLOOR_LAMP_DECORATION_BAT4.createWithSuffix(block, "4", tm, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, one,
				1, two,
				2, four
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(FloorLampDecorationBatBlock.NUMBER_OF_ARMS, FloorLampDecorationBatBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(shape, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(shape, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, two);
	}

	private void registerFloorLampDecorationVillager(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation one = FLOOR_LAMP_DECORATION_VILLAGER1.createWithSuffix(block, "1", tm, blockModelGenerators.modelOutput);
		ResourceLocation two = FLOOR_LAMP_DECORATION_VILLAGER2.createWithSuffix(block, "2", tm, blockModelGenerators.modelOutput);
		ResourceLocation four = FLOOR_LAMP_DECORATION_VILLAGER4.createWithSuffix(block, "4", tm, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, one,
				1, two,
				2, four
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(FloorLampDecorationVillagerBlock.NUMBER_OF_ARMS, FloorLampDecorationVillagerBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			var shape = entry.getKey();
			var model = entry.getValue();
			map.select(shape, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(shape, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(shape, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(shape, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, two);
	}

	private void registerSphereLamp(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmOn, TextureMapping tmOff) {
		ResourceLocation modelOn = SPHERE_LAMP.createWithSuffix(block, "_on", tmOn, blockModelGenerators.modelOutput);
		ResourceLocation modelOff = SPHERE_LAMP.createWithSuffix(block, "_off", tmOff, blockModelGenerators.modelOutput);
		Map<Boolean, ResourceLocation> modelMap = Map.of(
				true, modelOn,
				false, modelOff
		);
		PropertyDispatch.C2<Boolean, Direction> map = PropertyDispatch.properties(SphereLampBlock.LIT, SphereLampBlock.FACING);
		for (boolean bool : new boolean[]{true, false}) {
			map.select(bool, Direction.DOWN, Variant.variant().with(VariantProperties.MODEL, modelMap.get(bool)).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))
					.select(bool, Direction.UP, Variant.variant().with(VariantProperties.MODEL, modelMap.get(bool)).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
					.select(bool, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, modelMap.get(bool)).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(bool, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, modelMap.get(bool)))
					.select(bool, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, modelMap.get(bool)).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(bool, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, modelMap.get(bool)).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
		}
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, modelOff);
	}

	private void registerIronLamp(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmOn, TextureMapping tmOff) {
		ResourceLocation modelOn = METAL_LAMP.createWithSuffix(block, "_on", tmOn, blockModelGenerators.modelOutput);
		ResourceLocation modelOff = METAL_LAMP.createWithSuffix(block, "_off", tmOff, blockModelGenerators.modelOutput);
		ResourceLocation modelWallOn = METAL_LAMP_WALL.createWithSuffix(block, "_wall_on", tmOn, blockModelGenerators.modelOutput);
		ResourceLocation modelWallOff = METAL_LAMP_WALL.createWithSuffix(block, "_wall_off", tmOff, blockModelGenerators.modelOutput);
		ResourceLocation modelFloorOn = METAL_LAMP_FLOOR.createWithSuffix(block, "_floor_on", tmOn, blockModelGenerators.modelOutput);
		ResourceLocation modelFloorOff = METAL_LAMP_FLOOR.createWithSuffix(block, "_floor_off", tmOff, blockModelGenerators.modelOutput);
		Map<Pair<Boolean, Direction>, ResourceLocation> modelMap = Map.ofEntries(
				Map.entry(Pair.of(true, Direction.UP), modelOn),
				Map.entry(Pair.of(false, Direction.UP), modelOff),
				Map.entry(Pair.of(true, Direction.DOWN), modelFloorOn),
				Map.entry(Pair.of(false, Direction.DOWN), modelFloorOff),
				Map.entry(Pair.of(true, Direction.NORTH), modelWallOn),
				Map.entry(Pair.of(false, Direction.NORTH), modelWallOff),
				Map.entry(Pair.of(true, Direction.SOUTH), modelWallOn),
				Map.entry(Pair.of(false, Direction.SOUTH), modelWallOff),
				Map.entry(Pair.of(true, Direction.EAST), modelWallOn),
				Map.entry(Pair.of(false, Direction.EAST), modelWallOff),
				Map.entry(Pair.of(true, Direction.WEST), modelWallOn),
				Map.entry(Pair.of(false, Direction.WEST), modelWallOff)
		);
		PropertyDispatch.C2<Boolean, Direction> map = PropertyDispatch.properties(SphereLampBlock.LIT, SphereLampBlock.FACING);
		for (boolean bool : new boolean[]{true, false}) {
			map.select(bool, Direction.DOWN, Variant.variant().with(VariantProperties.MODEL, modelMap.get(Pair.of(bool, Direction.DOWN))))
					.select(bool, Direction.UP, Variant.variant().with(VariantProperties.MODEL, modelMap.get(Pair.of(bool, Direction.UP))))
					.select(bool, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, modelMap.get(Pair.of(bool, Direction.NORTH))).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(bool, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, modelMap.get(Pair.of(bool, Direction.SOUTH))))
					.select(bool, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, modelMap.get(Pair.of(bool, Direction.WEST))).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(bool, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, modelMap.get(Pair.of(bool, Direction.EAST))).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));
		}
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, modelOff);
	}

	private void registerTropicalPlant(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation model1 = TROPICAL_PLANT_1.createWithSuffix(block, "_1", tm, blockModelGenerators.modelOutput);
		ResourceLocation model2 = TROPICAL_PLANT_2.createWithSuffix(block, "_2", tm, blockModelGenerators.modelOutput);
		ResourceLocation model3 = TROPICAL_PLANT_3.createWithSuffix(block, "_3", tm, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, model1,
				1, model2,
				2, model3
		);

		PropertyDispatch.C1<Integer> map = PropertyDispatch.property(TropicalPlantBlock.PLANT_TYPE);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Variant.variant().with(VariantProperties.MODEL, model));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerWallTropicalPlant(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = TROPICAL_PLANT_WALL.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
	}

	private void registerMushroomPatch(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmRed, TextureMapping tmBrown) {
		ResourceLocation modelRed = MUSHROOM_PATCH.createWithSuffix(block, "_red", tmRed, blockModelGenerators.modelOutput);
		ResourceLocation modelBrown = MUSHROOM_PATCH.createWithSuffix(block, "_brown", tmBrown, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, modelRed,
				1, modelBrown
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(MushroomPatchBlock.PLANT_TYPE, MushroomPatchBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(blockstate, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(blockstate, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerWallMushroomPatch(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmRed, TextureMapping tmBrown) {
		ResourceLocation model1Red = MUSHROOM_PATCH_WALL1.createWithSuffix(block, "_1_red", tmRed, blockModelGenerators.modelOutput);
		ResourceLocation model1Brown = MUSHROOM_PATCH_WALL1.createWithSuffix(block, "_1_brown", tmBrown, blockModelGenerators.modelOutput);
		ResourceLocation model2Red = MUSHROOM_PATCH_WALL2.createWithSuffix(block, "_2_red", tmRed, blockModelGenerators.modelOutput);
		ResourceLocation model2Brown = MUSHROOM_PATCH_WALL2.createWithSuffix(block, "_2_brown", tmBrown, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, model1Red,
				1, model1Brown,
				2, model2Red,
				3, model2Brown
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(WallMushroomPatchBlock.PLANT_TYPE, WallMushroomPatchBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(blockstate, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(blockstate, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerWaterPlants(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmSingle, TextureMapping tmBottom, TextureMapping tmTop) {
		ResourceLocation model1 = ModelTemplates.CROSS.create(block, tmSingle, blockModelGenerators.modelOutput);
		ResourceLocation model2 = ModelTemplates.CROSS.createWithSuffix(block, "_big", tmTop, blockModelGenerators.modelOutput);
		ResourceLocation model3 = ModelTemplates.CROSS.createWithSuffix(block, "_big_bottom", tmBottom, blockModelGenerators.modelOutput);
		Map<WaterPlantProperty, ResourceLocation> modelMap = Map.of(
				WaterPlantProperty.SINGLE, model1,
				WaterPlantProperty.TOP, model2,
				WaterPlantProperty.BOTTOM, model3
		);

		PropertyDispatch.C1<WaterPlantProperty> map = PropertyDispatch.property(WaterPlantsLandBlock.PLANT_TYPE);
		for (var entry : modelMap.entrySet()) {
			WaterPlantProperty blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Variant.variant().with(VariantProperties.MODEL, model));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerWaterPlantsWater(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmLily, TextureMapping tmScum1, TextureMapping tmScum2) {
		ResourceLocation model1 = LILY_PAD.createWithSuffix(block, "_lilypad", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model2 = SCUM.createWithSuffix(block, "_scum_1", tmScum1, blockModelGenerators.modelOutput);
		ResourceLocation model3 = SCUM.createWithSuffix(block, "_scum_2", tmScum2, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, model1,
				1, model2,
				2, model3
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(WaterPlantsBlock.PLANT_TYPE, WaterPlantsBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(blockstate, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(blockstate, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerPebbleBag(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmLily) {
		ResourceLocation model1 = PEBBLE1.createWithSuffix(block, "_0", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model2 = PEBBLE2.createWithSuffix(block, "_1", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model3 = PEBBLE3.createWithSuffix(block, "_2", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model4 = PEBBLE4.createWithSuffix(block, "_3", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model5 = PEBBLE5.createWithSuffix(block, "_4", tmLily, blockModelGenerators.modelOutput);
		//TODO: the numbers don't correspond correctly
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, model1,
				1, model2,
				2, model3,
				3, model4,
				4, model5
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(PebbleBagBlock.PEBBLE_TYPE, PebbleBagBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(blockstate, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(blockstate, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerPot(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm, ModelTemplate template) {
		ResourceLocation model = template.create(block, tm, blockModelGenerators.modelOutput);

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, model)));
		blockModelGenerators.delegateItemModel(block, model);
	}

	private void registerPoster(BlockModelGenerators blockModelGenerators, Block block,
								TextureMapping tm1, TextureMapping tm2, TextureMapping tm3,
								TextureMapping tm4, TextureMapping tm5, TextureMapping tm6,
								TextureMapping tm7) {
		ResourceLocation model1 = POSTER.createWithSuffix(block, "_1", tm1, blockModelGenerators.modelOutput);
		ResourceLocation model2 = POSTER.createWithSuffix(block, "_2", tm2, blockModelGenerators.modelOutput);
		ResourceLocation model3 = POSTER.createWithSuffix(block, "_3", tm3, blockModelGenerators.modelOutput);
		ResourceLocation model4 = POSTER.createWithSuffix(block, "_4", tm4, blockModelGenerators.modelOutput);
		ResourceLocation model5 = POSTER.createWithSuffix(block, "_5", tm5, blockModelGenerators.modelOutput);
		ResourceLocation model6 = POSTER.createWithSuffix(block, "_6", tm6, blockModelGenerators.modelOutput);
		ResourceLocation model7 = POSTER.createWithSuffix(block, "_7", tm7, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, model1,
				1, model2,
				2, model3,
				3, model4,
				4, model5,
				5, model6,
				6, model7
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(PosterBlock.POSTER_TYPE, PosterBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(blockstate, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(blockstate, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
					.select(blockstate, Direction.UP, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.DOWN, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.X_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerTrash(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation modelItem = TRASH_ITEM.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation modelTop = TRASH_TOP.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation modelBottom = TRASH_BOTTOM.create(block, tm, blockModelGenerators.modelOutput);
		Map<Integer, ResourceLocation> modelMap = Map.of(
				0, modelBottom,
				1, modelTop
		);

		PropertyDispatch.C2<Integer, Direction> map = PropertyDispatch.properties(TrashBlock.SHAPE, TrashBlock.FACING);
		for (var entry : modelMap.entrySet()) {
			int blockstate = entry.getKey();
			ResourceLocation model = entry.getValue();
			map.select(blockstate, Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, model))
					.select(blockstate, Direction.SOUTH, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
					.select(blockstate, Direction.WEST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
					.select(blockstate, Direction.EAST, Variant.variant().with(VariantProperties.MODEL, model).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
		}

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
		blockModelGenerators.delegateItemModel(block, modelItem);
	}

	private void handleBeam(BlockModelGenerators blockModelGenerators, Block block, ResourceLocation identifier) {
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
