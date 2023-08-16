package dev.anonymousvoid.aelven_expansion.world.feature;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, AelvenExpansion.MODID);


    public static final RegistryObject<PlacedFeature> MOON_FIR_TREE_PLACED =
            PLACED_FEATURES.register("moon_fir_tree_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.MOON_FIR_TREE_SPAWN, VegetationPlacements.treePlacement(
                            PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> SILVERBLOOD_TREE_PLACED =
            PLACED_FEATURES.register("silverblood_tree_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.SILVERBLOOD_TREE_SPAWN, VegetationPlacements.treePlacement(
                            PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> PEACHGROVE_TREE_PLACED =
            PLACED_FEATURES.register("peachgrove_tree_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PEACHGROVE_TREE_SPAWN, VegetationPlacements.treePlacement(
                            PlacementUtils.countExtra(3, 0.1f, 2))));

    public static final RegistryObject<PlacedFeature> PATCH_SILVER_MARIGOLD_PLACED =
            PLACED_FEATURES.register("patch_silver_marigold_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_SILVER_MARIGOLD,
                            List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_SILVER_SPRING_PLACED =
            PLACED_FEATURES.register("patch_silver_spring_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_SILVER_SPRING,
                            List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_MOON_BLOOM_PLACED =
            PLACED_FEATURES.register("patch_moon_bloom_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_MOON_BLOOM,
                            List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_MOONSHADE_PLACED =
            PLACED_FEATURES.register("patch_moonshade_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_MOONSHADE,
                            List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_PEACH_LAVENDER_PLACED =
            PLACED_FEATURES.register("patch_peach_lavender_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_PEACH_LAVENDER,
                            List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_PEACH_LILAC_PLACED =
            PLACED_FEATURES.register("patch_peach_lilac_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_PEACH_LILAC,
                            List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_MULCHY_GRASS_PLACED =
            PLACED_FEATURES.register("patch_mulchy_grass_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_MULCHY_GRASS,
                            List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_TALL_MULCHY_GRASS_PLACED =
            PLACED_FEATURES.register("patch_tall_mulchy_grass_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_TALL_MULCHY_GRASS,
                            List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> ELERIUM_ORE_PLACED = PLACED_FEATURES.register("elerium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ELERIUM_ORE.getHolder().get(),
                    commonOrePlacement(16, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED = PLACED_FEATURES.register("silver_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.SILVER_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-100), VerticalAnchor.aboveBottom(100)))));

    public static final RegistryObject<PlacedFeature> CHIPSTONE_SPIRE_PLACED =
            PLACED_FEATURES.register("chipstone_spire_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.CHIPSTONE_SPIRE.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));



    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventbus) { PLACED_FEATURES.register(eventbus); }

}
