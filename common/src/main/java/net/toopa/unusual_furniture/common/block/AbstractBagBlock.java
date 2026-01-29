package net.toopa.unusual_furniture.common.block;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public abstract class AbstractBagBlock extends BushBlock {

	public AbstractBagBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState().setValue(getPlantTypeProperty(), 0));
	}

	protected IntegerProperty getPlantTypeProperty() {
		throw new UnsupportedOperationException("Must be overridden by child class");
	}

	@Override
	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		super.onPlace(state, level, pos, oldState, movedByPiston);
		if (!level.isClientSide) {
			level.setBlock(pos, state.setValue(getPlantTypeProperty(), level.random.nextInt(getPlantTypeProperty().getPossibleValues().stream().max(Integer::compareTo).orElse(0) + 1)), Block.UPDATE_ALL);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(getPlantTypeProperty());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, list, flag);
		list.add(Component.translatable("block.unusual_furniture.tropical_plant.description_0"));
	}
}
