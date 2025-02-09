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

    public static final RegistryObject<PlacedFeature> PATCH_CHIPSTONE_GRASS_AND_BLADES_PLACED =
            PLACED_FEATURES.register("patch_chipstone_grass_and_blades_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_CHIPSTONE_GRASS_AND_BLADES,
                            List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_TALL_CHIPSTONE_BLADES_PLACED =
            PLACED_FEATURES.register("patch_tall_chipstone_blades_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_TALL_CHIPSTONE_BLADES,
                            List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_CHIPSTONE_BUSH_PLACED =
            PLACED_FEATURES.register("patch_chipstone_bush_placed",
                    () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
                            ModConfiguredFeatures.VegetalGeneration.PATCH_CHIPSTONE_BUSH,
                            List.of(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));


    public static final RegistryObject<PlacedFeature> PATCH_HYDROSATIN_SPROUTS_PLACED =
            PLACED_FEATURES.register("patch_hydrosatin_sprouts_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.PATCH_HYDROSATIN_SPROUTS.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_JADEGRASS_PLACED =
            PLACED_FEATURES.register("patch_jadegrass_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.PATCH_JADEGRASS.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> PATCH_LUMINESCENT_KELP_PLACED =
            PLACED_FEATURES.register("patch_luminescent_kelp_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.PATCH_LUMINESCENT_KELP.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> CHIPSTONE_SPIRE_PLACED =
            PLACED_FEATURES.register("chipstone_spire_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.CHIPSTONE_SPIRE.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> HYDROSATIN_FUNGUS_PLACED =
            PLACED_FEATURES.register("hydrosatin_fungus_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.HYDROSATIN_FUNGUS_FEATURE.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome())));

    public static final RegistryObject<PlacedFeature> CHIPSTONE_OASIS_PLACED =
            PLACED_FEATURES.register("chipstone_oasis_placed",
                    () -> new PlacedFeature(ModConfiguredFeatures.CHIPSTONE_OASIS_FEATURE.getHolder().get(),
                            List.of(RarityFilter.onAverageOnceEvery(200), InSquarePlacement.spread(),
                                    PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome())));


    public static final RegistryObject<PlacedFeature> AELVEN_COAL_ORE_PLACED = PLACED_FEATURES.register("aelven_coal_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.AELVEN_COAL_ORE.getHolder().get(),
                    commonOrePlacement(20, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(200)))));

    public static final RegistryObject<PlacedFeature> AELVEN_IRON_ORE_PLACED = PLACED_FEATURES.register("aelven_iron_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.AELVEN_IRON_ORE.getHolder().get(),
                    commonOrePlacement(16, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-60), VerticalAnchor.aboveBottom(140)))));

    public static final RegistryObject<PlacedFeature> AELVEN_GOLD_ORE_PLACED = PLACED_FEATURES.register("aelven_gold_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.AELVEN_GOLD_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(120)))));

    public static final RegistryObject<PlacedFeature> ELERIUM_ORE_PLACED = PLACED_FEATURES.register("elerium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ELERIUM_ORE.getHolder().get(),
                    commonOrePlacement(12, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> SILVER_ORE_PLACED = PLACED_FEATURES.register("silver_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.SILVER_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-100), VerticalAnchor.aboveBottom(60)))));

    public static final RegistryObject<PlacedFeature> VABRIUM_ORE_PLACED = PLACED_FEATURES.register("vabrium_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.VABRIUM_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-100), VerticalAnchor.aboveBottom(60)))));

    public static final RegistryObject<PlacedFeature> ORHALT_ORE_PLACED = PLACED_FEATURES.register("orhalt_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ORHALT_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-100), VerticalAnchor.aboveBottom(60)))));




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
