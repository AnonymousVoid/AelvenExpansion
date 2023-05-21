package dev.anonymousvoid.aelven_expansion.entity.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.entity.client.model.ModBoatModel;
import dev.anonymousvoid.aelven_expansion.entity.vehicle.ModBoat;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends EntityRenderer<ModBoat> {
    private final Map<ModBoat.Type, Pair<ResourceLocation, ModBoatModel>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean b) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources = Stream.of(ModBoat.Type.values()).collect(
                ImmutableMap.toImmutableMap((type) -> type,
                        (type) -> Pair.of(new ResourceLocation(getTextureLocation(type, b)),
                                this.createBoatModel(context, type, b))));
    }

    public ModBoatRenderer(EntityRendererProvider.Context context) {
        this(context, false);
    }

    private ModBoatModel createBoatModel(EntityRendererProvider.Context context, ModBoat.Type type, boolean b) {
        ModelLayerLocation modellayerlocation = b ? createChestBoatModelName(type) : createBoatModelName(type);
        return new ModBoatModel(context.bakeLayer(modellayerlocation), b);
    }

    private static String getTextureLocation(ModBoat.Type type, boolean b) {
        return b ? "textures/entity/mod_chest_boat/" + type.getName() + ".png" : "textures/entity/mod_boat/" + type.getName() + ".png";
    }

    public void render(ModBoat pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        pMatrixStack.translate(0.0D, 0.375D, 0.0D);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - pEntityYaw));
        float f = (float)pEntity.getHurtTime() - pPartialTicks;
        float f1 = pEntity.getDamage() - pPartialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f) * f * f1 / 10.0F * (float)pEntity.getHurtDir()));
        }

        float f2 = pEntity.getBubbleAngle(pPartialTicks);
        if (!Mth.equal(f2, 0.0F)) {
            pMatrixStack.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), pEntity.getBubbleAngle(pPartialTicks), true));
        }

        Pair<ResourceLocation, ModBoatModel> pair = getModelWithLocation(pEntity);
        ResourceLocation resourcelocation = pair.getFirst();
        ModBoatModel boatmodel = pair.getSecond();
        pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        boatmodel.setupAnim(pEntity, pPartialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(boatmodel.renderType(resourcelocation));
        boatmodel.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!pEntity.isUnderWater()) {
            VertexConsumer vertexconsumer1 = pBuffer.getBuffer(RenderType.waterMask());
            boatmodel.waterPatch().render(pMatrixStack, vertexconsumer1, pPackedLight, OverlayTexture.NO_OVERLAY);
        }

        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Deprecated // forge: override getModelWithLocation to change the texture / model
    public @NotNull ResourceLocation getTextureLocation(@NotNull ModBoat pEntity) {
        return getModelWithLocation(pEntity).getFirst();
    }

    public Pair<ResourceLocation, ModBoatModel> getModelWithLocation(ModBoat boat) { return this.boatResources.get(boat.getModBoatType()); }


    public static ModelLayerLocation createBoatModelName(ModBoat.Type type) {
        return new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                "mod_boat/" + type.getName()), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(ModBoat.Type type) {
        return new ModelLayerLocation(new ResourceLocation(AelvenExpansion.MODID,
                "mod_chest_boat/" + type.getName()), "main");
    }
}
