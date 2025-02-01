package dev.anonymousvoid.aelven_expansion.world.feature;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, AelvenExpansion.MODID);


    public static final RegistryObject<Feature<SpireConfiguration>> SPIRE =
            FEATURES.register("spire", () -> new SpireFeature(SpireConfiguration.CODEC));
    public static final RegistryObject<Feature<WaterFungusConfiguration>> WATER_FUNGUS =
            FEATURES.register("water_fungus", () -> new WaterFungusFeature(WaterFungusConfiguration.CODEC));
    public static final RegistryObject<Feature<OasisConfiguration>> OASIS =
            FEATURES.register("oasis", () -> new OasisFeature(OasisConfiguration.CODEC));
    public static final RegistryObject<Feature<UnderwaterRandomPatchConfiguration>> UNDERWATER_RANDOM_PATCH =
            FEATURES.register("underwater_random_patch", () -> new UnderwaterRandomPatchFeature(UnderwaterRandomPatchConfiguration.CODEC));
    public static final RegistryObject<Feature<ModdedKelpPatchConfiguration>> MODDED_KELP_PATCH =
            FEATURES.register("modded_kelp_patch", () -> new ModdedKelpPatchFeature(ModdedKelpPatchConfiguration.CODEC));


    public static void register(IEventBus bus) { FEATURES.register(bus); }
}
