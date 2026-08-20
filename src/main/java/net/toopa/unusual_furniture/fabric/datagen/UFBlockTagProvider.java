package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;

import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.reg.UFObjects;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class UFBlockTagProvider extends FabricTagProvider.BlockTagProvider {

	public UFBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		getOrCreateTagBuilder(UFBlockTags.TABLE)
				.add(UFObjects.TABLE_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.COFFEE_TABLE)
				.add(UFObjects.COFFEE_TABLE_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.CHAIR)
				.add(UFObjects.CHAIR_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.STOOL)
				.add(UFObjects.STOOL_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.SOFA)
				.add(UFObjects.SOFA_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.CEILING_LAMP)
				.add(UFObjects.CEILING_LAMP_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.DRAWER)
				.add(UFObjects.DRAWER_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.BENCH)
				.add(UFObjects.BENCH_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.CURTAIN)
				.add(UFObjects.CURTAIN_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.SHELF)
				.add(UFObjects.SHELF_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.POT)
				.add(UFObjects.POT_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.TABLE_LAMP)
				.add(UFObjects.TABLE_LAMP_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.CARVED_PLANKS)
				.add(UFObjects.CARVED_PLANK_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.OPEN_RISER_STAIRS)
				.add(UFObjects.OPEN_RISER_STAIR_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.RAILING)
				.add(UFObjects.RAILING_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.BEAM)
				.add(UFObjects.BEAM_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(UFBlockTags.FLOOR_LAMP_DECORATIONS)
				.add(UFObjects.FLOOR_LAMP_BLOCKS.keys(Block[]::new));

		getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
				.add(UFObjects.INDUSTRIAL_TABLE)
				.add(UFObjects.INDUSTRIAL_COFFEE_TABLE)
				.add(UFObjects.COPPER_CEILING_LAMP)
				.add(UFObjects.IRON_BEAM)
				.add(UFObjects.DECORATED_IRON_BEAM)
				.add(UFObjects.FLOOR_LAMP_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(UFObjects.INDUSTRIAL_TABLE)
				.add(UFObjects.INDUSTRIAL_COFFEE_TABLE)
				.add(UFObjects.COPPER_CEILING_LAMP)
				.add(UFObjects.POT_BLOCKS.stream().filter((entry) ->
								!entry.getValue().getPath().contains("wood"))
						.map(Map.Entry::getKey).toArray(Block[]::new))
				.add(UFObjects.WARNING_BARRIER)
				.add(UFObjects.ROAD_WORKS_BARRIER)
				.add(UFObjects.DANGER_BARRIER)
				.add(UFObjects.DECORATIVE_TOOLBOX)
				.add(UFObjects.IRON_LAMP)
				.add(UFObjects.SPHERE_LAMP)
				.add(UFObjects.MANHOLE)
				.add(UFObjects.FIRE_HYDRANT)
				.add(UFObjects.TRASH)
				.add(UFObjects.GRAVE_BLOCKS.keys(Block[]::new))
				.add(UFObjects.IRON_BEAM)
				.add(UFObjects.DECORATED_IRON_BEAM)
				.add(UFObjects.FLOOR_LAMP_BLOCKS.keys(Block[]::new));
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
				.add(UFObjects.WOODEN_TABLE_BLOCKS.keys(Block[]::new))
				.add(UFObjects.WOODEN_COFFEE_TABLE_BLOCKS.keys(Block[]::new))
				.add(UFObjects.CHAIR_BLOCKS.keys(Block[]::new))
				.add(UFObjects.STOOL_BLOCKS.keys(Block[]::new))
				.add(UFObjects.SOFA_BLOCKS.keys(Block[]::new))
				.add(UFObjects.WOODEN_CEILING_LAMP_BLOCKS.keys(Block[]::new))
				.add(UFObjects.DRAWER_BLOCKS.keys(Block[]::new))
				.add(UFObjects.BENCH_BLOCKS.keys(Block[]::new))
				.add(UFObjects.CURTAIN_BLOCKS.keys(Block[]::new))
				.add(UFObjects.SHELF_BLOCKS.keys(Block[]::new))
				.add(UFObjects.POT_BLOCKS.stream().filter((entry) ->
						entry.getValue().getPath().contains("wood"))
						.map(Map.Entry::getKey).toArray(Block[]::new))
				.add(UFObjects.TABLE_LAMP_BLOCKS.keys(Block[]::new))
				.add(UFObjects.CARVED_PLANK_BLOCKS.keys(Block[]::new))
				.add(UFObjects.OPEN_RISER_STAIR_BLOCKS.keys(Block[]::new))
				.add(UFObjects.RAILING_BLOCKS.keys(Block[]::new))
				.add(UFObjects.WOODEN_BEAM_BLOCKS.keys(Block[]::new))
				.add(UFObjects.BROOM)
				.add(UFObjects.RAKE);

		getOrCreateTagBuilder(createTag("ftbchunks", "interact_whitelist"))
				.addTag(UFBlockTags.CHAIR)
				.addTag(UFBlockTags.STOOL)
				.addTag(UFBlockTags.SOFA)
				.addTag(UFBlockTags.BENCH)
				.addTag(UFBlockTags.SITTABLE_BLOCKS);

		getOrCreateTagBuilder(UFBlockTags.SITTABLE_BLOCKS);

		getOrCreateTagBuilder(UFBlockTags.EMITS_DUST_PARTICLES)
				.add(UFObjects.BARRIER_BLOCKS.keys(Block[]::new))
				.add(UFObjects.FIRE_HYDRANT_BLOCKS.keys(Block[]::new))
				.add(UFObjects.PLUSH_BLOCKS.keys(Block[]::new))
				.add(UFObjects.GRAVE_BLOCKS.keys(Block[]::new))
				.add(UFObjects.DECORATIVE_TOOLBOX)
				.addTag(UFBlockTags.TABLE)
				.addTag(UFBlockTags.COFFEE_TABLE)
				.addTag(UFBlockTags.CHAIR)
				.addTag(UFBlockTags.STOOL)
				.addTag(UFBlockTags.SOFA)
				.addTag(UFBlockTags.CEILING_LAMP)
				.addTag(UFBlockTags.BENCH)
				.addTag(UFBlockTags.TABLE_LAMP)
				.addTag(UFBlockTags.SHELF)
				.addTag(UFBlockTags.POT)
				.addTag(UFBlockTags.FLOOR_LAMP_DECORATIONS);
	}


	@SuppressWarnings("deprecation")
	private ResourceKey<Block> getRes(Block block, HolderLookup.Provider wrapperLookup) {
		var lookup = wrapperLookup.lookupOrThrow(Registries.BLOCK);
		return lookup.get(block.builtInRegistryHolder().key()).orElseThrow().key();
	}

	private static TagKey<Block> createTag(String modId, String name) {
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(modId, name));
	}
}
//?}
