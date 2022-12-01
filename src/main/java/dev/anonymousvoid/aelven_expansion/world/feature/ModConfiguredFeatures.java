package dev.anonymousvoid.aelven_expansion.world.feature;

import com.google.common.base.Suppliers;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static class TreeGeneration {
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MOON_FIR_TREE =
                FeatureUtils.register("moon_fir_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.MOON_FIR_LOG.get()),
                        new StraightTrunkPlacer(5, 6, 3),
                        BlockStateProvider.simple(ModBlocks.MOON_FIR_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SILVERBLOOD_TREE =
                FeatureUtils.register("silverblood_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.SILVERBLOOD_LOG.get()),
                        new StraightTrunkPlacer(5, 6, 3),
                        BlockStateProvider.simple(ModBlocks.SILVERBLOOD_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PEACHGROVE_TREE =
                FeatureUtils.register("peachggrove_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.PEACHGROVE_LOG.get()),
                        new StraightTrunkPlacer(5, 6, 3),
                        BlockStateProvider.simple(ModBlocks.PEACHGROVE_LEAVES.get()),
                        new RandomSpreadFoliagePlacer(ConstantInt.of(5), ConstantInt.of(2), ConstantInt.of(4), 100),
                        new TwoLayersFeatureSize(1, 0, 2)).build());


        public static final Holder<PlacedFeature> MOON_FIR_TREE_CHECKED = PlacementUtils.register("moon_fir_tree_checked",
                MOON_FIR_TREE, PlacementUtils.filteredByBlockSurvival(ModBlocks.MOON_FIR_SAPLING.get()));
        public static final Holder<PlacedFeature> SILVERBLOOD_TREE_CHECKED = PlacementUtils.register("silverblood_tree_checked",
                SILVERBLOOD_TREE, PlacementUtils.filteredByBlockSurvival(ModBlocks.SILVERBLOOD_SAPLING.get()));
        public static final Holder<PlacedFeature> PEACHGROVE_TREE_CHECKED = PlacementUtils.register("peachgrove_tree_checked",
                PEACHGROVE_TREE, PlacementUtils.filteredByBlockSurvival(ModBlocks.PEACHGROVE_SAPLING.get()));


        public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> MOON_FIR_TREE_SPAWN =
                FeatureUtils.register("moon_fir_tree_spawn", Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(MOON_FIR_TREE_CHECKED,
                                0.5f)), MOON_FIR_TREE_CHECKED));
        public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> SILVERBLOOD_TREE_SPAWN =
                FeatureUtils.register("silverblood_tree_spawn", Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(SILVERBLOOD_TREE_CHECKED,
                                0.5f)), SILVERBLOOD_TREE_CHECKED));
        public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> PEACHGROVE_TREE_SPAWN =
                FeatureUtils.register("peachgrove_tree_spawn", Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(PEACHGROVE_TREE_CHECKED,
                                0.5f)), PEACHGROVE_TREE_CHECKED));
    }

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, AelvenExpansion.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_ELERIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ELERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ELERIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SILVER_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ELERIUM_ORE = CONFIGURED_FEATURES.register("elerium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_ELERIUM_ORES.get(),7)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE = CONFIGURED_FEATURES.register("silver_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SILVER_ORES.get(),7)));

    public static void register(IEventBus eventBus) {
         CONFIGURED_FEATURES.register(eventBus);
    }


}
