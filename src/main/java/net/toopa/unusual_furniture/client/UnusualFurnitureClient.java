package net.toopa.unusual_furniture.client;

import net.toopa.unusual_furniture.Platform;
import net.toopa.unusual_furniture.client.particle.FurnitureSmokeParticle;
import net.toopa.unusual_furniture.client.renderer.DrawerRenderer;
import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.reg.UFParticleTypes;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.GrassColor;

public class UnusualFurnitureClient {

	public static void init() {
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.INDUSTRIAL_TABLE);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.INDUSTRIAL_COFFEE_TABLE);
		UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.FLOOR_LAMP_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.LAMP_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.translucent(), block));
		UFObjects.POT_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.TROPICAL_PLANT);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")));
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.WATER_PLANTS);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")));
		UFObjects.POSTER_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.TRASH);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.MANHOLE);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.DECORATIVE_TOOLBOX);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.PIG_PLUSH);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.COW_PLUSH);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.BROOM);
		Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.RAKE);
		UFObjects.BROOM_BLOCKS.forEach((block, reLo) -> Platform.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		Platform.INSTANCE.registerBlockColors(
				(blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null
						? BiomeColors.getAverageGrassColor(blockAndTintGetter, blockPos)
						: GrassColor.getDefaultColor(),
				UFObjects.TROPICAL_PLANT,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")),
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")));
		Platform.INSTANCE.registerEntityRenderer(UFEntityTypes.SEAT, NoopRenderer::new);
		BlockEntityRenderers.register(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, DrawerRenderer::new);
		Platform.INSTANCE.registerClientParticleType(UFParticleTypes.FURNITURE_SMOKE, FurnitureSmokeParticle.FurnitureSmokeParticleProvider::new);
	}
}
