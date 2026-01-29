package net.toopa.unusual_furniture.common.block;

import java.util.Map;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MushroomPatchBlock extends AbstractBagBlock {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	private static final VoxelShape DEFAULT_SHAPE = Shapes.or(
			box(9.0F, 0.0F, 12.0F, 11.0F, 2.0F, 14.0F),
			box(3.0F, 0.0F, 4.0F, 5.0F, 3.0F, 6.0F),
			box(11.0F, 0.0F, 5.0F, 13.0F, 2.0F, 7.0F),
			box(1.0F, 3.0F, 2.0F, 7.0F, 5.0F, 8.0F),
			box(7.0F, 2.0F, 10.0F, 13.0F, 4.0F, 16.0F),
			box(10.0F, 2.0F, 4.0F, 14.0F, 4.0F, 8.0F)
	).optimize();
	private static final Map<Direction, VoxelShape> SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_SHAPE);
	public static final IntegerProperty PLANT_TYPE = IntegerProperty.create("plant_type", 0, 1);
	private static final MapCodec<MushroomPatchBlock> CODEC = simpleCodec(MushroomPatchBlock::new);

	public MushroomPatchBlock(Properties properties) {
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
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state != null)
			return state
					.setValue(FACING, context.getHorizontalDirection().getOpposite());
		else return null;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(FACING));
	}
}
