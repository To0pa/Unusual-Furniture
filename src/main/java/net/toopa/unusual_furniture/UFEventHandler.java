package net.toopa.unusual_furniture;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

import net.minecraft.world.entity.player.Player;

import net.minecraft.world.level.Level;

import net.minecraft.world.phys.BlockHitResult;

import net.toopa.unusual_furniture.common.block.ISittableBlock;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;

public class UFEventHandler {

    public static InteractionResult onBlockClick(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
		var blockPos = hitResult.getBlockPos();
		var block = level.getBlockState(blockPos);
		if (block.is(UFBlockTags.SITTABLE_BLOCKS)) {
			if (ISittableBlock.sitOn(level, blockPos, player))
				return InteractionResult.CONSUME;
		}
		return InteractionResult.PASS;
    }

}
