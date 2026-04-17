package net.toopa.unusual_furniture.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.toopa.unusual_furniture.common.reg.UFBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.Vec3;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockStateBaseMixin {

	@ModifyReturnValue(
			method = "getOffset",
			at = @At("RETURN")
	)
	private static Vec3 unusual_furniture$onOffsetType(
			Vec3 original,
			@Local(argsOnly = true) BlockGetter level,
			@Local(argsOnly = true) BlockPos pos
	) {
		BlockPos.MutableBlockPos cursor = pos.mutable().move(0, -1, 0);

		while (cursor.getY() >= level.getMinBuildHeight()) {
			var state = level.getBlockState(cursor);

			if (state.is(UFBlockTags.FLOWER_POTS)) {
				return Vec3.ZERO;
			}
			if (!state.isAir() && !state.getCollisionShape(level, cursor).isEmpty()) {
				break;
			}
			cursor.move(0, -1, 0);
		}
		return original;
	}
}
