package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TropicalPlantBlock extends AbstractBagBlock {

	private static final VoxelShape SHAPE = box(4.0F, 0.0F, 4.0F, 12.0F, 12.0F, 12.0F);
	public static final IntegerProperty PLANT_TYPE = IntegerProperty.create("plant_type", 0, 2);
	private static final MapCodec<TropicalPlantBlock> CODEC = simpleCodec(TropicalPlantBlock::new);

	public TropicalPlantBlock(Properties properties) {
		super(properties.noCollission());
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected IntegerProperty getPlantTypeProperty() {
		return PLANT_TYPE;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
