package net.toopa.unusual_furniture.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.world.level.LevelReader;

import net.minecraft.world.level.block.state.BlockState;

import net.toopa.unusual_furniture.common.block.WaterPlantsBlock;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

	@ModifyReturnValue(method = "getOffset", at = @At("RETURN"))
	private Vec3 unusual_furniture$adjustOffset(
			Vec3 original,
			@Local(argsOnly = true) BlockGetter level,
			@Local(argsOnly = true) BlockPos pos
	) {
		if (original == Vec3.ZERO || original.equals(Vec3.ZERO)) {
			return original;
		}

		BlockBehaviour.BlockStateBase self = (BlockBehaviour.BlockStateBase) (Object) this;

		if (self.getBlock() instanceof WaterPlantsBlock
				&& self.getValue(WaterPlantsBlock.PLANT_TYPE) == 0) {
			return Vec3.ZERO;
		}

		if (!self.is(UFBlockTags.POT_PLANTABLE)) {
			return original;
		}

		if (level instanceof LevelReader reader && !reader.hasChunkAt(pos)) {
			return original;
		}

		BlockState below = level.getBlockState(pos.below());
		return below.is(UFBlockTags.POT) ? Vec3.ZERO : original;
	}
}
