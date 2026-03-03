package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import java.util.Map;

public class WallTropicalPlantBlock extends AbstractWallBagBlock {

	private static final MapCodec<WallTropicalPlantBlock> CODEC = simpleCodec(WallTropicalPlantBlock::new);
	private static final VoxelShape DEFAULT_SHAPE = box(0.0F, 0.0F, 14.0F, 16.0F, 16.0F, 16.0F);
	private static final Map<Direction, VoxelShape> SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPE);

	public WallTropicalPlantBlock(Properties properties) {
		super(properties.noCollission());
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(FACING));
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(UFObjects.TROPICAL_PLANT);
	}
}
