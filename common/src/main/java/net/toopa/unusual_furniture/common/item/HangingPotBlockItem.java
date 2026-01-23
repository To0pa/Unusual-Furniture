package net.toopa.unusual_furniture.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;

import org.jspecify.annotations.Nullable;

public class HangingPotBlockItem extends BlockItem {

	public HangingPotBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public @Nullable BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
		if (context.getClickedFace() == Direction.DOWN) {
			BlockPos newPos = context.getClickedPos().below();
			if (!context.getLevel().getBlockState(newPos).canBeReplaced(context)) {
				return null;
			}
			return BlockPlaceContext.at(context, newPos, context.getClickedFace());
		}
		return super.updatePlacementContext(context);
	}
}
