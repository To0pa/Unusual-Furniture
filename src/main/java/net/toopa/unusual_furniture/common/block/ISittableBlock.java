package net.toopa.unusual_furniture.common.block;

import net.toopa.unusual_furniture.common.entity.SeatEntity;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public interface ISittableBlock {

	default boolean sitOn(Level world, BlockPos pos, Player player, Direction dir) {
		if (!world.isClientSide && !SeatEntity.SITTING_POSITIONS.get(world.dimension()).contains(pos)) {
			SeatEntity entity = SeatEntity.of(world, pos, dir);
			if (world.addFreshEntity(entity)) {
				player.startRiding(entity);
				BlockState state = world.getBlockState(pos);
				if (state.is(UFBlockTags.CHAIR) || state.is(UFBlockTags.STOOL) || state.is(UFBlockTags.BENCH)) {
					world.playSound(null, pos, SoundEvents.WOOD_STEP, SoundSource.BLOCKS, 0.6F, 0.5F);
				} else if (state.is(UFBlockTags.SOFA)) {
					world.playSound(null, pos, SoundEvents.WOOL_STEP, SoundSource.BLOCKS, 0.6F, 0.5F);
				}
				return true;
			} else {
				entity.removeSeat();
			}
		}
		return false;
	}

	AABB getSeatSize(BlockState state);
}
