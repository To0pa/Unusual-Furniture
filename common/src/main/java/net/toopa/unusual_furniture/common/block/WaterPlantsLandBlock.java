package net.toopa.unusual_furniture.common.block;

import java.util.List;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.block.properties.WaterPlantProperty;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WaterPlantsLandBlock extends BushBlock {

	public static final EnumProperty<WaterPlantProperty> PLANT_TYPE = EnumProperty.create("plant_type", WaterPlantProperty.class);
	private static final MapCodec<WaterPlantsLandBlock> CODEC = simpleCodec(WaterPlantsLandBlock::new);
	private static final VoxelShape SHAPE = box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);

	public WaterPlantsLandBlock(Properties properties) {
		super(properties.noCollission());
		this.registerDefaultState(this.stateDefinition.any().setValue(PLANT_TYPE, WaterPlantProperty.SINGLE));
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(PLANT_TYPE);
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		return super.mayPlaceOn(state, level, pos) || state.is(this);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.DOWN && !state.canSurvive(world, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		}

		WaterPlantProperty type = state.getValue(PLANT_TYPE);

		if (facing == Direction.UP) {
			if (facingState.is(this)) {
				return state.setValue(PLANT_TYPE, WaterPlantProperty.BOTTOM);
			} else if (type == WaterPlantProperty.BOTTOM) {
				return state.setValue(
						PLANT_TYPE,
						world.getBlockState(currentPos.below()).is(this)
								? WaterPlantProperty.TOP
								: WaterPlantProperty.SINGLE
				);
			}
		}

		if (facing == Direction.DOWN) {
			if (facingState.is(this)) {
				return state.setValue(PLANT_TYPE, WaterPlantProperty.TOP);
			} else if (type == WaterPlantProperty.TOP) {
				return state.setValue(
						PLANT_TYPE,
						world.getBlockState(currentPos.above()).is(this)
								? WaterPlantProperty.BOTTOM
								: WaterPlantProperty.SINGLE
				);
			}
		}

		return state;
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		LevelAccessor level = context.getLevel();

		boolean hasPlantAbove = level.getBlockState(pos.above()).is(this);
		boolean hasPlantBelow = level.getBlockState(pos.below()).is(this);

		WaterPlantProperty type;
		if (hasPlantAbove) {
			type = WaterPlantProperty.BOTTOM;
		} else if (hasPlantBelow) {
			type = WaterPlantProperty.TOP;
		} else {
			type = WaterPlantProperty.SINGLE;
		}

		BlockState state = super.getStateForPlacement(context);
		if (state == null) return null;
		return state.setValue(PLANT_TYPE, type);

	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("block.unusual_furniture.water_plants.description_0"));
	}
}
