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
		CommonAbstraction.INSTANCE.registerClientParticleType(UFParticleTypes.FURNITURE_SMOKE.get(), FurnitureSmokeParticle.FurnitureSmokeParticleProvider::new);
	}

	public static void init() {
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(),
				UFObjects.INDUSTRIAL_TABLE,
				UFObjects.INDUSTRIAL_COFFEE_TABLE
		);
		UFObjects.BENCH_BLOCKS.stream().forEach(block -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block.get()));
		UFObjects.CURTAIN_BLOCKS.stream().forEach(block -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block.get()));
		UFObjects.FLOOR_LAMP_BLOCKS.stream().forEach(block -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block.get()));
		UFObjects.LAMP_BLOCKS.stream().forEach(block -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.translucent(), block.get()));
		UFObjects.POT_BLOCKS.stream().forEach(block -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block.get()));
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(),
				UFObjects.TROPICAL_PLANT,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")),
				UFObjects.WOOD_SETS.get("bamboo").chair(),
				UFObjects.WATER_PLANTS,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")),
				UFObjects.POSTER
		);
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), UFObjects.TRASH);
		UFObjects.TABLE_LAMP_BLOCKS.stream().forEach(block -> CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(), block.get()));
		CommonAbstraction.INSTANCE.registerRenderType(RenderType.cutoutMipped(),
				UFObjects.MANHOLE,
				UFObjects.DECORATIVE_TOOLBOX,
				UFObjects.PIG_PLUSH,
				UFObjects.COW_PLUSH,
				UFObjects.BROOM,
				UFObjects.RAKE
		);
		CommonAbstraction.INSTANCE.registerBlockColors(
				(blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null
						? BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos)
						: GrassColor.getDefaultColor(),
				UFObjects.TROPICAL_PLANT,
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("tropical_plant_wall")),
				BuiltInRegistries.BLOCK.get(UnusualFurniture.id("water_plants_water")));
		CommonAbstraction.INSTANCE.registerEntityRenderer(UFEntityTypes.SEAT.get(), NoopRenderer::new);
		BlockEntityRenderers.register(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY.get(), DrawerRenderer::new);
		BlockEntityRenderers.register(UFBlockEntityTypes.WALL_CLOCK_BLOCK_ENTITY.get(), WallClockRenderer::new);
	}
}
