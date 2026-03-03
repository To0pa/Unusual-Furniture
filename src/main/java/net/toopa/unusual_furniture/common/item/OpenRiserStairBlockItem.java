package net.toopa.unusual_furniture.common.item;

import net.toopa.unusual_furniture.common.block.OpenRiserStairBlock;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class OpenRiserStairBlockItem extends BlockItem {

	public OpenRiserStairBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Nullable
	@Override
	public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
		Level level = context.getLevel();
		Block block = this.getBlock();
		BlockPos clickedPos = context.getClickedPos().relative(context.getClickedFace().getOpposite());
		BlockState clickedState = level.getBlockState(clickedPos);

		if (!clickedState.is(block)) return context;

		Direction stairsFacing = clickedState.getValue(OpenRiserStairBlock.FACING);
		Direction playerDir = context.getHorizontalDirection();
		boolean goingUp = (stairsFacing == playerDir.getOpposite());

		if (goingUp) {
			BlockPos targetPos = clickedPos.relative(playerDir).relative(Direction.UP);

			if (level.isInWorldBounds(targetPos) && level.getBlockState(targetPos).canBeReplaced(context)) {
				return BlockPlaceContext.at(context, targetPos, playerDir);
			}
		}
		return context;
	}

	@Override
	protected boolean mustSurvive() {
		return false;
	}
}
