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


    public static final RegistryObject<Biome> PEACHGROVE_SWAMP = BIOMES.register("peachgrove_swamp",
            ModBiomes::createPeachgroveSwamp);
    public static final RegistryObject<Biome> CHALK_PEAKS = BIOMES.register("chalk_peaks",
            ModBiomes::createChalkPeaks);


    private static Biome createPeachgroveSwamp() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
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
                .downfall(0.4F)
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

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
