package net.toopa.unusual_furniture.neoforge.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import net.toopa.unusual_furniture.common.UnusualFurniture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.registries.BuiltInRegistries;

@Mixin(BuiltInRegistries.class)
public class BuiltInRegistriesMixin {

	@Definition(id = "createContents", method = "Lnet/minecraft/core/registries/BuiltInRegistries;createContents()V")
	@Expression("createContents()")
	@Inject(
			method = "bootStrap",
			at = @At("MIXINEXTRAS:EXPRESSION")
	)
	private static void unusual_furniture$onBootStrap(CallbackInfo ci) {
		UnusualFurniture.initRegistries();
	}
}
