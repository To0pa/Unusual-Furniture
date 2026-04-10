package net.toopa.unusual_furniture.common.block;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.block.properties.ModularCurtainProperty;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

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

public class CurtainBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<ModularCurtainProperty> SHAPE = EnumProperty.create("shape", ModularCurtainProperty.class);
	private static final MapCodec<CurtainBlock> CODEC = simpleCodec(CurtainBlock::new);

	// Base shapes
	private static final VoxelShape VOXEL_SHAPE = box(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 15.0F);
	private static final VoxelShape LEFT_OPEN_SHAPE = box(0.0F, 0.0F, 14.0F, 8.0F, 16.0F, 15.0F);
	private static final VoxelShape RIGHT_OPEN_SHAPE = box(8.0F, 0.0F, 14.0F, 16.0F, 16.0F, 15.0F);
	private static final VoxelShape CENTER_HALF_SHAPE = box(0.0F, 8.0F, 14.0F, 16.0F, 16.0F, 15.0F);

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
		if (level.isClientSide) return InteractionResult.SUCCESS;

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

		level.playSound(null, pos, SoundEvents.BUNDLE_INSERT, SoundSource.BLOCKS, 0.5F, 0.5F);
		return InteractionResult.SUCCESS;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		if ((direction.getAxis().isHorizontal() || direction == Direction.UP || direction == Direction.DOWN) && level instanceof Level realLevel) {
			updateCurtainGroup(realLevel, pos);
			if (neighborState.is(UFBlockTags.CURTAIN) && neighborState.getBlock() instanceof CurtainBlock) {
				updateCurtainGroup(realLevel, neighborPos);
			}
		}

		return state;
	}

	private void updateCurtainGroup(Level level, BlockPos origin) {
		BlockState originState = level.getBlockState(origin);
		if (!originState.is(UFBlockTags.CURTAIN) || !(originState.getBlock() instanceof CurtainBlock)) return;

		List<BlockPos> group = new ArrayList<>();
		collectConnectedCurtains(level, origin, originState.getValue(FACING), group);

		for (BlockPos p : group) {
			BlockState oldState = level.getBlockState(p);
			if (oldState.getBlock() instanceof CurtainBlock cBlock) {
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

		for (Direction dir : new Direction[]{Direction.UP, Direction.DOWN, facing.getClockWise(), facing.getCounterClockWise()}) {
			BlockPos neighbor = pos.relative(dir);
			if (isSameCurtain(level, neighbor, facing)) {
				collectConnectedCurtains(level, neighbor, facing, group);
			}
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

		boolean connectedAbove = isSameCurtain(level, pos.above(), facing);
		boolean connectedBelow = isSameCurtain(level, pos.below(), facing);
		boolean connectedLeft = isSameCurtain(level, pos.relative(leftDir), facing);
		boolean connectedRight = isSameCurtain(level, pos.relative(rightDir), facing);

		boolean isOpen = forceOpenState != null ? forceOpenState : state.getValue(SHAPE).isOpen();

		if (!connectedAbove && !connectedBelow && !connectedLeft && !connectedRight) return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.SINGLE_CLOSED;
		if (connectedAbove && connectedBelow && !connectedLeft && !connectedRight) return isOpen ? ModularCurtainProperty.MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;

		if (!connectedAbove && connectedBelow) {
			if (connectedLeft && !connectedRight) return isOpen ? ModularCurtainProperty.LEFT_TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
			if (!connectedLeft && connectedRight) return isOpen ? ModularCurtainProperty.RIGHT_TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
			return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.TOP_CLOSED;
		}

		if (connectedAbove && !connectedBelow) {
			if (connectedLeft && !connectedRight) return isOpen ? ModularCurtainProperty.LEFT_BOTTOM_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
			if (!connectedLeft && connectedRight) return isOpen ? ModularCurtainProperty.RIGHT_BOTTOM_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
			return isOpen ? ModularCurtainProperty.MIDDLE_OPEN : ModularCurtainProperty.BOTTOM_CLOSED;
		}

		if (connectedAbove && connectedBelow) {
			if (connectedLeft && !connectedRight) return isOpen ? ModularCurtainProperty.LEFT_MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
			if (!connectedLeft && connectedRight) return isOpen ? ModularCurtainProperty.RIGHT_MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
			return isOpen ? ModularCurtainProperty.MIDDLE_OPEN : ModularCurtainProperty.MIDDLE_CLOSED;
		}

		return isOpen ? ModularCurtainProperty.TOP_OPEN : ModularCurtainProperty.SINGLE_CLOSED;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		ModularCurtainProperty shape = state.getValue(SHAPE);
		Direction facing = state.getValue(FACING);

		if (shape.isOpen()) {
			// Immediately return an empty shape for the middle open piece so there is NO hitbox
			if (shape == ModularCurtainProperty.MIDDLE_OPEN) {
				return Shapes.empty();
			}

			VoxelShape voxelShape = VOXEL_SHAPE;
			if (shape == ModularCurtainProperty.LEFT_TOP_OPEN || shape == ModularCurtainProperty.RIGHT_TOP_OPEN) {
				voxelShape = VOXEL_SHAPE;
			} else if (shape == ModularCurtainProperty.LEFT_MIDDLE_OPEN || shape == ModularCurtainProperty.LEFT_BOTTOM_OPEN) {
				voxelShape = RIGHT_OPEN_SHAPE;
			} else if (shape == ModularCurtainProperty.RIGHT_MIDDLE_OPEN || shape == ModularCurtainProperty.RIGHT_BOTTOM_OPEN) {
				voxelShape = LEFT_OPEN_SHAPE;
			} else if (shape == ModularCurtainProperty.TOP_OPEN) {
				voxelShape = CENTER_HALF_SHAPE;
			}

			return switch (facing) {
				case NORTH -> voxelShape;
				case SOUTH -> VoxelShapeUtils.rotateVoxelShape(voxelShape, Direction.Axis.Y, 180);
				case WEST -> VoxelShapeUtils.rotateVoxelShape(voxelShape, Direction.Axis.Y, 270);
				case EAST -> VoxelShapeUtils.rotateVoxelShape(voxelShape, Direction.Axis.Y, 90);
				default -> Shapes.block();
			};
		}

		// For closed states, standard full box
		return switch (facing) {
			case NORTH -> VOXEL_SHAPE;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return getShape(state, world, pos, context);
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() { return CODEC; }

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) { return false; }

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		Direction facing = blockstate.getValue(FACING);
		boolean hasOpenNeighbor = false;

		for (Direction dir : new Direction[]{Direction.UP, Direction.DOWN, facing.getClockWise(), facing.getCounterClockWise()}) {
			BlockPos neighborPos = pos.relative(dir);
			if (isSameCurtain(world, neighborPos, facing) && world.getBlockState(neighborPos).getValue(SHAPE).isOpen()) {
				hasOpenNeighbor = true;
				break;
			}
		}

		BlockState newState = blockstate.setValue(SHAPE, computeShape(blockstate, world, pos, hasOpenNeighbor ? true : null));
		if (!blockstate.equals(newState)) world.setBlock(pos, newState, Block.UPDATE_ALL);
	}
}
