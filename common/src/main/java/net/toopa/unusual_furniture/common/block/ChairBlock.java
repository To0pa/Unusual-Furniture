package net.toopa.unusual_furniture.common.block;

import java.util.Map;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChairBlock extends SittableBlock {

	private static final MapCodec<ChairBlock> CODEC = simpleCodec(ChairBlock::new);
	private static final VoxelShape DEFAULT_SHAPE = Shapes.or(
			box(2.0, 8.0, 2.0, 14.0, 11.0, 14.0),
			box(11.0, 0.0, 2.0, 14.0, 8.0, 5.0),
			box(11.0, 0.0, 11.0, 14.0, 8.0, 14.0),
			box(2.0, 11.0, 11.0, 5.0, 13.0, 14.0),
			box(11.0, 11.0, 11.0, 14.0, 13.0, 14.0),
			box(2.0, 0.0, 11.0, 5.0, 8.0, 14.0),
			box(2.0, 0.0, 2.0, 5.0, 8.0, 5.0),
			box(2.0, 13.0, 11.0, 14.0, 21.0, 14.0)
	);
	private static final Map<Direction, VoxelShape> SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPE);

	public ChairBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(FACING));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
