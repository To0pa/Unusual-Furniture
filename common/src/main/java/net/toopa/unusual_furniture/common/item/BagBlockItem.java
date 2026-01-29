package net.toopa.unusual_furniture.common.item;

import org.jspecify.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BagBlockItem extends BlockItem {

	private final Block wallBlock;
	private final Block floorBlock;

	public BagBlockItem(Block floorBlock, Block wallBlock, Properties properties) {
		super(floorBlock, properties);
		this.floorBlock = floorBlock;
		this.wallBlock = wallBlock;
	}

	@Override
	protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
		Direction clickedFace = context.getClickedFace();

		BlockState state;
		if (clickedFace.getAxis().isHorizontal()) {
			state = wallBlock.getStateForPlacement(context);
		} else {
			state = floorBlock.getStateForPlacement(context);
		}

		return state != null && this.canPlace(context, state) ? state : null;
	}
}
