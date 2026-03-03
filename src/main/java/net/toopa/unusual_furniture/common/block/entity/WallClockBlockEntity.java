package net.toopa.unusual_furniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;

public class WallClockBlockEntity extends BlockEntity {

	public WallClockBlockEntity(BlockPos pos, BlockState blockState) {
		super(UFBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY, pos, blockState);
	}
}
