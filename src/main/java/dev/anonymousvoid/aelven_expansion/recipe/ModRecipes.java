package dev.anonymousvoid.aelven_expansion.recipe;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AelvenExpansion.MODID);

    public static final RegistryObject<RecipeSerializer<KilnRecipe>> KILN_SERIALIZER =
            SERIALIZERS.register("kiln", () -> KilnRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
