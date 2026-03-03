package net.toopa.unusual_furniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.block.entity.DrawerBlockEntity;

public class DrawerRenderer implements BlockEntityRenderer<DrawerBlockEntity> {

	public DrawerRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(DrawerBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		poseStack.pushPose();

		poseStack.translate(0.5, 0.5, 0.5);

		TextureManager textureManager = Minecraft.getInstance().getTextureManager();

		textureManager.bindForSetup(UnusualFurniture.id("textures/block/acacia_drawer_java.png"));

		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockEntity.getBlockState(), poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);

		poseStack.popPose();
	}
}
