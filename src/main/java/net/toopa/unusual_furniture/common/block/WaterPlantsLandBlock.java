package net.toopa.unusual_furniture.common.block;

import java.util.List;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.block.properties.WaterPlantProperty;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
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
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		WaterPlantProperty type = state.getValue(PLANT_TYPE);
		if (type == WaterPlantProperty.TOP) {
			BlockState below = level.getBlockState(pos.below());
			return below.is(this) && below.getValue(PLANT_TYPE) == WaterPlantProperty.BOTTOM;
		}
		return super.canSurvive(state, level, pos);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if ((facing == Direction.DOWN && !state.canSurvive(world, currentPos))
				|| (facing == Direction.UP && state.getValue(PLANT_TYPE) == WaterPlantProperty.BOTTOM && !facingState.is(this))) {
			return Blocks.AIR.defaultBlockState();
		}

		return state;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		if (!level.isClientSide && state.getValue(PLANT_TYPE) == WaterPlantProperty.SINGLE) {
			if (level.random.nextFloat() < 0.5f) {
				BlockPos abovePos = pos.above();
				if (level.getBlockState(abovePos).canBeReplaced() && pos.getY() < level.getMaxBuildHeight() - 1) {
					level.setBlock(pos, state.setValue(PLANT_TYPE, WaterPlantProperty.BOTTOM), 3);
					level.setBlock(abovePos, state.setValue(PLANT_TYPE, WaterPlantProperty.TOP), 3);
				}
			}
		}
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide) {
			WaterPlantProperty type = state.getValue(PLANT_TYPE);
			if (type == WaterPlantProperty.BOTTOM) {
				BlockPos abovePos = pos.above();
				if (level.getBlockState(abovePos).is(this)) {
					level.destroyBlock(abovePos, false);
				}
			} else if (type == WaterPlantProperty.TOP) {
				BlockPos belowPos = pos.below();
				if (level.getBlockState(belowPos).is(this)) {
					level.destroyBlock(belowPos, false);
				}
			}
		}
		return super.playerWillDestroy(level, pos, state, player);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos pos = context.getClickedPos();
		LevelAccessor level = context.getLevel();

		if (level.getBlockState(pos.below()).is(this) || level.getBlockState(pos.above()).is(this)) {
			return null;
		}

		return super.getStateForPlacement(context);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("block.unusual_furniture.water_plants.description_0"));
	}
}
