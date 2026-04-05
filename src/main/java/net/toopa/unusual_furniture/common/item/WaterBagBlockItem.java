package net.toopa.unusual_furniture.common.item;

import net.minecraft.tags.FluidTags;

import net.minecraft.world.level.block.Blocks;

import org.jspecify.annotations.Nullable;

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

public class WaterBagBlockItem extends BlockItem {

	private final Block waterBlock;
	private final Block floorBlock;

	public WaterBagBlockItem(Block floorBlock, Block waterBlock, Properties properties) {
		super(floorBlock, properties);
		this.floorBlock = floorBlock;
		this.waterBlock = waterBlock;
	}

	public InteractionResult useOn(UseOnContext useOnContext) {
		BlockState aboveState = useOnContext.getLevel().getBlockState(useOnContext.getClickedPos().above());
		if (aboveState.is(Blocks.WATER)) {
			return InteractionResult.PASS;
		}
		return super.useOn(useOnContext);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
		BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockHitResult2 = blockHitResult.withPosition(blockHitResult.getBlockPos().above());
		InteractionResult interactionResult = super.useOn(new UseOnContext(player, interactionHand, blockHitResult2));
		return new InteractionResultHolder<>(interactionResult, player.getItemInHand(interactionHand));
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
