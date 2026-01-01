package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.block.properties.RailingDirectionProperty;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

public class RailingBlock extends Block implements SimpleWaterloggedBlock {

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<RailingDirectionProperty> RIGHT = EnumProperty.create("right", RailingDirectionProperty.class);
	public static final EnumProperty<RailingDirectionProperty> LEFT = EnumProperty.create("left", RailingDirectionProperty.class);
	private static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 4.0F, 16.0F, 16.0F, 12.0F);

	public RailingBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(AXIS, Direction.Axis.X).setValue(RIGHT, RailingDirectionProperty.NONE).setValue(LEFT, RailingDirectionProperty.NONE));
	}

	public static BlockState rotatePillar(BlockState state, Rotation rotation) {
		return switch (rotation) {
			case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
				case X -> state.setValue(AXIS, Direction.Axis.Z);
				case Z -> state.setValue(AXIS, Direction.Axis.X);
				default -> state;
			};
			default -> state;
		};
	}

	private static Direction getLeft(BlockState state) {
		return state.getValue(AXIS) == Direction.Axis.Z ? Direction.WEST : Direction.NORTH;
	}

	private static Direction getRight(BlockState state) {
		return state.getValue(AXIS) == Direction.Axis.Z ? Direction.EAST : Direction.SOUTH;
	}

	private static RailingDirectionProperty getDiagonalConnection(LevelAccessor level, BlockPos pos, Direction side) {
		BlockPos up = pos.above().relative(side);
		if (level.getBlockState(up).is(UFBlockTags.RAILING)) {
			return RailingDirectionProperty.UP;
		}

		BlockPos down = pos.below().relative(side);
		if (level.getBlockState(down).is(UFBlockTags.RAILING)) {
			return RailingDirectionProperty.DOWN;
		}

		return RailingDirectionProperty.NONE;
	}

	private static RailingDirectionProperty[] resolveConflict(RailingDirectionProperty left, RailingDirectionProperty right) {
		if (left != RailingDirectionProperty.NONE && left == right) {
			return new RailingDirectionProperty[]{left, RailingDirectionProperty.NONE};
		}

		return new RailingDirectionProperty[]{left, right};
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = this.defaultBlockState().setValue(AXIS, context.getHorizontalDirection().getAxis()).setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);

		return updateConnections(context.getLevel(), context.getClickedPos(), state);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);

		Direction leftDir = getLeft(blockstate);
		Direction rightDir = getRight(blockstate);

		for (Direction dir : new Direction[]{leftDir, rightDir}) {
			BlockPos upPos = pos.above().relative(dir);
			BlockPos downPos = pos.below().relative(dir);

			world.neighborShapeChanged(Direction.DOWN, blockstate, upPos, pos, Block.UPDATE_ALL, 512);
			world.neighborShapeChanged(Direction.UP, blockstate, downPos, pos, Block.UPDATE_ALL, 512);
		}

		// TODO: Place particles
//        TableSmokeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return rotatePillar(state, rotation);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos pos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		BlockState newState = updateConnections(level, pos, state);

		if (newState != state) {
			Direction leftDir = getLeft(state);
			Direction rightDir = getRight(state);

			for (Direction dir : new Direction[]{leftDir, rightDir}) {
				BlockPos upPos = pos.above().relative(dir);
				BlockPos downPos = pos.below().relative(dir);

				level.neighborShapeChanged(Direction.DOWN, newState, upPos, pos, Block.UPDATE_ALL, 512);
				level.neighborShapeChanged(Direction.UP, newState, downPos, pos, Block.UPDATE_ALL, 512);
			}
		}

		return newState;
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(AXIS, LEFT, RIGHT, WATERLOGGED);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(AXIS)) {
			case Direction.Axis.Z -> SHAPE;
			case Direction.Axis.X -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	private BlockState updateConnections(LevelAccessor level, BlockPos pos, BlockState state) {
		Direction leftDir = getLeft(state);
		Direction rightDir = getRight(state);

		RailingDirectionProperty left = getDiagonalConnection(level, pos, leftDir);
		RailingDirectionProperty right = getDiagonalConnection(level, pos, rightDir);

		RailingDirectionProperty[] resolved = resolveConflict(left, right);

		return state.setValue(LEFT, resolved[0]).setValue(RIGHT, resolved[1]);
	}
}
