package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.concurrent.CompletableFuture;

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
		//TODO: do the weird dual block items manually
		UFObjects.FURNITURE_BLOCKS.forEach(entry -> entry.forEach((block, reLo) -> {
			if (!UFObjects.LOOT_TABLE_BLACKLIST.contains(block)) {
				dropSelf(block);
			}
		}));
		UFObjects.BUILDING_BLOCKS.forEach(entry -> entry.forEach((block, reLo) -> {
			if (!UFObjects.LOOT_TABLE_BLACKLIST.contains(block)) {
				dropSelf(block);
			}
		}));
		UFObjects.PROPS_BLOCKS.forEach(entry -> entry.forEach((block, reLo) -> {
			if (!UFObjects.LOOT_TABLE_BLACKLIST.contains(block)) {
				dropSelf(block);
			}
		}));
	}
}
//?}
