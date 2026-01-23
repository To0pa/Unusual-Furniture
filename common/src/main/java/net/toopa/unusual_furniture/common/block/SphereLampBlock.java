package net.toopa.unusual_furniture.common.block;

import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SphereLampBlock extends AbstractLampBlock {

	public static VoxelShape SHAPE = Shapes.or(
			box(5.0F, -0.05, 4.95, 11.0F, 1.95, 10.95),
			box(6.0F, 3.95, 5.95, 10.0F, 7.95, 9.95),
			box(7.0F, 1.95, 6.95, 9.0F, 3.95, 8.95),
			box(3.0F, 1.95, 2.95, 13.0F, 10.95, 12.95),
			box(5.0F, 10.95, 4.95, 11.0F, 11.95, 10.95)
	);

	public SphereLampBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case DOWN -> SHAPE;
			case UP -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.X, 180);
			case NORTH -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.X, 90);
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.X, 270);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Z, 90);
			case EAST ->
					VoxelShapeUtils.rotateVoxelShape(VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Z, 270), Direction.Axis.Y, 180);
		};
	}
}
