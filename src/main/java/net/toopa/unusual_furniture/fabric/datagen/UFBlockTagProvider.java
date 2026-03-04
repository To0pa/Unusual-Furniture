package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.concurrent.CompletableFuture;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;

import net.toopa.unusual_furniture.common.UnusualFurniture;
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
		UFObjects.INDUSTRIAL_TABLE_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.TABLE).add(getRes(block, provider));
			tag(BlockTags.NEEDS_STONE_TOOL).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(getRes(block, provider));
		});
		UFObjects.INDUSTRIAL_COFFEE_TABLE_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.COFFEE_TABLE).add(getRes(block, provider));
			tag(BlockTags.NEEDS_STONE_TOOL).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(getRes(block, provider));
		});
		UFObjects.TABLE_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.TABLE).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.COFFEE_TABLE_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.COFFEE_TABLE).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.CHAIR_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.CHAIR).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.STOOL_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.STOOL).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.SOFA_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.SOFA).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.CEILING_LAMP_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.CEILING_LAMP).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.DRAWER_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.DRAWER).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.BENCH).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.CURTAIN).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_HOE).add(getRes(block, provider));
		});
		UFObjects.SHELF_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.SHELF).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.CARVED_PLANK_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.CARVED_PLANKS).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.OPEN_RISER_STAIR_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.OPEN_RISER_STAIRS).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});
		UFObjects.RAILING_BLOCKS.forEach((block, reLo) -> {
			tag(UFBlockTags.RAILING).add(getRes(block, provider));
			tag(BlockTags.MINEABLE_WITH_AXE).add(getRes(block, provider));
		});

		tag(createTag("ftbchunks", "interact_whitelist"))
				.addTag(UFBlockTags.CHAIR)
				.addTag(UFBlockTags.STOOL)
				.addTag(UFBlockTags.SOFA)
				.addTag(UFBlockTags.BENCH);

		tag(BlockTags.NEEDS_STONE_TOOL)
				.add(getRes(UFObjects.IRON_BEAM, provider))
				.add(getRes(UFObjects.DECORATED_IRON_BEAM, provider))
				.add(getRes(UFObjects.IRON_LAMP, provider));

		tag(BlockTags.MINEABLE_WITH_AXE)
				.add(getRes(UFObjects.WOODEN_HANGING_POT, provider))
				.add(getRes(UFObjects.WOODEN_BARRIER, provider))
				.add(getRes(UFObjects.WOODEN_CLOCK, provider))
				.add(getRes(UFObjects.BLACKBOARD_MENU, provider))
				.add(getRes(UFObjects.SPRUCE_FLOOR_LAMP, provider))
				.add(getRes(UFObjects.RAKE, provider))
				.add(getRes(UFObjects.BROOM, provider));

		UFObjects.POT_BLOCKS.forEach((block, reLo) -> {
			if (getRes(block, provider).registry().getPath().contains("wood")) return;
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(getRes(block, provider));
		});

		UFObjects.GRAVE_BLOCKS.forEach((block, reLo) -> {
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(getRes(block, provider));
		});

		UFObjects.FLOOR_LAMP_BLOCKS.forEach((block, reLo) -> {
			tag(BlockTags.MINEABLE_WITH_PICKAXE).add(getRes(block, provider));
			tag(BlockTags.NEEDS_STONE_TOOL).add(getRes(block, provider));
		});

		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(getRes(UFObjects.WARNING_BARRIER, provider))
				.add(getRes(UFObjects.ROAD_WORKS_BARRIER, provider))
				.add(getRes(UFObjects.DANGER_BARRIER, provider))
				.add(getRes(UFObjects.DECORATIVE_TOOLBOX, provider))
				.add(getRes(UFObjects.IRON_LAMP, provider))
				.add(getRes(UFObjects.SPHERE_LAMP, provider))
				.add(getRes(UFObjects.MANHOLE, provider))
				.add(getRes(UFObjects.FIRE_HYDRANT, provider))
				.add(getRes(UFObjects.TRASH, provider));
	}

	private ResourceKey<Block> getRes(Block block, HolderLookup.Provider wrapperLookup) {
		var lookup = wrapperLookup.lookupOrThrow(Registries.BLOCK);
		return lookup.get(block.builtInRegistryHolder().key()).orElseThrow().key();
	}

	private static TagKey<Block> createTag(String modId, String name) {
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(modId, name));
	}
}
//?}
