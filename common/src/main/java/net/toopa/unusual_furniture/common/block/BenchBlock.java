package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
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
import net.toopa.unusual_furniture.common.block.properties.ModularBenchProperty;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

public class BenchBlock extends HorizontalDirectionalBlock implements ISittableBlock, SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<ModularBenchProperty> SHAPE = EnumProperty.create("shape", ModularBenchProperty.class);
	private static final MapCodec<BenchBlock> CODEC = simpleCodec(BenchBlock::new);
	private static final AABB SEAT = new AABB(0.125, 0, 0.125, 0.875, 0.5, 0.875);
	private static final VoxelShape VOXEL_SHAPE = Shapes.or(
			box(0.0F, 0.0F, 1.0F, 16.0F, 8.0F, 16.0F),
			box(0.0F, 0.0F, 14.0F, 16.0F, 18.0F, 16.0F)
	);

	public BenchBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState()
				.setValue(FACING, Direction.NORTH)
				.setValue(SHAPE, ModularBenchProperty.SINGLE)
				.setValue(WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, SHAPE, WATERLOGGED);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		if (direction.getAxis().isHorizontal()) {
			return state.setValue(SHAPE, getShape(state, level, pos));
		}

		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
		return state.setValue(SHAPE, getShape(state, context.getLevel(), context.getClickedPos()));
	}

	public ModularBenchProperty getShape(BlockState state, BlockGetter level, BlockPos pos) {
		Direction direction = state.getValue(FACING);

		BlockState state3 = level.getBlockState(pos.relative(direction.getClockWise().getOpposite()));
		BlockState state4 = level.getBlockState(pos.relative(direction.getClockWise()));

		boolean connectLeft = state3.is(UFBlockTags.BENCH) && state3.getValue(FACING) == direction;
		boolean connectRight = state4.is(UFBlockTags.BENCH) && state4.getValue(FACING) == direction;

		if (!connectLeft && !connectRight) {
			return ModularBenchProperty.SINGLE;
		} else if (!connectLeft) {
			return ModularBenchProperty.RIGHT;
		} else if (!connectRight) {
			return ModularBenchProperty.LEFT;
		} else {
			return ModularBenchProperty.MIDDLE;
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case NORTH -> VOXEL_SHAPE;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(VOXEL_SHAPE, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public AABB getSeatSize(BlockState state) {
		return SEAT;
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		// TODO: Place particles
//        TableSmokeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (this.sitOn(level, pos, player, state.getValue(FACING))) {
			return ItemInteractionResult.CONSUME;
		}
		return ItemInteractionResult.CONSUME_PARTIAL;
	}
}
