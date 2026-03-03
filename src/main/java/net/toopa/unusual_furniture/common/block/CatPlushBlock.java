package net.toopa.unusual_furniture.common.block;

import java.util.Map;

import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CatPlushBlock extends PlushBlock {

	private static final VoxelShape DEFAULT_SHAPE = box(4.0F, 0.0F, 1.0F, 12.0F, 10.0F, 14.0F);
	private static final Map<Direction, VoxelShape> SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPE);

	public CatPlushBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(FACING));
	}
}
