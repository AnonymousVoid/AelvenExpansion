package dev.anonymousvoid.aelven_expansion.event;


import dev.anonymousvoid.aelven_expansion.AelvenExpansion;
import dev.anonymousvoid.aelven_expansion.entity.ModEntityTypes;
import dev.anonymousvoid.aelven_expansion.entity.client.renderer.GnomeRenderer;
import dev.anonymousvoid.aelven_expansion.entity.mob.Gnome;
import dev.anonymousvoid.aelven_expansion.particle.ModParticles;
import dev.anonymousvoid.aelven_expansion.particle.custom.EleriumFlameParticles;
import dev.anonymousvoid.aelven_expansion.particle.custom.RuningParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.GNOME.get(), Gnome.createAttributes().build());
    }
}
