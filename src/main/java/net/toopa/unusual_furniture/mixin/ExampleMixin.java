package net.toopa.unusual_furniture.mixin;

import net.toopa.unusual_furniture.UnusualFurniture;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {

    @Inject(method = "loadLevel", at = @At("RETURN"))
    private void afterLoadLevel(CallbackInfo ci) {
        UnusualFurniture.LOG.info("Level Loaded!");
    }

}
