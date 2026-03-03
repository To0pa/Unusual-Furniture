package net.toopa.unusual_furniture.common.block;

import java.util.Map;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PebbleBagBlock extends AbstractBagBlock {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty PEBBLE_TYPE = IntegerProperty.create("pebble_type", 0, 4);
	private static final MapCodec<PebbleBagBlock> CODEC = simpleCodec(PebbleBagBlock::new);
	private static final VoxelShape[] DEFAULT_SHAPES = {
			Shapes.or(
					box(4, 0, 3, 13, 8, 12),
					box(1, 0, 1, 5, 3, 5),
					box(8, 0, 10, 12, 2, 14)
			).optimize(),
			Shapes.or(
					box(8, 0, 3, 12, 2, 7),
					box(4, 0, 5, 11, 6, 12)
			).optimize(),
			Shapes.or(
					box(1, 0, 6, 5, 3, 10),
					box(10, 0, 2, 14, 2, 6),
					box(3, 0, 8, 10, 6, 15)
			).optimize(),
			Shapes.or(
					box(8, 0, 10, 12, 3, 14),
					box(10, 0, 8, 14, 2, 12),
					box(2, 0, 4, 6, 1, 8)
			).optimize(),
			Shapes.or(
					box(10, 0, 9, 14, 2, 13),
					box(8, 0, 2, 12, 1, 6),
					box(3, 0, 8, 7, 1, 12)
			).optimize()
	};
	private static final Map<Direction, VoxelShape>[] SHAPES =
			new Map[DEFAULT_SHAPES.length];

	public PebbleBagBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState()
				.setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected IntegerProperty getPlantTypeProperty() {
		return PEBBLE_TYPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null) return null;
		return state
				.setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(PEBBLE_TYPE)].get(state.getValue(FACING));
	}

	static {
		for (int i = 0; i < DEFAULT_SHAPES.length; i++) {
			SHAPES[i] = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPES[i]);
		}
	}
}
