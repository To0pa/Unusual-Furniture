package net.toopa.unusual_furniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.toopa.unusual_furniture.client.model.WallClockModel;
import net.toopa.unusual_furniture.common.block.WallClockBlock;
import net.toopa.unusual_furniture.common.block.entity.WallClockBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public class WallClockRenderer implements BlockEntityRenderer<WallClockBlockEntity> {
	private static final long TICKS_PER_DAY = 24000L;
	private static final long MINUTES_PER_DAY = 24L * 60L;
	private static final long CLOCK_FACE_OFFSET_TICKS = 6000L;

	private final WallClockModel model;

	public WallClockRenderer(BlockEntityRendererProvider.Context context) {
		this.model = new WallClockModel(context.bakeLayer(WallClockModel.LAYER_LOCATION));
	}

	@Override
	public void render(WallClockBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		Level level = blockEntity.getLevel();
		if (level == null) return;

		poseStack.pushPose();
		poseStack.translate(0.5F, 1.5F, 0.5F);
		poseStack.scale(-1.0F, -1.0F, 1.0F);

		long clockTicks = Math.floorMod(level.getDayTime() + CLOCK_FACE_OFFSET_TICKS, TICKS_PER_DAY);
		int totalMinutes = (int) (clockTicks * MINUTES_PER_DAY / TICKS_PER_DAY);

		int minute = totalMinutes % 60;
		int hour = totalMinutes % (12 * 60);

		float hourAngle = hour * 0.5F;
		float minuteAngle = minute * 6F;

		this.model.getHour().zRot = hourAngle * Mth.DEG_TO_RAD;
		this.model.getMinute().zRot = minuteAngle * Mth.DEG_TO_RAD;

		poseStack.mulPose(Axis.YP.rotationDegrees(switch (blockEntity.getBlockState().getValue(WallClockBlock.FACING)) {
			case SOUTH -> 180;
			case WEST -> 270;
			case EAST -> 90;
			default -> 0;
		}));

		this.model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(WallClockModel.TEXTURE_LOCATION)), packedLight, packedOverlay);
		poseStack.popPose();
	}
}
