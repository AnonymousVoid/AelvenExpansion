package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk;

import com.mojang.serialization.Codec;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
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
