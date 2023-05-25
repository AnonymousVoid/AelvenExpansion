package dev.anonymousvoid.aelven_expansion.world.feature;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.BladeFeature;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.SpireFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, AelvenExpansion.MODID);


    public static final RegistryObject<Feature<BlockStateConfiguration>> SPIRE =
            FEATURES.register("spire", () -> new SpireFeature(BlockStateConfiguration.CODEC));
    public static final RegistryObject<Feature<BlockStateConfiguration>> BLADE =
            FEATURES.register("blade", () -> new BladeFeature(BlockStateConfiguration.CODEC));


    public static void register(IEventBus bus) { FEATURES.register(bus); }
}
