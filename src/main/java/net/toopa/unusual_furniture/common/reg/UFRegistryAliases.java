package net.toopa.unusual_furniture.common.reg;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.toopa.unusual_furniture.CommonAbstraction;
import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public final class UFRegistryAliases {

	private UFRegistryAliases() {}

	private static final List<String> WOODS = List.of(
			"oak", "spruce", "birch", "jungle", "acacia", "dark_oak",
			"mangrove", "cherry", "bamboo", "crimson", "warped"
	);

	private static final Map<String, String> BLOCK_ALIASES = blockAliases();
	private static final Map<String, String> ITEM_ALIASES = itemAliases();

	public static void addAliases() {
		addAliases(BuiltInRegistries.BLOCK, BLOCK_ALIASES);
		addAliases(BuiltInRegistries.ITEM, ITEM_ALIASES);
	}

	private static void addAliases(Registry<?> registry, Map<String, String> aliases) {
		aliases.forEach((oldId, newId) ->
				CommonAbstraction.INSTANCE.addRegistryAlias(
						registry,
						UnusualFurniture.id(oldId),
						UnusualFurniture.id(newId)
				)
		);
	}

	private static Map<String, String> blockAliases() {
		Map<String, String> aliases = new LinkedHashMap<>();

		for (String wood : WOODS) {
			alias(aliases, wood + "_celling_lamp", wood + "_ceiling_lamp");
			alias(aliases, "carved_" + wood, "carved_" + wood + "_planks");
		}
		alias(aliases, "copper_celling_lamp", "copper_ceiling_lamp");

		alias(aliases, "wall_tropical_plant", "tropical_plant_wall");
		alias(aliases, "wall_mushroom_patch", "mushroom_patch_wall");

		alias(aliases, "small_cattail", "bag_of_water_plants");
		alias(aliases, "big_cattail", "bag_of_water_plants");
		alias(aliases, "small_lily_pads", "water_plant");
		alias(aliases, "scum_plant", "water_plant");
		alias(aliases, "scum_plant_2", "water_plant");
		alias(aliases, "water_plants", "water_plant");
		alias(aliases, "water_plants_water", "water_plant");

		alias(aliases, "tall_clay_pot", "tall_terracotta_pot");
		alias(aliases, "vintage_flower_pot", "bauhaus_pot");
		alias(aliases, "hit_box_guides", "iron_beam");
		alias(aliases, "brown_curtains", "brown_curtain");
		alias(aliases, "spruce_floor_lamp", "brown_table_lamp");

		alias(aliases, "tropical_plant", "bag_of_tropical_plants");
		alias(aliases, "mushroom_patch", "bag_of_mushrooms");
		alias(aliases, "pebble_bag", "bag_of_pebbles");

		alias(aliases, "decorative_toolbox", "toolbox");
		alias(aliases, "iron_lamp", "street_lantern");
		alias(aliases, "sphere_lamp", "sphere_lantern");
		alias(aliases, "rakes", "rake");

		return Map.copyOf(aliases);
	}

	private static Map<String, String> itemAliases() {
		Map<String, String> aliases = new LinkedHashMap<>(BLOCK_ALIASES);

		// The old water plants had separate items; the new bag item is the single item for all of them.
		alias(aliases, "small_cattail", "bag_of_water_plants");
		alias(aliases, "big_cattail", "bag_of_water_plants");
		alias(aliases, "small_lily_pads", "bag_of_water_plants");
		alias(aliases, "scum_plant", "bag_of_water_plants");
		alias(aliases, "scum_plant_2", "bag_of_water_plants");
		alias(aliases, "water_plants", "bag_of_water_plants");
		alias(aliases, "water_plants_water", "bag_of_water_plants");

		// Wall plants are part of their bag item now.
		alias(aliases, "wall_tropical_plant", "bag_of_tropical_plants");
		alias(aliases, "wall_mushroom_patch", "bag_of_mushrooms");

		return Map.copyOf(aliases);
	}

	private static void alias(Map<String, String> aliases, String oldId, String newId) {
		aliases.put(oldId, newId);
	}
}
