package net.toopa.unusual_furniture.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public class DrawerModel<T extends Entity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER = new ModelLayerLocation(UnusualFurniture.id("drawer_model"), "main");
	public final ModelPart top;
	public final ModelPart bottom;
	public final ModelPart bb_main;

	public DrawerModel(ModelPart root) {
		this.top = root.getChild("top");
		this.bottom = root.getChild("bottom");
		this.bb_main = root.getChild("bb_main");
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.top.render(poseStack, buffer, packedLight, packedOverlay, color);
		this.bottom.render(poseStack, buffer, packedLight, packedOverlay, color);
		this.bb_main.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}
