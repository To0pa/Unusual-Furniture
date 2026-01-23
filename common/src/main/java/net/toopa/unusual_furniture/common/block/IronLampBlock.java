package net.toopa.unusual_furniture.common.block;

import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronLampBlock extends AbstractLampBlock {

	public static VoxelShape FLOOR_SHAPE = box(6.0F, 0.0F, 6.0F, 10.0F, 6.0F, 10.0F);
	public static VoxelShape BOTTOM_SHAPE = box(6.0F, 4.0F, 6.0F, 10.0F, 4.0F, 10.0F);
	public static VoxelShape WALL_SHAPE = Shapes.or(
			box(6.0F, 4.0F, 8.0F, 10.0F, 6.0F, 10.0F),
			box(6.0F, 2.0F, 10.0F, 10.0F, 6.0F, 16.0F)
	);
	public static VoxelShape SHAPE = Shapes.or(
			box(4.0F, 6.0F, 4.0F, 12.0F, 14.0F, 12.0F),
			box(3.0F, 14.0F, 3.0F, 13.0F, 16.0F, 13.0F)
	);

	public IronLampBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case DOWN -> Shapes.or(SHAPE, FLOOR_SHAPE);
			case UP -> Shapes.or(SHAPE, BOTTOM_SHAPE);
			case NORTH -> Shapes.or(SHAPE, VoxelShapeUtils.rotateVoxelShape(WALL_SHAPE, Direction.Axis.Y, 180));
			case SOUTH -> Shapes.or(SHAPE, WALL_SHAPE);
			case WEST -> Shapes.or(SHAPE, VoxelShapeUtils.rotateVoxelShape(WALL_SHAPE, Direction.Axis.Y, 90));
			case EAST -> Shapes.or(SHAPE, VoxelShapeUtils.rotateVoxelShape(WALL_SHAPE, Direction.Axis.Y, 270));
		};
	}

	@Override
	public BlockState updateShape(BlockState state, Direction changedSide, BlockState changedState, LevelAccessor level, BlockPos pos, BlockPos changedPos) {
		Direction facing = state.getValue(FACING);

		if (changedSide == facing && !changedState.isFaceSturdy(level, changedPos, changedSide.getOpposite())) {
			return level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP) ? state.setValue(FACING, Direction.DOWN) : state.setValue(FACING, Direction.UP);
		}

		if (changedSide == Direction.DOWN && changedState.isFaceSturdy(level, changedPos, Direction.UP)) {
			return state.setValue(FACING, Direction.DOWN);
		}

		return super.updateShape(state, changedSide, changedState, level, pos, changedPos);
	}
}
