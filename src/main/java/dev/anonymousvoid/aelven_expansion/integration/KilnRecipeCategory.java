package dev.anonymousvoid.aelven_expansion.integration;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.item.ModItems;
import dev.anonymousvoid.aelven_expansion.recipe.KilnRecipe;
import dev.anonymousvoid.aelven_expansion.recipe.ModRecipes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
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

    public KilnRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.KILN.get()));
    }

    @Override
    public RecipeType<KilnRecipe> getRecipeType() {
        return JEIAelvenExpansionModPlugin.KILN_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Kiln");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, KilnRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT,20,35).addIngredients(Ingredient.of(new ItemStack(ModItems.ELERIUM.get())));
        builder.addSlot(RecipeIngredientRole.INPUT,56,17).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT,116,9).addItemStack(recipe.getResultItem());
    }
}
