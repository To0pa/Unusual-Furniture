package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.util.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FloorLampDecorationBatBlock extends AbstractFloorLampDecorationBlock {

	public static final VoxelShape MIDDLE_SHAPE = Shapes.or(
			box(6.0F, 0.0F, 6.0F, 10.0F, 1.0F, 10.0F),
			box(4.0F, 1.0F, 4.0F, 12.0F, 9.0F, 12.0F)
	).optimize();
	public static final VoxelShape ARM_SHAPE =box(7.0F, 0.0F, -9.0F, 9.0F, 9.0F, 4.0F);
	private static final VoxelShape[][] SHAPES = createShapes();
	private static final MapCodec<FloorLampDecorationBatBlock> CODEC = simpleCodec(FloorLampDecorationBatBlock::new);

	public FloorLampDecorationBatBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(FACING, Direction.NORTH)
				.setValue(NUMBER_OF_ARMS, 0)
				.setValue(WATERLOGGED, false));
	}

	private static VoxelShape[][] createShapes() {
		VoxelShape north0 = Shapes.or(MIDDLE_SHAPE, ARM_SHAPE).optimize();

		VoxelShape north1 = Shapes.or(
				MIDDLE_SHAPE,
				VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 90),
				VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 270)
		).optimize();

		VoxelShape north2 = Shapes.or(
				MIDDLE_SHAPE,
				ARM_SHAPE,
				VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 90),
				VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 180),
				VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 270)
		).optimize();

		VoxelShape[][] shapes = new VoxelShape[3][Direction.Plane.HORIZONTAL.stream().toList().size()];

		for (Direction facing : Direction.Plane.HORIZONTAL) {
			int facingIndex = facing.get2DDataValue();

			shapes[0][facingIndex] = rotateForFacing(north0, facing);
			shapes[1][facingIndex] = rotateForFacing(north1, facing);
			shapes[2][facingIndex] = rotateForFacing(north2, facing);
		}

		return shapes;
	}

	private static VoxelShape rotateForFacing(VoxelShape shape, Direction facing) {
		return switch (facing) {
			case NORTH -> shape;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(shape, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(shape, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(shape, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	protected VoxelShape getShape(
			BlockState state,
			BlockGetter level,
			BlockPos pos,
			CollisionContext context
	) {
		int arms = state.getValue(NUMBER_OF_ARMS);
		if (arms < 0 || arms >= SHAPES.length) {
			return Shapes.block();
		}

		return SHAPES[arms][state.getValue(FACING).get2DDataValue()];
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
