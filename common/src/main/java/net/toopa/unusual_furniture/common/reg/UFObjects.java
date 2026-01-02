package net.toopa.unusual_furniture.common.reg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.BeamBlock;
import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.ChairBlock;
import net.toopa.unusual_furniture.common.block.CoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.IndustrialCoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.IndustrialTableBlock;
import net.toopa.unusual_furniture.common.block.OpenRiserStairBlock;
import net.toopa.unusual_furniture.common.block.RailingBlock;
import net.toopa.unusual_furniture.common.block.ShelfBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;
import net.toopa.unusual_furniture.common.block.StoolBlock;
import net.toopa.unusual_furniture.common.block.TableBlock;
import net.toopa.unusual_furniture.common.item.DiscordItem;
import net.toopa.unusual_furniture.common.item.OpenRiserStairBlockItem;
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

public interface UFObjects {

	/* --------------------------------------------------------------------- */
	/* Creative-tab grouping                                                  */
	/* --------------------------------------------------------------------- */

	List<Map<Block, ResourceLocation>> FURNITURE_BLOCKS = new ArrayList<>();
	List<Map<Block, ResourceLocation>> BUILDING_BLOCKS = new ArrayList<>();
	List<Map<Block, ResourceLocation>> PROPS_BLOCKS = new ArrayList<>();

	List<Map<Item, ResourceLocation>> FURNITURE_ITEMS = new ArrayList<>();
	List<Map<Item, ResourceLocation>> BUILDING_ITEMS = new ArrayList<>();
	List<Map<Item, ResourceLocation>> PROPS_ITEMS = new ArrayList<>();
	List<Map<Item, ResourceLocation>> ALL_ITEMS = new ArrayList<>();

	/* --------------------------------------------------------------------- */
	/* Variant lookup                                                         */
	/* --------------------------------------------------------------------- */

	Map<Block, WoodSet> BLOCK_TO_WOODSET = new LinkedHashMap<>();
	Map<Block, DyeSet> BLOCK_TO_DYESET = new LinkedHashMap<>();

	Map<String, WoodSet> WOOD_SETS = new LinkedHashMap<>();

	/* --------------------------------------------------------------------- */
	/* Per-type registries                                                    */
	/* --------------------------------------------------------------------- */

	Map<Block, ResourceLocation> INDUSTRIAL_TABLE_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> INDUSTRIAL_TABLE_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> INDUSTRIAL_COFFEE_TABLE_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> INDUSTRIAL_COFFEE_TABLE_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> TABLE_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> TABLE_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> COFFEE_TABLE_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> COFFEE_TABLE_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> CHAIR_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> CHAIR_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> STOOL_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> STOOL_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> SOFA_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> SOFA_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> CURTAIN_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> CURTAIN_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> CEILING_LAMP_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> CEILING_LAMP_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> DRAWER_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> DRAWER_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> BENCH_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> BENCH_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> SHELF_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> SHELF_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> CARVED_PLANK_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> CARVED_PLANK_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> OPEN_RISER_STAIR_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> OPEN_RISER_STAIR_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> RAILING_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> RAILING_ITEMS = new LinkedHashMap<>();

	Map<Block, ResourceLocation> BEAM_BLOCKS = new LinkedHashMap<>();
	Map<Item, ResourceLocation> BEAM_ITEMS = new LinkedHashMap<>();

	/* --------------------------------------------------------------------- */
	/* Variant definitions                                                    */
	/* --------------------------------------------------------------------- */

	record WoodDef(String name, Block base) {
	}

	record DyeDef(String name, Block wool) {
	}

