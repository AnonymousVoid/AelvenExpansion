package dev.anonymousvoid.aelven_expansion.world.feature;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.SpireConfiguration;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.SpireFeature;
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


    public static void register(IEventBus bus) { FEATURES.register(bus); }
}
