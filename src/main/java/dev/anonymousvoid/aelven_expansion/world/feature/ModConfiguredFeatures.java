package dev.anonymousvoid.aelven_expansion.world.feature;

import com.google.common.base.Suppliers;
import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.OasisConfiguration;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.WaterFungusConfiguration;
import dev.anonymousvoid.aelven_expansion.world.feature.custom.SpireConfiguration;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.foliage.DroopyFoliagePlacer;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.roots.PeachgroveRootPlacement;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.roots.PeachgroveRootPlacer;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk.LargeStraightTrunkPlacer;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk.SlantedTrunkPlacer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static class VegetalGeneration {
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> MOON_FIR_TREE =
                FeatureUtils.register("moon_fir_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.MOON_FIR_LOG.get()),
                        new SlantedTrunkPlacer(10, 5, 5),
                        BlockStateProvider.simple(ModBlocks.MOON_FIR_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SILVERBLOOD_TREE =
                FeatureUtils.register("silverblood_tree", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.SILVERBLOOD_LOG.get()),
                        new SlantedTrunkPlacer(10, 5, 5),
                        BlockStateProvider.simple(ModBlocks.SILVERBLOOD_LEAVES.get().defaultBlockState().setValue(BlockStateProperties.DISTANCE, 1)),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                        new TwoLayersFeatureSize(1, 0, 2)).build());

        /* > TODO
         * - ModBlockTags.java Class
         * - roots_can_grow_through.json BlockTag
         * - leaves_can_fall_on.json BlockTag
         */
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> PEACHGROVE_TREE =
                FeatureUtils.register("peachgrove_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.PEACHGROVE_LOG.get()),
                        new LargeStraightTrunkPlacer(10, 6, 3),
                        BlockStateProvider.simple(ModBlocks.PEACHGROVE_LEAVES.get()),
                        new DroopyFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0)),
                        Optional.of(new PeachgroveRootPlacer(UniformInt.of(0, 0),
                                BlockStateProvider.simple(ModBlocks.PEACHGROVE_ROOTS.get()), Optional.empty(),
                                new PeachgroveRootPlacement(Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), // TODO
                                HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, ModBlocks.MUDDY_PEACHGROVE_ROOTS.get()),
                                BlockStateProvider.simple(ModBlocks.MUDDY_PEACHGROVE_ROOTS.get()), 3, 9, 0.5F))),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).build());


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


        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_SILVER_MARIGOLD =
                FeatureUtils.register("patch_silver_marigold", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SILVER_MARIGOLD.get()))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_SILVER_SPRING =
                FeatureUtils.register("patch_silver_spring", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.SILVER_SPRING.get()))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MOON_BLOOM =
                FeatureUtils.register("patch_moon_bloom", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MOON_BLOOM.get()))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MOONSHADE =
                FeatureUtils.register("patch_moonshade", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.MOONSHADE.get()))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_PEACH_LAVENDER =
                FeatureUtils.register("patch_peach_lavender", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PEACH_LAVENDER.get()))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_PEACH_LILAC =
                FeatureUtils.register("patch_peach_lilac", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PEACH_LILAC.get()))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_MULCHY_GRASS =
                FeatureUtils.register("patch_mulchy_grass", Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(64,
                                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.MULCHY_GRASS.get())))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TALL_MULCHY_GRASS =
                FeatureUtils.register("patch_tall_mulchy_grass", Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(64,
                                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.TALL_MULCHY_GRASS.get())))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CHIPSTONE_GRASS_AND_BLADES =
                FeatureUtils.register("patch_chipstone_grass_and_blades", Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(64,
                                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                                .add(ModBlocks.CHIPSTONE_GRASS.get().defaultBlockState(), 3)
                                                .add(ModBlocks.CHIPSTONE_BLADES.get().defaultBlockState(), 1))))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TALL_CHIPSTONE_BLADES =
                FeatureUtils.register("patch_tall_chipstone_blades", Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(32,
                                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.TALL_CHIPSTONE_BLADES.get())))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_CHIPSTONE_BUSH =
                FeatureUtils.register("patch_chipstone_bush", Feature.RANDOM_PATCH,
                        FeatureUtils.simpleRandomPatchConfiguration(32,
                                PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.CHIPSTONE_BUSH.get())))));

        public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_HYDROSATIN_SPROUTS =
                FeatureUtils.register("patch_hydrosatin_sprouts", Feature.RANDOM_PATCH,
                        FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.HYDROSATIN_SPROUTS.get()))));

    }

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, AelvenExpansion.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> AELVEN_COAL_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.COAL_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_COAL_ORE.defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_COAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_COAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_COAL_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_COAL_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> AELVEN_IRON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.IRON_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_IRON_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_IRON_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_IRON_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_IRON_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> AELVEN_GOLD_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.GOLD_ORE.defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.DEEPSLATE_GOLD_ORE.defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_GOLD_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_GOLD_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_GOLD_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_GOLD_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ELERIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ELERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ELERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_ELERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_ELERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_ELERIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_ELERIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> SILVER_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_SILVER_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_SILVER_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> VABRIUM_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.VABRIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_VABRIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_VABRIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_VABRIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_VABRIUM_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_VABRIUM_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORHALT_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.ORHALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_ORHALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "chitta_ore_replaceables"))),
                    ModBlocks.CHITTA_ORHALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "drystone_ore_replaceables"))),
                    ModBlocks.DRYSTONE_ORHALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "hydrojade_ore_replaceables"))),
                    ModBlocks.HYDROJADE_ORHALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(TagKey.create(Registry.BLOCK_REGISTRY,
                            ResourceLocation.tryBuild(AelvenExpansion.MODID, "mudstone_ore_replaceables"))),
                    ModBlocks.MUDSTONE_ORHALT_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> AELVEN_COAL_ORE = CONFIGURED_FEATURES.register("aelven_coal_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(AELVEN_COAL_ORES.get(),12)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> AELVEN_IRON_ORE = CONFIGURED_FEATURES.register("aelven_iron_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(AELVEN_IRON_ORES.get(),8)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> AELVEN_GOLD_ORE = CONFIGURED_FEATURES.register("aelven_gold_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(AELVEN_GOLD_ORES.get(),6)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ELERIUM_ORE = CONFIGURED_FEATURES.register("elerium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ELERIUM_ORES.get(),10)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> SILVER_ORE = CONFIGURED_FEATURES.register("silver_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(SILVER_ORES.get(),6)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> VABRIUM_ORE = CONFIGURED_FEATURES.register("vabrium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(VABRIUM_ORES.get(),6)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ORHALT_ORE = CONFIGURED_FEATURES.register("orhalt_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ORHALT_ORES.get(),6)));


    public static final RegistryObject<ConfiguredFeature<?, ?>> CHIPSTONE_SPIRE = CONFIGURED_FEATURES.register("chipstone_spire",
            () -> new ConfiguredFeature<>(ModFeatures.SPIRE.get(), new SpireConfiguration(
                    ModBlocks.CHIPSTONE.get().defaultBlockState(),
                    UniformInt.of(20, 50),
                    ConstantInt.of(10),
                    ConstantFloat.of(5.0F))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> HYDROSATIN_FUNGUS_FEATURE = CONFIGURED_FEATURES.register("hydrosatin_fungus",
            () -> new ConfiguredFeature<>(ModFeatures.WATER_FUNGUS.get(), new WaterFungusConfiguration(
                    ModBlocks.HYDROSATIN_STEM.get().defaultBlockState(),
                    ModBlocks.HYDROSATIN_FUNGUS.get().defaultBlockState(),
                    ModBlocks.HYDROSATIN_CAP.get().defaultBlockState(),
                    ModBlocks.GLIMMERSATIN.get().defaultBlockState(),
                    ModBlocks.JADEGRASS_NYLIUM.get().defaultBlockState(),
                    UniformInt.of(8, 9),
                    UniformInt.of(5, 6),
                    ConstantInt.of(2))));


    public static final RegistryObject<ConfiguredFeature<?, ?>> CHIPSTONE_OASIS_FEATURE = CONFIGURED_FEATURES.register("chipstone_oasis",
            () -> new ConfiguredFeature<>(ModFeatures.OASIS.get(), new OasisConfiguration(
                    ModBlocks.CHIPSTONE_GRAVEL.get().defaultBlockState(),
                    ModBlocks.CHIPSTONE.get().defaultBlockState(),
                    ModBlocks.CHIPSTONE_GRASS.get().defaultBlockState(),
                    ModBlocks.CHIPSTONE_BLADES.get().defaultBlockState(),
                    ModBlocks.CHIPSTONE_LOG.get().defaultBlockState(),
                    ModBlocks.CHIPSTONE_LEAVES.get().defaultBlockState(),
                    ModBlocks.HANGING_CHIPSTONE_LEAVES.get().defaultBlockState(),
                    ModBlocks.CHIPSTONE.get().defaultBlockState(),
                    UniformInt.of(9, 12),
                    UniformInt.of(2, 3),
                    UniformInt.of(9, 12),
                    UniformInt.of(1, 2),
                    UniformInt.of(5, 7))));


    public static void register(IEventBus eventBus) { CONFIGURED_FEATURES.register(eventBus); }
}
