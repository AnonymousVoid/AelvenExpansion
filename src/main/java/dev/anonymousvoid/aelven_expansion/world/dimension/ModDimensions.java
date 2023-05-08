package dev.anonymousvoid.aelven_expansion.world.dimension;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> AELFHEIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(AelvenExpansion.MODID, "aelfheim"));
    public static final ResourceKey<DimensionType> AELFHEIM_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, AELFHEIM_KEY.location());
    
    public static void register() {
        System.out.println("Registering ModDimensions for " + AelvenExpansion.MODID);
    }
}
