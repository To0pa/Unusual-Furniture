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

public class FloorLampDecorationBatBlock extends AbstractFloorLampDecorationBlock {

	//TODO: multiblock the arms
	public static VoxelShape MIDDLE_SHAPE = Shapes.or(
			box(6.0F, 0.0F, 6.0F, 10.0F, 1.0F, 10.0F),
			box(4.0F, 1.0F, 4.0F, 12.0F, 9.0F, 12.0F)
	).optimize();
	public static VoxelShape ARM_SHAPE = box(7.0F, 0.0F, -9.0F, 9.0F, 9.0F, 4.0F);
	private static final MapCodec<FloorLampDecorationBatBlock> CODEC = simpleCodec(FloorLampDecorationBatBlock::new);
	//TODO: Cache shapes

	public FloorLampDecorationBatBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(FACING, Direction.NORTH)
				.setValue(NUMBER_OF_ARMS, 0)
				.setValue(WATERLOGGED, false));
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		VoxelShape baseShape;

		int arms = state.getValue(NUMBER_OF_ARMS);

		if (arms == 0) {
			baseShape = Shapes.or(
					MIDDLE_SHAPE,
					ARM_SHAPE
			);
		} else if (arms == 1) {
			baseShape = Shapes.or(
					MIDDLE_SHAPE,
					VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 90),
					VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 270)
			);
		} else if (arms == 2) {
			baseShape = Shapes.or(
					MIDDLE_SHAPE,
					ARM_SHAPE,
					VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 90),
					VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 180),
					VoxelShapeUtils.rotateVoxelShape(ARM_SHAPE, Direction.Axis.Y, 270)
			);
		} else {
			return Shapes.block();
		}

		return switch (state.getValue(FACING)) {
			case NORTH -> baseShape;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(baseShape, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(baseShape, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(baseShape, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
