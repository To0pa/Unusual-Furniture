package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.toopa.unusual_furniture.common.entity.SeatEntity;

public interface ISittableBlock {

	default boolean sitOn(Level world, BlockPos pos, Player player, Direction dir) {
		if (!world.isClientSide && !SeatEntity.SITTING_POSITIONS.get(world.dimension()).contains(pos)) {
			SeatEntity entity = SeatEntity.of(world, pos, dir);
			if (world.addFreshEntity(entity)) {
				player.startRiding(entity);
				return true;
			} else {
				entity.removeSeat();
			}
		}
		return false;
	}

	AABB getSeatSize(BlockState state);
}
