package net.toopa.unusual_furniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.toopa.unusual_furniture.client.model.ClockModel;
import net.toopa.unusual_furniture.common.block.WallClockBlock;
import net.toopa.unusual_furniture.common.block.entity.WallClockBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class WallClockRenderer implements BlockEntityRenderer<WallClockBlockEntity> {
	private final ClockModel model;

	public WallClockRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new ClockModel(context.bakeLayer(ClockModel.LAYER_LOCATION));
	}

	@Override
	public void render(WallClockBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		Level level = blockEntity.getLevel();
		if (level == null) return;

		poseStack.pushPose();
		poseStack.translate(0.5F, 1.5F, 0.5F);
		poseStack.scale(-1.0F, -1.0F, 1.0F);

		long dayTime = level.getDayTime() % 24000L;
		float dayFraction = (dayTime / 24000F + 0.25F) % 1.0F; // noon is 6000

		float hourAngle = (dayFraction * 2F % 1.0F) * 360F;
		float minuteAngle = (dayFraction * 24F * 60F % 60F) / 60F * 360F;

		this.model.getHour().zRot = hourAngle * Mth.DEG_TO_RAD;
		this.model.getMinute().zRot = minuteAngle * Mth.DEG_TO_RAD;

		poseStack.mulPose(Axis.YP.rotationDegrees(switch (blockEntity.getBlockState().getValue(WallClockBlock.FACING)) {
			case NORTH -> 180;
			case WEST -> 270;
			case EAST -> 90;
			default -> 0;
		}));

		this.model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(ClockModel.TEXTURE_LOCATION)), packedLight, packedOverlay);
		poseStack.popPose();
	}
}
