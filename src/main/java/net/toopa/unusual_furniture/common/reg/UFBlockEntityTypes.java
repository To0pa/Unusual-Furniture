package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.block.entity.DrawerBlockEntity;
import net.toopa.unusual_furniture.common.block.entity.WallClockBlockEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class UFBlockEntityTypes {

	private UFBlockEntityTypes() {}

	public static final BlockEntityType<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			"drawer",
			BlockEntityType.Builder.of(DrawerBlockEntity::new,
					UFObjects.DRAWER_BLOCKS.keySet()
							.toArray(new Block[0])).build(null));

	public static final BlockEntityType<WallClockBlockEntity> WALL_CLOCK_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			"wall_clock", BlockEntityType.Builder.of(WallClockBlockEntity::new,
					UFObjects.WOODEN_CLOCK).build(null));

	public static void init() {
	}
}
