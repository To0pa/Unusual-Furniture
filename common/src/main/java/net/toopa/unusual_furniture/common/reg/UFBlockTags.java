package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public interface UFBlockTags {

	TagKey<Block> TABLE = createTag("table");
	TagKey<Block> COFFEE_TABLE = createTag("coffee_table");
	TagKey<Block> CHAIR = createTag("chair");
	TagKey<Block> STOOL = createTag("stool");
	TagKey<Block> SOFA = createTag("sofa");
	TagKey<Block> CEILING_LAMP = createTag("ceiling_lamp");
	TagKey<Block> DRAWER = createTag("drawer");
	TagKey<Block> BENCH = createTag("bench");
	TagKey<Block> CURTAIN = createTag("curtain");
	TagKey<Block> SHELF = createTag("shelf");
	TagKey<Block> CARVED_PLANKS = createTag("craved_planks");
	TagKey<Block> OPEN_RISER_STAIRS = createTag("open_riser_stairs");
	TagKey<Block> RAILING = createTag("railing");

	TagKey<Block> COMMON_WATER_BUCKETS = createTag("buckets/water");

	private static TagKey<Block> createTag(String name) {
		return TagKey.create(Registries.BLOCK, UnusualFurniture.id(name));
	}

	private static TagKey<Block> createCommonTag(String name) {
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
	}
}
