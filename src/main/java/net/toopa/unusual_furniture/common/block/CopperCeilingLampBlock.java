package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CopperCeilingLampBlock extends CeilingLampBlock {

	public CopperCeilingLampBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void playSound(Level level, Player player, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		level.playSound(player, pos, SoundEvents.COPPER_STEP, SoundSource.BLOCKS, 1F, state.getValue(LIT) ? 3F : 2F);
	}
}
