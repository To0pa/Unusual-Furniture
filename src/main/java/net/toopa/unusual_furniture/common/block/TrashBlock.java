package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class TrashBlock extends HorizontalDirectionalBlock {

	public static final IntegerProperty SHAPE = IntegerProperty.create("shape", 0, 1);
	private static final MapCodec<TrashBlock> CODEC = simpleCodec(TrashBlock::new);
	private static final VoxelShape TOP_SHAPE = Shapes.or(
			box(1, 0, 1, 15, 9, 15),
			box(3, 2, 1, 13, 8, 13)
	).optimize();
	private static final Map<Direction, VoxelShape> TOP_SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(TOP_SHAPE);
	private static final VoxelShape BOTTOM_SHAPE = box(1, 0, 1, 15, 16, 15);

	public TrashBlock(Properties properties) {
		super(properties.pushReaction(PushReaction.BLOCK));
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(SHAPE, 0));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SHAPE, FACING);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(SHAPE) == 1) {
			return TOP_SHAPE_MAP.get(state.getValue(FACING));
		}
		return BOTTOM_SHAPE;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();

		if (pos.getY() >= level.getMaxBuildHeight() - 1) return null;

		BlockPos above = pos.above();
		if (!level.getBlockState(above).canBeReplaced(context)) return null;

		return this.defaultBlockState().setValue(SHAPE, 0).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		// TODO: Place particles
//        TableSmokeProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		level.setBlock(pos.above(), state.setValue(SHAPE, 1), 3);
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide) {
			BlockPos other = state.getValue(SHAPE) == 0 ? pos.above() : pos.below();
			BlockState otherState = level.getBlockState(other);

			if (otherState.is(this)) {
				level.destroyBlock(other, false);
			}
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
