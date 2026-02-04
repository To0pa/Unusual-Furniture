package net.toopa.unusual_furniture.common.reg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.BauhausPotBlock;
import net.toopa.unusual_furniture.common.block.BeamBlock;
import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.BlackstonePotBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.ChairBlock;
import net.toopa.unusual_furniture.common.block.CoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.DecoratedIronBeamBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.FireHydrantBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationBatBlock;
import net.toopa.unusual_furniture.common.block.FloorLampDecorationVillagerBlock;
import net.toopa.unusual_furniture.common.block.FudgePotBlock;
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
import net.toopa.unusual_furniture.common.block.PosterBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
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
import net.toopa.unusual_furniture.common.block.WallMushroomPatchBlock;
import net.toopa.unusual_furniture.common.block.WallTropicalPlantBlock;
import net.toopa.unusual_furniture.common.block.WaterPlantsBlock;
import net.toopa.unusual_furniture.common.block.WaterPlantsLandBlock;
import net.toopa.unusual_furniture.common.block.WoodenHangingPotBlock;
import net.toopa.unusual_furniture.common.item.BagBlockItem;
import net.toopa.unusual_furniture.common.item.DiscordItem;
import net.toopa.unusual_furniture.common.item.HangingPotBlockItem;
import net.toopa.unusual_furniture.common.item.OpenRiserStairBlockItem;
import net.toopa.unusual_furniture.common.item.WaterBagBlockItem;
import net.toopa.unusual_furniture.common.utils.DyeSet;
import net.toopa.unusual_furniture.common.utils.WoodSet;
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

	public static final List<Map<Block, ResourceLocation>> FURNITURE_BLOCKS = new ArrayList<>();
	public static final List<Map<Block, ResourceLocation>> BUILDING_BLOCKS = new ArrayList<>();
	public static final List<Map<Block, ResourceLocation>> PROPS_BLOCKS = new ArrayList<>();

	public static final List<Map<Item, ResourceLocation>> FURNITURE_ITEMS = new ArrayList<>();
	public static final List<Map<Item, ResourceLocation>> BUILDING_ITEMS = new ArrayList<>();
	public static final List<Map<Item, ResourceLocation>> PROPS_ITEMS = new ArrayList<>();
	public static final List<Map<Item, ResourceLocation>> ALL_ITEMS = new ArrayList<>();

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

	public static final Map<Block, ResourceLocation> INDUSTRIAL_TABLE_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> INDUSTRIAL_TABLE_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> INDUSTRIAL_COFFEE_TABLE_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> INDUSTRIAL_COFFEE_TABLE_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> TABLE_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> TABLE_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> COFFEE_TABLE_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> COFFEE_TABLE_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> CHAIR_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> CHAIR_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> STOOL_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> STOOL_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> SOFA_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> SOFA_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> CURTAIN_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> CURTAIN_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> CEILING_LAMP_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> CEILING_LAMP_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> DRAWER_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> DRAWER_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> BENCH_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> BENCH_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> SHELF_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> SHELF_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> CARVED_PLANK_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> CARVED_PLANK_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> OPEN_RISER_STAIR_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> OPEN_RISER_STAIR_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> RAILING_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> RAILING_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> BEAM_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> BEAM_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> FLOOR_LAMP_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> FLOOR_LAMP_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> LAMP_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> LAMP_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> BAG_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> BAG_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> POT_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> POT_ITEMS = new LinkedHashMap<>();

	// TODO: maybe for blocks that are alone we need a better way of registering them...
	public static final Map<Block, ResourceLocation> POSTER_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> POSTER_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> TRASH_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> TRASH_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> FIRE_HYDRANT_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> FIRE_HYDRANT_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> MANHOLE_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> MANHOLE_ITEMS = new LinkedHashMap<>();

	public static final Map<Block, ResourceLocation> TOOLBOX_BLOCKS = new LinkedHashMap<>();
	public static final Map<Item, ResourceLocation> TOOLBOX_ITEMS = new LinkedHashMap<>();

	/* --------------------------------------------------------------------- */
	/* Variant definitions                                                    */
	/* --------------------------------------------------------------------- */

	public record WoodDef(String name, Block base) {
	}

	public record DyeDef(String name, Block wool) {
	}

	public static final List<WoodDef> WOODS = List.of(
			new WoodDef("oak", Blocks.OAK_PLANKS),
			new WoodDef("spruce", Blocks.SPRUCE_PLANKS),
			new WoodDef("birch", Blocks.BIRCH_PLANKS),
			new WoodDef("jungle", Blocks.JUNGLE_PLANKS),
			new WoodDef("acacia", Blocks.ACACIA_PLANKS),
			new WoodDef("dark_oak", Blocks.DARK_OAK_PLANKS),
			new WoodDef("mangrove", Blocks.MANGROVE_PLANKS),
			new WoodDef("cherry", Blocks.CHERRY_PLANKS),
			new WoodDef("bamboo", Blocks.BAMBOO_PLANKS),
			new WoodDef("crimson", Blocks.CRIMSON_PLANKS),
			new WoodDef("warped", Blocks.WARPED_PLANKS)
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

	public static final DiscordItem DISCORD_ITEM =
			registerItem("discord", new DiscordItem(new Item.Properties()), FURNITURE_MAP());

	public static final Item SCREW_ITEM =
			registerItem("screw", new Item(new Item.Properties()), PROPS_MAP());

	private static Map<Item, ResourceLocation> ALL_MAP() {
		Map<Item, ResourceLocation> map = new LinkedHashMap<>();
		ALL_ITEMS.add(map);
		return map;
	}

	private static Map<Item, ResourceLocation> FURNITURE_MAP() {
		Map<Item, ResourceLocation> map = new LinkedHashMap<>();
		FURNITURE_ITEMS.add(map);
		return map;
	}

	private static Map<Item, ResourceLocation> PROPS_MAP() {
		Map<Item, ResourceLocation> map = new LinkedHashMap<>();
		PROPS_ITEMS.add(map);
		return map;
	}

	/* --------------------------------------------------------------------- */
	/* Init                                                                   */
	/* --------------------------------------------------------------------- */

	public static void init() {

		/* ---------- Wood furniture ---------- */

		for (WoodDef wood : WOODS) {
			String w = wood.name();

			TableBlock table = registerTable(w + "_table", wood.base());
			CoffeeTableBlock coffee = registerCoffeeTable(w + "_coffee_table", wood.base());
			ChairBlock chair = registerChair(w + "_chair", wood.base());
			StoolBlock stool = registerStool(w + "_stool", wood.base());
			CeilingLampBlock lamp = registerCeilingLamp(w + "_ceiling_lamp", wood.base());
			DrawerBlock drawer = registerDrawer(w + "_drawer", wood.base());
			BenchBlock bench = registerBench(w + "_bench", wood.base());
			ShelfBlock shelf = registerShelf(w + "_shelf", wood.base());
			CarvedPlanksBlock carved = registerCarvedPlanks("carved_" + w, wood.base());
			OpenRiserStairBlock stairs = registerOpenRiserStair(w + "_open_riser_stairs", wood.base());
			RailingBlock railing = registerRailing(w + "_railing", wood.base());
			BeamBlock beam = registerBeam(w + "_beam", wood.base());

			WoodSet set = new WoodSet(
					wood.base(),
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

			DyeSet set = new DyeSet(dye.wool(), sofa, curtain);
			for (Block b : set.stream().toList()) {
				BLOCK_TO_DYESET.put(b, set);
			}
		}

		/* ---------- Creative tab grouping ---------- */
		addFurniture(INDUSTRIAL_TABLE_BLOCKS, INDUSTRIAL_TABLE_ITEMS);
		addFurniture(TABLE_BLOCKS, TABLE_ITEMS);
		addFurniture(INDUSTRIAL_COFFEE_TABLE_BLOCKS, INDUSTRIAL_COFFEE_TABLE_ITEMS);
		addFurniture(COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS);
		addFurniture(CHAIR_BLOCKS, CHAIR_ITEMS);
		addFurniture(STOOL_BLOCKS, STOOL_ITEMS);
		addFurniture(SOFA_BLOCKS, SOFA_ITEMS);
		addFurniture(CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS);
		addFurniture(DRAWER_BLOCKS, DRAWER_ITEMS);
		addFurniture(BENCH_BLOCKS, BENCH_ITEMS);
		addFurniture(CURTAIN_BLOCKS, CURTAIN_ITEMS);
		addFurniture(SHELF_BLOCKS, SHELF_ITEMS);

		addBuilding(CARVED_PLANK_BLOCKS, CARVED_PLANK_ITEMS);
		addBuilding(OPEN_RISER_STAIR_BLOCKS, OPEN_RISER_STAIR_ITEMS);
		addBuilding(RAILING_BLOCKS, RAILING_ITEMS);
		addBuilding(BEAM_BLOCKS, BEAM_ITEMS);
		addBuilding(FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS);
		addBuilding(LAMP_BLOCKS, LAMP_ITEMS);

		addProps(BAG_BLOCKS, BAG_ITEMS);
		addProps(POT_BLOCKS, POT_ITEMS);
		addProps(POSTER_BLOCKS, POSTER_ITEMS);
		addProps(TRASH_BLOCKS, TRASH_ITEMS);
		addProps(FIRE_HYDRANT_BLOCKS, FIRE_HYDRANT_ITEMS);
		addProps(MANHOLE_BLOCKS, MANHOLE_ITEMS);
		addProps(TOOLBOX_BLOCKS, TOOLBOX_ITEMS);

		/* ---------- Final registry ---------- */

		registerAll(FURNITURE_BLOCKS, BuiltInRegistries.BLOCK);
		registerAll(BUILDING_BLOCKS, BuiltInRegistries.BLOCK);
		registerAll(PROPS_BLOCKS, BuiltInRegistries.BLOCK);

		registerAll(FURNITURE_ITEMS, BuiltInRegistries.ITEM);
		registerAll(BUILDING_ITEMS, BuiltInRegistries.ITEM);
		registerAll(PROPS_ITEMS, BuiltInRegistries.ITEM);
		registerAll(ALL_ITEMS, BuiltInRegistries.ITEM);
	}

	/* --------------------------------------------------------------------- */
	/* Other blocks                                                          */
	/* --------------------------------------------------------------------- */

	public static final IndustrialTableBlock INDUSTRIAL_TABLE = registerIndustrialTable("industrial_table");
	public static final IndustrialCoffeeTableBlock INDUSTRIAL_COFFEE_TABLE = registerIndustrialCoffeeTable("industrial_coffee_table");
	public static final CeilingLampBlock COPPER_CEILING_LAMP = registerCopperLamp("copper_ceiling_lamp");
	public static final DecoratedIronBeamBlock DECORATED_IRON_BEAM = registerDecoratedIronBeam("decorated_iron_beam");
	public static final IronBeamBlock IRON_BEAM = registerIronBeam("iron_beam");

	public static final FloorLampDecorationBatBlock FLOOR_LAMP_DECORATION_BAT = registerFloorLampDecorationBatBlock("floor_lamp_decoration_bat");
	public static final FloorLampDecorationVillagerBlock FLOOR_LAMP_DECORATION_VILLAGER = registerFloorLampDecorationVillagerBlock("floor_lamp_decoration_villager");
	public static final IronLampBlock IRON_LAMP = registerIronLampBlock("iron_lamp");
	public static final SphereLampBlock SPHERE_LAMP = registerSphereLampBlock("sphere_lamp");

	public static final TropicalPlantBlock TROPICAL_PLANT = registerTropicalPlantBlock("tropical_plant");
	public static final MushroomPatchBlock MUSHROOM_PATCH = registerMushroomPatchBlock("mushroom_patch");
	public static final WaterPlantsLandBlock WATER_PLANTS = registerWaterPlantsBlock("water_plants");
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

	/* --------------------------------------------------------------------- */
	/* Helpers                                                               */
	/* --------------------------------------------------------------------- */

	private static void addFurniture(Map<Block, ResourceLocation> b, Map<Item, ResourceLocation> i) {
		FURNITURE_BLOCKS.add(b);
		FURNITURE_ITEMS.add(i);
	}

	private static void addProps(Map<Block, ResourceLocation> b, Map<Item, ResourceLocation> i) {
		PROPS_BLOCKS.add(b);
		PROPS_ITEMS.add(i);
	}

	private static void addBuilding(Map<Block, ResourceLocation> b, Map<Item, ResourceLocation> i) {
		BUILDING_BLOCKS.add(b);
		BUILDING_ITEMS.add(i);
	}

	private static <T> void registerAll(List<Map<T, ResourceLocation>> maps, Registry<T> registry) {
		maps.forEach(m -> m.forEach((obj, id) -> Registry.register(registry, id, obj)));
	}

	/* --------------------------------------------------------------------- */
	/* Registration wrappers (unchanged logic)                                */
	/* --------------------------------------------------------------------- */

	private static <T extends Block> T registerWithItem(
			String name,
			Function<BlockBehaviour.Properties, T> blockFactory,
			BlockBehaviour.Properties properties,
			@Nullable BiFunction<Block, Item.Properties, ? extends BlockItem> itemFactory,
			Map<Block, ResourceLocation> blockMap,
			Map<Item, ResourceLocation> itemMap
	) {
		T block = blockFactory.apply(properties);
		blockMap.put(block, UnusualFurniture.id(name));
		if (itemFactory != null) {
			Item item = itemFactory.apply(block, new Item.Properties());
			itemMap.put(item, UnusualFurniture.id(name));
		}
		return block;
	}

	private static <T extends Block> T simple(
			String name,
			Function<BlockBehaviour.Properties, T> f,
			Block base,
			Map<Block, ResourceLocation> bm,
			Map<Item, ResourceLocation> im
	) {
		return registerWithItem(
				name,
				f,
				BlockBehaviour.Properties.ofFullCopy(base).mapColor(base.defaultMapColor()),
				BlockItem::new,
				bm,
				im
		);
	}

	// @formatter:off
	private static TableBlock registerTable(String n, Block b) { return simple(n, TableBlock::new, b, TABLE_BLOCKS, TABLE_ITEMS); }
	private static CoffeeTableBlock registerCoffeeTable(String n, Block b) { return simple(n, CoffeeTableBlock::new, b, COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS); }
	private static ChairBlock registerChair(String n, Block b) { return simple(n, ChairBlock::new, b, CHAIR_BLOCKS, CHAIR_ITEMS); }
	private static StoolBlock registerStool(String n, Block b) { return simple(n, StoolBlock::new, b, STOOL_BLOCKS, STOOL_ITEMS); }
	private static SofaBlock registerSofa(String n, Block b) { return simple(n, SofaBlock::new, b, SOFA_BLOCKS, SOFA_ITEMS); }
	private static CurtainBlock registerCurtain(String n, Block b) { return simple(n, CurtainBlock::new, b, CURTAIN_BLOCKS, CURTAIN_ITEMS); }
	private static CeilingLampBlock registerCeilingLamp(String n, Block b) { return simple(n, CeilingLampBlock::new, b, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS); }
	private static DrawerBlock registerDrawer(String n, Block b) { return simple(n, DrawerBlock::new, b, DRAWER_BLOCKS, DRAWER_ITEMS); }
	private static BenchBlock registerBench(String n, Block b) { return simple(n, BenchBlock::new, b, BENCH_BLOCKS, BENCH_ITEMS); }
	private static ShelfBlock registerShelf(String n, Block b) { return simple(n, ShelfBlock::new, b, SHELF_BLOCKS, SHELF_ITEMS); }
	private static CarvedPlanksBlock registerCarvedPlanks(String n, Block b) { return simple(n, CarvedPlanksBlock::new, b, CARVED_PLANK_BLOCKS, CARVED_PLANK_ITEMS); }
	private static OpenRiserStairBlock registerOpenRiserStair(String n, Block b) { return registerWithItem(n, OpenRiserStairBlock::new, BlockBehaviour.Properties.ofFullCopy(b).mapColor(b.defaultMapColor()), OpenRiserStairBlockItem::new, OPEN_RISER_STAIR_BLOCKS, OPEN_RISER_STAIR_ITEMS); }
	private static RailingBlock registerRailing(String n, Block b) { return simple(n, RailingBlock::new, b, RAILING_BLOCKS, RAILING_ITEMS); }
	private static BeamBlock registerBeam(String n, Block b) { return simple(n, BeamBlock::new, b, BEAM_BLOCKS, BEAM_ITEMS); }

	private static IndustrialTableBlock registerIndustrialTable(String n) { return simple(n, IndustrialTableBlock::new, Blocks.IRON_BLOCK, INDUSTRIAL_TABLE_BLOCKS, INDUSTRIAL_TABLE_ITEMS); }
	private static IndustrialCoffeeTableBlock registerIndustrialCoffeeTable(String n) { return simple(n, IndustrialCoffeeTableBlock::new, Blocks.IRON_BLOCK, INDUSTRIAL_COFFEE_TABLE_BLOCKS, INDUSTRIAL_COFFEE_TABLE_ITEMS); }
	private static CeilingLampBlock registerCopperLamp(String n) { return simple(n, CeilingLampBlock::new, Blocks.COPPER_BLOCK, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS); }
	private static IronBeamBlock registerIronBeam(String n) { return simple(n, IronBeamBlock::new, Blocks.IRON_BLOCK, BEAM_BLOCKS, BEAM_ITEMS); }
	private static DecoratedIronBeamBlock registerDecoratedIronBeam(String n) { return simple(n, DecoratedIronBeamBlock::new, Blocks.IRON_BLOCK, BEAM_BLOCKS, BEAM_ITEMS); }
	private static FloorLampDecorationBatBlock registerFloorLampDecorationBatBlock(String n) { return simple(n, FloorLampDecorationBatBlock::new, Blocks.IRON_BLOCK, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static FloorLampDecorationVillagerBlock registerFloorLampDecorationVillagerBlock(String n) { return simple(n, FloorLampDecorationVillagerBlock::new, Blocks.IRON_BLOCK, FLOOR_LAMP_BLOCKS, FLOOR_LAMP_ITEMS); }
	private static IronLampBlock registerIronLampBlock(String n) { return simple(n, IronLampBlock::new, Blocks.IRON_BLOCK, LAMP_BLOCKS, LAMP_ITEMS); }
	private static SphereLampBlock registerSphereLampBlock(String n) { return simple(n, SphereLampBlock::new, Blocks.GLASS, LAMP_BLOCKS, LAMP_ITEMS); }
	private static TropicalPlantBlock registerTropicalPlantBlock(String n) {
		TropicalPlantBlock block = new TropicalPlantBlock(BlockBehaviour.Properties.of());
		WallTropicalPlantBlock wallBlock = new WallTropicalPlantBlock(BlockBehaviour.Properties.of());
		BAG_BLOCKS.put(block, UnusualFurniture.id(n));
		BAG_BLOCKS.put(wallBlock, UnusualFurniture.id(n + "_wall"));
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.put(blockItem, UnusualFurniture.id(n));
		return block;
	}
	private static MushroomPatchBlock registerMushroomPatchBlock(String n) {
		MushroomPatchBlock block = new MushroomPatchBlock(BlockBehaviour.Properties.of());
		WallMushroomPatchBlock wallBlock = new WallMushroomPatchBlock(BlockBehaviour.Properties.of());
		BAG_BLOCKS.put(block, UnusualFurniture.id(n));
		BAG_BLOCKS.put(wallBlock, UnusualFurniture.id(n + "_wall"));
		LOOT_TABLE_BLACKLIST.add(wallBlock);
		BagBlockItem blockItem = new BagBlockItem(block, wallBlock, new Item.Properties());
		BAG_ITEMS.put(blockItem, UnusualFurniture.id(n));
		return block;
	}
	private static WaterPlantsLandBlock registerWaterPlantsBlock(String n) {
		WaterPlantsLandBlock block = new WaterPlantsLandBlock(BlockBehaviour.Properties.of());
		WaterPlantsBlock waterBlock = new WaterPlantsBlock(BlockBehaviour.Properties.of());
		BAG_BLOCKS.put(block, UnusualFurniture.id(n));
		BAG_BLOCKS.put(waterBlock, UnusualFurniture.id(n + "_water"));
		LOOT_TABLE_BLACKLIST.add(waterBlock);
		WaterBagBlockItem blockItem = new WaterBagBlockItem(block, waterBlock, new Item.Properties());
		BAG_ITEMS.put(blockItem, UnusualFurniture.id(n));
		return block;
	}
	private static PebbleBagBlock registerPebbleBagBlock(String n) { return simple(n, PebbleBagBlock::new, Blocks.GLASS, BAG_BLOCKS, BAG_ITEMS); }
	private static GreekPotBlock registerGreekPotBlock(String n) { return simple(n, GreekPotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static HugePotBlock registerHugePotBlock(String n) { return simple(n, HugePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static StonePotBlock registerStonePotBlock(String n) { return simple(n, StonePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static TallTerracottaPotBlock registerTallTerracottaPotBlock(String n) { return simple(n, TallTerracottaPotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static BauhausPotBlock registerBauhausPotBlock(String n) { return simple(n, BauhausPotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static BlackstonePotBlock registerBlackstonePotBlock(String n) { return simple(n, BlackstonePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static FudgePotBlock registerFudgePotBlock(String n) { return simple(n, FudgePotBlock::new, Blocks.DECORATED_POT, POT_BLOCKS, POT_ITEMS); }
	private static HangingPotBlock registerHangingPotBlock(String n) { return registerWithItem(n, HangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static LargeHangingPotBlock registerLargeHangingPotBlock(String n) { return registerWithItem(n, LargeHangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static WoodenHangingPotBlock registerWoodenHangingPotBlock(String n) { return registerWithItem(n, WoodenHangingPotBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DECORATED_POT).mapColor(Blocks.DECORATED_POT.defaultMapColor()), HangingPotBlockItem::new, POT_BLOCKS, POT_ITEMS); }
	private static PosterBlock registerPosterBlock(String n) { return simple(n, PosterBlock::new, Blocks.OAK_SIGN, POSTER_BLOCKS, POSTER_ITEMS); }
	private static TrashBlock registerTrashBlock(String n) { return simple(n, TrashBlock::new, Blocks.OAK_PLANKS, TRASH_BLOCKS, TRASH_ITEMS); }
	private static FireHydrantBlock registerFireHydrantBlock(String n) { return simple(n, FireHydrantBlock::new, Blocks.IRON_BLOCK, FIRE_HYDRANT_BLOCKS, FIRE_HYDRANT_ITEMS); }
	private static ManholeBlock registerManholeBlock(String n) { return simple(n, ManholeBlock::new, Blocks.IRON_TRAPDOOR, MANHOLE_BLOCKS, MANHOLE_ITEMS); }
	private static ToolboxBlock registerToolboxBlock(String n) { return simple(n, ToolboxBlock::new, Blocks.IRON_TRAPDOOR, TOOLBOX_BLOCKS, TOOLBOX_ITEMS); }
	// @formatter:on

	private static <T extends Item> T registerItem(String name, T item, Map<Item, ResourceLocation> map) {
		map.put(item, UnusualFurniture.id(name));
		return item;
	}

	public static @Nullable WoodSet getWoodSet(Block block) {
		return BLOCK_TO_WOODSET.get(block);
	}

	public static @Nullable DyeSet getDyeSet(Block block) {
		return BLOCK_TO_DYESET.get(block);
	}
}
