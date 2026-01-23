package net.toopa.unusual_furniture.common.block;

import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronBeamBlock extends BeamBlock {

	public static final VoxelShape SHAPE = box(0.0F, 6.0F, 6.0F, 16.0F, 10.0F, 10.0F);

	public IronBeamBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		return switch (blockState.getValue(AXIS)) {
			case X -> SHAPE;
			case Y -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Z, 90);
			case Z -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 90);
		};
	}
}
