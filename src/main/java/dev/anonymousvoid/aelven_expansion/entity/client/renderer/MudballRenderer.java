package dev.anonymousvoid.aelven_expansion.entity.client.renderer;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.entity.client.model.GnomeModel;
import dev.anonymousvoid.aelven_expansion.entity.mob.Gnome;
import dev.anonymousvoid.aelven_expansion.entity.projectile.Mudball;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MudballRenderer extends EntityRenderer<Mudball> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(AelvenExpansion.MODID, "textures/item/mudball.png");
    public MudballRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Mudball pEntity) {
        return TEXTURE;
    }
}
