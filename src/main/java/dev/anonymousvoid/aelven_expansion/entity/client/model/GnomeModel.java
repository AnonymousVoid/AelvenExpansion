package dev.anonymousvoid.aelven_expansion.entity.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.entity.mob.Gnome;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GnomeModel extends EntityModel<Gnome> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID, "gnome"), "main");
	private final ModelPart core;

	public GnomeModel(ModelPart root) {
		this.core = root.getChild("core");
	}

	public static LayerDefinition createBodyLayer() {
		var meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -8.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition rarm = core.addOrReplaceChild("rarm", CubeListBuilder.create().texOffs(24, 0).addBox(-5.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));
		PartDefinition larm = core.addOrReplaceChild("larm", CubeListBuilder.create().texOffs(24, 8).addBox(3.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition lleg = core.addOrReplaceChild("lleg", CubeListBuilder.create().texOffs(8, 22).mirror().addBox(0.5F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -3.0F, 0.0F));
		PartDefinition rleg = core.addOrReplaceChild("rleg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.5F, 1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition head = core.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(12, 23).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(20, 20).addBox(-1.5F, -3.0F, -4.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.01F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -3.5F, -1.0F, 0.0873F, 1.1781F, 0.0F));
		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.01F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.5F, -1.0F, 0.0873F, -1.1781F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Gnome entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.core.getChild("head").yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.core.getChild("head").xRot = headPitch * ((float)Math.PI / 180F);

		this.core.getChild("lleg").xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.core.getChild("rleg").xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		this.core.getChild("larm").xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.core.getChild("rarm").xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}