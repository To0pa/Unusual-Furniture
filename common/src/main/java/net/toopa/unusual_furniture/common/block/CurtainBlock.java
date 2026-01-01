package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.block.properties.ModularCurtainProperty;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import java.util.ArrayList;
import java.util.List;

public class CurtainBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<ModularCurtainProperty> SHAPE = EnumProperty.create("shape", ModularCurtainProperty.class);
	private static final MapCodec<CurtainBlock> CODEC = simpleCodec(CurtainBlock::new);
	private static final VoxelShape VOXEL_SHAPE = box(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 15.0F);

	public CurtainBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(FACING, Direction.NORTH)
				.setValue(SHAPE, ModularCurtainProperty.SINGLE_CLOSED)
				.setValue(WATERLOGGED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		FluidState fluidState = context.getLevel().getFluidState(pos);
		BlockState state = defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
		return state.setValue(SHAPE, computeShape(state, context.getLevel(), pos));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, SHAPE, WATERLOGGED);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		}

		boolean currentlyOpen = state.getValue(SHAPE).isOpen();

		List<BlockPos> group = new ArrayList<>();
		collectConnectedCurtains(level, pos, state.getValue(FACING), group);

		for (BlockPos p : group) {
			BlockState s = level.getBlockState(p);
			if (s.getBlock() instanceof CurtainBlock cBlock) {
				ModularCurtainProperty newShape = cBlock.computeShape(s, level, p, !currentlyOpen);
				if (s.getValue(SHAPE) != newShape) {
					level.setBlock(p, s.setValue(SHAPE, newShape), Block.UPDATE_ALL);
				}
			}
		}

		level.playSound(player, pos, SoundEvents.WOOL_STEP, SoundSource.BLOCKS, 0.5F, 1.2F);
		return InteractionResult.SUCCESS;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		if (direction.getAxis().isHorizontal() || direction == Direction.UP || direction == Direction.DOWN) {
			if (level instanceof Level realLevel) {
				updateCurtainGroup(realLevel, pos);
				if (neighborState.is(UFBlockTags.CURTAIN) && neighborState.getBlock() instanceof CurtainBlock) {
					updateCurtainGroup(realLevel, neighborPos);
				}
			}
		}

		return state;
	}

	private void updateCurtainGroup(Level level, BlockPos origin) {
		BlockState originState = level.getBlockState(origin);
		if (!originState.is(UFBlockTags.CURTAIN) && !(originState.getBlock() instanceof CurtainBlock)) {
			return;
		}

		List<BlockPos> group = new ArrayList<>();
		collectConnectedCurtains(level, origin, originState.getValue(FACING), group);

		for (BlockPos p : group) {
			BlockState oldState = level.getBlockState(p);
			if (originState.is(UFBlockTags.CURTAIN) && oldState.getBlock() instanceof CurtainBlock cBlock) {
				ModularCurtainProperty newShape = cBlock.computeShape(oldState, level, p);
				if (oldState.getValue(SHAPE) != newShape) {
					level.setBlock(p, oldState.setValue(SHAPE, newShape), Block.UPDATE_ALL);
				}
			}
		}
	}

	private void collectConnectedCurtains(Level level, BlockPos pos, Direction facing, List<BlockPos> group) {
		if (group.contains(pos)) return;
		group.add(pos);

		Direction left = facing.getCounterClockWise();
		Direction right = facing.getClockWise();

		BlockPos above = pos.above();
		if (isSameCurtain(level, above, facing)) {
			collectConnectedCurtains(level, above, facing, group);
		}

		BlockPos below = pos.below();
		if (isSameCurtain(level, below, facing)) {
			collectConnectedCurtains(level, below, facing, group);
		}

		BlockPos leftPos = pos.relative(left);
		if (isSameCurtain(level, leftPos, facing)) {
			collectConnectedCurtains(level, leftPos, facing, group);
		}

		BlockPos rightPos = pos.relative(right);
		if (isSameCurtain(level, rightPos, facing)) {
			collectConnectedCurtains(level, rightPos, facing, group);
		}
	}

	private boolean isSameCurtain(Level level, BlockPos pos, Direction facing) {
		if (!level.isLoaded(pos)) return false;
		BlockState s = level.getBlockState(pos);
		return s.is(UFBlockTags.CURTAIN) && s.getBlock() instanceof CurtainBlock && s.getValue(FACING) == facing;
	}

	public ModularCurtainProperty computeShape(BlockState state, Level level, BlockPos pos) {
		return computeShape(state, level, pos, null);
	}

	public ModularCurtainProperty computeShape(BlockState state, Level level, BlockPos pos, Boolean forceOpenState) {
		Direction facing = state.getValue(FACING);
		Direction leftDir = facing.getCounterClockWise();
		Direction rightDir = facing.getClockWise();

		BlockPos abovePos = pos.above();
		BlockPos belowPos = pos.below();
		BlockPos leftPos = pos.relative(leftDir);
		BlockPos rightPos = pos.relative(rightDir);

		boolean connectedAbove = isSameCurtain(level, abovePos, facing);
		boolean connectedBelow = isSameCurtain(level, belowPos, facing);
		boolean connectedLeft = isSameCurtain(level, leftPos, facing);
		boolean connectedRight = isSameCurtain(level, rightPos, facing);

		boolean isOpen = forceOpenState != null ? forceOpenState : state.getValue(SHAPE).isOpen();

		if (!connectedAbove && !connectedBelow && !connectedLeft && !connectedRight) {
			return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.SINGLE_CLOSED;
		}

		if (connectedAbove && connectedBelow && !connectedLeft && !connectedRight) {
			return isOpen ? ModularCurtainProperty.MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
		}

		if (!connectedAbove && connectedBelow) {
			if (!connectedLeft && !connectedRight) {
				return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
			}
			if (connectedLeft && !connectedRight) {
				return isOpen ? ModularCurtainProperty.LEFT_TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
			}
			if (!connectedLeft && connectedRight) {
				return isOpen ? ModularCurtainProperty.RIGHT_TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
			}
			return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
		}

		if (connectedAbove && !connectedBelow) {
			if (!connectedLeft && !connectedRight) {
				return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
			}
			if (connectedLeft && !connectedRight) {
				return isOpen ? ModularCurtainProperty.LEFT_BOTTOM_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
			}
			if (!connectedLeft && connectedRight) {
				return isOpen ? ModularCurtainProperty.RIGHT_BOTTOM_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
			}
			return isOpen ? ModularCurtainProperty.MIDDLE_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
		}

		if (connectedAbove && connectedBelow) {
			if (connectedLeft && !connectedRight) {
				return isOpen ? ModularCurtainProperty.LEFT_MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
			}
			if (!connectedLeft && connectedRight) {
				return isOpen ? ModularCurtainProperty.RIGHT_MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
			}
			return isOpen ? ModularCurtainProperty.MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
		}

		if (!connectedAbove && !connectedBelow) {
			return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.SINGLE_CLOSED;
		}

		return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.SINGLE_CLOSED;
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);

		Direction facing = blockstate.getValue(FACING);
		Direction leftDir = facing.getCounterClockWise();
		Direction rightDir = facing.getClockWise();

		boolean hasOpenNeighbor = false;
		BlockPos[] neighborPositions = {
				pos.above(),
				pos.below(),
				pos.relative(leftDir),
				pos.relative(rightDir)
		};

		for (BlockPos neighborPos : neighborPositions) {
			if (isSameCurtain(world, neighborPos, facing)) {
				BlockState neighborState = world.getBlockState(neighborPos);
				if (neighborState.getValue(SHAPE).isOpen()) {
					hasOpenNeighbor = true;
					break;
				}
			}
		}

		BlockState newState = blockstate.setValue(SHAPE, computeShape(blockstate, world, pos, hasOpenNeighbor ? true : null));
		if (!blockstate.equals(newState)) {
			world.setBlock(pos, newState, Block.UPDATE_ALL);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(SHAPE).equals(ModularCurtainProperty.MIDDLE_OPEN))
			return Shapes.empty();

		return switch (state.getValue(FACING)) {
			case NORTH -> VOXEL_SHAPE;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(SHAPE).isOpen()) {
			return Shapes.empty();
		}
		return getShape(state, world, pos, context);
	}
}