	List<WoodDef> WOODS = List.of(
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

	List<DyeDef> DYES = List.of(
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

	DiscordItem DISCORD_ITEM =
			registerItem("discord", new DiscordItem(new Item.Properties()), ALL_MAP());

	private static Map<Item, ResourceLocation> ALL_MAP() {
		Map<Item, ResourceLocation> map = new LinkedHashMap<>();
		ALL_ITEMS.add(map);
		return map;
	}

	/* --------------------------------------------------------------------- */
	/* Init                                                                   */
	/* --------------------------------------------------------------------- */

	static void init() {

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

		registerIndustrialTable("industrial_table");
		registerIndustrialCoffeeTable("industrial_coffee_table");
		registerCopperLamp("copper_ceiling_lamp");

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
	/* Helpers                                                                */
	/* --------------------------------------------------------------------- */

	private static void addFurniture(Map<Block, ResourceLocation> b, Map<Item, ResourceLocation> i) {
		FURNITURE_BLOCKS.add(b);
		FURNITURE_ITEMS.add(i);
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

	private static TableBlock registerTable(String n, Block b) {return simple(n, TableBlock::new, b, TABLE_BLOCKS, TABLE_ITEMS);}

	private static CoffeeTableBlock registerCoffeeTable(String n, Block b) {return simple(n, CoffeeTableBlock::new, b, COFFEE_TABLE_BLOCKS, COFFEE_TABLE_ITEMS);}

	private static ChairBlock registerChair(String n, Block b) {return simple(n, ChairBlock::new, b, CHAIR_BLOCKS, CHAIR_ITEMS);}

	private static StoolBlock registerStool(String n, Block b) {return simple(n, StoolBlock::new, b, STOOL_BLOCKS, STOOL_ITEMS);}

	private static SofaBlock registerSofa(String n, Block b) {return simple(n, SofaBlock::new, b, SOFA_BLOCKS, SOFA_ITEMS);}

	private static CurtainBlock registerCurtain(String n, Block b) {return simple(n, CurtainBlock::new, b, CURTAIN_BLOCKS, CURTAIN_ITEMS);}

	private static CeilingLampBlock registerCeilingLamp(String n, Block b) {return simple(n, CeilingLampBlock::new, b, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS);}

	private static DrawerBlock registerDrawer(String n, Block b) {return simple(n, DrawerBlock::new, b, DRAWER_BLOCKS, DRAWER_ITEMS);}

	private static BenchBlock registerBench(String n, Block b) {return simple(n, BenchBlock::new, b, BENCH_BLOCKS, BENCH_ITEMS);}

	private static ShelfBlock registerShelf(String n, Block b) {return simple(n, ShelfBlock::new, b, SHELF_BLOCKS, SHELF_ITEMS);}

	private static CarvedPlanksBlock registerCarvedPlanks(String n, Block b) {return simple(n, CarvedPlanksBlock::new, b, CARVED_PLANK_BLOCKS, CARVED_PLANK_ITEMS);}

	private static OpenRiserStairBlock registerOpenRiserStair(String n, Block b) {return registerWithItem(n, OpenRiserStairBlock::new, BlockBehaviour.Properties.ofFullCopy(b).mapColor(b.defaultMapColor()), OpenRiserStairBlockItem::new, OPEN_RISER_STAIR_BLOCKS, OPEN_RISER_STAIR_ITEMS);}

	private static RailingBlock registerRailing(String n, Block b) {return simple(n, RailingBlock::new, b, RAILING_BLOCKS, RAILING_ITEMS);}

	private static BeamBlock registerBeam(String n, Block b) {return simple(n, BeamBlock::new, b, BEAM_BLOCKS, BEAM_ITEMS);}

	private static IndustrialTableBlock registerIndustrialTable(String n) {return simple(n, IndustrialTableBlock::new, Blocks.IRON_BLOCK, INDUSTRIAL_TABLE_BLOCKS, INDUSTRIAL_TABLE_ITEMS);}

	private static IndustrialCoffeeTableBlock registerIndustrialCoffeeTable(String n) {return simple(n, IndustrialCoffeeTableBlock::new, Blocks.IRON_BLOCK, INDUSTRIAL_COFFEE_TABLE_BLOCKS, INDUSTRIAL_COFFEE_TABLE_ITEMS);}

	private static CeilingLampBlock registerCopperLamp(String n) {return simple(n, CeilingLampBlock::new, Blocks.COPPER_BLOCK, CEILING_LAMP_BLOCKS, CEILING_LAMP_ITEMS);}

	private static <T extends Item> T registerItem(String name, T item, Map<Item, ResourceLocation> map) {
		map.put(item, UnusualFurniture.id(name));
		return item;
	}

	static @Nullable WoodSet getWoodSet(Block block) {
		return BLOCK_TO_WOODSET.get(block);
	}

	static @Nullable DyeSet getDyeSet(Block block) {
		return BLOCK_TO_DYESET.get(block);
	}
}
