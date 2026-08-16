package net.toopa.unusual_furniture.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import net.minecraft.resources.ResourceLocation;

public class ClockModel extends Model {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(UnusualFurniture.id("wall_clock"), "main_layer");
	public static final ResourceLocation TEXTURE_LOCATION = UnusualFurniture.id("textures/block/animclock.png");

	private final ModelPart main;
	private final ModelPart minute;
	private final ModelPart hour;

	public ClockModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.main = root.getChild("main");
		this.minute = this.main.getChild("minute");
		this.hour = this.main.getChild("hour");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-6.0F, -12.0F, 6.0F, 11.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 13)
				.addBox(-3.5F, -16.0F, 7.8F, 6.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition minute = main.addOrReplaceChild("minute", CubeListBuilder.create(), PartPose.offset(-0.5F, -6.5F, 5.9F));
		minute.addOrReplaceChild("cube_r1", CubeListBuilder.create()
				.texOffs(12, 14)
				.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition hour = main.addOrReplaceChild("hour", CubeListBuilder.create(), PartPose.offset(-0.5F, -6.5F, 5.8F));
		hour.addOrReplaceChild("cube_r2", CubeListBuilder.create()
				.texOffs(12, 13)
				.addBox(-0.5F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, int k) {
		main.render(poseStack, vertexConsumer, i, j, k);
	}

	public ModelPart getMain() {
		return main;
	}

	public ModelPart getMinute() {
		return minute;
	}

	public ModelPart getHour() {
		return hour;
	}
}
