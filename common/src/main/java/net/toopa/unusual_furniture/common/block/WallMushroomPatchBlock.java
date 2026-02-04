package net.toopa.unusual_furniture.common.block;

import java.util.Map;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallMushroomPatchBlock extends AbstractWallBagBlock {

	private static final MapCodec<WallMushroomPatchBlock> CODEC = simpleCodec(WallMushroomPatchBlock::new);
	private static final VoxelShape DEFAULT_SHAPE1 = Shapes.or(
			box(7.0F, 3.0F, 12.0F, 13.0F, 5.0F, 18.0F),
			box(1.0F, 8.0F, 13.0F, 7.0F, 10.0F, 19.0F),
			box(11.0F, 11.0F, 13.0F, 15.0F, 13.0F, 17.0F)
	).optimize();
	private static final Map<Direction, VoxelShape> SHAPE_MAP1 = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPE1);
	private static final VoxelShape DEFAULT_SHAPE2 = Shapes.or(
			box(13.0F, 3.0F, 14.0F, 19.0F, 5.0F, 20.0F),
			box(5.0F, 7.0F, 13.0F, 11.0F, 9.0F, 19.0F),
			box(14.0F, 11.0F, 14.0F, 18.0F, 13.0F, 18.0F)
	).optimize();
	private static final Map<Direction, VoxelShape> SHAPE_MAP2 = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPE2);
	// If plant_type%2 == 0 it is a red mushroom
	public static final IntegerProperty PLANT_TYPE = IntegerProperty.create("plant_type", 0, 3);

	public WallMushroomPatchBlock(Properties properties) {
		super(properties.noCollission());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(PLANT_TYPE);
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(PLANT_TYPE) / 2 == 0) {
			return SHAPE_MAP1.get(state.getValue(FACING));
		} else if (state.getValue(PLANT_TYPE) / 2 == 1) {
			return SHAPE_MAP2.get(state.getValue(FACING));
		} else {
			return Shapes.block();
		}
	}

	@Override
	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		super.onPlace(state, level, pos, oldState, movedByPiston);
		if (!level.isClientSide) {
			level.setBlock(pos, state.setValue(PLANT_TYPE, level.random.nextInt(PLANT_TYPE.getPossibleValues().stream().max(Integer::compareTo).orElse(0) + 1)), Block.UPDATE_ALL);
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(UFObjects.MUSHROOM_PATCH);
	}
}
