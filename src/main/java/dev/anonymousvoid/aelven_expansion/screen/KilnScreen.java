package dev.anonymousvoid.aelven_expansion.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KilnScreen extends AbstractContainerScreen<KilnMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AelvenExpansion.MODID, "textures/gui/kiln_gui.png");

    public KilnScreen(KilnMenu menu, Inventory inv, Component component) {
        super(menu, inv, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(stack, x, y);
    }

    private void renderProgressArrow(PoseStack stack, int x, int y) {
        if (menu.isCrafting()) {
            blit(stack, x+80, y+8, 176, 0, menu.getScaledProgress(), 16);
            blit(stack, x+80, y+34, 176, 0, menu.getScaledProgress(), 16);
            blit(stack, x+80, y+60, 176, 0, menu.getScaledProgress(), 16);
        }
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, delta);
        renderTooltip(stack, mouseX, mouseY);
    }
}
