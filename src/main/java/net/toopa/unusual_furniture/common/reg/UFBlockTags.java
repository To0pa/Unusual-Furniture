package net.toopa.unusual_furniture.common.reg;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.toopa.unusual_furniture.common.UnusualFurniture;

public final class UFBlockTags {

	public UFBlockTags() {}

	public static final TagKey<Block> TABLE = createTag("table");
	public static final TagKey<Block> COFFEE_TABLE = createTag("coffee_table");
	public static final TagKey<Block> CHAIR = createTag("chair");
	public static final TagKey<Block> STOOL = createTag("stool");
	public static final TagKey<Block> SOFA = createTag("sofa");
	public static final TagKey<Block> CEILING_LAMP = createTag("ceiling_lamp");
	public static final TagKey<Block> DRAWER = createTag("drawer");
	public static final TagKey<Block> BENCH = createTag("bench");
	public static final TagKey<Block> CURTAIN = createTag("curtain");
	public static final TagKey<Block> SHELF = createTag("shelf");
	public static final TagKey<Block> CARVED_PLANKS = createTag("craved_planks");
	public static final TagKey<Block> OPEN_RISER_STAIRS = createTag("open_riser_stairs");
	public static final TagKey<Block> RAILING = createTag("railing");

	public static final TagKey<Block> COMMON_WATER_BUCKETS = createTag("buckets/water");

	private static TagKey<Block> createTag(String name) {
		return TagKey.create(Registries.BLOCK, UnusualFurniture.id(name));
	}

	private static TagKey<Block> createCommonTag(String name) {
		return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
	}
}
