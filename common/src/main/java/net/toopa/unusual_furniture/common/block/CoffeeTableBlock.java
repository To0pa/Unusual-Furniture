package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CoffeeTableBlock extends TableBlock {

	private static final VoxelShape SHAPE = Shapes.or(
			box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
			box(12.0, 0.0, 12.0, 15.0, 12.0, 15.0),
			box(1.0, 0.0, 12.0, 4.0, 12.0, 15.0),
			box(12.0, 0.0, 1.0, 15.0, 12.0, 4.0),
			box(1.0, 0.0, 1.0, 4.0, 12.0, 4.0),
			box(1.0, 5.0, 1.0, 15.0, 8.0, 15.0),
			box(0.0, 12.0, 0.0, 16.0, 16.0, 16.0),
			box(1.0, 5.0, 1.0, 15.0, 8.0, 15.0),
			box(1.0, 0.0, 1.0, 4.0, 12.0, 4.0),
			box(12.0, 0.0, 1.0, 15.0, 12.0, 4.0),
			box(1.0, 0.0, 12.0, 4.0, 12.0, 15.0),
			box(12.0, 0.0, 12.0, 15.0, 12.0, 15.0)
	);

	public CoffeeTableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
