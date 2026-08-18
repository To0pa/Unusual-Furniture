package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.entity.DrawerBlockEntity;
import net.toopa.unusual_furniture.common.block.entity.WallClockBlockEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.toopa.unusual_furniture.platform.UFRegistries;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;

public final class UFBlockEntityTypes {

	public static final UFRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = UFRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, UnusualFurniture.MOD_ID);

	private UFBlockEntityTypes() {}

	public static final UFRegistryEntry<BlockEntityType<DrawerBlockEntity>> DRAWER_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
			"drawer",
			() -> BlockEntityType.Builder.of(
					DrawerBlockEntity::new,
					UFObjects.DRAWER_BLOCKS.stream()
							.map(UFRegistryEntry::get)
							.toArray(Block[]::new)
			).build(null));

	public static final UFRegistryEntry<BlockEntityType<WallClockBlockEntity>> WALL_CLOCK_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
			"wall_clock", () -> BlockEntityType.Builder.of(WallClockBlockEntity::new,
					UFObjects.WOODEN_CLOCK).build(null));

	public static void init() {
		BLOCK_ENTITY_TYPES.init();
	}
}
