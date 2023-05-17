package dev.anonymousvoid.aelven_expansion.world.feature.tree.placer.roots;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRootPlacerType {
    public static final DeferredRegister<RootPlacerType<?>> ROOT_PLACERS =
            DeferredRegister.create(Registry.ROOT_PLACER_TYPE_REGISTRY, AelvenExpansion.MODID);


    public static final RegistryObject<RootPlacerType<?>> PEACHGROVE_ROOT_PLACER =
            ROOT_PLACERS.register("peachgrove_root_placer",
                    () -> new RootPlacerType(PeachgroveRootPlacer.CODEC));


    public static void register(IEventBus eventbus) { ROOT_PLACERS.register(eventbus); }

}
