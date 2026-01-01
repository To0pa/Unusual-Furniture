package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IndustrialTableBlock extends TableBlock {

	private static final VoxelShape SHAPE = Shapes.or(
			box(-8.0, 14.0, -8.0, 24.0, 16.0, 24.0),
			box(7.0, 1.0, 7.0, 9.0, 16.0, 9.0),
			box(6.0, 0.0, 6.0, 10.0, 3.0, 10.0)
	);

	public IndustrialTableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
