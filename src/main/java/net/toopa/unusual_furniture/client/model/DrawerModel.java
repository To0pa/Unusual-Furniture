package net.toopa.unusual_furniture.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.blockentity.SignRenderer;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class DrawerModel extends Model {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(UnusualFurniture.id("drawer"), "main_layer");
	private final ModelPart main;
	private final ModelPart top;
	private final ModelPart bottom;

	public DrawerModel(ModelPart root) {
		super(RenderType::entitySolid);
		this.main = root.getChild("main");
		this.top = this.main.getChild("top");
		this.bottom = this.main.getChild("bottom");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		main.addOrReplaceChild("top", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-7.0F, -13.0F, -8.001F, 14.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		main.addOrReplaceChild("bottom", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-7.0F, -7.0F, -8.001F, 14.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
		main.render(poseStack, vertexConsumer, i, j, k);
	}

	public ModelPart getMain() {
		return main;
	}

	public ModelPart getTop() {
		return top;
	}

	public ModelPart getBottom() {
		return bottom;
	}
}
