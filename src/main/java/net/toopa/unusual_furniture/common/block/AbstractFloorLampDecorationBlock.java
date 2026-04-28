package net.toopa.unusual_furniture.common.block;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.toopa.unusual_furniture.common.reg.UFObjects;
import org.jspecify.annotations.Nullable;

public abstract class AbstractFloorLampDecorationBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	private static final int MAX_NUMBER_OF_ARMS = 2;
	// 0 = 1 arm, 1 = 2 arms, 2 = 4 arms
	public static final IntegerProperty NUMBER_OF_ARMS = IntegerProperty.create("number_of_arms", 0, MAX_NUMBER_OF_ARMS);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public AbstractFloorLampDecorationBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(FACING, Direction.NORTH)
				.setValue(NUMBER_OF_ARMS, 0)
				.setValue(WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, NUMBER_OF_ARMS, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		super.setPlacedBy(level, pos, state, entity, stack);

		if (!level.isClientSide) {
			int arms = state.getValue(NUMBER_OF_ARMS);
			if (canPlaceArms(level, pos, state, arms)) {
				placeArms(level, pos, state, arms);
			}
		}
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			removeArms(level, pos);
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("block.unusual_furniture.floor_lamp_decoration.description_0"));
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		int nextArms = state.getValue(NUMBER_OF_ARMS) + 1;
		if (nextArms > MAX_NUMBER_OF_ARMS) nextArms = 0;

		if (!level.isClientSide) {
			if (!canPlaceArms(level, pos, state, nextArms)) {
				return InteractionResult.FAIL;
			}
			removeArms(level, pos);
			placeArms(level, pos, state, nextArms);

			level.setBlock(pos, state.setValue(NUMBER_OF_ARMS, nextArms), Block.UPDATE_ALL);
		}
		return InteractionResult.SUCCESS;
	}

	private boolean canPlaceAt(Level level, BlockPos pos, Direction expectedFacing) {
		var blockState = level.getBlockState(pos);

		if (blockState.canBeReplaced()) {
			return true;
		}
		if (blockState.is(UFObjects.FLOOR_LAMP_SUPPORT)) {
			return blockState.getValue(HorizontalDirectionalBlock.FACING) == expectedFacing;
		}
		return false;
	}

	private boolean canPlaceArms(Level level, BlockPos pos, BlockState state, int arms) {
		Direction front = state.getValue(FACING);
		Direction left = front.getCounterClockWise();
		Direction right = front.getClockWise();
		Direction back = front.getOpposite();

		return switch (arms) {
			case 0 -> canPlaceAt(level, pos.relative(front), front);

			case 1 -> canPlaceAt(level, pos.relative(left), left)
					&& canPlaceAt(level, pos.relative(right), right);

			case 2 -> canPlaceAt(level, pos.relative(front), front)
					&& canPlaceAt(level, pos.relative(back), back)
					&& canPlaceAt(level, pos.relative(left), left)
					&& canPlaceAt(level, pos.relative(right), right);

			default -> true;
		};
	}

	private void placeSupport(Level level, BlockPos targetPos, Direction facing, BlockState support) {
		var blockState = level.getBlockState(targetPos);
		if (!blockState.canBeReplaced() && !blockState.is(UFObjects.FLOOR_LAMP_SUPPORT)) {
			return;
		}
		level.setBlock(targetPos,
				support.setValue(HorizontalDirectionalBlock.FACING, facing),
				Block.UPDATE_ALL
		);
	}

	private void placeArms(Level level, BlockPos pos, BlockState state, int arms) {
		Direction front = state.getValue(FACING);
		Direction left = front.getCounterClockWise();
		Direction right = front.getClockWise();
		Direction back = front.getOpposite();

		BlockState support = UFObjects.FLOOR_LAMP_SUPPORT.defaultBlockState();

		switch (arms) {
			case 0: // 1 arm
				placeSupport(level, pos.relative(front), front, support);
				break;

			case 1: // 2 arms
				placeSupport(level, pos.relative(left), left, support);
				placeSupport(level, pos.relative(right), right, support);
				break;

			case 2: // 4 arms
				placeSupport(level, pos.relative(front), front, support);
				placeSupport(level, pos.relative(back), back, support);
				placeSupport(level, pos.relative(left), left, support);
				placeSupport(level, pos.relative(right), right, support);
				break;
		}
	}

	private void removeArms(Level level, BlockPos pos) {
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			BlockPos target = pos.relative(dir);
			if (level.getBlockState(target).is(UFObjects.FLOOR_LAMP_SUPPORT)) {
				level.removeBlock(target, false);
			}
		}
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}

		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	protected VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}
}
