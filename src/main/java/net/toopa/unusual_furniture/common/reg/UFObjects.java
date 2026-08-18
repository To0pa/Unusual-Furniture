package net.toopa.unusual_furniture.common.reg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import net.minecraft.world.level.block.SoundType;

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
import net.toopa.unusual_furniture.common.block.FloorLampSupportBlock;
import net.toopa.unusual_furniture.common.block.TableLampBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationBatBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationVillagerBlock;
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
import net.toopa.unusual_furniture.common.utils.DyeSet;
import net.toopa.unusual_furniture.common.utils.WoodSet;
import net.toopa.unusual_furniture.platform.UFRegistries;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class UFObjects {

	private UFObjects() {}

	/* --------------------------------------------------------------------- */
	/* Creative-tab grouping                                                  */
	/* --------------------------------------------------------------------- */

	public static final UFRegistry<Block> BLOCKS = UFRegistries.create(BuiltInRegistries.BLOCK, UnusualFurniture.MOD_ID);
	public static final UFRegistry<Item> ITEMS = UFRegistries.create(BuiltInRegistries.ITEM, UnusualFurniture.MOD_ID);

	public static final UFRegistry<Block> FURNITURE_BLOCKS = UFRegistries.create(BLOCKS);
	public static final UFRegistry<Block> BUILDING_BLOCKS = UFRegistries.create(BLOCKS);
	public static final UFRegistry<Block> PROPS_BLOCKS = UFRegistries.create(BLOCKS);

	public static final UFRegistry<Item> FURNITURE_ITEMS = UFRegistries.create(ITEMS);
	public static final UFRegistry<Item> BUILDING_ITEMS = UFRegistries.create(ITEMS);
	public static final UFRegistry<Item> PROPS_ITEMS = UFRegistries.create(ITEMS);

	/* --------------------------------------------------------------------- */
	/* Variant lookup                                                         */
	/* --------------------------------------------------------------------- */

	public static final Map<Block, WoodSet> BLOCK_TO_WOODSET = new LinkedHashMap<>();
	public static final Map<Block, DyeSet> BLOCK_TO_DYESET = new LinkedHashMap<>();

	public static final Map<String, WoodSet> WOOD_SETS = new LinkedHashMap<>();

	public static final List<Block> LOOT_TABLE_BLACKLIST = new ArrayList<>();

	/* --------------------------------------------------------------------- */
	/* Per-type registries                                                    */
	/* --------------------------------------------------------------------- */

	public static final UFRegistry<Block> TABLE_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> TABLE_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> WOODEN_TABLE_BLOCKS = UFRegistries.create(TABLE_BLOCKS);
	public static final UFRegistry<Item> WOODEN_TABLE_ITEMS = UFRegistries.create(TABLE_ITEMS);

	public static final UFRegistry<Block> COFFEE_TABLE_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> COFFEE_TABLE_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> WOODEN_COFFEE_TABLE_BLOCKS = UFRegistries.create(COFFEE_TABLE_BLOCKS);
	public static final UFRegistry<Item> WOODEN_COFFEE_TABLE_ITEMS = UFRegistries.create(COFFEE_TABLE_ITEMS);

	public static final UFRegistry<Block> CHAIR_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> CHAIR_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> STOOL_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> STOOL_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> SOFA_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> SOFA_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> CEILING_LAMP_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> CEILING_LAMP_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> WOODEN_CEILING_LAMP_BLOCKS = UFRegistries.create(CEILING_LAMP_BLOCKS);
	public static final UFRegistry<Item> WOODEN_CEILING_LAMP_ITEMS = UFRegistries.create(CEILING_LAMP_ITEMS);

	public static final UFRegistry<Block> DRAWER_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> DRAWER_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> BENCH_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> BENCH_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> CURTAIN_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> CURTAIN_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> SHELF_BLOCKS = UFRegistries.create(FURNITURE_BLOCKS);
	public static final UFRegistry<Item> SHELF_ITEMS = UFRegistries.create(FURNITURE_ITEMS);

	public static final UFRegistry<Block> BAG_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> BAG_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> POT_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> POT_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> FIRE_HYDRANT_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> FIRE_HYDRANT_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> BARRIER_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> BARRIER_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> TABLE_LAMP_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> TABLE_LAMP_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> PLUSH_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> PLUSH_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> GRAVE_BLOCKS = UFRegistries.create(PROPS_BLOCKS);
	public static final UFRegistry<Item> GRAVE_ITEMS = UFRegistries.create(PROPS_ITEMS);

	public static final UFRegistry<Block> CARVED_PLANK_BLOCKS = UFRegistries.create(BUILDING_BLOCKS);
	public static final UFRegistry<Item> CARVED_PLANK_ITEMS = UFRegistries.create(BUILDING_ITEMS);

	public static final UFRegistry<Block> OPEN_RISER_STAIR_BLOCKS = UFRegistries.create(BUILDING_BLOCKS);
	public static final UFRegistry<Item> OPEN_RISER_STAIR_ITEMS = UFRegistries.create(BUILDING_ITEMS);

	public static final UFRegistry<Block> RAILING_BLOCKS = UFRegistries.create(BUILDING_BLOCKS);
	public static final UFRegistry<Item> RAILING_ITEMS = UFRegistries.create(BUILDING_ITEMS);

	public static final UFRegistry<Block> BEAM_BLOCKS = UFRegistries.create(BUILDING_BLOCKS);
	public static final UFRegistry<Item> BEAM_ITEMS = UFRegistries.create(BUILDING_ITEMS);

	public static final UFRegistry<Block> WOODEN_BEAM_BLOCKS = UFRegistries.create(BEAM_BLOCKS);
	public static final UFRegistry<Item> WOODEN_BEAM_ITEMS = UFRegistries.create(BEAM_ITEMS);

	public static final UFRegistry<Block> FLOOR_LAMP_BLOCKS = UFRegistries.create(BUILDING_BLOCKS);
	public static final UFRegistry<Item> FLOOR_LAMP_ITEMS = UFRegistries.create(BUILDING_ITEMS);

	public static final UFRegistry<Block> LAMP_BLOCKS = UFRegistries.create(BUILDING_BLOCKS);
	public static final UFRegistry<Item> LAMP_ITEMS = UFRegistries.create(BUILDING_ITEMS);

	/* --------------------------------------------------------------------- */
	/* Variant definitions                                                    */
	/* --------------------------------------------------------------------- */

	public record WoodDef(String name, Block plank, Block log) {
	}

	public record DyeDef(String name, Block wool) {
	}

	public static final List<WoodDef> WOODS = List.of(
			new WoodDef("oak", Blocks.OAK_PLANKS, Blocks.OAK_LOG),
			new WoodDef("spruce", Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_LOG),
			new WoodDef("birch", Blocks.BIRCH_PLANKS, Blocks.BIRCH_LOG),
			new WoodDef("jungle", Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_LOG),
			new WoodDef("acacia", Blocks.ACACIA_PLANKS, Blocks.ACACIA_LOG),
			new WoodDef("dark_oak", Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_LOG),
			new WoodDef("mangrove", Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_LOG),
			new WoodDef("cherry", Blocks.CHERRY_PLANKS, Blocks.CHERRY_LOG),
			new WoodDef("bamboo", Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_BLOCK),
			new WoodDef("crimson", Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STEM),
			new WoodDef("warped", Blocks.WARPED_PLANKS, Blocks.WARPED_STEM)
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
	/* Items                                                                  */
	/* --------------------------------------------------------------------- */

	public static final UFRegistryEntry<Item> SCREW_ITEM =
			registerItem("screw", new Item(new Item.Properties()), PROPS_ITEMS);

	public static final UFRegistryEntry<Block> FLOOR_LAMP_SUPPORT =
			registerBlock("floor_lamp_support", new FloorLampSupportBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK)), BLOCKS);

	/* --------------------------------------------------------------------- */
	/* Init                                                                   */
	/* --------------------------------------------------------------------- */

	public static void init() {

		/* ---------- Wood furniture ---------- */

		for (WoodDef wood : WOODS) {
			String w = wood.name();

			TableBlock table = registerTable(w + "_table", wood.plank());
			CoffeeTableBlock coffee = registerCoffeeTable(w + "_coffee_table", wood.plank());
			ChairBlock chair = registerChair(w + "_chair", wood.plank());
			StoolBlock stool = registerStool(w + "_stool", wood.plank());
			CeilingLampBlock lamp = registerCeilingLamp(w + "_ceiling_lamp", wood.plank());
			DrawerBlock drawer = registerDrawer(w + "_drawer", wood.plank());
			BenchBlock bench = registerBench(w + "_bench", wood.plank());
			ShelfBlock shelf = registerShelf(w + "_shelf", wood.plank());
			CarvedPlanksBlock carved = registerCarvedPlanks("carved_" + w, wood.plank());
			OpenRiserStairBlock stairs = registerOpenRiserStair(w + "_open_riser_stairs", wood.plank());
			RailingBlock railing = registerRailing(w + "_railing", wood.plank());
			BeamBlock beam = registerBeam(w + "_beam", wood.plank());

			WoodSet set = new WoodSet(
					wood.plank(), wood.log(),
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
			SofaBlock sofa = registerSofa(dye.name() + "_sofa", dye.wool());
			CurtainBlock curtain = registerCurtain(dye.name() + "_curtain", dye.wool());
			TableLampBlock floorLamp = registerTableLamp(dye.name() + "_table_lamp", dye.wool());

			DyeSet set = new DyeSet(dye.wool(), sofa, curtain, floorLamp);
			for (Block b : set.stream().toList()) {
				BLOCK_TO_DYESET.put(b, set);
			}
		}

		BLOCKS.init();
		ITEMS.init();
	}

	/* --------------------------------------------------------------------- */
	/* Other blocks                                                          */
	/* --------------------------------------------------------------------- */

	public static final IndustrialTableBlock INDUSTRIAL_TABLE = registerIndustrialTable("industrial_table");
	public static final IndustrialCoffeeTableBlock INDUSTRIAL_COFFEE_TABLE = registerIndustrialCoffeeTable("industrial_coffee_table");
	public static final CeilingLampBlock COPPER_CEILING_LAMP = registerCopperLamp("copper_ceiling_lamp");
	public static final DecoratedIronBeamBlock DECORATED_IRON_BEAM = registerDecoratedIronBeam("decorated_iron_beam");
	public static final IronBeamBlock IRON_BEAM = registerIronBeam("iron_beam");

	public static final FloorLampDecorationBatBlock FLOOR_LAMP_DECORATION_BAT = registerFloorLampDecorationBatBlock("floor_lamp_decoration_bat", p -> p.sound(SoundType.NETHERITE_BLOCK));
	public static final FloorLampDecorationVillagerBlock FLOOR_LAMP_DECORATION_VILLAGER = registerFloorLampDecorationVillagerBlock("floor_lamp_decoration_villager", p -> p.sound(SoundType.NETHERITE_BLOCK));
	public static final IronLampBlock IRON_LAMP = registerIronLampBlock("iron_lamp", p -> p.sound(SoundType.NETHERITE_BLOCK));
	public static final SphereLampBlock SPHERE_LAMP = registerSphereLampBlock("sphere_lamp", p -> p.sound(SoundType.NETHERITE_BLOCK));

	public static final TropicalPlantBlock TROPICAL_PLANT = registerTropicalPlantBlock("tropical_plant", p -> p.sound(SoundType.GRASS));
	public static final MushroomPatchBlock MUSHROOM_PATCH = registerMushroomPatchBlock("mushroom_patch", p -> p.sound(SoundType.GRASS));
	public static final WaterPlantsLandBlock WATER_PLANTS = registerWaterPlantsBlock("water_plants", p -> p.sound(SoundType.LILY_PAD));
	public static final PebbleBagBlock PEBBLE_BAG = registerPebbleBagBlock("pebble_bag");
	public static final GreekPotBlock GREEK_POT = registerGreekPotBlock("greek_pot");
	public static final HugePotBlock HUGE_POT = registerHugePotBlock("huge_pot");
	public static final StonePotBlock STONE_POT = registerStonePotBlock("stone_pot");
	public static final TallTerracottaPotBlock TALL_TERRACOTTA_POT = registerTallTerracottaPotBlock("tall_terracotta_pot");
	public static final BauhausPotBlock BAUHAUS_POT = registerBauhausPotBlock("bauhaus_pot");
	public static final BlackstonePotBlock BLACKSTONE_POT = registerBlackstonePotBlock("blackstone_pot");
	public static final FudgePotBlock FUDGE_POT = registerFudgePotBlock("fudge_pot");
	public static final HangingPotBlock HANGING_POT = registerHangingPotBlock("hanging_pot");
	public static final LargeHangingPotBlock LARGE_HANGING_POT = registerLargeHangingPotBlock("large_hanging_pot");
	public static final WoodenHangingPotBlock WOODEN_HANGING_POT = registerWoodenHangingPotBlock("wooden_hanging_pot");
	public static final PosterBlock POSTER = registerPosterBlock("poster");
	public static final TrashBlock TRASH = registerTrashBlock("trash");
	public static final FireHydrantBlock FIRE_HYDRANT = registerFireHydrantBlock("fire_hydrant");
	public static final FireHydrantBlock EMERGENCY_FIRE_HYDRANT = registerFireHydrantBlock("emergency_fire_hydrant");
	public static final ManholeBlock MANHOLE = registerManholeBlock("manhole");
	public static final ToolboxBlock DECORATIVE_TOOLBOX = registerToolboxBlock("decorative_toolbox");
	public static final BarrierBlock WARNING_BARRIER = registerBarrierBlock("warning_barrier");
	public static final BarrierBlock ROAD_WORKS_BARRIER = registerBarrierBlock("road_works_barrier");
	public static final BarrierBlock DANGER_BARRIER = registerBarrierBlock("danger_barrier");
	public static final BarrierBlock WOODEN_BARRIER = registerBarrierBlock("wooden_barrier");
	public static final BlackboardMenuBlock BLACKBOARD_MENU = registerBlackboardMenuBlock("blackboard_menu");
	public static final WallClockBlock WOODEN_CLOCK = registerWallClockBlock("wooden_clock");
//	public static final FloorLampBlock SPRUCE_FLOOR_LAMP = registerFloorLampBlock("spruce_floor_lamp");
	public static final PlushBlock PIG_PLUSH = registerPlushBlock("pig_plush");
	public static final PlushBlock COW_PLUSH = registerPlushBlock("cow_plush");
	public static final CatPlushBlock CAT_PLUSH = registerCatPlushBlock("cat_plush");
	public static final BroomBlock BROOM = registerBroomBlock("broom");
	public static final RakeBlock RAKE = registerRakeBlock("rakes");
	public static final GraveBlock GRAVE_BROKEN = registerGraveBlock("grave_broken");
	public static final GraveBlock GRAVE_SKELETON = registerGraveBlock("grave_skeleton");
	public static final GraveBlock GRAVE_CREEPER = registerGraveBlock("grave_creeper");

	/* --------------------------------------------------------------------- */
	/* Registration wrappers                                                 */
	/* --------------------------------------------------------------------- */

	private static <T extends Block> UFRegistryEntry<T> registerWithItem(
			String name,
			Function<BlockBehaviour.Properties, T> blockFactory,
			BlockBehaviour.Properties properties,
			@Nullable BiFunction<Block, Item.Properties, ? extends BlockItem> itemFactory,
			UFRegistry<Block> blockReg,
			UFRegistry<Item> itemReg
	) {
		T block = blockFactory.apply(properties);
		var toReturn = blockReg.register(name, () -> block);
		if (itemFactory != null) {
			Item item = itemFactory.apply(block, new Item.Properties());
			itemReg.register(name, () -> item);
		}
		return toReturn;
	}

	private static <T extends Block> UFRegistryEntry<T> simple(
			String name,
			Function<BlockBehaviour.Properties, T> factory,
			Block base,
			@Nullable UnaryOperator<BlockBehaviour.Properties> modifier,
			UFRegistry<Block> blockReg,
			UFRegistry<Item> itemReg
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
				blockReg,
				itemReg
		);
	}

	private static <T extends Block> UFRegistryEntry<T> simple(
			String name,
			Function<BlockBehaviour.Properties, T> factory,
			Block base,
			UFRegistry<Block> blockReg,
			UFRegistry<Item> itemReg
	) {
		return simple(name, factory, base, null, blockReg, itemReg);
	}

	// @formatter:off
	private static TableBlock registerTable(String n, Block b) { return simple(n, TableBlock::new, b, WOODEN_TABLE_BLOCKS, WOODEN_TABLE_ITEMS); }
	private static CoffeeTableBlock registerCoffeeTable(String n, Block b) { return simple(n, CoffeeTableBlock::new, b, WOODEN_COFFEE_TABLE_BLOCKS, WOODEN_COFFEE_TABLE_ITEMS); }
	private static ChairBlock registerChair(String n, Block b) { return simple(n, ChairBlock::new, b, CHAIR_BLOCKS, CHAIR_ITEMS); }
	private static StoolBlock registerStool(String n, Block b) { return simple(n, StoolBlock::new, b, STOOL_BLOCKS, STOOL_ITEMS); }
	private static SofaBlock registerSofa(String n, Block b) { return simple(n, SofaBlock::new, b, SOFA_BLOCKS, SOFA_ITEMS); }
	private static CurtainBlock registerCurtain(String n, Block b) { return simple(n, CurtainBlock::new, b, p -> p.sound(SoundType.WOOL), CURTAIN_BLOCKS, CURTAIN_ITEMS); }
	private static TableLampBlock registerTableLamp(String n, Block b) { return simple(n, TableLampBlock::new, b, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static CeilingLampBlock registerCeilingLamp(String n, Block b) { return simple(n, CeilingLampBlock::new, b, WOODEN_CEILING_LAMP_BLOCKS, WOODEN_CEILING_LAMP_ITEMS); }
	private static DrawerBlock registerDrawer(String n, Block b) { return simple(n, DrawerBlock::new, b, DRAWER_BLOCKS, DRAWER_ITEMS); }
	private static BenchBlock registerBench(String n, Block b) { return simple(n, BenchBlock::new, b, BENCH_BLOCKS, BENCH_ITEMS); }
	private static ShelfBlock registerShelf(String n, Block b) { return simple(n, ShelfBlock::new, b, SHELF_BLOCKS, SHELF_ITEMS); }
	private static CarvedPlanksBlock registerCarvedPlanks(String n, Block b) { return simple(n, CarvedPlanksBlock::new, b, CARVED_PLANK_BLOCKS, CARVED_PLANK_ITEMS); }
	private static OpenRiserStairBlock registerOpenRiserStair(String n, Block b) { return registerWithItem(n, OpenRiserStairBlock::new, BlockBehaviour.Properties.ofFullCopy(b).mapColor(b.defaultMapColor()), OpenRiserStairBlockItem::new, OPEN_RISER_STAIR_BLOCKS, OPEN_RISER_STAIR_ITEMS); }
	private static RailingBlock registerRailing(String n, Block b) { return simple(n, RailingBlock::new, b, RAILING_BLOCKS, RAILING_ITEMS); }
	private static BeamBlock registerBeam(String n, Block b) { return simple(n, BeamBlock::new, b, WOODEN_BEAM_BLOCKS, WOODEN_BEAM_ITEMS); }

	private static IndustrialTableBlock registerIndustrialTable(String n) { return simple(n, IndustrialTableBlock::new, Blocks.IRON_BLOCK, TABLE_BLOCKS, TABLE_ITEMS); }
	private static IndustrialTableBlock registerIndustrialTable(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IndustrialTableBlock::new, Blocks.IRON_BLOCK, modifier, TABLE_BLOCKS, TABLE_ITEMS); }
	private static IndustrialCoffeeTableBlock registerIndustrialCoffeeTable(String n) { return simple(n, IndustrialCoffeeTableBlock::new, Blocks.IRON_BLOCK, COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS); }
	private static IndustrialCoffeeTableBlock registerIndustrialCoffeeTable(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IndustrialCoffeeTableBlock::new, Blocks.IRON_BLOCK, modifier, COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS); }
	private static CeilingLampBlock registerCopperLamp(String n) { return simple(n, CopperCeilingLampBlock::new, Blocks.COPPER_BLOCK, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS); }
	private static CeilingLampBlock registerCopperLamp(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CeilingLampBlock::new, Blocks.COPPER_BLOCK, modifier, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS); }
	private static IronBeamBlock registerIronBeam(String n) { return simple(n, IronBeamBlock::new, Blocks.IRON_BLOCK, BEAM_BLOCKS, BEAM_ITEMS); }
	private static IronBeamBlock registerIronBeam(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IronBeamBlock::new, Blocks.IRON_BLOCK, modifier, BEAM_BLOCKS, BEAM_ITEMS); }
	private static DecoratedIronBeamBlock registerDecoratedIronBeam(String n) { return simple(n, DecoratedIronBeamBlock::new, Blocks.IRON_BLOCK, BEAM_BLOCKS, BEAM_ITEMS); }
	private static DecoratedIronBeamBlock registerDecoratedIronBeam(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, DecoratedIronBeamBlock::new, Blocks.IRON_BLOCK, modifier, BEAM_BLOCKS, BEAM_ITEMS); }
	private static FloorLampDecorationBatBlock registerFloorLampDecorationBatBlock(String n) { return simple(n, FloorLampDecorationBatBlock::new, Blocks.IRON_BLOCK, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationBatBlock registerFloorLampDecorationBatBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FloorLampDecorationBatBlock::new, Blocks.IRON_BLOCK, modifier, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationVillagerBlock registerFloorLampDecorationVillagerBlock(String n) { return simple(n, FloorLampDecorationVillagerBlock::new, Blocks.IRON_BLOCK, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationVillagerBlock registerFloorLampDecorationVillagerBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FloorLampDecorationVillagerBlock::new, Blocks.IRON_BLOCK, modifier, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static IronLampBlock registerIronLampBlock(String n) { return simple(n, IronLampBlock::new, Blocks.IRON_BLOCK, LAMP_BLOCKS, LAMP_ITEMS); }
	private static IronLampBlock registerIronLampBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, IronLampBlock::new, Blocks.IRON_BLOCK, modifier, LAMP_BLOCKS, LAMP_ITEMS); }
	private static SphereLampBlock registerSphereLampBlock(String n) { return simple(n, SphereLampBlock::new, Blocks.GLASS, LAMP_BLOCKS, LAMP_ITEMS); }
	private static SphereLampBlock registerSphereLampBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, SphereLampBlock::new, Blocks.GLASS, modifier, LAMP_BLOCKS, LAMP_ITEMS); }
	private static TropicalPlantBlock registerTropicalPlantBlock(String n) {
		TropicalPlantBlock block = new TropicalPlantBlock(BlockBehaviour.Properties.of());
		WallTropicalPlantBlock wallBlock = new WallTropicalPlantBlock(BlockBehaviour.Properties.of());
		BAG_BLOCKS.register(n, () -> block);
		BAG_BLOCKS.register(n + "_wall", () -> wallBlock);
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.register(n, () -> blockItem);
		return block;
	}
	private static TropicalPlantBlock registerTropicalPlantBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) {
		TropicalPlantBlock block = new TropicalPlantBlock(modifier.apply(BlockBehaviour.Properties.of()));
		WallTropicalPlantBlock wallBlock = new WallTropicalPlantBlock(modifier.apply(BlockBehaviour.Properties.of()));
		BAG_BLOCKS.register(n, () -> block);
		BAG_BLOCKS.register(n + "_wall", () -> wallBlock);
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.register(n, () -> blockItem);
		return block;
	}
	private static MushroomPatchBlock registerMushroomPatchBlock(String n) {
		MushroomPatchBlock block = new MushroomPatchBlock(BlockBehaviour.Properties.of());
		WallMushroomPatchBlock wallBlock = new WallMushroomPatchBlock(BlockBehaviour.Properties.of());
		BAG_BLOCKS.register(n, () -> block);
		BAG_BLOCKS.register(n + "_wall", () -> wallBlock);
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.register(n, () -> blockItem);
		return block;
	}
	private static MushroomPatchBlock registerMushroomPatchBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) {
		MushroomPatchBlock block = new MushroomPatchBlock(modifier.apply(BlockBehaviour.Properties.of()));
		WallMushroomPatchBlock wallBlock = new WallMushroomPatchBlock(modifier.apply(BlockBehaviour.Properties.of()));
		BAG_BLOCKS.register(n, () -> block);
		BAG_BLOCKS.register(n + "_wall", () -> wallBlock);
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.register(n, () -> blockItem);
		return block;
	}
	private static WaterPlantsLandBlock registerWaterPlantsBlock(String n) {
		WaterPlantsLandBlock block = new WaterPlantsLandBlock(BlockBehaviour.Properties.of());
		WaterPlantsBlock waterBlock = new WaterPlantsBlock(BlockBehaviour.Properties.of());
		BAG_BLOCKS.register(n, () -> block);
		BAG_BLOCKS.register(n + "_water", () -> waterBlock);
		LOOT_TABLE_BLACKLIST.add(waterBlock);
		WaterBagBlockItem blockItem = new WaterBagBlockItem(block, waterBlock, new Item.Properties());
		BAG_ITEMS.register(n, () -> blockItem);
		return block;
	}
	private static WaterPlantsLandBlock registerWaterPlantsBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) {
		WaterPlantsLandBlock block = new WaterPlantsLandBlock(modifier.apply(BlockBehaviour.Properties.of()));
		WaterPlantsBlock waterBlock = new WaterPlantsBlock(modifier.apply(BlockBehaviour.Properties.of()));
		BAG_BLOCKS.register(n, () -> block);
		BAG_BLOCKS.register(n + "_water", () -> waterBlock);
		LOOT_TABLE_BLACKLIST.add(waterBlock);
		WaterBagBlockItem blockItem = new WaterBagBlockItem(block, waterBlock, new Item.Properties());
		BAG_ITEMS.register(n, () -> blockItem);
		return block;
	}
	private static PebbleBagBlock registerPebbleBagBlock(String n) { return simple(n, PebbleBagBlock::new, Blocks.STONE, BAG_BLOCKS, BAG_ITEMS); }
	private static PebbleBagBlock registerPebbleBagBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, PebbleBagBlock::new, Blocks.STONE, modifier, BAG_BLOCKS, BAG_ITEMS); }
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
	private static LargeHangingPotBlock registerLargeHangingPotBlock(String n) { return registerWithItem(n, LargeHangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static WoodenHangingPotBlock registerWoodenHangingPotBlock(String n) { return registerWithItem(n, WoodenHangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static PosterBlock registerPosterBlock(String n) { return simple(n, PosterBlock::new, Blocks.OAK_SIGN, PROPS_BLOCKS, PROPS_ITEMS); }
	private static PosterBlock registerPosterBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, PosterBlock::new, Blocks.OAK_SIGN, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static TrashBlock registerTrashBlock(String n) { return simple(n, TrashBlock::new, Blocks.OAK_PLANKS, PROPS_BLOCKS, PROPS_ITEMS); }
	private static TrashBlock registerTrashBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TrashBlock::new, Blocks.OAK_PLANKS, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static FireHydrantBlock registerFireHydrantBlock(String n) { return simple(n, FireHydrantBlock::new, Blocks.IRON_BLOCK, FIRE_HYDRANT_BLOCKS, FIRE_HYDRANT_ITEMS); }
	private static FireHydrantBlock registerFireHydrantBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, FireHydrantBlock::new, Blocks.IRON_BLOCK, modifier, FIRE_HYDRANT_BLOCKS, FIRE_HYDRANT_ITEMS); }
	private static ManholeBlock registerManholeBlock(String n) { return simple(n, ManholeBlock::new, Blocks.IRON_TRAPDOOR, PROPS_BLOCKS, PROPS_ITEMS); }
	private static ManholeBlock registerManholeBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, ManholeBlock::new, Blocks.IRON_TRAPDOOR, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static ToolboxBlock registerToolboxBlock(String n) { return simple(n, ToolboxBlock::new, Blocks.IRON_TRAPDOOR, PROPS_BLOCKS, PROPS_ITEMS); }
	private static ToolboxBlock registerToolboxBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, ToolboxBlock::new, Blocks.IRON_TRAPDOOR, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static BarrierBlock registerBarrierBlock(String n) { return simple(n, BarrierBlock::new, Blocks.OAK_PLANKS, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static BarrierBlock registerBarrierBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BarrierBlock::new, Blocks.OAK_PLANKS, modifier, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static BlackboardMenuBlock registerBlackboardMenuBlock(String n) { return simple(n, BlackboardMenuBlock::new, Blocks.OAK_PLANKS, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static BlackboardMenuBlock registerBlackboardMenuBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BlackboardMenuBlock::new, Blocks.OAK_PLANKS, modifier, BARRIER_BLOCKS, BARRIER_ITEMS); }
	private static WallClockBlock registerWallClockBlock(String n) { return simple(n, WallClockBlock::new, Blocks.OAK_PLANKS, PROPS_BLOCKS, PROPS_ITEMS); }
	private static WallClockBlock registerWallClockBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, WallClockBlock::new, Blocks.OAK_PLANKS, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static TableLampBlock registerFloorLampBlock(String n) { return simple(n, TableLampBlock::new, Blocks.OAK_PLANKS, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static TableLampBlock registerFloorLampBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, TableLampBlock::new, Blocks.OAK_PLANKS, modifier, TABLE_LAMP_BLOCKS, TABLE_LAMP_ITEMS); }
	private static PlushBlock registerPlushBlock(String n) { return simple(n, PlushBlock::new, Blocks.WHITE_WOOL, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static PlushBlock registerPlushBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, PlushBlock::new, Blocks.WHITE_WOOL, modifier, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static CatPlushBlock registerCatPlushBlock(String n) { return simple(n, CatPlushBlock::new, Blocks.WHITE_WOOL, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static CatPlushBlock registerCatPlushBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, CatPlushBlock::new, Blocks.WHITE_WOOL, modifier, PLUSH_BLOCKS, PLUSH_ITEMS); }
	private static BroomBlock registerBroomBlock(String n) { return simple(n, BroomBlock::new, Blocks.OAK_PLANKS, PROPS_BLOCKS, PROPS_ITEMS); }
	private static BroomBlock registerBroomBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, BroomBlock::new, Blocks.OAK_PLANKS, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static RakeBlock registerRakeBlock(String n) { return simple(n, RakeBlock::new, Blocks.OAK_PLANKS, PROPS_BLOCKS, PROPS_ITEMS); }
	private static RakeBlock registerRakeBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, RakeBlock::new, Blocks.OAK_PLANKS, modifier, PROPS_BLOCKS, PROPS_ITEMS); }
	private static GraveBlock registerGraveBlock(String n) { return simple(n, GraveBlock::new, Blocks.STONE, GRAVE_BLOCKS, GRAVE_ITEMS); }
	private static GraveBlock registerGraveBlock(String n, UnaryOperator<BlockBehaviour.Properties> modifier) { return simple(n, GraveBlock::new, Blocks.STONE, modifier, GRAVE_BLOCKS, GRAVE_ITEMS); }
	// @formatter:on

	private static <T extends Item> UFRegistryEntry<T> registerItem(String name, T item, UFRegistry<Item> itemReg) {
		return itemReg.register(name, () -> item);
	}

	private static <T extends Block> UFRegistryEntry<T> registerBlock(String name, T block, UFRegistry<Block> blockReg) {
		return blockReg.register(name, () -> block);
	}

	public static @Nullable WoodSet getWoodSet(Block block) {
		return BLOCK_TO_WOODSET.get(block);
	}

	public static @Nullable DyeSet getDyeSet(Block block) {
		return BLOCK_TO_DYESET.get(block);
	}
}
