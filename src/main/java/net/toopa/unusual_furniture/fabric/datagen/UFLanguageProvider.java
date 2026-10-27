package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class UFLanguageProvider extends FabricLanguageProvider {

	private static final Set<String> LOWERCASE_WORDS = Set.of(
			"a", "an", "and", "for", "from", "in", "of", "on", "the", "to", "with"
	);

	protected UFLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(
			HolderLookup.Provider registryLookup,
			TranslationBuilder translationBuilder
	) {
		translationBuilder.add("item_group.unusual_furniture.unusual_furniture", "§f\ue800§r - Furniture");
		translationBuilder.add("item_group.unusual_furniture.unusual_furniture_props", "§f\ue800§r - Props");
		translationBuilder.add("item_group.unusual_furniture.unusual_tab_2", "§f\ue800§r - Building Blocks");

		translationBuilder.add("gui.unusual_furniture.drawer_gui.label_inventory", "Inventory");

		translationBuilder.add("subtitles.squeak", "squeak");

		// These blocks intentionally share display names, so their names cannot be generated from the registry id.
		Map<String, String> blockOverrides = Map.of(
				"floor_lamp_decoration_villager", "Floor Lamp Decoration",
				"floor_lamp_decoration_bat", "Floor Lamp Decoration",
				"grave_broken", "Grave",
				"grave_skeleton", "Grave",
				"grave_creeper", "Grave"
		);

		// Blocks with appendHoverText resolve these dynamically from their description id.
		Map<String, String> blockDescriptions = Map.ofEntries(
				Map.entry("bamboo_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("jungle_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("mangrove_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("dark_oak_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("oak_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("crimson_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("birch_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("spruce_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("acacia_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("warped_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("cherry_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("copper_ceiling_lamp", "§7Right-click with a Wrench to toggle redstone mode"),
				Map.entry("toolbox", "§7Right-click to open"),
				Map.entry("manhole", "§7Right-click to open"),
				Map.entry("poster", "§7Has 7 random variations"),
				Map.entry("bag_of_water_plants", "§7Placeable on dirt and water"),
				Map.entry("bag_of_tropical_plants", "§7Placeable on dirt, pots and walls"),
				Map.entry("bag_of_mushrooms", "§7Placeable on dirt, pots and walls"),
				Map.entry("bag_of_pebbles", "§7Placeable on dirt, pots and walls"),
				Map.entry("street_lantern", "§7Reacts to the day cycle"),
				Map.entry("sphere_lantern", "§7Reacts to the day cycle"),
				Map.entry("cow_plush", "§7Mystery Crate item"),
				Map.entry("cat_plush", "§7Mystery Crate item"),
				Map.entry("pig_plush", "§7Mystery Crate item"),
				Map.entry("floor_lamp_decoration_bat", "§7Right-click to change the number of arms"),
				Map.entry("floor_lamp_decoration_villager", "§7Right-click to change the number of arms"),
				Map.entry("broom", "§7Right-click to change position"),
				Map.entry("rake", "§7Right-click to change position"),
				Map.entry("grave_broken", "§7Broken"),
				Map.entry("grave_skeleton", "§7Skeleton"),
				Map.entry("grave_creeper", "§7Creeper")
		);

		BuiltInRegistries.BLOCK.forEach(block -> {
			var id = BuiltInRegistries.BLOCK.getKey(block);

			if (!id.getNamespace().equals("unusual_furniture")) {
				return;
			}

			String path = id.getPath();
			String name = blockOverrides.getOrDefault(
					path,
					capitalizeString(path.replace("_", " "))
			);

			translationBuilder.add(block, name);

			String description = blockDescriptions.get(path);
			if (description == null && path.endsWith("_table_lamp")) {
				description = "§7Stackable";
			}

			if (description != null) {
				translationBuilder.add(
						"block.unusual_furniture." + path + ".description_0",
						description
				);
			}
		});

		BuiltInRegistries.ITEM.forEach(item -> {
			var id = BuiltInRegistries.ITEM.getKey(item);

			if (!id.getNamespace().equals("unusual_furniture")
					|| item instanceof net.minecraft.world.item.BlockItem) {
				return;
			}

			String path = id.getPath();
			translationBuilder.add(item, capitalizeString(path.replace("_", " ")));
		});
	}

	private String capitalizeString(String string) {
		String[] words = string.toLowerCase(Locale.getDefault()).split(" ");
		StringBuilder result = new StringBuilder(string.length());

		for (int i = 0; i < words.length; i++) {
			String word = words[i];

			if (i > 0 && LOWERCASE_WORDS.contains(word)) {
				result.append(word);
			} else if (!word.isEmpty()) {
				result.append(Character.toUpperCase(word.charAt(0)))
						.append(word.substring(1));
			}

			if (i < words.length - 1) {
				result.append(' ');
			}
		}

		return result.toString();
	}
}
//?}
