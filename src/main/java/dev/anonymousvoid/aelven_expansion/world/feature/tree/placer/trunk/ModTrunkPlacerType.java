package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk;

import com.mojang.serialization.Codec;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerType {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY.registry(), AelvenExpansion.MODID);

    public static final RegistryObject<TrunkPlacerType<?>> LARGE_STRAIGHT_TRUNK_PLACER = TRUNK_PLACER_TYPES.register(
            "large_straight_trunk_placer", () -> new TrunkPlacerType<>(LargeStraightTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) { TRUNK_PLACER_TYPES.register(eventBus); }
}
