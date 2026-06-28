package net.toopa.unusual_furniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.util.Mth;

import net.toopa.unusual_furniture.client.model.DrawerModel;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.entity.DrawerBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class DrawerRenderer implements BlockEntityRenderer<DrawerBlockEntity> {
	private final DrawerModel model;

	public DrawerRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new DrawerModel(context.bakeLayer(DrawerModel.LAYER_LOCATION));
	}

	@Override
	public void render(DrawerBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		poseStack.pushPose();

		poseStack.translate(0.5f, -0.5f, 0.5f);

		ModelPart lid = this.model.getTop();

		float f = blockEntity.getOpenNess(partialTick);

		f = 1.0F - f;
		f = 1.0F - f * f * f;

		lid.xRot = -(f * (float)(Math.PI / 2));

		poseStack.mulPose(Axis.YP.rotationDegrees(switch (blockEntity.getBlockState().getValue(DrawerBlock.FACING)) {
			case EAST -> 270;
			case SOUTH -> 180;
			case WEST -> 90;
			default -> 0;
		}));

		this.model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entitySolid(DrawerModel.TEXTURE_LOCATION)), packedLight, packedOverlay);

		poseStack.popPose();
	}
}
