package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoolBlock extends SittableBlock {

	private static final MapCodec<StoolBlock> CODEC = simpleCodec(StoolBlock::new);
	private static final VoxelShape SHAPE = Shapes.or(
			box(2.0, 8.0, 2.0, 14.0, 11.0, 14.0),
			box(10.0, 0.0, 3.0, 13.0, 8.0, 6.0),
			box(10.0, 0.0, 10.0, 13.0, 8.0, 13.0),
			box(3.0, 0.0, 10.0, 6.0, 8.0, 13.0),
			box(3.0, 0.0, 3.0, 6.0, 8.0, 6.0)
	);

	public StoolBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
}
