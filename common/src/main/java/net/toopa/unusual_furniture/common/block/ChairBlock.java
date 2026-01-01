package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

public class ChairBlock extends SittableBlock {

	private static final MapCodec<ChairBlock> CODEC = simpleCodec(ChairBlock::new);
	private static final VoxelShape SHAPE = Shapes.or(
			box(2.0, 8.0, 2.0, 14.0, 11.0, 14.0),
			box(11.0, 0.0, 2.0, 14.0, 8.0, 5.0),
			box(11.0, 0.0, 11.0, 14.0, 8.0, 14.0),
			box(2.0, 11.0, 11.0, 5.0, 13.0, 14.0),
			box(11.0, 11.0, 11.0, 14.0, 13.0, 14.0),
			box(2.0, 0.0, 11.0, 5.0, 8.0, 14.0),
			box(2.0, 0.0, 2.0, 5.0, 8.0, 5.0),
			box(2.0, 13.0, 11.0, 14.0, 21.0, 14.0)
	);

	public ChairBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case NORTH -> SHAPE;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
