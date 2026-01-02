package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface UFItemTags {

	TagKey<Item> COMMON_WATER_BUCKETS = createTag("buckets/water");

	private static TagKey<Item> createTag(String name) {
		return TagKey.create(Registries.ITEM, UnusualFurniture.id(name));
	}

	private static TagKey<Item> createCommonTag(String name) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
	}
}
