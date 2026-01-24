package net.toopa.unusual_furniture.common.block;

import java.util.Map;

import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SphereLampBlock extends AbstractLampBlock {

	public static VoxelShape DEFAULT_SHAPE = Shapes.or(
			box(5.0F, -0.05, 4.95, 11.0F, 1.95, 10.95),
			box(6.0F, 3.95, 5.95, 10.0F, 7.95, 9.95),
			box(7.0F, 1.95, 6.95, 9.0F, 3.95, 8.95),
			box(3.0F, 1.95, 2.95, 13.0F, 10.95, 12.95),
			box(5.0F, 10.95, 4.95, 11.0F, 11.95, 10.95)
	);
	private static final Map<Direction, VoxelShape> SHAPE_MAP = VoxelShapeUtils.createFullDirectionalShapeMap(DEFAULT_SHAPE, Direction.DOWN);

	public SphereLampBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(FACING));
	}
}
