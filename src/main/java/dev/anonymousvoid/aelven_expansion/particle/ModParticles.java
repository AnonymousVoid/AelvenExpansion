package dev.anonymousvoid.aelven_expansion.particle;

import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AelvenExpansion.MODID);

    public static final RegistryObject<SimpleParticleType> ELERIUM_FLAME_PARTICLES =
            PARTICLE_TYPES.register("elerium_flame_particles", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> RUNING_PARTICLES =
            PARTICLE_TYPES.register("runing_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
