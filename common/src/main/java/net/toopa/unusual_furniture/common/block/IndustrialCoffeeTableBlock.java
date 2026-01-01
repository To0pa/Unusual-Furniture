package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IndustrialCoffeeTableBlock extends CoffeeTableBlock {

	private static final VoxelShape SHAPE = Shapes.or(
			box(2.0, 14.0, 2.0, 14.0, 16.0, 14.0),
			box(2.0, 14.0, 0.0, 14.0, 16.0, 2.0),
			box(2.0, 14.0, 14.0, 14.0, 16.0, 16.0),
			box(0.0, 14.0, 0.0, 2.0, 16.0, 16.0),
			box(14.0, 14.0, 0.0, 16.0, 16.0, 16.0),
			box(7.0, 1.0, 7.0, 9.0, 16.0, 9.0),
			box(6.0, 0.0, 6.0, 10.0, 3.0, 10.0)
	);

	public IndustrialCoffeeTableBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
