package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class UFCreativeTabs {

	private UFCreativeTabs() {}

	private static CreativeModeTab.Builder creativeModeTabBuilder() {
		return	//? fabric {
				CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0);
				//?} neoforge {
				//CreativeModeTab.builder();
				//?}
	}

	public static final ResourceKey<CreativeModeTab> FURNITURE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), UnusualFurniture.id("01_furniture"));
	public static final CreativeModeTab FURNITURE_TAB = creativeModeTabBuilder()
			.title(Component.translatable("item_group.unusual_furniture.unusual_furniture"))
			.icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("oak").chair()))
			.displayItems((itemDisplayParameters, output) -> {
				UFObjects.FURNITURE_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
			})
			.build();

	public static final ResourceKey<CreativeModeTab> PROPS_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), UnusualFurniture.id("02_props"));
	public static final CreativeModeTab PROPS_TAB = creativeModeTabBuilder()
			.title(Component.translatable("item_group.unusual_furniture.unusual_furniture_props"))
			.icon(() -> new ItemStack(UFObjects.HUGE_POT))
			.displayItems((itemDisplayParameters, output) -> {
				UFObjects.BAG_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				UFObjects.POT_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				output.accept(UFObjects.POSTER);
				output.accept(UFObjects.TRASH);
				UFObjects.FIRE_HYDRANT_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				output.accept(UFObjects.MANHOLE);
				output.accept(UFObjects.DECORATIVE_TOOLBOX);
				UFObjects.BARRIER_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				output.accept(UFObjects.WOODEN_CLOCK);
				UFObjects.TABLE_LAMP_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				UFObjects.PLUSH_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				output.accept(UFObjects.BROOM);
				output.accept(UFObjects.RAKE);
				UFObjects.GRAVE_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
			})
			.build();

	public static final ResourceKey<CreativeModeTab> BUILDING_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), UnusualFurniture.id("03_building"));
	public static final CreativeModeTab BUILDING_TAB = creativeModeTabBuilder()
			.title(Component.translatable("item_group.unusual_furniture.unusual_tab_2"))
			.icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("acacia").open_riser_stairs()))
			.displayItems((itemDisplayParameters, output) -> {
				UFObjects.DRAWER_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				UFObjects.OPEN_RISER_STAIR_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				UFObjects.RAILING_BLOCKS.forEachEntry((item, reLo) -> output.accept(item));
				UFObjects.WOODEN_BEAM_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				output.accept(UFObjects.DECORATED_IRON_BEAM);
				output.accept(UFObjects.IRON_BEAM);
				UFObjects.FLOOR_LAMP_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
				UFObjects.LAMP_ITEMS.forEachEntry((item, reLo) -> output.accept(item));
			})
			.build();

	public static void init() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FURNITURE_TAB_KEY, FURNITURE_TAB);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PROPS_TAB_KEY, PROPS_TAB);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BUILDING_TAB_KEY, BUILDING_TAB);
	}
}
