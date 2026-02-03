package net.toopa.unusual_furniture.client;

import net.toopa.unusual_furniture.client.renderer.DrawerRenderer;
import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.utils.PlatformUtils;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.GrassColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class UnusualFurnitureClient {

	public static void init() {
		PlatformUtils.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_table")));
		PlatformUtils.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_coffee_table")));
		UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.FLOOR_LAMP_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.LAMP_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.translucent(), block));
		UFObjects.POT_BLOCKS.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutoutMipped(), block));
		PlatformUtils.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant")));
		PlatformUtils.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")));
		PlatformUtils.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants")));
		PlatformUtils.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")));
		UFObjects.POSTER_BLOCK.forEach((block, reLo) -> PlatformUtils.registerRenderType(RenderType.cutoutMipped(), block));
		PlatformUtils.registerBlockColors(
				(blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null
						? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos)
						: GrassColor.getDefaultColor(),
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant")),
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")),
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")));
		PlatformUtils.registerEntityRenderer(UFEntityTypes.SEAT, NoopRenderer::new);
		BlockEntityRenderers.register(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, DrawerRenderer::new);
	}
}
