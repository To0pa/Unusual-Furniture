package net.toopa.unusual_furniture.fabric.datagen;

import static net.minecraft.data.models.BlockModelGenerators.*;

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
import net.toopa.unusual_furniture.common.block.ManholeBlock;
import net.toopa.unusual_furniture.common.block.MushroomPatchBlock;
import net.toopa.unusual_furniture.common.block.PebbleBagBlock;
import net.toopa.unusual_furniture.common.block.PosterBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;
import net.toopa.unusual_furniture.common.block.SphereLampBlock;
import net.toopa.unusual_furniture.common.block.ToolboxBlock;
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
		registerDecoratedIronBeam(blockModelGenerators, UFObjects.DECORATED_IRON_BEAM,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.DECORATED_IRON_BEAM))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerIronBeam(blockModelGenerators, UFObjects.IRON_BEAM,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.DECORATED_IRON_BEAM))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerFloorLampDecorationBat(blockModelGenerators, UFObjects.FLOOR_LAMP_DECORATION_BAT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.FLOOR_LAMP_DECORATION_BAT))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerFloorLampDecorationVillager(blockModelGenerators, UFObjects.FLOOR_LAMP_DECORATION_VILLAGER,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.FLOOR_LAMP_DECORATION_VILLAGER))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerIronLamp(blockModelGenerators, UFObjects.IRON_LAMP,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/street_lamps"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/street_lamps_off"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/metal_particule")));
		registerSphereLamp(blockModelGenerators, UFObjects.SPHERE_LAMP,
				new TextureMapping().put(SLOT_1, UnusualFurniture.id("block/street_lamps"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.YELLOW_STAINED_GLASS)),
				new TextureMapping().put(SLOT_1, UnusualFurniture.id("block/street_lamps_off"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.YELLOW_STAINED_GLASS)));
		registerPot(blockModelGenerators, UFObjects.GREEK_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.GREEK_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.GREEK_POT)),
				GREEK_POT);
		registerPot(blockModelGenerators, UFObjects.HUGE_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.HUGE_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.HUGE_POT)),
				HUGE_POT);
		registerPot(blockModelGenerators, UFObjects.STONE_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.STONE_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.STONE_POT)),
				STONE_POT);
		registerPot(blockModelGenerators, UFObjects.TALL_TERRACOTTA_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.HANGING_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.HANGING_POT)),
				TALL_TERRACOTTA_POT);
		registerPot(blockModelGenerators, UFObjects.BAUHAUS_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.BAUHAUS_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.BAUHAUS_POT)),
				BAUHAUS_POT);
		registerPot(blockModelGenerators, UFObjects.BLACKSTONE_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.BLACKSTONE_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.BLACKSTONE_POT)),
				BLACKSTONE_POT);
		registerPot(blockModelGenerators, UFObjects.FUDGE_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.FUDGE_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.FUDGE_POT)),
				FUDGE_POT);
		registerPot(blockModelGenerators, UFObjects.HANGING_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.HANGING_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.HANGING_POT)),
				HANGING_POT);
		registerPot(blockModelGenerators, UFObjects.LARGE_HANGING_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.HANGING_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.HANGING_POT)),
				LARGE_HANGING_POT);
		registerPot(blockModelGenerators, UFObjects.WOODEN_HANGING_POT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.WOODEN_HANGING_POT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.WOODEN_HANGING_POT)),
				WOODEN_HANGING_POT);
		registerTropicalPlant(blockModelGenerators, UFObjects.TROPICAL_PLANT,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/jungle_plant"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/jungle_plant")));
		Block tropicalPlantWall = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall"));
		registerWallTropicalPlant(blockModelGenerators, tropicalPlantWall,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/jungle_plant"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/jungle_plant")));
		registerMushroomPatch(blockModelGenerators, UFObjects.MUSHROOM_PATCH,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/red_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/red_mushroom")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/brown_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/brown_mushroom")));
		Block mushroomPatchWall = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("mushroom_patch_wall"));
		registerWallMushroomPatch(blockModelGenerators, mushroomPatchWall,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/red_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/red_mushroom")),
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/brown_mushroom"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/brown_mushroom")));
		registerWaterPlants(blockModelGenerators, UFObjects.WATER_PLANTS,
				new TextureMapping().put(TextureSlot.CROSS, UnusualFurniture.id("block/small_cattail"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/small_cattail")),
				new TextureMapping().put(TextureSlot.CROSS, UnusualFurniture.id("block/large_cattail1"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/large_cattail1")),
				new TextureMapping().put(TextureSlot.CROSS, UnusualFurniture.id("block/large_cattail2"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/large_cattail2")));
		Block waterPlantWater = BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water"));
		registerWaterPlantsWater(blockModelGenerators, waterPlantWater,
				new TextureMapping().put(TextureSlot.TEXTURE, UnusualFurniture.id("block/small_lily"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/small_lily")),
				new TextureMapping().put(TextureSlot.TEXTURE, UnusualFurniture.id("block/scum1"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/scum1")),
				new TextureMapping().put(TextureSlot.TEXTURE, UnusualFurniture.id("block/scum2"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/scum2")));
		registerPebbleBag(blockModelGenerators, UFObjects.PEBBLE_BAG,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/pebbles"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.COBBLESTONE)));
		registerPoster(blockModelGenerators, UFObjects.POSTER,
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
		registerTrash(blockModelGenerators, UFObjects.TRASH,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/trash_can"))
						.put(TextureSlot.PARTICLE, UnusualFurniture.id("block/carved_spruce_alternate")));
		registerFireHydrant(blockModelGenerators, UFObjects.FIRE_HYDRANT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.FIRE_HYDRANT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.FIRE_HYDRANT)));
		registerFireHydrant(blockModelGenerators, UFObjects.EMERGENCY_FIRE_HYDRANT,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.EMERGENCY_FIRE_HYDRANT))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.EMERGENCY_FIRE_HYDRANT)));
		registerManhole(blockModelGenerators, UFObjects.MANHOLE,
				new TextureMapping().put(SLOT_0, TextureMapping.getBlockTexture(UFObjects.MANHOLE))
						.put(SLOT_1, UnusualFurniture.id("block/manhole_open"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(UFObjects.MANHOLE)));
		registerToolbox(blockModelGenerators, UFObjects.DECORATIVE_TOOLBOX,
				new TextureMapping().put(SLOT_0, UnusualFurniture.id("block/toolbox"))
						.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.REDSTONE_BLOCK)));
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

	private static final ModelTemplate FIRE_HYDRANT = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/fire_hydrant")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate MANHOLE = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/manhole")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate MANHOLE_OPEN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/manhole_open")),
			Optional.of("_open"),
			SLOT_0, SLOT_1, TextureSlot.PARTICLE);

	private static final ModelTemplate TOOLBOX = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/toolbox")),
			Optional.empty(),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate TOOLBOX_OPEN = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/toolbox_open")),
			Optional.of("_open"),
			SLOT_0, TextureSlot.PARTICLE);

	private static final ModelTemplate BARRIER = new ModelTemplate(
			Optional.of(UnusualFurniture.id("custom/barrier")),
			Optional.empty(),
			SLOT_1, TextureSlot.PARTICLE);

	private void registerIndustrialTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = INDUSTRIAL_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block,
								Variant.variant().with(VariantProperties.MODEL, identifier))
						.with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = WOODEN_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block,
								Variant.variant().with(VariantProperties.MODEL, identifier))
						.with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerCoffeeTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = COFFEE_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block,
								Variant.variant().with(VariantProperties.MODEL, identifier))
						.with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerSmallTable(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = SMALL_TABLE.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block,
								Variant.variant().with(VariantProperties.MODEL, identifier))
						.with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerChair(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = CHAIR.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block,
								Variant.variant().with(VariantProperties.MODEL, identifier))
						.with(createHorizontalFacingDispatch()));
		blockModelGenerators.delegateItemModel(block, identifier);
	}

	private void registerStool(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = STOOL.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block,
								Variant.variant().with(VariantProperties.MODEL, identifier))
						.with(createHorizontalFacingDispatch()));
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

		PropertyDispatch map = PropertyDispatch.property(SofaBlock.SHAPE)
				.select(ModularSofaProperty.SINGLE, Variant.variant().with(VariantProperties.MODEL, singleModel))
				.select(ModularSofaProperty.LEFT, Variant.variant().with(VariantProperties.MODEL, leftModel))
				.select(ModularSofaProperty.MIDDLE, Variant.variant().with(VariantProperties.MODEL, middleModel))
				.select(ModularSofaProperty.RIGHT, Variant.variant().with(VariantProperties.MODEL, rightModel))
				.select(ModularSofaProperty.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerLeftModel))
				.select(ModularSofaProperty.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerRightModel))
				.select(ModularSofaProperty.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerLeftModel))
				.select(ModularSofaProperty.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerRightModel));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
		blockModelGenerators.delegateItemModel(block, singleModel);
	}

	private void registerCeilingLamp(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm, TextureMapping tmOn) {
		ResourceLocation ceilingLampId = CEILING_LAMP.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation ceilingLampOnId = CEILING_LAMP_ON.create(block, tmOn, blockModelGenerators.modelOutput);

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createBooleanModelDispatch(CeilingLampBlock.LIT, ceilingLampOnId, ceilingLampId)));
		blockModelGenerators.delegateItemModel(block, ceilingLampId);
	}

	private void registerDrawer(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm, TextureMapping tmOpen) {
		ResourceLocation drawerId = ModelTemplates.CUBE_ALL.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation drawerOpenId = DRAWER_OPEN.create(block, tmOpen, blockModelGenerators.modelOutput);

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(createBooleanModelDispatch(DrawerBlock.OPEN, drawerOpenId, drawerId)));
	}

	private void registerBench(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm0, TextureMapping tm1) {
		ResourceLocation middleModel = BENCH_MIDDLE.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation leftModel = BENCH_LEFT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation rightModel = BENCH_RIGHT.create(block, tm0, blockModelGenerators.modelOutput);
		ResourceLocation singleModel = BENCH.create(block, tm1, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(BenchBlock.SHAPE)
				.select(ModularBenchProperty.SINGLE, Variant.variant().with(VariantProperties.MODEL, singleModel))
				.select(ModularBenchProperty.LEFT, Variant.variant().with(VariantProperties.MODEL, leftModel))
				.select(ModularBenchProperty.MIDDLE, Variant.variant().with(VariantProperties.MODEL, middleModel))
				.select(ModularBenchProperty.RIGHT, Variant.variant().with(VariantProperties.MODEL, rightModel));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(map)
						.with(createHorizontalFacingDispatch()));
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

		PropertyDispatch map = PropertyDispatch.property(CurtainBlock.SHAPE)
				.select(ModularCurtainProperty.SINGLE_CLOSED, Variant.variant().with(VariantProperties.MODEL, singleClosedCurtain))
				.select(ModularCurtainProperty.TOP_CLOSED, Variant.variant().with(VariantProperties.MODEL, topClosedModel))
				.select(ModularCurtainProperty.MIDDLE_CLOSED, Variant.variant().with(VariantProperties.MODEL, middleClosedModel))
				.select(ModularCurtainProperty.BOTTOM_CLOSED, Variant.variant().with(VariantProperties.MODEL, bottomClosedModel))
				.select(ModularCurtainProperty.MIDDLE_OPEN, Variant.variant().with(VariantProperties.MODEL, middleOpenModel))
				.select(ModularCurtainProperty.TOP_OPEN, Variant.variant().with(VariantProperties.MODEL, topOpenModel))
				.select(ModularCurtainProperty.LEFT_MIDDLE_OPEN, Variant.variant().with(VariantProperties.MODEL, leftMiddleOpenModel))
				.select(ModularCurtainProperty.RIGHT_MIDDLE_OPEN, Variant.variant().with(VariantProperties.MODEL, rightMiddleOpenModel))
				.select(ModularCurtainProperty.LEFT_BOTTOM_OPEN, Variant.variant().with(VariantProperties.MODEL, leftBottomOpenModel))
				.select(ModularCurtainProperty.RIGHT_BOTTOM_OPEN, Variant.variant().with(VariantProperties.MODEL, rightBottomOpenModel))
				.select(ModularCurtainProperty.LEFT_TOP_OPEN, Variant.variant().with(VariantProperties.MODEL, leftTopOpenModel))
				.select(ModularCurtainProperty.RIGHT_TOP_OPEN, Variant.variant().with(VariantProperties.MODEL, rightTopOpenModel));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(map)
						.with(createHorizontalFacingDispatch()));
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

		PropertyDispatch connectMap = PropertyDispatch.property(CarvedPlanksBlock.SHAPE)
				.select(ModularCarvedPlanksProperty.SINGLE, Variant.variant().with(VariantProperties.MODEL, single))
				.select(ModularCarvedPlanksProperty.MIDDLE, Variant.variant().with(VariantProperties.MODEL, middle))
				.select(ModularCarvedPlanksProperty.TOP, Variant.variant().with(VariantProperties.MODEL, top))
				.select(ModularCarvedPlanksProperty.BOTTOM, Variant.variant().with(VariantProperties.MODEL, bottom));

		PropertyDispatch rotationMap = PropertyDispatch.property(CarvedPlanksBlock.AXIS)
				.select(Direction.Axis.Y, Variant.variant())
				.select(Direction.Axis.X, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
				.select(Direction.Axis.Z, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(connectMap)
						.with(rotationMap));
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

		PropertyDispatch map = PropertyDispatch.property(FloorLampDecorationBatBlock.NUMBER_OF_ARMS)
				.select(0, Variant.variant().with(VariantProperties.MODEL, one))
				.select(1, Variant.variant().with(VariantProperties.MODEL, two))
				.select(2, Variant.variant().with(VariantProperties.MODEL, four));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
		blockModelGenerators.delegateItemModel(block, two);
	}

	private void registerFloorLampDecorationVillager(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation one = FLOOR_LAMP_DECORATION_VILLAGER1.createWithSuffix(block, "1", tm, blockModelGenerators.modelOutput);
		ResourceLocation two = FLOOR_LAMP_DECORATION_VILLAGER2.createWithSuffix(block, "2", tm, blockModelGenerators.modelOutput);
		ResourceLocation four = FLOOR_LAMP_DECORATION_VILLAGER4.createWithSuffix(block, "4", tm, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(FloorLampDecorationVillagerBlock.NUMBER_OF_ARMS)
				.select(0, Variant.variant().with(VariantProperties.MODEL, one))
				.select(1, Variant.variant().with(VariantProperties.MODEL, two))
				.select(2, Variant.variant().with(VariantProperties.MODEL, four));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
		blockModelGenerators.delegateItemModel(block, two);
	}

	private void registerSphereLamp(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmOn, TextureMapping tmOff) {
		ResourceLocation modelOn = SPHERE_LAMP.createWithSuffix(block, "_on", tmOn, blockModelGenerators.modelOutput);
		ResourceLocation modelOff = SPHERE_LAMP.createWithSuffix(block, "_off", tmOff, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(SphereLampBlock.FACING)
				.select(Direction.DOWN, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))
				.select(Direction.UP, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
				.select(Direction.NORTH, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
				.select(Direction.SOUTH, Variant.variant())
				.select(Direction.WEST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
				.select(Direction.EAST, Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createBooleanModelDispatch(SphereLampBlock.LIT, modelOn, modelOff))
						.with(map));
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
		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(map));
		blockModelGenerators.delegateItemModel(block, modelOff);
	}

	private void registerTropicalPlant(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation model1 = TROPICAL_PLANT_1.createWithSuffix(block, "_1", tm, blockModelGenerators.modelOutput);
		ResourceLocation model2 = TROPICAL_PLANT_2.createWithSuffix(block, "_2", tm, blockModelGenerators.modelOutput);
		ResourceLocation model3 = TROPICAL_PLANT_3.createWithSuffix(block, "_3", tm, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(TropicalPlantBlock.PLANT_TYPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, model1))
				.select(1, Variant.variant().with(VariantProperties.MODEL, model2))
				.select(2, Variant.variant().with(VariantProperties.MODEL, model3));

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(map));
	}

	private void registerWallTropicalPlant(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation identifier = TROPICAL_PLANT_WALL.create(block, tm, blockModelGenerators.modelOutput);
		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, identifier)).with(createHorizontalFacingDispatch()));
	}

	private void registerMushroomPatch(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmRed, TextureMapping tmBrown) {
		ResourceLocation modelRed = MUSHROOM_PATCH.createWithSuffix(block, "_red", tmRed, blockModelGenerators.modelOutput);
		ResourceLocation modelBrown = MUSHROOM_PATCH.createWithSuffix(block, "_brown", tmBrown, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(MushroomPatchBlock.PLANT_TYPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, modelRed))
				.select(1, Variant.variant().with(VariantProperties.MODEL, modelBrown));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
	}

	private void registerWallMushroomPatch(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmRed, TextureMapping tmBrown) {
		ResourceLocation model1Red = MUSHROOM_PATCH_WALL1.createWithSuffix(block, "_1_red", tmRed, blockModelGenerators.modelOutput);
		ResourceLocation model1Brown = MUSHROOM_PATCH_WALL1.createWithSuffix(block, "_1_brown", tmBrown, blockModelGenerators.modelOutput);
		ResourceLocation model2Red = MUSHROOM_PATCH_WALL2.createWithSuffix(block, "_2_red", tmRed, blockModelGenerators.modelOutput);
		ResourceLocation model2Brown = MUSHROOM_PATCH_WALL2.createWithSuffix(block, "_2_brown", tmBrown, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(WallMushroomPatchBlock.PLANT_TYPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, model1Red))
				.select(1, Variant.variant().with(VariantProperties.MODEL, model1Brown))
				.select(2, Variant.variant().with(VariantProperties.MODEL, model2Red))
				.select(3, Variant.variant().with(VariantProperties.MODEL, model2Brown));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
	}

	private void registerWaterPlants(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmSingle, TextureMapping tmBottom, TextureMapping tmTop) {
		ResourceLocation modelSingle = ModelTemplates.CROSS.create(block, tmSingle, blockModelGenerators.modelOutput);
		ResourceLocation modelTop = ModelTemplates.CROSS.createWithSuffix(block, "_big", tmTop, blockModelGenerators.modelOutput);
		ResourceLocation modelBottom = ModelTemplates.CROSS.createWithSuffix(block, "_big_bottom", tmBottom, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(WaterPlantsLandBlock.PLANT_TYPE)
				.select(WaterPlantProperty.SINGLE, Variant.variant().with(VariantProperties.MODEL, modelSingle))
				.select(WaterPlantProperty.TOP, Variant.variant().with(VariantProperties.MODEL, modelTop))
				.select(WaterPlantProperty.BOTTOM, Variant.variant().with(VariantProperties.MODEL, modelBottom));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(map));
	}

	private void registerWaterPlantsWater(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmLily, TextureMapping tmScum1, TextureMapping tmScum2) {
		ResourceLocation model1 = LILY_PAD.createWithSuffix(block, "_lilypad", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model2 = SCUM.createWithSuffix(block, "_scum_1", tmScum1, blockModelGenerators.modelOutput);
		ResourceLocation model3 = SCUM.createWithSuffix(block, "_scum_2", tmScum2, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(WaterPlantsBlock.PLANT_TYPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, model1))
				.select(1, Variant.variant().with(VariantProperties.MODEL, model2))
				.select(2, Variant.variant().with(VariantProperties.MODEL, model3));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
	}

	private void registerPebbleBag(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tmLily) {
		ResourceLocation model1 = PEBBLE1.createWithSuffix(block, "_0", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model2 = PEBBLE2.createWithSuffix(block, "_1", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model3 = PEBBLE3.createWithSuffix(block, "_2", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model4 = PEBBLE4.createWithSuffix(block, "_3", tmLily, blockModelGenerators.modelOutput);
		ResourceLocation model5 = PEBBLE5.createWithSuffix(block, "_4", tmLily, blockModelGenerators.modelOutput);

		//TODO: the numbers don't correspond correctly to the original mod
		PropertyDispatch map = PropertyDispatch.property(PebbleBagBlock.PEBBLE_TYPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, model1))
				.select(1, Variant.variant().with(VariantProperties.MODEL, model2))
				.select(2, Variant.variant().with(VariantProperties.MODEL, model3))
				.select(3, Variant.variant().with(VariantProperties.MODEL, model4))
				.select(4, Variant.variant().with(VariantProperties.MODEL, model5));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
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

		PropertyDispatch map = PropertyDispatch.property(PosterBlock.POSTER_TYPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, model1))
				.select(1, Variant.variant().with(VariantProperties.MODEL, model2))
				.select(2, Variant.variant().with(VariantProperties.MODEL, model3))
				.select(3, Variant.variant().with(VariantProperties.MODEL, model4))
				.select(4, Variant.variant().with(VariantProperties.MODEL, model5))
				.select(5, Variant.variant().with(VariantProperties.MODEL, model6))
				.select(6, Variant.variant().with(VariantProperties.MODEL, model7));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createFacingDispatch())
						.with(map));
	}

	private void registerTrash(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation modelItem = TRASH_ITEM.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation modelTop = TRASH_TOP.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation modelBottom = TRASH_BOTTOM.create(block, tm, blockModelGenerators.modelOutput);

		PropertyDispatch map = PropertyDispatch.property(TrashBlock.SHAPE)
				.select(0, Variant.variant().with(VariantProperties.MODEL, modelBottom))
				.select(1, Variant.variant().with(VariantProperties.MODEL, modelTop));

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(map));
		blockModelGenerators.delegateItemModel(block, modelItem);
	}

	private void registerFireHydrant(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation model = FIRE_HYDRANT.create(block, tm, blockModelGenerators.modelOutput);

		blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, model)));
		blockModelGenerators.delegateItemModel(block, model);
	}

	private void registerManhole(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation modelClosed = MANHOLE.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation modelOpen = MANHOLE_OPEN.create(block, tm, blockModelGenerators.modelOutput);

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(createBooleanModelDispatch(ManholeBlock.OPEN, modelOpen, modelClosed)));
		blockModelGenerators.delegateItemModel(block, modelClosed);
	}

	private void registerToolbox(BlockModelGenerators blockModelGenerators, Block block, TextureMapping tm) {
		ResourceLocation modelClosed = TOOLBOX.create(block, tm, blockModelGenerators.modelOutput);
		ResourceLocation modelOpen = TOOLBOX_OPEN.create(block, tm, blockModelGenerators.modelOutput);

		blockModelGenerators.blockStateOutput
				.accept(MultiVariantGenerator.multiVariant(block)
						.with(createHorizontalFacingDispatch())
						.with(createBooleanModelDispatch(ToolboxBlock.OPEN, modelOpen, modelClosed)));
		blockModelGenerators.delegateItemModel(block, modelClosed);
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
