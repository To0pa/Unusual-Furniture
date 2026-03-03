package net.toopa.unusual_furniture.fabric.datagen;
//? fabric {

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.toopa.unusual_furniture.common.reg.UFObjects;

import java.util.concurrent.CompletableFuture;

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
