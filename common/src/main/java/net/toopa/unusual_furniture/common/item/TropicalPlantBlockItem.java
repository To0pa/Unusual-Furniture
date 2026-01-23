package net.toopa.unusual_furniture.common.item;

import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.jspecify.annotations.Nullable;

public class TropicalPlantBlockItem extends BlockItem {

	private final Block wallBlock;
	private final Block floorBlock;

	public TropicalPlantBlockItem(Block floorBlock, Block wallBlock, Properties properties) {
		super(floorBlock, properties);
		this.floorBlock = floorBlock;
		this.wallBlock = wallBlock;
	}

	@Override
	protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
		Direction clickedFace = context.getClickedFace();

		if (clickedFace.getAxis().isHorizontal()) {
			return wallBlock.getStateForPlacement(context);
		}

		return floorBlock.getStateForPlacement(context);
	}
}
