package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.block.properties.BroomProperty;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import java.util.Map;

public class BroomBlock extends HorizontalDirectionalBlock {

	private static final MapCodec<BroomBlock> CODEC = simpleCodec(BroomBlock::new);
	private static final VoxelShape DEFAULT_STANDING_SHAPE = Shapes.or(
			box(4.0F, 0.0F, 4.0F, 12.0F, 10.0F, 12.0F),
			box(4.0F, 0.0F, 4.0F, 12.0F, 10.0F, 12.0F),
			box(5.0F, 10.0F, 5.0F, 11.0F, 12.0F, 11.0F),
			box(7.0F, 14.0F, 7.0F, 9.0F, 32.0F, 9.0F),
			box(5.0F, 12.0F, 5.0F, 11.0F, 14.0F, 11.0F)
	);
	private static final Map<Direction, VoxelShape> STANDING_SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_STANDING_SHAPE);
	private static final VoxelShape DEFAULT_LAYING_SHAPE = Shapes.or(
			box(4.0F, 0.1, -8.0F, 12.0F, 8.1, 2.0F),
			box(4.0F, 0.1, -8.0F, 12.0F, 8.1, 2.0F),
			box(5.0F, 1.1, 2.0F, 11.0F, 7.1, 4.0F),
			box(7.0F, 3.1, 6.0F, 9.0F, 5.1, 24.0F),
			box(5.0F, 1.1, 4.0F, 11.0F, 7.1, 6.0F)
	);
	private static final Map<Direction, VoxelShape> LAYING_SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_LAYING_SHAPE);
	public static final EnumProperty<BroomProperty> PLACEMENT =
			EnumProperty.create("placement", BroomProperty.class);

	public BroomBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any()
				.setValue(PLACEMENT, BroomProperty.STANDING)
				.setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(PLACEMENT, FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction dir = state.getValue(FACING);
		return state.getValue(PLACEMENT) == BroomProperty.LAYING ? LAYING_SHAPE_MAP.get(dir) : STANDING_SHAPE_MAP.get(dir);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		BroomProperty placement = state.getValue(PLACEMENT);
		if (level.setBlock(pos, state.setValue(PLACEMENT, placement.cycle()), Block.UPDATE_ALL))
			return InteractionResult.SUCCESS;
		return super.useWithoutItem(state, level, pos, player, hitResult);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
		return false;
	}
}
