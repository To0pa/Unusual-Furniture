package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.world.item.Items;

import net.toopa.unusual_furniture.common.reg.UFItemTags;

public class UFItemTagProvider extends FabricTagProvider.ItemTagProvider {

	public UFItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture, null);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		getOrCreateTagBuilder(UFItemTags.COMMON_WATER_BUCKETS)
				.addOptionalTag(ConventionalItemTags.WATER_BUCKETS);

		getOrCreateTagBuilder(UFItemTags.TROPICAL_PLANTS)
				.add(Items.SMALL_DRIPLEAF)
				.add(Items.SPORE_BLOSSOM)
				.add(Items.BIG_DRIPLEAF)
				.add(Items.JUNGLE_SAPLING);
		getOrCreateTagBuilder(UFItemTags.WATER_PLANTS)
				.add(Items.SEAGRASS)
				.add(Items.KELP)
				.add(Items.LILY_PAD)
				.add(Items.SEA_PICKLE);
	}
}
//?}
