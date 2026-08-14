package net.toopa.unusual_furniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.toopa.unusual_furniture.client.model.DrawerModel;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.entity.DrawerBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;

public class DrawerRenderer implements BlockEntityRenderer<DrawerBlockEntity> {
	private final DrawerModel model;

	public DrawerRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new DrawerModel(context.bakeLayer(DrawerModel.LAYER_LOCATION));
	}

	@Override
	public void render(DrawerBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		poseStack.pushPose();

		poseStack.translate(0.5F, 1.5F, 0.5F);
		poseStack.scale(-1.0F, -1.0F, 1.0F);

		float open = blockEntity.getOpenNess(partialTick);

		float topProgress = Mth.clamp(open / 0.3333F, 0.0F, 1.0F);
		float bottomProgress = Mth.clamp(open / 0.4167F, 0.0F, 1.0F);

		topProgress = drawerCurve(topProgress);
		bottomProgress = drawerCurve(bottomProgress);

		this.model.getTop().z = -7.0F * topProgress;
		this.model.getBottom().z = -10.0F * bottomProgress;

		poseStack.mulPose(Axis.YP.rotationDegrees(switch (blockEntity.getBlockState().getValue(DrawerBlock.FACING)) {
			case EAST -> 270;
			case SOUTH -> 180;
			case WEST -> 90;
			default -> 0;
		}));

		this.model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entitySolid(DrawerModel.TEXTURE_LOCATION)), packedLight, packedOverlay);

		poseStack.popPose();
	}

	private static float drawerCurve(float t) {
		t = Mth.clamp(t, 0.0F, 1.0F);

		float t2 = t * t;
		float t3 = t2 * t;

		return 3.0F * t2 - 2.0F * t3;
	}
}
