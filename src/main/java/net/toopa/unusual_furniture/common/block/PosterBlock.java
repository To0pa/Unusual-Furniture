package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class PosterBlock extends DirectionalBlock {

	private static final MapCodec<PosterBlock> CODEC = simpleCodec(PosterBlock::new);
	public static final IntegerProperty POSTER_TYPE = IntegerProperty.create("poster_type", 0, 6);
	private static final VoxelShape SHAPE = box(0.0F, 0.0F, 15.9, 16.0F, 16.0F, 16.0F);
	private static final Map<Direction, VoxelShape> SHAPE_MAP = VoxelShapeUtils.createFullDirectionalShapeMap(SHAPE);

	public PosterBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, POSTER_TYPE);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction facing = state.getValue(FACING);
		BlockPos attachedPos = pos.relative(facing.getOpposite());
		BlockState attachedState = level.getBlockState(attachedPos);
		return attachedState.isFaceSturdy(level, attachedPos, facing);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(level, pos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = this.defaultBlockState()
				.setValue(FACING, context.getClickedFace());
		return state.canSurvive(context.getLevel(), context.getClickedPos()) ? state : null;
	}

	@Override
	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		super.onPlace(state, level, pos, oldState, movedByPiston);
		if (!level.isClientSide) {
			level.setBlock(pos, state.setValue(POSTER_TYPE, level.random.nextInt(POSTER_TYPE.getPossibleValues().stream().max(Integer::compareTo).orElse(0) + 1)), Block.UPDATE_ALL);
		}
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(FACING));
	}

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return CODEC;
	}
}
