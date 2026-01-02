package net.toopa.unusual_furniture.common.utils.fabric;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class PlatformUtilsImpl {

	public static CreativeModeTab.Builder creativeModeTabBuilder() {
		return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0);
	}

	public static void registerRenderType(RenderType renderType, Block... blocks) {
		BlockRenderLayerMap.INSTANCE.putBlocks(renderType, blocks);
	}

	public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
		EntityRendererRegistry.register(type, provider);
	}
}
