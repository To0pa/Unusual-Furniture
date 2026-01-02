package net.toopa.unusual_furniture.client;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.utils.PlatformUtils;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.registries.BuiltInRegistries;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class UnusualFurnitureClient {

	public static void init() {
		PlatformUtils.registerRenderType(RenderType.cutout(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_table")));
		PlatformUtils.registerRenderType(RenderType.cutout(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_coffee_table")));
		UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutout(), block));
		UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutout(), block));
		PlatformUtils.registerEntityRenderer(UFEntityTypes.SEAT, NoopRenderer::new);
	}
}
