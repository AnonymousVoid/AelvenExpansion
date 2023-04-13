package dev.anonymousvoid.aelven_expansion.integration;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.recipe.KilnRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIAelvenExpansionModPlugin implements IModPlugin {
    public static RecipeType<KilnRecipe> KILN_TYPE =
            new RecipeType<>(KilnRecipeCategory.UID, KilnRecipe.class);
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(AelvenExpansion.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                KilnRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<KilnRecipe> recipesKiln = rm.getAllRecipesFor(KilnRecipe.Type.INSTANCE);
        registration.addRecipes(KILN_TYPE, recipesKiln);
    }

//    @Override
//    public void registerItemSubtypes(ISubtypeRegistration registry) {
//        IIngredientSubtypeInterpreter<ItemStack> tables = (stack, context) -> {
//            if (context == UidContext.Ingredient) {
////                return RetexturedBlockItem;
//            }
//            return IIngredientSubtypeInterpreter.NONE;
//        };
//        registry.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, ModBlocks.KILN.get().asItem(), tables);
//    }
}
