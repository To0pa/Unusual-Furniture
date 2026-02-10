package net.toopa.unusual_furniture.common.block.entity;

import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WallClockBlockEntity extends BlockEntity {

	public WallClockBlockEntity(BlockPos pos, BlockState blockState) {
		super(UFBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY, pos, blockState);
	}
}
