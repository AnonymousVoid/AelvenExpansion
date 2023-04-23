package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerType {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registry.TRUNK_PLACER_TYPE_REGISTRY, AelvenExpansion.MODID);


    public static final RegistryObject<TrunkPlacerType<?>> LARGE_STRAIGHT_TRUNK_PLACER =
            TRUNK_PLACERS.register("large_straight_trunk_placer",
                    () -> new TrunkPlacerType(LargeStraightTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<?>> SLANTED_TRUNK_PLACER =
            TRUNK_PLACERS.register("slanted_trunk_placer",
                    () -> new TrunkPlacerType(SlantedTrunkPlacer.CODEC));


    public static void register(IEventBus eventbus) { TRUNK_PLACERS.register(eventbus); }

}
