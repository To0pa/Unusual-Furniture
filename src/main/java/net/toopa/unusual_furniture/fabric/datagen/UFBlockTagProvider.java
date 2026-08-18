package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.concurrent.CompletableFuture;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;

import net.minecraft.world.level.block.Blocks;

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
		getOrCreateTagBuilder(UFBlockTags.TABLE).add(UFObjects.INDUSTRIAL_TABLE);
		getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(UFObjects.INDUSTRIAL_TABLE);
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(UFObjects.INDUSTRIAL_TABLE);
		
		getOrCreateTagBuilder(UFBlockTags.COFFEE_TABLE).add(UFObjects.INDUSTRIAL_COFFEE_TABLE);
		getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(UFObjects.INDUSTRIAL_COFFEE_TABLE);
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(UFObjects.INDUSTRIAL_COFFEE_TABLE);
		UFObjects.WOODEN_TABLE_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.TABLE).add(block);
			getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
		});
		UFObjects.WOODEN_COFFEE_TABLE_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.COFFEE_TABLE).add(block);
			getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(block);
		});
		UFObjects.CHAIR_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.CHAIR).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.CHAIR);
		UFObjects.STOOL_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.STOOL).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.STOOL);
		UFObjects.SOFA_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.SOFA).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.SOFA);
		UFObjects.CEILING_LAMP_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.CEILING_LAMP).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.CEILING_LAMP);
		UFObjects.DRAWER_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.DRAWER).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.DRAWER);
		UFObjects.BENCH_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.BENCH).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.BENCH);
		UFObjects.CURTAIN_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.CURTAIN).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.CURTAIN);
		UFObjects.TABLE_LAMP_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.WOODEN_FLOOR_LAMP).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.WOODEN_FLOOR_LAMP);
		UFObjects.SHELF_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.SHELF).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.SHELF);
		UFObjects.CARVED_PLANK_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.CARVED_PLANKS).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.CARVED_PLANKS);
		UFObjects.OPEN_RISER_STAIR_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.OPEN_RISER_STAIRS).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.OPEN_RISER_STAIRS);
		UFObjects.RAILING_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.RAILING).add(block);
		});
		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(UFBlockTags.RAILING);

		getOrCreateTagBuilder(createTag("ftbchunks", "interact_whitelist"))
				.addTag(UFBlockTags.CHAIR)
				.addTag(UFBlockTags.STOOL)
				.addTag(UFBlockTags.SOFA)
				.addTag(UFBlockTags.BENCH);

		getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
				.add(getRes(UFObjects.IRON_BEAM, provider))
				.add(getRes(UFObjects.DECORATED_IRON_BEAM, provider))
				.add(getRes(UFObjects.IRON_LAMP, provider));

		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
				.add(getRes(UFObjects.WOODEN_HANGING_POT, provider))
				.add(getRes(UFObjects.WOODEN_BARRIER, provider))
				.add(getRes(UFObjects.WOODEN_CLOCK, provider))
				.add(getRes(UFObjects.BLACKBOARD_MENU, provider))
				.add(getRes(UFObjects.RAKE, provider))
				.add(getRes(UFObjects.BROOM, provider));

		UFObjects.POT_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			if (getRes(block, provider).registry().getPath().contains("wood")) return;
			getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
		});

		UFObjects.GRAVE_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
		});

		UFObjects.FLOOR_LAMP_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
			getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(block);
		});

		getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(getRes(UFObjects.WARNING_BARRIER, provider))
				.add(getRes(UFObjects.ROAD_WORKS_BARRIER, provider))
				.add(getRes(UFObjects.DANGER_BARRIER, provider))
				.add(getRes(UFObjects.DECORATIVE_TOOLBOX, provider))
				.add(getRes(UFObjects.IRON_LAMP, provider))
				.add(getRes(UFObjects.SPHERE_LAMP, provider))
				.add(getRes(UFObjects.MANHOLE, provider))
				.add(getRes(UFObjects.FIRE_HYDRANT, provider))
				.add(getRes(UFObjects.TRASH, provider));

		UFObjects.POT_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.FLOWER_POTS).add(block);
		});

		UFObjects.FLOOR_LAMP_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.FLOOR_LAMP_DECORATIONS).add(block);
		});

		//== dust ==

		UFObjects.BARRIER_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.EMITS_DUST_PARTICLES).add(block);
		});

		UFObjects.FIRE_HYDRANT_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.EMITS_DUST_PARTICLES).add(block);
		});

		UFObjects.PLUSH_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.EMITS_DUST_PARTICLES).add(block);
		});

		UFObjects.GRAVE_BLOCKS.stream().forEach(entry -> {
			var block = entry.get();
			getOrCreateTagBuilder(UFBlockTags.EMITS_DUST_PARTICLES).add(block);
		});

		getOrCreateTagBuilder(UFBlockTags.SITTABLE_BLOCKS);
		getOrCreateTagBuilder(UFBlockTags.EMITS_DUST_PARTICLES)
				.add(UFObjects.DECORATIVE_TOOLBOX)
				.addTag(UFBlockTags.TABLE)
				.addTag(UFBlockTags.COFFEE_TABLE)
				.addTag(UFBlockTags.CHAIR)
				.addTag(UFBlockTags.STOOL)
				.addTag(UFBlockTags.SOFA)
				.addTag(UFBlockTags.CEILING_LAMP)
				.addTag(UFBlockTags.BENCH)
				.addTag(UFBlockTags.WOODEN_FLOOR_LAMP)
				.addTag(UFBlockTags.SHELF)
				.addTag(UFBlockTags.FLOWER_POTS)
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
