package dev.anonymousvoid.aelven_expansion.world.biome;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.world.feature.*;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
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

    private static Biome createPeachgroveSwamp() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 50, 0, 50, false);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);

        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);

        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).temperature(0.8F)
                .downfall(0.4F).specialEffects((new BiomeSpecialEffects.Builder())
                        .skyColor(7907327).fogColor(12638463).waterColor(4603966)
                        .waterFogColor(4603966).grassColorOverride(3288374)
                        .foliageColorOverride(11741754).build()).mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();

    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
