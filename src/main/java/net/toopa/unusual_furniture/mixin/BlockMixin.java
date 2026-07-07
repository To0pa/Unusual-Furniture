package net.toopa.unusual_furniture.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.state.BlockState;

import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import net.toopa.unusual_furniture.common.reg.UFParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

	@Inject(method = "setPlacedBy", at = @At("HEAD"))
	private void unusual_furniture$sendSmokeParticles(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack, CallbackInfo ci) {
		if (level instanceof ServerLevel serverLevel) {
			if (blockState.is(UFBlockTags.EMITS_DUST_PARTICLES)) {
				serverLevel.sendParticles(
						UFParticleTypes.FURNITURE_SMOKE, blockPos.getX() + 0.5, blockPos.getY() + 0.25, blockPos.getZ() + 0.5, 16, 0.6, 0.1, 0.6, 0.03
				);
			}
		}
	}
}
