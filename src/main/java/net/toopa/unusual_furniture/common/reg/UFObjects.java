package net.toopa.unusual_furniture.common.reg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.BarrierBlock;
import net.toopa.unusual_furniture.common.block.BauhausPotBlock;
import net.toopa.unusual_furniture.common.block.BeamBlock;
import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.BlackboardMenuBlock;
import net.toopa.unusual_furniture.common.block.BlackstonePotBlock;
import net.toopa.unusual_furniture.common.block.BroomBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CatPlushBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.ChairBlock;
import net.toopa.unusual_furniture.common.block.CoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.CopperCeilingLampBlock;
import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.DecoratedIronBeamBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.FireHydrantBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationBatBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationVillagerBlock;
import net.toopa.unusual_furniture.common.block.FloorLampSupportBlock;
import net.toopa.unusual_furniture.common.block.FudgePotBlock;
import net.toopa.unusual_furniture.common.block.GraveBlock;
import net.toopa.unusual_furniture.common.block.GreekPotBlock;
import net.toopa.unusual_furniture.common.block.HangingPotBlock;
import net.toopa.unusual_furniture.common.block.HugePotBlock;
import net.toopa.unusual_furniture.common.block.IndustrialCoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.IndustrialTableBlock;
import net.toopa.unusual_furniture.common.block.IronBeamBlock;
import net.toopa.unusual_furniture.common.block.IronLampBlock;
import net.toopa.unusual_furniture.common.block.LargeHangingPotBlock;
import net.toopa.unusual_furniture.common.block.ManholeBlock;
import net.toopa.unusual_furniture.common.block.MushroomPatchBlock;
import net.toopa.unusual_furniture.common.block.OpenRiserStairBlock;
import net.toopa.unusual_furniture.common.block.PebbleBagBlock;
import net.toopa.unusual_furniture.common.block.PlushBlock;
import net.toopa.unusual_furniture.common.block.PosterBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
import net.toopa.unusual_furniture.common.block.RakeBlock;
import net.toopa.unusual_furniture.common.block.ShelfBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;
import net.toopa.unusual_furniture.common.block.SphereLampBlock;
import net.toopa.unusual_furniture.common.block.StonePotBlock;
import net.toopa.unusual_furniture.common.block.StoolBlock;
import net.toopa.unusual_furniture.common.block.TableBlock;
import net.toopa.unusual_furniture.common.block.TableLampBlock;
import net.toopa.unusual_furniture.common.block.TallTerracottaPotBlock;
import net.toopa.unusual_furniture.common.block.ToolboxBlock;
import net.toopa.unusual_furniture.common.block.TrashBlock;
import net.toopa.unusual_furniture.common.block.TropicalPlantBlock;
import net.toopa.unusual_furniture.common.block.WallClockBlock;
import net.toopa.unusual_furniture.common.block.WallMushroomPatchBlock;
import net.toopa.unusual_furniture.common.block.WallTropicalPlantBlock;
import net.toopa.unusual_furniture.common.block.WaterPlantsBlock;
import net.toopa.unusual_furniture.common.block.WaterPlantsLandBlock;
import net.toopa.unusual_furniture.common.block.WoodenHangingPotBlock;
import net.toopa.unusual_furniture.common.item.BagBlockItem;
import net.toopa.unusual_furniture.common.item.HangingPotBlockItem;
import net.toopa.unusual_furniture.common.item.OpenRiserStairBlockItem;
import net.toopa.unusual_furniture.common.item.WaterBagBlockItem;
import net.toopa.unusual_furniture.common.util.DyeSet;
import net.toopa.unusual_furniture.common.util.RegistryGroup;
import net.toopa.unusual_furniture.common.util.WoodSet;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class UFObjects {

	private UFObjects() {}

	/* --------------------------------------------------------------------- */
	/* Creative-tab grouping                                                 */
	/* --------------------------------------------------------------------- */

	public static final RegistryGroup<Block> BLOCKS = new RegistryGroup<>();
	public static final RegistryGroup<Item> ITEM = new RegistryGroup<>();

	public static final RegistryGroup<Block> FURNITURE_BLOCKS = BLOCKS.child();
	public static final RegistryGroup<Block> BUILDING_BLOCKS = BLOCKS.child();
	public static final RegistryGroup<Block> PROPS_BLOCKS = BLOCKS.child();

	public static final RegistryGroup<Item> FURNITURE_ITEMS = ITEM.child();
	public static final RegistryGroup<Item> BUILDING_ITEMS = ITEM.child();
	public static final RegistryGroup<Item> PROPS_ITEMS = ITEM.child();

	/* --------------------------------------------------------------------- */
	/* Variant lookup                                                        */
	/* --------------------------------------------------------------------- */

	public static final Map<Block, WoodSet> BLOCK_TO_WOODSET = new LinkedHashMap<>();
	public static final Map<Block, DyeSet> BLOCK_TO_DYESET = new LinkedHashMap<>();

	public static final Map<String, WoodSet> WOOD_SETS = new LinkedHashMap<>();

	public static final List<Block> LOOT_TABLE_BLACKLIST = new ArrayList<>();

	/* --------------------------------------------------------------------- */
	/* Per-type registries                                                   */
	/* --------------------------------------------------------------------- */

	public static final RegistryGroup<Block> TABLE_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> TABLE_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> WOODEN_TABLE_BLOCKS = TABLE_BLOCKS.child();
	public static final RegistryGroup<Item> WOODEN_TABLE_ITEMS = TABLE_ITEMS.child();

	public static final RegistryGroup<Block> COFFEE_TABLE_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> COFFEE_TABLE_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> WOODEN_COFFEE_TABLE_BLOCKS = TABLE_BLOCKS.child();
	public static final RegistryGroup<Item> WOODEN_COFFEE_TABLE_ITEMS = TABLE_ITEMS.child();

	public static final RegistryGroup<Block> CHAIR_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> CHAIR_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> STOOL_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> STOOL_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> SOFA_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> SOFA_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> CEILING_LAMP_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> CEILING_LAMP_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> WOODEN_CEILING_LAMP_BLOCKS = CEILING_LAMP_BLOCKS.child();
	public static final RegistryGroup<Item> WOODEN_CEILING_LAMP_ITEMS = CEILING_LAMP_ITEMS.child();

	public static final RegistryGroup<Block> DRAWER_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> DRAWER_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> BENCH_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> BENCH_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> CURTAIN_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> CURTAIN_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> SHELF_BLOCKS = FURNITURE_BLOCKS.child();
	public static final RegistryGroup<Item> SHELF_ITEMS = FURNITURE_ITEMS.child();

	public static final RegistryGroup<Block> BAG_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> BAG_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> POT_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> POT_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> FIRE_HYDRANT_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> FIRE_HYDRANT_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> BARRIER_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> BARRIER_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> TABLE_LAMP_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> TABLE_LAMP_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> PLUSH_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> PLUSH_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> GRAVE_BLOCKS = PROPS_BLOCKS.child();
	public static final RegistryGroup<Item> GRAVE_ITEMS = PROPS_ITEMS.child();

	public static final RegistryGroup<Block> CARVED_PLANK_BLOCKS = BUILDING_BLOCKS.child();
	public static final RegistryGroup<Item> CARVED_PLANK_ITEMS = BUILDING_ITEMS.child();

	public static final RegistryGroup<Block> OPEN_RISER_STAIR_BLOCKS = BUILDING_BLOCKS.child();
	public static final RegistryGroup<Item> OPEN_RISER_STAIR_ITEMS = BUILDING_ITEMS.child();

	public static final RegistryGroup<Block> RAILING_BLOCKS = BUILDING_BLOCKS.child();
	public static final RegistryGroup<Item> RAILING_ITEMS = BUILDING_ITEMS.child();

	public static final RegistryGroup<Block> BEAM_BLOCKS = BUILDING_BLOCKS.child();
	public static final RegistryGroup<Item> BEAM_ITEMS = BUILDING_ITEMS.child();

	public static final RegistryGroup<Block> WOODEN_BEAM_BLOCKS = BEAM_BLOCKS.child();
	public static final RegistryGroup<Item> WOODEN_BEAM_ITEMS = BEAM_ITEMS.child();

	public static final RegistryGroup<Block> FLOOR_LAMP_BLOCKS = BUILDING_BLOCKS.child();
	public static final RegistryGroup<Item> FLOOR_LAMP_ITEMS = BUILDING_ITEMS.child();

	public static final RegistryGroup<Block> LAMP_BLOCKS = BUILDING_BLOCKS.child();
	public static final RegistryGroup<Item> LAMP_ITEMS = BUILDING_ITEMS.child();

	/* --------------------------------------------------------------------- */
	/* Variant definitions                                                   */
	/* --------------------------------------------------------------------- */

	public record WoodDef(String name, Block plank, Block log, Block wood) {
	}

	public record DyeDef(String name, Block wool) {
	}

	public static final List<WoodDef> WOODS = List.of(
			new WoodDef("oak", Blocks.OAK_PLANKS, Blocks.OAK_LOG, Blocks.OAK_WOOD),
			new WoodDef("spruce", Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD),
			new WoodDef("birch", Blocks.BIRCH_PLANKS, Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD),
			new WoodDef("jungle", Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_LOG, Blocks.JUNGLE_WOOD),
			new WoodDef("acacia", Blocks.ACACIA_PLANKS, Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD),
			new WoodDef("dark_oak", Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD),
			new WoodDef("mangrove", Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_LOG, Blocks.MANGROVE_WOOD),
			new WoodDef("cherry", Blocks.CHERRY_PLANKS, Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD),
			new WoodDef("bamboo", Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK),
			new WoodDef("crimson", Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE),
			new WoodDef("warped", Blocks.WARPED_PLANKS, Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE)
	);

	public static final List<DyeDef> DYES = List.of(
			new DyeDef("white", Blocks.WHITE_WOOL),
			new DyeDef("light_gray", Blocks.LIGHT_GRAY_WOOL),
			new DyeDef("gray", Blocks.GRAY_WOOL),
			new DyeDef("black", Blocks.BLACK_WOOL),
			new DyeDef("brown", Blocks.BROWN_WOOL),
			new DyeDef("red", Blocks.RED_WOOL),
			new DyeDef("orange", Blocks.ORANGE_WOOL),
			new DyeDef("yellow", Blocks.YELLOW_WOOL),
			new DyeDef("lime", Blocks.LIME_WOOL),
			new DyeDef("green", Blocks.GREEN_WOOL),
			new DyeDef("cyan", Blocks.CYAN_WOOL),
			new DyeDef("light_blue", Blocks.LIGHT_BLUE_WOOL),
			new DyeDef("blue", Blocks.BLUE_WOOL),
			new DyeDef("purple", Blocks.PURPLE_WOOL),
			new DyeDef("magenta", Blocks.MAGENTA_WOOL),
			new DyeDef("pink", Blocks.PINK_WOOL)
	);

	/* --------------------------------------------------------------------- */
	/* Items                                                                 */
	/* --------------------------------------------------------------------- */

	public static final Item SCREW =
			registerItem("screw", new Item(new Item.Properties()), PROPS_ITEMS);

	public static final Block FLOOR_LAMP_SUPPORT =
			registerBlock("floor_lamp_support", new FloorLampSupportBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK)), BLOCKS);

	/* --------------------------------------------------------------------- */
	/* Init                                                                  */
	/* --------------------------------------------------------------------- */

	public static void init() {

		/* ---------- Wood furniture ---------- */

		for (WoodDef wood : WOODS) {
			String w = wood.name();

			TableBlock table = registerTable(w + "_table", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			CoffeeTableBlock coffee = registerCoffeeTable(w + "_coffee_table", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			ChairBlock chair = registerChair(w + "_chair", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			StoolBlock stool = registerStool(w + "_stool", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			CeilingLampBlock lamp = registerCeilingLamp(w + "_ceiling_lamp", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.dynamicShape()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			DrawerBlock drawer = registerDrawer(w + "_drawer", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.dynamicShape()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			BenchBlock bench = registerBench(w + "_bench", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.dynamicShape()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			ShelfBlock shelf = registerShelf(w + "_shelf", wood.plank(),
					p -> p.sound(wood.plank().defaultBlockState().getSoundType())
							.strength(1.0F, 10.0F)
							.noOcclusion());
			CarvedPlanksBlock carved = registerCarvedPlanks("carved_" + w + "_planks", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.dynamicShape()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			OpenRiserStairBlock stairs = registerOpenRiserStair(w + "_open_riser_stairs", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			RailingBlock railing = registerRailing(w + "_railing", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.dynamicShape()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			BeamBlock beam = registerBeam(w + "_beam", wood.plank(),
					p -> p.strength(1.0F, 10.0F)
							.noOcclusion()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));

			WoodSet set = new WoodSet(
					wood.plank(), wood.log(), wood.wood(),
					table, coffee, chair, stool, lamp,
					drawer, bench, shelf, carved, stairs,
					railing, beam
			);

			WOOD_SETS.put(w, set);

			for (Block b : set.stream().toList()) {
				BLOCK_TO_WOODSET.put(b, set);
			}
		}

		/* ---------- Dye furniture ---------- */

		for (DyeDef dye : DYES) {
			SofaBlock sofa = registerSofa(dye.name() + "_sofa", dye.wool(),
					p -> p.sound(SoundType.WOOD)
							.strength(1.0F, 10.0F)
							.noOcclusion()
							.dynamicShape()
							.ignitedByLava()
							.instrument(NoteBlockInstrument.BASS));
			CurtainBlock curtain = registerCurtain(dye.name() + "_curtain", dye.wool(),
					p -> p.sound(SoundType.WOOL)
							.strength(0.5F, 10.0F)
							.noOcclusion()
							.dynamicShape());
			TableLampBlock floorLamp = registerTableLamp(dye.name() + "_table_lamp", dye.wool(),
					p -> p.sound(SoundType.WOOD)
							.strength(1.0F, 10.0F)
							.noCollission()
							.noOcclusion()
							.dynamicShape());

			DyeSet set = new DyeSet(dye.wool(), sofa, curtain, floorLamp);
			for (Block b : set.stream().toList()) {
				BLOCK_TO_DYESET.put(b, set);
			}
		}

		LOOT_TABLE_BLACKLIST.add(FLOOR_LAMP_SUPPORT);

		BLOCKS.forEachEntry((block, id) -> Registry.register(BuiltInRegistries.BLOCK, id, block));
		ITEM.forEachEntry((item, id) -> Registry.register(BuiltInRegistries.ITEM, id, item));
	}

	/* --------------------------------------------------------------------- */
	/* Other blocks                                                          */
	/* --------------------------------------------------------------------- */

	public static final IndustrialTableBlock INDUSTRIAL_TABLE = registerIndustrialTable("industrial_table",
			p -> p.sound(SoundType.METAL)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL)
					.requiresCorrectToolForDrops());
	public static final IndustrialCoffeeTableBlock INDUSTRIAL_COFFEE_TABLE = registerIndustrialCoffeeTable("industrial_coffee_table",
			p -> p.sound(SoundType.METAL)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL)
					.requiresCorrectToolForDrops());
	public static final CeilingLampBlock COPPER_CEILING_LAMP = registerCopperLamp("copper_ceiling_lamp",
			p -> p.sound(SoundType.COPPER)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.COLOR_ORANGE));
	public static final DecoratedIronBeamBlock DECORATED_IRON_BEAM = registerDecoratedIronBeam("decorated_iron_beam",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL)
					.requiresCorrectToolForDrops());
	public static final IronBeamBlock IRON_BEAM = registerIronBeam("iron_beam",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL)
					.requiresCorrectToolForDrops());

	public static final FloorLampDecorationBatBlock FLOOR_LAMP_DECORATION_BAT = registerFloorLampDecorationBatBlock("floor_lamp_decoration_bat",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL));
	public static final FloorLampDecorationVillagerBlock FLOOR_LAMP_DECORATION_VILLAGER = registerFloorLampDecorationVillagerBlock("floor_lamp_decoration_villager",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL));
	public static final IronLampBlock IRON_LAMP = registerIronLampBlock("street_lantern",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL));
	public static final SphereLampBlock SPHERE_LAMP = registerSphereLampBlock("sphere_lantern",
			p -> p.sound(SoundType.GLASS)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape());

	public static final TropicalPlantBlock TROPICAL_PLANT = registerTropicalPlantBlock("bag_of_tropical_plants", "tropical_plant_wall",
			p -> p.sound(SoundType.BIG_DRIPLEAF)
					.instabreak()
					.noCollission()
					.noOcclusion()
					.pushReaction(PushReaction.DESTROY)
					.dynamicShape());
	public static final MushroomPatchBlock MUSHROOM_PATCH = registerMushroomPatchBlock("bag_of_mushrooms", "mushroom_patch_wall",
			p -> p.sound(SoundType.GRASS)
					.instabreak()
					.noCollission()
					.noOcclusion()
					.pushReaction(PushReaction.DESTROY)
					.dynamicShape());
	public static final WaterPlantsLandBlock WATER_PLANTS = registerWaterPlantsBlock("bag_of_water_plants", "water_plant",
			p -> p.sound(SoundType.SMALL_DRIPLEAF)
					.instabreak()
					.noCollission()
					.pushReaction(PushReaction.DESTROY)
					.offsetType(BlockBehaviour.OffsetType.XZ)
					.mapColor(MapColor.PLANT),
			p -> p.sound(SoundType.LILY_PAD)
					.instabreak()
					.noCollission()
					.noOcclusion()
					.pushReaction(PushReaction.DESTROY)
					.offsetType(BlockBehaviour.OffsetType.XYZ)
					.dynamicShape()
					.mapColor(MapColor.PLANT));
	public static final PebbleBagBlock PEBBLE_BAG = registerPebbleBagBlock("bag_of_pebbles",
			p -> p.strength(1.0F, 0.0F)
					.noOcclusion()
					.pushReaction(PushReaction.DESTROY)
					.dynamicShape()
					.mapColor(MapColor.STONE));
	public static final GreekPotBlock GREEK_POT = registerGreekPotBlock("greek_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final HugePotBlock HUGE_POT = registerHugePotBlock("huge_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final StonePotBlock STONE_POT = registerStonePotBlock("stone_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final TallTerracottaPotBlock TALL_TERRACOTTA_POT = registerTallTerracottaPotBlock("tall_terracotta_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final BauhausPotBlock BAUHAUS_POT = registerBauhausPotBlock("bauhaus_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final BlackstonePotBlock BLACKSTONE_POT = registerBlackstonePotBlock("blackstone_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final FudgePotBlock FUDGE_POT = registerFudgePotBlock("fudge_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final HangingPotBlock HANGING_POT = registerHangingPotBlock("hanging_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final LargeHangingPotBlock LARGE_HANGING_POT = registerLargeHangingPotBlock("large_hanging_pot",
			p -> p.sound(SoundType.DECORATED_POT)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final WoodenHangingPotBlock WOODEN_HANGING_POT = registerWoodenHangingPotBlock("wooden_hanging_pot",
			p -> p.sound(SoundType.WOOD)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final PosterBlock POSTER = registerPosterBlock("poster",
			p -> p.sound(new SoundType(1.0F, 1.0F, SoundEvents.BOOK_PAGE_TURN, SoundEvents.BOOK_PAGE_TURN, SoundEvents.BOOK_PAGE_TURN, SoundEvents.BOOK_PAGE_TURN, SoundEvents.BOOK_PAGE_TURN))
					.strength(0.01F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.instrument(NoteBlockInstrument.BASEDRUM));
	public static final TrashBlock TRASH = registerTrashBlock("trash",
			p -> p.sound(SoundType.METAL)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL));
	public static final FireHydrantBlock FIRE_HYDRANT = registerFireHydrantBlock("fire_hydrant",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL));
	public static final FireHydrantBlock EMERGENCY_FIRE_HYDRANT = registerFireHydrantBlock("emergency_fire_hydrant",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL));
	public static final ManholeBlock MANHOLE = registerManholeBlock("manhole",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL));
	public static final ToolboxBlock DECORATIVE_TOOLBOX = registerToolboxBlock("toolbox",
			p -> p.sound(SoundType.METAL)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape()
					.mapColor(MapColor.METAL));
	public static final BarrierBlock WARNING_BARRIER = registerBarrierBlock("warning_barrier",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL));
	public static final BarrierBlock ROAD_WORKS_BARRIER = registerBarrierBlock("road_works_barrier",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL));
	public static final BarrierBlock DANGER_BARRIER = registerBarrierBlock("danger_barrier",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL));
	public static final BarrierBlock WOODEN_BARRIER = registerBarrierBlock("wooden_barrier",
			p -> p.sound(SoundType.NETHERITE_BLOCK)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.METAL));
	public static final BlackboardMenuBlock BLACKBOARD_MENU = registerBlackboardMenuBlock("blackboard_menu",
			p -> p.sound(SoundType.WOOD)
					.strength(1.0F, 10.0F)
					.noOcclusion());
	public static final WallClockBlock WOODEN_CLOCK = registerWallClockBlock("wooden_clock",
			p -> p.sound(SoundType.WOOD)
					.strength(1.0F, 10.0F)
					.noOcclusion());
	public static final PlushBlock PIG_PLUSH = registerPlushBlock("pig_plush",
			p -> p.sound(SoundType.WOOL)
					.strength(0.7F, 10.0F)
					.noOcclusion()
					.dynamicShape());
	public static final PlushBlock COW_PLUSH = registerPlushBlock("cow_plush",
			p -> p.sound(SoundType.WOOL)
					.strength(0.7F, 10.0F)
					.noOcclusion()
					.dynamicShape());
	public static final CatPlushBlock CAT_PLUSH = registerCatPlushBlock("cat_plush",
			p -> p.sound(SoundType.WOOL)
					.strength(0.7F, 10.0F)
					.noOcclusion()
					.dynamicShape());
	public static final BroomBlock BROOM = registerBroomBlock("broom",
			p -> p.sound(SoundType.WOOD)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape());
	public static final RakeBlock RAKE = registerRakeBlock("rake",
			p -> p.sound(SoundType.WOOD)
					.strength(1.0F, 10.0F)
					.noOcclusion()
					.dynamicShape());
	public static final GraveBlock GRAVE_BROKEN = registerGraveBlock("grave_broken",
			p -> p.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.STONE));
	public static final GraveBlock GRAVE_SKELETON = registerGraveBlock("grave_skeleton",
			p -> p.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.STONE));
	public static final GraveBlock GRAVE_CREEPER = registerGraveBlock("grave_creeper",
			p -> p.strength(1.0F, 10.0F)
					.noOcclusion()
					.mapColor(MapColor.STONE));

	/* --------------------------------------------------------------------- */
	/* Registration wrappers                                                 */
	/* --------------------------------------------------------------------- */

	private static <T extends Block> T registerWithItem(
			String name,
			Function<BlockBehaviour.Properties, T> blockFactory,
			BlockBehaviour.Properties properties,
			@Nullable BiFunction<Block, Item.Properties, ? extends BlockItem> itemFactory,
			RegistryGroup<Block> blockGroup,
			RegistryGroup<Item> itemGroup
	) {
		T block = blockFactory.apply(properties);
		blockGroup.add(block, UnusualFurniture.id(name));
		if (itemFactory != null) {
			Item item = itemFactory.apply(block, new Item.Properties());
			itemGroup.add(item, UnusualFurniture.id(name));
		}
		return block;
	}

	private static <T extends Block> T simple(
			String name,
			Function<BlockBehaviour.Properties, T> factory,
			Block base,
			@Nullable UnaryOperator<BlockBehaviour.Properties> modifier,
			RegistryGroup<Block> blockGroup,
			RegistryGroup<Item> itemGroup
	) {
		BlockBehaviour.Properties props =
				BlockBehaviour.Properties.ofFullCopy(base)
						.mapColor(base.defaultMapColor());

		if (modifier != null) {
			props = modifier.apply(props);
		}

		return registerWithItem(
				name,
				factory,
				props,
				BlockItem::new,
				blockGroup,
				itemGroup
		);
	}

	private static <T extends Block> T simple(
			String name,
			Function<BlockBehaviour.Properties, T> factory,
			Block base,
			RegistryGroup<Block> blockGroup,
			RegistryGroup<Item> itemGroup
	) {
		return simple(name, factory, base, null, blockGroup, itemGroup);
	}

	private static <T extends Block> T simpleOf(
			String name,
			Function<BlockBehaviour.Properties, T> factory,
			@Nullable UnaryOperator<BlockBehaviour.Properties> modifier,
			RegistryGroup<Block> blockGroup,
			RegistryGroup<Item> itemGroup
	) {
		BlockBehaviour.Properties props = BlockBehaviour.Properties.of();

		if (modifier != null) {
			props = modifier.apply(props);
		}

		return registerWithItem(
				name,
				factory,
				props,
				BlockItem::new,
				blockGroup,
				itemGroup
		);
	}

	private static <T extends Block> T simpleOf(
			String name,
			Function<BlockBehaviour.Properties, T> factory,
			RegistryGroup<Block> blockGroup,
			RegistryGroup<Item> itemGroup
	) {
		return simpleOf(name, factory, null, blockGroup, itemGroup);
	}

	// @formatter:off
	private static TableBlock registerTable(String n, Block b) { return simple(n, TableBlock::new, b, WOODEN_TABLE_BLOCKS, WOODEN_TABLE_ITEMS); }
	private static TableBlock registerTable(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TableBlock::new, b, modifier, WOODEN_TABLE_BLOCKS, WOODEN_TABLE_ITEMS); }
	private static CoffeeTableBlock registerCoffeeTable(String n, Block b) { return simple(n, CoffeeTableBlock::new, b, WOODEN_COFFEE_TABLE_BLOCKS, WOODEN_COFFEE_TABLE_ITEMS); }
	private static CoffeeTableBlock registerCoffeeTable(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CoffeeTableBlock::new, b, modifier, WOODEN_COFFEE_TABLE_BLOCKS, WOODEN_COFFEE_TABLE_ITEMS); }
	private static ChairBlock registerChair(String n, Block b) { return simple(n, ChairBlock::new, b, CHAIR_BLOCKS, CHAIR_ITEMS); }
	private static ChairBlock registerChair(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, ChairBlock::new, b, modifier, CHAIR_BLOCKS, CHAIR_ITEMS); }
	private static StoolBlock registerStool(String n, Block b) { return simple(n, StoolBlock::new, b, STOOL_BLOCKS, STOOL_ITEMS); }
	private static StoolBlock registerStool(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, StoolBlock::new, b, modifier, STOOL_BLOCKS, STOOL_ITEMS); }
	private static SofaBlock registerSofa(String n, Block b) { return simple(n, SofaBlock::new, b, SOFA_BLOCKS, SOFA_ITEMS); }
	private static SofaBlock registerSofa(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, SofaBlock::new, b, modifier, SOFA_BLOCKS, SOFA_ITEMS); }
	private static CurtainBlock registerCurtain(String n, Block b) { return simple(n, CurtainBlock::new, b, CURTAIN_BLOCKS, CURTAIN_ITEMS); }
	private static CurtainBlock registerCurtain(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CurtainBlock::new, b, modifier, CURTAIN_BLOCKS, CURTAIN_ITEMS); }
	private static TableLampBlock registerTableLamp(String n, Block b) { return simple(n, TableLampBlock::new, b, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static TableLampBlock registerTableLamp(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TableLampBlock::new, b, modifier, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static CeilingLampBlock registerCeilingLamp(String n, Block b) { return simple(n, CeilingLampBlock::new, b, WOODEN_CEILING_LAMP_BLOCKS, WOODEN_CEILING_LAMP_ITEMS); }
	private static CeilingLampBlock registerCeilingLamp(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CeilingLampBlock::new, b, modifier, WOODEN_CEILING_LAMP_BLOCKS, WOODEN_CEILING_LAMP_ITEMS); }
	private static DrawerBlock registerDrawer(String n, Block b) { return simple(n, DrawerBlock::new, b, DRAWER_BLOCKS, DRAWER_ITEMS); }
	private static DrawerBlock registerDrawer(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, DrawerBlock::new, b, modifier, DRAWER_BLOCKS, DRAWER_ITEMS); }
	private static BenchBlock registerBench(String n, Block b) { return simple(n, BenchBlock::new, b, BENCH_BLOCKS, BENCH_ITEMS); }
	private static BenchBlock registerBench(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BenchBlock::new, b, modifier, BENCH_BLOCKS, BENCH_ITEMS); }
	private static ShelfBlock registerShelf(String n, Block b) { return simple(n, ShelfBlock::new, Blocks.DIRT, SHELF_BLOCKS, SHELF_ITEMS); }
	private static ShelfBlock registerShelf(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, ShelfBlock::new, Blocks.DIRT, modifier, SHELF_BLOCKS, SHELF_ITEMS); }
	private static CarvedPlanksBlock registerCarvedPlanks(String n, Block b) { return simple(n, CarvedPlanksBlock::new, b, CARVED_PLANK_BLOCKS, CARVED_PLANK_ITEMS); }
	private static CarvedPlanksBlock registerCarvedPlanks(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CarvedPlanksBlock::new, b, modifier, CARVED_PLANK_BLOCKS, CARVED_PLANK_ITEMS); }
	private static OpenRiserStairBlock registerOpenRiserStair(String n, Block b) { return registerWithItem(n, OpenRiserStairBlock::new, BlockBehaviour.Properties.ofFullCopy(b).mapColor(b.defaultMapColor()), OpenRiserStairBlockItem::new, OPEN_RISER_STAIR_BLOCKS, OPEN_RISER_STAIR_ITEMS); }
	private static OpenRiserStairBlock registerOpenRiserStair(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return registerWithItem(n, OpenRiserStairBlock::new, modifier.apply(BlockBehaviour.Properties.ofFullCopy(b).mapColor(b.defaultMapColor())), OpenRiserStairBlockItem::new, OPEN_RISER_STAIR_BLOCKS, OPEN_RISER_STAIR_ITEMS); }
	private static RailingBlock registerRailing(String n, Block b) { return simple(n, RailingBlock::new, b, RAILING_BLOCKS, RAILING_ITEMS); }
	private static RailingBlock registerRailing(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, RailingBlock::new, b, modifier, RAILING_BLOCKS, RAILING_ITEMS); }
	private static BeamBlock registerBeam(String n, Block b) { return simple(n, BeamBlock::new, b, WOODEN_BEAM_BLOCKS, WOODEN_BEAM_ITEMS); }
	private static BeamBlock registerBeam(String n, Block b, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BeamBlock::new, b, modifier, WOODEN_BEAM_BLOCKS, WOODEN_BEAM_ITEMS); }

	private static IndustrialTableBlock registerIndustrialTable(String n) { return simple(n, IndustrialTableBlock::new, Blocks.IRON_BLOCK, TABLE_BLOCKS, TABLE_ITEMS); }
	private static IndustrialTableBlock registerIndustrialTable(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IndustrialTableBlock::new, Blocks.IRON_BLOCK, modifier, TABLE_BLOCKS, TABLE_ITEMS); }
	private static IndustrialCoffeeTableBlock registerIndustrialCoffeeTable(String n) { return simple(n, IndustrialCoffeeTableBlock::new, Blocks.IRON_BLOCK, COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS); }
	private static IndustrialCoffeeTableBlock registerIndustrialCoffeeTable(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IndustrialCoffeeTableBlock::new, Blocks.IRON_BLOCK, modifier, COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS); }
	private static CeilingLampBlock registerCopperLamp(String n) { return simple(n, CopperCeilingLampBlock::new, Blocks.DIRT, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS); }
	private static CeilingLampBlock registerCopperLamp(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CopperCeilingLampBlock::new, Blocks.DIRT, modifier, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS); }
	private static IronBeamBlock registerIronBeam(String n) { return simple(n, IronBeamBlock::new, Blocks.IRON_BLOCK, BEAM_BLOCKS, BEAM_ITEMS); }
	private static IronBeamBlock registerIronBeam(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IronBeamBlock::new, Blocks.IRON_BLOCK, modifier, BEAM_BLOCKS, BEAM_ITEMS); }
	private static DecoratedIronBeamBlock registerDecoratedIronBeam(String n) { return simple(n, DecoratedIronBeamBlock::new, Blocks.IRON_BLOCK, BEAM_BLOCKS, BEAM_ITEMS); }
	private static DecoratedIronBeamBlock registerDecoratedIronBeam(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, DecoratedIronBeamBlock::new, Blocks.IRON_BLOCK, modifier, BEAM_BLOCKS, BEAM_ITEMS); }
	private static FloorLampDecorationBatBlock registerFloorLampDecorationBatBlock(String n) { return simple(n, FloorLampDecorationBatBlock::new, Blocks.DIRT, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationBatBlock registerFloorLampDecorationBatBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FloorLampDecorationBatBlock::new, Blocks.DIRT, modifier, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationVillagerBlock registerFloorLampDecorationVillagerBlock(String n) { return simple(n, FloorLampDecorationVillagerBlock::new, Blocks.DIRT, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationVillagerBlock registerFloorLampDecorationVillagerBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FloorLampDecorationVillagerBlock::new, Blocks.DIRT, modifier, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static IronLampBlock registerIronLampBlock(String n) { return simple(n, IronLampBlock::new, Blocks.DIRT, LAMP_BLOCKS, LAMP_ITEMS); }
	private static IronLampBlock registerIronLampBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IronLampBlock::new, Blocks.DIRT, modifier, LAMP_BLOCKS, LAMP_ITEMS); }
	private static SphereLampBlock registerSphereLampBlock(String n) { return simple(n, SphereLampBlock::new, Blocks.GLASS, LAMP_BLOCKS, LAMP_ITEMS); }
	private static SphereLampBlock registerSphereLampBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, SphereLampBlock::new, Blocks.GLASS, modifier, LAMP_BLOCKS, LAMP_ITEMS); }
	private static TropicalPlantBlock registerTropicalPlantBlock(String n, String wallName, UnaryOperator<BlockBehaviour.Properties> modifier) {
		TropicalPlantBlock block = new TropicalPlantBlock(modifier.apply(BlockBehaviour.Properties.of()));
		WallTropicalPlantBlock wallBlock = new WallTropicalPlantBlock(modifier.apply(BlockBehaviour.Properties.of()));
		BAG_BLOCKS.add(block, UnusualFurniture.id(n));
		BAG_BLOCKS.add(wallBlock, UnusualFurniture.id(wallName));
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.add(blockItem, UnusualFurniture.id(n));
		return block;
	}
	private static MushroomPatchBlock registerMushroomPatchBlock(String n, String wallName, UnaryOperator<BlockBehaviour.Properties> modifier) {
		MushroomPatchBlock block = new MushroomPatchBlock(modifier.apply(BlockBehaviour.Properties.of()));
		WallMushroomPatchBlock wallBlock = new WallMushroomPatchBlock(modifier.apply(BlockBehaviour.Properties.of()));
		BAG_BLOCKS.add(block, UnusualFurniture.id(n));
		BAG_BLOCKS.add(wallBlock, UnusualFurniture.id(wallName));
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.add(blockItem, UnusualFurniture.id(n));
		return block;
	}
	private static WaterPlantsLandBlock registerWaterPlantsBlock(String n, String waterName, UnaryOperator<BlockBehaviour.Properties> landModifier, UnaryOperator<BlockBehaviour.Properties> waterModifier) {
		WaterPlantsLandBlock block = new WaterPlantsLandBlock(landModifier.apply(BlockBehaviour.Properties.of()));
		WaterPlantsBlock waterBlock = new WaterPlantsBlock(waterModifier.apply(BlockBehaviour.Properties.of()));
		BAG_BLOCKS.add(block, UnusualFurniture.id(n));
		BAG_BLOCKS.add(waterBlock, UnusualFurniture.id(waterName));
		LOOT_TABLE_BLACKLIST.add(waterBlock);
		WaterBagBlockItem blockItem = new WaterBagBlockItem(block, waterBlock, new Item.Properties());
		BAG_ITEMS.add(blockItem, UnusualFurniture.id(n));
		return block;
	}
	private static PebbleBagBlock registerPebbleBagBlock(String n) { return simpleOf(n, PebbleBagBlock::new, BAG_BLOCKS, BAG_ITEMS); }
	private static PebbleBagBlock registerPebbleBagBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simpleOf(n, PebbleBagBlock::new, modifier, BAG_BLOCKS, BAG_ITEMS); }
	private static GreekPotBlock registerGreekPotBlock(String n) { return simple(n, GreekPotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static GreekPotBlock registerGreekPotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, GreekPotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static HugePotBlock registerHugePotBlock(String n) { return simple(n, HugePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static HugePotBlock registerHugePotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, HugePotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static StonePotBlock registerStonePotBlock(String n) { return simple(n, StonePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static StonePotBlock registerStonePotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, StonePotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static TallTerracottaPotBlock registerTallTerracottaPotBlock(String n) { return simple(n, TallTerracottaPotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static TallTerracottaPotBlock registerTallTerracottaPotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TallTerracottaPotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static BauhausPotBlock registerBauhausPotBlock(String n) { return simple(n, BauhausPotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static BauhausPotBlock registerBauhausPotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BauhausPotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static BlackstonePotBlock registerBlackstonePotBlock(String n) { return simple(n, BlackstonePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static BlackstonePotBlock registerBlackstonePotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BlackstonePotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static FudgePotBlock registerFudgePotBlock(String n) { return simple(n, FudgePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static FudgePotBlock registerFudgePotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FudgePotBlock::new, Blocks.DECORATED_POT, modifier, POT_BLOCKS, POT_ITEMS); }
	private static HangingPotBlock registerHangingPotBlock(String n) { return registerWithItem(n, HangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static HangingPotBlock registerHangingPotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return registerWithItem(n, HangingPotBlock::new, modifier.apply(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor())), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static LargeHangingPotBlock registerLargeHangingPotBlock(String n) { return registerWithItem(n, LargeHangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static LargeHangingPotBlock registerLargeHangingPotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return registerWithItem(n, LargeHangingPotBlock::new, modifier.apply(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor())), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static WoodenHangingPotBlock registerWoodenHangingPotBlock(String n) { return registerWithItem(n, WoodenHangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static WoodenHangingPotBlock registerWoodenHangingPotBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return registerWithItem(n, WoodenHangingPotBlock::new, modifier.apply(BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor())), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static PosterBlock registerPosterBlock(String n) { return simple(n, PosterBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static PosterBlock registerPosterBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, PosterBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static TrashBlock registerTrashBlock(String n) { return simple(n, TrashBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static TrashBlock registerTrashBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TrashBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static FireHydrantBlock registerFireHydrantBlock(String n) { return simple(n, FireHydrantBlock::new, Blocks.DIRT, FIRE_HYDRANT_BLOCKS, FIRE_HYDRANT_ITEMS); }
	private static FireHydrantBlock registerFireHydrantBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FireHydrantBlock::new, Blocks.DIRT, modifier, FIRE_HYDRANT_BLOCKS, FIRE_HYDRANT_ITEMS); }
	private static ManholeBlock registerManholeBlock(String n) { return simple(n, ManholeBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static ManholeBlock registerManholeBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, ManholeBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static ToolboxBlock registerToolboxBlock(String n) { return simple(n, ToolboxBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static ToolboxBlock registerToolboxBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, ToolboxBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static BarrierBlock registerBarrierBlock(String n) { return simple(n, BarrierBlock::new, Blocks.DIRT, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static BarrierBlock registerBarrierBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BarrierBlock::new, Blocks.DIRT, modifier, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static BlackboardMenuBlock registerBlackboardMenuBlock(String n) { return simple(n, BlackboardMenuBlock::new, Blocks.DIRT, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static BlackboardMenuBlock registerBlackboardMenuBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BlackboardMenuBlock::new, Blocks.DIRT, modifier, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static WallClockBlock registerWallClockBlock(String n) { return simple(n, WallClockBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static WallClockBlock registerWallClockBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, WallClockBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static TableLampBlock registerFloorLampBlock(String n) { return simple(n, TableLampBlock::new, Blocks.OAK_PLANKS, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static TableLampBlock registerFloorLampBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TableLampBlock::new, Blocks.OAK_PLANKS, modifier, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static PlushBlock registerPlushBlock(String n) { return simple(n, PlushBlock::new, Blocks.WHITE_WOOL, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static PlushBlock registerPlushBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, PlushBlock::new, Blocks.WHITE_WOOL, modifier, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static CatPlushBlock registerCatPlushBlock(String n) { return simple(n, CatPlushBlock::new, Blocks.WHITE_WOOL, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static CatPlushBlock registerCatPlushBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CatPlushBlock::new, Blocks.WHITE_WOOL, modifier, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static BroomBlock registerBroomBlock(String n) { return simple(n, BroomBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static BroomBlock registerBroomBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BroomBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static RakeBlock registerRakeBlock(String n) { return simple(n, RakeBlock::new, Blocks.DIRT, PROPS_BLOCKS, PROPS_ITEMS); }
	private static RakeBlock registerRakeBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, RakeBlock::new, Blocks.DIRT, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static GraveBlock registerGraveBlock(String n) { return simpleOf(n, GraveBlock::new, GRAVE_BLOCKS, GRAVE_ITEMS); }
	private static GraveBlock registerGraveBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simpleOf(n, GraveBlock::new, modifier, GRAVE_BLOCKS, GRAVE_ITEMS); }
	// @formatter:on

	private static <T extends Item> T registerItem(String name, T item, RegistryGroup<Item> regGroup) {
		regGroup.add(item, UnusualFurniture.id(name));
		return item;
	}

	private static <T extends Block> T registerBlock(String name, T block, RegistryGroup<Block> regGroup) {
		regGroup.add(block, UnusualFurniture.id(name));
		return block;
	}

	public static @Nullable WoodSet getWoodSet(Block block) {
		return BLOCK_TO_WOODSET.get(block);
	}

	public static @Nullable DyeSet getDyeSet(Block block) {
		return BLOCK_TO_DYESET.get(block);
	}
}
