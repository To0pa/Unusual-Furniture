package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.toopa.unusual_furniture.common.block.properties.ModularCarvedPlanksProperty;

public class CarvedPlanksBlock extends RotatedPillarBlock {

	public static final EnumProperty<ModularCarvedPlanksProperty> SHAPE =
			EnumProperty.create("shape", ModularCarvedPlanksProperty.class);

	public CarvedPlanksBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(AXIS, Direction.Axis.Y)
				.setValue(SHAPE, ModularCarvedPlanksProperty.SINGLE));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SHAPE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		return state.setValue(SHAPE, getShape(state, context.getLevel(), context.getClickedPos()));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (direction.getAxis() == state.getValue(AXIS)) {
			return state.setValue(SHAPE, getShape(state, level, pos));
		}
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	public ModularCarvedPlanksProperty getShape(BlockState state, BlockGetter level, BlockPos pos) {
		Direction.Axis axis = state.getValue(AXIS);

		Direction negative = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.NEGATIVE);
		Direction positive = Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE);

		BlockState prev = level.getBlockState(pos.relative(negative));
		BlockState next = level.getBlockState(pos.relative(positive));

		boolean connectPrev = connectsTo(state, prev);
		boolean connectNext = connectsTo(state, next);

		if (!connectPrev && !connectNext) {
			return ModularCarvedPlanksProperty.SINGLE;
		} else if (!connectPrev) {
			return ModularCarvedPlanksProperty.BOTTOM;
		} else if (!connectNext) {
			return ModularCarvedPlanksProperty.TOP;
		} else {
			return ModularCarvedPlanksProperty.MIDDLE;
		}
	}

	private boolean connectsTo(BlockState self, BlockState other) {
		return other.is(this) && other.getValue(AXIS) == self.getValue(AXIS);
	}
}
