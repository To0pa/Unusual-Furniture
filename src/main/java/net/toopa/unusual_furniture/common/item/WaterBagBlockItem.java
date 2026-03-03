package net.toopa.unusual_furniture.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class WaterBagBlockItem extends BlockItem {

	private final Block waterBlock;
	private final Block floorBlock;

	public WaterBagBlockItem(Block floorBlock, Block waterBlock, Properties properties) {
		super(floorBlock, properties);
		this.floorBlock = floorBlock;
		this.waterBlock = waterBlock;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		return super.useOn(context);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
		BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockHitResult2 = blockHitResult.withPosition(blockHitResult.getBlockPos().above());
		InteractionResult interactionResult = super.useOn(new UseOnContext(player, usedHand, blockHitResult2));
		return new InteractionResultHolder<>(interactionResult, player.getItemInHand(usedHand));
	}

	@Override
	protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
		BlockPos clickedPos = context.getClickedPos();
		BlockState belowState = context.getLevel().getBlockState(clickedPos.below());

		BlockState state;
		if (belowState.getFluidState().is(Fluids.WATER) && belowState.getFluidState().isSource()) {
			state = waterBlock.getStateForPlacement(context);
		} else {
			state = floorBlock.getStateForPlacement(context);
		}

		return state != null && this.canPlace(context, state) ? state : null;
	}
}
