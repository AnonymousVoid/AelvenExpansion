package dev.anonymousvoid.aelven_expansion.world.biome;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, AelvenExpansion.MODID);


    public static final RegistryObject<Biome> CHALK_PEAKS = BIOMES.register("chalk_peaks",
            ModBiomes::createChalkPeaks);
    public static final RegistryObject<Biome> SILVERBLOOD_RISE = BIOMES.register("silverblood_rise",
            ModBiomes::createSilverbloodRise);
    public static final RegistryObject<Biome> DRYSTONE_PLAINS = BIOMES.register("drystone_plains",
            ModBiomes::createDrystonePlains);
    public static final RegistryObject<Biome> PEACHGROVE_SWAMP = BIOMES.register("peachgrove_swamp",
            ModBiomes::createPeachgroveSwamp);


    private static Biome createChalkPeaks() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(0.4F)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(0xCCCCFF)
                        .fogColor(0xBBBBEE)
                        .waterColor(0x6688FF)
                        .waterFogColor(0x4466EE)
                        .grassColorOverride(0xDDFFDD)
                        .foliageColorOverride(0xDDFFDD)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JAGGED_PEAKS)).build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    private static Biome createSilverbloodRise() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_FOREST);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.5F)
                .downfall(0.5F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(0x88CCFF)
                        .fogColor(0x88CCFF)
                        .waterColor(0x88CCFF)
                        .waterFogColor(0x88CCFF)
                        .grassColorOverride(0x22CC44)
                        .foliageColorOverride(0x22CC44)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_JUNGLE_AND_FOREST)).build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    private static Biome createDrystonePlains() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(0.5F)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(0x444444)
                        .fogColor(0x444444)
                        .waterColor(0x666666)
                        .waterFogColor(0x666666)
                        .grassColorOverride(0x116633)
                        .foliageColorOverride(0x116633)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_BASALT_DELTAS)).build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    private static Biome createPeachgroveSwamp() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CANYON);
        generationSettings.addFeature(GenerationStep.Decoration.LAKES, MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CavePlacements.GLOW_LICHEN);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEA_PICKLE);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.KELP_WARM);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.8F)
                .downfall(1.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(7907327)
                        .fogColor(12638463)
                        .waterColor(4603966)
                        .waterFogColor(4603966)
                        .grassColorOverride(6705463)
                        .foliageColorOverride(16212834)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP)).build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    public static void register(IEventBus eventBus) { BIOMES.register(eventBus); }
}
