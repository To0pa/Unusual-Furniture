package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.block.entity.DrawerBlockEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface UFBlockEntityTypes {

	BlockEntityType<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE,
			"drawer",
			BlockEntityType.Builder.of(DrawerBlockEntity::new,
					UFObjects.DRAWER_BLOCKS.keySet()
							.toArray(new Block[0])).build(null));

	static void init() {
	}
}
