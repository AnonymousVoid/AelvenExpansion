package dev.anonymousvoid.aelven_expansion.event;


import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.particle.ModParticles;
import dev.anonymousvoid.aelven_expansion.particle.custom.EleriumFlameParticles;
import dev.anonymousvoid.aelven_expansion.particle.custom.RuningParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AelvenExpansion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.ELERIUM_FLAME_PARTICLES.get(),
                EleriumFlameParticles.Provider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.RUNING_PARTICLES.get(),
                RuningParticles.Provider::new);
    }
}
