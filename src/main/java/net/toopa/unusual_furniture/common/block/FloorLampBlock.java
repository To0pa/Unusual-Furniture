package net.toopa.unusual_furniture.common.block;

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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.block.properties.FloorLampProperty;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class FloorLampBlock extends Block {

	public static final EnumProperty<FloorLampProperty> SHAPE = EnumProperty.create("shape", FloorLampProperty.class);
	private static final Map<FloorLampProperty, VoxelShape> SHAPE_MAP = Map.of(
			FloorLampProperty.SINGLE, Shapes.or(
					box(5.0F, 0.0F, 5.0F, 11.0F, 1.0F, 11.0F),
					box(6.0F, 15.0F, 6.0F, 10.0F, 16.0F, 10.0F),
					box(7.0F, 1.0F, 7.0F, 9.0F, 10.0F, 9.0F),
					box(3.0F, 7.0F, 3.0F, 13.0F, 15.0F, 13.0F),
					box(6.0F, 9.0F, 6.0F, 10.0F, 13.0F, 10.0F),
					box(3.0F, 7.0F, 3.0F, 13.0F, 15.0F, 13.0F)
			),
			FloorLampProperty.TOP, Shapes.or(
					box(6.0F, 15.0F, 6.0F, 10.0F, 16.0F, 10.0F),
					box(7.0F, 0.0F, 7.0F, 9.0F, 10.0F, 9.0F),
					box(3.0F, 7.0F, 3.0F, 13.0F, 15.0F, 13.0F),
					box(6.0F, 9.0F, 6.0F, 10.0F, 13.0F, 10.0F),
					box(3.0F, 7.0F, 3.0F, 13.0F, 15.0F, 13.0F)
			),
			FloorLampProperty.BOTTOM, Shapes.or(
					box(7.0F, 0.0F, 7.0F, 9.0F, 16.0F, 9.0F),
					box(5.0F, 0.0F, 5.0F, 11.0F, 1.0F, 11.0F)
			),
			FloorLampProperty.MIDDLE, box(7.0F, 0.0F, 7.0F, 9.0F, 16.0F, 9.0F)
	);

	public FloorLampBlock(Properties properties) {
		super(properties.noCollission());
		registerDefaultState(defaultBlockState()
				.setValue(SHAPE, FloorLampProperty.SINGLE));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SHAPE);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE_MAP.get(state.getValue(SHAPE));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (facing == Direction.DOWN && !state.canSurvive(world, currentPos)) {
			return Blocks.AIR.defaultBlockState();
		}

		FloorLampProperty type = state.getValue(SHAPE);

		if (facing == Direction.UP) {
			if (facingState.is(this)) {
				BlockState below = world.getBlockState(currentPos.below());
				return state.setValue(SHAPE, below.is(this) ? FloorLampProperty.MIDDLE : FloorLampProperty.BOTTOM);
			} else if (type == FloorLampProperty.BOTTOM || type == FloorLampProperty.MIDDLE) {
				BlockState below = world.getBlockState(currentPos.below());
				return state.setValue(
						SHAPE,
						below.is(this) ? FloorLampProperty.TOP : FloorLampProperty.SINGLE
				);
			}
		}

		if (facing == Direction.DOWN) {
			if (facingState.is(this)) {
				BlockState above = world.getBlockState(currentPos.above());
				return state.setValue(SHAPE, above.is(this) ? FloorLampProperty.MIDDLE : FloorLampProperty.TOP);
			} else if (type == FloorLampProperty.TOP || type == FloorLampProperty.MIDDLE) {
				BlockState above = world.getBlockState(currentPos.above());
				return state.setValue(
						SHAPE,
						above.is(this) ? FloorLampProperty.BOTTOM : FloorLampProperty.SINGLE
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

		FloorLampProperty type;
		if (hasPlantAbove && hasPlantBelow) {
			type = FloorLampProperty.MIDDLE;
		} else if (hasPlantAbove) {
			type = FloorLampProperty.BOTTOM;
		} else if (hasPlantBelow) {
			type = FloorLampProperty.TOP;
		} else {
			type = FloorLampProperty.SINGLE;
		}

		BlockState state = super.getStateForPlacement(context);
		if (state == null) return null;
		return state.setValue(SHAPE, type);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("block.unusual_furniture.spruce_floor_lamp.description_0"));
	}
}
