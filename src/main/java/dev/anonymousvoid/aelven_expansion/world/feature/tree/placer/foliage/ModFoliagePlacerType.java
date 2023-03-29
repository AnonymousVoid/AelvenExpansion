package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.foliage;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk.LargeStraightTrunkPlacer;
import dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.trunk.SlantedTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacerType {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registry.FOLIAGE_PLACER_TYPE_REGISTRY, AelvenExpansion.MODID);


    public static final RegistryObject<FoliagePlacerType<?>> DROOPY_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("droopy_foliage_placer",
                    () -> new FoliagePlacerType(DroopyFoliagePlacer.CODEC));


    public static void register(IEventBus eventbus) { FOLIAGE_PLACERS.register(eventbus); }

}
