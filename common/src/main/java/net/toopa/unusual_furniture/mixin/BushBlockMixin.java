package net.toopa.unusual_furniture.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BushBlock.class)
public class BushBlockMixin {

	@ModifyReturnValue(
			method = "mayPlaceOn",
			at = @At("RETURN")
	)
	private boolean unusual_furniture$onMayPlaceOn(boolean original, @Local(argsOnly = true) BlockState blockState) {
		return original
//                || blockState.getBlock() instanceof WoodenHangingPotBlock
//                || blockState.getBlock() instanceof LargeHangingPotBlock
				;
	}
}
