package net.toopa.unusual_furniture.common.reg;

import net.minecraft.world.level.block.entity.BlockEntityType;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.toopa.unusual_furniture.platform.UFRegistries;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;

public final class UFCreativeTabs {

	public static final UFRegistry<CreativeModeTab> CREATIVE_MODE_TABS = UFRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, UnusualFurniture.MOD_ID);

	private UFCreativeTabs() {}

	private static CreativeModeTab.Builder creativeModeTabBuilder() {
		return  //? fabric {
				CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0);
		        //?} neoforge {
		        /*CreativeModeTab.builder();
		         *///?}
	}

	public static final UFRegistryEntry<CreativeModeTab> FURNITURE_TAB = CREATIVE_MODE_TABS.register("01_furniture", () -> creativeModeTabBuilder()
			.title(Component.translatable("item_group.unusual_furniture.unusual_furniture"))
			.icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("oak").chair()))
			.displayItems((itemDisplayParameters, output) -> {
				UFObjects.FURNITURE_ITEMS.stream().forEach((entry) -> output.accept(entry.get()));
			})
			.build());

	public static final UFRegistryEntry<CreativeModeTab> PROPS_TAB = CREATIVE_MODE_TABS.register("02_props", () -> creativeModeTabBuilder()
			.title(Component.translatable("item_group.unusual_furniture.unusual_furniture_props"))
			.icon(() -> new ItemStack(UFObjects.HUGE_POT))
			.displayItems((itemDisplayParameters, output) -> {
				UFObjects.PROPS_ITEMS.stream().forEach((entry) -> output.accept(entry.get()));
			})
			.build());

	public static final UFRegistryEntry<CreativeModeTab> BUILDING_TAB = CREATIVE_MODE_TABS.register("01_furniture", () -> creativeModeTabBuilder()
			.title(Component.translatable("item_group.unusual_furniture.unusual_tab_2"))
			.icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("acacia").open_riser_stairs()))
			.displayItems((itemDisplayParameters, output) -> {
				UFObjects.BUILDING_ITEMS.stream().forEach((entry) -> output.accept(entry.get()));
			})
			.build());

	public static void init() {
		CREATIVE_MODE_TABS.init();
	}
}
