package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.block.properties.ModularSofaProperty;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SofaBlock extends HorizontalDirectionalBlock implements ISittableBlock, SimpleWaterloggedBlock {

	public static final EnumProperty<ModularSofaProperty> SHAPE = EnumProperty.create("shape", ModularSofaProperty.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final AABB SEAT = new AABB(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	private static final MapCodec<SofaBlock> CODEC = simpleCodec(SofaBlock::new);

	public SofaBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(SHAPE, ModularSofaProperty.SINGLE)
				.setValue(FACING, Direction.NORTH)
				.setValue(WATERLOGGED, false)
		);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SHAPE, FACING, WATERLOGGED);
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		Direction direction = state.getValue(FACING);
		ModularSofaProperty couchShape = state.getValue(SHAPE);
		switch (mirror) {
			case LEFT_RIGHT -> {
				if (direction.getAxis() == Direction.Axis.Z) {
					var shape = switch (couchShape) {
						case OUTER_LEFT -> ModularSofaProperty.OUTER_RIGHT;
						case OUTER_RIGHT -> ModularSofaProperty.OUTER_LEFT;
						case INNER_LEFT -> ModularSofaProperty.INNER_RIGHT;
						case INNER_RIGHT -> ModularSofaProperty.INNER_LEFT;
						default -> couchShape;
					};
					return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, shape);
				}
			}
			case FRONT_BACK -> {
				if (direction.getAxis() == Direction.Axis.X) {
					var shape = switch (couchShape) {
						case OUTER_LEFT -> ModularSofaProperty.OUTER_RIGHT;
						case OUTER_RIGHT -> ModularSofaProperty.OUTER_LEFT;
						case INNER_LEFT -> ModularSofaProperty.INNER_LEFT;
						case INNER_RIGHT -> ModularSofaProperty.INNER_RIGHT;
						default -> couchShape;
					};
					return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, shape);
				}
			}
		}

		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return direction.getAxis().isHorizontal() ? state.setValue(SHAPE, getShape(state, level, currentPos)) : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		ModularSofaProperty sofaType = state.getValue(SHAPE);
		Direction facing = state.getValue(FACING);

		VoxelShape baseShape = switch (sofaType) {
			case SINGLE -> Shapes.or(
					box(13, 8, 0, 16, 12, 16),
					box(0, 8, 0, 3, 12, 16),
					box(13, 0, 13, 16, 2, 16),
					box(0, 0, 13, 3, 2, 16),
					box(13, 0, 0, 16, 2, 3),
					box(0, 0, 0, 3, 2, 3),
					box(0, 2, 0, 16, 8, 16),
					box(1, 8, 12, 15, 17, 16)
			);

			case LEFT -> Shapes.or(
					box(13, 8, 0, 16, 12, 16),
					box(13, 0, 13, 16, 2, 16),
					box(13, 0, 0, 16, 2, 3),
					box(0, 2, 0, 16, 8, 16),
					box(0, 8, 12, 15, 17, 16)
			);

			case MIDDLE -> Shapes.or(
					box(0, 2, 0, 16, 8, 16),
					box(0, 8, 12, 16, 17, 16)
			);

			case RIGHT -> Shapes.or(
					box(0, 8, 0, 3, 12, 16),
					box(0, 0, 13, 3, 2, 16),
					box(0, 0, 0, 3, 2, 3),
					box(0, 2, 0, 16, 8, 16),
					box(1, 8, 12, 16, 17, 16)
			);

			case INNER_LEFT -> Shapes.or(
					box(0, 0, 0, 3, 2, 3),
					box(0, 2, 0, 16, 8, 16),
					box(0, 8, 12, 12, 17, 16),
					box(12, 8, 0, 16, 17, 16),
					box(0, 0, 0, 3, 0, 3)
			);

			case INNER_RIGHT -> Shapes.or(
					box(0, 0, 13, 3, 2, 16),
					box(0, 2, 0, 16, 8, 16),
					box(0, 8, 0, 4, 17, 12),
					box(0, 8, 12, 16, 17, 16),
					box(13, 0, 0, 16, 2, 3)
			);

			case OUTER_LEFT -> Shapes.or(
					box(0, 0, 0, 3, 2, 3),
					box(0, 2, 14, 16, 8, 16),
					box(0, 2, 0, 16, 8, 14),
					box(12, 8, 12, 16, 17, 16)
			);

			case OUTER_RIGHT -> Shapes.or(
					box(13, 0, 0, 16, 2, 3),
					box(0, 2, 14, 16, 8, 16),
					box(0, 2, 0, 16, 8, 14),
					box(0, 8, 12, 4, 17, 16)
			);
		};

		return switch (facing) {
			case NORTH -> baseShape;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(baseShape, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(baseShape, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(baseShape, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (this.sitOn(level, pos, player, state.getValue(FACING))) {
			return ItemInteractionResult.CONSUME;
		}
		return ItemInteractionResult.CONSUME_PARTIAL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
		return state.setValue(SHAPE, getShape(state, context.getLevel(), context.getClickedPos()));
	}

	public boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
		BlockState other = level.getBlockState(pos.relative(face));
		return !other.is(UFBlockTags.SOFA) || other.getValue(FACING) != state.getValue(FACING);
	}

	public ModularSofaProperty getShape(BlockState state, BlockGetter level, BlockPos pos) {
		Direction direction = state.getValue(FACING);

		BlockState state1 = level.getBlockState(pos.relative(direction.getOpposite()));
		if (state1.is(UFBlockTags.SOFA) && state1.getBlock() instanceof SofaBlock) {
			Direction dir = state1.getValue(FACING);
			if (dir.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, level, pos, dir)) {
				return dir == direction.getCounterClockWise() ?
						ModularSofaProperty.OUTER_LEFT :
						ModularSofaProperty.OUTER_RIGHT;
			}
		}

		BlockState state2 = level.getBlockState(pos.relative(direction));
		if (state2.is(UFBlockTags.SOFA) && state2.getBlock() instanceof SofaBlock) {
			Direction dir = state2.getValue(FACING);
			if (dir.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, level, pos, dir.getOpposite())) {
				return dir == direction.getCounterClockWise() ?
						ModularSofaProperty.INNER_LEFT :
						ModularSofaProperty.INNER_RIGHT;
			}
		}

		BlockState state3 = level.getBlockState(pos.relative(direction.getClockWise().getOpposite()));
		BlockState state4 = level.getBlockState(pos.relative(direction.getClockWise()));
		if (!state3.is(UFBlockTags.SOFA) && !state4.is(UFBlockTags.SOFA)) {
			return ModularSofaProperty.SINGLE;
		} else if (!state3.is(UFBlockTags.SOFA)) {
			return ModularSofaProperty.RIGHT;
		} else if (!state4.is(UFBlockTags.SOFA)) {
			return ModularSofaProperty.LEFT;
		} else {
			return ModularSofaProperty.MIDDLE;
		}
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		// TODO: Place particles
//        TableSmokeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public AABB getSeatSize(BlockState state) {
		return SEAT;
	}
}
