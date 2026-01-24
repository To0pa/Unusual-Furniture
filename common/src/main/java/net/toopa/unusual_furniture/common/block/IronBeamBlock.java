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
	private static final VoxelShape[] SHAPES = new VoxelShape[3];

	public IronBeamBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
		return SHAPES[state.getValue(AXIS).ordinal()];
	}

	static {
		SHAPES[Direction.Axis.X.ordinal()] = SHAPE;
		SHAPES[Direction.Axis.Y.ordinal()] =
				VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Z, 90);
		SHAPES[Direction.Axis.Z.ordinal()] =
				VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 90);
	}
}
