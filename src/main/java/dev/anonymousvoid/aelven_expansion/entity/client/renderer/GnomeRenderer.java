package dev.anonymousvoid.aelven_expansion.entity.client.renderer;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.entity.client.model.GnomeModel;
import dev.anonymousvoid.aelven_expansion.entity.mob.Gnome;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GnomeRenderer extends MobRenderer<Gnome, GnomeModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AelvenExpansion.MODID, "textures/entity/gnome.png");
    public GnomeRenderer(EntityRendererProvider.Context context) {
        super(context, new GnomeModel(context.bakeLayer(GnomeModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Gnome pEntity) {
        return TEXTURE;
    }
}
