package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlackstonePotBlock extends AbstractPotBlock {

	private static final VoxelShape SHAPE = Shapes.or(
			box(2.0F, 0.0F, 2.0F, 14.0F, 14.0F, 14.0F),
			box(1.0F, 14.0F, 1.0F, 15.0F, 17.0F, 15.0F),
			box(3.0F, 16.0F, 3.0F, 13.0F, 17.0F, 13.0F),
			box(1.0F, 2.0F, 1.0F, 15.0F, 10.0F, 15.0F)
	);

	public BlackstonePotBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
