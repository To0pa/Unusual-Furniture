package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TropicalPlantBlock extends AbstractBagBlock {

	private static final VoxelShape SHAPE = box(4.0F, 0.0F, 4.0F, 12.0F, 12.0F, 12.0F);

	public TropicalPlantBlock(Properties properties) {
		super(properties.noCollission(), 2);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
