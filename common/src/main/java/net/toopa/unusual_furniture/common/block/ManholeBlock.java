package net.toopa.unusual_furniture.common.block;

import java.util.function.BiConsumer;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

public class ManholeBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	private static final MapCodec<ManholeBlock> CODEC = simpleCodec(ManholeBlock::new);
	private static final VoxelShape OPEN_SHAPE = Shapes.or(
			box(0, 14, 0, 16, 16, 2),
			box(0, 14, 14, 16, 16, 16),
			box(0, 14, 2, 2, 16, 14),
			box(14, 14, 2, 16, 16, 14)
	).optimize();
	private static final VoxelShape CLOSED_SHAPE = box(0, 14, 0, 16, 16, 16);

	public ManholeBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(WATERLOGGED, false)
				.setValue(OPEN, false)
				.setValue(POWERED, false)
				.setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(OPEN)) {
			return OPEN_SHAPE;
		}
		return CLOSED_SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, OPEN, POWERED, WATERLOGGED);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return switch (pathComputationType) {
			case LAND, AIR -> state.getValue(OPEN);
			case WATER -> state.getValue(WATERLOGGED);
		};
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState blockState = this.defaultBlockState();
		FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
		Direction direction = context.getClickedFace();
		if (!context.replacingClickedOnBlock() && direction.getAxis().isHorizontal()) {
			blockState = blockState.setValue(FACING, direction);
		} else {
			blockState = blockState.setValue(FACING, context.getHorizontalDirection().getOpposite());
		}

		if (context.getLevel().hasNeighborSignal(context.getClickedPos())) {
			blockState = blockState.setValue(OPEN, true).setValue(POWERED, true);
		}

		return blockState.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		this.toggle(state, level, pos, player);
		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	@Override
	protected void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
		if (explosion.canTriggerBlocks() && !state.getValue(POWERED)) {
			this.toggle(state, level, pos, null);
		}

		super.onExplosionHit(state, level, pos, explosion, dropConsumer);
	}

	private void toggle(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
		BlockState blockState = state.cycle(OPEN);
		level.setBlock(pos, blockState, 2);
		if (blockState.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		this.playSound(player, level, pos, blockState.getValue(OPEN));
	}

	protected void playSound(@Nullable Player player, Level level, BlockPos pos, boolean isOpened) {
		level.playSound(
				player, pos, isOpened ? SoundEvents.IRON_TRAPDOOR_OPEN : SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F
		);
		level.gameEvent(player, isOpened ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
	}

	@Override
	protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
		if (!level.isClientSide) {
			boolean bl = level.hasNeighborSignal(pos);
			if (bl != state.getValue(POWERED)) {
				if (state.getValue(OPEN) != bl) {
					state = state.setValue(OPEN, bl);
					this.playSound(null, level, pos, bl);
				}

				level.setBlock(pos, state.setValue(POWERED, bl), 2);
				if (state.getValue(WATERLOGGED)) {
					level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
				}
			}
		}
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		// TODO: Place particles
//        TableSmokeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
}
