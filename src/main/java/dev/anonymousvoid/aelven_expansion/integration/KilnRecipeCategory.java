package dev.anonymousvoid.aelven_expansion.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.recipe.KilnRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class KilnRecipeCategory implements IRecipeCategory<KilnRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(AelvenExpansion.MODID, "kiln");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(AelvenExpansion.MODID, "textures/gui/kiln_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public KilnRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.KILN.get()));

        arrow = helper.drawableBuilder(TEXTURE, 176, 0, 26, 15)
                .buildAnimated(240, IDrawableAnimated.StartDirection.LEFT, false);
    }

//    @Override
//    public ResourceLocation getUid() {
//        return RecipeType.create(AelvenExpansion.MODID, "kiln", #class);
//    }

    @Override
    public RecipeType<KilnRecipe> getRecipeType() { return JEIAelvenExpansionModPlugin.KILN_TYPE; }

    @Override
    public Component getTitle() { return Component.translatable("jei.kilning"); }

    @Override
    public IDrawable getBackground() { return this.background; }

    @Override
    public IDrawable getIcon() { return this.icon; }

    @Override
    public void draw(KilnRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
//        arrow.draw(stack, 80, 8);
        arrow.draw(stack, 80, 34);
//        arrow.draw(stack, 80, 60);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KilnRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,20,35).addIngredients(Ingredient.of(new ItemStack(ModItems.ELERIUM.get(), 3)));

//        builder.addSlot(RecipeIngredientRole.INPUT,56,17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT,56,35).addIngredients(recipe.getIngredients().get(0));
//        builder.addSlot(RecipeIngredientRole.INPUT,56,53).addIngredients(recipe.getIngredients().get(0));

//        builder.addSlot(RecipeIngredientRole.OUTPUT,116,9).addItemStack(recipe.getResultItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT,116,35).addItemStack(recipe.getResultItem());
//        builder.addSlot(RecipeIngredientRole.OUTPUT,116,61).addItemStack(recipe.getResultItem());

        builder.addInvisibleIngredients(RecipeIngredientRole.CATALYST).addItemStack(new ItemStack(ModBlocks.KILN.get()));
    }
}
