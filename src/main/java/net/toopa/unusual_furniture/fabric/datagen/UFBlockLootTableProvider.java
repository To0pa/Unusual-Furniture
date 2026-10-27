package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFObjects;

import net.minecraft.core.HolderLookup;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class UFBlockLootTableProvider extends FabricBlockLootTableProvider {

	protected UFBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		Block mushroomPatchWall = BuiltInRegistries.BLOCK.get(
				UnusualFurniture.id("mushroom_patch_wall")
		);
		Block tropicalPlantWall = BuiltInRegistries.BLOCK.get(
				UnusualFurniture.id("tropical_plant_wall")
		);
		Block waterPlantsWater = BuiltInRegistries.BLOCK.get(
				UnusualFurniture.id("water_plant")
		);

		Item mushroomPatchItem = BuiltInRegistries.ITEM.get(
				UnusualFurniture.id("bag_of_mushrooms")
		);
		Item tropicalPlantItem = BuiltInRegistries.ITEM.get(
				UnusualFurniture.id("bag_of_tropical_plants")
		);
		Item waterPlantsItem = BuiltInRegistries.ITEM.get(
				UnusualFurniture.id("bag_of_water_plants")
		);

		Set<Block> dualBlocks = Set.of(
				UFObjects.MUSHROOM_PATCH,
				mushroomPatchWall,
				UFObjects.TROPICAL_PLANT,
				tropicalPlantWall,
				UFObjects.WATER_PLANTS,
				waterPlantsWater
		);

		dropOther(UFObjects.MUSHROOM_PATCH, mushroomPatchItem);
		dropOther(mushroomPatchWall, mushroomPatchItem);

		dropOther(UFObjects.TROPICAL_PLANT, tropicalPlantItem);
		dropOther(tropicalPlantWall, tropicalPlantItem);

		dropOther(UFObjects.WATER_PLANTS, waterPlantsItem);
		dropOther(waterPlantsWater, waterPlantsItem);

		UFObjects.BLOCKS.forEachEntry((block, id) -> {
			if (!dualBlocks.contains(block)
					&& !UFObjects.LOOT_TABLE_BLACKLIST.contains(block)) {
				dropSelf(block);
			}
		});
	}
}
//?}
