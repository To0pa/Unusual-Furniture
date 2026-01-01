package net.toopa.unusual_furniture.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.toopa.unusual_furniture.client.renderer.DrawerRenderer;
import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;

public final class UnusualFurnitureClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(UFEntityTypes.SEAT, NoopRenderer::new); //TODO: make it all common
        BlockEntityRenderers.register(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, DrawerRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_table")), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BuiltInRegistries.BLOCK.get(UnusualFurniture.id("industrial_coffee_table")), RenderType.cutout());
        UFObjects.BENCH_BLOCKS.forEach((block, reLo) -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout()));
        UFObjects.CURTAIN_BLOCKS.forEach((block, reLo) -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout()));
    }
}
