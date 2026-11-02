package net.toopa.unusual_furniture.fabric.datagen;

//? fabric {
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.toopa.unusual_furniture.common.reg.UFEntityTypes;

public class UFEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

	public UFEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		// Keeps the seat in a sub-level plot so Sable can move it together with the sub-level.
		getOrCreateTagBuilder(createTag("sable", "retain_in_sub_level"))
				.add(UFEntityTypes.SEAT);
	}

	private static TagKey<EntityType<?>> createTag(String modId, String name) {
		return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(modId, name));
	}
}
//?}
