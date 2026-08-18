package net.toopa.unusual_furniture.client;

import net.toopa.unusual_furniture.CommonAbstraction;
import net.toopa.unusual_furniture.client.model.ClockModel;
import net.toopa.unusual_furniture.client.model.DrawerModel;
import net.toopa.unusual_furniture.client.particle.FurnitureSmokeParticle;
import net.toopa.unusual_furniture.client.renderer.DrawerRenderer;
import net.toopa.unusual_furniture.client.renderer.WallClockRenderer;
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

	public static void initEarly() {
		CommonAbstraction.INSTANCE.registerLayerDefinition(DrawerModel.LAYER_LOCATION, DrawerModel::createBodyLayer);
		CommonAbstraction.INSTANCE.registerLayerDefinition(ClockModel.LAYER_LOCATION, ClockModel::createBodyLayer);
		CommonAbstraction.INSTANCE.registerClientParticleType(UFParticleTypes.FURNITURE_SMOKE, FurnitureSmokeParticle.FurnitureSmokeParticleProvider::new);
	}

	public static void init() {
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(),
				UFObjects.INDUSTRIAL_TABLE,
				UFObjects.INDUSTRIAL_COFFEE_TABLE
		);
		UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.FLOOR_LAMP_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		UFObjects.LAMP_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.translucent(), block));
		UFObjects.POT_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(),
				UFObjects.TROPICAL_PLANT,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")),
				UFObjects.WOOD_SETS.get("bamboo").chair(),
				UFObjects.WATER_PLANTS,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water"))
		);
		UFObjects.POSTER_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.TRASH);
		UFObjects.TABLE_LAMP_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(),
				UFObjects.MANHOLE,
				UFObjects.DECORATIVE_TOOLBOX,
				UFObjects.PIG_PLUSH,
				UFObjects.COW_PLUSH,
				UFObjects.BROOM,
				UFObjects.RAKE
		);
		UFObjects.BROOM_BLOCKS.forEach((block, reLo) -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block));
		CommonAbstraction.INSTANCE.registerBlockColors(
				(blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null
						? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos)
						: GrassColor.getDefaultColor(),
				UFObjects.TROPICAL_PLANT,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")),
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")));
		CommonAbstraction.INSTANCE.registerEntityRenderer(UFEntityTypes.SEAT, NoopRenderer::new);
		BlockEntityRenderers.register(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, DrawerRenderer::new);
		BlockEntityRenderers.register(UFBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY, WallClockRenderer::new);
	}
}
